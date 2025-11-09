using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class PerfilCliente : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                //conectar con Back
                //// Verificar si hay sesión activa
                //if (Session["Usuario"] == null)
                //{
                //    Response.Redirect("Home.aspx"); //redireccion si no hay usuario
                //    return;
                //}

                // Cargar datos del perfil
                CargarDatosPerfil();
            }
        }

        private void CargarDatosPerfil()
        {
            try
            {
                // Datos de ejemplo 
                txtNombre.Text = "Juan";
                txtApellido.Text = "Perez Martinez";
                txtDireccion.Text = "Mz L 20 Av argentina";
                txtRuc.Text = "2020320030023";
                txtTelefono.Text = "999 999 999";
                txtPassword.Attributes["value"] = "**********";

                // Registrar script para actualizar el header
                string script = @"
                    document.getElementById('displayName').textContent = '" + txtNombre.Text + " " + txtApellido.Text + @"';
                    document.getElementById('displayEmail').textContent = 'JuanPerez@gmail.com';
                    document.getElementById('emailPrincipal').textContent = 'JuanPerez@gmail.com';
                ";
                ScriptManager.RegisterStartupScript(this, GetType(), "updateHeader", script, true);
            }
            catch (Exception ex)
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
            catch (Exception ex)
            {
                MostrarError("Error al cambiar la contraseña: " + ex.Message);
            }
        }

        private bool CambiarPassword(string currentPassword, string newPassword)
        {
            try
            {
                return true;
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine($"Error en CambiarPassword: {ex.Message}");
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

                if (string.IsNullOrWhiteSpace(txtDireccion.Text))
                {
                    MostrarError("La dirección es obligatoria.");
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
            catch (Exception ex)
            {
                MostrarError("Error al guardar los cambios: " + ex.Message);
            }
        }

        private bool GuardarCambiosPerfil()
        {
            try
            {
                return true;
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine($"Error en GuardarCambiosPerfil: {ex.Message}");
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

        // Método para eliminar email
        protected void btnRemoveEmail_Click(object sender, EventArgs e)
        {
            try
            {
                // Lógica para eliminar email secundario
                // No se debe permitir eliminar el email principal

                MostrarExito("Email eliminado correctamente.");
            }
            catch (Exception ex)
            {
                MostrarError("Error al eliminar el email: " + ex.Message);
            }
        }
    }
}