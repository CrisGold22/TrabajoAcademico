using SoftProgWeb.SoftProgWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class CategoriaProducto : System.Web.UI.Page
    {
        private ProductoWSClient productoWS;
        public CategoriaProducto()
        {
            productoWS = new ProductoWSClient();    
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string category = Request.QueryString["category"];

                if (!string.IsNullOrEmpty(category))
                {
                    CategoryLabel.Text = "Home > " + category;
                }
                else
                {
                    CategoryLabel.Text = "Home > Categoría no disponible";
                }
                // Aquí deberías obtener la lista de productos desde tu backend
                var products = productoWS.listaProductosPorCategoria(category); // Llama al backend para obtener los productos

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
                    CategoryLabel.Text += " - No se encontraron productos en esta categoría.";
                }
            }
        }
        
    }
}