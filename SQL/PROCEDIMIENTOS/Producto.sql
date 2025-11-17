USE REDCOM;

-- =====================================================================
-- PRODUCTO
-- =====================================================================

DROP PROCEDURE IF EXISTS insertarProducto;
DROP PROCEDURE IF EXISTS modificarProducto;
DROP PROCEDURE IF EXISTS eliminarProducto;
DROP PROCEDURE IF EXISTS buscarProductoPorId;
DROP PROCEDURE IF EXISTS buscarProductoPorSKU;
DROP PROCEDURE IF EXISTS listarProductos;
DROP PROCEDURE IF EXISTS filtrarProductoPorDescuento;
DROP PROCEDURE IF EXISTS filtrarProductoPorMarca;
DROP PROCEDURE IF EXISTS filtrarProductoPorPrecioAlMayor;
DROP PROCEDURE IF EXISTS filtrarProductoPorPrecioRegular;
DROP PROCEDURE IF EXISTS verificarStockSuficientePorID;
DROP PROCEDURE IF EXISTS verificarStockSuficientePorSKU;
DROP PROCEDURE IF EXISTS obtenerProductosPorCategoria;

DELIMITER //

CREATE PROCEDURE insertarProducto (
    IN p_Nombre                                VARCHAR(45),
    IN p_SKU                                   VARCHAR(45),
    IN p_Descripcion                           VARCHAR(45),
    IN p_precioRegular                         DOUBLE,
    IN p_precioAlMayor                         DOUBLE,
    IN p_UnidadDeMedida                        VARCHAR(45),
    IN p_StockDisponible                       INT,
    IN p_StockMaximo                           INT,
    IN p_Activo                                TINYINT,
    IN p_CategoriaProducto_idCategoriaProducto INT, 
    IN p_MarcaProducto VARCHAR(45),
    OUT p_id INT
)
BEGIN
    INSERT INTO Producto (
        nombre, sku, descripcion, precioRegular, precioAlMayor,
        unidadDeMedida, stockDisponible, stockMaximo, activo,
        categoriaProducto_idCategoriaProducto,marca
    ) VALUES (
        p_Nombre, p_SKU, p_Descripcion, p_precioRegular, p_precioAlMayor,
        p_UnidadDeMedida, p_StockDisponible, p_StockMaximo, p_Activo,
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
    IN p_StockMaximo                           INT,
    IN p_Activo                                TINYINT,
    IN p_CategoriaProducto_idCategoriaProducto INT,
    IN p_MarcaProducto VARCHAR(45)
)
BEGIN
    UPDATE Producto
       SET nombre        = p_Nombre,
           sku           = p_SKU,
           descripcion   = p_Descripcion,
           precioRegular = p_precioRegular,
           precioAlMayor = p_precioAlMayor,
           unidadDeMedida = p_UnidadDeMedida,
           stockDisponible = p_StockDisponible,
           stockMaximo   = p_StockMaximo,
           activo        = p_Activo,
           categoriaProducto_idCategoriaProducto = p_CategoriaProducto_idCategoriaProducto,
           marca         = p_MarcaProducto 
     WHERE id_Producto = p_ID_Producto;
END //

CREATE PROCEDURE eliminarProducto (IN p_ID_Producto INT)
BEGIN
    DELETE FROM Producto WHERE id_Producto = p_ID_Producto;
END //

CREATE PROCEDURE buscarProductoPorId (IN p_ID_Producto INT)
BEGIN
    SELECT * FROM Producto WHERE id_Producto = p_ID_Producto;
END //

CREATE PROCEDURE listarProductos ()
BEGIN
    SELECT * FROM Producto;
END //

CREATE PROCEDURE buscarProductoPorSKU (IN p_SKU VARCHAR(45))
BEGIN
    SELECT * FROM Producto WHERE sku = p_SKU;
END //

CREATE PROCEDURE filtrarProductoPorPrecioAlMayor(
	IN p_idCategoriaProducto INT,IN p_Filtro1 DOUBLE,
	IN p_Filtro2 DOUBLE)
BEGIN
    SELECT 
        p.*
    FROM Producto p
    INNER JOIN CategoriaProducto c ON p.categoriaProducto_idCategoriaProducto = c.idCategoriaProducto
    WHERE p.categoriaProducto_idCategoriaProducto = p_idCategoriaProducto
        AND p.activo = 1
        AND c.activo = 1
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
        p.*
    FROM Producto p
    INNER JOIN CategoriaProducto c ON p.categoriaProducto_idCategoriaProducto = c.idCategoriaProducto
    WHERE p.categoriaProducto_idCategoriaProducto = p_idCategoriaProducto
        AND p.marca = p_MarcaProducto
        AND p.activo = 1
        AND c.activo = 1
    ORDER BY p.nombre;
END //

CREATE PROCEDURE filtrarProductoPorDescuento(IN p_idCategoriaProducto INT,IN p_NombreCategoria VARCHAR(45))
BEGIN
	IF UPPER(p_NombreCategoria) = 'CON DESCUENTO' THEN
        SELECT 
            p.*
        FROM Producto p
        INNER JOIN Descuento d ON p.id_Producto = d.producto_ID_Producto
        WHERE p.categoriaProducto_idCategoriaProducto = p_idCategoriaProducto
            AND p.activo = 1
            AND d.activo = 1
        ORDER BY p.nombre;
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
        stockDisponible, 
        activo
    INTO 
        stock_actual, 
        producto_activo
    FROM Producto 
    WHERE id_Producto = p_id_producto;
    
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
 
CREATE PROCEDURE filtrarProductoPorPrecioRegular(
	IN p_idCategoriaProducto INT,IN p_Filtro1 DOUBLE,
	IN p_Filtro2 DOUBLE)
BEGIN
    SELECT 
        p.*
    FROM Producto p
    INNER JOIN CategoriaProducto c ON p.categoriaProducto_idCategoriaProducto = c.idCategoriaProducto
    WHERE p.categoriaProducto_idCategoriaProducto = p_idCategoriaProducto
        AND p.activo = 1
        AND c.activo = 1
        AND (
            (p.precioRegular BETWEEN p_Filtro1 AND p_Filtro2)
        )
    ORDER BY p.precioRegular;
END// 

CREATE PROCEDURE obtenerProductosPorCategoria(IN p_idCategoriaProducto INT)
BEGIN 
	SELECT p.*
    FROM Producto p
    INNER JOIN CategoriaProducto c ON c.idCategoriaProducto = p.categoriaProducto_idCategoriaProducto
    WHERE categoriaProducto_idCategoriaProducto = p_idCategoriaProducto
    AND p.activo = 1 AND c.activo = 1
    ORDER BY p.nombre;

END//