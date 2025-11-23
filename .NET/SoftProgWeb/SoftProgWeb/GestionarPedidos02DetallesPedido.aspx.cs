using SoftProgWeb.SoftProgWS;
using System;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class GestionarPedidos02DetallesPedido : System.Web.UI.Page
    {
        private OrdenCompraWSClient servicioOrdenCompra;
        private ordenCompra pedidoActual;
        private cliente cliente;

        protected void Page_Load(object sender, EventArgs e)
        {
            servicioOrdenCompra = new OrdenCompraWSClient("OrdenCompraWSPort1");

            pnlMensaje.Visible = false;

            if (!Page.IsPostBack)
            {
                CargarComboEstados();

                if (Request.QueryString["id"] == null)
                {
                    Response.Redirect("~/GestionarPedidos01.aspx");
                    return;
                }

                int idPedido = Convert.ToInt32(Request.QueryString["id"]);
                ViewState["idPedido"] = idPedido;

                CargarDetallePedido(idPedido);
            }
        }

        private void CargarComboEstados()
        {
            ddlEstado.DataSource = Enum.GetNames(typeof(estadoOrdenCompra));
            ddlEstado.DataBind();
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

                if (pedido.cliente != null)
                {
                    lblCliente.Text = pedido.cliente.nombre + " " + pedido.cliente.apellidoPaterno;
                    lblDNI.Text = pedido.cliente.dni;
                }
                else
                {
                    lblCliente.Text = "No Identificado";
                    lblDNI.Text = "-";
                }

                lblTotal.Text = pedido.totalFinal.ToString("C2");

                if (!string.IsNullOrEmpty(pedido.fechaCreacion))
                {
                    DateTime fecha;
                    if (DateTime.TryParse(pedido.fechaCreacion, out fecha))
                    {
                        lblFecha.Text = fecha.ToString("dd/MM/yyyy");
                    }
                    else
                    {
                        lblFecha.Text = pedido.fechaCreacion;
                    }
                }
                else
                {
                    lblFecha.Text = "-";
                }
                lblTotal.Text = pedido.totalFinal.ToString("C2");
                lblSubtotal.Text = pedido.totalParcial.ToString("C2");
                lblDescuento.Text = pedido.descuentoTotal.ToString("C2");
                lblClienteID.Text = pedido.cliente.id.ToString();
                lblCliente.Text = pedido.cliente.nombre + " " + pedido.cliente.apellidoPaterno + " " + pedido.cliente.apellidoMaterno;
                lblEmpresa.Text = pedido.empresa.razonSocial;
                lblEmpresaID.Text = pedido.empresa.id.ToString();


                string estadoActual = pedido.estado.ToString();
                if (ddlEstado.Items.FindByValue(estadoActual) != null)
                {
                    ddlEstado.SelectedValue = estadoActual;
                }

                if (pedido.lineasOrden != null)
                {
                    var lineas = pedido.lineasOrden;

                    var lineasOrdenadas = lineas
                        .OrderBy(l => l.producto.id)   // ajusta si el campo se llama distinto
                        .ToList();

                    gvLineasPedido.DataSource = pedido.lineasOrden;
                    gvLineasPedido.DataBind();
                }
            }
            catch (System.Exception ex)
            {
                MostrarError("Error al cargar el detalle: " + ex.Message);
            }
        }

        protected void btnCambiarEstado_Click(object sender, EventArgs e)
        {
            int idPedido = (int)ViewState["idPedido"];

            try
            {
                ordenCompra pedido = servicioOrdenCompra.obtenerOrdenCompra(idPedido);

                pedido.lineasOrden = servicioOrdenCompra.listarLineasOrdenCompraPorIdOrdenCompra(idPedido);

                string nuevoEstadoStr = ddlEstado.SelectedValue;
                estadoOrdenCompra nuevoEstadoEnum = (estadoOrdenCompra)Enum.Parse(typeof(estadoOrdenCompra), nuevoEstadoStr);

                pedido.estado = nuevoEstadoEnum;
                pedido.estadoSpecified = true;

                pedido.estadoString = nuevoEstadoStr;

                servicioOrdenCompra.actualizarOrdenCompra(pedido);

                Response.Redirect(Request.RawUrl, false);
                Context.ApplicationInstance.CompleteRequest();
            }
            catch (System.Exception ex)
            {
                MostrarError("Error al cambiar estado: " + ex.Message);
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