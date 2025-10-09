CREATE SCHEMA REDCOM_DB2;
SET CURRENT SCHEMA REDCOM_DB2;

-- ============================================================
-- Tablas principales
-- ============================================================

CREATE TABLE "CategoriaProducto" (
  "idCategoriaProducto" INT NOT NULL,
  "NombreCategoria"     VARCHAR(45),
  "Descripcion"         VARCHAR(45),
  "Catalogo_idCatalogo" INT,     -- La FK a Catalogo sigue omitida porque no existe tabla Catalogo
  "Activo"              SMALLINT,
  PRIMARY KEY ("idCategoriaProducto")
);

CREATE TABLE "Producto" (
  "ID_Producto"                            INT NOT NULL,
  "Nombre"                                 VARCHAR(45),
  "SKU"                                    VARCHAR(45),
  "Descripcion"                            VARCHAR(45),
  "precioRegular"                          DOUBLE,
  "precioAlMayor"                          DOUBLE,
  "UnidadDeMedida"                         VARCHAR(45),
  "StockDisponible"                        INT,
  "StockMinimo"                            INT,           -- CAMBIO: antes VARCHAR(45)
  "StockMaximo"                            INT,           -- CAMBIO: antes VARCHAR(45)
  "Activo"                                 SMALLINT,
  "CategoriaProducto_idCategoriaProducto"  INT,
  PRIMARY KEY ("ID_Producto")
);

CREATE TABLE "cliente" (
  "lineaCredito"              DOUBLE,
  "Categoria"                 VARCHAR(45),
  "numeroDePedido_Historico"  INT,
  "numeroDePedido_MensualPro" INT,
  "dni"                       VARCHAR(45),
  "nombre"                    VARCHAR(45),
  "apellidoPaterno"           VARCHAR(45),
  "apellidoMaterno"           VARCHAR(45),
  "genero"                    VARCHAR(45),
  "fechaNacimiento"           DATE,
  "telefono"                  INT,
  "Activo"                    SMALLINT,
  "idCliente"                 INT NOT NULL,  -- CAMBIO: SMALLINT -> INT
  PRIMARY KEY ("idCliente")
);

CREATE TABLE "Administrador" (
  "idAdministrador"  INT NOT NULL,
  "cargo"            VARCHAR(45),
  "rango"            VARCHAR(45),
  "dni"              VARCHAR(45),
  "nombre"           VARCHAR(45),
  "apellidoPaterno"  VARCHAR(45),
  "apellidoMaterno"  VARCHAR(45),
  "genero"           VARCHAR(45),
  "fechaNacimiento"  DATE,
  "telefono"         INT,
  "Sueldo"           DOUBLE,
  "Activo"           SMALLINT,
  PRIMARY KEY ("idAdministrador")
);

CREATE TABLE "CuentaUsuario" (
  "idCuentaUsuario"          INT NOT NULL,
  "userName"                 VARCHAR(45),
  "password"                 VARCHAR(45),
  "Administrador_idAdministrador" INT NULL,  -- CAMBIO: pueden ser NULL e INTEGER
  "cliente_idCliente"        INT NULL,       -- CAMBIO: SMALLINT -> INT y permite NULL
  PRIMARY KEY ("idCuentaUsuario")
);

CREATE TABLE "CarritoDeCompras" (
  "Id_CarritoDeCompras" INT NOT NULL,
  "Total_Parcial"       DOUBLE,
  "Estado"              VARCHAR(45),
  "total_con_descuento" DOUBLE,
  "cliente_idCliente"   INT,                  -- CAMBIO por idCliente INT
  PRIMARY KEY ("Id_CarritoDeCompras")
);

CREATE TABLE "LineaCarrito" (
  "idLineaCarrito"              INT NOT NULL,
  "cantidad"                    INT,
  "precioVolumen"               DOUBLE,
  "subTotal"                    DOUBLE,
  "Producto_ID_Producto1"       INT,
  "CarritoDeCompras_Productos"  INT,
  "activo"                      SMALLINT,
  "Producto_ID_Producto"        INT,
  PRIMARY KEY ("idLineaCarrito")
);

CREATE TABLE "Descuento" (
  "idReglaPrecioVolumen" INT NOT NULL,
  "precioPorVolumen"     DOUBLE,
  "cantidadMax"          INT,
  "cantidadMin"          INT,
  "Activo"               SMALLINT,
  "Producto_ID_Producto" INT,
  PRIMARY KEY ("idReglaPrecioVolumen")
);

