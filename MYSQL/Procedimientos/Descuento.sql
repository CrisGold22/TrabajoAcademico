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
    IN p_Producto_ID_Producto INT
)
BEGIN
    INSERT INTO Descuento (
        idReglaPrecioVolumen, precioPorVolumen, cantidadMax, cantidadMin, Activo, Producto_ID_Producto
    ) VALUES (
        p_idReglaPrecioVolumen, p_precioPorVolumen, p_cantidadMax, p_cantidadMin, p_Activo, p_Producto_ID_Producto
    );
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
