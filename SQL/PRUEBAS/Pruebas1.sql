USE REDCOM;

-- ============================================================
-- DATOS PARA CADA TABLA
-- ============================================================

-- CuentaUsuario 
INSERT INTO CuentaUsuario (idCuentaUsuario, userName, password, activo) VALUES
(1, 'jperez', 'pass123', 1),
(2, 'mgarcia', 'pass123', 1),
(3, 'lrodriguez', 'pass123', 1),
(4, 'acastro', 'pass123', 1),
(5, 'plopez', 'pass123', 1),
(6, 'rmartinez', 'pass123', 1),
(7, 'dsanchez', 'pass123', 1),
(8, 'mgonzalez', 'pass123', 1),
(9, 'ctorres', 'pass123', 1),
(10, 'jramirez', 'pass123', 1),
(11, 'admin1', 'admin123', 1),
(12, 'admin2', 'admin123', 1),
(13, 'admin3', 'admin123', 1),
(14, 'cflores', 'pass123', 1),
(15, 'avargas', 'pass123', 1);

-- Cliente 
INSERT INTO Cliente (idCliente, lineaCredito, categoria, numeroDePedido_Historico, numeroDePedido_MensualPro, dni, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento, telefono, activo, idCuentaUsuario) VALUES
(1, 50000.00, 'DISTRIBUIDOR', 45, 5, '12345678', 'Juan', 'Pérez', 'Gómez', 'MASCULINO', '1980-05-15', 987654321, 1, 1),
(2, 30000.00, 'REVENDEDOR', 30, 3, '23456789', 'María', 'García', 'López', 'FEMENINO', '1985-08-20', 987654322, 1, 2),
(3, 75000.00, 'RESTAURANTE', 60, 8, '34567890', 'Luis', 'Rodríguez', 'Martínez', 'MASCULINO', '1975-12-10', 987654323, 1, 3),
(4, 25000.00, 'HOTEL', 25, 2, '45678901', 'Ana', 'Castro', 'Silva', 'FEMENINO', '1990-03-25', 987654324, 1, 4),
(5, 40000.00, 'CATERING', 35, 4, '56789012', 'Pedro', 'López', 'Díaz', 'MASCULINO', '1982-07-18', 987654325, 1, 5),
(6, 100000.00, 'GOBIERNO', 80, 10, '67890123', 'Rosa', 'Martínez', 'Ruiz', 'FEMENINO', '1978-11-30', 987654326, 1, 6),
(7, 35000.00, 'DISTRIBUIDOR', 28, 3, '78901234', 'David', 'Sánchez', 'Vargas', 'MASCULINO', '1988-04-12', 987654327, 1, 7),
(8, 28000.00, 'REVENDEDOR', 22, 2, '89012345', 'Marta', 'González', 'Castro', 'FEMENINO', '1992-09-05', 987654328, 1, 8),
(9, 60000.00, 'RESTAURANTE', 48, 6, '90123456', 'Carlos', 'Torres', 'Rojas', 'MASCULINO', '1983-01-22', 987654329, 1, 9),
(10, 32000.00, 'HOTEL', 26, 2, '11223344', 'Julia', 'Ramírez', 'Mendoza', 'FEMENINO', '1987-06-14', 987654330, 1, 10),
(11, 45000.00, 'CATERING', 38, 4, '22334455', 'Fernando', 'Flores', 'Salas', 'MASCULINO', '1981-02-28', 987654331, 1, 14),
(12, 85000.00, 'GOBIERNO', 65, 7, '33445566', 'Andrea', 'Vargas', 'Paredes', 'FEMENINO', '1976-10-08', 987654332, 1, 15);

