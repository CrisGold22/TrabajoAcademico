using System;
using System.Web.UI;
using SoftProgWeb.SoftProgWS;

namespace SoftProgWeb
{
    public partial class RegistrarEmpresa : System.Web.UI.Page
    {
        private ClienteWSClient clienteWS = new ClienteWSClient();
        private EmpresaWSClient empresaWS = new EmpresaWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Verificar sesión
                if (Session["dni"] == null)
                {
                    Response.Redirect("InicioSesion.aspx");
                    return;
                }
            }
        }

        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
            // Validación de ASP.NET (validators)
            if (!Page.IsValid) return;

            try
            {
                if (Session["dni"] == null)
                {
                    MostrarError("Sesión expirada. Inicie sesión nuevamente.");
                    return;
                }

                string dni = Session["dni"].ToString();

                // Buscar cliente asociado
                cliente cli = clienteWS.buscarPorDni(dni);
                if (cli == null)
                {
                    MostrarError("No se encontró el cliente asociado a esta sesión.");
                    return;
                }

                // Crear objeto empresa
                empresa emp = new empresa
                {
                    ruc = txtRUC.Text.Trim(),
                    razonSocial = txtRazonSocial.Text.Trim(),
                    direccionFiscal = txtDireccionFiscal.Text.Trim(),
                    departamento = txtDepartamento.Text.Trim(),
                    provincia = txtProvincia.Text.Trim(),
                    distrito = txtDistrito.Text.Trim(),
                    telefono = txtTelefono.Text.Trim(),
                    email = txtEmail.Text.Trim(),
                    codigoPostal = txtCodigoPostal.Text.Trim(),
                    activo = true,
                    cliente = cli,
                    activoInt = 1
                };

                // Llamar al WS para insertar
                empresaWS.insertarEmpresa(emp);

                // Opcional: mostrar mensaje y redirigir al perfil
                // MostrarExito("Empresa registrada correctamente.");
                Response.Redirect("PerfilCliente.aspx?emp=ok", false);
            }
            catch (System.ServiceModel.CommunicationException commEx)
            {
                MostrarError("Error de comunicación con el servidor: " + commEx.Message);
            }
            catch (System.Exception ex)
            {
                MostrarError("Error al registrar la empresa: " + ex.Message);
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
            litMensaje.Text = mensaje;
        }

        private void MostrarExito(string mensaje)
        {
            pnlMensaje.Visible = true;
            pnlMensaje.CssClass = "alert alert-success";
            litMensaje.Text = mensaje;
        }
    }
}
