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
                    <h2 id="displayName"></h2>
                    <p id="displayEmail"></p>
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
                    <label class="form-label-perfil">Apellido</label>
                    <asp:TextBox ID="txtApellido" runat="server" CssClass="form-control-perfil" 
                        placeholder="Perez Martinez" Enabled="false"></asp:TextBox>
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
                        <div class="email-address" id="emailPrincipal"></div>
                        <div class="email-date">now</div>
                    </div>
                </div>
            </div>

            <div class="empresas-button-section" style="margin-top: 20px;">
                <asp:Button ID="btnMisEmpresas" runat="server" CssClass="btn-empresas" 
                    Text="Mis Empresas" OnClick="btnMisEmpresas_Click" />
            </div>

            <!-- Modal Principal: Lista de Empresas -->
            <div class="modal-empresas" id="modalMisEmpresas">
                <div class="modal-empresas-content">
                    <div class="modal-empresas-header">
                        <h3>Mis Empresas</h3>
                        <button class="close-btn" onclick="closeModalEmpresas('modalMisEmpresas')">&times;</button>
                    </div>
        
                    <div class="modal-empresas-body">
                        <!-- Lista de empresas -->
                        <div class="empresas-lista">
                            <asp:Repeater ID="repEmpresas" runat="server">
                                <ItemTemplate>
                                    <div class="empresa-card">
                                        <div class="empresa-info">
                                            <h4><%# Eval("razonSocial") %></h4>
                                            <p><strong>RUC:</strong> <%# Eval("RUC") %></p>
                                            <p><strong>Dirección:</strong> <%# Eval("direccionFiscal") %></p>
                                            <p><strong>Teléfono:</strong> <%# Eval("telefono") %></p>
                                            <p><strong>Email:</strong> <%# Eval("email") %></p>
                                        </div>
                                        <div class="empresa-actions">
                                            <span class="badge-activo">Activa</span>
                                        </div>
                                    </div>
                                </ItemTemplate>
                            </asp:Repeater>
                
                            <!-- Mensaje cuando no hay empresas -->
                            <div id="mensajeNoEmpresas" runat="server" visible="false" class="no-empresas-message">
                                <i class="fas fa-building"></i>
                                <p>Aún no tienes empresas registradas</p>
                            </div>
                        </div>
                    </div>
        
                    <div class="modal-empresas-footer">
                        <button class="btn-agregar-empresa" onclick="openModalAgregarEmpresa(event); return false;">
                            <i class="fas fa-plus"></i> Agregar Empresa
                        </button>
                    </div>
                </div>
            </div>

            <!-- Modal Secundario: Agregar Nueva Empresa -->
            <div class="modal-empresas" id="modalAgregarEmpresa">
                <div class="modal-empresas-content modal-agregar">
                    <div class="modal-empresas-header">
                        <h3>Agregar Nueva Empresa</h3>
                        <button class="close-btn" onclick="closeModalAgregarEmpresa()">&times;</button>
                    </div>
        
                    <div class="modal-empresas-body">
                        <div class="form-empresa">
                            <div class="form-row-custom">
                                <div class="form-group-perfil">
                                    <label class="form-label-perfil">RUC <span class="required">*</span></label>
                                    <asp:TextBox ID="txtRucEmp" runat="server" CssClass="form-control-perfil" 
                                        placeholder="Ingrese RUC" MaxLength="11"></asp:TextBox>
                                </div>
                    
                                <div class="form-group-perfil">
                                    <label class="form-label-perfil">Razón Social <span class="required">*</span></label>
                                    <asp:TextBox ID="txtRazonEmp" runat="server" CssClass="form-control-perfil" 
                                        placeholder="Ingrese Razón Social"></asp:TextBox>
                                </div>
                            </div>
                
                            <div class="form-row-custom">
                                <div class="form-group-perfil">
                                    <label class="form-label-perfil">Dirección Fiscal <span class="required">*</span></label>
                                    <asp:TextBox ID="txtDirEmp" runat="server" CssClass="form-control-perfil" 
                                        placeholder="Ingrese Dirección"></asp:TextBox>
                                </div>
                            </div>
                
                            <div class="form-row-custom">
                                <div class="form-group-perfil">
                                    <label class="form-label-perfil">Departamento <span class="required">*</span></label>
                                    <asp:TextBox ID="txtDeparta" runat="server" CssClass="form-control-perfil" 
                                        placeholder="Ej: Lima"></asp:TextBox>
                                </div>
                    
                                <div class="form-group-perfil">
                                    <label class="form-label-perfil">Provincia <span class="required">*</span></label>
                                    <asp:TextBox ID="txtProvincia" runat="server" CssClass="form-control-perfil" 
                                        placeholder="Ej: Lima"></asp:TextBox>
                                </div>
                            </div>
                
                            <div class="form-row-custom">
                                <div class="form-group-perfil">
                                    <label class="form-label-perfil">Distrito <span class="required">*</span></label>
                                    <asp:TextBox ID="txtDistrito" runat="server" CssClass="form-control-perfil" 
                                        placeholder="Ej: Miraflores"></asp:TextBox>
                                </div>
                    
                                <div class="form-group-perfil">
                                    <label class="form-label-perfil">Código Postal</label>
                                    <asp:TextBox ID="txtCodPost" runat="server" CssClass="form-control-perfil" 
                                        placeholder="Ej: 15074"></asp:TextBox>
                                </div>
                            </div>
                
                            <div class="form-row-custom">
                                <div class="form-group-perfil">
                                    <label class="form-label-perfil">Teléfono <span class="required">*</span></label>
                                    <asp:TextBox ID="txtTelEmp" runat="server" CssClass="form-control-perfil" 
                                        placeholder="Ej: 999 999 999"></asp:TextBox>
                                </div>
                    
                                <div class="form-group-perfil">
                                    <label class="form-label-perfil">Email <span class="required">*</span></label>
                                    <asp:TextBox ID="txtEmailEmp" runat="server" CssClass="form-control-perfil" 
                                        placeholder="empresa@email.com" TextMode="Email"></asp:TextBox>
                                </div>
                            </div>
                        </div>
                    </div>
        
                    <div class="modal-empresas-footer">
                        <button class="btn-cancelar" onclick="closeModalAgregarEmpresa()">
                            Cancelar
                        </button>
                        <asp:Button ID="btnRegistrarEmpresa" runat="server" CssClass="btn-modal-action btn-accept" 
                            Text="Agregar" OnClick="btnRegistrarEmpresa_Click" 
                            OnClientClick="return validarFormularioEmpresa();" />
                    </div>
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

   <%-- script creado con ayuda de chat gpt--%>
    <script>
        let editMode = false;

        // Toggle entre modo edición y visualización
        function toggleEditMode() {
            editMode = !editMode;
            const btnEdit = document.getElementById('<%= btnEdit.ClientID %>');
            const inputs = [
                '<%= txtNombre.ClientID %>',
                '<%= txtApellido.ClientID %>',
               <%-- '<%= txtDireccion.ClientID %>',--%>
               <%-- '<%= txtRuc.ClientID %>',--%>
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

        // Abrir modal de Mis Empresas
        function openModalEmpresas(modalId) {
            document.getElementById(modalId).classList.add('show');
        }

        // Cerrar modal de Mis Empresas
        function closeModalEmpresas(modalId) {
            document.getElementById(modalId).classList.remove('show');
        }

        // Abrir modal de Agregar Empresa
        function openModalAgregarEmpresa(event) {
            if (event) {
                event.preventDefault();
                event.stopPropagation();
            }
            document.getElementById('modalAgregarEmpresa').classList.add('show');
        }

        // Cerrar modal de Agregar Empresa
        function closeModalAgregarEmpresa() {
            document.getElementById('modalAgregarEmpresa').classList.remove('show');
        }

        // Validar formulario de empresa antes de enviar
        function validarFormularioEmpresa() {
            const ruc = document.getElementById('<%= txtRucEmp.ClientID %>').value.trim();
        const razon = document.getElementById('<%= txtRazonEmp.ClientID %>').value.trim();
        const direccion = document.getElementById('<%= txtDirEmp.ClientID %>').value.trim();
        const departamento = document.getElementById('<%= txtDeparta.ClientID %>').value.trim();
        const provincia = document.getElementById('<%= txtProvincia.ClientID %>').value.trim();
        const distrito = document.getElementById('<%= txtDistrito.ClientID %>').value.trim();
        const telefono = document.getElementById('<%= txtTelEmp.ClientID %>').value.trim();
            const email = document.getElementById('<%= txtEmailEmp.ClientID %>').value.trim();

            if (!ruc || !razon || !direccion || !departamento || !provincia ||
                !distrito || !telefono || !email) {
                alert('Por favor, complete todos los campos obligatorios.');
                return false;
            }

            if (ruc.length !== 11) {
                alert('El RUC debe tener 11 dígitos.');
                return false;
            }

            if (!/^\d+$/.test(ruc)) {
                alert('El RUC debe contener solo números.');
                return false;
            }

            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(email)) {
                alert('Por favor, ingrese un email válido.');
                return false;
            }

            return true;
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
        .btn-empresas {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            padding: 12px 30px;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);
        }
    
        .btn-empresas:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(102, 126, 234, 0.4);
        }
    
        .modal-empresas {
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            animation: fadeIn 0.3s ease;
        }
    
        .modal-empresas.show {
            display: flex;
            align-items: center;
            justify-content: center;
        }
    
        .modal-empresas-content {
            background: white;
            border-radius: 12px;
            width: 90%;
            max-width: 800px;
            max-height: 85vh;
            display: flex;
            flex-direction: column;
            animation: slideUp 0.3s ease;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
        }
    
        .modal-agregar {
            max-width: 700px;
        }
    
        .modal-empresas-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px 30px;
            border-bottom: 1px solid #e0e0e0;
        }
    
        .modal-empresas-header h3 {
            margin: 0;
            color: #333;
            font-size: 24px;
        }
    
        .modal-empresas-body {
            padding: 20px 30px;
            overflow-y: auto;
            flex: 1;
        }
    
        .empresas-lista {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
    
        .empresa-card {
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            padding: 20px;
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            transition: all 0.3s ease;
            background: #f9f9f9;
        }
    
        .empresa-card:hover {
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            transform: translateY(-2px);
        }
    
        .empresa-info h4 {
            margin: 0 0 10px 0;
            color: #667eea;
            font-size: 18px;
        }
    
        .empresa-info p {
            margin: 5px 0;
            color: #555;
            font-size: 14px;
        }
    
        .badge-activo {
            background: #4caf50;
            color: white;
            padding: 6px 12px;
            border-radius: 20px;
            font-size: 12px;
            font-weight: 600;
        }
    
        .no-empresas-message {
            text-align: center;
            padding: 60px 20px;
            color: #999;
        }
    
        .no-empresas-message i {
            font-size: 64px;
            margin-bottom: 20px;
            opacity: 0.3;
        }
    
        .no-empresas-message p {
            font-size: 18px;
            margin: 0;
        }
    
        .modal-empresas-footer {
            padding: 20px 30px;
            border-top: 1px solid #e0e0e0;
            display: flex;
            justify-content: flex-end;
            gap: 10px;
        }
    
        .btn-agregar-empresa {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            padding: 12px 24px;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            display: flex;
            align-items: center;
            gap: 8px;
            transition: all 0.3s ease;
        }
    
        .btn-agregar-empresa:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);
        }
    
        .btn-cancelar {
            background: #e0e0e0;
            color: #333;
            border: none;
            padding: 12px 24px;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
        }
    
        .btn-cancelar:hover {
            background: #d0d0d0;
        }
    
        .form-empresa {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
    
        .required {
            color: #f44336;
        }
    
        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
    
        @keyframes slideUp {
            from {
                transform: translateY(50px);
                opacity: 0;
            }
            to {
                transform: translateY(0);
                opacity: 1;
            }
        }
    </style>
</asp:Content>
