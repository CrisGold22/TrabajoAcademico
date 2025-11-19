using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
//using SoftProgWeb.ServicioOrdenCompraWS;
using SoftProgWeb.SoftProgWS;

namespace SoftProgWeb
{
    public partial class Seguimiento : System.Web.UI.Page
    {
        OrdenCompraWSClient ordenWS = new OrdenCompraWSClient();
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
            return 1; // ID CLIENTE HARDCODE PARA PRUEBAS
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

            if (detalle == null)
            {
                // No hay detalle de envío
                return;
            }

            // MOSTRAR DATOS
            
            lblCodigoOrden.Text = orden.id.ToString();

            var fecha = detalle.fechaEntrega;
            DateTime fechaEntregaConvertida = DateTime.Parse(fecha.ToString());
            lblFechaEntrega.Text = fechaEntregaConvertida.ToString("dd/MM/yyyy");

            var hora = detalle.horarioEntrega;
            DateTime horaEntregaConvertida = DateTime.Parse(fecha.ToString());
            lblHorarioEntrega.Text = horaEntregaConvertida.ToString("HH:mm");
            lblDireccion.Text = detalle.direccion;

            // TRACKING SEGÚN ESTADO
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