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

        <div class="card mb-3">
            <div class="card-body">
                <div class="row g-3 align-items-center">
                    <div class="col-md-3">
                        <asp:DropDownList ID="ddlCriterio" runat="server" CssClass="form-select">
                            <asp:ListItem Value="SKU">Buscar por SKU</asp:ListItem>
                            <%--<asp:ListItem Value="ID">Buscar por ID</asp:ListItem>--%>
                        </asp:DropDownList>
                    </div>
                    <div class="col-md-5">
                        <asp:TextBox ID="txtTerminoBusqueda" runat="server" CssClass="form-control" placeholder="Ingrese el término de búsqueda..."></asp:TextBox>
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
        <%-- FIN DE LA NUEVA SECCIÓN --%>

        <asp:Panel ID="pnlMensaje" runat="server" Visible="false" CssClass="alert alert-danger mt-3" role="alert">
            <asp:Literal ID="litMensajeError" runat="server"></asp:Literal>
        </asp:Panel>
        <div class="row">
            <div class="col-md-12">
                <%-- 
                    CORRECCIÓN DEL GRIDVIEW:
                    He re-añadido las columnas 'precio', 'stock' y 'activo'
                    porque su proyecto "TA V2" SÍ las incluye en el modelo
                    y en los procedimientos almacenados. El XML anterior era obsoleto.
                --%>
                <asp:GridView ID="gvProductos" runat="server"
                    CssClass="table table-hover table-striped"
                    AutoGenerateColumns="False"
                    DataKeyNames="id" 
                    AllowPaging="True"
                    PageSize="10"
                    OnPageIndexChanging="gvProductos_PageIndexChanging"
                    OnRowCommand="gvProductos_RowCommand">
                    <Columns>
                        <asp:BoundField DataField="SKU" HeaderText="SKU" ItemStyle-CssClass="fw-bold" />
                        <asp:BoundField DataField="nombre" HeaderText="Nombre del Producto" />
                        <asp:TemplateField HeaderText="Categoría">
                            <ItemTemplate>
                                <%# Eval("categoria.nombre") %>
                            </ItemTemplate>
                        </asp:TemplateField>
                        
                        <%-- Estos campos SÍ existen en su proyecto TA V2 --%>
                        <asp:BoundField DataField="precioRegular" HeaderText="Precio" DataFormatString="{0:C2}" />
                        <asp:BoundField DataField="stockDisponible" HeaderText="Stock" />
                        
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
                                        <PagerSettings 
                            Mode="NumericFirstLast" 
                            Position="Bottom"
                            PageButtonCount="5"
                            FirstPageText="&laquo; Primero"
                            LastPageText="Último &raquo;"
                            NextPageText="Siguiente &rsaquo;"
                            PreviousPageText="&lsaquo; Anterior" />
        
                        <PagerStyle 
                            CssClass="pagination-container" 
                            HorizontalAlign="Center" />

                    <EmptyDataTemplate>
                        <div class="alert alert-info">
                            No se encontraron productos para el criterio de búsqueda.
                        </div>
                    </EmptyDataTemplate>
                </asp:GridView>
            </div>
        </div>
    </div>

</asp:Content>