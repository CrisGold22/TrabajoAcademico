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
            <asp:Button ID="btnVerPendientes" runat="server" Text="Bandeja de Pendientes" 
                CssClass="btn btn-warning" OnClick="btnVerPendientes_Click" />
    </div>

</div>
    <div class="card shadow-sm">
        <div class="card-body">
            <asp:GridView ID="gvEmpresas" runat="server"
                CssClass="table table-hover table-striped"
                AutoGenerateColumns="False"
                DataKeyNames="id" 
                AllowPaging="True"
                PageSize="15"
                OnPageIndexChanging="gvEmpresas_PageIndexChanging">
                <Columns>
                    <asp:BoundField DataField="razonSocial" HeaderText="Razón Social" />
                    <asp:BoundField DataField="RUC" HeaderText="RUC" />
                    <asp:BoundField DataField="direccionFiscal" HeaderText="Dirección" />
                    <asp:BoundField DataField="email" HeaderText="Email" />
                    <asp:BoundField DataField="telefono" HeaderText="Teléfono" />
      
        
                </Columns>
                <EmptyDataTemplate>
                    <div class="alert alert-info">
                        No se encontraron empresas registradas.
                    </div>
                </EmptyDataTemplate>
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
