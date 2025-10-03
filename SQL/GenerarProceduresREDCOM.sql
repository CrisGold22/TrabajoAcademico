SET CURRENT SCHEMA DB2REDCOM;

-- =====================================================================
-- CATEGORIAPRODUCTO
-- =====================================================================
CREATE OR REPLACE PROCEDURE insertarCategoriaProducto (
    IN p_idCategoriaProducto INT,
    IN p_NombreCategoria     VARCHAR(45),
    IN p_Descripcion         VARCHAR(45),
    IN p_Catalogo_idCatalogo INT,
    IN p_Activo              SMALLINT
)
LANGUAGE SQL
BEGIN
    INSERT INTO "CategoriaProducto" (
        "idCategoriaProducto","NombreCategoria","Descripcion","Catalogo_idCatalogo","Activo"
    ) VALUES (
        p_idCategoriaProducto,p_NombreCategoria,p_Descripcion,p_Catalogo_idCatalogo,p_Activo
    );
END;

CREATE OR REPLACE PROCEDURE modificarCategoriaProducto (
    IN p_idCategoriaProducto INT,
    IN p_NombreCategoria     VARCHAR(45),
    IN p_Descripcion         VARCHAR(45),
    IN p_Catalogo_idCatalogo INT,
    IN p_Activo              SMALLINT
)
LANGUAGE SQL
BEGIN
    UPDATE "CategoriaProducto"
       SET "NombreCategoria"     = p_NombreCategoria,
           "Descripcion"         = p_Descripcion,
           "Catalogo_idCatalogo" = p_Catalogo_idCatalogo,
           "Activo"              = p_Activo
     WHERE "idCategoriaProducto" = p_idCategoriaProducto;
END;

CREATE OR REPLACE PROCEDURE eliminarCategoriaProducto (IN p_idCategoriaProducto INT)
LANGUAGE SQL
BEGIN
    DELETE FROM "CategoriaProducto" WHERE "idCategoriaProducto" = p_idCategoriaProducto;
END;

CREATE OR REPLACE PROCEDURE buscarCategoriaProductoPorId (IN p_idCategoriaProducto INT)
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "CategoriaProducto" WHERE "idCategoriaProducto" = p_idCategoriaProducto;
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE listarCategoriaProductos ()
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "CategoriaProducto";
    OPEN c;
END;

-- =====================================================================
-- PRODUCTO
-- =====================================================================
CREATE OR REPLACE PROCEDURE insertarProducto (
    IN p_ID_Producto                           INT,
    IN p_Nombre                                VARCHAR(45),
    IN p_SKU                                   VARCHAR(45),
    IN p_Descripcion                           VARCHAR(45),
    IN p_precioRegular                         DOUBLE,
    IN p_precioAlMayor                         DOUBLE,
    IN p_UnidadDeMedida                        VARCHAR(45),
    IN p_StockDisponible                       INT,
    IN p_StockMinimo                           VARCHAR(45),
    IN p_StockMaximo                           VARCHAR(45),
    IN p_Activo                                SMALLINT,
    IN p_CategoriaProducto_idCategoriaProducto INT
)
LANGUAGE SQL
BEGIN
    INSERT INTO "Producto" (
        "ID_Producto","Nombre","SKU","Descripcion","precioRegular","precioAlMayor",
        "UnidadDeMedida","StockDisponible","StockMinimo","StockMaximo","Activo",
        "CategoriaProducto_idCategoriaProducto"
    ) VALUES (
        p_ID_Producto,p_Nombre,p_SKU,p_Descripcion,p_precioRegular,p_precioAlMayor,
        p_UnidadDeMedida,p_StockDisponible,p_StockMinimo,p_StockMaximo,p_Activo,
        p_CategoriaProducto_idCategoriaProducto
    );
END;

