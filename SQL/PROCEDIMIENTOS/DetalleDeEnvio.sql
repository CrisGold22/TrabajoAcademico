USE REDCOM;

-- =====================================================================
-- DETALLEDEENVIO
-- =====================================================================

DELIMITER // 

CREATE PROCEDURE insertarDetalleDeEnvio (
    IN p_id_DetalleEnvio INT,
    IN p_descripcion     VARCHAR(45),
    IN p_Direccion       VARCHAR(100),
    IN p_Distrito        VARCHAR(60),
    IN p_fechaEntrega    DATE,
    IN p_horarioEntrega  DATETIME, 
    OUT p_id INT
)
BEGIN
    INSERT INTO DetalleDeEnvio (
        id_DetalleEnvio, descripcion, Direccion, Distrito, fechaEntrega, horarioEntrega
    ) VALUES (
        p_id_DetalleEnvio, p_descripcion, p_Direccion, p_Distrito, p_fechaEntrega, p_horarioEntrega
    );
        
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarDetalleDeEnvio (
    IN p_id_DetalleEnvio INT,
    IN p_descripcion     VARCHAR(45),
    IN p_Direccion       VARCHAR(100),
    IN p_Distrito        VARCHAR(60),
    IN p_fechaEntrega    DATE,
    IN p_horarioEntrega  DATETIME
)
BEGIN
    UPDATE DetalleDeEnvio
       SET descripcion    = p_descripcion,
           Direccion      = p_Direccion,
           Distrito       = p_Distrito,
           fechaEntrega   = p_fechaEntrega,
           horarioEntrega = p_horarioEntrega
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