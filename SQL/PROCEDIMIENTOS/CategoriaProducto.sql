USE REDCOM;

-- =====================================================================
-- CATEGORIAPRODUCTO
-- =====================================================================

DELIMITER //

CREATE PROCEDURE insertarCategoriaProducto (
    IN p_idCategoriaProducto INT,
    IN p_NombreCategoria     VARCHAR(45),
    IN p_Descripcion         VARCHAR(45),
    IN p_Catalogo_idCatalogo INT,
    IN p_Activo              TINYINT, 
    OUT p_id INT
)
BEGIN
    INSERT INTO CategoriaProducto (
        idCategoriaProducto, NombreCategoria, Descripcion, Catalogo_idCatalogo, Activo
    ) VALUES (
        p_idCategoriaProducto, p_NombreCategoria, p_Descripcion, p_Catalogo_idCatalogo, p_Activo
    );
	    
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarCategoriaProducto (
    IN p_idCategoriaProducto INT,
    IN p_NombreCategoria     VARCHAR(45),
    IN p_Descripcion         VARCHAR(45),
    IN p_Catalogo_idCatalogo INT,
    IN p_Activo              TINYINT
)
BEGIN
    UPDATE CategoriaProducto
       SET NombreCategoria     = p_NombreCategoria,
           Descripcion         = p_Descripcion,
           Catalogo_idCatalogo = p_Catalogo_idCatalogo,
           Activo              = p_Activo
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
