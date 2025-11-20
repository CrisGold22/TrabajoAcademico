USE REDCOM;

-- =====================================================================
-- ORDENCOMPRA
-- =====================================================================


DROP PROCEDURE IF EXISTS insertarOrdenCompra;
DROP PROCEDURE IF EXISTS modificarOrdenCompra;
DROP PROCEDURE IF EXISTS eliminarOrdenCompra;
DROP PROCEDURE IF EXISTS buscarOrdenCompraPorId;
DROP PROCEDURE IF EXISTS listarOrdenesCompra;
DROP PROCEDURE IF EXISTS consultarOrdenCompraPorIdCliente;
DROP PROCEDURE IF EXISTS consultarPedidoPorFechas;
DROP PROCEDURE IF EXISTS procesarOrdenCompra;
DROP PROCEDURE IF EXISTS DesactivarOrdenCompra;

DELIMITER //

CREATE PROCEDURE insertarOrdenCompra (
    IN p_FechaCreacion            DATE,
    IN p_total_parcial            DOUBLE,
    IN p_total_final              DOUBLE,
    IN p_descuentoTotal           DOUBLE,
    IN p_Estado                   VARCHAR(45),
    IN p_carrito_idCarrito 		  INT,
    IN p_Activo                   TINYINT, 
    IN p_Cliente                  INT,
    IN p_Empresa				  INT,
    OUT p_id INT
)
BEGIN
    INSERT INTO OrdenCompra (
        fechaCreacion, total_parcial, total_final, descuentoTotal, estado,
        carrito_idCarrito, activo,cliente_idCliente,empresa_idEmpresa
    ) VALUES (
        p_FechaCreacion, p_total_parcial, p_total_final, p_descuentoTotal, p_Estado,
        p_carrito_idCarrito, p_Activo,p_Cliente,p_Empresa
    );
        
    SET p_id = LAST_INSERT_ID();
END //


//
CREATE PROCEDURE modificarOrdenCompra (
    IN p_idOrdenCompra            INT,
    IN p_FechaCreacion            DATE,
    IN p_total_parcial            DOUBLE,
    IN p_total_final              DOUBLE,
    IN p_descuentoTotal           DOUBLE,
    IN p_Estado                   VARCHAR(45),
    IN p_carrito_idCarrito 		  INT,
    IN p_Activo                   TINYINT, 
    IN p_Cliente                  INT,
    IN p_Empresa				  INT
)
BEGIN
    UPDATE OrdenCompra
       SET fechaCreacion = p_FechaCreacion,
           total_parcial = p_total_parcial,
           total_final   = p_total_final,
           descuentoTotal = p_descuentoTotal,
           estado        = p_Estado,
           carrito_idCarrito = p_carrito_idCarrito,
           activo        = p_Activo,
           cliente_idCliente = p_Cliente
     WHERE idOrdenCompra = p_idOrdenCompra;
END //

CREATE PROCEDURE eliminarOrdenCompra (IN p_idOrdenCompra INT)
BEGIN
    DELETE FROM OrdenCompra WHERE idOrdenCompra = p_idOrdenCompra;
END //

CREATE PROCEDURE buscarOrdenCompraPorId (IN p_idOrdenCompra INT)
BEGIN
    SELECT * FROM OrdenCompra WHERE idOrdenCompra = p_idOrdenCompra;
END //

CREATE PROCEDURE listarOrdenesCompra ()
BEGIN
    SELECT * FROM OrdenCompra;
END //

CREATE PROCEDURE reporteOrdenCompra(
	IN p_id INT
)
DELIMITER //

DELIMITER //
CREATE PROCEDURE listarOrdenesCompraPorCuenta(IN p_cuenta VARCHAR(50))
BEGIN
    SELECT O.*
    FROM OrdenCompra AS O
    INNER JOIN Cliente AS C ON C.idCliente = O.cliente_idCliente
    INNER JOIN CuentaUsuario AS CU ON CU.idCuentaUsuario = C.idCuentaUsuario
    WHERE CU.userName = p_cuenta;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE reporteOrdenCompra(
	IN p_id INT
)
BEGIN 
	SELECT 
		O.idOrdenCompra, C.dni, C.nombre, C.apellidoPaterno, O.total_final, O.total_parcial, O.descuentoTotal
	FROM 
		OrdenCompra AS O
	INNER JOIN Cliente AS C
		ON O.cliente_idCliente = C.idCliente
	WHERE O.idOrdenCompra = p_id;
END //
DELIMITER //;


