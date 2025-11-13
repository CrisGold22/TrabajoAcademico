USE REDCOM;

-- =====================================================================
-- DESCUENTO
-- =====================================================================

DELIMITER //

CREATE PROCEDURE insertarDescuento (
    IN p_idReglaPrecioVolumen INT,
    IN p_precioPorVolumen     DOUBLE,
    IN p_cantidadMax          INT,
    IN p_cantidadMin          INT,
    IN p_Activo               TINYINT,
    IN p_Producto_ID_Producto INT, 
    OUT p_id INT
)
BEGIN
    INSERT INTO Descuento (
        idReglaPrecioVolumen, precioPorVolumen, cantidadMax, cantidadMin, Activo, Producto_ID_Producto
    ) VALUES (
        p_idReglaPrecioVolumen, p_precioPorVolumen, p_cantidadMax, p_cantidadMin, p_Activo, p_Producto_ID_Producto
    );
        
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarDescuento (
    IN p_idReglaPrecioVolumen INT,
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
           Activo               = p_Activo,
           Producto_ID_Producto = p_Producto_ID_Producto
     WHERE idReglaPrecioVolumen = p_idReglaPrecioVolumen;
END //

CREATE PROCEDURE eliminarDescuento (IN p_idReglaPrecioVolumen INT)
BEGIN
    DELETE FROM Descuento WHERE idReglaPrecioVolumen = p_idReglaPrecioVolumen;
END //

CREATE PROCEDURE buscarDescuentoPorId (IN p_idReglaPrecioVolumen INT)
BEGIN
    SELECT * FROM Descuento WHERE idReglaPrecioVolumen = p_idReglaPrecioVolumen;
END //

CREATE PROCEDURE listarDescuentos ()
BEGIN
    SELECT * FROM Descuento;
END //

CREATE PROCEDURE actualizarPrecioDescuentoProducto(IN p_idProducto INT,IN p_nuevo_precio_desc DOUBLE)

BEGIN

    IF NOT EXISTS (SELECT 1 FROM Producto WHERE ID_Producto = p_idProducto) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El producto no existe';
    END IF;

    IF EXISTS (SELECT 1 FROM Descuento WHERE Producto_ID_Producto = p_idProducto) THEN
        UPDATE Descuento
        SET precioPorVolumen = p_nuevo_precio_desc
        WHERE Producto_ID_Producto = p_idProducto;

    END IF;
 

END//