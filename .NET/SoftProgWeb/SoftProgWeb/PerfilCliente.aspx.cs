using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftProgWeb.SoftProgWS;

namespace SoftProgWeb
{
    public partial class PerfilCliente : System.Web.UI.Page
    {
        private ClienteWSClient clienteWS = new ClienteWSClient();
        private CuentaUsuarioWSClient cuentaUsuarioWS = new CuentaUsuarioWSClient();
        private EmpresaWSClient empresaWS = new EmpresaWSClient();
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarDatosPerfil();
            }
        }

        private void CargarDatosPerfil()
        {
            try
            {
                if (Session["dni"] == null)
                {
                    Response.Redirect("InicioSesion.aspx");
                    return;
                }

                string dni = Session["dni"].ToString();

                // 1. Obtener cliente por DNI desde el WS
                cliente cli = clienteWS.buscarPorDni(dni);

                if (cli == null)
                {
                    MostrarError("No se encontró el cliente.");
                    return;
                }

                // 2. Mostrar datos reales
                txtNombre.Text = cli.nombre;
                txtApellido.Text = cli.apellidoPaterno + " " + cli.apellidoMaterno;
                //txtRuc.Text = cli.dni;
                txtTelefono.Text = cli.telefono.ToString();

                // 3. Obtener email desde su cuenta
                if (cli.cuenta != null)
                {
                    txtPassword.Attributes["value"] = "**********";

                    string correo = cli.cuenta.correo;

                    string script = $@"
                document.getElementById('displayName').textContent = '{cli.nombre} {cli.apellidoPaterno}';
                document.getElementById('displayEmail').textContent = '{correo}';
                document.getElementById('emailPrincipal').textContent = '{correo}';";

                    ScriptManager.RegisterStartupScript(this, GetType(), "updateHeader", script, true);
                }
                else
                {
                    MostrarError("No se encontró la cuenta asociada.");
                }
            }
            catch (System.Exception ex)
            {
                MostrarError("Error al cargar los datos del perfil: " + ex.Message);
            }
        }

        protected void btnChangePassword_Click(object sender, EventArgs e)
        {
            try
            {
                string currentPassword = txtCurrentPassword.Text.Trim();
                string newPassword = txtNewPassword.Text.Trim();

                // Validar campos
                if (string.IsNullOrEmpty(currentPassword) || string.IsNullOrEmpty(newPassword))
                {
                    MostrarError("Debe completar ambos campos de contraseña.");
                    return;
                }

                if (newPassword.Length < 6)
                {
                    MostrarError("La nueva contraseña debe tener al menos 6 caracteres.");
                    return;
                }

                //CAMBIAR LA CONTRASEÑA EN LA BASE DE DATOS
                bool cambioExitoso = CambiarPassword(currentPassword, newPassword);

                if (cambioExitoso)
                {
                    txtCurrentPassword.Text = string.Empty;
                    txtNewPassword.Text = string.Empty;

                    ScriptManager.RegisterStartupScript(this, GetType(), "showConfirmation",
                        "showConfirmationModal();", true);
                }
                else
                {
                    MostrarError("La contraseña actual no es correcta.");
                }
            }
            catch (System.Exception ex)
            {
                MostrarError("Error al cambiar la contraseña: " + ex.Message);
            }
        }

        private bool CambiarPassword(string currentPassword, string newPassword)
        {
            try
            {
                if (Session["usuario"] == null)
                    return false;

                string username = Session["usuario"].ToString();

                return cuentaUsuarioWS.cambiarPassword(username, currentPassword, newPassword);
            }
            catch
            {
                return false;
            }
        }

        private string EncriptarPassword(string password)
        {
            using (var sha256 = System.Security.Cryptography.SHA256.Create())
            {
                byte[] bytes = sha256.ComputeHash(System.Text.Encoding.UTF8.GetBytes(password));
                return Convert.ToBase64String(bytes);
            }
        }

        protected void btnGuardarCambios_Click(object sender, EventArgs e)
        {
            try
            {
                // Validar datos
                if (string.IsNullOrWhiteSpace(txtNombre.Text))
                {
                    MostrarError("El nombre es obligatorio.");
                    return;
                }

                if (string.IsNullOrWhiteSpace(txtApellido.Text))
                {
                    MostrarError("El apellido es obligatorio.");
                    return;
                }

                if (string.IsNullOrWhiteSpace(txtTelefono.Text))
                {
                    MostrarError("El teléfono es obligatorio.");
                    return;
                }

                // GUARDAR CAMBIOS EN LA BASE DE DATOS
                bool guardadoExitoso = GuardarCambiosPerfil();

                if (guardadoExitoso)
                {
                    MostrarExito("Perfil actualizado correctamente.");
                    CargarDatosPerfil(); 
                }
                else
                {
                    MostrarError("No se pudo actualizar el perfil.");
                }
            }
            catch (System.Exception ex)
            {
                MostrarError("Error al guardar los cambios: " + ex.Message);
            }
        }

        private bool GuardarCambiosPerfil()
        {
            try
            {
                if (Session["dni"] == null)
                    return false;

                string dni = Session["dni"].ToString();

                // Obtener cliente actual
                cliente cli = clienteWS.buscarPorDni(dni);

                if (cli == null)
                    return false;

                // Actualizar datos
                cli.nombre = txtNombre.Text.Trim();
                string[] partes = txtApellido.Text.Trim().Split(' ');
                cli.apellidoPaterno = partes[0];
                cli.apellidoMaterno = partes.Length > 1 ? partes[1] : "";
                cli.telefono = int.Parse(txtTelefono.Text.Trim());

                // Llamar WS
                clienteWS.actualizarCliente(cli);

                return true;
            }
            catch (System.Exception ex)
            {
                System.Diagnostics.Debug.WriteLine("Error: " + ex.Message);
                return false;
            }
        }

        private void MostrarError(string mensaje)
        {
            ScriptManager.RegisterStartupScript(this, GetType(), "alert",
                $"alert('{mensaje}');", true);
        }

        private void MostrarExito(string mensaje)
        {
            ScriptManager.RegisterStartupScript(this, GetType(), "alert",
                $"alert('{mensaje}');", true);
        }

        private void CargarEmpresas()
        {
            try
            {
                if (Session["dni"] == null)
                {
                    Response.Redirect("InicioSesion.aspx");
                    return;
                }

                string dni = Session["dni"].ToString();

                // Obtener cliente completo
                cliente cli = clienteWS.buscarPorDni(dni);

                if (cli == null)
                {
                    MostrarError("No se encontró el cliente.");
                    return;
                }

                // Obtener lista de empresas activas del cliente
                List<empresa> lista = empresaWS.listarEmpresasPorClienteActivas(cli.id).ToList();

                if (lista != null && lista.Count > 0)
                {
                    repEmpresas.DataSource = lista;
                    repEmpresas.DataBind();
                    mensajeNoEmpresas.Visible = false;
                }
                else
                {
                    repEmpresas.DataSource = null;
                    repEmpresas.DataBind();
                    mensajeNoEmpresas.Visible = true;
                }
            }
            catch (System.Exception ex)
            {
                MostrarError("Error al cargar las empresas: " + ex.Message);
            }
        }

        protected void btnRegistrarEmpresa_Click(object sender, EventArgs e)
        {
            try
            {
                // Validación adicional en el servidor
                if (string.IsNullOrWhiteSpace(txtRucEmp.Text) ||
                    string.IsNullOrWhiteSpace(txtRazonEmp.Text) ||
                    string.IsNullOrWhiteSpace(txtDirEmp.Text) ||
                    string.IsNullOrWhiteSpace(txtDeparta.Text) ||
                    string.IsNullOrWhiteSpace(txtProvincia.Text) ||
                    string.IsNullOrWhiteSpace(txtDistrito.Text) ||
                    string.IsNullOrWhiteSpace(txtTelEmp.Text) ||
                    string.IsNullOrWhiteSpace(txtEmailEmp.Text))
                {
                    MostrarError("Debe completar todos los campos obligatorios.");
                    return;
                }

                // Validar RUC
                if (txtRucEmp.Text.Trim().Length != 11)
                {
                    MostrarError("El RUC debe tener 11 dígitos.");
                    return;
                }

                // Crear nueva empresa
                empresa emp = new empresa();
                emp.RUC = txtRucEmp.Text.Trim();
                emp.razonSocial = txtRazonEmp.Text.Trim();
                emp.direccionFiscal = txtDirEmp.Text.Trim();
                emp.departamento = txtDeparta.Text.Trim();
                emp.provincia = txtProvincia.Text.Trim();
                emp.distrito = txtDistrito.Text.Trim();
                emp.telefono = txtTelEmp.Text.Trim();
                emp.email = txtEmailEmp.Text.Trim();
                emp.codigoPostal = txtCodPost.Text.Trim();
                emp.activo = true;

                // Asignar cliente actual como representante legal
                string dni = Session["dni"].ToString();
                emp.cliente = clienteWS.buscarPorDni(dni);

                // Insertar empresa en la base de datos
                empresaWS.insertarEmpresa(emp);

                // Limpiar campos
                LimpiarCamposEmpresa();

                // Recargar lista de empresas
                CargarEmpresas();

                // Cerrar modal de agregar y reabrir el principal
                ScriptManager.RegisterStartupScript(this, GetType(),
                    "empresaAgregada",
                    @"closeModalAgregarEmpresa(); 
              setTimeout(function() { 
                  openModalEmpresas('modalMisEmpresas'); 
                  alert('Empresa registrada correctamente.'); 
              }, 300);",
                    true);
            }
            catch (System.Exception ex)
            {
                MostrarError("Error al registrar empresa: " + ex.Message);

                // Mantener el modal de agregar abierto en caso de error
                ScriptManager.RegisterStartupScript(this, GetType(),
                    "reabrirModalAgregar",
                    "setTimeout(function() { openModalAgregarEmpresa(); }, 100);",
                    true);
            }
        }

        protected void btnMisEmpresas_Click(object sender, EventArgs e)
        {
            CargarEmpresas();

            // Abrir el modal desde el servidor
            ScriptManager.RegisterStartupScript(this, GetType(),
                "openEmpresasModal",
                "openModalEmpresas('modalMisEmpresas');",
                true);
        }

        // Método para limpiar los campos del formulario de empresa
        private void LimpiarCamposEmpresa()
        {
            txtRucEmp.Text = string.Empty;
            txtRazonEmp.Text = string.Empty;
            txtDirEmp.Text = string.Empty;
            txtDeparta.Text = string.Empty;
            txtProvincia.Text = string.Empty;
            txtDistrito.Text = string.Empty;
            txtCodPost.Text = string.Empty;
            txtTelEmp.Text = string.Empty;
            txtEmailEmp.Text = string.Empty;
        }
    }
}