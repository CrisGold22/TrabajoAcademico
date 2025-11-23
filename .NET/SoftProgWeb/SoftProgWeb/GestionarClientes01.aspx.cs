using SoftProgWeb.SoftProgWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class GestionarClientes01 : System.Web.UI.Page
    {
        private ClienteWSClient _servicioCliente;

        protected void Page_Load(object sender, EventArgs e)
        {
            _servicioCliente = new ClienteWSClient();
            pnlMensaje.Visible = false;

            if (!Page.IsPostBack)
            {
                CargarClientesTodos();
            }
        }

        private void EnlazarGrilla(IEnumerable<cliente> clientes)
        {
            gvClientes.DataSource = clientes.OrderBy(c => c.apellidoPaterno).ToList();
            gvClientes.DataBind();
        }

        private void CargarClientesTodos()
        {
            try
            {
                cliente[] clientes = _servicioCliente.listarCliente();
                EnlazarGrilla(clientes);
            }
            catch (System.Exception ex)
            {
                MostrarError(ex.Message);
            }
        }

        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            string id = txtId.Text.Trim();
            if (string.IsNullOrEmpty(id))
            {
                CargarClientesTodos();
                return;
            }

            try
            {
                int idProducto = int.Parse(id);

                cliente cli = _servicioCliente.obtenerCliente(idProducto);
                var listaResultados = new List<cliente>();
                if (cli != null && cli.id > 0)
                {
                    listaResultados.Add(cli);
                }
                EnlazarGrilla(listaResultados);
            }
            catch (System.Exception ex)
            {
                EnlazarGrilla(new List<cliente>());
                MostrarError(ex.Message);
            }
        }

        protected void btnLimpiar_Click(object sender, EventArgs e)
        {
            txtId.Text = string.Empty;
            CargarClientesTodos();
        }

        protected void gvClientes_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvClientes.PageIndex = e.NewPageIndex;

            if (string.IsNullOrEmpty(txtId.Text.Trim()))
            {
                CargarClientesTodos();
            }
            else
            {
                btnBuscar_Click(sender, e);
            }
        }

        protected void btnGenerarReporte_Click(object sender, EventArgs e)
        {
            Response.Redirect("http://localhost:8080/SoftProgReportes/ReporteClientes?autor=miraya");
        }
        protected void btnAtenderSolicitudes_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/GestionarEmpresasPendientes.aspx");
        }

        protected void gvClientes_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "VerEmpresas")
            {
                int idCliente = Convert.ToInt32(e.CommandArgument);
                Response.Redirect(string.Format("~/GestionarEmpresasPorCliente.aspx?id={0}", idCliente));
            }
        }

        private void MostrarError(string mensaje)
        {
            pnlMensaje.Visible = true;
            litMensajeError.Text = mensaje;
        }
    }
}