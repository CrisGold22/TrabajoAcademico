using SoftProgWeb.SoftProgWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class Registro : System.Web.UI.Page
    {

        private CuentaUsuarioWSClient cuentaUsuarioWS = new CuentaUsuarioWSClient();
        private ClienteWSClient clienteWS = new ClienteWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
            }
        }

        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
            try
            {
                cuentaUsuario cuenta = new cuentaUsuario();
                cliente cliente = new cliente();

                // Validar que todos los campos estén completos
                if (!ValidarDatos(cuenta, cliente))
                {
                    return;
                }

                // lógica para guardar en la base de datos
                bool registroExitoso = RegistrarUser(cuenta, cliente);

                if (registroExitoso)
                {
                    // Mostrar el modal de éxito
                    ScriptManager.RegisterStartupScript(this, GetType(), "showModal",
                        "showSuccessModal();", true);
                    LimpiarFormulario();
                }
                else
                {
                    // Mostrar mensaje de error
                    ScriptManager.RegisterStartupScript(this, GetType(), "alert",
                        "alert('Hubo un error al registrar el usuario. Por favor, intente nuevamente.');", true);
                }
            }
            catch (System.Exception ex)
            {
                // Manejo de errores
                ScriptManager.RegisterStartupScript(this, GetType(), "alert",
                    $"alert('Error: {ex.Message}');", true);
            }
        }

        private bool ValidarDatos(cuentaUsuario cuenta, cliente cliente)
        {

            if (string.IsNullOrWhiteSpace(txtNumeroDocumento.Text))
            {
                MostrarError("El número de DNI es obligatorio.");
                return false;
            }

            if (string.IsNullOrWhiteSpace(txtUsuario.Text))
            {
                MostrarError("El usuario es obligatorio.");
                return false;
            }

            if (string.IsNullOrWhiteSpace(txtCorreo.Text))
            {
                MostrarError("El correo electrónico es obligatorio.");
                return false;
            }

            if (!EsCorreoValido(txtCorreo.Text))
            {
                MostrarError("El formato del correo electrónico no es válido.");
                return false;
            }

            if (string.IsNullOrWhiteSpace(txtTelefono.Text))
            {
                MostrarError("El número de teléfono es obligatorio.");
                return false;
            }

            if (string.IsNullOrWhiteSpace(txtPassword.Text))
            {
                MostrarError("La contraseña es obligatoria.");
                return false;
            }

            if (txtPassword.Text.Length < 6)
            {
                MostrarError("La contraseña debe tener al menos 6 caracteres.");
                return false;
            }

            // Validar Paso 3
            if (string.IsNullOrWhiteSpace(txtNombre.Text))
            {
                MostrarError("El nombre es obligatorio.");
                return false;
            }

            if (string.IsNullOrWhiteSpace(txtApellidoMaterno.Text))
            {
                MostrarError("El apellido materno es obligatorio.");
                return false;
            }

            if (string.IsNullOrWhiteSpace(txtApellidoPaterno.Text))
            {
                MostrarError("El apellido paterno es obligatorio.");
                return false;
            }

            if (ddlGenero.SelectedIndex == 0)
            {
                MostrarError("Debe seleccionar un género.");
                return false;
            }

            if (string.IsNullOrWhiteSpace(txtDireccion.Text))
            {
                MostrarError("La dirección es obligatoria.");
                return false;
            }

            //Settear los valores de cuenta
            cuenta.username = txtUsuario.Text.Trim();
            cuenta.correo = txtCorreo.Text.Trim();
            cuenta.password = txtPassword.Text.Trim();
            cuenta.activo = true;

            //Settear los valores de Cliente
            cliente.dni = txtNumeroDocumento.Text.Trim();
            cliente.nombre = txtNombre.Text.Trim();
            cliente.apellidoPaterno = txtApellidoPaterno.Text.Trim();
            cliente.apellidoMaterno = txtApellidoMaterno.Text.Trim();
            cliente.categoria = categoriaCliente.REVENDEDOR;
            var prop = cliente.GetType().GetProperty("categoriaCliente");
            if (prop != null)
            {
                prop.SetValue(cliente, categoriaCliente.REVENDEDOR);
            }
            int telefonoInt = 0;
            if (int.TryParse(System.Text.RegularExpressions.Regex.Replace(txtTelefono.Text, @"\D", ""), out telefonoInt))
            {
                try { cliente.telefono = telefonoInt; } catch { }
            }
            else
            {
                try { cliente.telefono = 0; } catch { }
            }

            string gen = ddlGenero.SelectedValue; // "M", "F", "O"
            string generoParaWs = "NO_ESPECIFICADO";
            if (gen == "M") generoParaWs = "MASCULINO";
            else if (gen == "F") generoParaWs = "FEMENINO";
            else generoParaWs = "NO_ESPECIFICADO";
            try
            { cliente.genero = (genero)Enum.Parse(typeof(genero), generoParaWs); }
            catch { }

            DateTime fechaNacimiento;
            if (!string.IsNullOrWhiteSpace(txtFechaNacimiento.Text) && DateTime.TryParse(txtFechaNacimiento.Text, out fechaNacimiento))
            {
                cliente.fechaNacimiento = fechaNacimiento.ToString("yyyy-MM-ddTHH:mm:ss");
            }

            cliente.activo = true;
            cliente.cuenta = cuenta;

            return true;
        }

        private bool EsCorreoValido(string correo)
        {
            try
            {
                var addr = new System.Net.Mail.MailAddress(correo);
                return addr.Address == correo;
            }
            catch
            {
                return false;
            }
        }

        private void MostrarError(string mensaje)
        {
            ScriptManager.RegisterStartupScript(this, GetType(), "alert",
                $"alert('{mensaje}');", true);
        }

        private bool RegistrarUser(cuentaUsuario cuenta, cliente cliente)
        {
            try
            {
                clienteWS.insertarCliente(cliente);
                cuentaUsuarioWS.insertarCuentaUsuario(cuenta);
                return true;
            }
            catch (System.Exception ex)
            {
                // Log del error
                System.Diagnostics.Debug.WriteLine($"Error en Registrar Usuario: {ex.Message}");
                ScriptManager.RegisterStartupScript(this, GetType(), "alert",
                    $"alert('Error al comunicarse con el servicio: {ex.Message}');", true);
                return false;
            }
        }

        private void LimpiarFormulario()
        {
            txtNumeroDocumento.Text = string.Empty;
            txtUsuario.Text = string.Empty;
            txtCorreo.Text = string.Empty;
            txtTelefono.Text = string.Empty;
            txtPassword.Text = string.Empty;
            txtNombre.Text = string.Empty;
            txtApellidoMaterno.Text = string.Empty;
            txtApellidoPaterno.Text = string.Empty;
            ddlGenero.SelectedIndex = 0;
            txtDireccion.Text = string.Empty;
            txtFechaNacimiento.Text = string.Empty;
            chkTerms1.Checked = false;
            chkTerms3.Checked = false;
        }
    }
}