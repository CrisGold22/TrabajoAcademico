

DELIMITER //

CREATE PROCEDURE actualizarPrecioDescuentoProducto(
    IN p_idProducto INT,
    IN p_nuevo_precio_desc DOUBLE,
    IN p_cantidadMin INT,
    IN p_cantidadMax INT
)
BEGIN

    -- Validar si el producto existe
    IF NOT EXISTS (SELECT 1 FROM Producto WHERE id_Producto = p_idProducto) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Error: El producto no existe';
    END IF;

    -- Actualizar si existe descuento
    IF EXISTS (SELECT 1 FROM Descuento WHERE producto_ID_Producto = p_idProducto) THEN
        UPDATE Descuento
        SET precioPorVolumen = p_nuevo_precio_desc,
            cantidadMin = p_cantidadMin,
            cantidadMax = p_cantidadMax
        WHERE producto_ID_Producto = p_idProducto;
    END IF;

END//

DELIMITER ;


USE REDCOM;

DROP PROCEDURE IF EXISTS insertarComprobantePago;

DELIMITER //
CREATE PROCEDURE insertarComprobantePago (
    IN p_fechaEmision DATE,          -- 1
    IN p_RUC VARCHAR(11),            -- 2
    IN p_totalSinImpuestos DOUBLE,   -- 3
    IN p_Impuesto DOUBLE,            -- 4
    IN p_totalFinal DOUBLE,          -- 5
    IN p_metodoPago VARCHAR(45),     -- 6
    IN p_activo TINYINT,             -- 7
    IN p_OrdenCompra_IdPedido INT,   -- 8
    OUT p_id INT                     -- 9
)
BEGIN
    INSERT INTO ComprobantePago (
        fechaEmision, RUC, totalSinImpuestos, Impuesto, 
        totalFinal, metodoPago, activo, OrdenCompra_IdPedido
    ) VALUES (
        p_fechaEmision, p_RUC, p_totalSinImpuestos, p_Impuesto, 
        p_totalFinal, p_metodoPago, p_activo, p_OrdenCompra_IdPedido
    );
    SET p_id = LAST_INSERT_ID();
END //
DELIMITER ;

USE REDCOM;

-- =====================================================================
-- 1. Tabla ComprobantePago (Cabecera)
-- =====================================================================
CREATE TABLE IF NOT EXISTS ComprobantePago (
    idComprobante INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fechaEmision DATE NOT NULL,
    RUC VARCHAR(11) NOT NULL,    -- Usamos VARCHAR(11) para evitar errores con números grandes
    totalSinImpuestos DOUBLE NOT NULL,
    Impuesto DOUBLE NOT NULL,    -- Coincide con tu Java: getImpuestos()
    totalFinal DOUBLE NOT NULL,
    metodoPago VARCHAR(45),      -- "CRIPTO", "VIRTUAL", etc.
    activo TINYINT DEFAULT 1,
    OrdenCompra_IdPedido INT NOT NULL,
    
    -- Clave foránea hacia OrdenCompra
    CONSTRAINT fk_Comprobante_Orden 
        FOREIGN KEY (OrdenCompra_IdPedido) 
        REFERENCES OrdenCompra(idOrdenCompra)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- =====================================================================
-- 2. Tabla LineaComprobantePago (Detalle)
-- =====================================================================
CREATE TABLE IF NOT EXISTS LineaComprobantePago (
    idLineaComprobantePago INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    montoPagado DOUBLE NOT NULL,
    montoImpuesto DOUBLE NOT NULL,
    codigo INT,                   -- Algún código interno o referencia
    cantidad INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    activo TINYINT DEFAULT 1,
    ComprobantePago_idComprobante INT NOT NULL,
    
    -- Clave foránea hacia la cabecera (ComprobantePago)
    CONSTRAINT fk_Linea_Comprobante 
        FOREIGN KEY (ComprobantePago_idComprobante) 
        REFERENCES ComprobantePago(idComprobante)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

USE REDCOM;

DROP PROCEDURE IF EXISTS insertarLineaComprobantePago;

DELIMITER //
CREATE PROCEDURE insertarLineaComprobantePago (
    IN p_montoPagado DOUBLE,         -- 1
    IN p_montoImpuesto DOUBLE,       -- 2
    IN p_codigo INT,                 -- 3
    IN p_cantidad INT,               -- 4
    IN p_subtotal DOUBLE,            -- 5
    IN p_activo TINYINT,             -- 6
    IN p_ComprobantePago_id INT,     -- 7
    OUT p_id INT                     -- 8
)
BEGIN
    INSERT INTO LineaComprobantePago (
        montoPagado, montoImpuesto, codigo, cantidad, 
        subtotal, activo, ComprobantePago_idComprobante
    ) VALUES (
        p_montoPagado, p_montoImpuesto, p_codigo, p_cantidad, 
        p_subtotal, p_activo, p_ComprobantePago_id
    );
    SET p_id = LAST_INSERT_ID();
END //
DELIMITER ;

ALTER TABLE ComprobantePago MODIFY COLUMN RUC VARCHAR(11) NOT NULL;

