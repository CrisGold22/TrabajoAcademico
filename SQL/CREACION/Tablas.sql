USE REDCOM;



DROP TABLE IF EXISTS Administrador;
DROP TABLE IF EXISTS LineaOrdenCompra;
DROP TABLE IF EXISTS DetalleDeEnvio;
DROP TABLE IF EXISTS OrdenCompra;
DROP TABLE IF EXISTS LineaCarrito;
DROP TABLE IF EXISTS CarritoDeCompras;
DROP TABLE IF EXISTS Descuento;
DROP TABLE IF EXISTS Producto;
DROP TABLE IF EXISTS CategoriaProducto_has_CategoriaProducto;
DROP TABLE IF EXISTS CategoriaProducto;
DROP TABLE IF EXISTS Empresa;
DROP TABLE IF EXISTS Cliente;
DROP TABLE IF EXISTS CuentaUsuario;





-- Tabla CuentaUsuario

CREATE TABLE IF NOT EXISTS CuentaUsuario (
    idCuentaUsuario INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    userName VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    activo TINYINT NOT NULL DEFAULT 1, 
    correo  VARCHAR(150) NOT NULL UNIQUE, 
    reset_token VARCHAR(100),
    reset_token_expira  timestamp
);

-- Tabla Cliente

CREATE TABLE IF NOT EXISTS Cliente (
  idCliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  lineaCredito DOUBLE NOT NULL,
  categoria VARCHAR(45) NOT NULL,
  numeroDePedido_Historico INT NOT NULL DEFAULT 0,
  numeroDePedido_MensualPro INT NOT NULL,
  dni VARCHAR(45) NOT NULL UNIQUE,
  nombre VARCHAR(45) NOT NULL,
  apellidoPaterno VARCHAR(45) NOT NULL,
  apellidoMaterno VARCHAR(45) NOT NULL,
  genero VARCHAR(45) NOT NULL,
  fechaNacimiento DATETIME NOT NULL,
  telefono VARCHAR(20) NOT NULL ,
  activo TINYINT NOT NULL DEFAULT 1,
  idCuentaUsuario INT NOT NULL
);

-- Tabla Empresa

CREATE TABLE IF NOT EXISTS Empresa (
    idEmpresa INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ruc VARCHAR(11) NOT NULL UNIQUE,
    razonSocial VARCHAR(200) NOT NULL,
    direccionFiscal VARCHAR(255) NOT NULL,
    departamento VARCHAR(100) NOT NULL,
    provincia VARCHAR(100) NOT NULL,
    distrito VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    activo TINYINT DEFAULT 1,
    codigoPostal VARCHAR(10) NOT NULL,
    cliente_idCliente INT NOT NULL
);

-- Tabla Administrador

CREATE TABLE IF NOT EXISTS Administrador (
  idAdministrador INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  cargo VARCHAR(45) NOT NULL,
  jerarquia VARCHAR(45) NOT NULL,
  dni VARCHAR(45) NOT NULL UNIQUE,
  nombre VARCHAR(45) NOT NULL,
  apellidoPaterno VARCHAR(45) NOT NULL,
  apellidoMaterno VARCHAR(45) NOT NULL,
  genero VARCHAR(45) NOT NULL,
  fechaNacimiento DATETIME NOT NULL,
  telefono VARCHAR(20) NOT NULL,
  sueldo DOUBLE NOT NULL,
  activo TINYINT NOT NULL DEFAULT 1,
  idCuentaUsuario INT NOT NULL
);

-- Tabla Categoria Producto

CREATE TABLE IF NOT EXISTS CategoriaProducto (
  idCategoriaProducto INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombreCategoria VARCHAR(45) NOT NULL,
  descripcion VARCHAR(45) NOT NULL,
  idCategoriaPadre INT,
  activo TINYINT DEFAULT 1
);

-- Tabla CategoriaProductaHasCategoriaProducto

CREATE TABLE if not exists CategoriaProducto_has_CategoriaProducto (
  categoriaProducto_idCategoriaProducto INT NOT NULL,
  categoriaProducto_idCategoriaProducto1 INT NOT NULL,
  PRIMARY KEY (CategoriaProducto_idCategoriaProducto, CategoriaProducto_idCategoriaProducto1)
);

