using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using MySql.Data.MySqlClient;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class Registro : System.Web.UI.Page
    {
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
                // Validar que todos los campos estén completos
                if (!ValidarDatos())
                {
                    return;
                }

                // lógica para guardar en la base de datos
                bool registroExitoso = RegistrarEmpresa();

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
            catch (Exception ex)
            {
                // Manejo de errores
                ScriptManager.RegisterStartupScript(this, GetType(), "alert",
                    $"alert('Error: {ex.Message}');", true);
            }
        }

        private bool ValidarDatos()
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

        private bool RegistrarEmpresa()
        {
            string connectionString = ConfigurationManager.ConnectionStrings["MySqlConnectionString"].ConnectionString;
            try
            {
                using (MySqlConnection conn = new MySqlConnection(connectionString))
                {
                    conn.Open();

                    // 1️⃣ Insertar el cliente
                    using (MySqlCommand cmd = new MySqlCommand("insertarCliente", conn))
                    {
                        cmd.CommandType = System.Data.CommandType.StoredProcedure;

                        cmd.Parameters.AddWithValue("p_idCliente", 0);
                        cmd.Parameters.AddWithValue("p_lineaCredito", 0);
                        cmd.Parameters.AddWithValue("p_Categoria", "Nuevo");
                        cmd.Parameters.AddWithValue("p_numeroDePedido_Historico", 0);
                        cmd.Parameters.AddWithValue("p_numeroDePedido_MensualPro", 0);
                        cmd.Parameters.AddWithValue("p_dni", txtNumeroDocumento.Text);
                        cmd.Parameters.AddWithValue("p_nombre", txtNombre.Text);
                        cmd.Parameters.AddWithValue("p_apellidoPaterno", txtApellidoPaterno.Text);
                        cmd.Parameters.AddWithValue("p_apellidoMaterno", txtApellidoMaterno.Text);
                        cmd.Parameters.AddWithValue("p_genero", ddlGenero.SelectedValue);
                        cmd.Parameters.AddWithValue("p_fechaNacimiento", DateTime.Now);
                        cmd.Parameters.AddWithValue("p_telefono", txtTelefono.Text);
                        cmd.Parameters.AddWithValue("p_Activo", 1);

                        MySqlParameter outputParam = new MySqlParameter("p_id", MySqlDbType.Int32);
                        outputParam.Direction = System.Data.ParameterDirection.Output;
                        cmd.Parameters.Add(outputParam);

                        cmd.ExecuteNonQuery();

                        int idCliente = Convert.ToInt32(outputParam.Value);

                        // 2️⃣ Insertar la cuenta del usuario
                        using (MySqlCommand cmdCuenta = new MySqlCommand("insertarCuentaUsuario", conn))
                        {
                            cmdCuenta.CommandType = System.Data.CommandType.StoredProcedure;
                            cmdCuenta.Parameters.AddWithValue("p_idCuentaUsuario", 0);
                            cmdCuenta.Parameters.AddWithValue("p_userName", txtCorreo.Text);
                            cmdCuenta.Parameters.AddWithValue("p_password", txtPassword.Text);
                            cmdCuenta.Parameters.AddWithValue("p_Administrador_idAdministrador", DBNull.Value);
                            cmdCuenta.Parameters.AddWithValue("p_cliente_idCliente", idCliente);

                            MySqlParameter outputCuenta = new MySqlParameter("p_id", MySqlDbType.Int32);
                            outputCuenta.Direction = System.Data.ParameterDirection.Output;
                            cmdCuenta.Parameters.Add(outputCuenta);

                            cmdCuenta.ExecuteNonQuery();
                        }
                    }

                    return true;
                }
            }
            catch (Exception ex)
            {
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