CREATE OR REPLACE PROCEDURE modificarProducto (
    IN p_ID_Producto                           INT,
    IN p_Nombre                                VARCHAR(45),
    IN p_SKU                                   VARCHAR(45),
    IN p_Descripcion                           VARCHAR(45),
    IN p_precioRegular                         DOUBLE,
    IN p_precioAlMayor                         DOUBLE,
    IN p_UnidadDeMedida                        VARCHAR(45),
    IN p_StockDisponible                       INT,
    IN p_StockMinimo                           VARCHAR(45),
    IN p_StockMaximo                           VARCHAR(45),
    IN p_Activo                                SMALLINT,
    IN p_CategoriaProducto_idCategoriaProducto INT
)
LANGUAGE SQL
BEGIN
    UPDATE "Producto"
       SET "Nombre"        = p_Nombre,
           "SKU"           = p_SKU,
           "Descripcion"   = p_Descripcion,
           "precioRegular" = p_precioRegular,
           "precioAlMayor" = p_precioAlMayor,
           "UnidadDeMedida"= p_UnidadDeMedida,
           "StockDisponible"= p_StockDisponible,
           "StockMinimo"   = p_StockMinimo,
           "StockMaximo"   = p_StockMaximo,
           "Activo"        = p_Activo,
           "CategoriaProducto_idCategoriaProducto" = p_CategoriaProducto_idCategoriaProducto
     WHERE "ID_Producto" = p_ID_Producto;
END;

CREATE OR REPLACE PROCEDURE eliminarProducto (IN p_ID_Producto INT)
LANGUAGE SQL
BEGIN
    DELETE FROM "Producto" WHERE "ID_Producto" = p_ID_Producto;
END;

CREATE OR REPLACE PROCEDURE buscarProductoPorId (IN p_ID_Producto INT)
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "Producto" WHERE "ID_Producto" = p_ID_Producto;
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE listarProductos ()
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "Producto";
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE buscarProductoPorSKU (IN p_SKU VARCHAR(45))
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "Producto" WHERE "SKU" = p_SKU;
    OPEN c;
END;

-- =====================================================================
-- CLIENTE
-- =====================================================================
CREATE OR REPLACE PROCEDURE insertarCliente (
    IN p_idCliente                 SMALLINT,
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
    IN p_Activo                    SMALLINT
)
LANGUAGE SQL
BEGIN
    INSERT INTO "cliente" (
        "idCliente","lineaCredito","Categoria","numeroDePedido_Historico","numeroDePedido_MensualPro",
        "dni","nombre","apellidoPaterno","apellidoMaterno","genero","fechaNacimiento","telefono","Activo"
    ) VALUES (
        p_idCliente,p_lineaCredito,p_Categoria,p_numeroDePedido_Historico,p_numeroDePedido_MensualPro,
        p_dni,p_nombre,p_apellidoPaterno,p_apellidoMaterno,p_genero,p_fechaNacimiento,p_telefono,p_Activo
    );
END;

CREATE OR REPLACE PROCEDURE modificarCliente (
    IN p_idCliente                 SMALLINT,
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
    IN p_Activo                    SMALLINT
)
LANGUAGE SQL
BEGIN
    UPDATE "cliente"
       SET "lineaCredito"              = p_lineaCredito,
           "Categoria"                 = p_Categoria,
           "numeroDePedido_Historico"  = p_numeroDePedido_Historico,
           "numeroDePedido_MensualPro" = p_numeroDePedido_MensualPro,
           "dni"                       = p_dni,
           "nombre"                    = p_nombre,
           "apellidoPaterno"           = p_apellidoPaterno,
           "apellidoMaterno"           = p_apellidoMaterno,
           "genero"                    = p_genero,
           "fechaNacimiento"           = p_fechaNacimiento,
           "telefono"                  = p_telefono,
           "Activo"                    = p_Activo
     WHERE "idCliente" = p_idCliente;
END;

CREATE OR REPLACE PROCEDURE eliminarCliente (IN p_idCliente SMALLINT)
LANGUAGE SQL
BEGIN
    DELETE FROM "cliente" WHERE "idCliente" = p_idCliente;
END;

CREATE OR REPLACE PROCEDURE buscarClientePorId (IN p_idCliente SMALLINT)
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "cliente" WHERE "idCliente" = p_idCliente;
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE listarClientes ()
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "cliente";
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE buscarClientePorDni (IN p_dni VARCHAR(45))
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "cliente" WHERE "dni" = p_dni;
    OPEN c;
END;

-- =====================================================================
-- ADMINISTRADOR
-- =====================================================================
CREATE OR REPLACE PROCEDURE insertarAdministrador (
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
    IN p_Activo          SMALLINT
)
LANGUAGE SQL
BEGIN
    INSERT INTO "Administrador" (
        "idAdministrador","cargo","rango","dni","nombre","apellidoPaterno","apellidoMaterno",
        "genero","fechaNacimiento","telefono","Sueldo","Activo"
    ) VALUES (
        p_idAdministrador,p_cargo,p_rango,p_dni,p_nombre,p_apellidoPaterno,p_apellidoMaterno,
        p_genero,p_fechaNacimiento,p_telefono,p_Sueldo,p_Activo
    );
