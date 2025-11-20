USE REDCOM;

-- =====================================================================
-- CLIENTE
-- =====================================================================

DROP PROCEDURE IF EXISTS insertarCliente;
DROP PROCEDURE IF EXISTS eliminarCliente;
DROP PROCEDURE IF EXISTS modificarCliente;
DROP PROCEDURE IF EXISTS listarClientes;
DROP PROCEDURE IF EXISTS buscarClientePorDni;
DROP PROCEDURE IF EXISTS buscarClientePorId;


DELIMITER //


CREATE PROCEDURE insertarCliente (
    IN p_lineaCredito              DOUBLE,
    IN p_Categoria                 VARCHAR(45),
    IN p_numeroDePedido_Historico  INT,
    IN p_numeroDePedido_MensualPro INT,
    IN p_dni                       VARCHAR(45),
    IN p_nombre                    VARCHAR(45),
    IN p_apellidoPaterno           VARCHAR(45),
    IN p_apellidoMaterno           VARCHAR(45),
    IN p_genero                    VARCHAR(45),
    IN p_fechaNacimiento           DATE,
    IN p_telefono                  INT,
    IN p_Activo                    TINYINT, 
    IN p_idCuentaUsuario INT,    
    OUT p_id INT
)
BEGIN
    INSERT INTO Cliente (
        lineaCredito, Categoria, numeroDePedido_Historico, numeroDePedido_MensualPro,
        dni, nombre, apellidoPaterno, apellidoMaterno, genero, fechaNacimiento, telefono, Activo,idCuentaUsuario
    ) VALUES (
        p_lineaCredito, p_Categoria, p_numeroDePedido_Historico, p_numeroDePedido_MensualPro,
        p_dni, p_nombre, p_apellidoPaterno, p_apellidoMaterno, p_genero, p_fechaNacimiento, p_telefono, p_Activo,p_idCuentaUsuario
    );
        
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarCliente (
    IN p_idCliente                 INT,
    IN p_lineaCredito              DOUBLE,
    IN p_Categoria                 VARCHAR(45),
    IN p_numeroDePedido_Historico  INT,
    IN p_numeroDePedido_MensualPro INT,
    IN p_dni                       VARCHAR(45),
    IN p_nombre                    VARCHAR(45),
    IN p_apellidoPaterno           VARCHAR(45),
    IN p_apellidoMaterno           VARCHAR(45),
    IN p_genero                    VARCHAR(45),
    IN p_fechaNacimiento           DATE,
    IN p_telefono                  INT,
    IN p_Activo                    TINYINT,
    IN p_idCuentaUsuario INT
)
BEGIN
    UPDATE Cliente
       SET lineaCredito              = p_lineaCredito,
           Categoria                 = p_Categoria,
           numeroDePedido_Historico  = p_numeroDePedido_Historico,
           numeroDePedido_MensualPro = p_numeroDePedido_MensualPro,
           dni                       = p_dni,
           nombre                    = p_nombre,
           apellidoPaterno           = p_apellidoPaterno,
           apellidoMaterno           = p_apellidoMaterno,
           genero                    = p_genero,
           fechaNacimiento           = p_fechaNacimiento,
           telefono                  = p_telefono,
           Activo                    = p_Activo,
		   idCuentaUsuario			 = p_idCuentaUsuario  
     WHERE idCliente = p_idCliente;
END //

CREATE PROCEDURE eliminarCliente (IN p_idCliente INT)
BEGIN
    DELETE FROM Cliente WHERE idCliente = p_idCliente;
END //

CREATE PROCEDURE buscarClientePorId (IN p_idCliente INT)
BEGIN
    SELECT * FROM Cliente WHERE idCliente = p_idCliente;
END //

CREATE PROCEDURE listarClientes ()
BEGIN
    SELECT * FROM Cliente;
END //

CREATE PROCEDURE buscarClientePorDni (IN p_dni VARCHAR(45))
BEGIN
    SELECT * FROM Cliente WHERE dni = p_dni;
END //
