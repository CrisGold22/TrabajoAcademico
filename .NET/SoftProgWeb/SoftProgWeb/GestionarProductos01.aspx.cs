using SoftProgWeb.ServiceioProductoWS;
using SoftProgWeb.SoftProgWS;
using System;
using System.Collections.Generic;
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
                    CargarProductosTodos();
                }
            }

            private void CargarProductosTodos()
            {
                try
                {
                    ServiceioProductoWS.producto[] productos = servicioProducto.listarProducto();
                    EnlazarGrilla(productos);
                }
                catch (System.Exception ex)
                {
                    System.Diagnostics.Debug.WriteLine("Error al cargar todos los productos: " + ex.Message);
                }
            }
        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            pnlMensaje.Visible = false;
            Page.Validate();
            if (!Page.IsValid)
            {
                return;
            }

            string criterio = ddlCriterio.SelectedValue;
            string termino = txtTerminoBusqueda.Text.Trim();

            if (string.IsNullOrEmpty(termino))
            {
                CargarProductosTodos();
                return;
            }

            List<ServiceioProductoWS.producto> listaResultados = new List<ServiceioProductoWS.producto>();
            try
            {
                switch (criterio)
                {
                    case "SKU":
                        ServiceioProductoWS.producto pSku = servicioProducto.obtenerPorSku(termino);
                        if (pSku != null && pSku.id > 0)
                        {
                            listaResultados.Add(pSku);
                        }
                        break;

                    case "ID":
                        int idProducto = int.Parse(termino);
                        ServiceioProductoWS.producto pId = servicioProducto.obtenerProducto(idProducto);
                        if (pId != null && pId.id > 0)
                        {
                            listaResultados.Add(pId);
                        }
                        break;
                }

                EnlazarGrilla(listaResultados);
            }
            catch (System.Exception ex)
            {
                EnlazarGrilla(new List<ServiceioProductoWS.producto>());
                MostrarError(ex.Message);
            }
        }
        private void MostrarError(string mensaje)
        {
            pnlMensaje.Visible = true;
            litMensajeError.Text = mensaje;
        }
        private void EnlazarGrilla(IEnumerable<ServiceioProductoWS.producto> productos)
        {
            gvProductos.DataSource = productos.OrderBy(p => p.SKU).ToList();
            gvProductos.DataBind();
        }

        protected void btnLimpiar_Click(object sender, EventArgs e)
            {
                txtTerminoBusqueda.Text = string.Empty;
                ddlCriterio.SelectedValue = "SKU";
                CargarProductosTodos();
            }

            protected void ddlCriterio_SelectedIndexChanged(object sender, EventArgs e)
            {
                if (Page.IsPostBack)
                {
                    Page.Validate();
                }
            }

            protected void gvProductos_PageIndexChanging(object sender, GridViewPageEventArgs e)
            {
                gvProductos.PageIndex = e.NewPageIndex;
                if (string.IsNullOrEmpty(txtTerminoBusqueda.Text))
                {
                    CargarProductosTodos();
                }
                else
                {
                    btnBuscar_Click(sender, e);
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
                Response.Redirect(string.Format("~/GestionarProductos02RegistrarProducto.aspx?id={0}", idProducto));
            }
            else if (e.CommandName == "Eliminar")
            {
                try
                {
                    servicioProducto.eliminarProducto(idProducto);
                    btnLimpiar_Click(sender, e);
                }
                catch (System.Exception ex)
                {
                    System.Diagnostics.Debug.WriteLine("Error al eliminar producto: " + ex.Message);
                }
            }
        }
    }
}