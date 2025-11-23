<%@ Page Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="CategoriaProducto.aspx.cs" Inherits="SoftProgWeb.CategoriaProducto" %>
<%@ Register TagPrefix="asp" Namespace="System.Web.UI.WebControls" Assembly="System.Web" %>


<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container my-5">
        <asp:Label ID="CategoryLabel" runat="server" CssClass="text-lg-start mb-5"></asp:Label>

        <!-- Barra lateral de filtros y categorías (si la necesitas) -->

        <div class="content my-5">
            <div class="row">
                <asp:Repeater ID="ProductRepeater" runat="server">
                    <ItemTemplate>
                        <div class="col-md-3 mb-4">
                            <div class="card">
                                <img src='<%# ResolveUrl("~/images/" + Eval("ImagenUrl")) %>' class="card-img-top" alt="<%# Eval("nombre") %>">
                                <%--<img src="<%# Eval("") %>" class="card-img-top" alt="<%# Eval("Name") %>">--%>
                                <div class="card-body">
                                    <h5 class="card-title"><%# Eval("nombre") %></h5>
                                    <p class="card-text"><%# Eval("descripcion") %></p>
                                    <p class="card-text"><strong>A partir de S/ <%# Eval("precioRegular") %></strong></p>
                                    
                                    <asp:Button 
                                                ID="btnAgregarCarrito" 
                                                runat="server"  
                                                Text="Agregar" 
                                                CssClass="btn btn-primary button-add-1" 
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

</asp:Content>
<%--CommandArgument="<%# Eval("id") %>"--%> 
<%--<a href="#" class="btn btn-primary button-add-1">Agregar</a>--%>