END;

CREATE OR REPLACE PROCEDURE modificarAdministrador (
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
    IN p_Activo          SMALLINT
)
LANGUAGE SQL
BEGIN
    UPDATE "Administrador"
       SET "cargo"            = p_cargo,
           "rango"            = p_rango,
           "dni"              = p_dni,
           "nombre"           = p_nombre,
           "apellidoPaterno"  = p_apellidoPaterno,
           "apellidoMaterno"  = p_apellidoMaterno,
           "genero"           = p_genero,
           "fechaNacimiento"  = p_fechaNacimiento,
           "telefono"         = p_telefono,
           "Sueldo"           = p_Sueldo,
           "Activo"           = p_Activo
     WHERE "idAdministrador"  = p_idAdministrador;
END;

CREATE OR REPLACE PROCEDURE eliminarAdministrador (IN p_idAdministrador INT)
LANGUAGE SQL
BEGIN
    DELETE FROM "Administrador" WHERE "idAdministrador" = p_idAdministrador;
END;

CREATE OR REPLACE PROCEDURE buscarAdministradorPorId (IN p_idAdministrador INT)
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "Administrador" WHERE "idAdministrador" = p_idAdministrador;
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE listarAdministradores ()
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "Administrador";
    OPEN c;
END;

-- =====================================================================
-- CUENTAOUSUARIO
-- =====================================================================
CREATE OR REPLACE PROCEDURE insertarCuentaUsuario (
    IN p_idCuentaUsuario             INT,
    IN p_userName                    VARCHAR(45),
    IN p_password                    VARCHAR(45),
    IN p_Administrador_idAdministrador INT,
    IN p_cliente_idCliente           SMALLINT
)
LANGUAGE SQL
BEGIN
    INSERT INTO "CuentaUsuario" (
        "idCuentaUsuario","userName","password","Administrador_idAdministrador","cliente_idCliente"
    ) VALUES (
        p_idCuentaUsuario,p_userName,p_password,p_Administrador_idAdministrador,p_cliente_idCliente
    );
END;

CREATE OR REPLACE PROCEDURE modificarCuentaUsuario (
    IN p_idCuentaUsuario             INT,
    IN p_userName                    VARCHAR(45),
    IN p_password                    VARCHAR(45),
    IN p_Administrador_idAdministrador INT,
    IN p_cliente_idCliente           SMALLINT
)
LANGUAGE SQL
BEGIN
    UPDATE "CuentaUsuario"
       SET "userName"                  = p_userName,
           "password"                  = p_password,
           "Administrador_idAdministrador" = p_Administrador_idAdministrador,
           "cliente_idCliente"         = p_cliente_idCliente
     WHERE "idCuentaUsuario" = p_idCuentaUsuario;
END;

CREATE OR REPLACE PROCEDURE eliminarCuentaUsuario (IN p_idCuentaUsuario INT)
LANGUAGE SQL
BEGIN
    DELETE FROM "CuentaUsuario" WHERE "idCuentaUsuario" = p_idCuentaUsuario;
END;

CREATE OR REPLACE PROCEDURE buscarCuentaUsuarioPorId (IN p_idCuentaUsuario INT)
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "CuentaUsuario" WHERE "idCuentaUsuario" = p_idCuentaUsuario;
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE listarCuentaUsuarios ()
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "CuentaUsuario";
    OPEN c;
END;

-- =====================================================================
-- CARRITODECOMPRAS
-- =====================================================================
CREATE OR REPLACE PROCEDURE insertarCarritoDeCompras (
    IN p_Id_CarritoDeCompras INT,
    IN p_Total_Parcial       DOUBLE,
    IN p_Estado              VARCHAR(45),
    IN p_total_con_descuento DOUBLE,
    IN p_cliente_idCliente   SMALLINT
)
LANGUAGE SQL
BEGIN
    INSERT INTO "CarritoDeCompras" (
        "Id_CarritoDeCompras","Total_Parcial","Estado","total_con_descuento","cliente_idCliente"
    ) VALUES (
        p_Id_CarritoDeCompras,p_Total_Parcial,p_Estado,p_total_con_descuento,p_cliente_idCliente
    );
END;

