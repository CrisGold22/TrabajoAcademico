USE REDCOM;

-- =====================================================================
-- DESCUENTO
-- =====================================================================

DROP PROCEDURE IF EXISTS insertarDescuento;
DROP PROCEDURE IF EXISTS eliminarDescuento;
DROP PROCEDURE IF EXISTS buscarDescuentoPorId;
DROP PROCEDURE IF EXISTS listarDescuentos;
DROP PROCEDURE IF EXISTS actualizarPrecioDescuentoProducto;
DROP PROCEDURE IF EXISTS modificarDescuento;

DELIMITER //

CREATE PROCEDURE insertarDescuento (
    IN p_precioPorVolumen     DOUBLE,
    IN p_cantidadMax          INT,
    IN p_cantidadMin          INT,
    IN p_Activo               TINYINT,
    IN p_Producto_ID_Producto INT, 
    OUT p_id INT
)
BEGIN
    INSERT INTO Descuento (
        precioPorVolumen, cantidadMax, cantidadMin, activo,producto_ID_Producto
    ) VALUES (
        p_precioPorVolumen, p_cantidadMax, p_cantidadMin, p_Activo, p_Producto_ID_Producto
    );
        
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarDescuento (
    IN p_idDescuento 		  INT,
    IN p_precioPorVolumen     DOUBLE,
    IN p_cantidadMax          INT,
    IN p_cantidadMin          INT,
    IN p_Activo               TINYINT,
    IN p_Producto_ID_Producto INT
)
BEGIN
    UPDATE Descuento
       SET precioPorVolumen     = p_precioPorVolumen,
           cantidadMax          = p_cantidadMax,
           cantidadMin          = p_cantidadMin,
           activo               = p_Activo,
           producto_ID_Producto = p_Producto_ID_Producto
     WHERE idDescuento = p_idDescuento;
END //

CREATE PROCEDURE eliminarDescuento (IN p_idDescuento INT)
BEGIN
    DELETE FROM Descuento WHERE idDescuento = p_idDescuento;
END //

CREATE PROCEDURE buscarDescuentoPorId (IN p_idDescuento INT)
BEGIN
    SELECT * FROM Descuento WHERE idDescuento = p_idDescuento;
END //

CREATE PROCEDURE listarDescuentos ()
BEGIN
    SELECT * FROM Descuento;
END //

CREATE PROCEDURE actualizarPrecioDescuentoProducto(IN p_idProducto INT,
IN p_nuevo_precio_desc DOUBLE,
IN p_cantidadMin	INT,
IN p_cantidadMax	INT)
BEGIN

    IF NOT EXISTS (SELECT 1 FROM Producto WHERE id_Producto = p_idProducto) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El producto no existe';
    END IF;

    IF EXISTS (SELECT 1 FROM Descuento WHERE producto_ID_Producto = p_idProducto) THEN
        UPDATE Descuento
        SET precioPorVolumen = p_nuevo_precio_desc,
			cantidadMin = p_cantidadMin,
            cantidadMax = p_cantidadMax
        WHERE producto_ID_Producto = p_idProducto;
    END IF;
END//