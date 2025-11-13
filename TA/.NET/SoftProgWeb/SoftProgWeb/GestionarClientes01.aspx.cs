using SoftProgWeb.ServiceioEmpresaWS;
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
        private EmpresaWSClient servicioEmpresa;

        protected void Page_Load(object sender, EventArgs e)
        {
            servicioEmpresa = new EmpresaWSClient();

            if (!Page.IsPostBack)
            {
                CargarTodasLasEmpresas();
            }
        }

        private void CargarTodasLasEmpresas()
        {
            try
            {
                empresa[] empresas = servicioEmpresa.listarEmpresasActivos();

                gvEmpresas.DataSource = empresas.OrderBy(e => e.razonSocial).ToList();
                gvEmpresas.DataBind();
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine("Error al cargar todas las empresas: " + ex.Message);
            }
        }

        protected void gvEmpresas_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvEmpresas.PageIndex = e.NewPageIndex;

            CargarTodasLasEmpresas();
        }
        protected void btnVerPendientes_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/GestionarEmpresasPendientes.aspx");
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