CREATE OR REPLACE PROCEDURE modificarCarritoDeCompras (
    IN p_Id_CarritoDeCompras INT,
    IN p_Total_Parcial       DOUBLE,
    IN p_Estado              VARCHAR(45),
    IN p_total_con_descuento DOUBLE,
    IN p_cliente_idCliente   SMALLINT
)
LANGUAGE SQL
BEGIN
    UPDATE "CarritoDeCompras"
       SET "Total_Parcial"       = p_Total_Parcial,
           "Estado"              = p_Estado,
           "total_con_descuento" = p_total_con_descuento,
           "cliente_idCliente"   = p_cliente_idCliente
     WHERE "Id_CarritoDeCompras" = p_Id_CarritoDeCompras;
END;

CREATE OR REPLACE PROCEDURE eliminarCarritoDeCompras (IN p_Id_CarritoDeCompras INT)
LANGUAGE SQL
BEGIN
    DELETE FROM "CarritoDeCompras" WHERE "Id_CarritoDeCompras" = p_Id_CarritoDeCompras;
END;

CREATE OR REPLACE PROCEDURE buscarCarritoDeComprasPorId (IN p_Id_CarritoDeCompras INT)
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "CarritoDeCompras" WHERE "Id_CarritoDeCompras" = p_Id_CarritoDeCompras;
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE listarCarritosDeCompras ()
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "CarritoDeCompras";
    OPEN c;
END;

-- =====================================================================
-- LINEACARRITO
-- =====================================================================
CREATE OR REPLACE PROCEDURE insertarLineaCarrito (
    IN p_idLineaCarrito             INT,
    IN p_cantidad                   INT,
    IN p_precioVolumen              DOUBLE,
    IN p_subTotal                   DOUBLE,
    IN p_Producto_ID_Producto1      INT,
    IN p_CarritoDeCompras_Productos INT,
    IN p_activo                     SMALLINT,
    IN p_Producto_ID_Producto       INT
)
LANGUAGE SQL
BEGIN
    INSERT INTO "LineaCarrito" (
        "idLineaCarrito","cantidad","precioVolumen","subTotal","Producto_ID_Producto1",
        "CarritoDeCompras_Productos","activo","Producto_ID_Producto"
    ) VALUES (
        p_idLineaCarrito,p_cantidad,p_precioVolumen,p_subTotal,p_Producto_ID_Producto1,
        p_CarritoDeCompras_Productos,p_activo,p_Producto_ID_Producto
    );
END;

CREATE OR REPLACE PROCEDURE modificarLineaCarrito (
    IN p_idLineaCarrito             INT,
    IN p_cantidad                   INT,
    IN p_precioVolumen              DOUBLE,
    IN p_subTotal                   DOUBLE,
    IN p_Producto_ID_Producto1      INT,
    IN p_CarritoDeCompras_Productos INT,
    IN p_activo                     SMALLINT,
    IN p_Producto_ID_Producto       INT
)
LANGUAGE SQL
BEGIN
    UPDATE "LineaCarrito"
       SET "cantidad"                   = p_cantidad,
           "precioVolumen"              = p_precioVolumen,
           "subTotal"                   = p_subTotal,
           "Producto_ID_Producto1"      = p_Producto_ID_Producto1,
           "CarritoDeCompras_Productos" = p_CarritoDeCompras_Productos,
           "activo"                     = p_activo,
           "Producto_ID_Producto"       = p_Producto_ID_Producto
     WHERE "idLineaCarrito" = p_idLineaCarrito;
END;

CREATE OR REPLACE PROCEDURE eliminarLineaCarrito (IN p_idLineaCarrito INT)
LANGUAGE SQL
BEGIN
    DELETE FROM "LineaCarrito" WHERE "idLineaCarrito" = p_idLineaCarrito;
END;

CREATE OR REPLACE PROCEDURE buscarLineaCarritoPorId (IN p_idLineaCarrito INT)
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "LineaCarrito" WHERE "idLineaCarrito" = p_idLineaCarrito;
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE listarLineasCarrito ()
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "LineaCarrito";
    OPEN c;
END;

