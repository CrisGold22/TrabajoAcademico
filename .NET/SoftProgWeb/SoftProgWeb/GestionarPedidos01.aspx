<%@ Page Title="Listado de Pedidos" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarPedidos01.aspx.cs" Inherits="SoftProgWeb.GestionarPedidos01" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    
    <div class="container-fluid">
        <div class="row mb-3">
            <div class="col-md-12">
                <h2><i class="fas fa-file-invoice-dollar"></i> Gestión de Pedidos</h2>
                <hr />
            </div>
        </div>

    <%--<div class="row mb-3">
    <div class="col-md-6">
        <asp:Button ID="btnGenerarReporte" runat="server"
            Text="Generar Reporte"
            CssClass="btn btn-success" />
    </div>--%>
</div>

<div class="card mb-3">
    <div class="card-header bg-light">
        <i class="fas fa-search"></i> <strong>Filtros de Búsqueda</strong>
    </div>
    <div class="card-body">
        <div class="row g-3">
            <div class="col-md-4">
                <label class="form-label">ID del Cliente:</label>
                <asp:TextBox ID="txtIdCliente" runat="server" CssClass="form-control" TextMode="Number" placeholder="Ej: 1"></asp:TextBox>
            </div>

            <div class="col-md-3">
                <label class="form-label">Fecha Inicio:</label>
                <asp:TextBox ID="txtFechaInicio" runat="server" CssClass="form-control" TextMode="Date"></asp:TextBox>
            </div>
            <div class="col-md-3">
                <label class="form-label">Fecha Fin:</label>
                <asp:TextBox ID="txtFechaFin" runat="server" CssClass="form-control" TextMode="Date"></asp:TextBox>
            </div>

            <div class="col-md-2 d-flex align-items-end">
                <asp:Button ID="btnFiltrar" runat="server" Text="Filtrar" 
                    CssClass="btn btn-primary w-100" OnClick="btnFiltrar_Click" />
            </div>
            <div class="col-md-12 text-end">
                <asp:LinkButton ID="lnkLimpiar" runat="server" CssClass="text-decoration-none" OnClick="lnkLimpiar_Click">
                    <i class="fas fa-sync"></i> Restablecer Filtros
                </asp:LinkButton>
            </div>
        </div>
    </div>
</div>
<asp:Panel ID="pnlMensaje" runat="server" Visible="false" CssClass="alert alert-danger alert-dismissible fade show" role="alert">
    <asp:Literal ID="litMensajeError" runat="server"></asp:Literal>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</asp:Panel>

        <div class="row">
            <div class="col-md-12">
<asp:GridView ID="gvPedidos" runat="server"
    CssClass="table table-hover table-striped"
    AutoGenerateColumns="False"
    DataKeyNames="id" 
    AllowPaging="True"
    PageSize="15"
    OnPageIndexChanging="gvPedidos_PageIndexChanging"
    OnRowCommand="gvPedidos_RowCommand">
    <Columns>
        <asp:BoundField DataField="id" HeaderText="N° Pedido" ItemStyle-CssClass="fw-bold" />
        
        <asp:TemplateField HeaderText="Empresa (Razón Social)">
            <ItemTemplate>
                <%# Eval("empresa.razonSocial") %>
            </ItemTemplate>
        </asp:TemplateField>
        
        <asp:BoundField DataField="fechaCreacion" HeaderText="Fecha de Creación" 
            DataFormatString="{0:dd/MM/yyyy}" NullDisplayText="-" />
        
        <asp:BoundField DataField="totalFinal" HeaderText="Monto Total" DataFormatString="{0:C2}" />

        <asp:TemplateField HeaderText="    Estado" ItemStyle-CssClass="text-center">
            <ItemTemplate>
                <span class="badge bg-info text-dark"><%# Eval("estadoString") %></span>
            </ItemTemplate>
        </asp:TemplateField>

        
        <asp:TemplateField HeaderText="Acciones">
            <ItemTemplate>
                <asp:Button ID="btnVerDetalle" runat="server" 
                    Text="Ver Detalle" 
                    CssClass="btn btn-primary btn-sm"
                    CommandName="VerDetalle"
                    CommandArgument='<%# Eval("id") %>' />
                <asp:Button ID="btnEliminar" runat="server" 
                    Text="Eliminar" 
                    CssClass="btn btn-danger btn-sm"
                    CommandName="Eliminar"
                    CommandArgument='<%# Eval("id") %>'
                    OnClientClick="return confirm('¿Está seguro de que desea eliminar este pedido?');" />
            </ItemTemplate>
        </asp:TemplateField>
    </Columns>
    <EmptyDataTemplate>
        <div class="alert alert-info">
            No se encontraron pedidos registrados.
        </div>
    </EmptyDataTemplate>
    
    <PagerSettings Mode="NumericFirstLast" Position="Bottom" PageButtonCount="5"
        FirstPageText="&laquo; Primero" LastPageText="Último &raquo;"
        NextPageText="Siguiente &rsaquo;" PreviousPageText="&lsaquo; Anterior" />
    <PagerStyle CssClass="pagination-container" HorizontalAlign="Center" />
    
</asp:GridView>
            </div>
        </div>
   

</asp:Content>