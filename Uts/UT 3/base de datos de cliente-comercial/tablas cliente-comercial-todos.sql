
create Database bdgestionventas2;
-- Tabla Producto
use  bdgestionventas;
CREATE TABLE Cliente (
    clienteID BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido1 VARCHAR(100) NOT NULL,
    apellido2 VARCHAR(100),
    ciudad VARCHAR(100),
    categoria BIGINT UNSIGNED
);
CREATE TABLE Comercial (
    comercialID BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido1 VARCHAR(100) NOT NULL,
    apellido2 VARCHAR(100),
    comision FLOAT
);
CREATE TABLE Producto (
    productoID BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL
);
CREATE TABLE Pedido (
    pedidoID BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    total DOUBLE NOT NULL,
    fecha DATE,
    clienteID BIGINT UNSIGNED NOT NULL,
    comercialID BIGINT UNSIGNED
  
); 
# FOREIGN KEY (clienteID) REFERENCES Cliente(clienteID),-->
# FOREIGN KEY (comercialID) REFERENCES Comercial(comercialID)
CREATE TABLE Contiene (
    contieneID BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    pedidoID BIGINT UNSIGNED NOT NULL,
    productoID BIGINT UNSIGNED NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (pedidoID) REFERENCES Pedido(pedidoID),
    FOREIGN KEY (productoID) REFERENCES Producto(productoID),
    UNIQUE KEY (pedidoID, productoID)  -- Para evitar duplicados en la relación
);