-- Tabla Producto

CREATE TABLE IF NOT EXISTS Producto (
  id_Producto INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(45) NOT NULL,
  sku VARCHAR(45) NOT NULL UNIQUE,
  descripcion VARCHAR(45) NOT NULL,
  precioRegular DOUBLE NOT NULL,
  precioAlMayor DOUBLE NOT NULL,
  unidadDeMedida VARCHAR(45) NOT NULL,
  stockDisponible INT NOT NULL,
  stockMaximo INT NOT NULL,
  activo TINYINT NOT NULL DEFAULT 1,
  marca VARCHAR(45) NOT NULL,
  categoriaProducto_idCategoriaProducto INT NOT NULL
);

-- Tabla Descuento

CREATE TABLE IF NOT EXISTS Descuento (
  idDescuento INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  precioPorVolumen DOUBLE NOT NULL,
  cantidadMax INT,
  cantidadMin INT,
  activo TINYINT DEFAULT 1,
  producto_ID_Producto INT NOT NULL UNIQUE
);

-- Tabla CarritoCompras

CREATE TABLE IF NOT EXISTS CarritoDeCompras (
  id_CarritoDeCompras INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  subtotal DOUBLE  ,
  estado VARCHAR(45) NOT NULL,
  descuento DOUBLE  ,
  montoFinal DOUBLE,
  cliente_idCliente INT NOT NULL,
  activo TINYINT DEFAULT 1
);

-- Tabla Linea Carrito

CREATE TABLE if not exists LineaCarrito (
  idLineaCarrito INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  cantidad INT,
  precio DOUBLE,
  subTotal DOUBLE,
  carritoDeCompras_Productos INT NOT NULL,
  activo TINYINT DEFAULT 1,
  producto_ID_Producto INT NOT NULL
);


-- Tabla Orden Compra

CREATE TABLE IF NOT EXISTS OrdenCompra (
  idOrdenCompra INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  fechaCreacion DATETIME,
  total_parcial DOUBLE NOT NULL,
  total_final DOUBLE NOT NULL,
  descuentoTotal DOUBLE,
  estado VARCHAR(45),
  carrito_idCarrito INT NOT NULL,
  activo TINYINT DEFAULT 1,
  cliente_idCliente INT NOT NULL,
  empresa_idEmpresa INT NOT NULL
);

-- Tabla LineaOrdenCompra

CREATE TABLE IF NOT EXISTS LineaOrdenCompra (
  idLineaOrdenCompra INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  cantidad INT,  
  precio DOUBLE,
  subtotal DOUBLE,
  producto_ID_Producto INT NOT NULL,
  ordenCompra_IdPedido INT NOT NULL,
  activo TINYINT DEFAULT 1
);

-- Tabla Detalle Envio

CREATE TABLE IF NOT EXISTS DetalleDeEnvio (
  id_DetalleEnvio INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  descripcion VARCHAR(45) ,
  direccion VARCHAR(100) NOT NULL,
  distrito VARCHAR(60) NOT NULL,
  fechaEntrega DATETIME NOT NULL,
  horarioEntrega DATETIME ,
  ordenCompra_IdPedido INT NOT NULL,
  activo TINYINT DEFAULT 1
);

-- ============================================================
-- Claves foráneas
-- ============================================================

-- Tabla Cliente
ALTER TABLE Cliente 
ADD CONSTRAINT fk_Cliente_CuentaUsuario 
FOREIGN KEY (idCuentaUsuario) 
REFERENCES CuentaUsuario(idCuentaUsuario);

-- Tabla Empresa
ALTER TABLE Empresa 
ADD CONSTRAINT fk_Empresa_Cliente 
FOREIGN KEY (cliente_idCliente) 
REFERENCES Cliente(idCliente)
ON DELETE CASCADE
ON UPDATE CASCADE;

-- Tabla Administrador
ALTER TABLE Administrador 
ADD CONSTRAINT fk_Administrador_CuentaUsuario 
FOREIGN KEY (idCuentaUsuario) 
REFERENCES CuentaUsuario(idCuentaUsuario);

