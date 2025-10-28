USE REDCOM;


CREATE TABLE if not exists CategoriaProducto (
  idCategoriaProducto INT NOT NULL,
  NombreCategoria VARCHAR(45) NOT NULL,
  Descripcion VARCHAR(45) NOT NULL,
  Catalogo_idCatalogo INT,
  Activo TINYINT NOT NULL,
  PRIMARY KEY (idCategoriaProducto)
);

CREATE TABLE if not exists Producto (
  ID_Producto INT NOT NULL,
  Nombre VARCHAR(45) NOT NULL,
  SKU VARCHAR(45) NOT NULL,
  Descripcion VARCHAR(45) NOT NULL,
  precioRegular DOUBLE NOT NULL,
  precioAlMayor DOUBLE NOT NULL,
  UnidadDeMedida VARCHAR(45) NOT NULL,
  StockDisponible INT NOT NULL,
  StockMinimo INT NOT NULL,
  StockMaximo INT NOT NULL,
  Activo TINYINT NOT NULL,
  CategoriaProducto_idCategoriaProducto INT,
  PRIMARY KEY (ID_Producto)
);

CREATE TABLE if not exists cliente (
  lineaCredito DOUBLE NOT NULL,
  Categoria VARCHAR(45) NOT NULL,
  numeroDePedido_Historico INT NOT NULL,
  numeroDePedido_MensualPro INT NOT NULL,
  dni VARCHAR(45) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  apellidoPaterno VARCHAR(45) NOT NULL,
  apellidoMaterno VARCHAR(45) NOT NULL,
  genero VARCHAR(45) NOT NULL,
  fechaNacimiento DATETIME NOT NULL,
  telefono INT ,
  Activo TINYINT NOT NULL,
  idCliente INT NOT NULL,
  PRIMARY KEY (idCliente)
);

CREATE TABLE if not exists Administrador (
  idAdministrador INT NOT NULL,
  cargo VARCHAR(45) NOT NULL,
  rango VARCHAR(45) NOT NULL,
  dni VARCHAR(45) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  apellidoPaterno VARCHAR(45) NOT NULL,
  apellidoMaterno VARCHAR(45) NOT NULL,
  genero VARCHAR(45) NOT NULL,
  fechaNacimiento DATETIME NOT NULL,
  telefono INT,
  Sueldo DOUBLE NOT NULL,
  Activo TINYINT NOT NULL,
  PRIMARY KEY (idAdministrador)
);

CREATE TABLE if not exists CuentaUsuario (
  idCuentaUsuario INT NOT NULL,
  userName VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  Administrador_idAdministrador INT NULL,
  cliente_idCliente INT NULL,
  PRIMARY KEY (idCuentaUsuario)
);

CREATE TABLE if not exists CarritoDeCompras (
  Id_CarritoDeCompras INT  ,
  Total_Parcial DOUBLE  ,
  Estado VARCHAR(45) NOT NULL,
  total_con_descuento DOUBLE  ,
  cliente_idCliente INT,
  PRIMARY KEY (Id_CarritoDeCompras)
);

CREATE TABLE if not exists LineaCarrito (
  idLineaCarrito INT NOT NULL,
  cantidad INT,
  precioVolumen DOUBLE,
  subTotal DOUBLE,
  Producto_ID_Producto1 INT,
  CarritoDeCompras_Productos INT,
  activo TINYINT,
  Producto_ID_Producto INT,
  PRIMARY KEY (idLineaCarrito)
);

CREATE TABLE if not exists Descuento (
  idReglaPrecioVolumen INT NOT NULL,
  precioPorVolumen DOUBLE,
  cantidadMax INT,
  cantidadMin INT,
  Activo TINYINT,
  Producto_ID_Producto INT,
  PRIMARY KEY (idReglaPrecioVolumen)
);

