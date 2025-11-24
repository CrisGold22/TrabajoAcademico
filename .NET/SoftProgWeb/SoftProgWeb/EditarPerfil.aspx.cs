using System;
using System.Linq;
using System.Web.UI;
using SoftProgWeb.SoftProgWS;
using Exception = System.Exception;

namespace SoftProgWeb
{
    public partial class EditarPerfil : System.Web.UI.Page
    {
        private ClienteWSClient clienteWS = new ClienteWSClient();
        private CuentaUsuarioWSClient cuentaUsuarioWS = new CuentaUsuarioWSClient();

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

                // Obtener cliente por DNI
                cliente cli = clienteWS.buscarPorDni(dni);

                if (cli == null)
                {
                    MostrarError("No se encontró el cliente.");
                    return;
                }

                txtNombre.Text = cli.nombre;
                txtApellido.Text = (cli.apellidoPaterno + " " + cli.apellidoMaterno).Trim();
                txtTelefono.Text = cli.telefono.ToString();

                if (cli.cuenta != null)
                {
                    txtEmail.Text = cli.cuenta.correo;
                }
            }
            catch (Exception ex)
            {
                MostrarError("Error al cargar los datos del perfil: " + ex.Message);
            }
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {
                if (Session["dni"] == null)
                {
                    Response.Redirect("InicioSesion.aspx");
                    return;
                }

                string dni = Session["dni"].ToString();
                cliente cli = clienteWS.buscarPorDni(dni);
                cuentaUsuario cuenta = cuentaUsuarioWS.obtenerCuentaUsuario(cli.cuenta.id);

                if (cli == null)
                {
                    MostrarError("No se encontró el cliente.");
                    return;
                }

                // Validaciones simples
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

                if (!int.TryParse(txtTelefono.Text.Trim(), out int telefono))
                {
                    MostrarError("El teléfono debe contener solo números.");
                    return;
                }

                if (string.IsNullOrWhiteSpace(txtEmail.Text))
                {
                    MostrarError("El correo es obligatorio.");
                    return;
                }

                // Actualizar datos del cliente
                cli.nombre = txtNombre.Text.Trim();

                string[] partesApellido = txtApellido.Text.Trim()
                    .Split(new[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);

                cli.apellidoPaterno = partesApellido.Length > 0 ? partesApellido[0] : "";
                cli.apellidoMaterno = partesApellido.Length > 1
                    ? string.Join(" ", partesApellido.Skip(1))
                    : "";

                cli.telefono = telefono;
                cuenta.correo = txtEmail.Text.Trim();
                cli.cuenta = cuenta;

                // Llamar al WS para actualizar
                cuentaUsuarioWS.actualizarCuentaUsuario(cuenta);
                clienteWS.actualizarCliente(cli);

                // Volver al perfil
                Response.Redirect("PerfilCliente.aspx");
            }
            catch (Exception ex)
            {
                MostrarError("Error al guardar los cambios: " + ex.Message);
            }
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("PerfilCliente.aspx");
        }

        private void MostrarError(string mensaje)
        {
            pnlMensaje.Visible = true;
            pnlMensaje.CssClass = "alert alert-danger";
            litMensaje.Text = mensaje.Replace("'", "’");
        }

        private void MostrarExito(string mensaje)
        {
            pnlMensaje.Visible = true;
            pnlMensaje.CssClass = "alert alert-success";
            litMensaje.Text = mensaje.Replace("'", "’");
        }
    }
}