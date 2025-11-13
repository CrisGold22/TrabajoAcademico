USE REDCOM;


DELIMITER //



INSERT INTO CategoriaProducto (idCategoriaProducto, Nombrecategoria, Descripcion, Catalogo_idcatalogo, Activo) VALUES
(1, 'Granos y Harinas', 'Productos de granos, harinas y cereales', 1, 1),
(2, 'Aceites y Grasas', 'Aceites vegetales y grasas comestibles', 1, 1),
(3, 'Lácteos', 'Leche, yogurt, queso y derivados lácteos', 1, 1),
(4, 'Conservas', 'Alimentos enlatados y conservados', 1, 1),
(5, 'Limpieza del Hogar', 'Productos de limpieza y aseo personal', 2, 1),
(6, 'Bebidas', 'Café, té, jugos y bebidas varias', 1, 1),
(7, 'Snacks y Dulces', 'Galletas, dulces y snacks variados', 1, 1);



//


INSERT INTO Producto (ID_Producto, Nombre, SKU, Descripcion, precioRegular, precioAlMayor, UnidadDeMedida, StockDisponible, StockMinimo, StockMaximo, Activo, Marca, CategoriaProducto_idcategoriaProducto) VALUES
(1, 'Arroz Extra', 'ARZ-EXT-001', 'Arroz de grano largo premium', 2.50, 1.80, 'Kg', 100, 20, 200, 1, 'GranoDorado', 1),
(2, 'Aceite Vegetal', 'ACE-VEG-002', 'Aceite de girasol 900ml', 4.20, 3.00, 'Litro', 80, 15, 150, 1, 'Natural', 2),
(3, 'Leche Entera', 'LEC-ENT-003', 'Leche pasteurizada 1L', 1.80, 1.30, 'Litro', 120, 25, 300, 1, 'LácteosFresh', 3),
(4, 'Azúcar Blanca', 'AZU-BLA-004', 'Azúcar refinada extra', 1.60, 1.10, 'Kg', 90, 30, 250, 1, 'DulcePureza', 1),
(5, 'Harina de Trigo', 'HAR-TRI-005', 'Harina todo uso 000', 2.00, 1.40, 'Kg', 110, 40, 350, 1, 'MolinoSureño', 1),
(8, 'Café Molido', 'CAF-MOL-008', 'Café tostado 500g', 8.50, 6.00, 'Kg', 45, 15, 100, 1, 'AromaCafé', 6),
(9, 'Pasta Spaghetti', 'PAS-SPA-009', 'Pasta italiana #5', 1.90, 1.20, 'Kg', 85, 25, 200, 1, 'PastaItalia', 1),
(10, 'Galletas Dulces', 'GAL-DUL-010', 'Galletas con chispas chocolate', 2.30, 1.60, 'Paquete', 95, 30, 220, 1, 'DulcesHoras', 7);

//
INSERT INTO cliente (lineacredito, Categoria, numeroDePedido_Historico, numeroDePedido_MensualPro, dni, nombre, apellidopaterno, apellidOMaterno, genero, fechaNacimiento, telefono, Activo, idCliente) VALUES
(5000.00, 'CATERING', 45, 8, '70123456', 'María', 'Gonzales', 'López', 'MUJER', '1985-03-15 00:00:00', 987654321, 1, 1),
(3000.00, 'REVENDEDOR', 28, 5, '70234567', 'Carlos', 'Rodríguez', 'Pérez', 'HOMBRE', '1990-07-22 00:00:00', 987654322, 1, 2),
(7500.00, 'RESTAURANTE', 89, 12, '70345678', 'Ana', 'Martínez', 'García', 'MUJER', '1982-11-08 00:00:00', 987654323, 1, 3),
(2000.00, 'HOTEL', 15, 3, '70456789', 'Luis', 'Torres', 'Silva', 'HOMBRE', '1995-05-30 00:00:00', 987654324, 1, 4),
(10000.00, 'GOBIERNO', 120, 15, '70567890', 'Elena', 'Vargas', 'Rojas', 'MUJER', '1978-12-10 00:00:00', 987654325, 1, 5);

//

INSERT INTO CarritoDeCompras (Id_CarritoDeCompras, Total_Parcial, Estado, total_con_descuento, cliente_idcliente) VALUES
(1, 185.50, 'EN_PROCESO', 175.50, 1),
(2, 92.75, 'EN_PROCESO', 92.75, 2),
(3, 325.80, 'EN_PROCESO', 305.80, 3),
(4, 45.20, 'EN_PROCESO', 42.20, 4),
(5, 520.40, 'EN_PROCESO', 490.40, 5);

//

INSERT INTO DetalleDeEnvio (id_DetalleEnvio, descripcion, Direccion, Distrito, fechaEntrega, horarioEntrega) VALUES
(1, 'Entrega a domicilio - Casa', 'Av. Arequipa 1234, Dpto 501', 'Miraflores', '2024-01-20 00:00:00', '2024-01-20 14:00:00'),
(2, 'Entrega oficina - Recepción', 'Jr. de la Unión 567, Piso 8', 'Cercado de Lima', '2024-01-21 00:00:00', '2024-01-21 10:30:00'),
(3, 'Entrega urgente - Casa', 'Av. Javier Prado 3210, Urb. San Borja', 'San Borja', '2024-01-22 00:00:00', '2024-01-22 16:45:00');

//

INSERT INTO OrdenCompra (IdPedido, FechaCreacion, total_parcial, total_final, descuentoTotal, Estado, DetalleDeEnvio_id_DetalleEnvio, Activo, cliente_idCliente) VALUES
(1, CURDATE(), 185.50, 175.50, 10.00, 'Completado', 1, 1, 1);

//

SELECT * FROM Producto;

//

INSERT INTO LineaOrdenCompra (idLineaOrdenCompra, cantidad, preciounitario, subtotal, Producto_ID_Producto, OrdenCompra_IdPedido, CarritoDecompras_Id, activo) VALUES
(1, 5, 2.50, 12.50, 1, 1, 1, 1),
(2, 3, 4.20, 12.60, 2, 1, 1, 1),
(3, 8, 1.80, 14.40, 3, 1, 1, 1),
(4, 10, 1.60, 16.00, 4, 1, 1, 1),
(5, 4, 2.00, 8.00, 5, 1, 1, 1);

//
INSERT INTO Empresa(idEmpresa, ruc, razonSocial, direccionFiscal, departamento, provincia, distrito, telefono, email, activo, codigoPostal, cliente_idCliente) 
VALUES 
(0,'20123456789', 'Comercial del Norte S.A.C.', 'Av. Industrial 123', 'Lima', 'Lima', 'Independencia', '015551234', 'ventas@comercialnorte.com', 1, '15301', 1);

INSERT INTO Empresa(idEmpresa, ruc, razonSocial, direccionFiscal, departamento, provincia, distrito, telefono, email, activo, codigoPostal, cliente_idCliente) 
VALUES 
(1,'10987654321', 'Distribuidora del Sur E.I.R.L.', 'Calle Las Garzas 456', 'Arequipa', 'Arequipa', 'Yanahuara', '054789123', 'contacto@distrisur.com', 0, '04013', 2);

INSERT INTO Empresa(idEmpresa, ruc, razonSocial, direccionFiscal, departamento, provincia, distrito, telefono, email, activo, codigoPostal, cliente_idCliente) 
VALUES 
(2,'20555666777', 'Bodegas Sol S.A.', 'Jr. El Comercio 789', 'La Libertad', 'Trujillo', 'Trujillo', '044123987', 'admin@bodegasol.com', 0, '13001', 3);

