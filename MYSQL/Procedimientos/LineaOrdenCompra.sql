USE REDCOM;

-- =====================================================================
-- LINEAORDENCOMPRA
-- =====================================================================

DELIMITER //

CREATE PROCEDURE insertarLineaOrdenCompra (
    IN p_idLineaOrdenCompra INT,
    IN p_cantidad           INT,
    IN p_precioUnitario     DOUBLE,
    IN p_subtotal           DOUBLE,
    IN p_Producto_ID_Producto INT,
    IN p_OrdenCompra_IdPedido INT,
    IN p_CarritoDeCompras_Id  INT,
    IN p_activo             TINYINT
)
BEGIN
    INSERT INTO LineaOrdenCompra (
        idLineaOrdenCompra, cantidad, precioUnitario, subtotal,
        Producto_ID_Producto, OrdenCompra_IdPedido, CarritoDeCompras_Id, activo
    ) VALUES (
        p_idLineaOrdenCompra, p_cantidad, p_precioUnitario, p_subtotal,
        p_Producto_ID_Producto, p_OrdenCompra_IdPedido, p_CarritoDeCompras_Id, p_activo
    );
END //

CREATE PROCEDURE modificarLineaOrdenCompra (
    IN p_idLineaOrdenCompra INT,
    IN p_cantidad           INT,
    IN p_precioUnitario     DOUBLE,
    IN p_subtotal           DOUBLE,
    IN p_Producto_ID_Producto INT,
    IN p_OrdenCompra_IdPedido INT,
    IN p_CarritoDeCompras_Id  INT,
    IN p_activo             TINYINT
)
BEGIN
    UPDATE LineaOrdenCompra
       SET cantidad             = p_cantidad,
           precioUnitario       = p_precioUnitario,
           subtotal             = p_subtotal,
           Producto_ID_Producto = p_Producto_ID_Producto,
           OrdenCompra_IdPedido = p_OrdenCompra_IdPedido,
           CarritoDeCompras_Id  = p_CarritoDeCompras_Id,
           activo               = p_activo
     WHERE idLineaOrdenCompra   = p_idLineaOrdenCompra;
END //

CREATE PROCEDURE eliminarLineaOrdenCompra (IN p_idLineaOrdenCompra INT)
BEGIN
    DELETE FROM LineaOrdenCompra WHERE idLineaOrdenCompra = p_idLineaOrdenCompra;
END //

CREATE PROCEDURE buscarLineaOrdenCompraPorId (IN p_idLineaOrdenCompra INT)
BEGIN
    SELECT * FROM LineaOrdenCompra WHERE idLineaOrdenCompra = p_idLineaOrdenCompra;
END //

CREATE PROCEDURE listarLineasOrdenCompra ()
BEGIN
    SELECT * FROM LineaOrdenCompra;
END //

-- Filtro: Lineas de orden por IdPedido
CREATE PROCEDURE listarLineasOrdenCompraPorIdOrden (
    IN p_IdPedido INT
)
BEGIN
    SELECT * FROM LineaOrdenCompra WHERE OrdenCompra_IdPedido = p_IdPedido;
END //