-- Empresa 
INSERT INTO Empresa (idEmpresa, ruc, razonSocial, direccionFiscal, departamento, provincia, distrito, telefono, email, activo, codigoPostal, cliente_idCliente) VALUES
(1, '20123456789', 'DISTRIBUIDORA PERÚ SAC', 'Av. Principal 123', 'Lima', 'Lima', 'PUEBLO_LIBRE', '012345678', 'contacto@distribuidora.com', 1, '15081', 1),
(2, '20234567890', 'REVENDE EXPRESS EIRL', 'Jr. Comercio 456', 'Lima', 'Lima', 'SAN_MIGUEL', '023456789', 'ventas@revendeexpress.com', 1, '15082', 2),
(3, '20345678901', 'RESTAURANTE SABORES SAC', 'Av. Gourmet 789', 'Lima', 'Lima', 'SAN_BORJA', '034567890', 'reservas@sabores.com', 1, '15083', 3),
(4, '20456789012', 'HOTEL PARAÍSO SA', 'Calle Descanso 321', 'Lima', 'Lima', 'SAN_LUIS', '045678901', 'recepcion@hotelparaiso.com', 1, '15084', 4),
(5, '20567890123', 'CATERING DELICIAS EIRL', 'Av. Eventos 654', 'Lima', 'Lima', 'LINCE', '056789012', 'eventos@cateringdelicias.com', 1, '15085', 5),
(6, '20678901234', 'MINISTERIO DE TURISMO', 'Av. Gobierno 987', 'Lima', 'Lima', 'CALLAO', '067890123', 'info@mincetur.gob.pe', 1, '07011', 6),
(7, '20789012345', 'DISTRIBUIDORA NORTE SAC', 'Av. Industrial 147', 'Lima', 'Lima', 'LA_VICTORIA', '078901234', 'ventas@distribuidoranorte.com', 1, '15087', 7),
(8, '20890123456', 'REVENDE TODO EIRL', 'Jr. Ventas 258', 'Lima', 'Lima', 'PUEBLO_LIBRE', '089012345', 'info@revendetodo.com', 1, '15081', 8),
(9, '20901234567', 'RESTAURANTE MAR Y TIERRA', 'Av. Costanera 369', 'Lima', 'Lima', 'SAN_MIGUEL', '090123456', 'reservas@marytierra.com', 1, '15082', 9),
(10, '20112345678', 'HOTEL BUSINESS SA', 'Calle Ejecutivos 741', 'Lima', 'Lima', 'SAN_BORJA', '011234567', 'reservas@hotelbusiness.com', 1, '15083', 10),
(11, '20123456780', 'CATERING ÉLITE EIRL', 'Av. Exclusiva 852', 'Lima', 'Lima', 'SAN_LUIS', '012345680', 'servicio@cateringelite.com', 1, '15084', 11),
(12, '20134567891', 'GOBIERNO REGIONAL LIMA', 'Av. Regional 963', 'Lima', 'Lima', 'LINCE', '013456789', 'informes@gobiernolima.gob.pe', 1, '15085', 12),
(13, '20134567892', 'GOBIERNO REGIONAL LINCE', 'Av. Regional 962', 'Lima', 'Lima', 'LINCE', '013456789', 'informes@gobiernolince.gob.pe', 1, '15085', 12);

-- Administrador 
INSERT INTO Administrador (idAdministrador, cargo, jerarquia, dni, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento, telefono, sueldo, activo, idCuentaUsuario) VALUES
(1, 'ADMINISTRADOR', 'COMPLETO', '87654321', 'Ricardo', 'Silva', 'Mendoza', 'MASCULINO', '1980-03-15', 987654340, 5000.00, 1, 11),
(2, 'GESTOR_PRODUCTOS', 'PARCIAL', '76543218', 'Laura', 'Hernández', 'Quispe', 'FEMENINO', '1985-07-22', 987654341, 3500.00, 1, 12),
(3, 'GESTOR_PEDIDOS', 'PARCIAL', '65432187', 'Jorge', 'Díaz', 'Salazar', 'MASCULINO', '1982-11-30', 987654342, 3200.00, 1, 13);

