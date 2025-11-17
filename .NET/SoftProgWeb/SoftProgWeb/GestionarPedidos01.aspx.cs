using SoftProgWeb.ServicioOrdenCompraWS;
using SoftProgWeb.SoftProgWS;
using System;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class GestionarPedidos01 : System.Web.UI.Page
    {
        private ServicioOrdenCompraWS.OrdenCompraWSClient servicioOrdenCompra;

        protected void Page_Load(object sender, EventArgs e)
        {
            servicioOrdenCompra = new ServicioOrdenCompraWS.OrdenCompraWSClient();
            if (!Page.IsPostBack)
            {
                CargarPedidos();
            }
        }

       private void CargarPedidos()
        {
            try
            {
                ServicioOrdenCompraWS.ordenCompra[] pedidos = servicioOrdenCompra.listarOrdenCompra();
                gvPedidos.DataSource = pedidos.OrderByDescending(p => p.fechaCreacion).ToList();

                gvPedidos.DataBind();
            }
            catch (System.Exception ex)
            {
                System.Diagnostics.Debug.WriteLine("Error al cargar pedidos: " + ex.Message);
            }
        }

        protected void gvPedidos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int idPedido = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "VerDetalle")
            {
                Response.Redirect(string.Format("~/GestionarPedidos02Detalle.aspx?id={0}", idPedido));
            }
            else if (e.CommandName == "Eliminar")
            {
                try
                {

                    servicioOrdenCompra.eliminarOrdenCompra(idPedido);


                     CargarPedidos();

                }
                catch (System.Exception ex)
                {
                    System.Diagnostics.Debug.WriteLine("Error al eliminar pedido: " + ex.Message);
                }
            }
        }

        protected void gvPedidos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvPedidos.PageIndex = e.NewPageIndex;
            CargarPedidos();
        }
    }
}