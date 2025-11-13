<%@ Page Title="Listado de Pedidos" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarPedidos01.aspx.cs" Inherits="SoftProgWeb.GestionarPedidos01" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    
    <div class="container-fluid">
        <div class="row mb-3">
            <div class="col-md-12">
                <h2><i class="fas fa-file-invoice-dollar"></i> Gestión de Pedidos</h2>
                <hr />
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <asp:GridView ID="gvPedidos" runat="server"
                    CssClass="table table-hover table-striped"
                    AutoGenerateColumns="False"
                    DataKeyNames="id" 
                    AllowPaging="True"
                    PageSize="20"
                    OnPageIndexChanging="gvPedidos_PageIndexChanging"
                    OnRowCommand="gvPedidos_RowCommand">
                    <Columns>
                        <asp:BoundField DataField="id" HeaderText="N° Pedido" ItemStyle-CssClass="fw-bold" />
                        
                        <asp:TemplateField HeaderText="Cliente">
                            <ItemTemplate>
                                <%# Eval("cliente.nombre") + " " + Eval("cliente.apellidoPaterno") %>
                            </ItemTemplate>
                        </asp:TemplateField>
                        
                     
                        <asp:BoundField DataField="fechaCreacion" HeaderText="Fecha de Creación" DataFormatString="{0:dd/MM/yyyy}" />
                        
                        
                        <asp:BoundField DataField="totalFinal" HeaderText="Monto Total" DataFormatString="{0:C2}" />

       
                        
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
                                    OnClientClick="return confirm('¿Está seguro de que desea eliminar este pedido? Esta acción es irreversible.');" />

                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                    <EmptyDataTemplate>
                        <div class="alert alert-info">
                            No se encontraron pedidos registrados.
                        </div>
                    </EmptyDataTemplate>
                </asp:GridView>
            </div>
        </div>
    </div>

</asp:Content>