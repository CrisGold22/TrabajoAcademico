<%@ Page Title="" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarClientes01.aspx.cs" Inherits="SoftProgWeb.GestionarClientes01" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    
    <div class="container-fluid">
        <div class="row mb-3">
            <div class="col-md-12">
                <h2><i class="fas fa-users"></i> Gestión de Clientes</h2>
                     <div class="col-md-4">
                        <asp:Button ID="btnAtenderSolicitudes" runat="server" 
                            Text="Atender Solicitudes Pendientes" 
                            CssClass="btn btn-warning" 
                            OnClick="btnAtenderSolicitudes_Click" 
                            CausesValidation="false" />
                    </div>
                
                <hr />
            </div>
        </div>

        <div class="card mb-3">
            <div class="card-body">
                <div class="row g-3 align-items-center">
                    <div class="col-md-3">
                        <label>Buscar por ID:</label>
                    </div>
                    <div class="col-md-5">
                        <asp:TextBox ID="txtId" runat="server" CssClass="form-control" placeholder="Ingrese ID..."></asp:TextBox>
                    </div>
                    <div class="col-md-4">
                        <asp:Button ID="btnBuscar" runat="server" Text="Buscar" 
                            CssClass="btn btn-primary" OnClick="btnBuscar_Click" />
                        <asp:Button ID="btnLimpiar" runat="server" Text="Limpiar" 
                            CssClass="btn btn-secondary" OnClick="btnLimpiar_Click" CausesValidation="false" />
                    </div>



                </div>

            </div>
        </div>

        <asp:Panel ID="pnlMensaje" runat="server" Visible="false" CssClass="alert alert-danger" role="alert">
            <asp:Literal ID="litMensajeError" runat="server"></asp:Literal>
        </asp:Panel>

        <div class="row">
            <div class="col-md-12">
                <asp:GridView ID="gvClientes" runat="server"
                    CssClass="table table-hover table-striped"
                    AutoGenerateColumns="False"
                    DataKeyNames="id" 
                    AllowPaging="True"
                    PageSize="15"
                    OnPageIndexChanging="gvClientes_PageIndexChanging"
                    OnRowCommand="gvClientes_RowCommand">
                    <Columns>
                        <asp:BoundField DataField="id" HeaderText="ID" ItemStyle-CssClass="fw-bold" />
                        <asp:TemplateField HeaderText="Nombre Completo">
                            <ItemTemplate>
                                <%# Eval("nombre") + " " + Eval("apellidoPaterno") %>
                            </ItemTemplate>
                        </asp:TemplateField>
                        <asp:BoundField DataField="dni" HeaderText="DNI" />
                        <asp:BoundField DataField="categoriaCliente" HeaderText="Categoria" />
                        <asp:BoundField DataField="telefono" HeaderText="Teléfono" />
                        
                        <asp:TemplateField HeaderText="Acciones">
                            <ItemTemplate>
                                <asp:Button ID="btnVerEmpresas" runat="server" 
                                    Text="Ver Empresas" 
                                    CssClass="btn btn-info btn-sm"
                                    CommandName="VerEmpresas"
                                    CommandArgument='<%# Eval("id") %>' />
                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                    <EmptyDataTemplate>
                        <div class="alert alert-info">
                            No se encontraron clientes.
                        </div>
                    </EmptyDataTemplate>
                    <PagerSettings Mode="NumericFirstLast" Position="Bottom" PageButtonCount="5"
                        FirstPageText="&laquo; Primero" LastPageText="Último &raquo;"
                        NextPageText="Siguiente &rsaquo;" PreviousPageText="&lsaquo; Anterior" />
                    <PagerStyle CssClass="pagination-container" HorizontalAlign="Center" />
                </asp:GridView>
            </div>
        </div>
    </div>

</asp:Content>