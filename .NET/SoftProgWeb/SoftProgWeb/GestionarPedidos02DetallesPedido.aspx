<%@ Page Title="Detalle de Pedido" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarPedidos02DetallesPedido.aspx.cs" Inherits="SoftProgWeb.GestionarPedidos02DetallesPedido" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    
    <div class="container-fluid">
        <div class="row mb-3">
            <div class="col-md-10">
                <h2><i class="fas fa-file-alt"></i> Detalle del Pedido N° 
                    <asp:Label ID="lblPedidoID" runat="server" CssClass="text-primary"></asp:Label>
                </h2>
            </div>
            <div class="col-md-2 text-end">
                <asp:Button ID="btnVolver" runat="server" Text="Volver al Listado" 
                    CssClass="btn btn-secondary" OnClick="btnVolver_Click" CausesValidation="false" />
            </div>
        </div>

        <asp:Panel ID="pnlMensaje" runat="server" Visible="false" CssClass="alert alert-danger">
            <asp:Literal ID="litMensajeError" runat="server"></asp:Literal>
        </asp:Panel>

        <div class="card">
            <div class="card-header">
                Información del Cliente y Pedido
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <label><b>Cliente:</b></label>
                        <asp:Label ID="lblCliente" runat="server"></asp:Label>
                    </div>
                    <div class="col-md-6">
                        <label><b>DNI:</b></label>
                        <asp:Label ID="lblDNI" runat="server"></asp:Label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-md-4">
                        <label><b>Fecha de Creación:</b></label>
                        <asp:Label ID="lblFecha" runat="server"></asp:Label>
                    </div>
                    <div class="col-md-4">
                        <label><b>Estado:</b></label>
                        <asp:Label ID="lblEstado" runat="server" CssClass="badge bg-info text-dark" Text="No disponible"></asp:Label>
                    </div>
                    <div class="col-md-4">
                        <label><b>Monto Total:</b></label>
                        <asp:Label ID="lblTotal" runat="server" CssClass="fw-bold fs-5"></asp:Label>
                    </div>
                </div>
            </div>
        </div>

        <hr />
        
        <h4 class="mt-4">Líneas del Pedido</h4>
        <asp:GridView ID="gvLineasPedido" runat="server"
            CssClass="table table-sm"
            AutoGenerateColumns="False">
            <Columns>
                <asp:BoundField DataField="producto.id" HeaderText="ID Prod." />
                <asp:BoundField DataField="producto.nombre" HeaderText="Producto" />
                <asp:BoundField DataField="cantidad" HeaderText="Cantidad" />
                <asp:BoundField DataField="producto.precio" HeaderText="P. Unit." DataFormatString="{0:C2}" />
                <asp:BoundField DataField="subtotal" HeaderText="Subtotal" DataFormatString="{0:C2}" />
            </Columns>
        </asp:GridView>

    </div>

</asp:Content>