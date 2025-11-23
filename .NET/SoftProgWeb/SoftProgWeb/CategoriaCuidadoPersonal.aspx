<%@ Page Language="C#" MasterPageFile="~/SoftProg.Master"  AutoEventWireup="true" CodeBehind="CategoriaCuidadoPersonal.aspx.cs" Inherits="SoftProgWeb.CategoriaCuidadoPersonal" %>
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
                          <img src="images/jabonArox6.png" class="card-img-top" alt="Arroz Extra Añejo Faraon">
                          <div class="card-body">
                             <h5 class="card-title">Jabón de Tocador Frutos Rojos ARO Sixpack 750g</h5>
                             <p class="card-text">Por RedCom</p>
                             <p class="card-text">Unidad: 1 un</p>
                             <p class="card-text"><strong>A partir de S/ 15.90</strong></p>
                             <a href="#" class="btn btn-primary button-add-1">Agregar</a>
                         </div>   
                     </div>
                 </div>
 
             <!--2-->
 
             <div class="col-md-3 mb-4">
                 <div class="card">
                         <img src="images/jabonprotex110gx6.png" class="card-img-top" alt="Arroz Extra Añejo Faraon">
                         <div class="card-body">
                         <h5 class="card-title">Jabón Antibacterial PROTEX Avena Paquete 6 Bolsas 110g c/u</h5>
                         <p class="card-text">Por RedCom</p>
                         <p class="card-text">Unidad: Paquete 1un</p>
                         <p class="card-text"><strong>A partir de S/ 20.28</strong></p>
                         <a href="#" class="btn btn-primary button-add-1">Agregar</a>
                     </div>   
                 </div>
             </div>
 
             <!--3-->
 
             <div class="col-md-3 mb-4">
                 <div class="card">
                         <img src="images/jabonNekox6.png" class="card-img-top" alt="Arroz Extra Añejo Faraon">
                         <div class="card-body">
                         <h5 class="card-title">Jabón en Barra Antibacterial NEKO Extra Protección</h5>
                         <p class="card-text">Por RedCom</p>
                         <p class="card-text">Unidad: Paquete 1un</p>
                         <p class="card-text"><strong>A partir de S/ 17.40</strong></p>
                         <a href="#" class="btn btn-primary button-add-1">Agregar</a>
                     </div>   
                 </div>
             </div>

             <!--4-->
 
             <div class="col-md-3 mb-4">
                 <div class="card">
                         <img src="images/jabon palmolivex6.png" class="card-img-top" alt="Arroz Extra Añejo Faraon">
                         <div class="card-body">
                         <h5 class="card-title">Jabón de Tocador PALMOLIVE Exfoliación Diaria Avena y Azúcar Morena Paquete 6 Bolsas 75g c/u</h5>
                         <p class="card-text">Por RedCom</p>
                         <p class="card-text">Unidad: Paquete 1 un</p>
                         <p class="card-text"><strong>A partir de S/ 16.80</strong></p>
                         <a href="#" class="btn btn-primary button-add-1">Agregar</a>
                     </div>   
                 </div>
             </div>

            
            
         </div>
     </div>
   </div>
</asp:Content>