-- =====================================================================
-- DESCUENTO
-- =====================================================================
CREATE OR REPLACE PROCEDURE insertarDescuento (
    IN p_idReglaPrecioVolumen INT,
    IN p_precioPorVolumen     DOUBLE,
    IN p_cantidadMax          INT,
    IN p_cantidadMin          INT,
    IN p_Activo               SMALLINT,
    IN p_Producto_ID_Producto INT
)
LANGUAGE SQL
BEGIN
    INSERT INTO "Descuento" (
        "idReglaPrecioVolumen","precioPorVolumen","cantidadMax","cantidadMin","Activo","Producto_ID_Producto"
    ) VALUES (
        p_idReglaPrecioVolumen,p_precioPorVolumen,p_cantidadMax,p_cantidadMin,p_Activo,p_Producto_ID_Producto
    );
END;

CREATE OR REPLACE PROCEDURE modificarDescuento (
    IN p_idReglaPrecioVolumen INT,
    IN p_precioPorVolumen     DOUBLE,
    IN p_cantidadMax          INT,
    IN p_cantidadMin          INT,
    IN p_Activo               SMALLINT,
    IN p_Producto_ID_Producto INT
)
LANGUAGE SQL
BEGIN
    UPDATE "Descuento"
       SET "precioPorVolumen"     = p_precioPorVolumen,
           "cantidadMax"          = p_cantidadMax,
           "cantidadMin"          = p_cantidadMin,
           "Activo"               = p_Activo,
           "Producto_ID_Producto" = p_Producto_ID_Producto
     WHERE "idReglaPrecioVolumen" = p_idReglaPrecioVolumen;
END;

CREATE OR REPLACE PROCEDURE eliminarDescuento (IN p_idReglaPrecioVolumen INT)
LANGUAGE SQL
BEGIN
    DELETE FROM "Descuento" WHERE "idReglaPrecioVolumen" = p_idReglaPrecioVolumen;
END;

CREATE OR REPLACE PROCEDURE buscarDescuentoPorId (IN p_idReglaPrecioVolumen INT)
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "Descuento" WHERE "idReglaPrecioVolumen" = p_idReglaPrecioVolumen;
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE listarDescuentos ()
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "Descuento";
    OPEN c;
END;

-- =====================================================================
-- ORDENCOMPRA
-- =====================================================================
CREATE OR REPLACE PROCEDURE insertarOrdenCompra (
    IN p_IdPedido                                INT,
    IN p_FechaCreacion                           DATE,
    IN p_total_parcial                           DOUBLE,
    IN p_total_final                             DOUBLE,
    IN p_descuentoTotal                          DOUBLE,
    IN p_Estado                                  VARCHAR(45),
    IN p_DetalleDeOrdenCompra_id_Detalle         VARCHAR(45),
    IN p_DetalleDeEnvio_id_DetalleEnvio          VARCHAR(45),
    IN p_LineaComprobantePago_idLineaComprobantePago INT,
    IN p_Activo                                  SMALLINT
)
LANGUAGE SQL
BEGIN
    INSERT INTO "OrdenCompra" (
        "IdPedido","FechaCreacion","total_parcial","total_final","descuentoTotal","Estado",
        "DetalleDeOrdenCompra_id_Detalle","DetalleDeEnvio_id_DetalleEnvio",
        "LineaComprobantePago_idLineaComprobantePago","Activo"
    ) VALUES (
        p_IdPedido,p_FechaCreacion,p_total_parcial,p_total_final,p_descuentoTotal,p_Estado,
        p_DetalleDeOrdenCompra_id_Detalle,p_DetalleDeEnvio_id_DetalleEnvio,
        p_LineaComprobantePago_idLineaComprobantePago,p_Activo
    );
END;

CREATE OR REPLACE PROCEDURE modificarOrdenCompra (
    IN p_IdPedido                                INT,
    IN p_FechaCreacion                           DATE,
    IN p_total_parcial                           DOUBLE,
    IN p_total_final                             DOUBLE,
    IN p_descuentoTotal                          DOUBLE,
    IN p_Estado                                  VARCHAR(45),
    IN p_DetalleDeOrdenCompra_id_Detalle         VARCHAR(45),
    IN p_DetalleDeEnvio_id_DetalleEnvio          VARCHAR(45),
    IN p_LineaComprobantePago_idLineaComprobantePago INT,
    IN p_Activo                                  SMALLINT
)
LANGUAGE SQL
BEGIN
    UPDATE "OrdenCompra"
       SET "FechaCreacion" = p_FechaCreacion,
           "total_parcial" = p_total_parcial,
           "total_final"   = p_total_final,
           "descuentoTotal"= p_descuentoTotal,
           "Estado"        = p_Estado,
           "DetalleDeOrdenCompra_id_Detalle" = p_DetalleDeOrdenCompra_id_Detalle,
           "DetalleDeEnvio_id_DetalleEnvio"  = p_DetalleDeEnvio_id_DetalleEnvio,
           "LineaComprobantePago_idLineaComprobantePago" = p_LineaComprobantePago_idLineaComprobantePago,
           "Activo"        = p_Activo
     WHERE "IdPedido" = p_IdPedido;
