USE REDCOM;

-- =====================================================================
-- ADMINISTRADOR
-- =====================================================================

DELIMITER //

CREATE PROCEDURE insertarAdministrador (
    IN p_idAdministrador INT,
    IN p_cargo           VARCHAR(45),
    IN p_rango           VARCHAR(45),
    IN p_dni             VARCHAR(45),
    IN p_nombre          VARCHAR(45),
    IN p_apellidoPaterno VARCHAR(45),
    IN p_apellidoMaterno VARCHAR(45),
    IN p_genero          VARCHAR(45),
    IN p_fechaNacimiento DATE,
    IN p_telefono        INT,
    IN p_Sueldo          DOUBLE,
    IN p_Activo          TINYINT, 
    OUT p_id INT
)
BEGIN
    INSERT INTO Administrador (
        idAdministrador, cargo, rango, dni, nombre, apellidoPaterno, apellidoMaterno,
        genero, fechaNacimiento, telefono, Sueldo, Activo
    ) VALUES (
        p_idAdministrador, p_cargo, p_rango, p_dni, p_nombre, p_apellidoPaterno, p_apellidoMaterno,
        p_genero, p_fechaNacimiento, p_telefono, p_Sueldo, p_Activo
    );
	SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarAdministrador (
    IN p_idAdministrador INT,
    IN p_cargo           VARCHAR(45),
    IN p_rango           VARCHAR(45),
    IN p_dni             VARCHAR(45),
    IN p_nombre          VARCHAR(45),
    IN p_apellidoPaterno VARCHAR(45),
    IN p_apellidoMaterno VARCHAR(45),
    IN p_genero          VARCHAR(45),
    IN p_fechaNacimiento DATE,
    IN p_telefono        INT,
    IN p_Sueldo          DOUBLE,
    IN p_Activo          TINYINT
)
BEGIN
    UPDATE Administrador
       SET cargo            = p_cargo,
           rango            = p_rango,
           dni              = p_dni,
           nombre           = p_nombre,
           apellidoPaterno  = p_apellidoPaterno,
           apellidoMaterno  = p_apellidoMaterno,
           genero           = p_genero,
           fechaNacimiento  = p_fechaNacimiento,
           telefono         = p_telefono,
           Sueldo           = p_Sueldo,
           Activo           = p_Activo
     WHERE idAdministrador  = p_idAdministrador;
END //

CREATE PROCEDURE eliminarAdministrador (IN p_idAdministrador INT)
BEGIN
    DELETE FROM Administrador WHERE idAdministrador = p_idAdministrador;
END //

CREATE PROCEDURE buscarAdministradorPorId (IN p_idAdministrador INT)
BEGIN
    SELECT * FROM Administrador WHERE idAdministrador = p_idAdministrador;
END //

CREATE PROCEDURE listarAdministradores ()
BEGIN
    SELECT * FROM Administrador;
END //
