using System;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftProgWeb;
using SoftProgWeb.ServiceioEmpresaWS;

namespace SoftProgWeb
{
    public partial class GestionarEmpresasPendientes : System.Web.UI.Page
    {
        private  ServiceioEmpresaWS.EmpresaWSClient servicioEmpresa;

        protected void Page_Load(object sender, EventArgs e)
        {
            servicioEmpresa = new ServiceioEmpresaWS.EmpresaWSClient();

            if (!Page.IsPostBack)
            {
                CargarEmpresasPendientes();
            }
        }

        private void CargarEmpresasPendientes()
        {
            try
            {
                empresa[] empresas = servicioEmpresa.listarEmpresasNoActivos();

                var empresasPendientes = empresas;

                gvEmpresas.DataSource = empresasPendientes;
                gvEmpresas.DataBind();
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine("Error al cargar empresas: " + ex.Message);
            }
        }

        protected void gvEmpresas_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "Procesar")
            {
                int idEmpresa = Convert.ToInt32(e.CommandArgument);

                Response.Redirect(string.Format("~/DetalleVerificacionEmpresa.aspx?id={0}", idEmpresa));
            }
        }
    }
}