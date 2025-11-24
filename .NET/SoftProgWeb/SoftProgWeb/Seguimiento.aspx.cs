using System;
using System.Linq;
using SoftProgWeb.SoftProgWS;

namespace SoftProgWeb
{
    public partial class Seguimiento : System.Web.UI.Page
    {
        OrdenCompraWSClient ordenWS = new OrdenCompraWSClient("OrdenCompraWSPort1");
        DetalleEnvioWSClient detalleWS = new DetalleEnvioWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarSeguimiento();
            }
        }

        private int ObtenerIdClienteActual()
        {
            return 1;
        }

        private void CargarSeguimiento()
        {
            int idCliente = ObtenerIdClienteActual();
            var ordenes = ordenWS.consultarOrdenCompraPorIdCliente(idCliente);

            if (ordenes == null || ordenes.Length == 0)
            {
                Response.Write("<script>alert('No tienes pedidos.');</script>");
                return;
            }

            var orden = ordenes.OrderByDescending(o => o.fechaCreacion).First();

            var detalles = detalleWS.listarDetalleEnvio();
            var detalle = detalles.FirstOrDefault(d => d.ordenCompra.id == orden.id);

            if (detalle == null) return;

            lblCodigoOrden.Text = orden.id.ToString();
            lblFechaEntrega.Text = detalle.fechaEntrega.ToString();
            lblHorarioEntrega.Text = detalle.horarioEntrega.ToString();
            lblDireccion.Text = detalle.direccion;


            ActualizarTracking(orden.estado);
        }

        private void ActualizarTracking(estadoOrdenCompra estado)
        {
            step1.Attributes["class"] = "tracking-step";
            step2.Attributes["class"] = "tracking-step";
            step3.Attributes["class"] = "tracking-step";
            step4.Attributes["class"] = "tracking-step";

            switch (estado)
            {
                case estadoOrdenCompra.PAGADO:
                    step1.Attributes["class"] += " active";
                    break;
                case estadoOrdenCompra.EN_PREPARACION:
                    step1.Attributes["class"] += " active";
                    step2.Attributes["class"] += " active";
                    break;
                case estadoOrdenCompra.ENVIADO:
                    step1.Attributes["class"] += " active";
                    step2.Attributes["class"] += " active";
                    step3.Attributes["class"] += " active";
                    break;
                case estadoOrdenCompra.ENTREGADO:
                    step1.Attributes["class"] += " active";
                    step2.Attributes["class"] += " active";
                    step3.Attributes["class"] += " active";
                    step4.Attributes["class"] += " active";
                    break;
            }
        }
    }
}
