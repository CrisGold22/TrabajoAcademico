using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Web.UI.WebControls;
using SoftProgWeb.SoftProgWS;

namespace SoftProgWeb
{
    public partial class CategoriaProducto : System.Web.UI.Page
    {
        private ProductoWSClient productoWS;
        private LineaCarritoWSClient lineaCarritoWS;
        private CarritoComprasWSClient carritoComprasWS;
        private CategoriaProductoWSClient categoriaProductoWS;

        public CategoriaProducto()
        {
            productoWS = new ProductoWSClient();
            lineaCarritoWS = new LineaCarritoWSClient();
            carritoComprasWS = new CarritoComprasWSClient();
            categoriaProductoWS = new CategoriaProductoWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string category = Request.QueryString["category"];

                CategoryLabel.Text = "Home > " + (category ?? "Categoría");

                CargarMarcasWS(category);
                CargarProductosIniciales(category);
            }
        }

        private void CargarMarcasWS(String category)
        {
            try
            {
                System.Diagnostics.Debug.WriteLine("=== INICIO CargarMarcasWS ===");
                System.Diagnostics.Debug.WriteLine("Category recibida: '" + category + "'");

                var categoriaClase = categoriaProductoWS.obtenerCategoriaSegunNombre(category);

                if (categoriaClase == null)
                {
                    System.Diagnostics.Debug.WriteLine("ERROR: categoriaClase es NULL");
                    return;
                }

                System.Diagnostics.Debug.WriteLine("Categoría encontrada - ID: " + categoriaClase.id + ", Nombre: " + categoriaClase.nombre);

                var marcas = categoriaProductoWS.listarmarcaporIdcategoria(categoriaClase.id);

                if (marcas == null || marcas.Length == 0)
                {
                    System.Diagnostics.Debug.WriteLine("WARNING: No hay marcas para esta categoría");
                    chkMarca.DataSource = new string[] { }; // Lista vacía
                }
                else
                {
                    System.Diagnostics.Debug.WriteLine("Marcas encontradas: " + marcas.Length);
                    foreach (var marca in marcas)
                    {
                        System.Diagnostics.Debug.WriteLine("  - Marca: '" + marca + "'");
                    }
                    chkMarca.DataSource = marcas;
                }

                chkMarca.DataBind();
                System.Diagnostics.Debug.WriteLine("=== FIN CargarMarcasWS ===");
            }
            catch (System.Exception ex)
            {
                System.Diagnostics.Debug.WriteLine("EXCEPCIÓN en CargarMarcasWS: " + ex.Message);
                System.Diagnostics.Debug.WriteLine("StackTrace: " + ex.StackTrace);
            }
        }


        private void CargarProductosIniciales(string categoria)
        {
            var productos = productoWS.listaProductosPorCategoria(categoria);

            ProductRepeater.DataSource = productos;
            ProductRepeater.DataBind();
        }


        private string Normalizar(string texto)
        {
            if (texto == null) return "";

            string normalized = texto.Normalize(NormalizationForm.FormD);
            var sb = new StringBuilder();

            foreach (char c in normalized)
            {
                if (CharUnicodeInfo.GetUnicodeCategory(c) != UnicodeCategory.NonSpacingMark)
                    sb.Append(c);
            }

            return sb.ToString().Trim().ToUpper();
        }

