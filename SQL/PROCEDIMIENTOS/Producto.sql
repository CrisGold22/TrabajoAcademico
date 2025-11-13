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
    IN p_CategoriaProducto_idCategoriaProducto INT, 
    IN p_MarcaProducto VARCHAR(45),
    OUT p_id INT
)
BEGIN
    INSERT INTO Producto (
        ID_Producto, Nombre, SKU, Descripcion, precioRegular, precioAlMayor,
        UnidadDeMedida, StockDisponible, StockMinimo, StockMaximo, Activo,
        CategoriaProducto_idCategoriaProducto,Marca
    ) VALUES (
        p_ID_Producto, p_Nombre, p_SKU, p_Descripcion, p_precioRegular, p_precioAlMayor,
        p_UnidadDeMedida, p_StockDisponible, p_StockMinimo, p_StockMaximo, p_Activo,
        p_CategoriaProducto_idCategoriaProducto,p_MarcaProducto
    );
        
    SET p_id = LAST_INSERT_ID();
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
    IN p_CategoriaProducto_idCategoriaProducto INT,
    IN p_MarcaProducto VARCHAR(45)
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
           CategoriaProducto_idCategoriaProducto = p_CategoriaProducto_idCategoriaProducto,
            Marca         = p_MarcaProducto 
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

CREATE PROCEDURE filtrarProductoPorPrecio(
	IN p_idCategoriaProducto INT,IN p_Filtro1 DOUBLE,
	IN p_Filtro2 DOUBLE)
BEGIN
    SELECT 
        p.*
    FROM Producto p
    INNER JOIN CategoriaProducto c ON p.CategoriaProducto_idCategoriaProducto = c.idCategoriaProducto
    WHERE p.CategoriaProducto_idCategoriaProducto = p_idCategoriaProducto
        AND p.Activo = 1
        AND c.Activo = 1
        AND (
            (p.precioAlMayor BETWEEN p_Filtro1 AND p_Filtro2)
        )
    ORDER BY p.precioAlMayor;
END//

CREATE PROCEDURE filtrarProductoPorMarca(
    IN p_idCategoriaProducto INT,
    IN p_MarcaProducto VARCHAR(45)
)
BEGIN
    SELECT 
        p.ID_Producto,
        p.Nombre,
        p.SKU,
        p.Descripcion,
        p.precioRegular,
        p.precioAlMayor,
        p.UnidadDeMedida,
        p.StockDisponible,
        p.StockMinimo,
        p.StockMaximo,
        p.Activo,
        p.Marca,
        p.CategoriaProducto_idCategoriaProducto
    FROM Producto p
    INNER JOIN CategoriaProducto c ON p.CategoriaProducto_idCategoriaProducto = c.idCategoriaProducto
    WHERE p.CategoriaProducto_idCategoriaProducto = p_idCategoriaProducto
        AND p.Marca = p_MarcaProducto
        AND p.Activo = 1
        AND c.Activo = 1
    ORDER BY p.Nombre;
END //

CREATE PROCEDURE filtrarProductoPorDescuento(IN p_idCategoriaProducto INT,IN p_NombreCategoria VARCHAR(45))
BEGIN
	IF UPPER(p_NombreCategoria) = 'CON DESCUENTO' THEN
        SELECT 
            p.*
        FROM Producto p
        INNER JOIN Descuento d ON p.ID_Producto = d.Producto_ID_Producto
        WHERE p.CategoriaProducto_idCategoriaProducto = p_idCategoriaProducto
            AND p.Activo = 1
            AND d.Activo = 1
        ORDER BY p.Nombre;
	END IF;
END //

CREATE PROCEDURE verificarStockSuficientePorID(
    IN p_id_producto INT,
    IN p_cantidad_solicitada INT,
    OUT p_stock_suficiente TINYINT 
)
BEGIN
    DECLARE stock_actual INT DEFAULT 0;
    DECLARE producto_activo TINYINT DEFAULT 0;
    
    SET p_stock_suficiente = 0;
    
    SELECT 
        StockDisponible, 
        Activo
    INTO 
        stock_actual, 
        producto_activo
    FROM Producto 
    WHERE ID_Producto = p_id_producto;
    
    IF producto_activo = 1 AND stock_actual >= p_cantidad_solicitada THEN
        SET p_stock_suficiente = 1;
    END IF;
    
END //


CREATE PROCEDURE verificarStockSuficientePorSKU(
    IN p_sku_producto VARCHAR(45),
    IN p_cantidad_solicitada INT,
    OUT p_stock_suficiente TINYINT
)
BEGIN
    DECLARE stock_actual INT DEFAULT 0;
    DECLARE producto_activo TINYINT DEFAULT 0;
    
    SET p_stock_suficiente = 0;
    
    SELECT 
        StockDisponible, 
        Activo
    INTO 
        stock_actual, 
        producto_activo
    FROM Producto 
    WHERE SKU = p_sku_producto;
    
    IF producto_activo = 1 AND stock_actual >= p_cantidad_solicitada THEN
        SET p_stock_suficiente = 1;
    END IF;
    
END //
 