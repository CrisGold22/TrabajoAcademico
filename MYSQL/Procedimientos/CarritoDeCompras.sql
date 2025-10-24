USE REDCOM;

-- =====================================================================
-- CARRITODECOMPRAS
-- =====================================================================

DELIMITER //

CREATE PROCEDURE insertarCarritoDeCompras (
    IN p_Id_CarritoDeCompras INT,
    IN p_Total_Parcial       DOUBLE,
    IN p_Estado              VARCHAR(45),
    IN p_total_con_descuento DOUBLE,
    IN p_cliente_idCliente   INT
)
BEGIN
    INSERT INTO CarritoDeCompras (
        Id_CarritoDeCompras, Total_Parcial, Estado, total_con_descuento, cliente_idCliente
    ) VALUES (
        p_Id_CarritoDeCompras, p_Total_Parcial, p_Estado, p_total_con_descuento, p_cliente_idCliente
    );
END //

CREATE PROCEDURE modificarCarritoDeCompras (
    IN p_Id_CarritoDeCompras INT,
    IN p_Total_Parcial       DOUBLE,
    IN p_Estado              VARCHAR(45),
    IN p_total_con_descuento DOUBLE,
    IN p_cliente_idCliente   INT
)
BEGIN
    UPDATE CarritoDeCompras
       SET Total_Parcial       = p_Total_Parcial,
           Estado              = p_Estado,
           total_con_descuento = p_total_con_descuento,
           cliente_idCliente   = p_cliente_idCliente
     WHERE Id_CarritoDeCompras = p_Id_CarritoDeCompras;
END //

CREATE PROCEDURE eliminarCarritoDeCompras (IN p_Id_CarritoDeCompras INT)
BEGIN
    DELETE FROM CarritoDeCompras WHERE Id_CarritoDeCompras = p_Id_CarritoDeCompras;
END //

CREATE PROCEDURE buscarCarritoDeComprasPorId (IN p_Id_CarritoDeCompras INT)
BEGIN
    SELECT * FROM CarritoDeCompras WHERE Id_CarritoDeCompras = p_Id_CarritoDeCompras;
END //

CREATE PROCEDURE listarCarritosDeCompras ()
BEGIN
    SELECT * FROM CarritoDeCompras;
END //