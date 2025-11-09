<%@ Page Title="Gestión de Productos" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarProductos01.aspx.cs" Inherits="SoftProgWeb.GestionarProductos01" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <div class="d-flex gap-3">
            <asp:DropDownList ID="ddlCategorias" runat="server" CssClass="form-select" style="width: 270px;">
                <asp:ListItem Text="Filtrar por categorías (Root)" Value="0"></asp:ListItem>
                <asp:ListItem Text="Gaseosas" Value="1"></asp:ListItem>
            </asp:DropDownList>
            <div class="input-group" style="width: 340px;">
                <asp:TextBox runat="server" ID="txtBuscarProducto" CssClass="form-control" placeholder="¿Buscar producto?"></asp:TextBox>
                <span class="input-group-text"><i class="fas fa-search"></i></span>
            </div>
        </div>
        <div>
            <asp:Button ID="btnNuevoProducto" runat="server" Text="+ Nuevo Producto" CssClass="btn btn-primary fw-bold" OnClick="btnNuevoProducto_Click"/>
        </div>
    </div>

    <div class="card shadow-sm">
        <div class="card-body">
            <asp:GridView 
                ID="gvProductos" 
                runat="server" 
                OnRowCommand="gvProductos_RowCommand"
                CssClass="table table-hover align-middle"
                AutoGenerateColumns="False"
                EmptyDataText="No se encontraron productos para mostrar."
                HeaderStyle-CssClass="table-light">
                <Columns>
                    <asp:TemplateField>
                        <HeaderTemplate>
                            <asp:CheckBox ID="chkSeleccionarTodo" runat="server" />
                        </HeaderTemplate>
                        <ItemTemplate>
                            <asp:CheckBox ID="chkSeleccionar" runat="server" />
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:BoundField DataField="ID" HeaderText="ID" />
                    <asp:TemplateField HeaderText="Imagen"><ItemTemplate><img src='https://placehold.co/40x40' class="rounded" /></ItemTemplate></asp:TemplateField>
                    <asp:BoundField DataField="Producto" HeaderText="Producto" />
                    <asp:BoundField DataField="Categoria" HeaderText="Categoría" />
                    <asp:BoundField DataField="PrecioSinIGV" HeaderText="Precio sin IGV" DataFormatString="{0:C}" />
                    <asp:BoundField DataField="PrecioConIGV" HeaderText="Precio con IGV" DataFormatString="{0:C}" />
                    <asp:BoundField DataField="Estado" HeaderText="Estado" />
                    <asp:BoundField DataField="Stock" HeaderText="Stock" />
                    <asp:TemplateField HeaderText="Acciones">
                        <ItemTemplate>
                            <asp:LinkButton ID="btnEditar" runat="server" CssClass="btn btn-sm btn-outline-secondary me-1"><i class="fas fa-pencil-alt"></i></asp:LinkButton>
                            <asp:LinkButton ID="LinkButton1" runat="server" 
                                CssClass="btn btn-sm btn-outline-danger"
                                CommandName="EliminarProducto" 
                                CommandArgument='<%# Eval("ID") %>'>
                                <i class="fas fa-trash-alt"></i>
                            </asp:LinkButton>
                        </ItemTemplate>
                    </asp:TemplateField>
                </Columns>
            </asp:GridView>
        </div>
<div class="card-footer d-flex justify-content-center">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link" href="#">&laquo;</a>
            </li>
            <li class="page-item active">
                <a class="page-link" href="#">1</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">2</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">3</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">4</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">5</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">&raquo;</a>
            </li>
        </ul>
    </nav>
</div>
    </div>

<%--Modal Eliminacion--%>
<div class="modal fade" id="modalConfirmacion" tabindex="-1" aria-labelledby="modalConfirmacionLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title" id="modalConfirmacionLabel"><i class="fas fa-exclamation-triangle me-2"></i>Confirmar Eliminación</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Se eliminará el producto seleccionado.
        <br/>
        <strong>¿Está seguro que desea continuar?</strong>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <button type="button" class="btn btn-danger">Eliminar</button>
      </div>
    </div>
  </div>
</div>

</asp:Content>
