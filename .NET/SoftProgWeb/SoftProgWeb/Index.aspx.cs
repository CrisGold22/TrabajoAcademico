using SoftProgWeb.SoftProgWS;
using System;
using System.Web.UI;

namespace SoftProgWeb
{
    public partial class Index : System.Web.UI.Page
    {
        private CuentaUsuarioWSClient cuentaWS = new CuentaUsuarioWSClient();
        private CarritoComprasWSClient carritoWS = new CarritoComprasWSClient();
        private ProductoWSClient productoWS = new ProductoWSClient();
        LineaCarritoWSClient lineaWS = new LineaCarritoWSClient();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarCarritoMini();
            }
        }

        private void CargarCarritoMini()
        {
            try
            {
                if (Session["idCliente"] == null)
                {
                    lblMiniCarrito.Text = "Inicia sesión para ver tu carrito";
                    return;
                }

                int idCliente = (int)Session["idCliente"];
                carritoCompras carrito= carritoWS.obtenerCarritoEnProcesoDeCliente(idCliente);
                int idCarrito = carrito.id;

                var lista = carritoWS.listarLineaCarritoPorIdCarrito(idCarrito);

                if (lista != null)
                {
                    decimal total = 0;
                    int cantidad = 0;

                    foreach (var linea in lista)
                    {
                        cantidad += linea.cantidad;
                        total += (decimal)(linea.cantidad * linea.producto.precioRegular);
                    }

                    lblMiniCarrito.Text = $"Items: {cantidad} - Total: S/. {total}";
                }
                else
                {
                    lblMiniCarrito.Text = "Carrito vacío";
                }
            }
            catch (System.Exception ex)
            {
                lblMiniCarrito.Text = "Error cargando carrito: " + ex.Message;
            }
        }

        protected void btnAgregarProducto_Click(object sender, EventArgs e)
        {
            try
            {
                // ID del producto desde el CommandArgument del botón
                int idProducto = int.Parse(((System.Web.UI.WebControls.Button)sender).CommandArgument);
                int idCliente = (int)Session["idCliente"];

                // Obtener el carrito completo del cliente
                var carrito = carritoWS.obtenerCarritoEnProcesoDeCliente(idCliente);

                if (carrito == null)
                {
                    lblMensaje.Text = "No se pudo obtener el carrito del cliente.";
                    return;
                }

                // Crear la línea de carrito
                var linea = new lineaCarrito
                {
                    carritoCompras = carrito,
                    producto = productoWS.obtenerProducto(idProducto),
                    cantidad = 1,
                    precio = productoWS.obtenerProducto(idProducto).precioRegular
                };

                // Insertar la línea usando LineaCarritoWS
                
                lineaWS.InsertarLineaCarrito(linea);

                // Recargar mini carrito
                CargarCarritoMini();

                lblMensaje.Text = "Producto agregado correctamente.";
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error al agregar al carrito: " + ex.Message;
            }
        }
    }
}