-- CategoriaProducto
INSERT INTO CategoriaProducto (idCategoriaProducto, nombreCategoria, descripcion, idCategoriaPadre, activo) VALUES
(1, 'Bebidas', 'Bebidas y refrescos', NULL, 1),
(2, 'Lácteos', 'Productos lácteos', NULL, 1),
(3, 'Carnes', 'Carnes y embutidos', NULL, 1),
(4, 'Frutas', 'Frutas frescas', NULL, 1),
(5, 'Verduras', 'Verduras frescas', NULL, 1),
(6, 'Bebidas Gaseosas', 'Refrescos carbonatados', 1, 1),
(7, 'Bebidas Naturales', 'Jugos y bebidas naturales', 1, 1),
(8, 'Leches', 'Leche y derivados', 2, 1),
(9, 'Quesos', 'Quesos de todo tipo', 2, 1),
(10, 'Carnes Rojas', 'Carne de res y cerdo', 3, 1),
(11, 'Carnes Blancas', 'Pollo y pavo', 3, 1),
(12, 'Frutas Tropicales', 'Frutas de clima tropical', 4, 1),
(13, 'Frutas Cítricas', 'Naranjas, limones, etc.', 4, 1),
(14, 'Verduras de Hoja', 'Lechuga, espinaca, etc.', 5, 1),
(15, 'Verduras de Raíz', 'Zanahoria, papa, etc.', 5, 1);

-- Producto 
INSERT INTO Producto (id_Producto, nombre, sku, descripcion, precioRegular, precioAlMayor, unidadDeMedida, stockDisponible, stockMaximo, activo, marca, categoriaProducto_idCategoriaProducto) VALUES
(1, 'Coca Cola 3L', 'SKU001', 'Gaseosa Coca Cola 3 litros', 12.50, 10.00, 'UNIDAD', 100, 500, 1, 'Coca Cola', 6),
(2, 'Leche Gloria 1L', 'SKU002', 'Leche evaporada Gloria 1L', 8.00, 6.50, 'UNIDAD', 80, 400, 1, 'Gloria', 8),
(3, 'Pechuga de Pollo', 'SKU003', 'Pechuga de pollo fresca', 18.00, 15.00, 'KILOGRAMO', 50, 200, 1, 'San Fernando', 11),
(4, 'Mango Kent', 'SKU004', 'Mango Kent de exportación', 12.00, 9.50, 'KILOGRAMO', 60, 300, 1, 'AgroExport', 12),
(5, 'Lechuga Americana', 'SKU005', 'Lechuga fresca americana', 6.50, 5.00, 'UNIDAD', 40, 150, 1, 'Verduras Frescas', 14),
(6, 'Inca Kola 2L', 'SKU006', 'Gaseosa Inca Kola 2 litros', 10.00, 8.00, 'UNIDAD', 120, 600, 1, 'Inca Kola', 6),
(7, 'Yogurt Gloria', 'SKU007', 'Yogurt bebible Gloria', 5.50, 4.50, 'UNIDAD', 90, 450, 1, 'Gloria', 8),
(8, 'Lomo Fino', 'SKU008', 'Lomo fino de res', 35.00, 28.00, 'KILOGRAMO', 30, 100, 1, 'Carnes Premium', 10),
(9, 'Naranja Valencia', 'SKU009', 'Naranja dulce Valencia', 8.00, 6.00, 'KILOGRAMO', 70, 350, 1, 'Cítricos Perú', 13),
(10, 'Zanahoria Fresca', 'SKU010', 'Zanahoria orgánica fresca', 4.50, 3.50, 'KILOGRAMO', 55, 250, 1, 'Orgánicos', 15),
(11, 'Sprite 2L', 'SKU011', 'Gaseosa Sprite 2 litros', 9.50, 7.50, 'UNIDAD', 110, 550, 1, 'Sprite', 6),
(12, 'Queso Fresco', 'SKU012', 'Queso fresco andino', 16.00, 13.00, 'KILOGRAMO', 35, 120, 1, 'Quesos Andinos', 9),
(13, 'Pavo Entero', 'SKU013', 'Pavo entero congelado', 45.00, 38.00, 'KILOGRAMO', 20, 80, 1, 'Avícola', 11),
(14, 'Limón Sutil', 'SKU014', 'Limón sutil fresco', 6.00, 4.50, 'KILOGRAMO', 65, 280, 1, 'Cítricos Perú', 13),
(15, 'Espinaca Fresca', 'SKU015', 'Espinaca orgánica fresca', 7.50, 6.00, 'ATADO', 45, 180, 1, 'Orgánicos', 14);

