<%@ Page Title="" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="EditarPerfil.aspx.cs" Inherits="SoftProgWeb.EditarPerfil" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
    Editar Perfil - REDCOM
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="perfil-header">
        <div class="container">
        </div>
    </div>

    <div class="perfil-container">
        <div class="perfil-card">
            <div class="profile-avatar-section">
                <div class="avatar-circle">
                    <img src="images/22bdc07ec31844f3ceb7bfc5fc64d16724d79af6.png" 
                         alt="Profile Picture" id="imgAvatar" />
                </div>
                <div class="profile-info-header flex-grow-1">
                    <h2>Editar Perfil</h2>
                    <p>Actualiza tus datos personales</p>
                </div>
            </div>

            <!-- Mensajes -->
            <asp:Panel ID="pnlMensaje" runat="server" Visible="false" CssClass="alert" Style="margin-top:15px;">
                <asp:Literal ID="litMensaje" runat="server"></asp:Literal>
            </asp:Panel>

            <!-- Formulario -->
            <div class="form-row-custom" style="margin-top:20px;">
                <div class="form-group-perfil">
                    <label class="form-label-perfil">Nombre</label>
                    <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control-perfil"
                        placeholder="Nombre"></asp:TextBox>
                </div>

                <div class="form-group-perfil">
                    <label class="form-label-perfil">Apellidos</label>
                    <asp:TextBox ID="txtApellido" runat="server" CssClass="form-control-perfil"
                        placeholder="Apellidos"></asp:TextBox>
                </div>
            </div>

            <div class="form-row-custom">
                <div class="form-group-perfil">
                    <label class="form-label-perfil">Teléfono</label>
                    <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control-perfil"
                        placeholder="999 999 999"></asp:TextBox>
                </div>

                <div class="form-group-perfil">
                    <label class="form-label-perfil">Email</label>
                    <asp:TextBox ID="txtEmail" runat="server" CssClass="form-control-perfil"
                        placeholder="ejemplo@gmail.com"></asp:TextBox>
                </div>
            </div>

            <div class="form-actions" style="margin-top:25px; display:flex; gap:10px; justify-content:flex-end;">
                <asp:Button ID="btnCancelar" runat="server" Text="Cancelar"
                    CssClass="btn-cancelar" CausesValidation="false"
                    OnClick="btnCancelar_Click" />

                <asp:Button ID="btnGuardar" runat="server" Text="Guardar cambios"
                    CssClass="btn-modal-action btn-accept"
                    OnClick="btnGuardar_Click" />
            </div>
        </div>
    </div>
</asp:Content>
