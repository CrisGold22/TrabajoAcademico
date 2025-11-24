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
                    <asp:GridView ID="gvCarrito" runat="server" AutoGenerateColumns="False" CssClass="table table-hover align-middle" OnRowCommand="gvCarrito_RowCommand">
                        <Columns>
                            <%-- PRODUCTO --%>
                            <asp:TemplateField HeaderText="PRODUCTO">
                                <ItemTemplate>
                                    <div class="d-flex align-items-center">
                                        <img src='<%# "images/" + Eval("producto.ImagenURL") %>' 
                                             alt='<%# Eval("producto.nombre") %>' 
                                             class="me-3" style="width: 80px; height: 80px; object-fit: contain;">
                                        <div>
                                            <div class="fw-bold"><%# Eval("producto.nombre") %></div>
                                            <div class="text-muted small"><%# Eval("producto.descripcion") %></div>
                                            <div class="text-muted small">Paquete <%# Eval("producto.SKU") %></div>
                                        </div>
                                    </div>
                                </ItemTemplate>
                            </asp:TemplateField>

                            <%-- PRECIO --%>
                            <asp:BoundField HeaderText="PRECIO" DataField="producto.precioRegular"
                                            DataFormatString="S/ {0:F2}" ItemStyle-HorizontalAlign="Center" />

                            <%-- CANTIDAD CON BOTONES FUNCIONALES --%>
                            <asp:TemplateField HeaderText="CANTIDAD">
                                <ItemTemplate>
                                    <div class="d-flex justify-content-center align-items-center">
                                        <asp:Button ID="btnDecrementar" runat="server"
                                            Text="-" CssClass="btn btn-sm btn-dark rounded-circle btn-minus"
                                            Style="width: 30px; height: 30px; padding: 0;"
                                            CommandName="Decrementar"
                                            CommandArgument="<%# Container.DataItemIndex %>" />

                                        <span class="mx-3 fw-bold cantidad"><%# Eval("cantidad") %></span>

                                        <asp:Button ID="btnIncrementar" runat="server"
                                            Text="+" CssClass="btn btn-sm btn-dark rounded-circle btn-plus"
                                            Style="width: 30px; height: 30px; padding: 0;"
                                            CommandName="Incrementar"
                                            CommandArgument="<%# Container.DataItemIndex %>" />
                                    </div>
                                </ItemTemplate>
                                <ItemStyle HorizontalAlign="Center" />
                            </asp:TemplateField>

                            <%-- UNIDAD --%>
                            <asp:BoundField HeaderText="UNIDAD" DataField="producto.medidaAlMayorString"
                                            ItemStyle-HorizontalAlign="Center" />

                            <%-- TOTAL --%>
                            <asp:BoundField HeaderText="TOTAL" DataField="subTotal"
                                            DataFormatString="S/ {0:F2}" 
                                            ItemStyle-HorizontalAlign="Center" 
                                            ItemStyle-ForeColor="Red" 
                                            ItemStyle-Font-Bold="true" />

                            <%-- ELIMINAR --%>
                            <asp:TemplateField>
                                <ItemTemplate>
                                    <asp:LinkButton ID="btnEliminar" runat="server"
                                        CssClass="btn btn-outline-danger"
                                        CommandName="Eliminar"
                                        CommandArgument='<%# Eval("id") %>'
                                        OnClientClick="return confirm('¿Seguro que quieres eliminar este producto?');">
                                        <i class="fas fa-trash"></i>
                                    </asp:LinkButton>
                                </ItemTemplate>
                                <ItemStyle HorizontalAlign="Center" />
                            </asp:TemplateField>
                        </Columns>
                    </asp:GridView>
                </div>
            </div>

            <!-- Resumen del pedido -->
           <div class="col-lg-4 col-md-12">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h4 class="text-center mb-4">Resumen</h4>

                        <div class="d-flex justify-content-between mb-3">
                            <span>Subtotal</span>
                            <asp:Label ID="lblSubtotal" runat="server" CssClass="fw-bold" Text="S/ 0.00" />
                        </div>

                        <div class="d-flex justify-content-between mb-3">
                            <span class="text-danger">Descuento</span>
                            <asp:Label ID="lblDescuento" runat="server" CssClass="text-danger fw-bold" Text="S/ 0.00" />
                        </div>

                        <hr />

                        <div class="d-flex justify-content-between mb-4">
                            <span class="fw-bold">Total</span>
                            <asp:Label ID="lblTotal" runat="server" CssClass="fw-bold fs-5 text-danger" Text="S/ 0.00" />
                        </div>

                        <asp:Button ID="btnContinuar" runat="server"
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
        document.addEventListener("DOMContentLoaded", function () {
            const plusButtons = document.querySelectorAll(".btn-plus");
            const minusButtons = document.querySelectorAll(".btn-minus");

            function actualizarResumen() {
                let subtotal = 0;
                document.querySelectorAll("#gvCarrito .cantidad").forEach(span => {
                    let row = span.closest("tr");
                    let precio = parseFloat(row.querySelector("td:nth-child(2)").textContent.replace("S/", "").trim());
                    let cantidad = parseInt(span.textContent);
                    subtotal += precio * cantidad;
                });

                let descuento = subtotal * 0.01; // ejemplo: 1% de descuento
                let total = subtotal - descuento;

                document.getElementById("<%= lblTotal.ClientID %>").textContent = "S/ " + total.toFixed(2);
                document.getElementById("<%= lblSubtotal.ClientID %>").textContent = "S/ " + subtotal.toFixed(2);
                document.getElementById("<%= lblDescuento.ClientID %>").textContent = "S/ " + descuento.toFixed(2);
            }

            plusButtons.forEach(button => {
                button.addEventListener("click", function () {
                    const row = this.closest("tr");
                    const cantidadSpan = row.querySelector(".cantidad");
                    const precioTexto = row.querySelector("td:nth-child(2)").textContent.replace("S/", "").trim();
                    const totalTd = row.querySelector("td:nth-child(5)"); // columna TOTAL
                    let cantidad = parseInt(cantidadSpan.textContent);
                    let precio = parseFloat(precioTexto);

                    cantidad++;
                    cantidadSpan.textContent = cantidad;
                    totalTd.textContent = "S/ " + (cantidad * precio).toFixed(2);

                    actualizarResumen();
                });
            });

            minusButtons.forEach(button => {
                button.addEventListener("click", function () {
                    const row = this.closest("tr");
                    const cantidadSpan = row.querySelector(".cantidad");
                    const precioTexto = row.querySelector("td:nth-child(2)").textContent.replace("S/", "").trim();
                    const totalTd = row.querySelector("td:nth-child(5)"); // columna TOTAL
                    let cantidad = parseInt(cantidadSpan.textContent);
                    let precio = parseFloat(precioTexto);

                    if (cantidad > 1) {
                        cantidad--;
                        cantidadSpan.textContent = cantidad;
                        totalTd.textContent = "S/ " + (cantidad * precio).toFixed(2);

                        actualizarTotalGeneral();
                    }
                });
            });
        });
    </script>
</asp:Content>
