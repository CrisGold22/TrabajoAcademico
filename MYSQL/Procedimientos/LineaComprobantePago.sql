USE REDCOM;

-- =====================================================================
-- LINEACOMPROBANTEPAGO
-- =====================================================================

DELIMITER //

CREATE PROCEDURE insertarLineaComprobantePago (
    IN p_idLineaComprobantePago      INT,
    IN p_montoPagado                 DOUBLE,
    IN p_montoImpuesto               DOUBLE,
    IN p_activo                      TINYINT,
    IN p_ComprobantePago_idComprobante INT,
    IN p_codigo                      INT,
    IN p_cantidad                    INT,
    IN p_subtotal                    DOUBLE
)
BEGIN
    INSERT INTO LineaComprobantePago (
        idLineaComprobantePago, montoPagado, montoImpuesto, activo,
        ComprobantePago_idComprobante, codigo, cantidad, subtotal
    ) VALUES (
        p_idLineaComprobantePago, p_montoPagado, p_montoImpuesto, p_activo,
        p_ComprobantePago_idComprobante, p_codigo, p_cantidad, p_subtotal
    );
END //

CREATE PROCEDURE modificarLineaComprobantePago (
    IN p_idLineaComprobantePago      INT,
    IN p_montoPagado                 DOUBLE,
    IN p_montoImpuesto               DOUBLE,    
    IN p_activo                      TINYINT,
    IN p_ComprobantePago_idComprobante INT,
    IN p_codigo                      INT,
    IN p_cantidad                    INT,
    IN p_subtotal                    DOUBLE
)
BEGIN
    UPDATE LineaComprobantePago
       SET montoPagado                 = p_montoPagado,
           montoImpuesto               = p_montoImpuesto,
           activo                      = p_activo,
           ComprobantePago_idComprobante = p_ComprobantePago_idComprobante,
           codigo                      = p_codigo,
           cantidad                    = p_cantidad,
           subtotal                    = p_subtotal
     WHERE idLineaComprobantePago      = p_idLineaComprobantePago;
END //

CREATE PROCEDURE eliminarLineaComprobantePago (IN p_idLineaComprobantePago INT)
BEGIN
    DELETE FROM LineaComprobantePago WHERE idLineaComprobantePago = p_idLineaComprobantePago;
END //

CREATE PROCEDURE buscarLineaComprobantePagoPorId (IN p_idLineaComprobantePago INT)
BEGIN
    SELECT * FROM LineaComprobantePago WHERE idLineaComprobantePago = p_idLineaComprobantePago;
END //

CREATE PROCEDURE listarLineasComprobantePago ()
BEGIN
    SELECT * FROM LineaComprobantePago;
END //

-- Filtro: Lineas de comprobante por idComprobante
CREATE PROCEDURE listarLineasComprobantePagoPorIdComprobante (
    IN p_idComprobante INT
)
BEGIN
    SELECT *
      FROM LineaComprobantePago
     WHERE ComprobantePago_idComprobante = p_idComprobante;
END //