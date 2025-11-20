USE REDCOM;


DROP TRIGGER IF EXISTS after_insert_categoriaProducto;
DROP TRIGGER IF EXISTS eliminar_linea_orden_compra_after_producto_eliminar;

DELIMITER //

-- Trigger para INSERT
CREATE TRIGGER after_insert_categoriaProducto
AFTER INSERT ON CategoriaProducto
FOR EACH ROW
BEGIN
    IF NEW.idCategoriaPadre IS NOT NULL THEN
        
        IF NOT EXISTS (
            SELECT 1 FROM CategoriaProducto_has_CategoriaProducto 
            WHERE categoriaProducto_idCategoriaProducto = NEW.idCategoriaPadre 
            AND categoriaProducto_idCategoriaProducto1 = NEW.idCategoriaProducto
        ) THEN
            INSERT INTO CategoriaProducto_has_CategoriaProducto (
                categoriaProducto_idCategoriaProducto, 
                categoriaProducto_idCategoriaProducto1
            )
            VALUES (NEW.idCategoriaPadre, NEW.idCategoriaProducto);
        END IF;
    END IF;
END//


CREATE TRIGGER eliminar_linea_orden_compra_after_producto_eliminar
AFTER DELETE ON Producto
FOR EACH ROW
BEGIN
    DELETE FROM LineaOrdenCompra 
    WHERE producto_ID_Producto = OLD.id_Producto;
END;//