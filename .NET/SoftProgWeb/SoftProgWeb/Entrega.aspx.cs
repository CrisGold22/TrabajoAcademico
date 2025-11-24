using System;
using System.Collections.Generic;
using System.Linq; // Necesario para Sum y ToList
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftProgWeb.SoftProgWS; // Tu referencia

namespace SoftProgWeb
{
    public partial class Entrega : System.Web.UI.Page
    {
        private EmpresaWSClient empresaWS = new EmpresaWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["IdCliente"] == null)
            {
                Response.Redirect("InicioSesion.aspx");
                return;
            }

            if (!IsPostBack)
            {
                CargarEstablecimientos();
            }
        }

        private void CargarEstablecimientos()
        {
            try
            {
                ddlEstablecimiento.Items.Clear();
                int idCliente = Convert.ToInt32(Session["IdCliente"]);

                var listaEmpresas = empresaWS.listarEmpresasPorClienteActivas(idCliente);

                if (listaEmpresas != null && listaEmpresas.Length > 0)
                {
                    foreach (var empresa in listaEmpresas)
                    {
                        ddlEstablecimiento.Items.Add(new ListItem(empresa.razonSocial, empresa.id.ToString()));
                    }
                }
                else
                {
                    ddlEstablecimiento.Items.Add(new ListItem("No hay establecimientos disponibles", "0"));
                }
            }
            catch (System.Exception ex)
            {
                ddlEstablecimiento.Items.Add(new ListItem("Error: " + ex.Message, "0"));
            }
        }

        protected void btnContinuar_Click(object sender, EventArgs e)
        {
            if (ddlEstablecimiento.SelectedValue == "0")
            {
                Response.Write("<script>alert('Seleccione un establecimiento válido');</script>");
                return;
            }

            try
            {
                int idCliente = (int)Session["IdCliente"];
                int idEmpresa = int.Parse(ddlEstablecimiento.SelectedValue);

                CarritoComprasWSClient carritoWS = new CarritoComprasWSClient();
                OrdenCompraWSClient ordenWS = new OrdenCompraWSClient("OrdenCompraWSPort");

                var carrito = carritoWS.obtenerCarritoDeCliente(idCliente);
                var lineasCarrito = carritoWS.listarLineaCarritoPorIdCarrito(carrito.id);

                if (lineasCarrito == null || lineasCarrito.Length == 0)
                {
                    Response.Write("<script>alert('Su carrito está vacío.'); window.location='MiCarrito.aspx';</script>");
                    return;
                }

                ordenCompra nuevaOrden = new ordenCompra();

                nuevaOrden.cliente = new cliente { id = idCliente };
                nuevaOrden.empresa = new empresa { id = idEmpresa };
                nuevaOrden.carritoCompras = new carritoCompras { id = carrito.id };

                nuevaOrden.estadoString = "EN_PREPARACION";
                nuevaOrden.fechaCreacion = DateTime.Now.ToString("yyyy-MM-ddTHH:mm:ss");
                nuevaOrden.activoInt = 1;

                double subTotalCalculado = lineasCarrito.Sum(x => x.subTotal);
                nuevaOrden.totalParcial = subTotalCalculado;
                nuevaOrden.totalFinal = subTotalCalculado;
                nuevaOrden.descuentoTotal = 0;

                List<lineaOrdenCompra> listaLineasOrden = new List<lineaOrdenCompra>();

                foreach (var itemCarrito in lineasCarrito)
                {
                    lineaOrdenCompra linea = new lineaOrdenCompra();
                    linea.producto = itemCarrito.producto;
                    linea.cantidad = itemCarrito.cantidad;
                    linea.precio = itemCarrito.precio;
                    linea.subTotal = itemCarrito.subTotal;
                    linea.activoInt = 1;

                    listaLineasOrden.Add(linea);
                }

                nuevaOrden.lineasOrden = listaLineasOrden.ToArray();

                ordenWS.insertarOrdenCompra(nuevaOrden);

                Session["idEmpresaEntrega"] = idEmpresa;
                Session["fechaEntrega"] = txtFechaEntrega.Text;

                Response.Redirect("Pago.aspx");
            }
            catch (System.Exception ex)
            {
                string mensajeError = ex.Message.Replace("'", "").Replace("\n", "");
                Response.Write($"<script>alert('Error al crear la orden: {mensajeError}');</script>");
            }
        }
    }
}