<%@ Page Title="Detalle de Pedido" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarPedidos02DetallesPedido.aspx.cs" Inherits="SoftProgWeb.GestionarPedidos02DetallesPedido" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

<div class="container-fluid">

    <!-- Título -->
    <div class="row mb-3">
        <div class="col-md-10">
            <h2>
                <i class="fas fa-file-alt"></i>
                Detalle del Pedido N° 
                <asp:Label ID="lblPedidoID" runat="server" CssClass="text-primary"></asp:Label>
            </h2>
        </div>
        <div class="col-md-2 text-end">
            <asp:Button ID="btnVolver" runat="server" Text="Volver al Listado" 
                CssClass="btn btn-secondary" OnClick="btnVolver_Click" />
        </div>
    </div>

    <!-- ERROR -->
    <asp:Panel ID="pnlMensaje" runat="server" Visible="false" CssClass="alert alert-danger">
        <asp:Literal ID="litMensajeError" runat="server"></asp:Literal>
    </asp:Panel>

    <!-- CARD PRINCIPAL -->
    <div class="card">
        <div class="card-header">
            Información del Cliente, Empresa y Pedido
        </div>

        <div class="card-body">

            <!-- FILA 1: Cliente y Empresa -->
            <div class="row">
                <div class="col-md-6">
                    <label><b>ID Cliente:</b></label>
                    <asp:Label ID="lblClienteID" runat="server"></asp:Label><br />

                    <label><b>Cliente:</b></label>
                    <asp:Label ID="lblCliente" runat="server"></asp:Label><br />

                    <label><b>DNI:</b></label>
                    <asp:Label ID="lblDNI" runat="server"></asp:Label>
                </div>

                <div class="col-md-6">
                    <label><b>ID Empresa:</b></label>
                    <asp:Label ID="lblEmpresaID" runat="server"></asp:Label><br />

                    <label><b>Empresa:</b></label>
                    <asp:Label ID="lblEmpresa" runat="server"></asp:Label>
                </div>
            </div>

            <hr />

            <!-- FILA 2: Estado - Fecha - Total -->
            <div class="row mt-3">

                <div class="col-md-4">
                    <label><b>Estado del Pedido:</b></label>
                    <div class="input-group">
                        <asp:DropDownList ID="ddlEstado" runat="server" CssClass="form-select form-select-sm"></asp:DropDownList>
                        <asp:Button ID="btnCambiarEstado" runat="server" Text="Actualizar"
                            CssClass="btn btn-warning btn-sm" OnClick="btnCambiarEstado_Click"
                            OnClientClick="return confirm('¿Está seguro de cambiar el estado manualmente?');" />
                    </div>
                </div>

                <div class="col-md-4">
                    <label><b>Fecha del Pedido:</b></label>
                    <asp:Label ID="lblFecha" runat="server" CssClass="fw-bold"></asp:Label>
                </div>

                <div class="col-md-4">
                    <label><b>Monto Total:</b></label>
                    <asp:Label ID="lblTotal" runat="server" CssClass="fw-bold fs-5"></asp:Label>
                </div>

            </div>

        </div>
    </div>

    <hr />

    <!-- LÍNEAS DEL PEDIDO -->
    <h4 class="mt-4">Líneas del Pedido</h4>

    <asp:GridView ID="gvLineasPedido" runat="server"
        CssClass="table table-sm table-bordered"
        AutoGenerateColumns="False">
        <Columns>

            <asp:TemplateField HeaderText="ID Prod.">
                <ItemTemplate>
                    <%# Eval("producto.id") %>
                </ItemTemplate>
            </asp:TemplateField>

            <asp:TemplateField HeaderText="Producto">
                <ItemTemplate>
                    <%# Eval("producto.nombre") %>
                </ItemTemplate>
            </asp:TemplateField>

            <asp:BoundField DataField="cantidad" HeaderText="Cantidad" />

            <asp:TemplateField HeaderText="P. Unit.">
                <ItemTemplate>
                    <%# Eval("precio", "{0:C2}") %>
                </ItemTemplate>
            </asp:TemplateField>

            <asp:BoundField DataField="subTotal" HeaderText="Subtotal" DataFormatString="{0:C2}" />

        </Columns>

        <EmptyDataTemplate>
            <div class="alert alert-warning">Este pedido no tiene líneas registradas.</div>
        </EmptyDataTemplate>
    </asp:GridView>

</div>

</asp:Content>
