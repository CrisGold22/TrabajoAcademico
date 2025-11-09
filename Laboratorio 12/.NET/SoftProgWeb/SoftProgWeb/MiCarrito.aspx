<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="MiCarrito.aspx.cs" Inherits="SoftProgWeb.MiCarrito" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
    <link href="Content/botones.css" rel="stylesheet" />
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container-fluid">
        <!-- Encabezado con pasos -->
        <div class="row mb-4">
            <div class="col-12">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h2 class="mb-0">
                        <i class="fa-solid fa-cart-shopping me-2"></i>Mi Carrito 
                        <span class="text-muted" style="font-size: 0.7em;">(x unidades)</span>
                    </h2>
                    <a href='<%= ResolveUrl("~/Index.aspx") %>' class="btn btn-outline-secondary">
                        <i class="fa-solid fa-arrow-left me-2"></i>Seguir comprando
                    </a>
                </div>
                
                <!-- Indicador de pasos -->
                <div class="step-indicator">
                    <div class="text-center">
                        <div class="step-circle active">1</div>
                        <div class="step-label active fw-bold">Carrito</div>
                    </div>
    
                    <div class="step-line"></div>
    
                    <div class="text-center">
                        <div class="step-circle inactive">2</div>
                        <div class="step-label inactive">Entrega</div>
                    </div>
    
                    <div class="step-line"></div>
    
                    <div class="text-center">
                        <div class="step-circle inactive">3</div>
                        <div class="step-label inactive">Pago</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <!-- Lista de productos -->
            <div class="col-lg-8 col-md-12 mb-4">
                <div class="table-responsive">
                    <table class="table table-hover align-middle">
                        <thead class="table-light">
                            <tr>
                                <th>PRODUCTO</th>
                                <th class="text-center">PRECIO</th>
                                <th class="text-center">CANTIDAD</th>
                                <th class="text-center">UNIDAD</th>
                                <th class="text-center">TOTAL</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Producto 1: Sprite -->
                            <tr>
                                <td>
                                    <div class="d-flex align-items-center">
                                        <img src="images/sprite.png" alt="Sprite" class="me-3" style="width: 80px; height: 80px; object-fit: contain;">
                                        <div>
                                            <div class="fw-bold">Gaseosa SPRITE</div>
                                            <div class="text-muted small">Lima limón</div>
                                            <div class="text-muted small">Paquete 12 Botellas</div>
                                        </div>
                                    </div>
                                </td>
                                <td class="text-center">S/ 35.50</td>
                                <td>
                                    <div class="d-flex justify-content-center align-items-center">
                                        <button type="button" class="btn btn-sm btn-dark rounded-circle btn-minus" style="width: 30px; height: 30px; padding: 0;">
                                            <i class="fa-solid fa-minus" style="font-size: 0.7em;"></i>
                                        </button>
                                        <span class="mx-3 fw-bold cantidad">2</span>
                                        <button type="button" class="btn btn-sm btn-dark rounded-circle btn-plus" style="width: 30px; height: 30px; padding: 0;">
                                            <i class="fa-solid fa-plus" style="font-size: 0.7em;"></i>
                                        </button>
                                    </div>
                                </td>
                                <td class="text-center">Paquete</td>
                                <td class="text-center text-danger fw-bold">S/ 71.00</td>
                                <td class="text-center">
                                    <button class="btn btn-icone text-danger">
                                        <i class="fa-solid fa-circle-xmark" style="font-size: 1.5em;"></i>
                                    </button>
                                </td>
                            </tr>

                            <!-- Producto 2: Fanta -->
                            <tr>
                                <td>
                                    <div class="d-flex align-items-center">
                                        <img src="images/fanta.png" alt="Fanta" class="me-3" style="width: 80px; height: 80px; object-fit: contain;">
                                        <div>
                                            <div class="fw-bold">Gaseosa FANTA</div>
                                            <div class="text-muted small">Naranja</div>
                                            <div class="text-muted small">Paquete 12 Botellas</div>
                                        </div>
                                    </div>
                                </td>
                                <td class="text-center">S/ 32.50</td>
                                <td>
                                    <div class="d-flex justify-content-center align-items-center">
                                        <button type="button" class="btn btn-sm btn-dark rounded-circle btn-minus" style="width: 30px; height: 30px; padding: 0;">
                                            <i class="fa-solid fa-minus" style="font-size: 0.7em;"></i>
                                        </button>
                                        <span class="mx-3 fw-bold cantidad">1</span>
                                        <button type="button" class="btn btn-sm btn-dark rounded-circle btn-plus" style="width: 30px; height: 30px; padding: 0;">
                                            <i class="fa-solid fa-plus" style="font-size: 0.7em;"></i>
                                        </button>
                                    </div>
                                </td>
                                <td class="text-center">Paquete</td>
                                <td class="text-center text-danger fw-bold">S/ 32.50</td>
                                <td class="text-center">
                                    <button class="btn btn-icone text-danger">
                                        <i class="fa-solid fa-circle-xmark" style="font-size: 1.5em;"></i>
                                    </button>
                                </td>
                            </tr>

                            <!-- Producto 3: Placeholder -->
                            <tr class="opacity-50">
                                <td>
                                    <div class="d-flex align-items-center">
                                        <img src="images/placeholder.png" alt="Producto" class="me-3" style="width: 80px; height: 80px; object-fit: contain; filter: grayscale(100%);">
                                        <div>
                                            <div class="fw-bold">Producto</div>
                                            <div class="text-muted small">Paquete XX</div>
                                            <div class="text-muted small">Botellas</div>
                                        </div>
                                    </div>
                                </td>
                                <td class="text-center">S/ XX.XX</td>
                                <td>
                                    <div class="d-flex justify-content-center align-items-center">
                                        <button type="button" class="btn btn-sm btn-dark rounded-circle" style="width: 30px; height: 30px; padding: 0;">
                                            <i class="fa-solid fa-minus" style="font-size: 0.7em;"></i>
                                        </button>
                                        <span class="mx-3 fw-bold">x</span>
                                        <button type="button" class="btn btn-sm btn-dark rounded-circle" style="width: 30px; height: 30px; padding: 0;">
                                            <i class="fa-solid fa-plus" style="font-size: 0.7em;"></i>
                                        </button>
                                    </div>
                                </td>
                                <td class="text-center">Paquete</td>
                                <td class="text-center text-danger fw-bold">S/ XX.XX</td>
                                <td class="text-center">
                                    <button class="btn btn-icone text-danger">
                                        <i class="fa-solid fa-circle-xmark" style="font-size: 1.5em;"></i>
                                    </button>
                                </td>
                            </tr>

                            <!-- Producto 4: Placeholder -->
                            <tr class="opacity-50">
                                <td>
                                    <div class="d-flex align-items-center">
                                        <img src="images/placeholder.png" alt="Producto" class="me-3" style="width: 80px; height: 80px; object-fit: contain; filter: grayscale(100%);">
                                        <div>
                                            <div class="fw-bold">Producto</div>
                                            <div class="text-muted small">Paquete XX</div>
                                            <div class="text-muted small">Botellas</div>
                                        </div>
                                    </div>
                                </td>
                                <td class="text-center">S/ XX.XX</td>
                                <td>
                                    <div class="d-flex justify-content-center align-items-center">
                                        <button class="btn btn-sm btn-dark rounded-circle" style="width: 30px; height: 30px; padding: 0;">
                                            <i class="fa-solid fa-minus" style="font-size: 0.7em;"></i>
                                        </button>
                                        <span class="mx-3 fw-bold">x</span>
                                        <button class="btn btn-sm btn-dark rounded-circle" style="width: 30px; height: 30px; padding: 0;">
                                            <i class="fa-solid fa-plus" style="font-size: 0.7em;"></i>
                                        </button>
                                    </div>
                                </td>
                                <td class="text-center">Paquete</td>
                                <td class="text-center text-danger fw-bold">S/ XX.XX</td>
                                <td class="text-center">
                                    <button class="btn btn-icone text-danger">
                                        <i class="fa-solid fa-circle-xmark" style="font-size: 1.5em;"></i>
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Resumen del pedido -->
            <div class="col-lg-4 col-md-12">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h4 class="text-center mb-4">Resumen</h4>
                        
                        <div class="d-flex justify-content-between mb-3">
                            <span>Subtotal</span>
                            <span class="fw-bold">S/ 9,999.99</span>
                        </div>
                        
                        <div class="d-flex justify-content-between mb-3">
                            <span class="text-danger">Descuento</span>
                            <span class="text-danger fw-bold">S/ -99.99</span>
                        </div>
                        
                        <hr>
                        
                        <div class="d-flex justify-content-between mb-4">
                            <span class="fw-bold">Total</span>
                            <span class="fw-bold fs-5">S/ 9,900.00</span>
                        </div>
                        
                        <asp:button ID="btnContinuar" runat="server"
                            Text="CONTINUAR"
                            CssClass="btn bg-rojo text-white w-100 py-3 fw-bold"
                            Style="font-size: 1.1em;"
                            OnClick="btnContinuar_Click" />
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%--Script realizado con ayuda de ChatGpt--%>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            // Todos los botones de "+"
            const plusButtons = document.querySelectorAll(".btn-plus");
            const minusButtons = document.querySelectorAll(".btn-minus");

            // Cuando se presione "+"
            plusButtons.forEach(button => {
                button.addEventListener("click", function() {
                    const row = this.closest("tr");
                    const cantidadSpan = row.querySelector(".cantidad");
                    const precioTexto = row.querySelector("td.text-center").textContent.replace("S/", "").trim();
                    const totalTd = row.querySelector("td.text-center.text-danger.fw-bold");

                    let cantidad = parseInt(cantidadSpan.textContent);
                    let precio = parseFloat(precioTexto);
                    cantidad++;
                    cantidadSpan.textContent = cantidad;
                    totalTd.textContent = "S/ " + (cantidad * precio).toFixed(2);
                });
            });

            // Cuando se presione "–"
            minusButtons.forEach(button => {
                button.addEventListener("click", function () {
                    const row = this.closest("tr");
                    const cantidadSpan = row.querySelector(".cantidad");
                    const precioTexto = row.querySelector("td.text-center").textContent.replace("S/", "").trim();
                    const totalTd = row.querySelector("td.text-center.text-danger.fw-bold");

                    let cantidad = parseInt(cantidadSpan.textContent);
                    let precio = parseFloat(precioTexto);
                    if (cantidad > 1) {
                        cantidad--;
                        cantidadSpan.textContent = cantidad;
                        totalTd.textContent = "S/ " + (cantidad * precio).toFixed(2);
                    }
                });
            });
        });
    </script>
</asp:Content>
