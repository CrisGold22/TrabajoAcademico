<%@ Page Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="CategoriaProducto.aspx.cs" Inherits="SoftProgWeb.CategoriaProducto" %>

<%@ Register TagPrefix="asp" Namespace="System.Web.UI.WebControls" Assembly="System.Web" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
    Productos
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">

    <div class="container my-5">
        <div class="row">

            <!-- =======================
                 SIDEBAR DE FILTROS
            ======================== -->
            <div class="col-md-3" cajita3>
                <div class="card p-3 mb-4">
                    <h5 class="mb-3">Filtros</h5>

                    <!-- FILTRO MARCA -->
                    <h6>Marca</h6>
                    <asp:CheckBoxList ID="chkMarca" runat="server" CssClass="mb-3"></asp:CheckBoxList>

                    <!-- FILTRO PRECIO -->
                    <h6>Precio</h6>
                    <asp:TextBox ID="txtPrecioMin" runat="server" CssClass="form-control mb-2" Placeholder="Precio mínimo"></asp:TextBox>
                    <asp:TextBox ID="txtPrecioMax" runat="server" CssClass="form-control mb-3" Placeholder="Precio máximo"></asp:TextBox>


                    <!-- BOTÓN -->
                    <asp:Button
                        ID="btnFiltrar"
                        runat="server"
                        CssClass="btn btn-primary button-add-1"
                        Text="Aplicar filtros"
                        OnClick="btnFiltrar_Click" />
                </div>
            </div>

            <!-- =======================
                 LISTA DE PRODUCTOS
            ======================== -->
            <div class="col-md-9">
                <asp:Label ID="CategoryLabel" runat="server" CssClass="text-lg-start mb-3" />

                <div class="row">
                    <asp:Repeater ID="ProductRepeater" runat="server">
                        <ItemTemplate>
                            <div class="col-md-4 mb-4">
                                <div class="card h-100">
                                    <img src='<%# ResolveUrl("~/images/" + Eval("imagenUrl")) %>' class="card-img-top" />
                                    <div class="card-body">
                                        <h5 class="card-title"><%# Eval("nombre") %></h5>
                                        <p class="card-text"><%# Eval("descripcion") %></p>
                                        <p><strong>S/ <%# Eval("precioRegular") %></strong></p>

                                        <asp:Button
                                            ID="btnAgregarCarrito"
                                            runat="server"
                                            CssClass="btn btn-primary button-add-1"
                                            Text="Agregar"
                                            CommandArgument='<%# Eval("id") %>'
                                            OnClick="btnAgregarCarrito_Click" />
                                    </div>
                                </div>
                            </div>
                        </ItemTemplate>
                    </asp:Repeater>
                </div>

            </div>

        </div>
    </div>

</asp:Content>