END;

CREATE OR REPLACE PROCEDURE eliminarOrdenCompra (IN p_IdPedido INT)
LANGUAGE SQL
BEGIN
    DELETE FROM "OrdenCompra" WHERE "IdPedido" = p_IdPedido;
END;

CREATE OR REPLACE PROCEDURE buscarOrdenCompraPorId (IN p_IdPedido INT)
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "OrdenCompra" WHERE "IdPedido" = p_IdPedido;
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE listarOrdenesCompra ()
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "OrdenCompra";
    OPEN c;
END;

-- =====================================================================
-- COMPROBANTEPAGO
-- =====================================================================
CREATE OR REPLACE PROCEDURE insertarComprobantePago (
    IN p_idComprobante        VARCHAR(45),
    IN p_fechaEmision         DATE,
    IN p_RUC                  INT,
    IN p_totalSinImpuestos    DOUBLE,
    IN p_Impuesto             VARCHAR(45),
    IN p_totalFinal           DOUBLE,
    IN p_metodoPago           VARCHAR(45),
    IN p_subTotal             DOUBLE,
    IN p_activo               SMALLINT,
    IN p_OrdenCompra_IdPedido INT
)
LANGUAGE SQL
BEGIN
    INSERT INTO "ComprobantePago" (
        "idComprobante","fechaEmision","RUC","totalSinImpuestos","Impuesto",
        "totalFinal","metodoPago","subTotal","activo","OrdenCompra_IdPedido"
    ) VALUES (
        p_idComprobante,p_fechaEmision,p_RUC,p_totalSinImpuestos,p_Impuesto,
        p_totalFinal,p_metodoPago,p_subTotal,p_activo,p_OrdenCompra_IdPedido
    );
END;

CREATE OR REPLACE PROCEDURE modificarComprobantePago (
    IN p_idComprobante        VARCHAR(45),
    IN p_fechaEmision         DATE,
    IN p_RUC                  INT,
    IN p_totalSinImpuestos    DOUBLE,
    IN p_Impuesto             VARCHAR(45),
    IN p_totalFinal           DOUBLE,
    IN p_metodoPago           VARCHAR(45),
    IN p_subTotal             DOUBLE,
    IN p_activo               SMALLINT,
    IN p_OrdenCompra_IdPedido INT
)
LANGUAGE SQL
BEGIN
    UPDATE "ComprobantePago"
       SET "fechaEmision"         = p_fechaEmision,
           "RUC"                  = p_RUC,
           "totalSinImpuestos"    = p_totalSinImpuestos,
           "Impuesto"             = p_Impuesto,
           "totalFinal"           = p_totalFinal,
           "metodoPago"           = p_metodoPago,
           "subTotal"             = p_subTotal,
           "activo"               = p_activo,
           "OrdenCompra_IdPedido" = p_OrdenCompra_IdPedido
     WHERE "idComprobante"        = p_idComprobante;
END;

CREATE OR REPLACE PROCEDURE eliminarComprobantePago (IN p_idComprobante VARCHAR(45))
LANGUAGE SQL
BEGIN
    DELETE FROM "ComprobantePago" WHERE "idComprobante" = p_idComprobante;
END;

CREATE OR REPLACE PROCEDURE buscarComprobantePagoPorId (IN p_idComprobante VARCHAR(45))
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "ComprobantePago" WHERE "idComprobante" = p_idComprobante;
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE listarComprobantesPago ()
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "ComprobantePago";
    OPEN c;
END;

-- =====================================================================
-- LINEACOMPROBANTEPAGO
-- =====================================================================
CREATE OR REPLACE PROCEDURE insertarLineaComprobantePago (
    IN p_idLineaComprobantePago     INT,
    IN p_montoPagado                VARCHAR(45),
    IN p_activo                     SMALLINT,
    IN p_ComprobantePago_idComprobante VARCHAR(45),
    IN p_codigo                     INT,
    IN p_cantidad                   INT,
    IN p_subtotal                   DOUBLE
)
LANGUAGE SQL
BEGIN
    INSERT INTO "LineaComprobantePago" (
        "idLineaComprobantePago","montoPagado","activo","ComprobantePago_idComprobante",
        "codigo","cantidad","subtotal"
    ) VALUES (
        p_idLineaComprobantePago,p_montoPagado,p_activo,p_ComprobantePago_idComprobante,
        p_codigo,p_cantidad,p_subtotal
    );
