<%@ Page Title="Registrar Nuevo Producto" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarProductos02RegistrarProducto.aspx.cs" Inherits="SoftProgWeb.GestionarProductos02RegistrarProducto" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

    <h2 class="mb-4"><i class="fas fa-plus-circle me-2"></i>Registrar Nuevo Producto</h2>

    <div class="card shadow-sm">
        <div class="card-body">
            <div class="row">
                <div class="col-md-6">
                    <h4>Datos del Producto</h4>
                    <hr />
                    <div class="mb-3">
                        <asp:Label runat="server" AssociatedControlID="txtNombreProducto">Nombre del Producto:</asp:Label>
                        <asp:TextBox ID="txtNombreProducto" runat="server" CssClass="form-control" placeholder="Ej: Gaseosa Coca Cola 500ml"></asp:TextBox>
                    </div>
                    <div class="mb-3">
                        <asp:Label runat="server" AssociatedControlID="ddlUnidadMedida">Unidad de Medida:</asp:Label>
                        <asp:DropDownList ID="ddlUnidadMedida" runat="server" CssClass="form-select">
                            <asp:ListItem Text="Seleccione una unidad" Value=""></asp:ListItem>
                            <asp:ListItem Text="Botella" Value="1"></asp:ListItem>
                            <asp:ListItem Text="Caja" Value="2"></asp:ListItem>
                            <asp:ListItem Text="Paquete" Value="3"></asp:ListItem>
                        </asp:DropDownList>
                    </div>
                    <div class="mb-3">
                        <asp:Label runat="server" AssociatedControlID="txtPrecio">Precio por Unidad (con IGV):</asp:Label>
                        <div class="input-group">
                            <span class="input-group-text">S/</span>
                            <asp:TextBox ID="txtPrecio" runat="server" CssClass="form-control" placeholder="0.00"></asp:TextBox>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <h4>Detalles del Producto</h4>
                    <hr />
                    <div class="mb-3">
                        <asp:Label runat="server" AssociatedControlID="ddlCategoria">Categoría del Producto:</asp:Label>
                        <asp:DropDownList ID="ddlCategoria" runat="server" CssClass="form-select">
                            <asp:ListItem Text="Seleccione una categoría" Value=""></asp:ListItem>
                            <asp:ListItem Text="Gaseosas" Value="1"></asp:ListItem>
                            <asp:ListItem Text="Aguas" Value="2"></asp:ListItem>
                            <asp:ListItem Text="Licores" Value="3"></asp:ListItem>
                        </asp:DropDownList>
                    </div>
                    <div class="mb-3">
                        <asp:Label runat="server" AssociatedControlID="txtStock">Stock Inicial:</asp:Label>
                        <asp:TextBox ID="txtStock" runat="server" CssClass="form-control" TextMode="Number" placeholder="0"></asp:TextBox>
                    </div>
                    <div class="mb-3">
                        <asp:Label runat="server" AssociatedControlID="fuImagen">Imagen del Producto:</asp:Label>
                        <asp:FileUpload ID="fuImagen" runat="server" CssClass="form-control" />
                    </div>
                </div>
            </div>
        </div>
        <div class="card-footer text-end">
            <asp:Button ID="btnCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary me-2" />
            <asp:Button ID="btnGuardar" runat="server" Text="Guardar Producto" CssClass="btn btn-primary" OnClick="btnGuardar_Click"/>
        </div>
    </div>

<%--Modal Regsitro--%>
<div class="modal fade" id="modalExito" tabindex="-1" aria-labelledby="modalExitoLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-primary text-white">
        <h5 class="modal-title" id="modalExitoLabel"><i class="fas fa-check-circle me-2"></i>Registro Exitoso</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        El producto se ha registrado correctamente.
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>

</asp:Content>