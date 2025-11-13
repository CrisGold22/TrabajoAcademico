<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="PerfilCliente.aspx.cs" Inherits="SoftProgWeb.PerfilCliente" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
    Mi Perfil - REDCOM
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="perfil-header">
        <div class="container">
            <!-- Espacio para el header rosado -->
        </div>
    </div>

    <div class="perfil-container">
        <div class="perfil-card">
            <!-- Sección de Avatar y Nombre -->
            <div class="profile-avatar-section">
                <div class="avatar-circle">
                    <img src="images/22bdc07ec31844f3ceb7bfc5fc64d16724d79af6.png" 
                         alt="Profile Picture" id="imgAvatar" />
                </div>
                <div class="profile-info-header flex-grow-1">
                    <h2 id="displayName">Juan Perez Martinez</h2>
                    <p id="displayEmail">cliente@gmail.com</p>
                </div>
                <div>
                    <asp:Button ID="btnEdit" runat="server" CssClass="btn-edit-profile" 
                        Text="Edit" OnClientClick="toggleEditMode(); return false;" />
                </div>
            </div>

            <!-- Formulario de datos -->
            <div class="form-row-custom">
                <div class="form-group-perfil">
                    <label class="form-label-perfil">Nombre</label>
                    <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control-perfil" 
                        placeholder="Juan" Enabled="false"></asp:TextBox>
                </div>
                <div class="form-group-perfil">
                    <label class="form-label-perfil">Dirección</label>
                    <asp:TextBox ID="txtDireccion" runat="server" CssClass="form-control-perfil" 
                        placeholder="Mz L 20 Av argentina" Enabled="false"></asp:TextBox>
                </div>
            </div>

            <div class="form-row-custom">
                <div class="form-group-perfil">
                    <label class="form-label-perfil">Apellido</label>
                    <asp:TextBox ID="txtApellido" runat="server" CssClass="form-control-perfil" 
                        placeholder="Perez Martinez" Enabled="false"></asp:TextBox>
                </div>
                <div class="form-group-perfil">
                    <label class="form-label-perfil">Ruc</label>
                    <asp:TextBox ID="txtRuc" runat="server" CssClass="form-control-perfil" 
                        placeholder="2020320030023" Enabled="false"></asp:TextBox>
                </div>
            </div>

            <div class="form-row-custom">
                <div class="form-group-perfil">
                    <label class="form-label-perfil">Contraseña</label>
                    <asp:TextBox ID="txtPassword" runat="server" CssClass="form-control-perfil" 
                        TextMode="Password" placeholder="**********" ReadOnly="true"></asp:TextBox>
                </div>
                <div class="form-group-perfil">
                    <label class="form-label-perfil">Telefono</label>
                    <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control-perfil" 
                        placeholder="999 999 999" Enabled="false"></asp:TextBox>
                </div>
            </div>

            <!-- Sección de Emails -->
            <div class="email-section">
                <h4>My email Address</h4>
                <div class="email-item">
                    <div class="email-icon">
                        <i class="fas fa-envelope"></i>
                    </div>
                    <div class="email-content">
                        <div class="email-address" id="emailPrincipal">JuanPerez@gmail.com</div>
                        <div class="email-date">1 day ago</div>
                    </div>
                    <button class="btn-remove-email" type="button" onclick="removeEmail()">
                        <i class="fas fa-times"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal de Ingreso de Contraseña -->
    <div class="password-modal" id="passwordModal">
        <div class="password-modal-content">
            <div class="password-modal-header">
                <h3>Ingrese Contraseña:</h3>
            </div>
            <div class="password-input-group">
                <div class="password-input-wrapper">
                    <asp:TextBox ID="txtCurrentPassword" runat="server" CssClass="form-control-perfil" 
                        TextMode="Password" placeholder="********"></asp:TextBox>
                    <i class="fas fa-eye eye-icon" id="toggleCurrentPassword"></i>
                </div>
            </div>
            <div class="password-input-group">
                <div class="password-input-wrapper">
                    <asp:TextBox ID="txtNewPassword" runat="server" CssClass="form-control-perfil" 
                        TextMode="Password" placeholder="********"></asp:TextBox>
                    <i class="fas fa-eye eye-icon" id="toggleNewPassword"></i>
                </div>
            </div>
            <asp:Button ID="btnChangePassword" runat="server" CssClass="btn-modal-action btn-accept" 
                Text="Aceptar" OnClientClick="return validatePasswordChange();" OnClick="btnChangePassword_Click" />
        </div>
    </div>

    <!-- Modal de Confirmación de Cambio -->
    <div class="confirmation-modal" id="confirmationModal">
        <div class="confirmation-content">
            <button class="close-btn" onclick="closeConfirmationModal()">&times;</button>
            <div class="success-message">
                <i class="fas fa-check-circle"></i> Éxito
            </div>
            <h3>Se cambio con éxito la contraseña</h3>
            <p>Su contraseña ha sido actualizada correctamente.</p>
            <button class="btn-modal-action btn-accept" onclick="closeConfirmationModal()">
                Aceptar
            </button>
        </div>
    </div>

    <script>
        let editMode = false;

        // Toggle entre modo edición y visualización
        function toggleEditMode() {
            editMode = !editMode;
            const btnEdit = document.getElementById('<%= btnEdit.ClientID %>');
            const inputs = [
                '<%= txtNombre.ClientID %>',
                '<%= txtApellido.ClientID %>',
                '<%= txtDireccion.ClientID %>',
                '<%= txtRuc.ClientID %>',
                '<%= txtTelefono.ClientID %>'
            ];

            if (editMode) {
                // Modo edición
                btnEdit.textContent = 'No Editar';
                inputs.forEach(id => {
                    const input = document.getElementById(id);
                    input.disabled = false;
                    input.classList.remove('filled');
                });
            } else {
                // Modo visualización
                btnEdit.textContent = 'Edit';
                inputs.forEach(id => {
                    const input = document.getElementById(id);
                    input.disabled = true;
                    if (input.value.trim() !== '') {
                        input.classList.add('filled');
                    }
                });

                //para guardar cambios, pendiente con el back
                // __doPostBack('btnGuardarCambios', '');
            }

            // Manejar contraseña - siempre mostrar modal
            const txtPassword = document.getElementById('<%= txtPassword.ClientID %>');
            txtPassword.readOnly = true;

            if (editMode) {
                txtPassword.onclick = function() {
                    showPasswordModal();
                };
                txtPassword.style.cursor = 'pointer';
            } else {
                txtPassword.onclick = null;
                txtPassword.style.cursor = 'not-allowed';
            }
        }

        // Mostrar modal de contraseña
        function showPasswordModal() {
            document.getElementById('passwordModal').classList.add('show');
        }

        // Cerrar modal de contraseña
        function closePasswordModal() {
            document.getElementById('passwordModal').classList.remove('show');
            document.getElementById('<%= txtCurrentPassword.ClientID %>').value = '';
            document.getElementById('<%= txtNewPassword.ClientID %>').value = '';
        }

        // Validar cambio de contraseña
        function validatePasswordChange() {
            const currentPassword = document.getElementById('<%= txtCurrentPassword.ClientID %>').value;
            const newPassword = document.getElementById('<%= txtNewPassword.ClientID %>').value;

            if (!currentPassword || !newPassword) {
                alert('Por favor, complete ambos campos de contraseña.');
                return false;
            }

            if (newPassword.length < 6) {
                alert('La nueva contraseña debe tener al menos 6 caracteres.');
                return false;
            }

            if (currentPassword === newPassword) {
                alert('La nueva contraseña debe ser diferente a la actual.');
                return false;
            }

            return true;
        }

        // Mostrar modal de confirmación
        function showConfirmationModal() {
            closePasswordModal();
            document.getElementById('confirmationModal').classList.add('show');
        }

        // Cerrar modal de confirmación
        function closeConfirmationModal() {
            document.getElementById('confirmationModal').classList.remove('show');
        }

        // Toggle mostrar/ocultar contraseña
        document.getElementById('toggleCurrentPassword').addEventListener('click', function() {
            togglePasswordVisibility('<%= txtCurrentPassword.ClientID %>', this);
        });

        document.getElementById('toggleNewPassword').addEventListener('click', function() {
            togglePasswordVisibility('<%= txtNewPassword.ClientID %>', this);
        });

        function togglePasswordVisibility(inputId, icon) {
            const input = document.getElementById(inputId);
            if (input.type === 'password') {
                input.type = 'text';
                icon.classList.remove('fa-eye');
                icon.classList.add('fa-eye-slash');
            } else {
                input.type = 'password';
                icon.classList.remove('fa-eye-slash');
                icon.classList.add('fa-eye');
            }
        }

        // Remover email (simulado)
        function removeEmail() {
            if (confirm('¿Está seguro de que desea eliminar este correo electrónico?')) {
                // logica con el backend para eliminar correo
                alert('Correo eliminado');
            }
        }

        // Cerrar modales al hacer clic fuera
        window.onclick = function(event) {
            const passwordModal = document.getElementById('passwordModal');
            const confirmationModal = document.getElementById('confirmationModal');
            
            if (event.target === passwordModal) {
                closePasswordModal();
            }
            if (event.target === confirmationModal) {
                closeConfirmationModal();
            }
        }

        // Cargar datos al iniciar (simulado)
        window.onload = function() {
            // Marcar campos con datos como "filled"
            const inputs = document.querySelectorAll('.form-control-perfil');
            inputs.forEach(input => {
                if (input.value.trim() !== '' && input.disabled) {
                    input.classList.add('filled');
                }
            });
        }
    </script>
</asp:Content>
