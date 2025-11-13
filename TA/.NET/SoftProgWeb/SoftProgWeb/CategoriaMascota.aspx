<%@ Page Language="C#" MasterPageFile="~/SoftProg.Master"  AutoEventWireup="true" CodeBehind="CategoriaMascota.aspx.cs" Inherits="SoftProgWeb.CategoriaMascota" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitle" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphScripts" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="cphContenido" runat="server">
      <div class ="container my-5">


      <h5 class="text-lg-start mb-5">Home > Abarrotes</h5>
      <!--barra lateral-->

      <div class="sidebar">
 
          <h4>Filtros</h4>

          <h4>Marcas</h4>
          <ul class="list-group">
            <li class="list-group-item">Marca 1</li>
            <li class="list-group-item">Marca 2</li>
            <li class="list-group-item">Marca 3</li>
          </ul>
      </div>

      <div class="content my-5">
          <!--1-->
          <div class="row">
              <div class="col-md-3 mb-4">
                  <div class="card">
                       <img src="images/dogchowcachorro21kg.png" class="card-img-top" alt="Arroz Extra Añejo Faraon">
                       <div class="card-body">
                          <h5 class="card-title">Comida para perros RICOCAN Adultos todas las razas multisabores Bolsa 15Kg</h5>
                          <p class="card-text">Por RedCom</p>
                          <p class="card-text">Unidad: 1 un</p>
                          <p class="card-text"><strong>A partir de S/ 105.90</strong></p>
                          <a href="#" class="btn btn-primary button-add-1">Agregar</a>
                      </div>   
                  </div>
              </div>
 
          <!--2-->
 
          <div class="col-md-3 mb-4">
              <div class="card">
                      <img src="images/ricocan15kg.png" class="card-img-top" alt="Arroz Extra Añejo Faraon">
                      <div class="card-body">
                      <h5 class="card-title">Comida para Perros DOG CHOW Cachorros Grandes y Pequeños Bolsa de 21Kg</h5>
                      <p class="card-text">Por RedCom</p>
                      <p class="card-text">Unidad: Paquete 1un</p>
                      <p class="card-text"><strong>A partir de S/ 175.50</strong></p>
                      <a href="#" class="btn btn-primary button-add-1">Agregar</a>
                  </div>   
              </div>
          </div>
         
         
      </div>
  </div>
</div>
</asp:Content>
