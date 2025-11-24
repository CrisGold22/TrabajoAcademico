<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" 
    CodeBehind="RegistrarEmpresa.aspx.cs" Inherits="SoftProgWeb.RegistrarEmpresa"
    UnobtrusiveValidationMode="None" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
    Registrar Empresa - REDCOM
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="perfil-header">
        <div class="container">
            <!-- Header rosado (igual que PerfilCliente) -->
        </div>
    </div>

    <div class="perfil-container">
        <div class="perfil-card">
            <div class="profile-avatar-section">
                <div class="profile-info-header flex-grow-1">
                    <h2>Registrar Nueva Empresa</h2>
                    <p>Complete los datos de la empresa que será asociada a su perfil.</p>
                </div>
            </div>

            <!-- Mensajes -->
            <asp:Panel ID="pnlMensaje" runat="server" Visible="false" CssClass="alert" role="alert">
                <asp:Literal ID="litMensaje" runat="server"></asp:Literal>
            </asp:Panel>

            <asp:ValidationSummary ID="vsErrores" runat="server" CssClass="text-danger" 
                HeaderText="Por favor corrija los siguientes errores:" />

            <!-- FORMULARIO EMPRESA -->
            <div class="form-empresa">
                <!-- RUC & Razón Social -->
                <div class="form-row-custom">
                    <div class="form-group-perfil">
                        <label class="form-label-perfil">RUC <span class="required">*</span></label>
                        <asp:TextBox ID="txtRUC" runat="server" CssClass="form-control-perfil"
                            MaxLength="11" placeholder="Ingrese RUC"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvRUC" runat="server"
                            ControlToValidate="txtRUC" ErrorMessage="El RUC es obligatorio."
                            CssClass="text-danger" Display="Dynamic" />
                        <asp:RegularExpressionValidator ID="revRUC" runat="server"
                            ControlToValidate="txtRUC"
                            ValidationExpression="^\d{11}$"
                            ErrorMessage="El RUC debe tener 11 dígitos numéricos."
                            CssClass="text-danger" Display="Dynamic" />
                    </div>

                    <div class="form-group-perfil">
                        <label class="form-label-perfil">Razón Social <span class="required">*</span></label>
                        <asp:TextBox ID="txtRazonSocial" runat="server" CssClass="form-control-perfil"
                            placeholder="Ingrese Razón Social"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvRazon" runat="server"
                            ControlToValidate="txtRazonSocial" ErrorMessage="La Razón Social es obligatoria."
                            CssClass="text-danger" Display="Dynamic" />
                    </div>
                </div>

                <!-- Dirección Fiscal -->
                <div class="form-row-custom">
                    <div class="form-group-perfil" style="flex: 1;">
                        <label class="form-label-perfil">Dirección Fiscal <span class="required">*</span></label>
                        <asp:TextBox ID="txtDireccionFiscal" runat="server" CssClass="form-control-perfil"
                            placeholder="Ingrese Dirección Fiscal"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvDir" runat="server"
                            ControlToValidate="txtDireccionFiscal" ErrorMessage="La dirección fiscal es obligatoria."
                            CssClass="text-danger" Display="Dynamic" />
                    </div>
                </div>

                <!-- Departamento / Provincia -->
                <div class="form-row-custom">
                    <div class="form-group-perfil">
                        <label class="form-label-perfil">Departamento <span class="required">*</span></label>
                        <asp:TextBox ID="txtDepartamento" runat="server" CssClass="form-control-perfil"
                            placeholder="Ej: Lima"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvDep" runat="server"
                            ControlToValidate="txtDepartamento" ErrorMessage="El departamento es obligatorio."
                            CssClass="text-danger" Display="Dynamic" />
                    </div>

                    <div class="form-group-perfil">
                        <label class="form-label-perfil">Provincia <span class="required">*</span></label>
                        <asp:TextBox ID="txtProvincia" runat="server" CssClass="form-control-perfil"
                            placeholder="Ej: Lima"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvProv" runat="server"
                            ControlToValidate="txtProvincia" ErrorMessage="La provincia es obligatoria."
                            CssClass="text-danger" Display="Dynamic" />
                    </div>
                </div>

                <!-- Distrito / Código Postal -->
                <div class="form-row-custom">
                    <div class="form-group-perfil">
                        <label class="form-label-perfil">Distrito <span class="required">*</span></label>
                        <asp:TextBox ID="txtDistrito" runat="server" CssClass="form-control-perfil"
                            placeholder="Ej: Miraflores"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvDist" runat="server"
                            ControlToValidate="txtDistrito" ErrorMessage="El distrito es obligatorio."
                            CssClass="text-danger" Display="Dynamic" />
                    </div>

                    <div class="form-group-perfil">
                        <label class="form-label-perfil">Código Postal</label>
                        <asp:TextBox ID="txtCodigoPostal" runat="server" CssClass="form-control-perfil"
                            placeholder="Ej: 15074"></asp:TextBox>
                    </div>
                </div>

                <!-- Teléfono / Email -->
                <div class="form-row-custom">
                    <div class="form-group-perfil">
                        <label class="form-label-perfil">Teléfono <span class="required">*</span></label>
                        <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control-perfil"
                            placeholder="Ej: 999 999 999"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvTel" runat="server"
                            ControlToValidate="txtTelefono" ErrorMessage="El teléfono es obligatorio."
                            CssClass="text-danger" Display="Dynamic" />
                    </div>

                    <div class="form-group-perfil">
                        <label class="form-label-perfil">Email <span class="required">*</span></label>
                        <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control-perfil"
                            TextMode="Email" placeholder="empresa@email.com"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="rfvEmail" runat="server"
                            ControlToValidate="txtEmail" ErrorMessage="El email es obligatorio."
                            CssClass="text-danger" Display="Dynamic" />
                        <asp:RegularExpressionValidator ID="revEmail" runat="server"
                            ControlToValidate="txtEmail"
                            ValidationExpression="^[^\s@]+@[^\s@]+\.[^\s@]+$"
                            ErrorMessage="Ingrese un email válido."
                            CssClass="text-danger" Display="Dynamic" />
                    </div>
                </div>
            </div>

            <!-- BOTONES -->
            <div class="form-row-custom" style="margin-top: 20px; justify-content:flex-end;">
                <asp:Button ID="btnCancelar" runat="server" Text="Cancelar"
                    CssClass="btn btn-secondary" CausesValidation="false"
                    OnClick="btnCancelar_Click" />
                &nbsp;
                <asp:Button ID="btnRegistrar" runat="server" Text="Registrar Empresa"
                    CssClass="btn btn-primary"
                    OnClick="btnRegistrar_Click" />
            </div>

        </div>
    </div>

    <style>
        .form-empresa {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
        .required {
            color: #f44336;
        }
        .no-empresas {
            margin-top: 10px;
            color: #777;
        }
        .text-danger {
            color: #dc3545;
            font-size: 0.9rem;
        }
        .btn-primary {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border: none;
            padding: 10px 25px;
            border-radius: 8px;
            font-weight: 600;
        }
        .btn-secondary {
            background: #e0e0e0;
            border: none;
            padding: 10px 25px;
            border-radius: 8px;
            font-weight: 600;
            color: #333;
        }
        .btn-primary:hover {
            opacity: 0.9;
        }
        .btn-secondary:hover {
            background: #d0d0d0;
        }
    </style>
</asp:Content>
