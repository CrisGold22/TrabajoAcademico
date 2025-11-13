using SoftProgWeb.ServiceioProductoWS;
using SoftProgWeb.SoftProgWS;
using System;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class GestionarProductos01 : System.Web.UI.Page
    {
        private ServiceioProductoWS.ProductoWSClient servicioProducto;

        protected void Page_Load(object sender, EventArgs e)
        {
            servicioProducto = new ServiceioProductoWS.ProductoWSClient();
            if (!Page.IsPostBack)
            {
                CargarProductos();
            }
        }

        private void CargarProductos()
        {
            try
            {
                ServiceioProductoWS.producto[] productos = servicioProducto.listarProducto();

                gvProductos.DataSource = productos.OrderBy(p => p.nombre).ToList();
                gvProductos.DataBind();
            }
            catch (System.Exception ex)
            {
                System.Diagnostics.Debug.WriteLine("Error al cargar productos: " + ex.Message);
            }
        }

        protected void btnNuevo_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/GestionarProductos02RegistrarProducto.aspx");
        }

        protected void gvProductos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int idProducto = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "Modificar")
            {
                Response.Redirect(string.Format("~/GestionarProductos02Detalle.aspx?id={0}", idProducto));
            }
            else if (e.CommandName == "Eliminar")
            {
                try
                {
                    servicioProducto.eliminarProducto(idProducto);

                    CargarProductos();
                }
                catch (System.Exception ex)
                {
                    System.Diagnostics.Debug.WriteLine("Error al eliminar producto: " + ex.Message);
                }
            }
        }

        protected void gvProductos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvProductos.PageIndex = e.NewPageIndex;
            CargarProductos();
        }
    }
}