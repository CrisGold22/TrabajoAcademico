USE REDCOM;

-- =====================================================================
-- CATEGORIAPRODUCTO
-- =====================================================================


DROP PROCEDURE IF EXISTS insertarCategoriaProducto;
DROP PROCEDURE IF EXISTS eliminarCategoriaProducto;
DROP PROCEDURE IF EXISTS modificarCategoriaProducto;
DROP PROCEDURE IF EXISTS listarCategoriaProductos;
DROP PROCEDURE IF EXISTS buscarCategoriaProductoPorId;
DROP PROCEDURE IF EXISTS obtenerMarcasPorCategoria;
DROP PROCEDURE IF EXISTS obtenerCategoriaPorNombre;

DELIMITER //

CREATE PROCEDURE insertarCategoriaProducto (
    IN p_NombreCategoria     VARCHAR(45),
    IN p_Descripcion         VARCHAR(45),
    IN p_idCategoriaPadre    INT,
    IN p_Activo              TINYINT, 
    OUT p_id INT
)
BEGIN
    INSERT INTO CategoriaProducto (
        nombreCategoria, descripcion, idCategoriaPadre, activo
    ) VALUES (
        p_NombreCategoria, p_Descripcion, p_idCategoriaPadre, p_Activo
    );
	    
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarCategoriaProducto (
    IN p_idCategoriaProducto INT,
    IN p_NombreCategoria     VARCHAR(45),
    IN p_Descripcion         VARCHAR(45),
    IN p_idCategoriaPadre    INT,
    IN p_Activo              TINYINT
)
BEGIN
    UPDATE CategoriaProducto
       SET nombreCategoria     = p_NombreCategoria,
           descripcion         = p_Descripcion,
           idCategoriaPadre    = p_idCategoriaPadre,
           activo              = p_Activo
     WHERE idCategoriaProducto = p_idCategoriaProducto;
END //

CREATE PROCEDURE eliminarCategoriaProducto (IN p_idCategoriaProducto INT)
BEGIN
    DELETE FROM CategoriaProducto WHERE idCategoriaProducto = p_idCategoriaProducto;
END //

CREATE PROCEDURE buscarCategoriaProductoPorId (IN p_idCategoriaProducto INT)
BEGIN
    SELECT * FROM CategoriaProducto WHERE idCategoriaProducto = p_idCategoriaProducto;
END //

CREATE PROCEDURE listarCategoriaProductos ()
BEGIN
    SELECT * FROM CategoriaProducto;
END //

CREATE PROCEDURE obtenerMarcasPorCategoria(
    IN p_idCategoriaProducto INT
)
BEGIN
    SELECT DISTINCT p.marca
    FROM Producto p
    INNER JOIN CategoriaProducto c ON c.idCategoriaProducto = p.categoriaProducto_idCategoriaProducto
    WHERE p.categoriaProducto_idCategoriaProducto = p_idCategoriaProducto
    AND p.activo = 1 AND c.activo = 1
    ORDER BY p.marca;
END //

CREATE PROCEDURE obtenerCategoriaPorNombre (IN p_categoria VARCHAR(45))
BEGIN
	SELECT * FROM CategoriaProducto
    WHERE nombreCategoria = p_categoria;
END//