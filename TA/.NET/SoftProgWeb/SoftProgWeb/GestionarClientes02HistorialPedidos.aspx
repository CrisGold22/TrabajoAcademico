<%@ Page Title="" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarClientes02HistorialPedidos.aspx.cs" Inherits="SoftProgWeb.GestionarClientes02HistorialPedidos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <a href="GestionarClientes01.aspx" class="btn btn-secondary">
            <i class="fas fa-arrow-left me-2"></i>Regresar a Clientes
        </a>
    </div>

    <div class="card shadow-sm mb-4">
        <div class="card-header">
            <h4>Detalle de Cliente</h4>
        </div>
        <div class="card-body">
            <h5><asp:Label ID="lblRazonSocial" runat="server"></asp:Label></h5>
            <strong>RUC:</strong> <asp:Label ID="lblRUC" runat="server"></asp:Label>
        </div>
    </div>

    <div class="card shadow-sm">
        <div class="card-header">Pedidos Realizados</div>
        <div class="card-body">
            <asp:GridView ID="gvHistorial" runat="server"
                CssClass="table table-bordered"
                AutoGenerateColumns="False" EmptyDataText="Este cliente no tiene pedidos registrados."
                HeaderStyle-CssClass="table-light">
                <Columns>
                    <asp:BoundField DataField="FechaPedido" HeaderText="Fecha de Pedido" DataFormatString="{0:dd/MM/yyyy}" />
                    <asp:BoundField DataField="CantidadProductos" HeaderText="Cant. Productos" />
                    <asp:TemplateField HeaderText="Estado">
                         <ItemTemplate>
                            <span class='<%# GetEstadoBadge(Eval("Estado").ToString()) %>'>
                                <%# Eval("Estado") %>
                            </span>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:BoundField DataField="MontoFinal" HeaderText="Monto Final" DataFormatString="{0:C}" />
                </Columns>
            </asp:GridView>
        </div>
    </div>
</asp:Content>