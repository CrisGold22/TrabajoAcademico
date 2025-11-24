<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="WebForm1.aspx.cs" Inherits="PRUEBA_1.WebForm1" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Pantalla de Seguimiento de Pedido</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: #f5f5f5;
        }
        header {
            background: #007bff;
            color: white;
            padding: 15px;
            text-align: center;
        }
        #main {
            display: flex;
            padding: 20px;
            gap: 20px;
        }
        #controls {
            flex: 1;
            max-width: 400px;
            background: white;
            padding: 20px;
            border-radius: 8px;
        }
        #map {
            flex: 2;
            height: 600px;
            border-radius: 8px;
        }
        .product-list {
            margin-top: 20px;
        }
        .product-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 6px;
            background: #e9ecef;
        }
        .product-name {
            font-weight: bold;
        }
        .estado {
            padding: 4px 8px;
            border-radius: 4px;
            color: white;
            font-weight: bold;
        }
        .cancelado { background: #dc3545; }
        .preparando { background: #ffc107; color: black; }
        .encamino { background: #17a2b8; }
        .entregado { background: #28a745; }
        #contador {
            font-size: 18px;
            margin-top: 10px;
        }
        button {
            padding: 10px 15px;
            margin-top: 10px;
            cursor: pointer;
            border: none;
            background: #007bff;
            color: white;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Super Pantalla de Seguimiento de Pedido</h1>
    </header>
    <div id="main">
        <div id="controls">
            <label>Ingrese la dirección de entrega:</label>
            <input type="text" id="destination" placeholder="Ej: Av. Argentina, Lima" style="width:100%; margin-top:5px; padding:5px;">
            <button onclick="iniciarPedido()">Iniciar Pedido</button>
            <div id="contador">Estado: Cancelado</div>
            
            <div class="product-list" id="productList">
                <!-- Productos se llenan dinámicamente -->
            </div>
        </div>
        <div id="map"></div>
    </div>

    <script>
        let map;
        let directionsService;
        let directionsRenderer;
        let markers = [];

        const empresa = { lat: -12.0464, lng: -77.0428 }; // Punto fijo: Lima
        const productos = [
            { nombre: "Producto A", estado: "Cancelado" },
            { nombre: "Producto B", estado: "Cancelado" },
            { nombre: "Producto C", estado: "Cancelado" }
        ];

        function initMap() {
            map = new google.maps.Map(document.getElementById("map"), {
                center: empresa,
                zoom: 14
            });
            directionsService = new google.maps.DirectionsService();
            directionsRenderer = new google.maps.DirectionsRenderer({ map: map });

            // Inicializar productos en pantalla
            actualizarProductos();
        }

        function actualizarProductos() {
            const list = document.getElementById("productList");
            list.innerHTML = "";
            productos.forEach(p => {
                const item = document.createElement("div");
                item.className = "product-item";
                item.innerHTML = `
                    <span class="product-name">${p.nombre}</span>
                    <span class="estado ${p.estado.toLowerCase()}">${p.estado}</span>
                `;
                list.appendChild(item);
            });
        }

        async function iniciarPedido() {
            const destino = document.getElementById("destination").value.trim();
            if (!destino) {
                alert("Ingrese una dirección de entrega.");
                return;
            }

            // 1️⃣ Cambiar estado a Preparando y mostrar contador
            productos.forEach(p => p.estado = "Preparando");
            actualizarProductos();
            let contadorEl = document.getElementById("contador");
            contadorEl.textContent = "Estado: Preparando productos...";

            await sleep(3000); // 3 segundos de preparación
            contadorEl.textContent = "Estado: Levando productos...";

            // 2️⃣ Cambiar estado a En camino
            productos.forEach(p => p.estado = "En camino");
            actualizarProductos();

            // 3️⃣ Calcular ruta en el mapa
            directionsService.route({
                origin: empresa,
                destination: destino,
                travelMode: 'DRIVING'
            }, function (result, status) {
                if (status === 'OK') {
                    directionsRenderer.setDirections(result);
                    // Extraer tiempo estimado
                    const duration = result.routes[0].legs[0].duration.text;
                    contadorEl.textContent = `Estado: En camino (Tiempo estimado: ${duration})`;

                    // Agregar marcador de entrega
                    const endLocation = result.routes[0].legs[0].end_location;
                    const marker = new google.maps.Marker({
                        position: endLocation,
                        map: map,
                        label: "Entrega"
                    });
                    markers.push(marker);
                } else {
                    alert("No se pudo calcular la ruta: " + status);
                }
            });

            // 4️⃣ Simular entrega después de 10 segundos
            await sleep(10000);
            productos.forEach(p => p.estado = "Entregado");
            actualizarProductos();
            contadorEl.textContent = "Estado: Entregado";
        }

        function sleep(ms) {
            return new Promise(resolve => setTimeout(resolve, ms));
        }
    </script>

    <script async
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA0atjMQ_m8LKtU5PaXXVMHT2W0e3YxK1A&callback=initMap">
    </script>
</body>
</html>