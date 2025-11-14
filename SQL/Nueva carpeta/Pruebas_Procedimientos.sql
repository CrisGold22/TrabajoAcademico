USE REDCOM;

DELIMITER //

CAll insertarCuentaUsuario('jpz', 'passSADAS123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('jperez', 'pass123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('mgarcia', 'pass123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('lrodriguez', 'pass123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('acastro', 'pass123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('plopez', 'pass123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('rmartinez', 'pass123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('dsanchez', 'pass123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('mgonzalez', 'pass123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('ctorres', 'pass123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('jramirez', 'pass123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('admin1', 'admin123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('admin2', 'admin123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('admin3', 'admin123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('cflores', 'pass123', 1,@nuevo_id);
//
CAll insertarCuentaUsuario('avargas', 'pass123', 1,@nuevo_id);


//

CALL insertarCliente (50000.00, 'DISTRIBUIDOR', 45, 5, '12345678', 'Juan', 'Pérez', 'Gómez', 'MASCULINO', '1980-05-15', 987654321, 1, 1, @nuevo_id);
//
CALL insertarCliente(30000.00, 'REVENDEDOR', 30, 3, '23456789', 'María', 'García', 'López', 'FEMENINO', '1985-08-20', 987654322, 1, 2, @nuevo_id);
//
CALL insertarCliente(75000.00, 'RESTAURANTE', 60, 8, '34567890', 'Luis', 'Rodríguez', 'Martínez', 'MASCULINO', '1975-12-10', 987654323, 1, 3, @nuevo_id);
//
CALL insertarCliente(25000.00, 'HOTEL', 25, 2, '45678901', 'Ana', 'Castro', 'Silva', 'FEMENINO', '1990-03-25', 987654324, 1, 4, @nuevo_id);
//
CALL insertarCliente(40000.00, 'CATERING', 35, 4, '56789012', 'Pedro', 'López', 'Díaz', 'MASCULINO', '1982-07-18', 987654325, 1, 5, @nuevo_id);
//
CALL insertarCliente(100000.00, 'GOBIERNO', 80, 10, '67890123', 'Rosa', 'Martínez', 'Ruiz', 'FEMENINO', '1978-11-30', 987654326, 1, 6, @nuevo_id);
//
CALL insertarCliente(35000.00, 'DISTRIBUIDOR', 28, 3, '78901234', 'David', 'Sánchez', 'Vargas', 'MASCULINO', '1988-04-12', 987654327, 1, 7, @nuevo_id);
//
CALL insertarCliente(28000.00, 'REVENDEDOR', 22, 2, '89012345', 'Marta', 'González', 'Castro', 'FEMENINO', '1992-09-05', 987654328, 1, 8, @nuevo_id);
//
CALL insertarCliente(60000.00, 'RESTAURANTE', 48, 6, '90123456', 'Carlos', 'Torres', 'Rojas', 'MASCULINO', '1983-01-22', 987654329, 1, 9, @nuevo_id);
//
CALL insertarCliente(32000.00, 'HOTEL', 26, 2, '11223344', 'Julia', 'Ramírez', 'Mendoza', 'FEMENINO', '1987-06-14', 987654330, 1, 10, @nuevo_id);
//
CALL insertarCliente(45000.00, 'CATERING', 38, 4, '22334455', 'Fernando', 'Flores', 'Salas', 'MASCULINO', '1981-02-28', 987654331, 1, 14, @nuevo_id);
//
CALL insertarCliente(85000.00, 'GOBIERNO', 65, 7, '33445566', 'Andrea', 'Vargas', 'Paredes', 'FEMENINO', '1976-10-08', 987654332, 1, 15, @nuevo_id);

//

CALL insertarEmpresa('20123456789', 'DISTRIBUIDORA PERÚ SAC', 'Av. Principal 123', 'Lima', 'Lima', 'PUEBLO_LIBRE', '012345678', 'contacto@distribuidora.com', '15081', 1, 1, @nuevo_id);
//
CALL insertarEmpresa('20234567890', 'REVENDE EXPRESS EIRL', 'Jr. Comercio 456', 'Lima', 'Lima', 'SAN_MIGUEL', '023456789', 'ventas@revendeexpress.com', '15082', 1, 2, @nuevo_id);
//
CALL insertarEmpresa('20345678901', 'RESTAURANTE SABORES SAC', 'Av. Gourmet 789', 'Lima', 'Lima', 'SAN_BORJA', '034567890', 'reservas@sabores.com', '15083', 1, 3, @nuevo_id);
//
CALL insertarEmpresa('20456789012', 'HOTEL PARAÍSO SA', 'Calle Descanso 321', 'Lima', 'Lima', 'SAN_LUIS', '045678901', 'recepcion@hotelparaiso.com', '15084', 1, 4, @nuevo_id);
//
CALL insertarEmpresa('20567890123', 'CATERING DELICIAS EIRL', 'Av. Eventos 654', 'Lima', 'Lima', 'LINCE', '056789012', 'eventos@cateringdelicias.com',  '15085', 1, 5, @nuevo_id);
//
CALL insertarEmpresa('20678901234', 'MINISTERIO DE TURISMO', 'Av. Gobierno 987', 'Lima', 'Lima', 'CALLAO', '067890123', 'info@mincetur.gob.pe', '07011', 1, 6, @nuevo_id);
//
CALL insertarEmpresa('20789012345', 'DISTRIBUIDORA NORTE SAC', 'Av. Industrial 147', 'Lima', 'Lima', 'LA_VICTORIA', '078901234', 'ventas@distribuidoranorte.com', '15087', 1, 7, @nuevo_id);
//
CALL insertarEmpresa('20890123456', 'REVENDE TODO EIRL', 'Jr. Ventas 258', 'Lima', 'Lima', 'PUEBLO_LIBRE', '089012345', 'info@revendetodo.com',  '15081', 1, 8, @nuevo_id);
//
CALL insertarEmpresa('20901234567', 'RESTAURANTE MAR Y TIERRA', 'Av. Costanera 369', 'Lima', 'Lima', 'SAN_MIGUEL', '090123456', 'reservas@marytierra.com', '15082', 1, 9, @nuevo_id);
//
CALL insertarEmpresa('20112345678', 'HOTEL BUSINESS SA', 'Calle Ejecutivos 741', 'Lima', 'Lima', 'SAN_BORJA', '011234567', 'reservas@hotelbusiness.com', '15083', 1, 10, @nuevo_id);
//
CALL insertarEmpresa('20123456780', 'CATERING ÉLITE EIRL', 'Av. Exclusiva 852', 'Lima', 'Lima', 'SAN_LUIS', '012345680', 'servicio@cateringelite.com', '15084', 1, 11, @nuevo_id);
//
CALL insertarEmpresa('20134567891', 'GOBIERNO REGIONAL LIMA', 'Av. Regional 963', 'Lima', 'Lima', 'LINCE', '013456789', 'informes@gobiernolima.gob.pe', '15085',1, 12, @nuevo_id);
//
CALL insertarEmpresa('20134567892', 'GOBIERNO REGIONAL LINCE', 'Av. Regional 962', 'Lima', 'Lima', 'LINCE', '013456789', 'informes@gobiernolince.gob.pe', '15085', 1, 12, @nuevo_id);
//
CALL insertarAdministrador('ADMINISTRADOR', 'COMPLETO', '87654321', 'Ricardo', 'Silva', 'Mendoza', 'MASCULINO', '1980-03-15', 987654340, 5000.00, 1, 11, @nuevo_id);
//
CALL insertarAdministrador('GESTOR_PRODUCTOS', 'PARCIAL', '76543218', 'Laura', 'Hernández', 'Quispe', 'FEMENINO', '1985-07-22', 987654341, 3500.00, 1, 12, @nuevo_id);
//
CALL insertarAdministrador('GESTOR_PEDIDOS', 'PARCIAL', '65432187', 'Jorge', 'Díaz', 'Salazar', 'MASCULINO', '1982-11-30', 987654342, 3200.00, 1, 13, @nuevo_id);
//
CALL insertarCategoriaProducto('Bebidas', 'Bebidas y refrescos', NULL, 1, @nuevo_id);
//
CALL insertarCategoriaProducto('Lácteos', 'Productos lácteos', NULL, 1, @nuevo_id);
//
CALL insertarCategoriaProducto('Carnes', 'Carnes y embutidos', NULL, 1, @nuevo_id);
//
CALL insertarCategoriaProducto('Frutas', 'Frutas frescas', NULL, 1, @nuevo_id);
//
CALL insertarCategoriaProducto('Verduras', 'Verduras frescas', NULL, 1, @nuevo_id);
//
CALL insertarCategoriaProducto('Bebidas Gaseosas', 'Refrescos carbonatados', 1, 1, @nuevo_id);
//
CALL insertarCategoriaProducto('Bebidas Naturales', 'Jugos y bebidas naturales', 1, 1, @nuevo_id);
//
CALL insertarCategoriaProducto('Leches', 'Leche y derivados', 2, 1, @nuevo_id);
//
CALL insertarCategoriaProducto('Quesos', 'Quesos de todo tipo', 2, 1, @nuevo_id);
//
CALL insertarCategoriaProducto('Carnes Rojas', 'Carne de res y cerdo', 3, 1, @nuevo_id);
//
CALL insertarCategoriaProducto('Carnes Blancas', 'Pollo y pavo', 3, 1, @nuevo_id);
//
CALL insertarCategoriaProducto('Frutas Tropicales', 'Frutas de clima tropical', 4, 1, @nuevo_id);
//
CALL insertarCategoriaProducto('Frutas Cítricas', 'Naranjas, limones, etc.', 4, 1, @nuevo_id);
//
CALL insertarCategoriaProducto('Verduras de Hoja', 'Lechuga, espinaca, etc.', 5, 1, @nuevo_id);
//
CALL insertarCategoriaProducto('Verduras de Raíz', 'Zanahoria, papa, etc.', 5, 1, @nuevo_id);

//
CALL insertarProducto('Coca Cola 3L', 'SKU001', 'Gaseosa Coca Cola 3 litros', 12.50, 10.00, 'UNIDAD', 100, 500, 1, 6, 'Coca Cola', @nuevo_id);
//
CALL insertarProducto('Leche Gloria 1L', 'SKU002', 'Leche evaporada Gloria 1L', 8.00, 6.50, 'UNIDAD', 80, 400, 1, 8, 'Gloria', @nuevo_id);
//
CALL insertarProducto('Pechuga de Pollo', 'SKU003', 'Pechuga de pollo fresca', 18.00, 15.00, 'KILOGRAMO', 50, 200, 1, 11, 'San Fernando', @nuevo_id);
//
CALL insertarProducto('Mango Kent', 'SKU004', 'Mango Kent de exportación', 12.00, 9.50, 'KILOGRAMO', 60, 300, 1, 12, 'AgroExport', @nuevo_id);
//
CALL insertarProducto('Lechuga Americana', 'SKU005', 'Lechuga fresca americana', 6.50, 5.00, 'UNIDAD', 40, 150, 1, 14, 'Verduras Frescas', @nuevo_id);
//
CALL insertarProducto('Inca Kola 2L', 'SKU006', 'Gaseosa Inca Kola 2 litros', 10.00, 8.00, 'UNIDAD', 120, 600, 1, 6, 'Inca Kola', @nuevo_id);
//
CALL insertarProducto('Yogurt Gloria', 'SKU007', 'Yogurt bebible Gloria', 5.50, 4.50, 'UNIDAD', 90, 450, 1, 8, 'Gloria', @nuevo_id);
//
CALL insertarProducto('Lomo Fino', 'SKU008', 'Lomo fino de res', 35.00, 28.00, 'KILOGRAMO', 30, 100, 1, 10, 'Carnes Premium', @nuevo_id);
//
CALL insertarProducto('Naranja Valencia', 'SKU009', 'Naranja dulce Valencia', 8.00, 6.00, 'KILOGRAMO', 70, 350, 1, 13, 'Cítricos Perú', @nuevo_id);
//
CALL insertarProducto('Zanahoria Fresca', 'SKU010', 'Zanahoria orgánica fresca', 4.50, 3.50, 'KILOGRAMO', 55, 250, 1, 15, 'Orgánicos', @nuevo_id);
//
CALL insertarProducto('Sprite 2L', 'SKU011', 'Gaseosa Sprite 2 litros', 9.50, 7.50, 'UNIDAD', 110, 550, 1, 6, 'Sprite', @nuevo_id);
//
CALL insertarProducto('Queso Fresco', 'SKU012', 'Queso fresco andino', 16.00, 13.00, 'KILOGRAMO', 35, 120, 1, 9, 'Quesos Andinos', @nuevo_id);
//
CALL insertarProducto('Pavo Entero', 'SKU013', 'Pavo entero congelado', 45.00, 38.00, 'KILOGRAMO', 20, 80, 1, 11, 'Avícola', @nuevo_id);
//
CALL insertarProducto('Limón Sutil', 'SKU014', 'Limón sutil fresco', 6.00, 4.50, 'KILOGRAMO', 65, 280, 1, 13, 'Cítricos Perú', @nuevo_id);
//
CALL insertarProducto('Espinaca Fresca', 'SKU015', 'Espinaca orgánica fresca', 7.50, 6.00, 'ATADO', 45, 180, 1, 14, 'Orgánicos', @nuevo_id);
//

CALL insertarDescuento(9.00, 100, 50, 1, 1, @nuevo_id);
//
CALL insertarDescuento(5.80, 80, 40, 1, 2, @nuevo_id);
//
CALL insertarDescuento(13.50, 60, 30, 1, 3, @nuevo_id);
//
CALL insertarDescuento(8.00, 70, 35, 1, 4, @nuevo_id);
//
CALL insertarDescuento(4.20, 50, 25, 1, 5, @nuevo_id);
//
CALL insertarDescuento(7.20, 120, 60, 1, 6, @nuevo_id);
//
CALL insertarDescuento(4.00, 90, 45, 1, 7, @nuevo_id);
//
CALL insertarDescuento(25.00, 40, 20, 1, 8, @nuevo_id);
//
CALL insertarDescuento(5.20, 70, 35, 1, 9, @nuevo_id);
//
CALL insertarDescuento(3.00, 55, 28, 1, 10, @nuevo_id);
//
CALL insertarDescuento(6.80, 110, 55, 1, 11, @nuevo_id);
//
CALL insertarDescuento(11.50, 35, 18, 1, 12, @nuevo_id);
//
CALL insertarDescuento(34.00, 25, 12, 1, 13, @nuevo_id);
//
CALL insertarDescuento(4.00, 65, 32, 1, 14, @nuevo_id);
//
CALL insertarDescuento(5.20, 45, 22, 1, 15, @nuevo_id);
//
CALL insertarCarritoDeCompras(150.50, 'EN_PROCESO', 15.05, 1, 135.45, 1, @nuevo_id);
//
CALL insertarCarritoDeCompras(280.75, 'PENDIENTE', 28.08, 2, 252.67, 1, @nuevo_id);
//
CALL insertarCarritoDeCompras(420.30, 'CONFIRMADO', 42.03, 3, 378.27, 1, @nuevo_id);
//
CALL insertarCarritoDeCompras(95.20, 'CANCELADO', 9.52, 4, 85.68, 1, @nuevo_id);
//
CALL insertarCarritoDeCompras(320.60, 'COMPLETADO', 32.06, 5, 288.54, 1, @nuevo_id);
//
CALL insertarCarritoDeCompras(180.90, 'EXPIRADO', 18.09, 6, 162.81, 1, @nuevo_id);
//
CALL insertarCarritoDeCompras(240.15, 'EN_REVISION', 24.02, 7, 216.13, 1, @nuevo_id);
//
CALL insertarCarritoDeCompras(160.80, 'VACIO', 0.00, 8, 160.80, 1, @nuevo_id);
//
CALL insertarCarritoDeCompras(380.45, 'EN_PROCESO', 38.05, 9, 342.41, 1, @nuevo_id);
//
CALL insertarCarritoDeCompras(210.70, 'PENDIENTE', 21.07, 10, 189.63, 1, @nuevo_id);
//
CALL insertarCarritoDeCompras(290.25, 'CONFIRMADO', 29.03, 11, 261.23, 1, @nuevo_id);
//
CALL insertarCarritoDeCompras(130.40, 'CANCELADO', 13.04, 12, 117.36, 1, @nuevo_id);
//
CALL insertarCarritoDeCompras(150.50, 'CONFIRMADO', 15.05, 1, 135.45, 1, @nuevo_id);
//
CALL insertarLineaCarrito(5, 12.50, 62.50, 1, 1, 1, @nuevo_id);
//
CALL insertarLineaCarrito(3, 18.00, 54.00, 1, 1, 3, @nuevo_id);
//
CALL insertarLineaCarrito(2, 8.00, 16.00, 2, 1, 2, @nuevo_id);
//
CALL insertarLineaCarrito(4, 12.00, 48.00, 2, 1, 4, @nuevo_id);
//
CALL insertarLineaCarrito(10, 6.50, 65.00, 3, 1, 5, @nuevo_id);
//
CALL insertarLineaCarrito(6, 10.00, 60.00, 3, 1, 6, @nuevo_id);
//
CALL insertarLineaCarrito(8, 5.50, 44.00, 4, 1, 7, @nuevo_id);
//
CALL insertarLineaCarrito(2, 35.00, 70.00, 4, 1, 8, @nuevo_id);
//
CALL insertarLineaCarrito(7, 8.00, 56.00, 5, 1, 9, @nuevo_id);
//
CALL insertarLineaCarrito(5, 4.50, 22.50, 5, 1, 10, @nuevo_id);
//
CALL insertarLineaCarrito(4, 9.50, 38.00, 6, 1, 11, @nuevo_id);
//
CALL insertarLineaCarrito(3, 16.00, 48.00, 6, 1, 12, @nuevo_id);
//
CALL insertarLineaCarrito(1, 45.00, 45.00, 7, 1, 13, @nuevo_id);
//
CALL insertarLineaCarrito(6, 6.00, 36.00, 7, 1, 14, @nuevo_id);
//
CALL insertarLineaCarrito(4, 7.50, 30.00, 8, 1, 15, @nuevo_id);
//
CALL insertarLineaCarrito(5, 12.50, 62.50, 1, 1, 1, @nuevo_id);
//
CALL insertarLineaCarrito(3, 18.00, 54.00, 1, 1, 3, @nuevo_id);
//
CALL insertarOrdenCompra('2024-01-15', 135.45, 121.91, 13.54, 'PAGADO', 1, 1, 1, 1, @nuevo_id);
//
CALL insertarOrdenCompra('2024-01-16', 252.67, 227.40, 25.27, 'EN_PREPARACION', 2, 1, 2, 2, @nuevo_id);
//
CALL insertarOrdenCompra('2024-01-17', 378.27, 340.44, 37.83, 'ENVIADO', 3, 1, 3, 3, @nuevo_id);
//
CALL insertarOrdenCompra('2024-01-18', 85.68, 77.11, 8.57, 'ENTREGADO', 4, 1, 4, 4, @nuevo_id);
//
CALL insertarOrdenCompra('2024-01-19', 288.54, 259.69, 28.85, 'CANCELADO', 5, 1, 5, 5, @nuevo_id);
//
CALL insertarOrdenCompra('2024-01-20', 162.81, 146.53, 16.28, 'REEMBOLSADO', 6, 1, 6, 6, @nuevo_id);
//
CALL insertarOrdenCompra('2024-01-21', 216.13, 194.52, 21.61, 'EXPIRADO', 7, 1, 7, 7, @nuevo_id);
//
CALL insertarOrdenCompra('2024-01-22', 160.80, 144.72, 16.08, 'PAGADO', 8, 1, 8, 8, @nuevo_id);
//
CALL insertarOrdenCompra('2024-01-23', 342.41, 308.17, 34.24, 'EN_PREPARACION', 9, 1, 9, 9, @nuevo_id);
//
CALL insertarOrdenCompra('2024-01-24', 189.63, 170.67, 18.96, 'ENVIADO', 10, 1, 10, 10, @nuevo_id);
//
CALL insertarOrdenCompra('2024-01-25', 261.23, 235.11, 26.12, 'ENTREGADO', 11, 1, 11, 11, @nuevo_id);
//
CALL insertarOrdenCompra('2024-01-26', 117.36, 105.62, 11.74, 'CANCELADO', 12, 1, 12, 12, @nuevo_id);
//
CALL insertarLineaOrdenCompra(5, 10.00, 50.00, 1, 1, 1, @nuevo_id);
//
CALL insertarLineaOrdenCompra(3, 15.00, 45.00, 3, 1, 1, @nuevo_id);
//
CALL insertarLineaOrdenCompra(3, 6.50, 19.50, 2, 2, 1, @nuevo_id);
//
CALL insertarLineaOrdenCompra(4, 9.50, 38.00, 4, 2, 1, @nuevo_id);
//
CALL insertarLineaOrdenCompra(10, 5.00, 50.00, 5, 3, 1, @nuevo_id);
//
CALL insertarLineaOrdenCompra(6, 8.00, 48.00, 6, 3, 1, @nuevo_id);
//
CALL insertarLineaOrdenCompra(8, 4.50, 36.00, 7, 4, 1, @nuevo_id);
//
CALL insertarLineaOrdenCompra(2, 28.00, 56.00, 8, 4, 1, @nuevo_id);
//
CALL insertarLineaOrdenCompra(7, 6.00, 42.00, 9, 5, 1, @nuevo_id);
//
CALL insertarLineaOrdenCompra(5, 3.50, 17.50, 10, 5, 1, @nuevo_id);
//
CALL insertarLineaOrdenCompra(4, 7.50, 30.00, 11, 6, 1, @nuevo_id);
//
CALL insertarLineaOrdenCompra(3, 13.00, 39.00, 12, 6, 1, @nuevo_id);
//
CALL insertarDetalleDeEnvio('Entrega en recepción', 'Av. Principal 123', 'PUEBLO_LIBRE', '2024-01-16 10:00:00', '2024-01-16 10:00:00', 1, 1, @nuevo_id);
//
CALL insertarDetalleDeEnvio('Dejar con conserje', 'Jr. Comercio 456', 'SAN_MIGUEL', '2024-01-17 14:00:00', '2024-01-17 14:00:00', 2, 1, @nuevo_id);
//
CALL insertarDetalleDeEnvio('Entregar en cocina', 'Av. Gourmet 789', 'SAN_BORJA', '2024-01-18 11:30:00', '2024-01-18 11:30:00', 3, 1, @nuevo_id);
//
CALL insertarDetalleDeEnvio('Recepción hotel', 'Calle Descanso 321', 'SAN_LUIS', '2024-01-19 16:00:00', '2024-01-19 16:00:00', 4, 1, @nuevo_id);
//
CALL insertarDetalleDeEnvio('Área de eventos', 'Av. Eventos 654', 'LINCE', '2024-01-20 09:00:00', '2024-01-20 09:00:00', 5, 1, @nuevo_id);
//
CALL insertarDetalleDeEnvio('Oficina principal', 'Av. Gobierno 987', 'CALLAO', '2024-01-21 15:30:00', '2024-01-21 15:30:00', 6, 1, @nuevo_id);
//
CALL insertarDetalleDeEnvio('Bodega principal', 'Av. Industrial 147', 'LA_VICTORIA', '2024-01-22 13:00:00', '2024-01-22 13:00:00', 7, 1, @nuevo_id);
//
CALL insertarDetalleDeEnvio('Local comercial', 'Jr. Ventas 258', 'PUEBLO_LIBRE', '2024-01-23 10:30:00', '2024-01-23 10:30:00', 8, 1, @nuevo_id);
//
CALL insertarDetalleDeEnvio('Entrada restaurante', 'Av. Costanera 369', 'SAN_MIGUEL', '2024-01-24 12:00:00', '2024-01-24 12:00:00', 9, 1, @nuevo_id);
//
CALL insertarDetalleDeEnvio('Recepción hotel', 'Calle Ejecutivos 741', 'SAN_BORJA', '2024-01-25 17:00:00', '2024-01-25 17:00:00', 10, 1, @nuevo_id);
//
CALL insertarDetalleDeEnvio('Salón de eventos', 'Av. Exclusiva 852', 'SAN_LUIS', '2024-01-26 08:30:00', '2024-01-26 08:30:00', 11, 1, @nuevo_id);
//
CALL insertarDetalleDeEnvio('Oficina regional', 'Av. Regional 963', 'LINCE', '2024-01-27 14:30:00', '2024-01-27 14:30:00', 12, 1, @nuevo_id);
//