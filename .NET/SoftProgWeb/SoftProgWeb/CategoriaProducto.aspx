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
                                <%--<img src='<%# ResolveUrl("~/images/" + Eval("ImageUrl")) %>' class="card-img-top" alt="<%# Eval("Name") %>">--%>
                                <%--<img src="<%# Eval("ImageUrl") %>" class="card-img-top" alt="<%# Eval("Name") %>">--%>
                                <div class="card-body">
                                    <h5 class="card-title"><%# Eval("Name") %></h5>
                                    <p class="card-text"><%# Eval("Description") %></p>
                                    <p class="card-text"><strong>A partir de S/ <%# Eval("Price") %></strong></p>
                                    <a href="#" class="btn btn-primary button-add-1">Agregar</a>
                                </div>
                            </div>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>
            </div>
        </div>
    </div>

</asp:Content>