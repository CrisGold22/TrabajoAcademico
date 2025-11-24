<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="Pago.aspx.cs" Inherits="SoftProgWeb.Pago" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
    <link href="Content/botones.css" rel="stylesheet" />
    <link href="Content/pagos.css" rel="stylesheet" />
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div id="pantallaMetodoPago" class="screen-content">
            <div class="row">
                <div class="col-12">
                    <div class="d-flex justify-content-end mb-3">
                        <a href='<%= ResolveUrl("~/Index.aspx") %>' class="btn btn-outline-secondary">
                            <i class="fa-solid fa-arrow-left me-2"></i>Seguir comprando
                        </a>
                    </div>

                    <div class="step-indicator">
                         <div class="text-center">
                            <div class="step-circle completed">
                                <i class="fa-solid fa-check"></i>
                            </div>
                            <div class="step-label completed fw-bold">Carrito</div>
                        </div>
                        
                         <div class="step-line"></div>
                        
                        <div class="text-center">
                            <div class="step-circle completed">
                                <i class="fa-solid fa-check"></i>
                            </div>
                            <div class="step-label completed fw-bold">Entrega</div>
                        </div>
                        
                        <div class="step-line"></div>
                        
                        <div class="text-center">
                            <div class="step-circle active">3</div>
                            <div class="step-label active fw-bold">Pago</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row justify-content-center">
                <div class="col-lg-6 col-md-8">
                    <div class="card shadow-sm">
                        <div class="card-body p-4">
                            <div class="mb-4">
                                <div class="d-flex align-items-center mb-3">
                                    <i class="fa-solid fa-file-invoice payment-icon"></i>
                                    <div>
                                        <strong>Tipo de comprobante:</strong>
                                        <div>Factura</div>
                                    </div>
                                </div>
                            </div>
                            <div class="text-center mb-4">
                                <h5 class="text-muted">Total a Pagar</h5>
                                <h2 class="fw-bold text-primary">
                                    <asp:Label ID="lblTotalPagar" runat="server" Text="S/ 0.00"></asp:Label>
                                </h2>
                            </div>
                            <hr>

                            <div class="mb-4">
                                <h5 class="mb-3">¿Cómo deseas pagar?</h5>
                                
                                <div class="payment-option" onclick="selectPayment('virtual')">
                                    <div class="d-flex align-items-center">
                                        <input type="radio" name="metodoPago" id="radioVirtual" value="virtual">
                                        <label for="radioVirtual" class="mb-0 flex-grow-1" style="cursor: pointer;">
                                            <i class="fa-solid fa-wallet payment-icon"></i>
                                            Billetera virtual (Cripto / MetaMask)
                                        </label>
                                    </div>
                                </div>

                                <div class="payment-option" onclick="selectPayment('contraentrega')">
                                    <div class="d-flex align-items-center">
                                        <input type="radio" name="metodoPago" id="radioContraentrega" value="contraentrega">
                                        <label for="radioContraentrega" class="mb-0 flex-grow-1" style="cursor: pointer;">
                                            <i class="fa-solid fa-money-bill payment-icon"></i>
                                            Contra entrega
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="text-center mt-4">
                                <button type="button" class="btn bg-rojo text-white w-75 py-3 fw-bold" 
                                    style="font-size: 1.1em;" onclick="finalizarPago()">
                                    FINALIZAR
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="modalQR" class="qr-modal">
            <div class="qr-content">
                <div id="qrCode" class="qr-code">
                    <img src="https://api.qrserver.com/v1/create-qr-code/?size=220x220&data=0xd9f931c0db96b290ccb0ca9bf1070767175b43ec" 
                        alt="QR Code" style="width: 100%; height: 100%;">
                    <div class="check-overlay">
                        <i class="fa-solid fa-check"></i>
                    </div>
                </div>
                
                <div id="statusMessage" class="status-message status-waiting">
                    Esperando la confirmación del pago en Blockchain...
                </div>

                <div class="d-flex justify-content-center gap-3 mt-3">
                    <button type="button" class="btn btn-outline-danger px-4" onclick="cancelarPago()">
                        CANCELAR
                    </button>
                    <button type="button" id="btnAceptarQR" class="btn btn-outline-secondary px-4" 
                        onclick="aceptarPago()" disabled>
                        ACEPTAR
                    </button>
                </div>
            </div>
        </div>

        <div id="pantallaConfirmacion" class="screen-content" style="display: none;">
            <div class="row">
                <div class="col-12">
                    <div class="d-flex justify-content-end mb-3">
                        <a href='<%= ResolveUrl("~/Home.aspx") %>' class="btn btn-outline-secondary">
                            <i class="fa-solid fa-arrow-left me-2"></i>Seguir comprando
                        </a>
                    </div>

                    <div class="step-indicator">
                        <div class="text-center">
                            <div class="step-circle completed">
                                <i class="fa-solid fa-check"></i>
                            </div>
                            <div class="step-label completed fw-bold">Carrito</div>
                        </div>
                        
                        <div class="step-line"></div>
                        
                        <div class="text-center">
                            <div class="step-circle completed">
                                <i class="fa-solid fa-check"></i>
                            </div>
                            <div class="step-label completed fw-bold">Entrega</div>
                        </div>
                        
                        <div class="step-line"></div>
                        
                        <div class="text-center">
                            <div class="step-circle completed">
                                <i class="fa-solid fa-check"></i>
                            </div>
                            <div class="step-label completed fw-bold">Pago</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row justify-content-center mt-5">
                <div class="col-lg-8 col-md-10 text-center">
                    <div class="success-icon">
                        <i class="fa-regular fa-circle-check"></i>
                    </div>
                    
                    <h2 class="mb-4">Orden Confirmada</h2>
                    
                    <div class="border border-danger rounded p-3 d-inline-block mb-4">
                        <a href="#" class="text-danger text-decoration-none fw-bold" onclick="verSeguimiento()">
                            Haz click aquí para realizar el seguimiento de tu pedido
                        </a>
                    </div>

                    <div class="alert alert-secondary mx-auto" style="max-width: 600px;">
                        <p class="mb-0">
                            Puede cancelar la orden de compra dentro de un plazo de 4 horas luego de haber realizado el pago.
                            Si el pago se realizó a partir de las 20:00 horas (8:00 p.m.), el plazo de cancelación se extenderá 
                            hasta las 7:00 a.m. del siguiente día.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script>
    const MI_WALLET = "0xd9f931c0db96b290ccb0ca9bf1070767175b43ec";
    const TASA_CAMBIO_FIJA = 4000.00; // Ethereum igual a la de Java

    let metodoPagoSeleccionado = '';

    function selectPayment(metodo) {
        metodoPagoSeleccionado = metodo;
        document.getElementById('radio' + (metodo === 'virtual' ? 'Virtual' : 'Contraentrega')).checked = true;
    }

    function finalizarPago() {
        if (!metodoPagoSeleccionado) {
            alert('Por favor seleccione un método de pago');
            return;
        }

        if (metodoPagoSeleccionado === 'virtual') {
            const textoTotal = document.getElementById('<%= lblTotalPagar.ClientID %>').innerText;
            const totalSoles = parseFloat(textoTotal.replace('S/', '').trim());

            const montoEth = totalSoles / TASA_CAMBIO_FIJA;

            const montoEthFinal = montoEth.toFixed(6);

            const contenidoQR = `ethereum:${MI_WALLET}?amount=${montoEthFinal}`;

            const urlQR = `https://api.qrserver.com/v1/create-qr-code/?size=220x220&data=${encodeURIComponent(contenidoQR)}`;
            document.querySelector('#qrCode img').src = urlQR;

            document.getElementById('statusMessage').innerHTML =
                `Monto a pagar: <strong>${montoEthFinal} ETH</strong><br/>Esperando confirmación...`;

            document.getElementById('modalQR').classList.add('show');

            iniciarProcesoPago();

        } else {
            alert("Pago contra entrega registrado.");
            window.location.href = "Seguimiento.aspx";
        }
    }

    function iniciarProcesoPago() {
        const payload = { metodoPago: 'CRIPTO' };

        fetch('Pago.aspx/ProcesarPagoCripto', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=utf-8',
                'Accept': 'application/json'
            },
            body: JSON.stringify(payload)
        })
            .then(response => response.json())
            .then(data => {
                const resultado = data.d;
                if (resultado === "OK") {
                    confirmarPagoQR();
                } else {
                    alert(resultado);
                    cancelarPago();
                }
            })
            .catch(error => {
                console.error('Error:', error);
                cancelarPago();
            });
    }

    function confirmarPagoQR() {
        const qrCode = document.getElementById('qrCode');
        const statusMessage = document.getElementById('statusMessage');
        const btnAceptar = document.getElementById('btnAceptarQR');

        qrCode.classList.add('success');
        statusMessage.className = 'status-message status-success';
        statusMessage.textContent = '¡Pago confirmado en Blockchain!';

        btnAceptar.disabled = false;
        btnAceptar.className = 'btn bg-rojo text-white px-4';
    }

    function cancelarPago() {
        document.getElementById('modalQR').classList.remove('show');
    }

    function aceptarPago() {
        document.getElementById('modalQR').classList.remove('show');
        mostrarPantallaConfirmacion();
    }

    function mostrarPantallaConfirmacion() {
        document.getElementById('pantallaMetodoPago').style.display = 'none';
        document.getElementById('pantallaConfirmacion').style.display = 'block';
    }

    function verSeguimiento() {
        window.location.href = 'Seguimiento.aspx';
    }
</script>
</asp:Content>