using System;
using System.Collections.Generic;
using System.Web.UI;

namespace SoftProgWeb
{
    public partial class GestionarPedidos01 : Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarDatosDeEjemplo();
            }
        }
        protected string GetEstadoBadge(string estado)
        {
            switch (estado)
            {
                case "Atendido":
                    return "badge bg-success";
                case "Pendiente":
                    return "badge bg-warning text-dark";
                case "Anulado":
                    return "badge bg-secondary";
                default:
                    return "badge bg-light text-dark";
            }
        }

        private void CargarDatosDeEjemplo()
        {
            var pedidos = new List<object>
            {
                new { PedidoID = 10248, RUC = "20131412038", RazonSocial = "TELEFONICA DEL PERU S.A.A.", Monto = 440.00, NFactura = "F001-123", Estado = "Atendido", Fecha = new DateTime(2022, 8, 4) },
                new { PedidoID = 10249, RUC = "20100053218", RazonSocial = "ACEROS AREQUIPA S.A.", Monto = 1863.40, NFactura = "F001-124", Estado = "Atendido", Fecha = new DateTime(2022, 8, 5) },
                new { PedidoID = 10250, RUC = "20100047218", RazonSocial = "BACKUS Y JOHNSTON S.A.A.", Monto = 1600.60, NFactura = "F001-125", Estado = "Pendiente", Fecha = new DateTime(2022, 8, 8) },
                new { PedidoID = 10251, RUC = "20100113259", RazonSocial = "GLORIA S.A.", Monto = 654.06, NFactura = "N/A", Estado = "Anulado", Fecha = new DateTime(2022, 8, 8) }
            };

            gvPedidos.DataSource = pedidos;
            gvPedidos.DataBind();
        }

        protected void gvPedidos_SelectedIndexChanged(object sender, EventArgs e)
        {

        }
    }
}