END;

CREATE OR REPLACE PROCEDURE modificarLineaComprobantePago (
    IN p_idLineaComprobantePago     INT,
    IN p_montoPagado                VARCHAR(45),
    IN p_activo                     SMALLINT,
    IN p_ComprobantePago_idComprobante VARCHAR(45),
    IN p_codigo                     INT,
    IN p_cantidad                   INT,
    IN p_subtotal                   DOUBLE
)
LANGUAGE SQL
BEGIN
    UPDATE "LineaComprobantePago"
       SET "montoPagado"                = p_montoPagado,
           "activo"                     = p_activo,
           "ComprobantePago_idComprobante" = p_ComprobantePago_idComprobante,
           "codigo"                     = p_codigo,
           "cantidad"                   = p_cantidad,
           "subtotal"                   = p_subtotal
     WHERE "idLineaComprobantePago"     = p_idLineaComprobantePago;
END;

CREATE OR REPLACE PROCEDURE eliminarLineaComprobantePago (IN p_idLineaComprobantePago INT)
LANGUAGE SQL
BEGIN
    DELETE FROM "LineaComprobantePago" WHERE "idLineaComprobantePago" = p_idLineaComprobantePago;
END;

CREATE OR REPLACE PROCEDURE buscarLineaComprobantePagoPorId (IN p_idLineaComprobantePago INT)
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "LineaComprobantePago" WHERE "idLineaComprobantePago" = p_idLineaComprobantePago;
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE listarLineasComprobantePago ()
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "LineaComprobantePago";
    OPEN c;
END;

-- =====================================================================
-- LINEAORDENCOMPRA
-- =====================================================================
CREATE OR REPLACE PROCEDURE insertarLineaOrdenCompra (
    IN p_idLineaOrdenCompra INT,
    IN p_cantidad           INT,
    IN p_precioUnitario     DOUBLE,
    IN p_subtotal           DOUBLE,
    IN p_Producto_ID_Producto INT,
    IN p_OrdenCompra_IdPedido INT,
    IN p_CarritoDeCompras_Id  INT,
    IN p_activo             SMALLINT
)
LANGUAGE SQL
BEGIN
    INSERT INTO "LineaOrdenCompra" (
        "idLineaOrdenCompra","cantidad","precioUnitario","subtotal",
        "Producto_ID_Producto","OrdenCompra_IdPedido","CarritoDeCompras_Id","activo"
    ) VALUES (
        p_idLineaOrdenCompra,p_cantidad,p_precioUnitario,p_subtotal,
        p_Producto_ID_Producto,p_OrdenCompra_IdPedido,p_CarritoDeCompras_Id,p_activo
    );
END;

CREATE OR REPLACE PROCEDURE modificarLineaOrdenCompra (
    IN p_idLineaOrdenCompra INT,
    IN p_cantidad           INT,
    IN p_precioUnitario     DOUBLE,
    IN p_subtotal           DOUBLE,
    IN p_Producto_ID_Producto INT,
    IN p_OrdenCompra_IdPedido INT,
    IN p_CarritoDeCompras_Id  INT,
    IN p_activo             SMALLINT
)
LANGUAGE SQL
BEGIN
    UPDATE "LineaOrdenCompra"
       SET "cantidad"             = p_cantidad,
           "precioUnitario"       = p_precioUnitario,
           "subtotal"             = p_subtotal,
           "Producto_ID_Producto" = p_Producto_ID_Producto,
           "OrdenCompra_IdPedido" = p_OrdenCompra_IdPedido,
           "CarritoDeCompras_Id"  = p_CarritoDeCompras_Id,
           "activo"               = p_activo
     WHERE "idLineaOrdenCompra"   = p_idLineaOrdenCompra;
END;

CREATE OR REPLACE PROCEDURE eliminarLineaOrdenCompra (IN p_idLineaOrdenCompra INT)
LANGUAGE SQL
BEGIN
    DELETE FROM "LineaOrdenCompra" WHERE "idLineaOrdenCompra" = p_idLineaOrdenCompra;
