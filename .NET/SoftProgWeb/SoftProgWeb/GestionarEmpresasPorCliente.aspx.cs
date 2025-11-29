using SoftProgWeb.SoftProgWS;
using System;
using System.Collections.Generic;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoftProgWeb
{
    public partial class GestionarEmpresasPorCliente : System.Web.UI.Page
    {
        private ClienteWSClient _servicioCliente;
        private EmpresaWSClient _servicioEmpresa;
        private cliente cliente;

        protected void Page_Load(object sender, EventArgs e)
        {
            _servicioEmpresa = new EmpresaWSClient();
            _servicioCliente = new ClienteWSClient();
            pnlMensaje.Visible = false;

            if (!Page.IsPostBack)
            {
                if (Request.QueryString["id"] == null)
                {
                    Response.Redirect("~/GestionarClientes01.aspx");
                    return;
                }

                int idCliente = Convert.ToInt32(Request.QueryString["id"]);
                cliente = _servicioCliente.obtenerCliente(idCliente);
                CargarDetallesCliente(idCliente);
            }
        }

        private void CargarDetallesCliente(int idCliente)
        {
            try
            {
                cliente cli = _servicioCliente.obtenerCliente(idCliente);

                if (cli != null)
                {
                    litNombreCliente.Text = cli.nombre + " " + cli.apellidoPaterno + " (DNI: " + cli.dni + ")";

                    var listaEmpresas = _servicioEmpresa.listarEmpresasPorCliente(cli.id);

                    if (listaEmpresas != null && listaEmpresas.Length > 0)
                    {
                        empresa primeraEmpresa = listaEmpresas[0];
                        LitUsuario.Text = primeraEmpresa.cliente.cuenta.username;
                        //litEmail.Text = primeraEmpresa.cliente..ToString();
                        litTelefono.Text = primeraEmpresa.cliente.telefono.ToString();
                        litGenero.Text = primeraEmpresa.cliente.genero.ToString();
                        litCatCli.Text = primeraEmpresa.cliente.categoriaCliente.ToString();
                        LitCredito.Text = primeraEmpresa.cliente.lineaCredito.ToString();
                        LitPedidosHist.Text = primeraEmpresa.cliente.numeroPedidosHistorico.ToString();
                        LitPedidosMen.Text = primeraEmpresa.cliente.numeroPedidosMensualPro.ToString();

                        gvEmpresasCliente.DataSource = listaEmpresas;
                    }
                    else
                    {
                        gvEmpresasCliente.DataSource = null;
                    }

                    gvEmpresasCliente.DataBind();
                }
                else
                {
                    MostrarError("No se encontró el cliente seleccionado.");
                }
            }
            catch (System.Exception ex)
            {
                MostrarError(ex.Message);
            }
        }

        protected void btnVolver_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/GestionarClientes01.aspx");
        }

        private void MostrarError(string mensaje)
        {
            pnlMensaje.Visible = true;
            litMensajeError.Text = mensaje;
        }

        protected void btnGenerarReporte_Click(object sender, EventArgs e)
        {
            int id = 2;
            Response.Redirect("http://localhost:8080/SoftProgReportes/ReporteOrdenCompra?id=" + id);
        }
    }
}