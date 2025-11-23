USE REDCOM;

-- =====================================================================
-- CUENTA USUARIO
-- =====================================================================

DROP PROCEDURE IF EXISTS insertarCuentaUsuario;
DROP PROCEDURE IF EXISTS modificarCuentaUsuario;
DROP PROCEDURE IF EXISTS eliminarCuentaUsuario;
DROP PROCEDURE IF EXISTS listarCuentaUsuarios;
DROP PROCEDURE IF EXISTS buscarCuentaUsuarioPorId;
DROP PROCEDURE IF EXISTS buscarCuentaUsuarioPorCorreo;
DROP PROCEDURE IF EXISTS loginUsuario;
DROP PROCEDURE IF EXISTS actualizarTokenRecuperacion;
DROP PROCEDURE IF EXISTS actualizarPasswordYLimpiarToken;

DELIMITER //

-- ============================================================
-- INSERTAR CUENTA USUARIO
-- ============================================================
CREATE PROCEDURE insertarCuentaUsuario (
    IN  p_userName     VARCHAR(50),
    IN  p_password     VARCHAR(100),   -- aquí ya deberías pasar el HASH
    IN  p_activo       TINYINT,
    IN  p_correo       VARCHAR(150),
    OUT p_id           INT
)
BEGIN
    INSERT INTO CuentaUsuario (
        userName, password, activo, correo
    ) VALUES (
        p_userName, p_password, p_activo, p_correo
    );

    SET p_id = LAST_INSERT_ID();
END //

-- ============================================================
-- MODIFICAR CUENTA USUARIO
-- ============================================================
CREATE PROCEDURE modificarCuentaUsuario (
    IN p_idCuentaUsuario INT,
    IN p_userName        VARCHAR(50),
    IN p_password        VARCHAR(100),   -- hash
    IN p_activo          TINYINT,
    IN p_correo          VARCHAR(150)
)
BEGIN
    UPDATE CuentaUsuario
       SET userName = p_userName,
           password = p_password,
           activo   = p_activo,
           correo   = p_correo
     WHERE idCuentaUsuario = p_idCuentaUsuario;
END //

-- ============================================================
-- ELIMINAR CUENTA USUARIO
-- ============================================================
CREATE PROCEDURE eliminarCuentaUsuario (IN p_idCuentaUsuario INT)
BEGIN
    DELETE FROM CuentaUsuario
     WHERE idCuentaUsuario = p_idCuentaUsuario;
END //

-- ============================================================
-- BUSCAR POR ID
-- ============================================================
CREATE PROCEDURE buscarCuentaUsuarioPorId (IN p_idCuentaUsuario INT)
BEGIN
    SELECT *
      FROM CuentaUsuario
     WHERE idCuentaUsuario = p_idCuentaUsuario;
END //

-- ============================================================
-- LISTAR TODOS
-- ============================================================
CREATE PROCEDURE listarCuentaUsuarios ()
BEGIN
    SELECT *
      FROM CuentaUsuario;
END //

-- ============================================================
-- BUSCAR POR CORREO (para recuperación de contraseña)
-- ============================================================
CREATE PROCEDURE buscarCuentaUsuarioPorCorreo (IN p_correo VARCHAR(150))
BEGIN
    SELECT *
      FROM CuentaUsuario
     WHERE correo = p_correo;
END //

-- ============================================================
-- LOGIN (si sigues validando en BD)
-- OJO: si usas HASH, normalmente validas en Java.
-- ============================================================
CREATE PROCEDURE loginUsuario(
    IN  p_correo VARCHAR(150),
    IN  p_password VARCHAR(100),  -- aquí pasarías el hash si sigues con este SP
    OUT p_valido   BOOLEAN
)
BEGIN
    DECLARE v_count INT DEFAULT 0;

    SELECT COUNT(*) INTO v_count
      FROM CuentaUsuario
     WHERE correo = p_correo
       AND password = p_password
       AND activo   = 1;

    IF v_count > 0 THEN
        SET p_valido = TRUE;
    ELSE
        SET p_valido = FALSE;
    END IF;
END //

-- ============================================================
-- ACTUALIZAR TOKEN DE RECUPERACIÓN
-- usado por CuentaUsuarioDAO.actualizarTokenRecuperacion(...)
-- ============================================================
CREATE PROCEDURE actualizarTokenRecuperacion(
    IN p_idCuentaUsuario    INT,
    IN p_reset_token        VARCHAR(100),
    IN p_reset_token_expira TIMESTAMP
)
BEGIN
    UPDATE CuentaUsuario
       SET reset_token        = p_reset_token,
           reset_token_expira = p_reset_token_expira
     WHERE idCuentaUsuario    = p_idCuentaUsuario;
END //

-- ============================================================
-- ACTUALIZAR PASSWORD Y LIMPIAR TOKEN
-- usado por resetPasswordConToken(...)
-- ============================================================
CREATE PROCEDURE actualizarPasswordYLimpiarToken(
    IN p_idCuentaUsuario INT,
    IN p_password        VARCHAR(100)   -- hash nuevo
)
BEGIN
    UPDATE CuentaUsuario
       SET password          = p_password,
           reset_token       = NULL,
           reset_token_expira = NULL
     WHERE idCuentaUsuario   = p_idCuentaUsuario;
END //

DELIMITER ;
