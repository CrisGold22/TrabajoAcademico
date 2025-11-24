<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="Index.aspx.cs" Inherits="SoftProgWeb.Index" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container-fluid px-4">
        <!-- Carrusel de Imágenes -->
        <div class="row mt-4">
            <div class="col-12">
                <div id="carouselPromociones" class="carousel slide carousel-container" data-bs-ride="carousel">
                    <div class="carousel-inner" >
                        <div class="carousel-item active">
                            <img src="images/portada1.png" class="d-block w-100" alt="Rebajas Navideñas" >
                        </div>
                        <div class="carousel-item">
                            <img src="images/portada2.png" class="d-block w-100" alt="Promoción 2" >
                        </div>
                        <div class="carousel-item">
                            <img  src="images/portada3.png" class="d-block w-100" alt="Promoción 3">
                        </div>
                    </div>
                    
                    <!-- Controles del carrusel -->
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselPromociones" data-bs-slide="prev">
                        <i class="fa-solid fa-chevron-left"></i>
                        <span class="visually-hidden">Anterior</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselPromociones" data-bs-slide="next">
                        <i class="fa-solid fa-chevron-right"></i>
                        <span class="visually-hidden">Siguiente</span>
                    </button>
                </div>
            </div>
        </div>

        <!-- Sección de Categorías -->
         <div class="content_2 my-5">
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

    <script>
        function irACategoria(categoria) {
            // Redirigir a la página de productos filtrada por categoría
            //window.location.href = 'Productos.aspx?categoria=' + categoria;
        }

        // Configurar el carrusel para que avance automáticamente cada 5 segundos
        document.addEventListener('DOMContentLoaded', function() {
            var carousel = new bootstrap.Carousel(document.getElementById('carouselPromociones'), {
                interval: 5000,
                wrap: true
            });
        });
    </script>
</asp:Content>
