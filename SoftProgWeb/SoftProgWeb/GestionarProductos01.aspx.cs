using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class GestionarProductos01 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarDatosDeEjemplo();
            }
        }

        private void CargarDatosDeEjemplo()
        {

            var productos = new List<object>
            {
                new { ID = 26, Imagen = "", Producto = "Gaseosa COCA COLA Caja 12 Botellas...", Categoria = "Gaseosas", PrecioSinIGV = 12.00, PrecioConIGV = 17.50, Estado = "Activo", Stock = 1000 },
                new { ID = 27, Imagen = "", Producto = "Gaseosa PEPSI Cola Paquete 15 Botellas...", Categoria = "Gaseosas", PrecioSinIGV = 10.00, PrecioConIGV = 12.50, Estado = "Activo", Stock = 2000 },
                new { ID = 28, Imagen = "", Producto = "Gaseosa INCA KOLA Sin Azúcar Paquete 6...", Categoria = "Gaseosas", PrecioSinIGV = 11.00, PrecioConIGV = 13.50, Estado = "Activo", Stock = 3131 },
                new { ID = 29, Imagen = "", Producto = "Gaseosa INCA KOLA Paquete 12 Botellas...", Categoria = "Gaseosas", PrecioSinIGV = 25.00, PrecioConIGV = 29.50, Estado = "Activo", Stock = 2000 },
                new { ID = 30, Imagen = "", Producto = "Gaseosa SPRITE Lima Limón Paquete 12...", Categoria = "Gaseosas", PrecioSinIGV = 30.00, PrecioConIGV = 35.50, Estado = "Activo", Stock = 500 }
            };

            gvProductos.DataSource = productos;

            gvProductos.DataBind();
        }
        protected void btnNuevoProducto_Click(object sender, EventArgs e)
        {
            Response.Redirect("GestionarProductos02RegistrarProducto.aspx");
        }

        protected void gvProductos_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "EliminarProducto")
            {
                string productoId = e.CommandArgument.ToString();

                // Script generado mediante IA
                string script = @"
            document.addEventListener('DOMContentLoaded', function () {
                var myModal = new bootstrap.Modal(document.getElementById('modalConfirmacion'), {});
                myModal.show();
            });";

                ScriptManager.RegisterStartupScript(this, GetType(), "mostrarModalConfirmacion", script, true);
            }
        }
    }
}