USE REDCOM;

-- =====================================================================
-- LINEACARRITO
-- =====================================================================

DELIMITER // 

CREATE PROCEDURE insertarLineaCarrito (
    IN p_idLineaCarrito             INT,
    IN p_cantidad                   INT,
    IN p_precioVolumen              DOUBLE,
    IN p_subTotal                   DOUBLE,
    IN p_Producto_ID_Producto1      INT,
    IN p_CarritoDeCompras_Productos INT,
    IN p_activo                     TINYINT,
    IN p_Producto_ID_Producto       INT, 
    OUT p_id INT
)
BEGIN
    INSERT INTO LineaCarrito (
        idLineaCarrito, cantidad, precioVolumen, subTotal, Producto_ID_Producto1,
        CarritoDeCompras_Productos, activo, Producto_ID_Producto
    ) VALUES (
        p_idLineaCarrito, p_cantidad, p_precioVolumen, p_subTotal, p_Producto_ID_Producto1,
        p_CarritoDeCompras_Productos, p_activo, p_Producto_ID_Producto
    );
        
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarLineaCarrito (
    IN p_idLineaCarrito             INT,
    IN p_cantidad                   INT,
    IN p_precioVolumen              DOUBLE,
    IN p_subTotal                   DOUBLE,
    IN p_Producto_ID_Producto1      INT,
    IN p_CarritoDeCompras_Productos INT,
    IN p_activo                     TINYINT,
    IN p_Producto_ID_Producto       INT
)
BEGIN
    UPDATE LineaCarrito
       SET cantidad                   = p_cantidad,
           precioVolumen              = p_precioVolumen,
           subTotal                   = p_subTotal,
           Producto_ID_Producto1      = p_Producto_ID_Producto1,
           CarritoDeCompras_Productos = p_CarritoDeCompras_Productos,
           activo                     = p_activo,
           Producto_ID_Producto       = p_Producto_ID_Producto
     WHERE idLineaCarrito = p_idLineaCarrito;
END //

CREATE PROCEDURE eliminarLineaCarrito (IN p_idLineaCarrito INT)
BEGIN
    DELETE FROM LineaCarrito WHERE idLineaCarrito = p_idLineaCarrito;
END //

CREATE PROCEDURE buscarLineaCarritoPorId (IN p_idLineaCarrito INT)
BEGIN
    SELECT * FROM LineaCarrito WHERE idLineaCarrito = p_idLineaCarrito;
END //

CREATE PROCEDURE listarLineasCarrito ()
BEGIN
    SELECT * FROM LineaCarrito;
END //

-- Filtro: Lineas de carrito por ID de carrito
CREATE PROCEDURE listarLineasCarritoPorIdCarrito (
    IN p_Id_CarritoDeCompras INT
)
BEGIN
    SELECT *
      FROM LineaCarrito
     WHERE CarritoDeCompras_Productos = p_Id_CarritoDeCompras;
END //
