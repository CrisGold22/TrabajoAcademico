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
                            <img src="images/a2b6d9ac85b783efbd6e9681351bddc2860259ba.png" class="d-block w-100" alt="Rebajas Navideñas" >
                        </div>
                        <div class="carousel-item">
                            <img src="images/a2b6d9ac85b783efbd6e9681351bddc2860259ba.png" class="d-block w-100" alt="Promoción 2" >
                        </div>
                        <div class="carousel-item">
                            <img src="images/a2b6d9ac85b783efbd6e9681351bddc2860259ba.png" class="d-block w-100" alt="Promoción 3">
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
        <div class="row">
            <div class="col-12">
                <div class="categories-section">
                    <div class="categories-header">
                        <i class="fa-solid fa-bars"></i>
                        <h3>Categorías</h3>
                    </div>

                    <div class="category-grid">
                        <!-- Abarrotes Básicos -->
                        <div class="category-card" onclick="irACategoria('abarrotes')">
                            <i class="fa-solid fa-carrot"></i>
                            <h5>Abarrotes Básicos</h5>
                        </div>

                        <!-- Conservas y Enlatados -->
                        <div class="category-card" onclick="irACategoria('conservas')">
                            <i class="fa-solid fa-jar"></i>
                            <h5>Conservas y Enlatados</h5>
                        </div>

                        <!-- Lácteos -->
                        <div class="category-card" onclick="irACategoria('lacteos')">
                            <i class="fa-solid fa-cheese"></i>
                            <h5>Lácteos</h5>
                        </div>

                        <!-- Bebidas -->
                        <div class="category-card" onclick="irACategoria('bebidas')">
                            <i class="fa-solid fa-bottle-water"></i>
                            <h5>Bebidas</h5>
                        </div>

                        <!-- Aperitivos -->
                        <div class="category-card" onclick="irACategoria('aperitivos')">
                            <i class="fa-solid fa-cookie-bite"></i>
                            <h5>Aperitivos</h5>
                        </div>

                        <!-- Otros Productos -->
                        <div class="category-card" onclick="irACategoria('otros')">
                            <i class="fa-solid fa-utensils"></i>
                            <h5>Otros Productos</h5>
                        </div>
                    </div>
                </div>
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
