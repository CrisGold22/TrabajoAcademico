using SoftProgWeb.SoftProgWS;
using System;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class DetalleVerificacionEmpresa : System.Web.UI.Page
    {
        private EmpresaWSClient servicioEmpresa;

        protected void Page_Load(object sender, EventArgs e)
        {
            servicioEmpresa = new EmpresaWSClient();

            if (!Page.IsPostBack)
            {
                pnlMensaje.Visible = false;

                if (Request.QueryString["id"] == null)
                {
                    Response.Redirect("~/GestionarEmpresasPendientes.aspx");
                    return;
                }

                int idEmpresa = Convert.ToInt32(Request.QueryString["id"]);

                ViewState["EmpresaID"] = idEmpresa;

                CargarDatosEmpresa(idEmpresa);
            }
        }

        private void CargarDatosEmpresa(int idEmpresa)
        {
            try
            {
                empresa emp = servicioEmpresa.obtenerEmpresa(idEmpresa);

                if (emp != null)
                {
                    lblRazonSocial.Text = emp.razonSocial;
                    lblRUC.Text = emp.ruc;
                    lblDireccion.Text = emp.direccionFiscal;
                }
                else
                {
                    MostrarError("No se encontró la empresa con ID: " + idEmpresa);
                    btnAprobar.Enabled = false;
                    btnRechazar.Enabled = false;
                }
            }
            catch (System.Exception ex)
            {
                MostrarError("Error al cargar datos: " + ex.Message);
                btnAprobar.Enabled = false;
                btnRechazar.Enabled = false;
            }
        }

        protected void btnAprobar_Click(object sender, EventArgs e)
        {
            int idEmpresa = (int)ViewState["EmpresaID"];

            try
            {
                empresa emp = servicioEmpresa.obtenerEmpresa(idEmpresa);

                servicioEmpresa.validarEmpresa(idEmpresa);

                Response.Redirect("~/GestionarEmpresasPendientes.aspx");

            }
            catch (System.Exception ex)
            {
                MostrarError("Error al aprobar: " + ex.Message);
            }
        }

        protected void btnRechazar_Click(object sender, EventArgs e)
        {
            int idEmpresa = (int)ViewState["EmpresaID"];

            try
            {
                servicioEmpresa.eliminarEmpresa(idEmpresa);

                Response.Redirect("~/GestionarEmpresasPendientes.aspx");
                
            }
            catch (System.Exception ex)
            {
                MostrarError("Error al rechazar/eliminar: " + ex.Message);
            }
        }

        private void MostrarError(string mensaje)
        {
            pnlMensaje.Visible = true;
            litMensajeError.Text = mensaje;
        }
    }
}