CREATE TABLE if not exists DetalleDeEnvio (
  id_DetalleEnvio INT NOT NULL,
  descripcion VARCHAR(45) ,
  Direccion VARCHAR(100) NOT NULL,
  Distrito VARCHAR(60) NOT NULL,
  fechaEntrega DATETIME NOT NULL,
  horarioEntrega DATETIME ,
  PRIMARY KEY (id_DetalleEnvio)
);

CREATE TABLE if not exists OrdenCompra (
  IdPedido INT NOT NULL,
  FechaCreacion DATE,
  total_parcial DOUBLE,
  total_final DOUBLE,
  descuentoTotal DOUBLE,
  Estado VARCHAR(45),
  DetalleDeEnvio_id_DetalleEnvio INT,
  Activo TINYINT,
  PRIMARY KEY (IdPedido)
);

CREATE TABLE if not exists ComprobantePago (
  idComprobante INT NOT NULL,
  fechaEmision DATE,
  RUC INT,
  totalSinImpuestos DOUBLE,
  Impuesto DOUBLE,
  totalFinal DOUBLE,
  metodoPago VARCHAR(45),
  subTotal DOUBLE,
  activo TINYINT,
  OrdenCompra_IdPedido INT,
  PRIMARY KEY (idComprobante)
);

CREATE TABLE if not exists LineaComprobantePago (
  idLineaComprobantePago INT NOT NULL,
  montoPagado VARCHAR(45),
  montoImpuesto DOUBLE,
  activo TINYINT,
  ComprobantePago_idComprobante INT,
  codigo INT,
  cantidad INT,
  subtotal DOUBLE,
  PRIMARY KEY (idLineaComprobantePago)
);

CREATE TABLE if not exists LineaOrdenCompra (
  idLineaOrdenCompra INT NOT NULL,
  cantidad INT,
  precioUnitario DOUBLE,
  subtotal DOUBLE,
  Producto_ID_Producto INT,
  OrdenCompra_IdPedido INT,
  CarritoDeCompras_Id INT,
  activo TINYINT,
  PRIMARY KEY (idLineaOrdenCompra)
);

CREATE TABLE if not exists CategoriaProducto_has_CategoriaProducto (
  CategoriaProducto_idCategoriaProducto INT NOT NULL,
  CategoriaProducto_idCategoriaProducto1 INT NOT NULL,
  PRIMARY KEY (CategoriaProducto_idCategoriaProducto, CategoriaProducto_idCategoriaProducto1)
);

-- ============================================================
-- Indices
-- ============================================================

CREATE INDEX fk_Producto_CategoriaProducto1_idx 
  ON Producto (CategoriaProducto_idCategoriaProducto);

CREATE INDEX fk_CategoriaProducto_Catalogo1_idx 
  ON CategoriaProducto (Catalogo_idCatalogo);

CREATE INDEX fk_CuentaUsuario_Administrador1_idx 
  ON CuentaUsuario (Administrador_idAdministrador);

CREATE INDEX fk_CuentaUsuario_cliente1_idx 
  ON CuentaUsuario (cliente_idCliente);

CREATE INDEX fk_CarritoDeCompras_cliente1_idx 
  ON CarritoDeCompras (cliente_idCliente);

CREATE INDEX fk_LineaCarrito_Producto1_idx 
  ON LineaCarrito (Producto_ID_Producto);

CREATE INDEX fk_LineaProducto_CarritoDeCompras1_idx 
  ON LineaCarrito (CarritoDeCompras_Productos);

CREATE INDEX fk_Descuento_Producto1_idx 
  ON Descuento (Producto_ID_Producto);

CREATE INDEX fk_OrdenCompra_DetalleDeEnvio1_idx 
  ON OrdenCompra (DetalleDeEnvio_id_DetalleEnvio);

CREATE INDEX fk_ComprobantePago_OrdenCompra1_idx 
  ON ComprobantePago (OrdenCompra_IdPedido);

CREATE INDEX fk_LineaComprobantePago_ComprobantePago1_idx 
  ON LineaComprobantePago (ComprobantePago_idComprobante);

CREATE INDEX fk_LineaOrdenCompra_OrdenCompra1_idx 
  ON LineaOrdenCompra (OrdenCompra_IdPedido);

