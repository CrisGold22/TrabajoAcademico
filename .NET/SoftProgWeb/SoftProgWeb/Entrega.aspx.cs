using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using SoftProgWeb.SoftProgWS;

namespace SoftProgWeb
{
    public partial class Entrega : System.Web.UI.Page
    {
        private EmpresaWSClient empresaWS = new EmpresaWSClient();
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarEstablecimientos();
            }
        }

        private void CargarEstablecimientos()
        {
            try
            {
                ddlEstablecimiento.Items.Clear(); // Limpiar items actuales
                int idCliente = Convert.ToInt32(Session["idCliente"]);

                // Lista de empresas activas desde el WS
                List<empresa> listaEmpresas = new List<empresa>(empresaWS.listarEmpresasPorClienteActivas(idCliente));

                if (listaEmpresas != null && listaEmpresas.Count > 0)
                {
                    foreach (var empresa in listaEmpresas)
                    {
                        ddlEstablecimiento.Items.Add(new System.Web.UI.WebControls.ListItem(
                            empresa.razonSocial, empresa.id.ToString()
                        ));
                    }
                }
                else
                {
                    ddlEstablecimiento.Items.Add(new System.Web.UI.WebControls.ListItem(
                        "No hay establecimientos disponibles", "0"));
                }
            }
            catch (System.Exception ex)
            {
                ddlEstablecimiento.Items.Add(new System.Web.UI.WebControls.ListItem(
                    "Error cargando establecimientos: " + ex.Message, "0"
                ));
            }
        }

        protected void btnContinuar_Click(object sender, EventArgs e)
        {
            if (ddlEstablecimiento.SelectedValue == "0")
            {
                // Mostrar mensaje o alert
                Response.Write("<script>alert('Seleccione un establecimiento válido');</script>");
                return;
            }

            
            Session["idEmpresaEntrega"] = ddlEstablecimiento.SelectedValue;
            Session["fechaEntrega"] = txtFechaEntrega.Text;

            // Redirigir a Pago.aspx
            Response.Redirect("Pago.aspx");
        }
    }
}