CREATE TABLE "DetalleDeEnvio" (
  "id_DetalleEnvio" INT NOT NULL,   -- CAMBIO: antes VARCHAR(45)
  "descripcion"     VARCHAR(45),
  "Direccion"       VARCHAR(100),   -- NUEVO
  "Distrito"        VARCHAR(60),    -- NUEVO
  "fechaEntrega"    DATE,
  "horarioEntrega"  DATE,
  PRIMARY KEY ("id_DetalleEnvio")
);

CREATE TABLE "OrdenCompra" (
  "IdPedido"                INT NOT NULL,
  "FechaCreacion"           DATE,
  "total_parcial"           DOUBLE,
  "total_final"             DOUBLE,
  "descuentoTotal"          DOUBLE,
  "Estado"                  VARCHAR(45),
  -- ELIMINADO: "DetalleDeOrdenCompra_id_Detalle"
  "DetalleDeEnvio_id_DetalleEnvio" INT,  -- CAMBIO: a INT
  -- ELIMINADO: "LineaComprobantePago_idLineaComprobantePago"
  "Activo"                  SMALLINT,
  PRIMARY KEY ("IdPedido")
);

CREATE TABLE "ComprobantePago" (
  "idComprobante"       INT NOT NULL,   -- AJUSTE: INT para compatibilidad de FK pedida en consideraciones
  "fechaEmision"        DATE,
  "RUC"                 INT,
  "totalSinImpuestos"   DOUBLE,
  "Impuesto"            DOUBLE,         -- CAMBIO: antes VARCHAR(45)
  "totalFinal"          DOUBLE,
  "metodoPago"          VARCHAR(45),
  "subTotal"            DOUBLE,
  "activo"              SMALLINT,
  "OrdenCompra_IdPedido" INT,
  PRIMARY KEY ("idComprobante")
);

CREATE TABLE "LineaComprobantePago" (
  "idLineaComprobantePago"      INT NOT NULL,
  "montoPagado"                 DOUBLE,     -- CAMBIO: agregado como DOUBLE
  "activo"                      SMALLINT,
  "ComprobantePago_idComprobante" INT,      -- CAMBIO: VARCHAR -> INT
  "codigo"                      INT,
  "cantidad"                    INT,
  "subtotal"                    DOUBLE,
  PRIMARY KEY ("idLineaComprobantePago")
);

CREATE TABLE "LineaOrdenCompra" (
  "idLineaOrdenCompra" INT NOT NULL,
  "cantidad"           INT,
  "precioUnitario"     DOUBLE,
  "subtotal"           DOUBLE,
  "Producto_ID_Producto" INT,
  "OrdenCompra_IdPedido" INT,
  "CarritoDeCompras_Id"  INT,
  "activo"             SMALLINT,
  PRIMARY KEY ("idLineaOrdenCompra")
);

CREATE TABLE "CategoriaProducto_has_CategoriaProducto" (
  "CategoriaProducto_idCategoriaProducto"  INT NOT NULL,
  "CategoriaProducto_idCategoriaProducto1" INT NOT NULL,
  PRIMARY KEY ("CategoriaProducto_idCategoriaProducto", "CategoriaProducto_idCategoriaProducto1")
);

-- ============================================================
-- Indices (ajustados a los cambios)
-- ============================================================

CREATE INDEX "fk_Producto_CategoriaProducto1_idx"
  ON "Producto" ("CategoriaProducto_idCategoriaProducto");

CREATE INDEX "fk_CategoriaProducto_Catalogo1_idx"
  ON "CategoriaProducto" ("Catalogo_idCatalogo");

CREATE INDEX "fk_CuentaUsuario_Administrador1_idx"
  ON "CuentaUsuario" ("Administrador_idAdministrador");

CREATE INDEX "fk_CuentaUsuario_cliente1_idx"
  ON "CuentaUsuario" ("cliente_idCliente");

CREATE INDEX "fk_CarritoDeCompras_cliente1_idx"
  ON "CarritoDeCompras" ("cliente_idCliente");

CREATE INDEX "fk_LineaCarrito_Producto1_idx"
  ON "LineaCarrito" ("Producto_ID_Producto");

CREATE INDEX "fk_LineaProducto_CarritoDeCompras1_idx"
  ON "LineaCarrito" ("CarritoDeCompras_Productos");

CREATE INDEX "fk_Descuento_Producto1_idx"
  ON "Descuento" ("Producto_ID_Producto");

-- ELIMINADO: fk_OrdenCompra_DetalleDeOrdenCompra1_idx
CREATE INDEX "fk_OrdenCompra_DetalleDeEnvio1_idx"
  ON "OrdenCompra" ("DetalleDeEnvio_id_DetalleEnvio");

CREATE INDEX "fk_ComprobantePago_OrdenCompra1_idx"
  ON "ComprobantePago" ("OrdenCompra_IdPedido");

