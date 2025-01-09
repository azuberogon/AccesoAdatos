Create database UT3AccesoAdatosHibernetEmpresa;
use UT3AccesoAdatosHibernetEmpresa;
-- Crear la tabla cliente
CREATE TABLE cliente (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido1 VARCHAR(100) NOT NULL,
    apellido2 VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    categoria INT UNSIGNED NOT NULL
);

-- Crear la tabla comercial
CREATE TABLE comercial (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido1 VARCHAR(100) NOT NULL,
    apellido2 VARCHAR(100) NOT NULL,
    comision FLOAT NOT NULL
);

-- Crear la tabla pedido
CREATE TABLE pedido (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    total DOUBLE NOT NULL,
    fecha DATE NOT NULL,
    id_cliente INT UNSIGNED NOT NULL,
    id_comercial INT UNSIGNED NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id),
    FOREIGN KEY (id_comercial) REFERENCES comercial(id)
);
