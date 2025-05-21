/********************************************************************
*********************************************************************
							BD Básicas
*********************************************************************
********************************************************************/
DROP SCHEMA IF EXISTS bdEmpleadosOficinas_Aitor;
create database bdEmpleadosOficinas_Aitor;
use bdBasicas;

create table empleados(
	dni varchar(9) primary key,
    nombre varchar(30) not null,
    apellidos varchar(60) not null,
    email varchar(60),
    telefono integer not null,
    sueldo decimal (10,2) not null,
    puesto varchar(30) not null
);

insert into empleados (DNI, nombre, apellidos, email, telefono, sueldo, puesto) values ('12345678A', 'Juan', 'López Pérez', 'jlope@navarra.es', 948111111, 1235.48, 'encargado');
insert into empleados (DNI, nombre, apellidos, email, telefono, sueldo, puesto) values ('98765432C', 'David', 'Herrero Sánchez', 'davidherresan@hotmail.es', 964851123, 1398.88, 'supervisor');
insert into empleados (DNI, nombre, apellidos, email, telefono, sueldo, puesto) values ('11111111V', 'Luis', 'Dorado Pérez', 'ldorado@gmail.com', 912587763, 2130.95, 'profesor');
insert into empleados (DNI, nombre, apellidos, email, telefono, sueldo, puesto) values ('35786521F', 'Ernesto', 'Sevilla', 'ernestosevilla@chanante.es', 917234685, 12395.27, 'actor');
insert into empleados (DNI, nombre, apellidos, email, telefono, sueldo, puesto) values ('98642531C', 'Alba', 'Etxeberría Luis', 'alba.etxe@gmail.com', 970256485, 1537.95, 'encargado');
insert into empleados (DNI, nombre, apellidos, email, telefono, sueldo, puesto) values ('67853427T', 'María', 'León Sánchez', 'maria.lsan@outlook.es', 920675428, 1747.76, 'supervisor');
insert into empleados (DNI, nombre, apellidos, email, telefono, sueldo, puesto) values ('75948264K', 'Vanessa', 'Pérez Melendro', 'vpm97@gmail.com', 948367589, 2037.67, 'profesor');
insert into empleados (DNI, nombre, apellidos, email, telefono, sueldo, puesto) values ('15862483L', 'Marta', 'González Ramos', 'martitagr@navarra.es', 979729709, 1673.17, 'operario');
insert into empleados (DNI, nombre, apellidos, email, telefono, sueldo, puesto) values ('65279423G', 'Fernando', 'Pastor Hernández', 'fphernan@hotmail.com', 986345761, 1268.76, 'operario');
insert into empleados (DNI, nombre, apellidos, email, telefono, sueldo, puesto) values ('75681263D', 'Pilar', 'Castro López', 'pcl83@gmail.com', 948715632, 1376.48, 'operario');
insert into empleados (DNI, nombre, apellidos, email, telefono, sueldo, puesto) values ('36218657H', 'Ramón', 'Henández Rojo', null, 948714282, 1396.46, 'operario');
insert into empleados (DNI, nombre, apellidos, email, telefono, sueldo, puesto) values ('32497256V', 'Pedro', 'Santos Ruiz', null, 948963432, 1338.67, 'operario');
insert into empleados (DNI, nombre, apellidos, email, telefono, sueldo, puesto) values ('38426851B', 'Laura', 'Garcés Sevilla', null, 948711515, 1349.57, 'operario');

create table libros(
	isbn integer primary key,
    titulo varchar(60) not null,
    tipo varchar (30) not null,
    autor varchar(90),
    precio decimal(8,2) not null
);

insert into libros (ISBN, titulo, tipo, autor, precio) values (542612578, 'El lazarillo de Tormes', 'novela', 'anónimo', 7.86);
insert into libros (ISBN, titulo, tipo, autor, precio) values (472617812, 'Sapiens: de animales a dioses', 'divulgación', 'Yuval Noah Harari', 19.95);
insert into libros (ISBN, titulo, tipo, autor, precio) values (431501467, 'Redes Locales', 'escolar', 'varios', 21.89);
insert into libros (ISBN, titulo, tipo, autor, precio) values (653723689, 'Pensar rápido, pensar despacio', 'divulgación', 'Daniel Kahneman', 23.49);
insert into libros (ISBN, titulo, tipo, autor, precio) values (734521667, 'Adiós al frío','novela','Elvira Sastre', 12.00);
insert into libros (ISBN, titulo, tipo, autor, precio) values (125789351, 'Aquitania', 'novela', 'Eva García Sáenz de Urturi', 21.90);
insert into libros (ISBN, titulo, tipo, autor, precio) values (705940145, 'La ciudad de vapor', 'novela', 'Carlos Ruiz Zafón', 17.90);
insert into libros (ISBN, titulo, tipo, autor, precio) values (365106701, 'La buena cocina', 'cocina', 'Karlos Arguiñano', 24.95);
insert into libros (ISBN, titulo, tipo, autor, precio) values (825430758, 'Una tierra prometida', 'biografico', 'Barack Obama', 32.90);
insert into libros (ISBN, titulo, tipo, autor, precio) values (326541968, 'El principito', 'novela', 'Antoine de Saint-Exupery', 6.95);
insert into libros (ISBN, titulo, tipo, autor, precio) values (936541869, 'Flamenca', 'novela', null, 7.95);
insert into libros (ISBN, titulo, tipo, autor, precio) values (917531860, 'Poema de Mio Cid', 'poesía', null, 11.15);
insert into libros (ISBN, titulo, tipo, autor, precio) values (996378927, 'Las mil y una noches', 'novela', null, 12.95);

create table facturas(
	codigo integer primary key,
    destinatario varchar(90) not null,
    cuenta integer not null,
    importe decimal(10,2) not null,
    fecha_hora datetime not null unique
);

insert into facturas (CODIGO, destinatario, cuenta, importe, fecha_hora) values (123456, 'Jhonny Silverhand', 75481256, 59.99, '2020-12-10 00:00:27');
insert into facturas (CODIGO, destinatario, cuenta, importe, fecha_hora) values (335902, 'Bolvar Fordragon', 15862842, 179.96, '2004-11-23 13:07:49');
insert into facturas (CODIGO, destinatario, cuenta, importe, fecha_hora) values (027792, 'Mark Zuckerberg', 75968423, 846.49, '2008-02-08 12:14:35');
insert into facturas (CODIGO, destinatario, cuenta, importe, fecha_hora) values (456123, 'Mark Hamill', 56348956, 59.99, '2007-05-25 17:15:47');
insert into facturas (CODIGO, destinatario, cuenta, importe, fecha_hora) values (246792, 'Nikola Tesla', 15862842, 23.75, '2005-08-31 19:57:36');
insert into facturas (CODIGO, destinatario, cuenta, importe, fecha_hora) values (354503, 'Bill Gates', 68452358, 139.83, '2009-03-25 08:09:12');
insert into facturas (CODIGO, destinatario, cuenta, importe, fecha_hora) values (578925, 'Alan Turing', 85325486, 658.75, '2015-06-15 18:37:24');
insert into facturas (CODIGO, destinatario, cuenta, importe, fecha_hora) values (487016, 'John Von Neumann', 28426684, 49.95, '2001-11-09 23:14:39');
insert into facturas (CODIGO, destinatario, cuenta, importe, fecha_hora) values (710349, 'Linus Torvalds', 86284266, 30.19, '2012-08-09 17:15:23');
insert into facturas (CODIGO, destinatario, cuenta, importe, fecha_hora) values (629430, 'Steve Wozniak', 62842668, 386.02, '2017-09-04 7:57:23');

