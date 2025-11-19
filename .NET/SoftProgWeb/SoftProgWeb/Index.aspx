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
                            <img src="images/portada3.png" class="d-block w-100" alt="Promoción 3">
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
        <div class="container my-5">
              <h2 class="text-center mb-4">¡Lo Mejor de la Semana!</h2>
              <div class="row">
                <!-- Producto 1 -->
                <div class="col-md-3 mb-4">
                  <div class="card">
                    <img src="images/arroz_faraon.png" class="card-img-top" alt="Arroz Extra Añejo Faraon">
                    <div class="card-body">
                      <h5 class="card-title">Arroz Extra Añejo Faraon Saco 50 Kg</h5>
                      <p class="card-text">Por RedCom</p>
                      <p class="card-text">Unidad: Saco 50Kg</p>
                      <p class="card-text"><strong>A partir de S/ 209</strong></p>
                      <asp:Button ID="btnAgregarProducto" 
                        CssClass="btn btn-primary button-add-1" 
                        runat="server" 
                        Text="Agregar" 
                        OnClick="btnAgregarProducto_Click"
                        CommandArgument="101" />

                      <%--<a href="#" class="btn btn-primary button-add-1">Agregar</a>--%>

                    </div>
                  </div>
                </div>
                <!-- Producto 2 -->
                <div class="col-md-3 mb-4">
                  <div class="card">
                    <img src="images/huevos_rosados_180un.png" class="card-img-top" alt="Huevo Rosado ARO">
                    <div class="card-body">
                      <h5 class="card-title">Huevo Rosado ARO Bandeja 180un</h5>
                      <p class="card-text">Por Redcom</p>
                      <p class="card-text">Unidad: Bandeja 180un</p>
                      <p class="card-text"><strong>A partir de S/ 58</strong></p>
                      <a href="#" class="btn btn-primary button-add-1">Agregar</a>
                    </div>
                  </div>
                </div>
                <!-- Producto 3 -->
                <div class="col-md-3 mb-4">
                  <div class="card">
                    <img src="images/aceite_aro_fritura_intensa_18L.png" class="card-img-top" alt="Aceite Fritura ARO">
                    <div class="card-body">
                      <h5 class="card-title">Aceite Fritura Intensa ARO Balde 18L</h5>
                      <p class="card-text">Por Redcom</p>
                      <p class="card-text">Unidad: Balde 18L</p>
                      <p class="card-text"><strong>A partir de S/ 130</strong></p>
                      <a href="#" class="btn btn-primary button-add-1">Agregar</a>
                    </div>
                  </div>
                </div>
                <!-- Producto 4 -->
                <div class="col-md-3 mb-4">
                  <div class="card">
                    <img src="images/dog_chow_21kg.png" class="card-img-top" alt="Alimento para Perro DOG CHOW">
                    <div class="card-body">
                      <h5 class="card-title">Alimento para Perro DOG CHOW</h5>
                      <p class="card-text">Por Redcom</p>
                      <p class="card-text">Unidad: Bolsa 21Kg</p>
                      <p class="card-text"><strong>A partir de S/ 164</strong></p>
                      <a href="#" class="btn btn-primary button-add-1">Agregar</a>
                    </div>
                  </div>
                </div>
                  <!-- Producto 5 -->
                  <div class="col-md-3 mb-4">
                      <div class="card">
                        <img src="images/cerveza_pilsen_6bot.png" class="card-img-top" alt="Alimento para Perro DOG CHOW">
                        <div class="card-body">
                          <h5 class="card-title">Cerveza PILSEN Callao Botella 305ml Paquete 6un</h5>
                          <p class="card-text">Por Redcom</p>
                          <p class="card-text">Unidad: Paquete 6un</p>
                          <p class="card-text"><strong>A partir de S/ 20</strong></p>
                          <a href="#" class="btn btn-primary button-add-1">Agregar</a>
                        </div>
                      </div>
                    </div>
                        <!-- Producto 6 -->
                <div class="col-md-3 mb-4">
                    <div class="card">
                    <img src="images/leche_reconstruida_gloria_plancha_24_latas_390gr.png" class="card-img-top" alt="Alimento para Perro DOG CHOW">
                    <div class="card-body">
                        <h5 class="card-title">Leche Reconstituida Entera GLORIA Plancha 24 Lat...</h5>
                        <p class="card-text">Por Redcom</p>
                        <p class="card-text">Unidad: Plancha 24 Latas 390g c/u</p>
                        <p class="card-text"><strong>A partir de S/ 87.12</strong></p>
                        <a href="#" class="btn btn-primary button-add-1">Agregar</a>
                    </div>
                    </div>
                </div>
                          <!-- Producto 7 -->
                <div class="col-md-3 mb-4">
                    <div class="card">
                    <img src="images/leche-light-gloria-plancha-24-latas-390g-cu.png" class="card-img-top" alt="Alimento para Perro DOG CHOW">
                    <div class="card-body">
                        <h5 class="card-title">Leche light gloria plancha 24 latas 390g c/u</h5>
                        <p class="card-text">Por Redcom</p>
                        <p class="card-text">Unidad: Plancha 24 Latas 390g c/u</p>
                        <p class="card-text"><strong>A partir de S/ 90.96</strong></p>
                        <a href="#" class="btn btn-primary button-add-1">Agregar</a>
                    </div>
                    </div>
                </div>
                <!-- Producto 8 -->
                <div class="col-md-3 mb-4">
                    <div class="card">
                    <img src="images/agua-mineral-san-mateo-sin-gas-paquete-15-botellas-600ml-cu.png" class="card-img-top" alt="Alimento para Perro DOG CHOW">
                    <div class="card-body">
                        <h5 class="card-title">Agua Mineral SAN MATEO sin Gas Paquete 15 Botellas</h5>
                        <p class="card-text">Por Redcom</p>
                        <p class="card-text">Unidad: Paquete 15 Botellas 600ml...</p>
                        <p class="card-text"><strong>A partir de S/ 16.50</strong></p>
                        <a href="#" class="btn btn-primary button-add-1">Agregar</a>
                    </div>
                    </div>
                </div>

                <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger"></asp:Label>
                <asp:Label ID="lblMiniCarrito" runat="server" CssClass="mini-cart"></asp:Label>


              </div>
              <div class="text-center">
                <a href="#" class="btn btn-link">Ver todo</a>
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