-- Descuento 
INSERT INTO Descuento (idDescuento, precioPorVolumen, cantidadMax, cantidadMin, activo, producto_ID_Producto) VALUES
(1, 9.00, 100, 50, 1, 1), 
(2, 5.80, 80, 40, 1, 2), 
(3, 13.50, 60, 30, 1, 3),
(4, 8.00, 70, 35, 1, 4), 
(5, 4.20, 50, 25, 1, 5), 
(6, 7.20, 120, 60, 1, 6),
(7, 4.00, 90, 45, 1, 7), 
(8, 25.00, 40, 20, 1, 8), 
(9, 5.20, 70, 35, 1, 9),
(10, 3.00, 55, 28, 1, 10),
(11, 6.80, 110, 55, 1, 11), 
(12, 11.50, 35, 18, 1, 12),
(13, 34.00, 25, 12, 1, 13), 
(14, 4.00, 65, 32, 1, 14), 
(15, 5.20, 45, 22, 1, 15);

-- CarritoDeCompras 
INSERT INTO CarritoDeCompras (id_CarritoDeCompras, subtotal, estado, descuento, montoFinal, cliente_idCliente, activo) VALUES
(1, 150.50, 'EN_PROCESO', 15.05, 135.45, 1, 1), 
(2, 280.75, 'PENDIENTE', 28.08, 252.67, 2, 1),
(3, 420.30, 'CONFIRMADO', 42.03, 378.27, 3, 1),
(4, 95.20, 'CANCELADO', 9.52, 85.68, 4, 1),
(5, 320.60, 'COMPLETADO', 32.06, 288.54, 5, 1),
(6, 180.90, 'EXPIRADO', 18.09, 162.81, 6, 1),
(7, 240.15, 'EN_REVISION', 24.02, 216.13, 7, 1),
(8, 160.80, 'VACIO', 0.00, 160.80, 8, 1),
(9, 380.45, 'EN_PROCESO', 38.05, 342.41, 9, 1), 
(10, 210.70, 'PENDIENTE', 21.07, 189.63, 10, 1),
(11, 290.25, 'CONFIRMADO', 29.03, 261.23, 11, 1),
(12, 130.40, 'CANCELADO', 13.04, 117.36, 12, 1),
(13, 150.50, 'CONFIRMADO', 15.05, 135.45, 1, 1);

-- LineaCarrito 
INSERT INTO LineaCarrito (idLineaCarrito, cantidad, precio, subTotal, carritoDeCompras_Productos, activo, producto_ID_Producto) VALUES
(1, 5, 12.50, 62.50, 1, 1, 1), 
(2, 3, 18.00, 54.00, 1, 1, 3),
(3, 2, 8.00, 16.00, 2, 1, 2),
(4, 4, 12.00, 48.00, 2, 1, 4), 
(5, 10, 6.50, 65.00, 3, 1, 5), 
(6, 6, 10.00, 60.00, 3, 1, 6),
(7, 8, 5.50, 44.00, 4, 1, 7),
(8, 2, 35.00, 70.00, 4, 1, 8),
(9, 7, 8.00, 56.00, 5, 1, 9),
(10, 5, 4.50, 22.50, 5, 1, 10),
(11, 4, 9.50, 38.00, 6, 1, 11),
(12, 3, 16.00, 48.00, 6, 1, 12),
(13, 1, 45.00, 45.00, 7, 1, 13), 
(14, 6, 6.00, 36.00, 7, 1, 14), 
(15, 4, 7.50, 30.00, 8, 1, 15),
(16, 5, 12.50, 62.50, 1, 1, 1), 
(17, 3, 18.00, 54.00, 1, 1, 3);

-- OrdenCompra 
INSERT INTO OrdenCompra (idOrdenCompra, fechaCreacion, total_parcial, total_final, descuentoTotal, estado, carrito_idCarrito, activo, cliente_idCliente, empresa_idEmpresa) VALUES
(1, '2024-01-15', 135.45, 121.91, 13.54, 'PAGADO', 1, 1, 1, 1),
(2, '2024-01-16', 252.67, 227.40, 25.27, 'EN_PREPARACION', 2, 1, 2, 2),
(3, '2024-01-17', 378.27, 340.44, 37.83, 'ENVIADO', 3, 1, 3, 3),
(4, '2024-01-18', 85.68, 77.11, 8.57, 'ENTREGADO', 4, 1, 4, 4),
(5, '2024-01-19', 288.54, 259.69, 28.85, 'CANCELADO', 5, 1, 5, 5),
(6, '2024-01-20', 162.81, 146.53, 16.28, 'REEMBOLSADO', 6, 1, 6, 6),
(7, '2024-01-21', 216.13, 194.52, 21.61, 'EXPIRADO', 7, 1, 7, 7),
(8, '2024-01-22', 160.80, 144.72, 16.08, 'PAGADO', 8, 1, 8, 8),
(9, '2024-01-23', 342.41, 308.17, 34.24, 'EN_PREPARACION', 9, 1, 9, 9),
(10, '2024-01-24', 189.63, 170.67, 18.96, 'ENVIADO', 10, 1, 10, 10),
(11, '2024-01-25', 261.23, 235.11, 26.12, 'ENTREGADO', 11, 1, 11, 11),
(12, '2024-01-26', 117.36, 105.62, 11.74, 'CANCELADO', 12, 1, 12, 12);

