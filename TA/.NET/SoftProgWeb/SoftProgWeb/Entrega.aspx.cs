using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class Entrega : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarEstablecimientos();
            }
        }

        private void CargarEstablecimientos()
        {

        }

        protected void btnContinuar_Click(object sender, EventArgs e)
        {
            // Validar y procesar el formulario
            // Redirigir a la página de Pago
            Response.Redirect("Pago.aspx");
        }
    }
}