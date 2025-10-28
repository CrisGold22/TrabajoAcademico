USE REDCOM;

-- =====================================================================
-- ORDENCOMPRA
-- =====================================================================

DELIMITER //

CREATE PROCEDURE insertarOrdenCompra (
    IN p_IdPedido                 INT,
    IN p_FechaCreacion            DATE,
    IN p_total_parcial            DOUBLE,
    IN p_total_final              DOUBLE,
    IN p_descuentoTotal           DOUBLE,
    IN p_Estado                   VARCHAR(45),
    IN p_DetalleDeEnvio_id_DetalleEnvio INT,
    IN p_Activo                   TINYINT
)
BEGIN
    INSERT INTO OrdenCompra (
        IdPedido, FechaCreacion, total_parcial, total_final, descuentoTotal, Estado,
        DetalleDeEnvio_id_DetalleEnvio, Activo
    ) VALUES (
        p_IdPedido, p_FechaCreacion, p_total_parcial, p_total_final, p_descuentoTotal, p_Estado,
        p_DetalleDeEnvio_id_DetalleEnvio, p_Activo
    );
END //

CREATE PROCEDURE modificarOrdenCompra (
    IN p_IdPedido                 INT,
    IN p_FechaCreacion            DATE,
    IN p_total_parcial            DOUBLE,
    IN p_total_final              DOUBLE,
    IN p_descuentoTotal           DOUBLE,
    IN p_Estado                   VARCHAR(45),
    IN p_DetalleDeEnvio_id_DetalleEnvio INT,
    IN p_Activo                   TINYINT
)
BEGIN
    UPDATE OrdenCompra
       SET FechaCreacion = p_FechaCreacion,
           total_parcial = p_total_parcial,
           total_final   = p_total_final,
           descuentoTotal = p_descuentoTotal,
           Estado        = p_Estado,
           DetalleDeEnvio_id_DetalleEnvio = p_DetalleDeEnvio_id_DetalleEnvio,
           Activo        = p_Activo
     WHERE IdPedido = p_IdPedido;
END //

CREATE PROCEDURE eliminarOrdenCompra (IN p_IdPedido INT)
BEGIN
    DELETE FROM OrdenCompra WHERE IdPedido = p_IdPedido;
END //

CREATE PROCEDURE buscarOrdenCompraPorId (IN p_IdPedido INT)
BEGIN
    SELECT * FROM OrdenCompra WHERE IdPedido = p_IdPedido;
END //

CREATE PROCEDURE listarOrdenesCompra ()
BEGIN
    SELECT * FROM OrdenCompra;
END //