        protected void btnFiltrar_Click(object sender, EventArgs e)
        {
            try
            {
                System.Diagnostics.Debug.WriteLine("=== INICIO btnFiltrar_Click ===");

                string category = Request.QueryString["category"];
                System.Diagnostics.Debug.WriteLine("Category: '" + category + "'");

                var categoriaClase = categoriaProductoWS.obtenerCategoriaSegunNombre(category);

                if (categoriaClase == null)
                {
                    System.Diagnostics.Debug.WriteLine("ERROR: No se pudo obtener categoriaClase");
                    CargarProductosIniciales(category);
                    return;
                }

                int idCategoria = categoriaClase.id;
                System.Diagnostics.Debug.WriteLine("ID Categoría: " + idCategoria);

                // Filtrar por MARCA
                var marcasSeleccionadas = chkMarca.Items
                    .Cast<ListItem>()
                    .Where(i => i.Selected)
                    .Select(i => Normalizar(i.Text))
                    .ToList();

                System.Diagnostics.Debug.WriteLine("Marcas seleccionadas: " + marcasSeleccionadas.Count);
                foreach (var m in marcasSeleccionadas)
                {
                    System.Diagnostics.Debug.WriteLine("  - " + m);
                }

                bool hayMarcaSeleccionada = marcasSeleccionadas.Any();

                double precioMin = 0;
                double precioMax = double.MaxValue;
                bool hayFiltroPrecio = false;

                if (!string.IsNullOrWhiteSpace(txtPrecioMin.Text))
                {
                    if (double.TryParse(txtPrecioMin.Text, NumberStyles.Any,
                        CultureInfo.InvariantCulture, out double tempMin))
                    {
                        precioMin = tempMin;
                        hayFiltroPrecio = true;
                    }
                }

                if (!string.IsNullOrWhiteSpace(txtPrecioMax.Text))
                {
                    if (double.TryParse(txtPrecioMax.Text, NumberStyles.Any,
                        CultureInfo.InvariantCulture, out double tempMax))
                    {
                        precioMax = tempMax;
                        hayFiltroPrecio = true;
                    }
                }

                System.Diagnostics.Debug.WriteLine("Filtro precio: Min=" + precioMin + ", Max=" + precioMax + ", Activo=" + hayFiltroPrecio);

                if (precioMin > precioMax)
                {
                    double temp = precioMin;
                    precioMin = precioMax;
                    precioMax = temp;
                }

                producto[] productos = null;

                if (!marcasSeleccionadas.Any())
                {
                    System.Diagnostics.Debug.WriteLine("No hay marcas seleccionadas, cargando productos iniciales");
                    CargarProductosIniciales(category);
                    return;
                }

                string marcaFiltrar = marcasSeleccionadas.First();
                System.Diagnostics.Debug.WriteLine("Filtrando por marca: '" + marcaFiltrar + "'");

                // CASO 1: Filtrar por MARCA y PRECIO
                if (hayMarcaSeleccionada && hayFiltroPrecio)
                {
                    System.Diagnostics.Debug.WriteLine("CASO 1: Marca + Precio");
                    var productosPorMarca = productoWS.listarfiltrarProductoPorMarca(idCategoria, marcaFiltrar);

                    System.Diagnostics.Debug.WriteLine("Productos encontrados por marca: " + (productosPorMarca?.Length ?? 0));

                    if (productosPorMarca != null)
                    {
                        productos = productosPorMarca
                            .Where(p => p.precioRegular != null &&
                                      p.precioRegular >= precioMin &&
                                      p.precioRegular <= precioMax)
                            .ToArray();

                        System.Diagnostics.Debug.WriteLine("Productos después de filtrar por precio: " + productos.Length);
                    }
                }
                // CASO 2: Solo filtrar por MARCA
                else if (hayMarcaSeleccionada)
                {
                    System.Diagnostics.Debug.WriteLine("CASO 2: Solo Marca");
                    productos = productoWS.listarfiltrarProductoPorMarca(idCategoria, marcaFiltrar);
                    System.Diagnostics.Debug.WriteLine("Productos encontrados: " + (productos?.Length ?? 0));
                }
                // CASO 3: Solo filtrar por PRECIO
                else if (hayFiltroPrecio)
                {
                    System.Diagnostics.Debug.WriteLine("CASO 3: Solo Precio");
                    productos = productoWS.listarfiltrarProductoPorPrecioRegular(idCategoria, precioMin, precioMax);
                    System.Diagnostics.Debug.WriteLine("Productos encontrados: " + (productos?.Length ?? 0));
                }
                // CASO 4: Sin filtros
                else
                {
                    System.Diagnostics.Debug.WriteLine("CASO 4: Sin filtros");
                    CargarProductosIniciales(category);
                    return;
                }

                // Mostrar productos filtrados
                if (productos != null && productos.Length > 0)
                {
                    System.Diagnostics.Debug.WriteLine("Mostrando " + productos.Length + " productos");
                    ProductRepeater.DataSource = productos;
                    ProductRepeater.DataBind();
                }
                else
                {
                    System.Diagnostics.Debug.WriteLine("WARNING: No hay productos para mostrar");
                    ProductRepeater.DataSource = new producto[] { };
                    ProductRepeater.DataBind();
                }

                System.Diagnostics.Debug.WriteLine("=== FIN btnFiltrar_Click ===");
            }
            catch (System.Exception ex)
            {
                System.Diagnostics.Debug.WriteLine("EXCEPCIÓN en btnFiltrar_Click: " + ex.Message);
                System.Diagnostics.Debug.WriteLine("StackTrace: " + ex.StackTrace);
                if (ex.InnerException != null)
                {
                    System.Diagnostics.Debug.WriteLine("InnerException: " + ex.InnerException.Message);
                }

                string category = Request.QueryString["category"];
                CargarProductosIniciales(category);
            }
        }


        protected void btnAgregarCarrito_Click(object sender, EventArgs e)
        {
            Button btnAgregar = (Button)sender;
            int productoId = Convert.ToInt32(btnAgregar.CommandArgument);

            var producto = productoWS.obtenerProducto(productoId);
            int idCliente = Convert.ToInt32(Session["IdCliente"]);

            var carrito = carritoComprasWS.obtenerCarritoDeCliente(idCliente);

            if (carrito != null)
            {
                lineaCarrito nuevaLinea = new lineaCarrito
                {
                    producto = producto,
                    cantidad = 1,
                    carritoCompras = carrito
                };

                lineaCarritoWS.actualizarLineaCarrito(nuevaLinea);
            }

            Response.Redirect("~/MiCarrito.aspx");
        }
    }
}