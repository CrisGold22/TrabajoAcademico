using SoftProgWeb.SoftProgWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class GestionarDescuentos : System.Web.UI.Page
    {
        DescuentoWSClient servicioDescuento = new DescuentoWSClient("DescuentoWSPort");
        private ProductoWSClient servicioProducto = new ProductoWSClient("ProductoWSPort1");

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Request.QueryString["idProducto"] != null)
                {
                    int idProducto = int.Parse(Request.QueryString["idProducto"]);
                    CargarInfoProducto(idProducto);
                    CargarGrillaDescuentos(idProducto);
                }
                else
                {
                    Response.Redirect("GestionarProductos01.aspx");
                }
            }
        }

        private void CargarInfoProducto(int id)
        {

            var producto = servicioProducto.obtenerProducto(id);
            lblNombreProducto.Text = producto.nombre;
            lblSKU.Text = producto.sku;
            lblPrecioRegular.Text = "S/. " + producto.precioRegular.ToString("N2");
        }

        private void CargarGrillaDescuentos(int idProducto)
        {
            var todosLosDescuentos = servicioDescuento.listarDescuento();

            if (todosLosDescuentos != null)
            {
                var descuentosDelProducto = todosLosDescuentos
                    .Where(d => d.producto.id == idProducto && d.activo == true)
                    .OrderBy(d => d.cantidadMin)
                    .ToList();

                gvDescuentos.DataSource = descuentosDelProducto;
                gvDescuentos.DataBind();
            }
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {
                int idProducto = int.Parse(Request.QueryString["idProducto"]);

                int min = int.Parse(txtMin.Text);
                int max = int.Parse(txtMax.Text);
                double precio = double.Parse(txtPrecioOferta.Text);

                if (min >= max) throw new System.Exception("El mínimo debe ser menor al máximo.");

                descuento nuevoDescuento = new descuento();
                nuevoDescuento.cantidadMin = min;
                nuevoDescuento.cantidadMax = max;
                nuevoDescuento.precioPorVolumen = precio;
                nuevoDescuento.activo = true;

                nuevoDescuento.producto = new producto();
                nuevoDescuento.producto.id = idProducto;

                nuevoDescuento.producto.medidaAlMayor = unidadMedida.UNIDAD;
                nuevoDescuento.producto.medidaAlMayorSpecified = true;

                int idDescuento = int.Parse(hdnIdDescuento.Value);

                if (idDescuento == 0)
                {
                    servicioDescuento.insertarDescuento(nuevoDescuento);
                }
                else
                {
                    nuevoDescuento.id = idDescuento;
                    servicioDescuento.actualizarDescuentoPorIdProducto(idProducto,nuevoDescuento);
                }

                LimpiarFormulario();
                CargarGrillaDescuentos(idProducto);
            }
            catch (System.Exception ex)
            {
                lblError.Text = "Error: " + ex.Message;
            }
        }

        protected void gvDescuentos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int idDescuento = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "EditarDescuento")
            {
                var desc = servicioDescuento.obtenerDescuento(idDescuento);

                hdnIdDescuento.Value = desc.id.ToString();
                txtMin.Text = desc.cantidadMin.ToString();
                txtMax.Text = desc.cantidadMax.ToString();
                txtPrecioOferta.Text = desc.precioPorVolumen.ToString();

                btnGuardar.Text = "Actualizar Rango";
                btnCancelar.Visible = true;
            }
            else if (e.CommandName == "EliminarDescuento")
            {
                servicioDescuento.eliminarDescuento(idDescuento);
                CargarGrillaDescuentos(int.Parse(Request.QueryString["idProducto"]));
            }
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            LimpiarFormulario();
        }

        protected void btnVolver_Click(object sender, EventArgs e)
        {
            Response.Redirect("GestionarProductos01.aspx");
        }

        private void LimpiarFormulario()
        {
            hdnIdDescuento.Value = "0";
            txtMin.Text = "";
            txtMax.Text = "";
            txtPrecioOferta.Text = "";
            btnGuardar.Text = "Guardar Rango";
            btnCancelar.Visible = false;
            lblError.Text = "";
        }
    }
}