CREATE INDEX "fk_LineaComprobantePago_ComprobantePago1_idx"
  ON "LineaComprobantePago" ("ComprobantePago_idComprobante");

CREATE INDEX "fk_LineaOrdenCompra_OrdenCompra1_idx"
  ON "LineaOrdenCompra" ("OrdenCompra_IdPedido");

CREATE INDEX "fk_LineaOrdenCompra_CarritoDeCompras1_idx"
  ON "LineaOrdenCompra" ("CarritoDeCompras_Id");

CREATE INDEX "fk_CategoriaProducto_has_CategoriaProducto1_idx"
  ON "CategoriaProducto_has_CategoriaProducto" ("CategoriaProducto_idCategoriaProducto");

-- ============================================================
-- Claves foraneas (solo a tablas existentes)
-- ============================================================

ALTER TABLE "Producto"
  ADD CONSTRAINT "fk_Producto_CategoriaProducto1"
  FOREIGN KEY ("CategoriaProducto_idCategoriaProducto")
  REFERENCES "CategoriaProducto" ("idCategoriaProducto");

ALTER TABLE "CuentaUsuario"
  ADD CONSTRAINT "fk_CuentaUsuario_Administrador1"
  FOREIGN KEY ("Administrador_idAdministrador")
  REFERENCES "Administrador" ("idAdministrador");

ALTER TABLE "CuentaUsuario"
  ADD CONSTRAINT "fk_CuentaUsuario_cliente1"
  FOREIGN KEY ("cliente_idCliente")
  REFERENCES "cliente" ("idCliente");

ALTER TABLE "CarritoDeCompras"
  ADD CONSTRAINT "fk_CarritoDeCompras_cliente1"
  FOREIGN KEY ("cliente_idCliente")
  REFERENCES "cliente" ("idCliente");

ALTER TABLE "LineaCarrito"
  ADD CONSTRAINT "fk_LineaCarrito_Producto1"
  FOREIGN KEY ("Producto_ID_Producto")
  REFERENCES "Producto" ("ID_Producto");

ALTER TABLE "LineaCarrito"
  ADD CONSTRAINT "fk_LineaProducto_CarritoDeCompras1"
  FOREIGN KEY ("CarritoDeCompras_Productos")
  REFERENCES "CarritoDeCompras" ("Id_CarritoDeCompras");

ALTER TABLE "Descuento"
  ADD CONSTRAINT "fk_Descuento_Producto1"
  FOREIGN KEY ("Producto_ID_Producto")
  REFERENCES "Producto" ("ID_Producto");

ALTER TABLE "OrdenCompra"
  ADD CONSTRAINT "fk_OrdenCompra_DetalleDeEnvio1"
  FOREIGN KEY ("DetalleDeEnvio_id_DetalleEnvio")
  REFERENCES "DetalleDeEnvio" ("id_DetalleEnvio");

ALTER TABLE "ComprobantePago"
  ADD CONSTRAINT "fk_ComprobantePago_OrdenCompra1"
  FOREIGN KEY ("OrdenCompra_IdPedido")
  REFERENCES "OrdenCompra" ("IdPedido");

ALTER TABLE "LineaComprobantePago"
  ADD CONSTRAINT "fk_LineaComprobantePago_ComprobantePago1"
  FOREIGN KEY ("ComprobantePago_idComprobante")
  REFERENCES "ComprobantePago" ("idComprobante");

ALTER TABLE "LineaOrdenCompra"
  ADD CONSTRAINT "fk_LineaOrdenCompra_OrdenCompra1"
  FOREIGN KEY ("OrdenCompra_IdPedido")
  REFERENCES "OrdenCompra" ("IdPedido");

ALTER TABLE "LineaOrdenCompra"
  ADD CONSTRAINT "fk_LineaOrdenCompra_CarritoDeCompras1"
  FOREIGN KEY ("CarritoDeCompras_Id")
  REFERENCES "CarritoDeCompras" ("Id_CarritoDeCompras");

ALTER TABLE "CategoriaProducto_has_CategoriaProducto"
  ADD CONSTRAINT "fk_CategoriaProducto_has_CategoriaProducto1"
  FOREIGN KEY ("CategoriaProducto_idCategoriaProducto")
  REFERENCES "CategoriaProducto" ("idCategoriaProducto");

ALTER TABLE "CategoriaProducto_has_CategoriaProducto"
  ADD CONSTRAINT "fk_CategoriaProducto_has_CategoriaProducto1_ref"
  FOREIGN KEY ("CategoriaProducto_idCategoriaProducto1")
  REFERENCES "CategoriaProducto" ("idCategoriaProducto");
