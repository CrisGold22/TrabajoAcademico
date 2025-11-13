<%@ Page Title="Verificar Empresas" Language="C#" MasterPageFile="~/SiteModulo01.Master" AutoEventWireup="true" CodeBehind="GestionarEmpresasPendientes.aspx.cs" Inherits="SoftProgWeb.GestionarEmpresasPendientes" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <h2><i class="fas fa-building"></i> Empresas Pendientes de Verificación</h2>
                <hr />
                <p>
                    La siguiente lista muestra las empresas registradas cuya cuenta está inactiva y requiere verificación.
                </p>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <asp:GridView ID="gvEmpresas" runat="server"
                    CssClass="table table-hover table-striped"
                    AutoGenerateColumns="False"
                    DataKeyNames="id"
                    OnRowCommand="gvEmpresas_RowCommand">
                    <Columns>
                        <asp:BoundField DataField="razonSocial" HeaderText="Razón Social" />
                        <asp:BoundField DataField="RUC" HeaderText="RUC" />
                        <asp:BoundField DataField="direccionFiscal" HeaderText="Dirección" />
                        <asp:TemplateField HeaderText="Acción">
                            <ItemTemplate>
                                <asp:Button ID="btnProcesar" runat="server" 
                                    Text="Procesar" 
                                    CssClass="btn btn-primary btn-sm"
                                    CommandName="Procesar"
                                    CommandArgument='<%# Eval("id") %>' />
                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                    <EmptyDataTemplate>
                        <div class="alert alert-info">
                            No hay empresas pendientes de verificación.
                        </div>
                    </EmptyDataTemplate>
                </asp:GridView>
            </div>
        </div>
    </div>

</asp:Content>