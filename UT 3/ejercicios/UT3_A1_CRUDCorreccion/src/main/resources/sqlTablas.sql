create database bdgestionventas;
use bdgestionventas;


CREATE TABLE Cliente (
    clienteID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido1 VARCHAR(100) NOT NULL,
    apellido2 VARCHAR(100),
    ciudad VARCHAR(100),
    categoria INT UNSIGNED
);

CREATE TABLE Comercial (
    comercialID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido1 VARCHAR(100) NOT NULL,
    apellido2 VARCHAR(100),
    comision FLOAT
);


CREATE TABLE Pedido (
    pedidoID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    total DOUBLE NOT NULL,
    fecha DATE
);

INSERT INTO Cliente (nombre, apellido1, apellido2, ciudad, categoria) VALUES
('Juan', 'Pérez', 'González', 'Madrid', 1),
('Ana', 'Martínez', 'López', 'Barcelona', 2),
('Luis', 'Fernández', NULL, 'Valencia', 1),
('María', 'Sánchez', 'Ramírez', 'Sevilla', 3);

INSERT INTO Comercial (nombre, apellido1, apellido2, comision) VALUES
('Carlos', 'García', 'Torres', 0.10),
('Laura', 'Hernández', NULL, 0.15),
('Javier', 'Jiménez', 'Morales', 0.12),
('Elena', 'Romero', 'Cruz', 0.08);
INSERT INTO Pedido (total, fecha) VALUES
(150.75, '2023-10-01'),
(200.50, '2023-10-02'),
(300.00, '2023-10-03'),
(125.25, '2023-10-04');