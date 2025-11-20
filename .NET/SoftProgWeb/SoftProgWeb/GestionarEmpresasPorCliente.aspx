<%@ Page Title="" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarEmpresasPorCliente.aspx.cs" Inherits="SoftProgWeb.GestionarEmpresasPorCliente" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container-fluid">
        <div class="row mb-3">
            <div class="col-md-10">
                <h2>Cliente: <asp:Literal ID="litNombreCliente" runat="server"></asp:Literal></h2>
                <h4>    Usuario: <asp:Literal ID="LitUsuario" runat="server"></asp:Literal></h4>
                <%--<h4>    Email: <asp:Literal ID="litEmail" runat="server"></asp:Literal></h4>--%>
                <h5>    Genero: <asp:Literal ID="litGenero" runat="server"></asp:Literal></h5>
                <h5>    Telefono: <asp:Literal ID="litTelefono" runat="server"></asp:Literal></h5>
                <h5>    Categoria: <asp:Literal ID="litCatCli" runat="server"></asp:Literal></h5>
                <h5>    Linea de Credito: <asp:Literal ID="LitCredito" runat="server"></asp:Literal></h5>
                <h5>    Nro Pedidos Historicos: <asp:Literal ID="LitPedidosHist" runat="server"></asp:Literal></h5>
                <h5>    Nro Pedidos Mensuales Prom.: <asp:Literal ID="LitPedidosMen" runat="server"></asp:Literal></h5>

                <h2><i class="fas fa-building"></i> Empresas Asociadas:</h2>
            </div>
            <div class="col-md-2 text-end">
                <asp:Button ID="btnVolver" runat="server" Text="Volver al Listado" 
                    CssClass="btn btn-secondary" OnClick="btnVolver_Click" CausesValidation="false" />
            </div>
        </div>

        <asp:Panel ID="pnlMensaje" runat="server" Visible="false" CssClass="alert alert-danger" role="alert">
            <asp:Literal ID="litMensajeError" runat="server"></asp:Literal>
        </asp:Panel>
        
        <div class="row">
            <div class="col-md-12">
                <asp:GridView ID="gvEmpresasCliente" runat="server"
                    CssClass="table table-hover"
                    AutoGenerateColumns="False"
                    DataKeyNames="id">
                    <Columns>
                        <asp:BoundField DataField="id" HeaderText="ID Empresa" />
                        <asp:BoundField DataField="razonSocial" HeaderText="Razón Social" />
                        <asp:BoundField DataField="ruc" HeaderText="RUC" />
                        <asp:BoundField DataField="codigoPostal" HeaderText="Codigo Postal" />
                        <asp:BoundField DataField="direccionFiscal" HeaderText="Direccion" />
                        <asp:BoundField DataField="distrito" HeaderText="Distrito" />
                    </Columns>
                    <EmptyDataTemplate>
                        <div class="alert alert-info">
                            El cliente no tiene empresas asociadas.
                        </div>
                    </EmptyDataTemplate>
                </asp:GridView>
            </div>
        </div>
    </div>
</asp:Content>