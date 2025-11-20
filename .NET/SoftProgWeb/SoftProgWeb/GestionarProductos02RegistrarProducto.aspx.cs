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
            }
            else
            {
                litTitulo.Text = "Modificar Producto";
                int idProducto = Convert.ToInt32(Request.QueryString["id"]);
                ViewState["ProductoID"] = idProducto;
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
                txtNombre.Text = prod.nombre;
                txtSKU.Text = prod.SKU;
                txtMarca.Text = prod.marca;
                txtDescripcion.Text = prod.descripcion;
                txtPrecioRegular.Text = prod.precioRegular.ToString();
                txtStockDisponible.Text = prod.stockDisponible.ToString();

                ddlCategoria.SelectedValue = prod.categoria.id.ToString();
                ddlUnidadMedida.SelectedValue = prod.medidaAlMayor.ToString();
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

            prod.nombre = txtNombre.Text;
            prod.SKU = txtSKU.Text;
            prod.marca = txtMarca.Text;
            prod.descripcion = txtDescripcion.Text;
            prod.precioRegular = double.Parse(txtPrecioRegular.Text);
            prod.stockDisponible = int.Parse(txtStockDisponible.Text);
            prod.categoria = new categoriaProducto();
            prod.categoria.id = int.Parse(ddlCategoria.SelectedValue);
            prod.medidaAlMayor = (unidadMedida)Enum.Parse(typeof(unidadMedida), ddlUnidadMedida.SelectedValue);
            prod.medidaAlMayorSpecified = true;

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
                Response.Redirect("~/GestionarProductos01.aspx");
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