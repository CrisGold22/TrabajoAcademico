<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="Entrega.aspx.cs" Inherits="SoftProgWeb.Entrega" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
    Entrega - SoftProg
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
    <link href="Content/botones.css" rel="stylesheet" />
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <!-- Botón Seguir Comprando -->
                <div class="d-flex justify-content-end mb-3">
                    <a href='<%= ResolveUrl("~/Index.aspx") %>' class="btn btn-outline-secondary">
                        <i class="fa-solid fa-arrow-left me-2"></i>Seguir comprando
                    </a>
                </div>

                <!-- Indicador de pasos -->
                <div class="step-indicator">
                    <div class="text-center">
                        <div class="step-circle completed">
                            <i class="fa-solid fa-check"></i>
                        </div>
                        <div class="step-label completed fw-bold">Carrito</div>
                    </div>
                    
                    <div class="step-line"></div>
                    
                    <div class="text-center">
                        <div class="step-circle active">2</div>
                        <div class="step-label active fw-bold">Entrega</div>
                    </div>
                    
                    <div class="step-line"></div>
                    
                    <div class="text-center">
                        <div class="step-circle inactive">3</div>
                        <div class="step-label inactive">Pago</div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Formulario de Entrega -->
        <div class="row justify-content-center">
            <div class="col-lg-6 col-md-8">
                <div class="card shadow-sm">
                    <div class="card-body p-4">
                        <!-- Establecimiento -->
                        <div class="mb-4">
                            <label class="form-label fw-bold">Establecimiento para el envío del pedido</label>
                            <asp:DropDownList ID="ddlEstablecimiento" runat="server" CssClass="form-select">
                                <asp:ListItem Value="Local San Miguel">Local San Miguel</asp:ListItem>
                                <asp:ListItem Value="Local Villa el Salvador">Local Villa el Salvador</asp:ListItem>
                                <asp:ListItem Value="Local Cieneguilla">Local Cieneguilla</asp:ListItem>
                            </asp:DropDownList>
                        </div>

                        <!-- Fecha de Entrega -->
                        <div class="mb-4">
                            <label class="form-label fw-bold">Fecha para la entrega del pedido</label>
                            <div class="border rounded p-3 bg-light">
                                <label class="form-label small text-muted mb-2">Fecha</label>
                                <asp:TextBox ID="txtFechaEntrega" runat="server" CssClass="form-control" 
                                    TextMode="Date" placeholder="DD/MM/YYYY"></asp:TextBox>
                                <small class="text-muted d-block mt-1">DD/MM/YYYY</small>
                            </div>
                        </div>

                        <!-- Hora de Entrega -->
                        <div class="mb-4">
                            <label class="form-label fw-bold">Hora de entrega (8:00 am a 8:00 pm)</label>
                            <div class="time-picker-container">
                                <label class="form-label small mb-3">Enter time</label>
                                
                                <div class="d-flex justify-content-center align-items-center mb-3">
                                    <div class="text-center me-3">
                                        <input type="number" id="hourInput" class="time-input" 
                                            min="1" max="12" value="07" maxlength="2" />
                                        <small class="d-block mt-2 text-muted">Hour</small>
                                    </div>
                                    
                                    <span class="time-separator">:</span>
                                    
                                    <div class="text-center ms-3">
                                        <input type="number" id="minuteInput" class="time-input" 
                                            min="0" max="59" value="00" maxlength="2" />
                                        <small class="d-block mt-2 text-muted">Minute</small>
                                    </div>
                                    
                                    <div class="ms-4">
                                        <button type="button" class="am-pm-btn mb-2 d-block" id="btnAM">AM</button>
                                        <button type="button" class="am-pm-btn active d-block" id="btnPM">PM</button>
                                    </div>
                                </div>

                                <div class="d-flex justify-content-between align-items-center">
                                    <button type="button" class="btn btn-link text-decoration-none text-dark">
                                        <i class="fa-regular fa-clock me-2"></i>
                                    </button>
                                    <div>
                                        <button type="button" class="btn btn-link text-decoration-none text-dark me-3">Cancel</button>
                                        <button type="button" class="btn btn-link text-decoration-none text-dark fw-bold">OK</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Botón Continuar -->
                        <div class="text-center mt-4">
                            <asp:Button ID="btnContinuar" runat="server" Text="CONTINUAR" 
                                CssClass="btn bg-rojo text-white w-75 py-3 fw-bold" 
                                style="font-size: 1.1em;" OnClick="btnContinuar_Click" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    <script>
        //script generado con ayuda de Claude AI
        // Funcionalidad para los botones AM/PM
        document.addEventListener('DOMContentLoaded', function() {
            const btnAM = document.getElementById('btnAM');
            const btnPM = document.getElementById('btnPM');
            
            btnAM.addEventListener('click', function() {
                btnAM.classList.add('active');
                btnPM.classList.remove('active');
            });
            
            btnPM.addEventListener('click', function() {
                btnPM.classList.add('active');
                btnAM.classList.remove('active');
            });

            // Validación de entrada para horas (1-12)
            const hourInput = document.getElementById('hourInput');
            hourInput.addEventListener('input', function() {
                let value = parseInt(this.value);
                if (value > 12) this.value = 12;
                if (value < 1) this.value = 1;
                if (this.value.length > 2) this.value = this.value.slice(0, 2);
            });

            hourInput.addEventListener('blur', function() {
                if (this.value.length === 1) {
                    this.value = '0' + this.value;
                }
            });

            // Validación de entrada para minutos (0-59)
            const minuteInput = document.getElementById('minuteInput');
            minuteInput.addEventListener('input', function() {
                let value = parseInt(this.value);
                if (value > 59) this.value = 59;
                if (value < 0) this.value = 0;
                if (this.value.length > 2) this.value = this.value.slice(0, 2);
            });

            minuteInput.addEventListener('blur', function() {
                if (this.value.length === 1) {
                    this.value = '0' + this.value;
                }
            });
        });
    </script>
</asp:Content>
