<%@ Page Title="Gestión de Productos" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarProductos01.aspx.cs" Inherits="SoftProgWeb.GestionarProductos01" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    
    <div class="container-fluid">
        <div class="row mb-3">
            <div class="col-md-6">
                <h2><i class="fas fa-boxes"></i> Gestión de Productos</h2>
            </div>
            <div class="col-md-6 text-end">
                <asp:Button ID="btnNuevo" runat="server" Text="Nuevo Producto" 
                    CssClass="btn btn-success" OnClick="btnNuevo_Click" />
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <asp:GridView ID="gvProductos" runat="server"
                    CssClass="table table-hover table-striped"
                    AutoGenerateColumns="False"
                    DataKeyNames="id" 
                    AllowPaging="True"
                    PageSize="15"
                    OnPageIndexChanging="gvProductos_PageIndexChanging"
                    OnRowCommand="gvProductos_RowCommand">
                    <Columns>
                        <asp:BoundField DataField="id" HeaderText="ID" ItemStyle-CssClass="fw-bold" />
                        <asp:BoundField DataField="nombre" HeaderText="Nombre del Producto" />
                        <asp:TemplateField HeaderText="Categoría">
                            <ItemTemplate>
                                <%# Eval("categoria.nombre") %>
                            </ItemTemplate>
                        </asp:TemplateField>
                        
                        <asp:BoundField DataField="precio" HeaderText="Precio" DataFormatString="{0:C2}" />
                        <asp:BoundField DataField="stock" HeaderText="Stock" />

                        <asp:TemplateField HeaderText="Estado" ItemStyle-CssClass="text-center">
                            <ItemTemplate>
                                <span class='badge <%# (bool)Eval("activo") ? "bg-success" : "bg-danger" %>'>
                                    <%# (bool)Eval("activo") ? "Activo" : "Inactivo" %>
                                </span>
                            </ItemTemplate>
                        </asp:TemplateField>
                        
                        <asp:TemplateField HeaderText="Acciones">
                            <ItemTemplate>
                                <asp:Button ID="btnModificar" runat="server" 
                                    Text="Modificar" 
                                    CssClass="btn btn-primary btn-sm"
                                    CommandName="Modificar"
                                    CommandArgument='<%# Eval("id") %>' />
                                <asp:Button ID="btnEliminar" runat="server" 
                                    Text="Eliminar" 
                                    CssClass="btn btn-danger btn-sm"
                                    CommandName="Eliminar"
                                    CommandArgument='<%# Eval("id") %>'
                                    OnClientClick="return confirm('¿Está seguro de que desea eliminar este producto?');" />
                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                    <EmptyDataTemplate>
                        <div class="alert alert-info">
                            No se encontraron productos registrados.
                        </div>
                    </EmptyDataTemplate>
                </asp:GridView>
            </div>
        </div>
    </div>

</asp:Content>