CREATE INDEX fk_LineaOrdenCompra_CarritoDeCompras1_idx 
  ON LineaOrdenCompra (CarritoDeCompras_Id);

CREATE INDEX fk_CategoriaProducto_has_CategoriaProducto1_idx 
  ON CategoriaProducto_has_CategoriaProducto (CategoriaProducto_idCategoriaProducto);

-- ============================================================
-- Claves foráneas
-- ============================================================

ALTER TABLE Producto
  ADD CONSTRAINT fk_Producto_CategoriaProducto1
  FOREIGN KEY (CategoriaProducto_idCategoriaProducto)
  REFERENCES CategoriaProducto (idCategoriaProducto);

ALTER TABLE CuentaUsuario
  ADD CONSTRAINT fk_CuentaUsuario_Administrador1
  FOREIGN KEY (Administrador_idAdministrador)
  REFERENCES Administrador (idAdministrador);

ALTER TABLE CuentaUsuario
  ADD CONSTRAINT fk_CuentaUsuario_cliente1
  FOREIGN KEY (cliente_idCliente)
  REFERENCES cliente (idCliente);

ALTER TABLE CarritoDeCompras
  ADD CONSTRAINT fk_CarritoDeCompras_cliente1
  FOREIGN KEY (cliente_idCliente)
  REFERENCES cliente (idCliente);

ALTER TABLE LineaCarrito
  ADD CONSTRAINT fk_LineaCarrito_Producto1
  FOREIGN KEY (Producto_ID_Producto)
  REFERENCES Producto (ID_Producto);

ALTER TABLE LineaCarrito
  ADD CONSTRAINT fk_LineaProducto_CarritoDeCompras1
  FOREIGN KEY (CarritoDeCompras_Productos)
  REFERENCES CarritoDeCompras (Id_CarritoDeCompras);

ALTER TABLE Descuento
  ADD CONSTRAINT fk_Descuento_Producto1
  FOREIGN KEY (Producto_ID_Producto)
  REFERENCES Producto (ID_Producto);

ALTER TABLE OrdenCompra
  ADD CONSTRAINT fk_OrdenCompra_DetalleDeEnvio1
  FOREIGN KEY (DetalleDeEnvio_id_DetalleEnvio)
  REFERENCES DetalleDeEnvio (id_DetalleEnvio);

ALTER TABLE ComprobantePago
  ADD CONSTRAINT fk_ComprobantePago_OrdenCompra1
  FOREIGN KEY (OrdenCompra_IdPedido)
  REFERENCES OrdenCompra (IdPedido);

ALTER TABLE LineaComprobantePago
  ADD CONSTRAINT fk_LineaComprobantePago_ComprobantePago1
  FOREIGN KEY (ComprobantePago_idComprobante)
  REFERENCES ComprobantePago (idComprobante);

ALTER TABLE LineaOrdenCompra
  ADD CONSTRAINT fk_LineaOrdenCompra_OrdenCompra1
  FOREIGN KEY (OrdenCompra_IdPedido)
  REFERENCES OrdenCompra (IdPedido);

ALTER TABLE LineaOrdenCompra
  ADD CONSTRAINT fk_LineaOrdenCompra_CarritoDeCompras1
  FOREIGN KEY (CarritoDeCompras_Id)
  REFERENCES CarritoDeCompras (Id_CarritoDeCompras);

ALTER TABLE CategoriaProducto_has_CategoriaProducto
  ADD CONSTRAINT fk_CategoriaProducto_has_CategoriaProducto1
  FOREIGN KEY (CategoriaProducto_idCategoriaProducto)
  REFERENCES CategoriaProducto (idCategoriaProducto);

ALTER TABLE CategoriaProducto_has_CategoriaProducto
  ADD CONSTRAINT fk_CategoriaProducto_has_CategoriaProducto1_ref
  FOREIGN KEY (CategoriaProducto_idCategoriaProducto1)
  REFERENCES CategoriaProducto (idCategoriaProducto);