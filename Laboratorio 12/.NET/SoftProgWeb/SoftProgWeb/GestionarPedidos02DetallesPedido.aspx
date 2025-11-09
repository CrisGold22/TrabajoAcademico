<%@ Page Title="Detalle de Pedido" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarPedidos02DetallesPedido.aspx.cs" Inherits="SoftProgWeb.GestionarPedidos02DetallesPedido" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

    <div class="mb-3">
        <a href="GestionarPedidos01.aspx" class="btn btn-secondary">
            <i class="fas fa-arrow-left me-2"></i>Regresar a Pedidos
        </a>
    </div>

    <div class="card shadow-sm mb-4">
        <div class="card-header">
            <h4>Detalle de la Orden de Venta</h4>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-6">
                    <strong>Nro Orden:</strong> <asp:Label ID="lblNroOrden" runat="server" Text="..."></asp:Label><br />
                    <strong>Fecha de Emisión:</strong> <asp:Label ID="lblFecha" runat="server" Text="..."></asp:Label>
                </div>
                <div class="col-md-6">
                    <strong>Nombre/Razón Social:</strong> <asp:Label ID="lblRazonSocial" runat="server" Text="..."></asp:Label><br />
                    <strong>RUC:</strong> <asp:Label ID="lblRUC" runat="server" Text="..."></asp:Label>
                </div>
            </div>
        </div>
    </div>

    <div class="card shadow-sm">
    <div class="card-header">Productos</div>
    <div class="card-body">
    <div class="table-responsive">
        <asp:GridView ID="gvProductos" runat="server" 
            CssClass="table table-bordered"
                AutoGenerateColumns="False" EmptyDataText="Este cliente no tiene pedidos registrados."
                HeaderStyle-CssClass="table-light">
            <Columns>
                <asp:BoundField DataField="Cantidad" HeaderText="Cantidad" />
                <asp:BoundField DataField="Codigo" HeaderText="Código" />
                <asp:BoundField DataField="Descripcion" HeaderText="Descripción" />
                <asp:BoundField DataField="PrecioUnitario" HeaderText="P. Unit" DataFormatString="{0:C}" />
                <asp:BoundField DataField="Subtotal" HeaderText="Subtotal" DataFormatString="{0:C}" />
            </Columns>
        </asp:GridView>
    </div>
        </div>
        </div>

    <div class="row justify-content-end mt-3">
        <div class="col-md-4">
            <table class="table table-sm">
                <tbody>
                    <tr>
                        <td><strong>SubTotal:</strong></td>
                        <td class="text-end"><asp:Label ID="lblSubtotal" runat="server" Text="S/ 0.00"></asp:Label></td>
                    </tr>
                    <tr>
                        <td><strong>IGV (18%):</strong></td>
                        <td class="text-end"><asp:Label ID="lblIGV" runat="server" Text="S/ 0.00"></asp:Label></td>
                    </tr>
                    <tr class="table-primary">
                        <td><strong>Total:</strong></td>
                        <td class="text-end fw-bold"><asp:Label ID="lblTotal" runat="server" Text="S/ 0.00"></asp:Label></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

</</asp:Content>