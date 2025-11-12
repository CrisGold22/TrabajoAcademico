USE REDCOM;

-- =====================================================================
-- COMPROBANTEPAGO
-- =====================================================================

DELIMITER //

CREATE PROCEDURE insertarComprobantePago (
    IN p_idComprobante        INT,
    IN p_fechaEmision         DATE,
    IN p_RUC                  INT,
    IN p_totalSinImpuestos    DOUBLE,
    IN p_Impuesto             DOUBLE,
    IN p_totalFinal           DOUBLE,
    IN p_metodoPago           VARCHAR(45),
    IN p_subTotal             DOUBLE,
    IN p_activo               TINYINT,
    IN p_OrdenCompra_IdPedido INT, 
    OUT p_id INT
)
BEGIN
    INSERT INTO ComprobantePago (
        idComprobante, fechaEmision, RUC, totalSinImpuestos, Impuesto,
        totalFinal, metodoPago, subTotal, activo, OrdenCompra_IdPedido
    ) VALUES (
        p_idComprobante, p_fechaEmision, p_RUC, p_totalSinImpuestos, p_Impuesto,
        p_totalFinal, p_metodoPago, p_subTotal, p_activo, p_OrdenCompra_IdPedido
    );
        
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarComprobantePago (
    IN p_idComprobante        INT,
    IN p_fechaEmision         DATE,
    IN p_RUC                  INT,
    IN p_totalSinImpuestos    DOUBLE,
    IN p_Impuesto             DOUBLE,
    IN p_totalFinal           DOUBLE,
    IN p_metodoPago           VARCHAR(45),
    IN p_subTotal             DOUBLE,
    IN p_activo               TINYINT,
    IN p_OrdenCompra_IdPedido INT
)
BEGIN
    UPDATE ComprobantePago
       SET fechaEmision         = p_fechaEmision,
           RUC                  = p_RUC,
           totalSinImpuestos    = p_totalSinImpuestos,
           Impuesto             = p_Impuesto,
           totalFinal           = p_totalFinal,
           metodoPago           = p_metodoPago,
           subTotal             = p_subTotal,
           activo               = p_activo,
           OrdenCompra_IdPedido = p_OrdenCompra_IdPedido
     WHERE idComprobante        = p_idComprobante;
END //

CREATE PROCEDURE eliminarComprobantePago (IN p_idComprobante INT)
BEGIN
    DELETE FROM ComprobantePago WHERE idComprobante = p_idComprobante;
END //

CREATE PROCEDURE buscarComprobantePagoPorId (IN p_idComprobante INT)
BEGIN
    SELECT * FROM ComprobantePago WHERE idComprobante = p_idComprobante;
END //

CREATE PROCEDURE listarComprobantesPago ()
BEGIN
    SELECT * FROM ComprobantePago;
END //
