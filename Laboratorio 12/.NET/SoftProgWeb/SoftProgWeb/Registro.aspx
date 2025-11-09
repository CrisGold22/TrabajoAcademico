<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProgInicio.Master" AutoEventWireup="true" CodeBehind="Registro.aspx.cs" Inherits="SoftProgWeb.Registro" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
    Registro - REDCOM
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphLogo" runat="server">
    <a class="navbar-brand" href='<%= ResolveUrl("~/Home.aspx") %>'>
        <img src="images/logo.png" style="position: absolute; width: 150px; top: 50%; left: 50%; transform: translate(-50%, -50%);"/>
    </a>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="registration-card">

        <!-- PASO 1: Datos de la Empresa -->
        <div class="form-step active" id="step1">
            <div class="form-group-custom">
                <label class="form-label-custom">Número de RUC de la empresa</label>
                <asp:TextBox ID="txtRUC" runat="server" CssClass="form-control-registro" 
                    placeholder="" MaxLength="11"></asp:TextBox>
            </div>

            <div class="form-group-custom">
                <label class="form-label-custom">Tipo de Documento de Representante Legal</label>
                <asp:DropDownList ID="ddlTipoDocumento" runat="server" CssClass="form-control-registro">
                    <asp:ListItem Value="">Seleccione un tipo de documento</asp:ListItem>
                    <asp:ListItem Value="DNI">DNI</asp:ListItem>
                    <asp:ListItem Value="CE">Carnet de Extranjería</asp:ListItem>
                    <asp:ListItem Value="PASS">Pasaporte</asp:ListItem>
                </asp:DropDownList>
            </div>

            <div class="form-group-custom">
                <label class="form-label-custom">Número de Documento de Representante Legal</label>
                <asp:TextBox ID="txtNumeroDocumento" runat="server" CssClass="form-control-registro" 
                    placeholder="" MaxLength="20"></asp:TextBox>
            </div>

            <div class="form-group-custom">
                <label class="form-label-custom">Correo Electrónico</label>
                <div class="input-with-icon">
                    <asp:TextBox ID="txtCorreo" runat="server" CssClass="form-control-registro" 
                        placeholder="" TextMode="Email"></asp:TextBox>
                    <i class="fas fa-envelope icon-right"></i>
                </div>
            </div>

            <div class="form-group-custom">
                <label class="form-label-custom">Número de Teléfono</label>
                <div class="input-with-icon">
                    <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control-registro" 
                        placeholder="+51 999 999 999"></asp:TextBox>
                    <i class="fas fa-phone icon-right"></i>
                </div>
            </div>

            <div class="form-group-custom">
                <label class="form-label-custom">Contraseña</label>
                <div class="input-with-icon">
                    <asp:TextBox ID="txtPassword" runat="server" CssClass="form-control-registro" 
                        placeholder="" TextMode="Password"></asp:TextBox>
                    <i class="fas fa-eye eye-icon icon-right" id="togglePassword1"></i>
                </div>
            </div>

            <div class="checkbox-container">
                <asp:CheckBox ID="chkTerms1" runat="server" />
                <label for="<%= chkTerms1.ClientID %>">
                    I accept the terms
                    <a href="#" target="_blank">Read our T&Cs</a>
                </label>
            </div>

            <div class="form-actions">
                <button type="button" class="btn-step btn-anterior" onclick="window.location.href='InicioSesion.aspx'">
                    Anterior
                </button>
                <button type="button" class="btn-step btn-siguiente" onclick="nextStep(1)">
                    Siguiente
                </button>
            </div>
        </div>

        <!-- PASO 2: Verificación (muestra datos) -->
        <div class="form-step" id="step2">
            <div class="form-group-custom">
                <label class="form-label-custom">Número de RUC de la empresa</label>
                <input type="text" class="form-control-registro filled" id="displayRUC" readonly />
            </div>

            <div class="form-group-custom">
                <label class="form-label-custom">Tipo de Documento de Representante Legal</label>
                <input type="text" class="form-control-registro filled" id="displayTipoDoc" readonly />
            </div>

            <div class="form-group-custom">
                <label class="form-label-custom">Número de Documento de Representante Legal</label>
                <input type="text" class="form-control-registro filled" id="displayNumDoc" readonly />
            </div>

            <div class="form-group-custom">
                <label class="form-label-custom">Correo Electrónico</label>
                <div class="input-with-icon">
                    <input type="text" class="form-control-registro filled" id="displayCorreo" readonly />
                    <i class="fas fa-envelope icon-right"></i>
                </div>
            </div>

            <div class="form-group-custom">
                <label class="form-label-custom">Número de Teléfono</label>
                <div class="input-with-icon">
                    <input type="text" class="form-control-registro filled" id="displayTelefono" readonly />
                    <i class="fas fa-phone icon-right"></i>
                </div>
            </div>

            <div class="form-group-custom">
                <label class="form-label-custom">Contraseña</label>
                <div class="input-with-icon">
                    <input type="password" class="form-control-registro filled" id="displayPassword" readonly />
                    <i class="fas fa-eye eye-icon icon-right" id="togglePassword2"></i>
                </div>
            </div>

            <div class="checkbox-container">
                <input type="checkbox" id="chkTerms2" checked disabled />
                <label>
                    I accept the terms
                    <a href="#" target="_blank">Read our T&Cs</a>
                </label>
            </div>

            <div class="form-actions">
                <button type="button" class="btn-step btn-anterior" onclick="prevStep(2)">
                    Anterior
                </button>
                <button type="button" class="btn-step btn-siguiente" onclick="nextStep(2)">
                    Siguiente
                </button>
            </div>
        </div>

        <!-- PASO 3: Datos Personales -->
        <div class="form-step" id="step3">
            <div class="form-group-custom">
                <label class="form-label-custom">Nombre</label>
                <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control-registro" 
                    placeholder="Juan"></asp:TextBox>
            </div>

            <div class="form-group-custom">
                <label class="form-label-custom">Apellido Materno</label>
                <asp:TextBox ID="txtApellidoMaterno" runat="server" CssClass="form-control-registro" 
                    placeholder="Perez"></asp:TextBox>
            </div>

            <div class="form-group-custom">
                <label class="form-label-custom">Apellido Paterno</label>
                <asp:TextBox ID="txtApellidoPaterno" runat="server" CssClass="form-control-registro" 
                    placeholder="JuanPerez@gmail.com"></asp:TextBox>
            </div>

            <div class="form-group-custom">
                <label class="form-label-custom">Genero</label>
                <asp:DropDownList ID="ddlGenero" runat="server" CssClass="form-control-registro">
                    <asp:ListItem Value="">Seleccione género</asp:ListItem>
                    <asp:ListItem Value="M">Masculino</asp:ListItem>
                    <asp:ListItem Value="F">Femenino</asp:ListItem>
                    <asp:ListItem Value="O">Otro</asp:ListItem>
                </asp:DropDownList>
            </div>

            <div class="form-group-custom">
                <label class="form-label-custom">Dirección</label>
                <asp:TextBox ID="txtDireccion" runat="server" CssClass="form-control-registro" 
                    placeholder="Mz L 20 Av argentina"></asp:TextBox>
            </div>

            <div class="checkbox-container">
                <asp:CheckBox ID="chkTerms3" runat="server" />
                <label for="<%= chkTerms3.ClientID %>">
                    I accept the terms
                    <a href="#" target="_blank">Read our T&Cs</a>
                </label>
            </div>

            <div class="form-actions">
                <button type="button" class="btn-step btn-anterior" onclick="prevStep(3)">
                    Anterior
                </button>
                <asp:Button ID="btnRegistrar" runat="server" CssClass="btn-step btn-registrar" 
                    Text="Registrar Empresa" OnClientClick="return validateStep3();" OnClick="btnRegistrar_Click" />
            </div>
        </div>
    </div>

    <!-- Modal de Éxito -->
    <div class="success-modal" id="successModal">
        <div class="success-modal-content">
            <span class="close-modal" onclick="closeSuccessModal()">&times;</span>
            <h2>¡Su cuenta fue Registrado!</h2>
            <p>Dentro de las próximas 24 horas recibirá un correo si su cuenta ha sido habilitada.</p>
        </div>
    </div>

     <script>
        let currentStep = 1;

        // Función para mostrar/ocultar contraseña
        document.getElementById('togglePassword1').addEventListener('click', function () {
            const passwordField = document.getElementById('<%= txtPassword.ClientID %>');
            const icon = this;
            
            if (passwordField.type === 'password') {
                passwordField.type = 'text';
                icon.classList.remove('fa-eye');
                icon.classList.add('fa-eye-slash');
            } else {
                passwordField.type = 'password';
                icon.classList.remove('fa-eye-slash');
                icon.classList.add('fa-eye');
            }
        });

        document.getElementById('togglePassword2').addEventListener('click', function () {
            const passwordField = document.getElementById('displayPassword');
            const icon = this;
            
            if (passwordField.type === 'password') {
                passwordField.type = 'text';
                icon.classList.remove('fa-eye');
                icon.classList.add('fa-eye-slash');
            } else {
                passwordField.type = 'password';
                icon.classList.remove('fa-eye-slash');
                icon.classList.add('fa-eye');
            }
        });

        // Validación del Paso 1
        function validateStep1() {
            const ruc = document.getElementById('<%= txtRUC.ClientID %>').value.trim();
            const tipoDoc = document.getElementById('<%= ddlTipoDocumento.ClientID %>').value;
            const numDoc = document.getElementById('<%= txtNumeroDocumento.ClientID %>').value.trim();
            const correo = document.getElementById('<%= txtCorreo.ClientID %>').value.trim();
            const telefono = document.getElementById('<%= txtTelefono.ClientID %>').value.trim();
            const password = document.getElementById('<%= txtPassword.ClientID %>').value.trim();
            const terms = document.getElementById('<%= chkTerms1.ClientID %>').checked;

            if (!ruc || !tipoDoc || !numDoc || !correo || !telefono || !password) {
                alert('Por favor, complete todos los campos obligatorios.');
                return false;
            }

            if (ruc.length !== 11) {
                alert('El RUC debe tener 11 dígitos.');
                return false;
            }

            if (!terms) {
                alert('Debe aceptar los términos y condiciones.');
                return false;
            }

            // Validar formato de correo
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(correo)) {
                alert('Por favor, ingrese un correo electrónico válido.');
                return false;
            }

            return true;
        }

        // Validación del Paso 3
        function validateStep3() {
            const nombre = document.getElementById('<%= txtNombre.ClientID %>').value.trim();
            const apellidoMaterno = document.getElementById('<%= txtApellidoMaterno.ClientID %>').value.trim();
            const apellidoPaterno = document.getElementById('<%= txtApellidoPaterno.ClientID %>').value.trim();
            const genero = document.getElementById('<%= ddlGenero.ClientID %>').value;
            const direccion = document.getElementById('<%= txtDireccion.ClientID %>').value.trim();
            const terms = document.getElementById('<%= chkTerms3.ClientID %>').checked;

            if (!nombre || !apellidoMaterno || !apellidoPaterno || !genero || !direccion) {
                alert('Por favor, complete todos los campos obligatorios.');
                return false;
            }

            if (!terms) {
                alert('Debe aceptar los términos y condiciones.');
                return false;
            }

            return true;
        }

        // Navegar al siguiente paso
        function nextStep(step) {
            if (step === 1) {
                if (!validateStep1()) return;

                // Copiar datos al paso 2
                document.getElementById('displayRUC').value = document.getElementById('<%= txtRUC.ClientID %>').value;
                document.getElementById('displayTipoDoc').value = document.getElementById('<%= ddlTipoDocumento.ClientID %>').options[document.getElementById('<%= ddlTipoDocumento.ClientID %>').selectedIndex].text;
                document.getElementById('displayNumDoc').value = document.getElementById('<%= txtNumeroDocumento.ClientID %>').value;
                document.getElementById('displayCorreo').value = document.getElementById('<%= txtCorreo.ClientID %>').value;
                document.getElementById('displayTelefono').value = document.getElementById('<%= txtTelefono.ClientID %>').value;
                document.getElementById('displayPassword').value = document.getElementById('<%= txtPassword.ClientID %>').value;
            }

            // Ocultar paso actual
            document.getElementById('step' + step).classList.remove('active');

            // Mostrar siguiente paso
            currentStep = step + 1;
            document.getElementById('step' + currentStep).classList.add('active');

            // Scroll to top
            window.scrollTo({ top: 0, behavior: 'smooth' });
        }

         // Navegar al paso anterior
         function prevStep(step) {
             document.getElementById('step' + step).classList.remove('active');
             currentStep = step - 1;
             document.getElementById('step' + currentStep).classList.add('active');
             window.scrollTo({ top: 0, behavior: 'smooth' });
         }

        // Mostrar modal de éxito
        function showSuccessModal() {
            document.getElementById('successModal').classList.add('show');
        }

        // Cerrar modal
        function closeSuccessModal() {
            document.getElementById('successModal').classList.remove('show');
            window.location.href = 'InicioSesion.aspx';
        }

        // Cerrar modal al hacer clic fuera
        document.getElementById('successModal').addEventListener('click', function (e) {
            if (e.target === this) {
                closeSuccessModal();
            }
        });
     </script>

</asp:Content>
