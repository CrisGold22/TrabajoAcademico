<%@ Page Title="Registrar Nuevo Producto" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarProductos02RegistrarProducto.aspx.cs" Inherits="SoftProgWeb.GestionarProductos02RegistrarProducto" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

    
    <div class="container">
        <div class="row">
            <div class="col-md-10 offset-md-1">
                <div class="card">
                    <div class="card-header">
                        <h3><asp:Literal ID="litTitulo" runat="server" Text="Detalle de Producto"></asp:Literal></h3>
                    </div>
                    <div class="card-body">
                        
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label>Nombre del Producto:</label>
                                    <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control"></asp:TextBox>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>SKU:</label>
                                    <asp:TextBox ID="txtSKU" runat="server" CssClass="form-control"></asp:TextBox>
                                </div>
                            </div>
                        </div>

                        <div class="row mt-3">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label>Categoría:</label>
                                    <asp:DropDownList ID="ddlCategoria" runat="server" CssClass="form-select"></asp:DropDownList>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Marca:</label>
                                    <asp:TextBox ID="txtMarca" runat="server" CssClass="form-control"></asp:TextBox>
                                </div>
                            </div>
                        </div>

                        <div class="row mt-3">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>Descripción:</label>
                                    <asp:TextBox ID="txtDescripcion" runat="server" TextMode="MultiLine" Rows="3" CssClass="form-control"></asp:TextBox>
                                </div>
                            </div>
                        </div>

                        <div class="row mt-3">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Precio Regular (S/):</label>
                                    <asp:TextBox ID="txtPrecioRegular" runat="server" CssClass="form-control" TextMode="Number"></asp:TextBox>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Stock Disponible:</label>
                                    <asp:TextBox ID="txtStockDisponible" runat="server" CssClass="form-control" TextMode="Number"></asp:TextBox>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Unidad de Medida:</label>
                                    <asp:DropDownList ID="ddlUnidadMedida" runat="server" CssClass="form-select"></asp:DropDownList>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                    <div class="card-footer text-end">
                        <asp:Button ID="btnGuardar" runat="server" Text="Guardar" CssClass="btn btn-success" OnClick="btnGuardar_Click" />
                        <asp:Button ID="btnCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary" OnClick="btnCancelar_Click" CausesValidation="false" />
                    </div>
                </div>
                
                <asp:Panel ID="pnlMensaje" runat="server" Visible="false" CssClass="alert alert-danger mt-3">
                    <asp:Literal ID="litMensajeError" runat="server"></asp:Literal>
                </asp:Panel>

            </div>
        </div>
    </div>
</asp:Content>