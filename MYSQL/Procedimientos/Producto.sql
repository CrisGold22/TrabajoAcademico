USE REDCOM;

-- =====================================================================
-- PRODUCTO
-- =====================================================================

DELIMITER //

CREATE PROCEDURE insertarProducto (
    IN p_ID_Producto                           INT,
    IN p_Nombre                                VARCHAR(45),
    IN p_SKU                                   VARCHAR(45),
    IN p_Descripcion                           VARCHAR(45),
    IN p_precioRegular                         DOUBLE,
    IN p_precioAlMayor                         DOUBLE,
    IN p_UnidadDeMedida                        VARCHAR(45),
    IN p_StockDisponible                       INT,
    IN p_StockMinimo                           INT,
    IN p_StockMaximo                           INT,
    IN p_Activo                                TINYINT,
    IN p_CategoriaProducto_idCategoriaProducto INT
)
BEGIN
    INSERT INTO Producto (
        ID_Producto, Nombre, SKU, Descripcion, precioRegular, precioAlMayor,
        UnidadDeMedida, StockDisponible, StockMinimo, StockMaximo, Activo,
        CategoriaProducto_idCategoriaProducto
    ) VALUES (
        p_ID_Producto, p_Nombre, p_SKU, p_Descripcion, p_precioRegular, p_precioAlMayor,
        p_UnidadDeMedida, p_StockDisponible, p_StockMinimo, p_StockMaximo, p_Activo,
        p_CategoriaProducto_idCategoriaProducto
    );
END //

CREATE PROCEDURE modificarProducto (
    IN p_ID_Producto                           INT,
    IN p_Nombre                                VARCHAR(45),
    IN p_SKU                                   VARCHAR(45),
    IN p_Descripcion                           VARCHAR(45),
    IN p_precioRegular                         DOUBLE,
    IN p_precioAlMayor                         DOUBLE,
    IN p_UnidadDeMedida                        VARCHAR(45),
    IN p_StockDisponible                       INT,
    IN p_StockMinimo                           INT,
    IN p_StockMaximo                           INT,
    IN p_Activo                                TINYINT,
    IN p_CategoriaProducto_idCategoriaProducto INT
)
BEGIN
    UPDATE Producto
       SET Nombre        = p_Nombre,
           SKU           = p_SKU,
           Descripcion   = p_Descripcion,
           precioRegular = p_precioRegular,
           precioAlMayor = p_precioAlMayor,
           UnidadDeMedida = p_UnidadDeMedida,
           StockDisponible = p_StockDisponible,
           StockMinimo   = p_StockMinimo,
           StockMaximo   = p_StockMaximo,
           Activo        = p_Activo,
           CategoriaProducto_idCategoriaProducto = p_CategoriaProducto_idCategoriaProducto
     WHERE ID_Producto = p_ID_Producto;
END //

CREATE PROCEDURE eliminarProducto (IN p_ID_Producto INT)
BEGIN
    DELETE FROM Producto WHERE ID_Producto = p_ID_Producto;
END //

CREATE PROCEDURE buscarProductoPorId (IN p_ID_Producto INT)
BEGIN
    SELECT * FROM Producto WHERE ID_Producto = p_ID_Producto;
END //

CREATE PROCEDURE listarProductos ()
BEGIN
    SELECT * FROM Producto;
END //

CREATE PROCEDURE buscarProductoPorSKU (IN p_SKU VARCHAR(45))
BEGIN
    SELECT * FROM Producto WHERE SKU = p_SKU;
END //
