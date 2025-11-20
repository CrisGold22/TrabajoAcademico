USE REDCOM;

-- =====================================================================
-- LINEAORDENCOMPRA
-- =====================================================================


DROP PROCEDURE IF EXISTS insertarLineaOrdenCompra;
DROP PROCEDURE IF EXISTS modificarLineaOrdenCompra;
DROP PROCEDURE IF EXISTS eliminarLineaOrdenCompra;
DROP PROCEDURE IF EXISTS buscarLineaOrdenCompraPorId;
DROP PROCEDURE IF EXISTS listarLineasOrdenCompra;
DROP PROCEDURE IF EXISTS ListarLineasOrdenCompraPorIdOrdenCompra;




DELIMITER //

CREATE PROCEDURE insertarLineaOrdenCompra (
    IN p_cantidad           INT,
    IN p_precio     		DOUBLE,
    IN p_subtotal           DOUBLE,
    IN p_Producto_ID_Producto INT,
    IN p_OrdenCompra_IdPedido INT,
    IN p_activo             TINYINT, 
    OUT p_id INT
)
BEGIN
    INSERT INTO LineaOrdenCompra (
        cantidad, precio, subtotal,producto_ID_Producto, ordenCompra_IdPedido, activo
    ) VALUES (
        p_cantidad, p_precio, p_subtotal,p_Producto_ID_Producto, p_OrdenCompra_IdPedido, p_activo
    );
        
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarLineaOrdenCompra (
    IN p_idLineaOrdenCompra INT,
    IN p_cantidad           INT,
    IN p_precio     		DOUBLE,
    IN p_subtotal           DOUBLE,
    IN p_Producto_ID_Producto INT,
    IN p_OrdenCompra_IdPedido INT,
    IN p_activo             TINYINT
)
BEGIN
    UPDATE LineaOrdenCompra
       SET cantidad             = p_cantidad,
           precio		        = p_precio,
           subtotal             = p_subtotal,
           producto_ID_Producto = p_Producto_ID_Producto,
           ordenCompra_IdPedido = p_OrdenCompra_IdPedido,
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

DELIMITER //

CREATE PROCEDURE ListarLineasOrdenCompraPorIdOrdenCompra (
    IN p_IdPedido INT
)
BEGIN
    SELECT * 
    FROM LineaOrdenCompra 
    WHERE ordenCompra_IdPedido = p_IdPedido;
END //

