USE REDCOM;

-- =====================================================================
-- CATEGORIAPRODUCTO_HAS_CATEGORIAPRODUCTO
-- =====================================================================

DELIMITER //

CREATE PROCEDURE insertarRelacionCategoriaProducto (
    IN p_idCategoriaProducto  INT,
    IN p_idCategoriaProducto1 INT
)
BEGIN
    INSERT INTO CategoriaProducto_has_CategoriaProducto (
        CategoriaProducto_idCategoriaProducto, CategoriaProducto_idCategoriaProducto1
    ) VALUES (
        p_idCategoriaProducto, p_idCategoriaProducto1
    );
END //

CREATE PROCEDURE eliminarRelacionCategoriaProducto (
    IN p_idCategoriaProducto  INT,
    IN p_idCategoriaProducto1 INT
)
BEGIN
    DELETE FROM CategoriaProducto_has_CategoriaProducto
     WHERE CategoriaProducto_idCategoriaProducto  = p_idCategoriaProducto
       AND CategoriaProducto_idCategoriaProducto1 = p_idCategoriaProducto1;
END //

CREATE PROCEDURE buscarRelacionCategoriaProducto (
    IN p_idCategoriaProducto  INT,
    IN p_idCategoriaProducto1 INT
)
BEGIN
    SELECT *
      FROM CategoriaProducto_has_CategoriaProducto
     WHERE CategoriaProducto_idCategoriaProducto  = p_idCategoriaProducto
       AND CategoriaProducto_idCategoriaProducto1 = p_idCategoriaProducto1;
END //

CREATE PROCEDURE listarRelacionesCategoriaProducto ()
BEGIN
    SELECT * FROM CategoriaProducto_has_CategoriaProducto;
END //

DELIMITER ;