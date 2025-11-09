using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class GestionarProductos02RegistrarProducto : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            // Script generdo mediante IA

            string script = @"
        document.addEventListener('DOMContentLoaded', function () {
            // Seleccionar el modal
            var myModal = new bootstrap.Modal(document.getElementById('modalExito'), {});

            document.getElementById('modalExito').addEventListener('hidden.bs.modal', function (event) {
                window.location.href = 'GestionarProductos01.aspx';
            });

            // Mostrar el modal
            myModal.show();
        });";

            ScriptManager.RegisterStartupScript(this, GetType(), "mostrarModalExito", script, true);
        }

    }
}