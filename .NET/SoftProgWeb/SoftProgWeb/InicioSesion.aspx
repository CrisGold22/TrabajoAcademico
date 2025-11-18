<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProgInicio.Master"
    AutoEventWireup="true" CodeBehind="InicioSesion.aspx.cs"
    Inherits="SoftProgWeb.InicioSesion" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">

</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphLogo" runat="server">
    <a class="navbar-brand" href='<%= ResolveUrl("~/Home.aspx") %>'>
        <img src="images/logo.png" style="position: absolute; width: 150px; top: 50%; left: 50%; transform: translate(-50%, -50%);"/>
    </a>
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="cphContenido" runat="server">
    <!-- Pantalla 1: Formulario Vacío -->
    <div id="pantallaLogin" class="login-card">
        <div class="form-group">
            <label class="form-label">Usuario</label>
            <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control-custom" 
                TextMode="SingleLine" placeholder="" onkeyup="checkInputs()"></asp:TextBox>
        </div>

        <div class="form-group">
            <label class="form-label">Password</label>
            <asp:TextBox ID="txtPassword" runat="server" CssClass="form-control-custom" 
                TextMode="Password" placeholder="" onkeyup="checkInputs()"></asp:TextBox>
        </div>

        <asp:Button ID="btnSignIn" runat="server" CssClass="btn-signin" Text="Sign In" OnClick="btnSignIn_Click" />

        <a href="InicioSesion.aspx" class="forgot-password" onclick="return false;">Forgot password?</a>
    </div>

    <!-- Bienvenida -->
    <div id="modalBienvenida" class="welcome-modal">
        <div class="welcome-content">
            <h2>!Bienvenido!</h2>
            <p id="nombreUsuario">Juan Perez</p>
            <button type="button" class="btn-accept" onclick="aceptarBienvenida()">
                Aceptar
            </button>
        </div>
    </div>

    <!-- Scripts realizados con ayuda de Claude AI -->
    <script>
        // Función para revisar si los inputs tienen contenido
        function checkInputs() {
            const emailInput = document.getElementById('<%= txtEmail.ClientID %>');
            const passwordInput = document.getElementById('<%= txtPassword.ClientID %>');

            // Aplicar estilo si tienen contenido
            if (emailInput.value.trim() !== '') {
                emailInput.classList.add('input-filled');
            } else {
                emailInput.classList.remove('input-filled');
            }

            if (passwordInput.value.trim() !== '') {
                passwordInput.classList.add('input-filled', 'password-filled');
            } else {
                passwordInput.classList.remove('input-filled', 'password-filled');
            }
        }

        function iniciarSesion() {
            const email = document.getElementById('<%= txtEmail.ClientID %>').value.trim();
            const password = document.getElementById('<%= txtPassword.ClientID %>').value.trim();

            // Validación básica
            if (!email || !password) {
                alert('Por favor complete todos los campos');
                return;
            }

            if (!validateEmail(email)) {
                alert('Por favor ingrese un email válido');
                return;
            }

            // Simular verificación de credenciales
            if (email === 'admin@gmail.com' && password === '123456') {
                // Usuario administrador
                mostrarBienvenida('Administrador', 'GestionarPedidos01.aspx');
            } else if (email === 'cliente@gmail.com' && password === '123456') {
                // Usuario cliente
                mostrarBienvenida('Cliente', 'Index.aspx');
            } else {
                //alert('Credenciales incorrectas');
            }
        }

        function validateEmail(email) {
            alert('Ingrese un usuario válido');
            return;
        }

        function mostrarBienvenida(nombre, destino) {
            document.getElementById('nombreUsuario').textContent = nombre;
            document.getElementById('modalBienvenida').classList.add('show');

            // Guardar el destino temporalmente
            document.getElementById('modalBienvenida').dataset.destino = destino;
        }

        function aceptarBienvenida() {
            document.getElementById('modalBienvenida').classList.remove('show');
            // Redirigir al destino guardado
            const destino = document.getElementById('modalBienvenida').dataset.destino || 'Index.aspx';
            window.location.href = destino;
        }

        // Permitir presionar Enter para iniciar sesión
        document.addEventListener('DOMContentLoaded', function () {
            const inputs = document.querySelectorAll('.form-control-custom');
            inputs.forEach(input => {
                input.addEventListener('keypress', function (e) {
                    if (e.key === 'Enter') {
                        iniciarSesion();
                    }
                });
            });
        });
    </script>

    <asp:HiddenField ID="hdnUsuarioNombre" runat="server" />
</asp:Content>