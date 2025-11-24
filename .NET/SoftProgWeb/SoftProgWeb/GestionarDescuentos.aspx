<%@ Page Title="" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarDescuentos.aspx.cs" Inherits="SoftProgWeb.GestionarDescuentos" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container mt-4">
        <h2>Gestión de Descuentos por Volumen</h2>
        
        <div class="card mb-4">
            <div class="card-header bg-info text-white">
                Información del Producto
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <label>Producto:</label>
                        <asp:Label ID="lblNombreProducto" runat="server" CssClass="fw-bold"></asp:Label>
                    </div>
                    <div class="col-md-3">
                        <label>SKU:</label>
                        <asp:Label ID="lblSKU" runat="server" CssClass="fw-bold"></asp:Label>
                    </div>
                    <div class="col-md-3">
                        <label>Precio Regular:</label>
                        <asp:Label ID="lblPrecioRegular" runat="server" CssClass="fw-bold"></asp:Label>
                    </div>
                </div>
            </div>
        </div>

        <div class="card mb-4">
            <div class="card-header">Agregar / Editar Rango</div>
            <div class="card-body">
                <asp:HiddenField ID="hdnIdDescuento" runat="server" Value="0" />
                <div class="row align-items-end">
                    <div class="col-md-3">
                        <label>Cant. Mínima:</label>
                        <asp:TextBox ID="txtMin" runat="server" CssClass="form-control" TextMode="Number"></asp:TextBox>
                    </div>
                    <div class="col-md-3">
                        <label>Cant. Máxima:</label>
                        <asp:TextBox ID="txtMax" runat="server" CssClass="form-control" TextMode="Number"></asp:TextBox>
                    </div>
                    <div class="col-md-3">
                        <label>Precio Oferta (S/.):</label>
                        <asp:TextBox ID="txtPrecioOferta" runat="server" CssClass="form-control" TextMode="Number" Step="0.01"></asp:TextBox>
                    </div>
                    <div class="col-md-3">
                        <asp:Button ID="btnGuardar" runat="server" Text="Guardar Rango" CssClass="btn btn-success w-100" OnClick="btnGuardar_Click" />
                        <asp:Button ID="btnCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary w-100 mt-1" OnClick="btnCancelar_Click" Visible="false" />
                    </div>
                </div>
                <asp:Label ID="lblError" runat="server" CssClass="text-danger mt-2"></asp:Label>
            </div>
        </div>

        <h4>Descuentos Activos</h4>
        <asp:GridView ID="gvDescuentos" runat="server" AutoGenerateColumns="False" CssClass="table table-striped" 
                      OnRowCommand="gvDescuentos_RowCommand" EmptyDataText="No hay descuentos configurados para este producto.">
            <Columns>
                <asp:BoundField DataField="cantidadMin" HeaderText="Mínimo" />
                <asp:BoundField DataField="cantidadMax" HeaderText="Máximo" />
                <asp:BoundField DataField="precioPorVolumen" HeaderText="Precio Oferta" DataFormatString="{0:C}" />
                <asp:TemplateField HeaderText="Acciones">
                    <ItemTemplate>
                        <asp:Button ID="btnEditar" runat="server" Text="Editar" CommandName="EditarDescuento" 
                                    CommandArgument='<%# Eval("id") %>' CssClass="btn btn-warning btn-sm" />
                        <asp:Button ID="btnEliminar" runat="server" Text="Eliminar" CommandName="EliminarDescuento" 
                                    CommandArgument='<%# Eval("id") %>' CssClass="btn btn-danger btn-sm" OnClientClick="return confirm('¿Eliminar este rango?');" />
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
        
        <asp:Button ID="btnVolver" runat="server" Text="Volver a Productos" CssClass="btn btn-link" OnClick="btnVolver_Click" />
    </div>
</asp:Content>