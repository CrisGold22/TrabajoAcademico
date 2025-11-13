using System;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftProgWeb.ServicioOrdenCompraWS;

namespace SoftProgWeb
{
    public partial class GestionarPedidos02DetallesPedido : System.Web.UI.Page
    {
        private OrdenCompraWSClient servicioOrdenCompra;

        protected void Page_Load(object sender, EventArgs e)
        {
            servicioOrdenCompra = new OrdenCompraWSClient();

            pnlMensaje.Visible = false;

            if (!Page.IsPostBack)
            {
                if (Request.QueryString["id"] == null)
                {
                    Response.Redirect("~/GestionarPedidos01.aspx");
                    return;
                }

                int idPedido = Convert.ToInt32(Request.QueryString["id"]);
                CargarDetallePedido(idPedido);
            }
        }

        private void CargarDetallePedido(int idPedido)
        {
            try
            {
                ordenCompra pedido = servicioOrdenCompra.obtenerOrdenCompra(idPedido);

                if (pedido == null)
                {
                    MostrarError("No se encontró el pedido con ID " + idPedido);
                    return;
                }

                lblPedidoID.Text = pedido.id.ToString();

                lblCliente.Text = pedido.cliente.nombre + " " + pedido.cliente.apellidoPaterno;
                lblDNI.Text = pedido.cliente.dni;

                lblFecha.Text = pedido.fechaCreacion.ToString("dd/MM/yyyy");
                lblTotal.Text = pedido.totalFinal.ToString("C2");

                gvLineasPedido.DataSource = pedido.lineasOrden;
                gvLineasPedido.DataBind();
            }
            catch (Exception ex)
            {
                MostrarError("Error al cargar el detalle del pedido: " + ex.Message);
            }
        }
        protected void btnVolver_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/GestionarPedidos01.aspx");
        }

        private void MostrarError(string mensaje)
        {
            pnlMensaje.Visible = true;
            litMensajeError.Text = mensaje;
        }
    }
}