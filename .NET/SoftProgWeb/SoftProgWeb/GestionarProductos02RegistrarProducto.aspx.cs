using SoftProgWeb.SoftProgWS;
using System;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class GestionarProductos02RegistrarProducto : System.Web.UI.Page
    {
        private ProductoWSClient _servicioProducto;
        private CategoriaProductoWSClient _servicioCategoria;

        protected void Page_Load(object sender, EventArgs e)
        {
            _servicioProducto = new ProductoWSClient();
            _servicioCategoria = new CategoriaProductoWSClient();

            pnlMensaje.Visible = false;

            if (!Page.IsPostBack)
            {
                CargarDependencias();

                if (Request.QueryString["id"] == null)
                {
                    litTitulo.Text = "Nuevo Producto";
                    ViewState["ProductoID"] = 0;
                    lblProductoID.Text = "Nuevo";
                }
                else
                {
                    litTitulo.Text = "Modificar Producto";
                    int idProducto = Convert.ToInt32(Request.QueryString["id"]);
                    ViewState["ProductoID"] = idProducto;
                    lblProductoID.Text = idProducto.ToString();
                    CargarDatosProducto(idProducto);
                }
            }
        }

        private void CargarDependencias()
        {
            try
            {
                ddlCategoria.DataSource = _servicioCategoria.listarCategoriaProducto();
                ddlCategoria.DataValueField = "id";
                ddlCategoria.DataTextField = "nombre";
                ddlCategoria.DataBind();
                ddlCategoria.Items.Insert(0, new ListItem("-- Seleccionar Categoría --", "0"));

                ddlUnidadMedida.DataSource = Enum.GetNames(typeof(unidadMedida));
                ddlUnidadMedida.DataBind();
            }
            catch (System.Exception ex)
            {
                MostrarError("Error al cargar dependencias: " + ex.Message);
            }
        }

        private void CargarDatosProducto(int idProducto)
        {
            try
            {
                producto prod = _servicioProducto.obtenerProducto(idProducto);
                if (prod != null)
                {
                    lblProductoID.Text = idProducto.ToString();
                    txtNombre.Text = prod.nombre;
                    txtSKU.Text = prod.sku;
                    txtMarca.Text = prod.marca;
                    txtDescripcion.Text = prod.descripcion;
                    txtUrlImagen.Text = prod.imagenURL;
                    txtPrecioRegular.Text = prod.precioRegular.ToString();
                    txtPrecioMayor.Text = prod.precioAlMayor.ToString();
                    txtStockDisponible.Text = prod.stockDisponible.ToString();
                    txtStockMaximo.Text = prod.stockMaximo.ToString();
                    ddlCategoria.SelectedValue = prod.categoria.id.ToString();
                    ddlUnidadMedida.SelectedValue = prod.medidaAlMayor.ToString();
                    chkActivo.Checked = prod.activo;
                }
            }
            catch (System.Exception ex)
            {
                MostrarError("Error al cargar el producto: " + ex.Message);
            }
        }

        protected ProductoWSClient Get_servicioProducto()
        {
            return _servicioProducto;
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {
                producto prod = new producto();
                
                if(lblProductoID.Text != "Nuevo")
                {
                    prod.id = int.Parse(lblProductoID.Text);
                }
                prod.nombre = txtNombre.Text;
                prod.sku = txtSKU.Text;
                prod.marca = txtMarca.Text;
                prod.descripcion = txtDescripcion.Text;
                prod.imagenURL = txtUrlImagen.Text;
                prod.precioRegular = double.Parse(txtPrecioRegular.Text);
                prod.precioAlMayor = double.Parse(txtPrecioMayor.Text);
                prod.stockDisponible = int.Parse(txtStockDisponible.Text);
                prod.stockMaximo = int.Parse(txtStockMaximo.Text);
                prod.categoria = _servicioCategoria.obtenerCategoriaProducto(1);
                prod.medidaAlMayor = (unidadMedida)Enum.Parse(typeof(unidadMedida), ddlUnidadMedida.SelectedValue);
                prod.medidaAlMayorSpecified = true;
                prod.activo = chkActivo.Checked;
                prod.activoInt = 1;


                int idProducto = (int)ViewState["ProductoID"];
                int resultado = 0;

                if (idProducto == 0)
                {
                    _servicioProducto.insertarProducto(prod);
                    resultado = 1;
                }
                else
                {
                    prod.id = idProducto;
                    _servicioProducto.actualizarProducto(prod);
                    resultado = 1;
                }

                if (resultado > 0)
                {
                    Response.Redirect("~/GestionarProductos01.aspx", false);
                    Context.ApplicationInstance.CompleteRequest();
                }
                else
                {
                    MostrarError("No se pudo guardar el producto. El backend no reportó filas afectadas.");
                }
            }
            catch (System.Exception ex)
            {
                MostrarError("Error al guardar: " + ex.Message);
            }
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/GestionarProductos01.aspx");
        }

        private void MostrarError(string mensaje)
        {
            pnlMensaje.Visible = true;
            litMensajeError.Text = mensaje;
        }
    }
}