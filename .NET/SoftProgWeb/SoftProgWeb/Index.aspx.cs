using SoftProgWeb.SoftProgWS;
using System;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class Index : System.Web.UI.Page
    {

        private ProductoWSClient productoWS;
        private CarritoComprasWSClient carritoComprasWS;
        private LineaCarritoWSClient lineaCarritoWS;
        public Index()
        {
            productoWS = new ProductoWSClient();
            carritoComprasWS = new CarritoComprasWSClient();
            lineaCarritoWS = new LineaCarritoWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                var products = productoWS.listarProducto();
                if (products != null)
                {
                    // Asignar los productos al Repeater
                    ProductRepeater.DataSource = products;
                    ProductRepeater.DataBind();
                }
                else
                {
                    // Manejar el caso en que no se encuentren productos para esta categoría
                    ProductRepeater.DataSource = null;
                    ProductRepeater.DataBind();
                    // Mostrar un mensaje si no hay productos
                    //CategoryLabel.Text += " - No se encontraron productos en esta categoría.";
                }
            }

        }
        protected void btnAgregarCarrito_Click(object sender, EventArgs e)
        {

            Button btnAgregar = (Button)sender; // Obtener el Button que fue clickeado
            int productoId = Convert.ToInt32(btnAgregar.CommandArgument);// Obtener el productoId desde el CommandArgument

            var producto = productoWS.obtenerProducto(productoId);// Llamar al WebService para obtener el producto usando el productoId

            int idCliente = Convert.ToInt32(Session["IdCliente"]);
            var carritoA = carritoComprasWS.obtenerCarritoDeCliente(idCliente);


            if (carritoA != null)
            {
                lineaCarrito lineaCarrito = new lineaCarrito();
                lineaCarrito.cantidad = 1;
                lineaCarrito.precio = producto.precioRegular;
                lineaCarrito.subTotal = 1 * producto.precioRegular;
                lineaCarrito.carritoCompras = carritoA;
                lineaCarrito.activo = true;
                lineaCarrito.activoInt = 1;
                lineaCarrito.producto = producto;
                lineaCarritoWS.insertarLineaCarrito(lineaCarrito);
            }
            // Redirigir al carrito
            Response.Redirect("~/MiCarrito.aspx");
        }
    }
}