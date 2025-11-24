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
            string marcaDeTiempo = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss.fff");
            System.Diagnostics.Debug.WriteLine($"[BTN REGISTRAR CLICK] Ejecutado a las: {marcaDeTiempo}");

            try
            {
                cuentaUsuario cuenta = new cuentaUsuario();
                cliente cliente = new cliente();

                if (!ValidarDatos(cuenta, cliente))
                {
                    System.Diagnostics.Debug.WriteLine($"[VALIDACIÓN FALLÓ] {marcaDeTiempo}");
                    return;
                }

                bool registroExitoso = RegistrarUser(cuenta, cliente);

                System.Diagnostics.Debug.WriteLine($"[RegistrarUser RESULTADO={registroExitoso}] {DateTime.Now:HH:mm:ss.fff}");

                if (registroExitoso)
                {
                    ScriptManager.RegisterStartupScript(this, GetType(), "showModal",
                        "showSuccessModal();", true);
                    LimpiarFormulario();
                }
                else
                {
                    ScriptManager.RegisterStartupScript(this, GetType(), "alert",
                        "alert('Hubo un error al registrar.');", true);
                }
            }
            catch (SystemException ex)
            {
                System.Diagnostics.Debug.WriteLine($"[ERROR btnRegistrar_Click] {DateTime.Now:HH:mm:ss.fff} — {ex}");
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
            cuenta.activoInt = 1;

            //Settear los valores de Cliente
            cliente.dni = txtNumeroDocumento.Text.Trim();
            cliente.nombre = txtNombre.Text.Trim();
            cliente.apellidoPaterno = txtApellidoPaterno.Text.Trim();
            cliente.apellidoMaterno = txtApellidoMaterno.Text.Trim();
            cliente.categoria = categoriaCliente.REVENDEDOR;
            cliente.numeroPedidosHistorico = 0;
            cliente.numeroPedidosMensualPro = 0;
            cliente.lineaCredito = 0;
  

            try { cliente.categoriaSpecified = true; } catch { }

            int telefonoInt = 0;
            if (int.TryParse(System.Text.RegularExpressions.Regex.Replace(txtTelefono.Text, @"\D", ""), out telefonoInt))
            {
                try { cliente.telefono = telefonoInt; } catch { }
            }
            else
            {
                try { cliente.telefono = 0; } catch { }
            }

            // Valor del combo: "M", "F", "O" (u otro)
            string gen = ddlGenero.SelectedValue;

            // Limpieza inicial
            cliente.generoSpecified = false;

            // Si no eligió nada, simplemente no mandamos género
            if (string.IsNullOrWhiteSpace(gen))
            {
                // cliente.generoSpecified sigue en false
            }
            else
            {
                // Mapeamos a los nombres EXACTOS del enum generado por el servicio
                // (ajusta NO_ESPECIFICADO/OTRO según cómo se llame en tu enum C#)
                genero generoEnum;

                switch (gen)
                {
                    case "M":
                        generoEnum = genero.MASCULINO;
                        break;

                    case "F":
                        generoEnum = genero.FEMENINO;
                        break;

                    case "O":
                        generoEnum = genero.NO_ESPECIFICADO;
                        break;

                    default:
                        generoEnum = genero.NO_ESPECIFICADO;
                        break;
                }

                // Asignamos y marcamos como "presente" en el XML del SOAP
                cliente.genero = generoEnum;
                cliente.generoSpecified = true;
            }

            DateTime fechaNacimiento;
            if (!string.IsNullOrWhiteSpace(txtFechaNacimiento.Text) && DateTime.TryParse(txtFechaNacimiento.Text, out fechaNacimiento))
            {
                cliente.fechaNacimiento = fechaNacimiento.ToString("yyyy-MM-ddTHH:mm:ss");
            }

            cliente.activo = true;
            //cliente.cuenta = cuenta;

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
            string marca = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss.fff");
            System.Diagnostics.Debug.WriteLine($"[RegistrarUser INICIO] {marca} — DNI={cliente.dni}");

            try
            {
                cuentaUsuarioWS.insertarCuentaUsuario(cuenta);
                System.Diagnostics.Debug.WriteLine($"[Cuenta insertada] {DateTime.Now:HH:mm:ss.fff}");

                cuentaUsuario cuentaConId = cuentaUsuarioWS.obtenerCuentaUsuarioPorUserName(cuenta.username);
                System.Diagnostics.Debug.WriteLine($"[Cuenta obtenida ID={cuentaConId.id}] {DateTime.Now:HH:mm:ss.fff}");

                cliente.cuenta = cuentaConId;
                clienteWS.insertarCliente(cliente); // ← IMPORTANTE
                System.Diagnostics.Debug.WriteLine($"[CLIENTE INSERTADO] {DateTime.Now:HH:mm:ss.fff} — DNI={cliente.dni}");

                cliente clienteConId = clienteWS.buscarPorDni(cliente.dni);
                System.Diagnostics.Debug.WriteLine($"[CLIENTE BUSCADO] {DateTime.Now:HH:mm:ss.fff} — ID={clienteConId.id}");

                carritoCompras carrito = new carritoCompras();
                carrito.cliente = clienteConId;
                carrito.activo = true;
                carrito.activoInt = 1;

                new CarritoComprasWSClient().insertarCarritoCompras(carrito);
                System.Diagnostics.Debug.WriteLine($"[CARRITO INSERTADO] {DateTime.Now:HH:mm:ss.fff}");



                //string mensaje = GenerarMensaje(cliente, cuenta);

                //NotificacionWSClient notificacion = new NotificacionWSClient();




                return true;
            }
            catch (SystemException ex)
            {
                System.Diagnostics.Debug.WriteLine($"[ERROR RegistrarUser] {DateTime.Now:HH:mm:ss.fff} — {ex}");
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