DELIMITER //
CREATE PROCEDURE reporteDetalleOrdenCompra(
	IN p_id INT
)
BEGIN
	SELECT 
		L.idLineaOrdenCompra, P.nombre, P.precioRegular, L.cantidad, L.subTotal
    FROM 
		LineaOrdenCompra AS L
    INNER JOIN 
		OrdenCompra AS O
        ON O.idOrdenCompra = L.ordenCompra_IdPedido
	INNER JOIN 
		Producto AS P
        ON L.producto_ID_Producto = P.id_Producto
	WHERE O.idOrdenCompra = p_id;
END //
DELIMITER //;

		

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
    
    SELECT COUNT(*) INTO v_count FROM OrdenCompra WHERE idOrdenCompra = p_IdPedido;
    IF v_count = 0 THEN
        SELECT 'Error: Orden no existe' AS Resultado;
    ELSE
        SELECT activo, estado INTO v_activo, v_estado FROM OrdenCompra WHERE idOrdenCompra = p_IdPedido;
        
        IF v_activo != 1 THEN
            SELECT 'Error: Orden no activa' AS Resultado;
        ELSE
            IF v_estado != 'PAGADO' THEN
                SELECT 'Error: Orden no pagada' AS Resultado;
            ELSE
                SELECT COUNT(*) INTO v_count
                FROM LineaOrdenCompra loc
                JOIN Producto p ON loc.Producto_ID_Producto = p.id_Producto
                WHERE loc.ordenCompra_Idpedido = p_IdPedido 
                AND loc.activo = 1
                AND p.stockDisponible < loc.cantidad;
                
                IF v_count > 0 THEN
                    SELECT 'Error: Stock insuficiente' AS Resultado;
                ELSE
                    START TRANSACTION;
                    UPDATE Producto p
                    JOIN LineaOrdenCompra loc ON p.id_Producto = loc.producto_ID_Producto
                    SET p.stockDisponible = p.stockDisponible - loc.cantidad
                    WHERE loc.OrdenCompra_Idpedido = p_IdPedido AND loc.activo = 1;
                    
                    UPDATE OrdenCompra SET estado = 'PROCESADO' WHERE idOrdenCompra = p_IdPedido;
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
    DECLARE v_ErrorCode CHAR(5) DEFAULT '00000';
    DECLARE v_ErrorMsg TEXT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1 
            v_ErrorCode = RETURNED_SQLSTATE, 
            v_ErrorMsg = MESSAGE_TEXT;
        
        ROLLBACK;
        
        -- Mensaje específico según el tipo de error
        IF v_ErrorCode = '23000' THEN
            SET p_Mensaje = 'Error: Violación de integridad referencial';
        ELSE
            SET p_Mensaje = CONCAT('Error: ', v_ErrorMsg, ' (Código: ', v_ErrorCode, ')');
        END IF;
    END;

    -- Verificar si la orden existe y está activa
    SELECT estado, activo INTO v_Estado, v_Activo 
    FROM OrdenCompra 
    WHERE idOrdenCompra = p_IdPedido;

    IF v_Activo IS NULL THEN
        SET p_Mensaje = 'Error: La orden de compra no existe';
    ELSEIF v_Activo = 0 THEN
        SET p_Mensaje = 'Error: La orden de compra ya está inactiva';
    ELSE
        START TRANSACTION;

        -- Desactivar las líneas de orden de compra y actualizar stock
        UPDATE LineaOrdenCompra loc
        INNER JOIN Producto p ON loc.producto_ID_Producto = p.id_Producto
        SET 
            loc.activo = 0,
            p.stockDisponible = p.stockDisponible + loc.cantidad
        WHERE loc.ordenCompra_IdPedido = p_IdPedido 
        AND loc.activo = 1;

        -- Contar líneas afectadas
        SET v_LineasAfectadas = ROW_COUNT();

        -- Verificar si se encontraron líneas para actualizar
        IF v_LineasAfectadas = 0 THEN
            ROLLBACK;
            SET p_Mensaje = 'Advertencia: No se encontraron líneas activas para desactivar';
        ELSE
            -- Desactivar la orden de compra principal
            UPDATE OrdenCompra 
            SET activo = 0 , estado = 'CANCELADO'
            WHERE idOrdenCompra = p_IdPedido 
            AND activo = 1;

            -- Verificar si se actualizó la orden
            IF ROW_COUNT() = 0 THEN
                ROLLBACK;
                SET p_Mensaje = 'Error: No se pudo desactivar la orden principal';
            ELSE
                COMMIT;
                SET v_StockActualizado = v_LineasAfectadas;
                SET p_Mensaje = CONCAT('Éxito: Orden desactivada. ', v_LineasAfectadas, ' líneas afectadas. ', v_StockActualizado, ' productos con stock actualizado.');
            END IF;
        END IF;
    END IF;
END//

