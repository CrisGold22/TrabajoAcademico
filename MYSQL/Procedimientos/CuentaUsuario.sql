    USE REDCOM;

    -- =====================================================================
    -- CUENTAOUSUARIO
    -- =====================================================================

    DELIMITER //

    CREATE PROCEDURE insertarCuentaUsuario (
        IN p_idCuentaUsuario             INT,
        IN p_userName                    VARCHAR(45),
        IN p_password                    VARCHAR(45),
        IN p_Administrador_idAdministrador INT,
        IN p_cliente_idCliente           INT
    )
    BEGIN
        INSERT INTO CuentaUsuario (
            idCuentaUsuario, userName, password, Administrador_idAdministrador, cliente_idCliente
        ) VALUES (
            p_idCuentaUsuario, p_userName, p_password, p_Administrador_idAdministrador, p_cliente_idCliente
        );
    END //

    CREATE PROCEDURE modificarCuentaUsuario (
        IN p_idCuentaUsuario             INT,
        IN p_userName                    VARCHAR(45),
        IN p_password                    VARCHAR(45),
        IN p_Administrador_idAdministrador INT,
        IN p_cliente_idCliente           INT
    )
    BEGIN
        UPDATE CuentaUsuario
        SET userName                     = p_userName,
            password                     = p_password,
            Administrador_idAdministrador = p_Administrador_idAdministrador,
            cliente_idCliente            = p_cliente_idCliente
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
