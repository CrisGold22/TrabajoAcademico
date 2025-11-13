USE REDCOM;

-- =====================================================================
-- Empresa
-- =====================================================================

DELIMITER //

CREATE PROCEDURE insertarEmpresa (
    IN p_idEmpresa       INT,
    IN p_ruc             VARCHAR(11),
    IN p_razonsocial     VARCHAR(200),
    IN p_direccionFiscal VARCHAR(255),
    IN p_departamento    VARCHAR(100),
    IN p_provincia       VARCHAR(100),
    IN p_distrito        VARCHAR(100),
    IN p_telefono        VARCHAR(20) ,
    IN p_email           VARCHAR(100),
    IN p_codigoPostal    VARCHAR(10),
    IN p_Activo          TINYINT, 
    IN p_idCliente       INT,
    OUT p_id INT
)
BEGIN
    INSERT INTO Empresa (
        idEmpresa, ruc, razonSocial, direccionFiscal, departamento, provincia, distrito,
        telefono, email, activo, codigoPostal, cliente_idCliente
    ) VALUES (
        p_idEmpresa, p_ruc, p_razonsocial, p_direccionFiscal, p_departamento, p_provincia, p_distrito,
        p_telefono, p_email, p_Activo, p_codigoPostal, p_idCliente
    );
	SET p_id = LAST_INSERT_ID();
END //

DELIMITER //

CREATE PROCEDURE validarEmpresa(IN p_id_empresa INT)
BEGIN
    UPDATE Empresa
    SET activo = 1
    WHERE idEmpresa = p_id_empresa AND activo = 0;
END //

DELIMITER //

CREATE PROCEDURE modificarEmpresa (
    IN p_idEmpresa       INT,
    IN p_ruc             VARCHAR(11),
    IN p_razonsocial     VARCHAR(200),
    IN p_direccionFiscal VARCHAR(255),
    IN p_departamento    VARCHAR(100),
    IN p_provincia       VARCHAR(100),
    IN p_distrito        VARCHAR(100),
    IN p_telefono        VARCHAR(20) ,
    IN p_email           VARCHAR(100),
    IN p_codigoPostal    VARCHAR(10),
    IN p_Activo          TINYINT,
    IN p_idCliente       INT
)
BEGIN
    UPDATE Empresa
       SET ruc              = p_ruc,
           razonSocial      = p_razonsocial,
           direccionFiscal  = p_razonsocial,
           departamento     = p_departamento,
           provincia        = p_provincia,
           distrito         = p_distrito,
           telefono         = p_telefono,
           email            = p_email,
           codigoPostal     = p_codigoPostal,
           activo           = p_Activo,
           cliente_idCliente = p_idCliente
	WHERE idEmpresa = p_idEmpresa;
END //


CREATE PROCEDURE eliminarEmpresa (IN p_idEmpresa INT)
BEGIN
    DELETE FROM Empresa WHERE idEmpresa = p_idEmpresa;
END //
DELIMITER //
CREATE PROCEDURE buscarEmpresaPorId (IN p_idEmpresa INT)
BEGIN
    SELECT * FROM Empresa WHERE idEmpresa = p_idEmpresa;
END //

DELIMITER //

CREATE PROCEDURE listarEmpresas ()
BEGIN
    SELECT * FROM Empresa;
END //
DELIMITER //
CREATE PROCEDURE listarEmpresasActivos ()
BEGIN
    SELECT * FROM Empresa WHERE activo = 1;
END //

CREATE PROCEDURE listarEmpresasNoActivos ()
BEGIN
    SELECT * FROM Empresa WHERE activo = 0;
END //
DELIMITER //
CREATE PROCEDURE listarEmpresasPorCliente (IN p_idCliente INT)
BEGIN
    SELECT * FROM Empresa WHERE cliente_idCliente = p_idCliente;
END //
DELIMITER //
CREATE PROCEDURE listarEmpresasPorClienteActivas (IN p_idCliente INT)
BEGIN
    SELECT * FROM Empresa WHERE cliente_idCliente = p_idCliente AND activo = 1;
END //
DELIMITER //
CREATE PROCEDURE listarEmpresasPorClienteNoActivas (IN p_idCliente INT)
BEGIN
    SELECT * FROM Empresa WHERE cliente_idCliente = p_idCliente AND activo = 0;
END //

DELIMITER //
