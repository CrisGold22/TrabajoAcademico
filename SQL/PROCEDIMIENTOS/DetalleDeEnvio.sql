USE REDCOM;

-- =====================================================================
-- DETALLEDEENVIO
-- =====================================================================

DROP PROCEDURE IF EXISTS insertarDetalleDeEnvio;
DROP PROCEDURE IF EXISTS eliminarDetalleDeEnvio;
DROP PROCEDURE IF EXISTS modificarDetalleDeEnvio;
DROP PROCEDURE IF EXISTS buscarDetalleDeEnvioPorId;
DROP PROCEDURE IF EXISTS listarDetallesDeEnvio;


DELIMITER // 

CREATE PROCEDURE insertarDetalleDeEnvio (
    IN p_descripcion     VARCHAR(45),
    IN p_Direccion       VARCHAR(100),
    IN p_Distrito        VARCHAR(60),
    IN p_fechaEntrega    DATETIME,
    IN p_horarioEntrega  DATETIME, 
    IN p_ordenCompra_IdPedido INT,
    IN p_activo			TINYINT,
    OUT p_id INT
)
BEGIN
    INSERT INTO DetalleDeEnvio (descripcion, direccion, distrito, fechaEntrega, horarioEntrega,ordenCompra_IdPedido,activo) 
    VALUES (p_descripcion, p_Direccion, p_Distrito, p_fechaEntrega, p_horarioEntrega,p_ordenCompra_IdPedido,p_activo
    );
        
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarDetalleDeEnvio (
    IN p_id_DetalleEnvio INT,
    IN p_descripcion     VARCHAR(45),
    IN p_Direccion       VARCHAR(100),
    IN p_Distrito        VARCHAR(60),
    IN p_fechaEntrega    DATETIME,
    IN p_horarioEntrega  DATETIME, 
    IN p_ordenCompra_IdPedido INT,
    IN p_activo			TINYINT
)
BEGIN
    UPDATE DetalleDeEnvio
       SET descripcion    = p_descripcion,
           direccion      = p_Direccion,
           distrito       = p_Distrito,
           fechaEntrega   = p_fechaEntrega,
           horarioEntrega = p_horarioEntrega,
           ordenCompra_IdPedido = p_ordenCompra_IdPedido,
           activo         = p_activo
     WHERE id_DetalleEnvio = p_id_DetalleEnvio;
END //

CREATE PROCEDURE eliminarDetalleDeEnvio (IN p_id_DetalleEnvio INT)
BEGIN
    DELETE FROM DetalleDeEnvio WHERE id_DetalleEnvio = p_id_DetalleEnvio;
END //

CREATE PROCEDURE buscarDetalleDeEnvioPorId (IN p_id_DetalleEnvio INT)
BEGIN
    SELECT * FROM DetalleDeEnvio WHERE id_DetalleEnvio = p_id_DetalleEnvio;
END //

CREATE PROCEDURE listarDetallesDeEnvio ()
BEGIN
    SELECT * FROM DetalleDeEnvio;
END //