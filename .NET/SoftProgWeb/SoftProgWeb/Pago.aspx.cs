using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.Services;
using SoftProgWeb.SoftProgWS;

namespace SoftProgWeb
{
    public partial class Pago : System.Web.UI.Page
    {
        private OrdenCompraWSClient ordenWS;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["IdCliente"] == null)
            {
                Response.Redirect("InicioSesion.aspx");
                return;
            }

            if (!IsPostBack)
            {
                CargarInformacionPedido();
            }
        }

        private void CargarInformacionPedido()
        {
            try
            {
                ordenWS = new OrdenCompraWSClient("OrdenCompraWSPort1");
                int idCliente = (int)Session["IdCliente"];

                var ordenes = ordenWS.consultarOrdenCompraPorIdCliente(idCliente);

                if (ordenes == null || ordenes.Length == 0)
                {
                    Response.Redirect("MiCarrito.aspx");
                    return;
                }

                var ordenPendiente = ordenes
                    .Where(o => o.activo == true && o.estado != estadoOrdenCompra.PAGADO && o.estado != estadoOrdenCompra.CANCELADO)
                    .OrderByDescending(o => o.id)
                    .FirstOrDefault();

                if (ordenPendiente != null)
                {
                    lblTotalPagar.Text = "S/ " + ordenPendiente.totalFinal.ToString("N2");
                }
                else
                {
                    Response.Redirect("MiCarrito.aspx");
                }
            }
            catch (System.Exception ex)
            {
                lblTotalPagar.Text = "Error: " + ex.Message;
            }
        }

        [WebMethod]
        public static string ProcesarPagoCripto(string metodoPago)
        {
            ComprobantePagoWSClient comprobanteWS = new ComprobantePagoWSClient();
            OrdenCompraWSClient ordenWS = new OrdenCompraWSClient("OrdenCompraWSPort1");
            EmpresaWSClient empresaWS = new EmpresaWSClient();
            ClienteWSClient clienteWS = new ClienteWSClient();

            comprobanteWS.Endpoint.Binding.SendTimeout = TimeSpan.FromMinutes(2);

            try
            {
                if (HttpContext.Current.Session["IdCliente"] == null) return "ERROR: Sesión expirada.";
                int idCliente = (int)HttpContext.Current.Session["IdCliente"];

                cliente clienteLogueado = clienteWS.obtenerCliente(idCliente);
                if (clienteLogueado == null) return "ERROR: Cliente no encontrado.";

                var ordenes = ordenWS.consultarOrdenCompraPorIdCliente(idCliente);

                if (ordenes == null)
                {
                    return "ERROR: No se encontraron órdenes registradas para este cliente.";
                }

                var ordenReal = ordenes
                    .Where(o => o.activo == true && o.estado != estadoOrdenCompra.PAGADO && o.estado != estadoOrdenCompra.CANCELADO)
                    .OrderByDescending(o => o.id)
                    .FirstOrDefault();

                if (ordenReal == null) return "ERROR: No hay ninguna orden pendiente de pago.";

                string rucFacturacion = clienteLogueado.dni;
                var empresas = empresaWS.listarEmpresasPorClienteActivas(idCliente);

                if (empresas != null && empresas.Length > 0)
                {
                    rucFacturacion = empresas[0].ruc;
                }

                comprobantePago nuevoComprobante = new comprobantePago();
                nuevoComprobante.fechaEmision = DateTime.Now;
                nuevoComprobante.ruc = rucFacturacion;
                nuevoComprobante.metodoString = "CRIPTO";
                nuevoComprobante.activoInt = 1;

                nuevoComprobante.totalFinal = ordenReal.totalFinal;
                nuevoComprobante.totalSinImpuestos = Math.Round(ordenReal.totalFinal / 1.18, 2);
                nuevoComprobante.impuestos = Math.Round(ordenReal.totalFinal - nuevoComprobante.totalSinImpuestos, 2);

                var ordenParaVincular = new ordenCompra1();
                ordenParaVincular.id = ordenReal.id;
                nuevoComprobante.ordenCompra = ordenParaVincular;

                List<lineaComprobantePago> lineasParaComprobante = new List<lineaComprobantePago>();

                var lineasOrden = ordenWS.listarLineasOrdenCompraPorIdOrdenCompra(ordenReal.id);

                if (lineasOrden != null)
                {
                    foreach (var itemOrden in lineasOrden)
                    {
                        lineaComprobantePago lineaCP = new lineaComprobantePago();
                        if (itemOrden.producto != null)
                        {
                            lineaCP.codigo = itemOrden.producto.id;
                        }
                        else
                        {
                            lineaCP.codigo = 0;
                        }

                        lineaCP.cantidad = itemOrden.cantidad;
                        lineaCP.subTotal = itemOrden.subTotal;
                        lineaCP.montoPagado = itemOrden.subTotal;
                        lineaCP.montoImpuesto = Math.Round(itemOrden.subTotal - (itemOrden.subTotal / 1.18), 2);
                        lineaCP.activoInt = 1;

                        lineasParaComprobante.Add(lineaCP);
                    }
                }
                nuevoComprobante.lineasComprobantes = lineasParaComprobante.ToArray();

                int idGenerado = comprobanteWS.insertarComprobantePago(nuevoComprobante);

                if (idGenerado > 0) return "OK";
                else return "ERROR: Tiempo de espera agotado o fallo en validación.";
            }
            catch (System.Exception ex)
            {
                return "ERROR TÉCNICO: " + ex.Message;
            }
        }
    }
}