-- Tabla CategoriaProducto (RELACIÓN AUTORREFERENCIAL)
ALTER TABLE CategoriaProducto 
ADD CONSTRAINT fk_CategoriaProducto_Padre 
FOREIGN KEY (idCategoriaPadre) 
REFERENCES CategoriaProducto(idCategoriaProducto)
ON DELETE SET NULL
ON UPDATE CASCADE;

-- Tabla CategoriaProducto_has_CategoriaProducto
ALTER TABLE CategoriaProducto_has_CategoriaProducto 
ADD CONSTRAINT fk_CategoriaProducto_has_Categoria1 
FOREIGN KEY (categoriaProducto_idCategoriaProducto) 
REFERENCES CategoriaProducto(idCategoriaProducto)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE CategoriaProducto_has_CategoriaProducto 
ADD CONSTRAINT fk_CategoriaProducto_has_Categoria2 
FOREIGN KEY (categoriaProducto_idCategoriaProducto1) 
REFERENCES CategoriaProducto(idCategoriaProducto)
ON DELETE CASCADE
ON UPDATE CASCADE;

-- Tabla Producto
ALTER TABLE Producto 
ADD CONSTRAINT fk_Producto_Categoria 
FOREIGN KEY (categoriaProducto_idCategoriaProducto) 
REFERENCES CategoriaProducto(idCategoriaProducto)
ON DELETE CASCADE
ON UPDATE CASCADE;

-- Tabla Descuento
ALTER TABLE Descuento 
ADD CONSTRAINT fk_Descuento_Producto 
FOREIGN KEY (producto_ID_Producto) 
REFERENCES Producto(id_Producto)
ON DELETE CASCADE
ON UPDATE CASCADE;

-- Tabla CarritoDeCompras
ALTER TABLE CarritoDeCompras 
ADD CONSTRAINT fk_Carrito_Cliente 
FOREIGN KEY (cliente_idCliente) 
REFERENCES Cliente(idCliente)
ON DELETE CASCADE
ON UPDATE CASCADE;

-- Tabla LineaCarrito
ALTER TABLE LineaCarrito 
ADD CONSTRAINT fk_LineaCarrito_Carrito 
FOREIGN KEY (carritoDeCompras_Productos) 
REFERENCES CarritoDeCompras(id_CarritoDeCompras)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE LineaCarrito 
ADD CONSTRAINT fk_LineaCarrito_Producto 
FOREIGN KEY (producto_ID_Producto) 
REFERENCES Producto(id_Producto)
ON DELETE CASCADE
ON UPDATE CASCADE;

-- Tabla OrdenCompra
ALTER TABLE OrdenCompra 
ADD CONSTRAINT fk_OrdenCompra_Carrito 
FOREIGN KEY (carrito_idCarrito) 
REFERENCES CarritoDeCompras(id_CarritoDeCompras)
ON DELETE RESTRICT
ON UPDATE CASCADE;

ALTER TABLE OrdenCompra 
ADD CONSTRAINT fk_OrdenCompra_Cliente 
FOREIGN KEY (cliente_idCliente) 
REFERENCES Cliente(idCliente)
ON DELETE RESTRICT
ON UPDATE CASCADE;

ALTER TABLE OrdenCompra 
ADD CONSTRAINT fk_OrdenCompra_Empresa 
FOREIGN KEY (empresa_idEmpresa) 
REFERENCES Empresa(idEmpresa)
ON DELETE RESTRICT
ON UPDATE CASCADE;

-- Tabla LineaOrdenCompra
ALTER TABLE LineaOrdenCompra 
ADD CONSTRAINT fk_LineaOrdenCompra_Producto 
FOREIGN KEY (producto_ID_Producto) 
REFERENCES Producto(id_Producto)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE LineaOrdenCompra 
ADD CONSTRAINT fk_LineaOrdenCompra_OrdenCompra 
FOREIGN KEY (ordenCompra_IdPedido) 
REFERENCES OrdenCompra(idOrdenCompra)
ON DELETE CASCADE
ON UPDATE CASCADE;

-- Tabla DetalleDeEnvio
ALTER TABLE DetalleDeEnvio 
ADD CONSTRAINT fk_DetalleEnvio_OrdenCompra 
FOREIGN KEY (ordenCompra_IdPedido) 
REFERENCES OrdenCompra(idOrdenCompra)
ON DELETE CASCADE
ON UPDATE CASCADE;

