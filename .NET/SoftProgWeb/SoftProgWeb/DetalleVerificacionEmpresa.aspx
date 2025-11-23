<%@ Page Title="Verificar Empresas" Language="C#" MasterPageFile="~/SiteModulo01.Master"  AutoEventWireup="true" CodeBehind="DetalleVerificacionEmpresa.aspx.cs" Inherits="SoftProgWeb.DetalleVerificacionEmpresa" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <h2><i class="fas fa-user-check"></i> Procesar Verificación de Empresa</h2>
                <hr />
            </div>
        </div>

        <div class="row">
            <div class="col-md-7">
                <div class="card">
                    <div class="card-header">
                        Datos de la Empresa
                    </div>
                    <div class="card-body">
                        <div class="form-group row">
                            <label class="col-sm-4 col-form-label">Razón Social:</label>
                            <div class="col-sm-8">
                                <asp:Label ID="lblRazonSocial" runat="server" CssClass="form-control-plaintext"></asp:Label>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-4 col-form-label">RUC:</label>
                            <div class="col-sm-8">
                                <asp:Label ID="lblRUC" runat="server" CssClass="form-control-plaintext"></asp:Label>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-4 col-form-label">Dirección:</label>
                            <div class="col-sm-8">
                                <asp:Label ID="lblDireccion" runat="server" CssClass="form-control-plaintext"></asp:Label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-5">
                <div class="card border-primary">
                    <div class="card-header bg-primary text-white">
                        Acción Requerida
                    </div>
                    <div class="card-body">
                        <p>
                            Realice la verificación con el cliente (llamada/reunión). 
                            Una vez completada, apruebe o rechace la solicitud.
                        </p>
                        <p>
                            <strong>Aprobar:</strong> Activará la cuenta de la empresa.
                            <br />
                            <strong>Rechazar:</strong> Eliminará permanentemente la empresa.
                        </p>
                        <hr />
                        
                        <div class="d-grid gap-2">
                            <asp:Button ID="btnAprobar" runat="server" Text="Aprobar Empresa" 
                                CssClass="btn btn-success" OnClick="btnAprobar_Click" />
                            
                            <asp:Button ID="btnRechazar" runat="server" Text="Rechazar y Eliminar Empresa" 
                                CssClass="btn btn-danger" OnClick="btnRechazar_Click" 
                                OnClientClick="return confirm('¿Está seguro de que desea eliminar esta empresa? Esta acción es irreversible.');" />
                        </div>
                    </div>
                </div>
                
                <asp:Panel ID="pnlMensaje" runat="server" Visible="false" CssClass="alert alert-danger mt-3">
                    <asp:Literal ID="litMensajeError" runat="server"></asp:Literal>
                </asp:Panel>

            </div>
        </div>
    </div>

</asp:Content>