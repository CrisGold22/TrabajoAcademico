<%@ Page Title="Mi Perfil" Language="C#" MasterPageFile="~/SoftProg.Master" AutoEventWireup="true" CodeBehind="PerfilCliente.aspx.cs" Inherits="SoftProgWeb.PerfilCliente" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
    Mi Perfil - REDCOM
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">




    <div class="perfil-container">
        <div class="perfil-card">

            <!-- Avatar -->
            <div class="profile-avatar-section">
                <div class="avatar-circle">
                    <img src="images/22bdc07ec31844f3ceb7bfc5fc64d16724d79af6.png"
                        alt="Profile Picture" id="imgAvatar" />
                </div>
            </div>

            <!-- Información del Cliente -->
            <h2 class="perfil-nombre" id="lblTituloNombre" runat="server"></h2>

            <asp:Panel ID="pnlDatos" runat="server">

                <div class="perfil-info-row">
                    <label>Nombre:</label>
                    <asp:TextBox ID="txtNombre" runat="server" CssClass="perfil-input" ReadOnly="true"></asp:TextBox>
                </div>

                <div class="perfil-info-row">
                    <label>Apellido:</label>
                    <asp:TextBox ID="txtApellido" runat="server" CssClass="perfil-input" ReadOnly="true"></asp:TextBox>
                </div>

                <div class="perfil-info-row">
                    <label>DNI:</label>
                    <asp:TextBox ID="txtDNI" runat="server" CssClass="perfil-input" ReadOnly="true"></asp:TextBox>
                </div>

                <div class="perfil-info-row">
                    <label>Correo:</label>
                    <asp:TextBox ID="txtCorreo" runat="server" CssClass="perfil-input" ReadOnly="true"></asp:TextBox>
                </div>

                <div class="perfil-info-row">
                    <label>Dirección:</label>
                    <asp:TextBox ID="txtDireccion" runat="server" CssClass="perfil-input" ReadOnly="true"></asp:TextBox>
                </div>

            </asp:Panel>

            <!-- Botones -->
            <div class="perfil-botones">
                <asp:Button ID="btnEditar" runat="server" Text="Modificar información" CssClass="perfil-btn"
                    OnClick="btnEditar_Click" />

                <asp:Button ID="btnGuardar" runat="server" Text="Guardar cambios" CssClass="perfil-btn-guardar"
                    Visible="false" OnClick="btnGuardar_Click" />
            </div>

            <div class="empresas-lista">
                <asp:GridView ID="gvEmpresas" runat="server"
                    AutoGenerateColumns="False"
                    CssClass="tabla-empresas"
                    DataKeyNames="id"
                    GridLines="None"
                    ShowHeaderWhenEmpty="true"
                    OnRowEditing="gvEmpresas_RowEditing"
                    OnRowCancelingEdit="gvEmpresas_RowCancelingEdit"
                    OnRowUpdating="gvEmpresas_RowUpdating"
                    OnRowDeleting="gvEmpresas_RowDeleting"
                    OnSelectedIndexChanged="gvEmpresas_SelectedIndexChanged">

                    <HeaderStyle CssClass="tabla-empresas-header" />
                    <RowStyle CssClass="tabla-empresas-row" />
                    <AlternatingRowStyle CssClass="tabla-empresas-row-alt" />
                    <EditRowStyle CssClass="tabla-empresas-row-edit" />
                    <SelectedRowStyle CssClass="tabla-empresas-row-selected" />

                    <Columns>
                        <!-- ID oculto, pero usado para Actualizar / Eliminar -->
                        <asp:BoundField DataField="id" HeaderText="ID" ReadOnly="true" Visible="false" />

                        <asp:BoundField DataField="RUC" HeaderText="RUC" />
                        <asp:BoundField DataField="razonSocial" HeaderText="Razón Social" />
                        <asp:BoundField DataField="direccionFiscal" HeaderText="Dirección" />
                        <asp:BoundField DataField="telefono" HeaderText="Teléfono" />
                        <asp:BoundField DataField="email" HeaderText="Correo" />

                        <!-- Botón para seleccionar la empresa y cargarla en el formulario de abajo -->
                        <asp:CommandField ShowSelectButton="true" SelectText="Editar" />

                        <!-- Botón de eliminar -->
                        <asp:CommandField ShowDeleteButton="true" DeleteText="Eliminar" />
                    </Columns>

                    <EmptyDataTemplate>
                        <div style="padding: 10px; text-align: center;">
                            Aún no tiene empresas registradas. Use el botón <b>"Agregar nueva empresa"</b>.
                        </div>
                    </EmptyDataTemplate>
                </asp:GridView>
            </div>



        </div>
    </div>

</asp:Content>
