<%@ Page Title="Listado de Pedidos" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarPedidos01.aspx.cs" Inherits="SoftProgWeb.GestionarPedidos01" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
            <div class="d-flex justify-content-between align-items-center mb-4">
            <div class="row g-3 align-items-end">

                <div class="col-md-5">
                    <asp:Label runat="server" AssociatedControlID="txtCodigoBusqueda">Buscar por PedidoID:</asp:Label>
                    <div class="input-group">
                        <div class="input-group-text">
                            <asp:RadioButton ID="rbBusquedaCodigo" runat="server" GroupName="tipoBusqueda" Checked="true" onclick="actualizarBusqueda();" CssClass="form-check-input mt-0" />
                        </div>
                        <asp:TextBox ID="txtCodigoBusqueda" runat="server" CssClass="form-control" placeholder="Ej: 10248"></asp:TextBox>
                    </div>
                </div>
              
                <div class="col-md-5">
                    <asp:Label runat="server">Buscar por rango de fechas:</asp:Label>
                    <div class="input-group">
                        <div class="input-group-text">
                            <asp:RadioButton ID="rbBusquedaFecha" runat="server" GroupName="tipoBusqueda" onclick="actualizarBusqueda();" CssClass="form-check-input mt-0" />
                        </div>
                        <asp:TextBox ID="txtFechaInicio" runat="server" CssClass="form-control" TextMode="Date"></asp:TextBox>
                        <span class="input-group-text">hasta</span>
                        <asp:TextBox ID="txtFechaFin" runat="server" CssClass="form-control" TextMode="Date"></asp:TextBox>
                    </div>
                </div>

            <div class="col-md-2 d-flex gap-5">
                <asp:Button ID="btnBuscar" runat="server" Text="Buscar" CssClass="btn btn-success fw-bold w-100" />
                <asp:Button ID="Button1" runat="server" Text="🖨 Generar Reporte" CssClass="btn btn-primary fw-bold w-120" />
            </div>

                </div>
            </div>

    <div class="card shadow-sm">
        <div class="card-body">
             <div class="table-responsive">
                <asp:GridView 
                    ID="gvPedidos" 
                    runat="server" 
                    CssClass="table table-hover align-middle"
                    AutoGenerateColumns="False"
                    EmptyDataText="No se encontraron órdenes de venta."
                    HeaderStyle-CssClass="table-light">
                    <Columns>             
                        <asp:TemplateField>
                            <HeaderTemplate>
                                <asp:CheckBox ID="chkSeleccionarTodosHeader" runat="server" onclick="seleccionarTodos(this);" />
                            </HeaderTemplate>
                            <ItemTemplate>
                                <asp:CheckBox ID="chkSeleccionar" runat="server" />
                            </ItemTemplate>
                        </asp:TemplateField>
                        <asp:BoundField DataField="PedidoID" HeaderText="PedidoID" />
                        <asp:BoundField DataField="RUC" HeaderText="RUC" />
                        <asp:BoundField DataField="RazonSocial" HeaderText="Razón Social" />
                        <asp:BoundField DataField="Monto" HeaderText="Monto" DataFormatString="{0:C}" />
                        <asp:BoundField DataField="NFactura" HeaderText="N° Factura" />
                        <asp:TemplateField HeaderText="Estado" ItemStyle-CssClass="text-center">
                            <ItemTemplate>
                                <span class='<%# GetEstadoBadge(Eval("Estado").ToString()) %>'>
                                    <%# Eval("Estado") %>
                                </span>
                            </ItemTemplate>
                        </asp:TemplateField>
                        <asp:BoundField DataField="Fecha" HeaderText="Fecha" DataFormatString="{0:dd/MM/yyyy}" />
                    </Columns>
                </asp:GridView>
            </div>
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

    <%-- Script generado mediante IA--%>
    <script type="text/javascript">
        function seleccionarTodos(chkHeader) {
            var grid = document.getElementById('<%= gvPedidos.ClientID %>');
            if (grid) {
                var inputs = grid.getElementsByTagName('input');
                for (var i = 0; i < inputs.length; i++) {
                    if (inputs[i].type === 'checkbox') {
                        inputs[i].checked = chkHeader.checked;
                    }
                }
            }
        }
    </script>



</asp:Content>