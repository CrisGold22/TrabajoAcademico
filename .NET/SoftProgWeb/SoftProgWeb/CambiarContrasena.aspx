
<%@ Page Title="Cambiar contraseña" Language="C#" MasterPageFile="~/SoftProg.Master"
    AutoEventWireup="true"
    CodeBehind="CambiarContrasena.aspx.cs"
    Inherits="SoftProgWeb.CambiarContrasena"
    UnobtrusiveValidationMode="None" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
    Cambiar contraseña - REDCOM
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
    <script type="text/javascript">
        function togglePwdVisibility(inputId, iconId) {
            var input = document.getElementById(inputId);
            var icon = document.getElementById(iconId);

            if (!input || !icon) return;

            if (input.type === "password") {
                input.type = "text";
                icon.classList.remove("fa-eye");
                icon.classList.add("fa-eye-slash");
            } else {
                input.type = "password";
                icon.classList.remove("fa-eye-slash");
                icon.classList.add("fa-eye");
            }
        }
    </script>
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="change-password-wrapper">
        <div class="change-password-card">
            <h2>Cambiar contraseña</h2>
            <p class="cp-subtitle">
                Por seguridad, ingrese su contraseña actual y luego la nueva contraseña.
            </p>

            <asp:ValidationSummary 
                ID="vsErrores" 
                runat="server" 
                CssClass="cp-validation-summary"
                HeaderText="Por favor corrija los siguientes errores:"
                ValidationGroup="CambiarPwd" />

            <!-- Contraseña actual -->
            <div class="cp-form-group">
                <label for="<%= txtPasswordActual.ClientID %>">Contraseña actual</label>
                <div class="cp-password-wrapper">
                    <asp:TextBox 
                        ID="txtPasswordActual" 
                        runat="server" 
                        CssClass="cp-input" 
                        TextMode="Password" />
                    <i id="iconPwdActual" class="fas fa-eye cp-eye-icon"
                       onclick="togglePwdVisibility('<%= txtPasswordActual.ClientID %>', 'iconPwdActual')"></i>
                </div>
                <asp:RequiredFieldValidator 
                    ID="rfvPwdActual" 
                    runat="server"
                    ControlToValidate="txtPasswordActual"
                    ErrorMessage="La contraseña actual es obligatoria."
                    CssClass="cp-error"
                    Display="Dynamic"
                    ValidationGroup="CambiarPwd" />
            </div>

            <!-- Nueva contraseña -->
            <div class="cp-form-group">
                <label for="<%= txtPasswordNueva.ClientID %>">Nueva contraseña</label>
                <div class="cp-password-wrapper">
                    <asp:TextBox 
                        ID="txtPasswordNueva" 
                        runat="server" 
                        CssClass="cp-input" 
                        TextMode="Password" />
                    <i id="iconPwdNueva" class="fas fa-eye cp-eye-icon"
                       onclick="togglePwdVisibility('<%= txtPasswordNueva.ClientID %>', 'iconPwdNueva')"></i>
                </div>
                <asp:RequiredFieldValidator 
                    ID="rfvPwdNueva" 
                    runat="server"
                    ControlToValidate="txtPasswordNueva"
                    ErrorMessage="La nueva contraseña es obligatoria."
                    CssClass="cp-error"
                    Display="Dynamic"
                    ValidationGroup="CambiarPwd" />
                <asp:RegularExpressionValidator
                    ID="revPwdNueva"
                    runat="server"
                    ControlToValidate="txtPasswordNueva"
                    CssClass="cp-error"
                    Display="Dynamic"
                    ValidationExpression="^.{6,}$"
                    ErrorMessage="La nueva contraseña debe tener al menos 6 caracteres."
                    ValidationGroup="CambiarPwd" />
            </div>

            <!-- Confirmar nueva contraseña -->
            <div class="cp-form-group">
                <label for="<%= txtPasswordConfirmar.ClientID %>">Confirmar nueva contraseña</label>
                <div class="cp-password-wrapper">
                    <asp:TextBox 
                        ID="txtPasswordConfirmar" 
                        runat="server" 
                        CssClass="cp-input" 
                        TextMode="Password" />
                    <i id="iconPwdConfirmar" class="fas fa-eye cp-eye-icon"
                       onclick="togglePwdVisibility('<%= txtPasswordConfirmar.ClientID %>', 'iconPwdConfirmar')"></i>
                </div>
                <asp:RequiredFieldValidator 
                    ID="rfvPwdConfirmar" 
                    runat="server"
                    ControlToValidate="txtPasswordConfirmar"
                    ErrorMessage="Debe confirmar la nueva contraseña."
                    CssClass="cp-error"
                    Display="Dynamic"
                    ValidationGroup="CambiarPwd" />
                <asp:CompareValidator
                    ID="cvPwd"
                    runat="server"
                    ControlToValidate="txtPasswordConfirmar"
                    ControlToCompare="txtPasswordNueva"
                    ErrorMessage="Las contraseñas no coinciden."
                    CssClass="cp-error"
                    Display="Dynamic"
                    ValidationGroup="CambiarPwd" />
            </div>

            <!-- Botones -->
            <div class="cp-buttons">
                <asp:Button 
                    ID="btnCambiar" 
                    runat="server" 
                    Text="Cambiar contraseña" 
                    CssClass="cp-btn cp-btn-primary"
                    ValidationGroup="CambiarPwd"
                    OnClick="btnCambiar_Click" />
                
                <asp:Button 
                    ID="btnCancelar" 
                    runat="server" 
                    Text="Cancelar" 
                    CssClass="cp-btn cp-btn-secondary"
                    CausesValidation="false"
                    OnClick="btnCancelar_Click" />
            </div>

            <!-- Mensaje de éxito opcional -->
            <asp:Label 
                ID="lblMensaje" 
                runat="server" 
                CssClass="cp-success-message" 
                Visible="false" />
        </div>
    </div>

    <style>
        .change-password-wrapper {
            min-height: 70vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: linear-gradient(135deg, #f5f7ff 0%, #eef2ff 50%, #e0f7ff 100%);
            padding: 40px 10px;
        }

        .change-password-card {
            background: #ffffff;
            border-radius: 16px;
            padding: 30px 35px;
            max-width: 480px;
            width: 100%;
            box-shadow: 0 10px 30px rgba(0,0,0,0.08);
            border: 1px solid rgba(102,126,234,0.2);
        }

        .change-password-card h2 {
            margin: 0 0 8px 0;
            font-size: 24px;
            font-weight: 700;
            color: #333;
        }

        .cp-subtitle {
            margin: 0 0 20px 0;
            color: #666;
            font-size: 14px;
        }

        .cp-form-group {
            margin-bottom: 18px;
        }

        .cp-form-group label {
            display: block;
            margin-bottom: 6px;
            font-weight: 600;
            font-size: 14px;
            color: #444;
        }

        .cp-password-wrapper {
            position: relative;
        }

        .cp-input {
            width: 100%;
            padding: 10px 38px 10px 12px;
            border-radius: 8px;
            border: 1px solid #d0d7f5;
            font-size: 14px;
            outline: none;
            transition: border-color 0.2s, box-shadow 0.2s;
        }

        .cp-input:focus {
            border-color: #667eea;
            box-shadow: 0 0 0 2px rgba(102,126,234,0.2);
        }

        .cp-eye-icon {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            cursor: pointer;
            color: #888;
        }

        .cp-error {
            color: #e53935;
            font-size: 12px;
            margin-top: 4px;
            display: block;
        }

        .cp-validation-summary {
            background: #ffebee;
            color: #c62828;
            border-radius: 8px;
            padding: 10px 12px;
            margin-bottom: 16px;
            font-size: 12px;
        }

        .cp-buttons {
            margin-top: 22px;
            display: flex;
            gap: 10px;
            justify-content: flex-end;
        }

        .cp-btn {
            border: none;
            padding: 10px 18px;
            border-radius: 8px;
            font-size: 14px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.2s ease;
        }

        .cp-btn-primary {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: #fff;
            box-shadow: 0 4px 12px rgba(102,126,234,0.4);
        }

        .cp-btn-primary:hover {
            transform: translateY(-1px);
            box-shadow: 0 6px 16px rgba(102,126,234,0.5);
        }

        .cp-btn-secondary {
            background: #e0e0e0;
            color: #333;
        }

        .cp-btn-secondary:hover {
            background: #d5d5d5;
        }

        .cp-success-message {
            display: block;
            margin-top: 16px;
            font-size: 13px;
            color: #2e7d32;
        }

        @media (max-width: 576px) {
            .change-password-card {
                padding: 22px 18px;
            }
        }
    </style>
</asp:Content>
