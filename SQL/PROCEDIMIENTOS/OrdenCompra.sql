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

select * from OrdenCompra
//
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

CREATE PROCEDURE procesarOrdenCompra(IN p_IdPedido INT)
BEGIN
    DECLARE v_count INT DEFAULT 0;
    DECLARE v_activo TINYINT;
    DECLARE v_estado VARCHAR(45);
    
    SELECT COUNT(*) INTO v_count FROM OrdenCompra WHERE IdPedido = p_IdPedido;
    IF v_count = 0 THEN
        SELECT 'Error: Orden no existe' AS Resultado;
    ELSE
        SELECT Activo, Estado INTO v_activo, v_estado FROM OrdenCompra WHERE IdPedido = p_IdPedido;
        
        IF v_activo != 1 THEN
            SELECT 'Error: Orden no activa' AS Resultado;
        ELSE
            IF v_estado != 'PAGADO' THEN
                SELECT 'Error: Orden no pagada' AS Resultado;
            ELSE
                SELECT COUNT(*) INTO v_count
                FROM LineaOrdenCompra loc
                JOIN Producto p ON loc.Producto_ID_Producto = p.ID_Producto
                WHERE loc.OrdenCompra_Idpedido = p_IdPedido 
                AND loc.activo = 1
                AND p.StockDisponible < loc.cantidad;
                
                IF v_count > 0 THEN
                    SELECT 'Error: Stock insuficiente' AS Resultado;
                ELSE
                    START TRANSACTION;
                    UPDATE Producto p
                    JOIN LineaOrdenCompra loc ON p.ID_Producto = loc.Producto_ID_Producto
                    SET p.StockDisponible = p.StockDisponible - loc.cantidad
                    WHERE loc.OrdenCompra_Idpedido = p_IdPedido AND loc.activo = 1;
                    
                    UPDATE OrdenCompra SET Estado = 'PROCESADO' WHERE IdPedido = p_IdPedido;
                    COMMIT;
                    
                    SELECT 'Orden procesada exitosamente' AS Resultado;
                END IF;
            END IF;
        END IF;
    END IF;
END//
DELIMITER //

CREATE PROCEDURE DesactivarOrdenCompra(
    IN p_IdPedido INT,
    OUT p_Mensaje VARCHAR(200)
)
BEGIN
    DECLARE v_Estado VARCHAR(45);
    DECLARE v_Activo TINYINT;
    DECLARE v_LineasAfectadas INT DEFAULT 0;
    DECLARE v_StockActualizado INT DEFAULT 0;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SET p_Mensaje = 'Error: Ocurrió un problema al procesar la desactivación de la orden';
    END;

    -- Verificar si la orden existe y está activa
    SELECT Estado, Activo INTO v_Estado, v_Activo 
    FROM OrdenCompra 
    WHERE IdPedido = p_IdPedido;

    IF v_Activo IS NULL THEN
        SET p_Mensaje = 'Error: La orden de compra no existe';
    ELSEIF v_Activo = 0 THEN
        SET p_Mensaje = 'Error: La orden de compra ya está inactiva';
    ELSE
        START TRANSACTION;

        -- Desactivar las líneas de orden de compra y actualizar stock
        UPDATE LineaOrdenCompra loc
        INNER JOIN Producto p ON loc.Producto_ID_Producto = p.ID_Producto
        SET 
            loc.activo = 0,
            p.StockDisponible = p.StockDisponible + loc.cantidad
        WHERE loc.OrdenCompra_IdPedido = p_IdPedido 
        AND loc.activo = 1;

        -- Contar líneas afectadas
        SET v_LineasAfectadas = ROW_COUNT();

        -- Desactivar la orden de compra principal
        UPDATE OrdenCompra 
        SET Activo = 0 
        WHERE IdPedido = p_IdPedido 
        AND Activo = 1;

        -- Contar stock actualizado (mismo que líneas afectadas)
        SET v_StockActualizado = v_LineasAfectadas;

        COMMIT;

        SET p_Mensaje = CONCAT('Éxito: Orden desactivada. ', v_LineasAfectadas, ' líneas afectadas. ',v_StockActualizado, ' productos con stock actualizado.');
    END IF;
END//

