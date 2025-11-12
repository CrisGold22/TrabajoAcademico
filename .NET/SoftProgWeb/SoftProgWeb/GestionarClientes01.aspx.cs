using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class GestionarClientes01 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarClientesDeEjemplo();
            }
        }

        private void CargarClientesDeEjemplo()
        {
            var clientes = new List<object>
            {
                new { RUC = "20131412038", RazonSocial = "TELEFONICA DEL PERU S.A.A." },
                new { RUC = "20100053218", RazonSocial = "CORPORACION ACEROS AREQUIPA S.A." },
                new { RUC = "20100047218", RazonSocial = "BACKUS Y JOHNSTON S.A.A." }
            };
            gvClientes.DataSource = clientes;
            gvClientes.DataBind();
        }

        protected void gvClientes_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "VerHistorial")
            {
                string rucCliente = e.CommandArgument.ToString();
                Response.Redirect($"~/GestionarClientes02HistorialPedidos.aspx?ruc={rucCliente}");
            }
        }
    }
}