END;

CREATE OR REPLACE PROCEDURE buscarLineaOrdenCompraPorId (IN p_idLineaOrdenCompra INT)
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "LineaOrdenCompra" WHERE "idLineaOrdenCompra" = p_idLineaOrdenCompra;
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE listarLineasOrdenCompra ()
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "LineaOrdenCompra";
    OPEN c;
END;

-- =====================================================================
-- DETALLEDEENVIO
-- =====================================================================
CREATE OR REPLACE PROCEDURE insertarDetalleDeEnvio (
    IN p_id_DetalleEnvio VARCHAR(45),
    IN p_descripcion     VARCHAR(45),
    IN p_fechaEntrega    DATE,
    IN p_horarioEntrega  DATE
)
LANGUAGE SQL
BEGIN
    INSERT INTO "DetalleDeEnvio" ("id_DetalleEnvio","descripcion","fechaEntrega","horarioEntrega")
    VALUES (p_id_DetalleEnvio,p_descripcion,p_fechaEntrega,p_horarioEntrega);
END;

CREATE OR REPLACE PROCEDURE modificarDetalleDeEnvio (
    IN p_id_DetalleEnvio VARCHAR(45),
    IN p_descripcion     VARCHAR(45),
    IN p_fechaEntrega    DATE,
    IN p_horarioEntrega  DATE
)
LANGUAGE SQL
BEGIN
    UPDATE "DetalleDeEnvio"
       SET "descripcion"    = p_descripcion,
           "fechaEntrega"   = p_fechaEntrega,
           "horarioEntrega" = p_horarioEntrega
     WHERE "id_DetalleEnvio" = p_id_DetalleEnvio;
END;

CREATE OR REPLACE PROCEDURE eliminarDetalleDeEnvio (IN p_id_DetalleEnvio VARCHAR(45))
LANGUAGE SQL
BEGIN
    DELETE FROM "DetalleDeEnvio" WHERE "id_DetalleEnvio" = p_id_DetalleEnvio;
END;

CREATE OR REPLACE PROCEDURE buscarDetalleDeEnvioPorId (IN p_id_DetalleEnvio VARCHAR(45))
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "DetalleDeEnvio" WHERE "id_DetalleEnvio" = p_id_DetalleEnvio;
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE listarDetallesDeEnvio ()
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "DetalleDeEnvio";
    OPEN c;
END;

-- =====================================================================
-- CATEGORIAPRODUCTO_HAS_CATEGORIAPRODUCTO (tabla puente)
-- =====================================================================
CREATE OR REPLACE PROCEDURE insertarRelacionCategoriaProducto (
    IN p_idCategoriaProducto  INT,
    IN p_idCategoriaProducto1 INT
)
LANGUAGE SQL
BEGIN
    INSERT INTO "CategoriaProducto_has_CategoriaProducto" (
        "CategoriaProducto_idCategoriaProducto","CategoriaProducto_idCategoriaProducto1"
    ) VALUES (
        p_idCategoriaProducto,p_idCategoriaProducto1
    );
END;

CREATE OR REPLACE PROCEDURE eliminarRelacionCategoriaProducto (
    IN p_idCategoriaProducto  INT,
    IN p_idCategoriaProducto1 INT
)
LANGUAGE SQL
BEGIN
    DELETE FROM "CategoriaProducto_has_CategoriaProducto"
     WHERE "CategoriaProducto_idCategoriaProducto"  = p_idCategoriaProducto
       AND "CategoriaProducto_idCategoriaProducto1" = p_idCategoriaProducto1;
END;

CREATE OR REPLACE PROCEDURE buscarRelacionCategoriaProducto (
    IN p_idCategoriaProducto  INT,
    IN p_idCategoriaProducto1 INT
)
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT *
      FROM "CategoriaProducto_has_CategoriaProducto"
     WHERE "CategoriaProducto_idCategoriaProducto"  = p_idCategoriaProducto
       AND "CategoriaProducto_idCategoriaProducto1" = p_idCategoriaProducto1;
    OPEN c;
END;

CREATE OR REPLACE PROCEDURE listarRelacionesCategoriaProducto ()
LANGUAGE SQL
DYNAMIC RESULT SETS 1
BEGIN
    DECLARE c CURSOR WITH RETURN TO CALLER FOR
    SELECT * FROM "CategoriaProducto_has_CategoriaProducto";
    OPEN c;
END;
