<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="Seguimiento.aspx.cs" Inherits="SoftProgWeb.Seguimiento" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    
    <!-- Pantalla 5: Seguimiento del Pedido -->
    <div id="pantallaSeguimiento" class="screen-content" >
        <div class="row">
            <div class="col-12">
                <!-- Botón Seguir Comprando -->
                <div class="d-flex justify-content-end mb-3">
                    <a href='<%= ResolveUrl("~/Index.aspx") %>' class="btn btn-outline-secondary">
                        <i class="fa-solid fa-arrow-left me-2"></i>Seguir comprando
                    </a>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-8 col-md-10">
                <!-- Información del Pedido -->
                <div class="order-info">
                    <p><strong>Código orden de compra:</strong> <asp:Label ID="lblCodigoOrden" runat="server" /></p>
                    <p><strong>Fecha de la entrega:</strong> <asp:Label ID="lblFechaEntrega" runat="server" /></p>
                    <p><strong>Hora estimada de la entrega:</strong> <asp:Label ID="lblHorarioEntrega" runat="server" /></p>
                    <p><strong>Lugar de entrega:</strong> <asp:Label ID="lblDireccion" runat="server" /></p>
                    <p class="mb-0">
                        <i class="fa-solid fa-check-circle text-success me-2"></i>
                        <strong class="text-success">Orden pagada</strong>
                    </p>
                </div>

                <!-- Tracking -->
                <div class="tracking-container">
                    <h5 class="mb-4 fw-bold">SIGUE TU PEDIDO:</h5>

                    <div id="step1" runat="server" class="tracking-step">
                        <div class="tracking-circle filled">
                            <i class="fa-solid fa-circle text-white"></i>
                        </div>
                        <div>
                            <strong>Orden Confirmada</strong>
                        </div>
                    </div>

                    <div id="step2" runat="server" class="tracking-step">
                        <div class="tracking-circle">
                            <i class="fa-regular fa-circle text-muted"></i>
                        </div>
                        <div>
                            <span class="text-muted">Orden en preparación</span>
                        </div>
                    </div>

                    <div id="step3" runat="server" class="tracking-step">
                        <div class="tracking-circle">
                            <i class="fa-regular fa-circle text-muted"></i>
                        </div>
                        <div>
                            <span class="text-muted">Enviado</span>
                        </div>
                    </div>

                    <div id="step4" runat="server" class="tracking-step">
                        <div class="tracking-circle">
                            <i class="fa-regular fa-circle text-muted"></i>
                        </div>
                        <div>
                            <span class="text-muted">Entregado</span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-2 d-flex align-items-center justify-content-center">
                <button type="button" class="btn btn-outline-danger px-4 py-3" 
                    onclick="mostrarConfirmacionCancelar()">
                    Cancelar<br>Pedido
                </button>
            </div>
        </div>
    </div>

    <!-- Modal Confirmación de Cancelación -->
    <div id="modalConfirmacionCancelar" class="confirmation-modal">
        <div class="cancel-confirmation">
            <h5 class="mb-4">¿Está seguro de que quiere cancelar su pedido?</h5>
            <div class="d-flex justify-content-center gap-3">
                <button type="button" class="btn bg-rojo text-white px-4" 
                    onclick="cerrarConfirmacionCancelar()">
                    Cancelar
                </button>
                <button type="button" class="btn btn-outline-secondary px-4" 
                    onclick="confirmarCancelacion()">
                    Aceptar
                </button>
            </div>
        </div>
    </div>

    <!-- Pantalla 7: Pedido Cancelado -->
    <div id="pantallaCancelado" class="confirmation-modal">
        <div class="confirmation-content">
            <div class="success-icon">
                <i class="fa-regular fa-circle-check"></i>
            </div>
            <h4 class="mb-0">Pedido cancelado exitosamente</h4>
        </div>
    </div>

    <script>
        function mostrarConfirmacionCancelar() {
            document.getElementById('modalConfirmacionCancelar').classList.add('show');
        }

        function cerrarConfirmacionCancelar() {
            document.getElementById('modalConfirmacionCancelar').classList.remove('show');
        }

        function confirmarCancelacion() {
            document.getElementById('modalConfirmacionCancelar').classList.remove('show');
            document.getElementById('pantallaSeguimiento').style.display = 'none';

            // Mostrar mensaje de cancelación exitosa
            document.getElementById('pantallaCancelado').classList.add('show');

            // Cerrar después de 2 segundos
            setTimeout(function () {
                document.getElementById('pantallaCancelado').classList.remove('show');
                // Redirigir o volver al inicio
                window.location.href = 'Home.aspx';
            }, 2000);
        }
    </script>
</asp:Content>
