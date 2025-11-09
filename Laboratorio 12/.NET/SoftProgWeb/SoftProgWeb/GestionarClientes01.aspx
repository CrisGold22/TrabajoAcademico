<%@ Page Title="" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarClientes01.aspx.cs" Inherits="SoftProgWeb.GestionarClientes01" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

    <div class="row mb-3 align-items-center">
        <div class="col-md-5">
            <div class="input-group">
                <asp:TextBox ID="txtBuscarCliente" runat="server" CssClass="form-control" placeholder="Buscar por RUC o Razón Social..."></asp:TextBox>
                <span class="input-group-text"><i class="fas fa-search"></i></span>
            </div>
        </div>

    <div class="col-md-auto ms-auto">
        <asp:Button ID="btnImprimirClientes" runat="server" Text="Generar Reporte" CssClass="btn btn-success fw-bold"/>
    </div>
    </div>

    <div class="card shadow-sm">
        <div class="card-body">
            <asp:GridView ID="gvClientes" runat="server"
                CssClass="table table-hover align-middle"
                AutoGenerateColumns="False"
                EmptyDataText="No se encontraron clientes."
                HeaderStyle-CssClass="table-light"
                OnRowCommand="gvClientes_RowCommand">
                <Columns>
                    <asp:TemplateField><HeaderTemplate><asp:CheckBox ID="chkSeleccionarTodo" runat="server" /></HeaderTemplate><ItemTemplate><asp:CheckBox ID="chkSeleccionar" runat="server" /></ItemTemplate></asp:TemplateField>
                    <asp:BoundField DataField="RUC" HeaderText="RUC" />
                    <asp:BoundField DataField="RazonSocial" HeaderText="Razón Social" />
                    <asp:TemplateField HeaderText="" ItemStyle-CssClass="text-center">
                        <ItemTemplate>
                            <asp:LinkButton ID="btnVerHistorial" runat="server"
                                CssClass="btn btn-sm btn-outline-primary"
                                CommandName="VerHistorial"
                                CommandArgument='<%# Eval("RUC") %>'>
                                <i class="fas fa-history me-1"></i> Ver Historial
                            </asp:LinkButton>
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
<div class="card-footer d-flex justify-content-center">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link" href="#">&laquo;</a>
            </li>
            <li class="page-item active">
                <a class="page-link" href="#">1</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">2</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">3</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">4</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">5</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">&raquo;</a>
            </li>
        </ul>
    </nav>
</div>

    </div>
</asp:Content>
