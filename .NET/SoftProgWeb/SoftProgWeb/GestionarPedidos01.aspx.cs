using SoftProgWeb.SoftProgWS;
using System;
using System.Data.SqlClient;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class GestionarPedidos01 : System.Web.UI.Page
    {
        private OrdenCompraWSClient servicioOrdenCompra;

        protected void Page_Load(object sender, EventArgs e)
        {

            servicioOrdenCompra = new OrdenCompraWSClient();
            if (!Page.IsPostBack)
            {
                CargarPedidos();
            }
        }

        protected string FormatearFecha(object objFecha)
            {
                if (objFecha == null)
                {
                    return "-";
                }

                try
                {
                    DateTime fechaObj = (DateTime)objFecha;

                    return fechaObj.ToString();
                }
                catch
                {
                    return "Error Fmt";
                }
            }



    private void CargarPedidos()
        {
            try
            {
                var lista = servicioOrdenCompra.listarOrdenCompra();
                gvPedidos.DataSource = lista;
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
                Response.Redirect(string.Format("~/GestionarPedidos02DetallesPedido.aspx?id={0}", idPedido));
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