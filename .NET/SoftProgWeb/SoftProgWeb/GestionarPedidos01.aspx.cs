using SoftProgWeb.SoftProgWS;
using System;
using System.Collections.Generic;
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
            servicioOrdenCompra = new OrdenCompraWSClient("OrdenCompraWSPort1");

            if (!Page.IsPostBack)
            {
                CargarPedidos();
            }
        }

        protected string FormatearFecha(object objFecha)
        {
            if (objFecha == null) return "-";
            try
            {
                if (objFecha is DateTime) return ((DateTime)objFecha).ToString("dd/MM/yyyy");
                return objFecha.ToString();
            }
            catch { return "Error Fmt"; }
        }

        private void CargarPedidos()
        {
            try
            {
                ViewState["EsBusqueda"] = false;

                ordenCompra[] pedidos = servicioOrdenCompra.listarOrdenCompra();

                if (pedidos != null)
                    gvPedidos.DataSource = pedidos.OrderByDescending(p => p.id).ToList();
                else
                    gvPedidos.DataSource = new List<ordenCompra>();

                gvPedidos.DataBind();
            }
            catch (System.Exception ex)
            {
                System.Diagnostics.Debug.WriteLine("Error: " + ex.Message);
            }
        }

        protected void btnFiltrar_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(txtIdCliente.Text))
            {
                MostrarError("Debes ingresar el ID del Cliente.");
                return;
            }
            if (string.IsNullOrEmpty(txtFechaInicio.Text) || string.IsNullOrEmpty(txtFechaFin.Text))
            {
                MostrarError("⚠️ Debes seleccionar un rango de fechas completo.");
                return;
            }

            try
            {
                pnlMensaje.Visible = false;

                int idCliente = int.Parse(txtIdCliente.Text);

                string fecha1SOAP = txtFechaInicio.Text + "T00:00:00";
                string fecha2SOAP = txtFechaFin.Text + "T23:59:59";

                var lista = servicioOrdenCompra.consultarPedidoPorFechas(idCliente, fecha1SOAP, fecha2SOAP);

                if (lista != null && lista.Length > 0)
                {
                    gvPedidos.DataSource = lista.OrderByDescending(x => x.id).ToList();
                    gvPedidos.DataBind();
                }
                else
                {
                    gvPedidos.DataSource = null;
                    gvPedidos.DataBind();
                    MostrarError("ℹ️ No se encontraron pedidos para este cliente en esas fechas.");
                }
            }
            catch (System.Exception ex)
            {
                MostrarError("Error al filtrar: " + ex.Message);
                System.Diagnostics.Debug.WriteLine("Error: " + ex.Message);
            }
        }

        private void MostrarError(string mensaje)
        {
            pnlMensaje.Visible = true;
            litMensajeError.Text = mensaje;
        }

        protected void gvPedidos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvPedidos.PageIndex = e.NewPageIndex;

            if (ViewState["EsBusqueda"] != null && (bool)ViewState["EsBusqueda"])
            {
                btnFiltrar_Click(sender, e);
            }
            else
            {
                CargarPedidos();
            }
        }

        protected void lnkLimpiar_Click(object sender, EventArgs e)
        {
            txtIdCliente.Text = "";
            txtFechaInicio.Text = "";
            txtFechaFin.Text = "";
            CargarPedidos();
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




    }
}