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
                // Inicialización si es necesaria
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
                bool registroExitoso = RegistrarEmpresa(cuenta, cliente);

                if (registroExitoso)
                {
                    // Mostrar el modal de éxito
                    ScriptManager.RegisterStartupScript(this, GetType(), "showModal",
                        "showSuccessModal();", true);
                }
                else
                {
                    // Mostrar mensaje de error
                    ScriptManager.RegisterStartupScript(this, GetType(), "alert",
                        "alert('Hubo un error al registrar la empresa. Por favor, intente nuevamente.');", true);
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
            // Validar Paso 1
            if (string.IsNullOrWhiteSpace(txtRUC.Text))
            {
                MostrarError("El RUC es obligatorio.");
                return false;
            }

            if (txtRUC.Text.Trim().Length != 11)
            {
                MostrarError("El RUC debe tener 11 dígitos.");
                return false;
            }

            if (ddlTipoDocumento.SelectedIndex == 0)
            {
                MostrarError("Debe seleccionar un tipo de documento.");
                return false;
            }

            if (string.IsNullOrWhiteSpace(txtNumeroDocumento.Text))
            {
                MostrarError("El número de documento es obligatorio.");
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
            //cuenta.username = txtUserName.Text;
            cuenta.correo = txtCorreo.Text;
            cuenta.password = txtPassword.Text;

            //Settear los valores de Cliente
            cliente.activo = true;
            //cliente.fechaNacimiento = ;

            var fecha = cliente.fechaNacimiento;

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

        private bool RegistrarEmpresa(cuentaUsuario cuenta, cliente cliente)
        {
            try
            {
                // Por ahora, simular que se guardó en nuestra base de datos
                return true;
            }
            catch (System.Exception ex)
            {
                // Log del error
                System.Diagnostics.Debug.WriteLine($"Error en RegistrarEmpresa: {ex.Message}");
                return false;
            }
        }

        private void LimpiarFormulario()
        {
            txtRUC.Text = string.Empty;
            ddlTipoDocumento.SelectedIndex = 0;
            txtNumeroDocumento.Text = string.Empty;
            txtCorreo.Text = string.Empty;
            txtTelefono.Text = string.Empty;
            txtPassword.Text = string.Empty;
            txtNombre.Text = string.Empty;
            txtApellidoMaterno.Text = string.Empty;
            txtApellidoPaterno.Text = string.Empty;
            ddlGenero.SelectedIndex = 0;
            txtDireccion.Text = string.Empty;
            chkTerms1.Checked = false;
            chkTerms3.Checked = false;
        }
    }
}