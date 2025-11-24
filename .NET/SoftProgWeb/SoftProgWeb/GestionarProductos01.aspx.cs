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
        private ProductoWSClient servicioProducto;

        protected void Page_Load(object sender, EventArgs e)
        {
            servicioProducto = new ProductoWSClient("ProductoWSPort1");

            if (!Page.IsPostBack)
            {
                CargarProductosTodos();
            }
        }



        private void CargarProductosTodos()
        {
            try
            {
                producto[] productos = servicioProducto.listarProducto();
                if (productos != null)
                {
                    EnlazarGrilla(productos);
                }
                else
                {
                    EnlazarGrilla(new List<producto>());
                }
            }
            catch (System.Exception ex)
            {
                System.Diagnostics.Debug.WriteLine("Error al cargar todos los productos: " + ex.Message);
                MostrarError("Error al cargar productos: " + ex.Message);
            }
        }

        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            pnlMensaje.Visible = false;
            Page.Validate();
            if (!Page.IsValid) return;

            string criterio = ddlCriterio.SelectedValue;
            string termino = txtTerminoBusqueda.Text.Trim();

            if (string.IsNullOrEmpty(termino))
            {
                CargarProductosTodos();
                return;
            }

            List<producto> listaResultados = new List<producto>();
            try
            {
                switch (criterio)
                {
                    case "SKU":
                        producto pSku = servicioProducto.obtenerPorSku(termino);
                        if (pSku != null && pSku.id > 0)
                        {
                            listaResultados.Add(pSku);
                        }
                        break;

                    case "ID":
                        int idProducto;
                        if (int.TryParse(termino, out idProducto))
                        {
                            producto pId = servicioProducto.obtenerProducto(idProducto);
                            if (pId != null && pId.id > 0)
                            {
                                listaResultados.Add(pId);
                            }
                        }
                        else
                        {
                            MostrarError("El ID debe ser un número válido.");
                            return;
                        }
                        break;
                }

                EnlazarGrilla(listaResultados);
            }
            catch (System.Exception ex)
            {
                EnlazarGrilla(new List<producto>());
                MostrarError(ex.Message);
            }
        }
        protected void btnGenerarReporte_Click(object sender, EventArgs e)
        {
            Response.Redirect("http://localhost:8080/SoftProgReportes/ReporteProductos?autor=miraya");
        }
        private void EnlazarGrilla(IEnumerable<producto> productos)
        {
            if (productos != null)
            {
                gvProductos.DataSource = productos.OrderBy(p => p.id).ToList();
                gvProductos.DataBind();
            }
        }
        private void MostrarError(string mensaje)
        {
            pnlMensaje.Visible = true;
            litMensajeError.Text = mensaje;
        }

        protected void btnLimpiar_Click(object sender, EventArgs e)
        {
            txtTerminoBusqueda.Text = string.Empty;
            if (ddlCriterio.Items.FindByValue("SKU") != null)
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
            if (string.IsNullOrEmpty(e.CommandArgument.ToString())) return;

            int idProducto = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "Modificar")
            {
                Response.Redirect(string.Format("~/GestionarProductos02RegistrarProducto.aspx?id={0}", idProducto));
            }
            else if (e.CommandName == "GestionarDescuentos")
            {
                Response.Redirect($"~/GestionarDescuentos.aspx?idProducto={idProducto}");
            }
            else if (e.CommandName == "Eliminar")
            {
                try
                {
                    servicioProducto.eliminarProducto(idProducto);
                    if (string.IsNullOrEmpty(txtTerminoBusqueda.Text))
                        CargarProductosTodos();
                    else
                        btnBuscar_Click(sender, e);
                }
                catch (System.Exception ex)
                {
                    System.Diagnostics.Debug.WriteLine("Error al eliminar producto: " + ex.Message);
                    MostrarError("Error al eliminar: " + ex.Message);
                }
            }
        }
    }
}