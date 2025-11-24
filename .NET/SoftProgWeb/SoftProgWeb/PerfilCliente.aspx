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
                    <h2>
                        <asp:Label ID="lblDisplayName" runat="server" />
                    </h2>
                    <p>
                        <asp:Label ID="lblDisplayEmail" runat="server" />
                    </p>
                </div>
                <div>
                    <asp:Button ID="btnEdit" runat="server" CssClass="btn-edit-profile" Text="Editar datos" OnClick="btnEdit_Click" />

                </div>
            </div>

            <!-- Formulario de datos -->
            <div class="form-row-custom">
                <div class="form-group-perfil">
                    <label class="form-label-perfil">Nombre</label>
                    <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control-perfil" 
                        placeholder="" Enabled="false"></asp:TextBox>
                </div>

                <div class="form-group-perfil">
                    <label class="form-label-perfil">Apellido</label>
                    <asp:TextBox ID="txtApellido" runat="server" CssClass="form-control-perfil" 
                        placeholder="" Enabled="false"></asp:TextBox>
                </div>
            </div>

            <div class="form-row-custom">
                <div class="form-group-perfil">
                    <label class="form-label-perfil">Contraseña</label>
                    <asp:TextBox ID="txtPassword" runat="server" CssClass="form-control-perfil" 
                        placeholder="**********" Enabled="false"></asp:TextBox>
                </div>
                <div class="form-group-perfil">
                    <label class="form-label-perfil">Telefono</label>
                    <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control-perfil" 
                        placeholder="" Enabled="false"></asp:TextBox>
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
                        <div class="email-address">
                            <asp:Label ID="lblEmailPrincipal" runat="server" />
                        </div>
                        <div class="email-date">now</div>
                    </div>
                </div>
            </div>


            <div class="empresas-section">
                <div class="empresas-header">
                    <div class="empresas-title">
                        <i class="fas fa-building"></i>
                        <span>Mis Empresas</span>
                    </div>

                    <asp:Button ID="btnIrAgregarEmpresa" runat="server"
                        Text="Agregar Empresa"
                        CssClass="btn-agregar-empresa"
                        OnClick="btnIrAgregarEmpresa_Click" />
                </div>

                <div class="empresas-list">
                    <asp:Repeater ID="repEmpresas" runat="server" OnItemCommand="repEmpresas_ItemCommand">
                        <ItemTemplate>
                            <div class="empresa-card">
                                <div class="empresa-card-header">
                                    <h4 class="empresa-nombre"><%# Eval("razonSocial") %></h4>

                                    <asp:LinkButton ID="btnEliminarEmpresa" runat="server"
                                        CssClass="btn-trash"
                                        CommandName="EliminarEmpresa"
                                        CommandArgument='<%# Eval("id") %>'
                                        OnClientClick="return confirm('¿Seguro que desea eliminar esta empresa?');">
                                        <i class="fas fa-trash"></i>
                                    </asp:LinkButton>
                                </div>

                                <div class="empresa-detalle">
                                    <p><strong>RUC:</strong> <%# Eval("RUC") %></p>
                                    <p><strong>Dirección:</strong> <%# Eval("direccionFiscal") %></p>
                                    <p><strong>Email:</strong> <%# Eval("email") %></p>
                                    <p><strong>Teléfono:</strong> <%# Eval("telefono") %></p>
                                </div>
                            </div>
                        </ItemTemplate>
                    </asp:Repeater>
                </div>

                <asp:Label ID="lblNoEmpresas" runat="server"
                    CssClass="no-empresas"
                    Visible="false"
                    Text="No tienes empresas registradas.">
                </asp:Label>
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

   <%-- script creado con ayuda de chat gpt--%>
    <script>
        let editMode = false;

        
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

        // Cerrar modales al hacer clic fuera de ellos
        window.onclick = function (event) {
            const modalMisEmpresas = document.getElementById('modalMisEmpresas');
            const modalAgregar = document.getElementById('modalAgregarEmpresa');
            const passwordModal = document.getElementById('passwordModal');
            const confirmationModal = document.getElementById('confirmationModal');

            if (event.target === modalMisEmpresas) {
                closeModalEmpresas('modalMisEmpresas');
            }
            if (event.target === modalAgregar) {
                closeModalAgregarEmpresa();
            }
            if (event.target === passwordModal) {
                closePasswordModal();
            }
            if (event.target === confirmationModal) {
                closeConfirmationModal();
            }
        }
    </script>

    <style>
        /* Sección de empresas */
        .empresas-section {
            margin-top: 40px;
            padding-top: 25px;
            border-top: 1px solid #e5e5e5;
        }

        .empresas-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 18px;
        }

        .empresas-title {
            display: flex;
            align-items: center;
            gap: 8px;
            font-size: 20px;
            font-weight: 600;
            color: #333;
        }

        .empresas-title i {
            font-size: 22px;
            color: #667eea;
        }

        /* Botón "Agregar Empresa" */
        .btn-agregar-empresa {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: #fff;
            border: none;
            padding: 10px 22px;
            border-radius: 999px;
            font-size: 14px;
            font-weight: 600;
            cursor: pointer;
            box-shadow: 0 4px 12px rgba(102, 126, 234, 0.35);
            transition: all 0.25s ease;
        }

        .btn-agregar-empresa:hover {
            transform: translateY(-1px);
            box-shadow: 0 6px 18px rgba(102, 126, 234, 0.45);
            opacity: 0.95;
        }

        /* Lista de empresas */
        .empresas-list {
            display: flex;
            flex-direction: column;
            gap: 12px;
        }

        /* Card de empresa */
        .empresa-card {
            background-color: #fafafa;
            border-radius: 12px;
            padding: 14px 18px;
            border: 1px solid #e3e3e3;
            display: flex;
            justify-content: space-between;
            gap: 18px;
            align-items: flex-start;
            transition: all 0.25s ease;
        }

        .empresa-card:hover {
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
            transform: translateY(-2px);
            background-color: #ffffff;
        }

        .empresa-main h4 {
            margin: 0 0 6px 0;
            font-size: 16px;
            font-weight: 600;
            color: #333;
        }

        .empresa-ruc {
            font-size: 13px;
            color: #777;
        }

        .empresa-meta p {
            margin: 2px 0;
            font-size: 13px;
            color: #555;
            display: flex;
            align-items: center;
            gap: 6px;
        }

        .empresa-meta i {
            font-size: 13px;
            color: #667eea;
        }

        /* Mensaje cuando no hay empresas */
        .no-empresas {
            display: block;
            margin-top: 10px;
            padding: 18px;
            border-radius: 10px;
            background-color: #fdf2f2;
            color: #b91c1c;
            font-size: 14px;
            text-align: center;
            border: 1px solid #fecaca;
        }

        .empresa-card {
            border: 1px solid #e0e0e0;
            border-radius: 10px;
            padding: 16px 20px;
            margin-bottom: 12px;
            background: #f9fafb;
            box-shadow: 0 2px 6px rgba(0,0,0,0.04);
        }

        .empresa-card-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 8px;
        }

        .empresa-nombre {
            margin: 0;
            font-size: 16px;
            font-weight: 600;
            color: #374151;
        }

        .empresa-detalle p {
            margin: 2px 0;
            font-size: 14px;
            color: #4b5563;
        }

        .btn-trash {
            background: transparent;
            border: none;
            padding: 4px 6px;
            cursor: pointer;
            color: #ef4444;
            border-radius: 50%;
            transition: background 0.2s ease, transform 0.1s ease;
        }

        .btn-trash i {
            font-size: 16px;
        }

        .btn-trash:hover {
            background: rgba(239, 68, 68, 0.08);
            transform: translateY(-1px);
        }
    </style>
</asp:Content>
