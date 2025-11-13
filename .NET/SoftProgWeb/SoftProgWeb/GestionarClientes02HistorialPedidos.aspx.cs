using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class GestionarClientes02HistorialPedidos : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string ruc = Request.QueryString["ruc"];
                if (!string.IsNullOrEmpty(ruc))
                {
                    CargarHistorialDeEjemplo(ruc);
                }
            }
        }

        protected string GetEstadoBadge(string estado)
        {
            return estado == "Atendido" ? "badge bg-success" : "badge bg-secondary";
        }

        private void CargarHistorialDeEjemplo(string ruc)
        {

            lblRUC.Text = ruc;
            lblRazonSocial.Text = "TELEFONICA DEL PERU S.A.A."; // Datos estaticos

            var historial = new List<object>
            {
                new { FechaPedido = new DateTime(2025, 10, 26), CantidadProductos = 5, Estado = "Atendido", MontoFinal = 1500.50 },
                new { FechaPedido = new DateTime(2025, 09, 15), CantidadProductos = 2, Estado = "Anulado", MontoFinal = 350.00 },
                new { FechaPedido = new DateTime(2025, 08, 01), CantidadProductos = 10, Estado = "Atendido", MontoFinal = 4200.00 }
            };

            gvHistorial.DataSource = historial;
            gvHistorial.DataBind();
        }
    }
}