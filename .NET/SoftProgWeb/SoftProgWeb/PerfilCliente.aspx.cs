using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftProgWeb.SoftProgWS;
using Exception = System.Exception;

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
                CargarEmpresas();
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

                // 2. Mostrar datos en los TextBox
                txtNombre.Text = cli.nombre;
                txtApellido.Text = cli.apellidoPaterno + " " + cli.apellidoMaterno;
                txtTelefono.Text = cli.telefono.ToString();

                // 3. Obtener email desde su cuenta
                if (cli.cuenta != null)
                {
                    txtPassword.Attributes["value"] = "**********";

                    string correo = cli.cuenta.correo;

                    // AHORA usamos labels de servidor:
                    lblDisplayName.Text = cli.nombre + " " + cli.apellidoPaterno;
                    lblDisplayEmail.Text = correo;
                    lblEmailPrincipal.Text = correo;
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
                // 1. Validar sesión
                if (Session["dni"] == null)
                {
                    Response.Redirect("InicioSesion.aspx");
                    return;
                }

                string dni = Session["dni"].ToString();

                // 2. Obtener cliente
                cliente cli = clienteWS.buscarPorDni(dni);
                if (cli == null)
                {
                    MostrarError("No se encontró el cliente asociado a la sesión.");
                    repEmpresas.DataSource = null;
                    repEmpresas.DataBind();
                    lblNoEmpresas.Visible = true;   // o mensajeNoEmpresas si usas el <div runat="server">
                    return;
                }

                // 3. Obtener empresas activas del WS
                var resultado = empresaWS.listarEmpresasPorClienteActivas(cli.id);

                // Por seguridad, filtramos solo las que realmente estén activas
                List<empresa> lista = (resultado ?? Array.Empty<empresa>())
                                        .Where(e => e.activo)   // si tu proxy tiene la propiedad "activo"
                                        .ToList();

                // 4. Enlazar o mostrar mensaje de vacío
                if (lista.Any())
                {
                    repEmpresas.DataSource = lista;
                    repEmpresas.DataBind();
                    lblNoEmpresas.Visible = false;  // o mensajeNoEmpresas.Visible = false;
                }
                else
                {
                    repEmpresas.DataSource = null;
                    repEmpresas.DataBind();
                    lblNoEmpresas.Visible = true;   // o mensajeNoEmpresas.Visible = true;
                }
            }
            catch (Exception ex)
            {
                MostrarError("Error al cargar las empresas: " + ex.Message);
                repEmpresas.DataSource = null;
                repEmpresas.DataBind();
                lblNoEmpresas.Visible = true;       // dejamos algo visible para el usuario
            }
        }


        protected void btnEdit_Click(object sender, EventArgs e)
        {
            Response.Redirect("EditarPerfil.aspx");
        }

        protected void btnIrAgregarEmpresa_Click(object sender, EventArgs e)
        {
            Response.Redirect("RegistrarEmpresa.aspx");
        }

        protected void repEmpresas_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "EliminarEmpresa")
            {
                int idEmpresa;
                if (int.TryParse(e.CommandArgument.ToString(), out idEmpresa))
                {
                    try
                    {
                        // Llamar al SOAP para eliminar
                        empresaWS.eliminarEmpresa(idEmpresa);

                        MostrarExito("Empresa eliminada correctamente.");

                        // Volver a cargar la lista de empresas
                        CargarEmpresas();
                    }
                    catch (Exception ex)
                    {
                        MostrarError("Error al eliminar la empresa: " + ex.Message);
                    }
                }
                else
                {
                    MostrarError("No se pudo identificar la empresa a eliminar.");
                }
            }
        }
    }
}