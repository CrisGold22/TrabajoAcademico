USE REDCOM;

-- =====================================================================
-- CARRITODECOMPRAS
-- =====================================================================


DROP PROCEDURE IF EXISTS insertarCarritoDeCompras;
DROP PROCEDURE IF EXISTS modificarCarritoDeCompras;
DROP PROCEDURE IF EXISTS eliminarCarritoDeCompras;
DROP PROCEDURE IF EXISTS buscarCarritoDeComprasPorId;
DROP PROCEDURE IF EXISTS listarCarritosDeCompras;
DROP PROCEDURE IF EXISTS buscarCarritoDeComprasPorId;
DROP PROCEDURE IF EXISTS obtenerCarritoPorIdCliente;


DELIMITER //

CREATE PROCEDURE insertarCarritoDeCompras (
    IN p_subtotal            DOUBLE,
    IN p_Estado              VARCHAR(45),
    IN p_descuento           DOUBLE,
    IN p_cliente_idCliente   INT, 
    IN p_monto_final         DOUBLE,
    IN p_activo              TINYINT,
    OUT p_id INT
)
BEGIN
    INSERT INTO CarritoDeCompras (
        subtotal,
        estado,
        descuento,
        montoFinal,
        cliente_idCliente,
        activo
    ) VALUES (
        p_subtotal,
        p_Estado,
        p_descuento,
        p_monto_final,
        p_cliente_idCliente,
        p_activo
    );

    SET p_id = LAST_INSERT_ID();
END //


CREATE PROCEDURE modificarCarritoDeCompras (
    IN p_Id_CarritoDeCompras INT,
    IN p_subtotal       DOUBLE,
    IN p_Estado              VARCHAR(45),
    IN p_descuento 			DOUBLE,
    IN p_cliente_idCliente   INT,
    IN p_monto_final         DOUBLE,
    IN p_activo              TINYINT    
)
BEGIN
    UPDATE CarritoDeCompras
       SET subtotal       	   = p_subtotal,
           estado              = p_Estado,
           descuento 		   = p_descuento,
           montoFinal		   = p_monto_final,
           cliente_idCliente   = p_cliente_idCliente,
           activo              = p_activo
     WHERE Id_CarritoDeCompras = p_Id_CarritoDeCompras;
END //

CREATE PROCEDURE eliminarCarritoDeCompras (IN p_Id_CarritoDeCompras INT)
BEGIN
    DELETE FROM CarritoDeCompras WHERE id_CarritoDeCompras = p_Id_CarritoDeCompras;
END //

CREATE PROCEDURE buscarCarritoDeComprasPorId (IN p_Id_CarritoDeCompras INT)
BEGIN
    SELECT * FROM CarritoDeCompras WHERE id_CarritoDeCompras = p_Id_CarritoDeCompras;
END //

CREATE PROCEDURE listarCarritosDeCompras ()
BEGIN
    SELECT * FROM CarritoDeCompras;
END //

CREATE PROCEDURE obtenerCarritoPorIdCliente (IN p_Id_Cliente INT)
BEGIN
    SELECT * FROM CarritoDeCompras WHERE cliente_idCliente = p_Id_Cliente and Estado='EN_PROCESO';
END //