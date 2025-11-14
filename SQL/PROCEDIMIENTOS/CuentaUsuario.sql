USE REDCOM;

-- =====================================================================
-- CUENTA USUARIO
-- =====================================================================

DROP PROCEDURE IF EXISTS insertarCuentaUsuario;
DROP PROCEDURE IF EXISTS modificarCuentaUsuario;
DROP PROCEDURE IF EXISTS eliminarCuentaUsuario;
DROP PROCEDURE IF EXISTS listarCuentaUsuarios;
DROP PROCEDURE IF EXISTS buscarCuentaUsuarioPorId;
DROP PROCEDURE IF EXISTS loginUsuario;


DELIMITER //

CREATE PROCEDURE insertarCuentaUsuario (
    IN p_userName                    VARCHAR(50),
    IN p_password                    VARCHAR(50),
    IN p_activo 					 TINYINT,
    OUT p_id 						 INT
)
BEGIN
    INSERT INTO CuentaUsuario (
        userName, password,activo) VALUES (
        p_userName, p_password,p_activo);  
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarCuentaUsuario (
    IN p_idCuentaUsuario             INT,
    IN p_userName                    VARCHAR(45),
    IN p_password                    VARCHAR(45),
    IN p_activo 					 TINYINT)
BEGIN
    UPDATE CuentaUsuario
       SET userName                     = p_userName,
           password                     = p_password,
           activo                       = p_activo
     WHERE idCuentaUsuario = p_idCuentaUsuario;
END //

CREATE PROCEDURE eliminarCuentaUsuario (IN p_idCuentaUsuario INT)
BEGIN
    DELETE FROM CuentaUsuario WHERE idCuentaUsuario = p_idCuentaUsuario;
END //

CREATE PROCEDURE buscarCuentaUsuarioPorId (IN p_idCuentaUsuario INT)
BEGIN
    SELECT * FROM CuentaUsuario WHERE idCuentaUsuario = p_idCuentaUsuario;
END //

CREATE PROCEDURE listarCuentaUsuarios ()
BEGIN
    SELECT * FROM CuentaUsuario;
END //

CREATE PROCEDURE loginUsuario(
    IN p_username VARCHAR(50),
    IN p_password VARCHAR(50),
    OUT p_valido BOOLEAN
)
BEGIN
    DECLARE v_count INT DEFAULT 0;

    SELECT COUNT(*) INTO v_count
    FROM CuentaUsuario
    WHERE userName = p_username
      AND password = p_password;

    IF v_count > 0 THEN
        SET p_valido = TRUE;
    ELSE
        SET p_valido = FALSE;
    END IF;
END //