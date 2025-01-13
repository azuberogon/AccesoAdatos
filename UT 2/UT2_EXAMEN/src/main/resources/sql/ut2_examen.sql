DROP SCHEMA IF EXISTS `ut2_examen`;
CREATE SCHEMA `ut2_examen` DEFAULT CHARACTER SET utf8mb4 ;
USE ut2_examen;

-- Creación de la tabla Cliente
CREATE TABLE Cliente (
    ClienteID INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Apellidos VARCHAR(50),
    F_nacimiento DATE
);

-- Creación de la tabla Historia
CREATE TABLE Historia (
    HistoriaID INT PRIMARY KEY AUTO_INCREMENT,
    Titulo VARCHAR(100) NOT NULL,
    Descripcion TEXT
);

-- Creación de la tabla Videojuego
CREATE TABLE Videojuego (
    VideojuegoID INT PRIMARY KEY AUTO_INCREMENT,
    Titulo VARCHAR(100) NOT NULL,
    Plataforma VARCHAR(50),
    Anio_lanzamiento INT,
    Precio DECIMAL(10, 2),
    Fecha_actualizacion DATE,
    Disponible BOOLEAN NOT NULL,
    HistoriaID INT NOT NULL,
    FOREIGN KEY (HistoriaID) REFERENCES Historia(HistoriaID)
);

-- Creación de la tabla Pedido
CREATE TABLE Pedido (
    PedidoID INT PRIMARY KEY AUTO_INCREMENT,
    ClienteID INT NOT NULL,
    Fecha DATE,
    FOREIGN KEY (ClienteID) REFERENCES Cliente(ClienteID)
);

-- Creación de la tabla Contiene (tabla intermedia para la relación M:N)
CREATE TABLE Contiene (
    PedidoID INT,
    VideojuegoID INT,
    Cantidad INT,
    PRIMARY KEY (PedidoID, VideojuegoID),
    FOREIGN KEY (PedidoID) REFERENCES Pedido(PedidoID) ON DELETE CASCADE,
    FOREIGN KEY (VideojuegoID) REFERENCES Videojuego(VideojuegoID)
);

-- Inserción de datos en la tabla Cliente
INSERT INTO Cliente (Nombre, Apellidos, F_nacimiento) VALUES
    ('Juan', 'Pérez', '1990-05-12'),
    ('Ana', 'García', '1985-07-23'),
    ('Luis', 'Martínez', '1992-02-15');

-- Inserción de datos en la tabla Historia
INSERT INTO Historia (Titulo, Descripcion) VALUES
    ('Salvar a la princesa', 'Bowser ha raptado a la princesa y Mario intentará rescatarla por todo el mundo.'),
    ('Salvar Hirule', 'Ganondorf ha raptado a la princesa y se ha hecho señor de Hirule. Link tratará de salvarlos a todos'),
    ('Mundo abierto', 'Se libre y haz lo que te plazca. Puedes construir o destruir mientras otros intentan matarte.');

-- Inserción de datos en la tabla Videojuego
INSERT INTO Videojuego (Titulo, Plataforma, Anio_lanzamiento, Precio, Fecha_actualizacion, Disponible, HistoriaID) VALUES
    ('Super Mario Bros', 'Nintendo', 1985, 29.99, '2024-10-01', TRUE, 1),
    ('The Legend of Zelda', 'Nintendo', 1986, 49.99, '2024-09-15', TRUE, 2),
    ('Minecraft', 'PC', 2011, 19.99, '2024-08-30', TRUE, 3);

-- Inserción de datos en la tabla Pedido
INSERT INTO Pedido (ClienteID, Fecha) VALUES
    (1, '2024-09-15'),
    (2, '2024-09-18'),
    (3, '2024-09-20');

-- Inserción de datos en la tabla Contiene (relación M:N)
INSERT INTO Contiene (PedidoID, VideojuegoID, Cantidad) VALUES
    (1, 1, 2),
    (1, 2, 1),
    (2, 3, 1),
    (3, 2, 3);