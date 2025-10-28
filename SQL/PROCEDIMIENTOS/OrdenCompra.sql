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
    IN p_Activo                   TINYINT, 
    IN p_Cliente                  INT,
    OUT p_id INT
)
BEGIN
    INSERT INTO OrdenCompra (
        IdPedido, FechaCreacion, total_parcial, total_final, descuentoTotal, Estado,
        DetalleDeEnvio_id_DetalleEnvio, Activo,cliente_idCliente
    ) VALUES (
        p_IdPedido, p_FechaCreacion, p_total_parcial, p_total_final, p_descuentoTotal, p_Estado,
        p_DetalleDeEnvio_id_DetalleEnvio, p_Activo,p_Cliente
    );
        
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarOrdenCompra (
    IN p_IdPedido                 INT,
    IN p_FechaCreacion            DATE,
    IN p_total_parcial            DOUBLE,
    IN p_total_final              DOUBLE,
    IN p_descuentoTotal           DOUBLE,
    IN p_Estado                   VARCHAR(45),
    IN p_DetalleDeEnvio_id_DetalleEnvio INT,
    IN p_Activo                   TINYINT,
    IN p_Cliente                  INT
)
BEGIN
    UPDATE OrdenCompra
       SET FechaCreacion = p_FechaCreacion,
           total_parcial = p_total_parcial,
           total_final   = p_total_final,
           descuentoTotal = p_descuentoTotal,
           Estado        = p_Estado,
           DetalleDeEnvio_id_DetalleEnvio = p_DetalleDeEnvio_id_DetalleEnvio,
           Activo        = p_Activo,
           cliente_idCliente = p_Cliente
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

CREATE PROCEDURE consultarPedidoPorFechas(
    IN p_Cliente INT,
    IN p_Filtro1 DATE,
    IN p_Filtro2 DATE
)
BEGIN
    SELECT * 
    FROM OrdenCompra
    WHERE cliente_idCliente = p_Cliente
        AND Activo = 1
        AND Fechacreacion BETWEEN p_Filtro1 AND p_Filtro2
    ORDER BY Fechacreacion DESC;
END //

CREATE PROCEDURE consultarOrdenCompraPorIdCliente(
    IN p_Cliente INT
)
BEGIN
    SELECT * 
    FROM OrdenCompra
    WHERE cliente_idCliente = p_Cliente
        AND Activo = 1
    ORDER BY Fechacreacion DESC;
END //