-- LineaOrdenCompra 
INSERT INTO LineaOrdenCompra (idLineaOrdenCompra, cantidad, precio, subtotal, producto_ID_Producto, ordenCompra_IdPedido, activo) VALUES
(1, 5, 10.00, 50.00, 1, 1, 1),
(2, 3, 15.00, 45.00, 3, 1, 1), 
(3, 3, 6.50, 19.50, 2, 2, 1),
(4, 4, 9.50, 38.00, 4, 2, 1),
(5, 10, 5.00, 50.00, 5, 3, 1),
(6, 6, 8.00, 48.00, 6, 3, 1),
(7, 8, 4.50, 36.00, 7, 4, 1),
(8, 2, 28.00, 56.00, 8, 4, 1),
(9, 7, 6.00, 42.00, 9, 5, 1),
(10, 5, 3.50, 17.50, 10, 5, 1),
(11, 4, 7.50, 30.00, 11, 6, 1),
(12, 3, 13.00, 39.00, 12, 6, 1);

-- DetalleDeEnvio
INSERT INTO DetalleDeEnvio (id_DetalleEnvio, descripcion, direccion, distrito, fechaEntrega, horarioEntrega, ordenCompra_IdPedido, activo) VALUES
(1, 'Entrega en recepción', 'Av. Principal 123', 'PUEBLO_LIBRE', '2024-01-16 10:00:00', '2024-01-16 10:00:00', 1, 1),
(2, 'Dejar con conserje', 'Jr. Comercio 456', 'SAN_MIGUEL', '2024-01-17 14:00:00', '2024-01-17 14:00:00', 2, 1),
(3, 'Entregar en cocina', 'Av. Gourmet 789', 'SAN_BORJA', '2024-01-18 11:30:00', '2024-01-18 11:30:00', 3, 1),
(4, 'Recepción hotel', 'Calle Descanso 321', 'SAN_LUIS', '2024-01-19 16:00:00', '2024-01-19 16:00:00', 4, 1),
(5, 'Área de eventos', 'Av. Eventos 654', 'LINCE', '2024-01-20 09:00:00', '2024-01-20 09:00:00', 5, 1),
(6, 'Oficina principal', 'Av. Gobierno 987', 'CALLAO', '2024-01-21 15:30:00', '2024-01-21 15:30:00', 6, 1),
(7, 'Bodega principal', 'Av. Industrial 147', 'LA_VICTORIA', '2024-01-22 13:00:00', '2024-01-22 13:00:00', 7, 1),
(8, 'Local comercial', 'Jr. Ventas 258', 'PUEBLO_LIBRE', '2024-01-23 10:30:00', '2024-01-23 10:30:00', 8, 1),
(9, 'Entrada restaurante', 'Av. Costanera 369', 'SAN_MIGUEL', '2024-01-24 12:00:00', '2024-01-24 12:00:00', 9, 1),
(10, 'Recepción hotel', 'Calle Ejecutivos 741', 'SAN_BORJA', '2024-01-25 17:00:00', '2024-01-25 17:00:00', 10, 1),
(11, 'Salón de eventos', 'Av. Exclusiva 852', 'SAN_LUIS', '2024-01-26 08:30:00', '2024-01-26 08:30:00', 11, 1),
(12, 'Oficina regional', 'Av. Regional 963', 'LINCE', '2024-01-27 14:30:00', '2024-01-27 14:30:00', 12, 1);