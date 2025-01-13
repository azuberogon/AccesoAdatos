/********************************************************************
*********************************************************************
							BD Libros
*********************************************************************
********************************************************************/
-- Borro la tabla libros si existe para que el script funcione bien siempre que se ejecute
DROP TABLE IF EXISTS libros;
-- Crear la tabla facturas
create table libros(
	isbn integer primary key,
    titulo varchar(60) not null,
    genero varchar (30) not null,
    autor varchar(90),
    precio decimal(8,2) not null
);
-- Poblamos la tabla de datos
insert into libros (ISBN, titulo, genero, autor, precio) values (542612578, 'El lazarillo de Tormes', 'novela', 'anónimo', 7.86);
insert into libros (ISBN, titulo, genero, autor, precio) values (472617812, 'Sapiens: de animales a dioses', 'divulgación', 'Yuval Noah Harari', 19.95);
insert into libros (ISBN, titulo, genero, autor, precio) values (431501467, 'Redes Locales', 'escolar', 'varios', 21.89);
insert into libros (ISBN, titulo, genero, autor, precio) values (653723689, 'Pensar rápido, pensar despacio', 'divulgación', 'Daniel Kahneman', 23.49);
insert into libros (ISBN, titulo, genero, autor, precio) values (734521667, 'Adiós al frío','novela','Elvira Sastre', 12.00);
insert into libros (ISBN, titulo, genero, autor, precio) values (125789351, 'Aquitania', 'novela', 'Eva García Sáenz de Urturi', 21.90);
insert into libros (ISBN, titulo, genero, autor, precio) values (705940145, 'La ciudad de vapor', 'novela', 'Carlos Ruiz Zafón', 17.90);
insert into libros (ISBN, titulo, genero, autor, precio) values (365106701, 'La buena cocina', 'cocina', 'Karlos Arguiñano', 24.95);
insert into libros (ISBN, titulo, genero, autor, precio) values (825430758, 'Una tierra prometida', 'biografico', 'Barack Obama', 32.90);
insert into libros (ISBN, titulo, genero, autor, precio) values (326541968, 'El principito', 'novela', 'Antoine de Saint-Exupery', 6.95);
insert into libros (ISBN, titulo, genero, autor, precio) values (936541869, 'Flamenca', 'novela', null, 7.95);
insert into libros (ISBN, titulo, genero, autor, precio) values (917531860, 'Poema de Mio Cid', 'poesía', null, 11.15);
insert into libros (ISBN, titulo, genero, autor, precio) values (996378927, 'Las mil y una noches', 'novela', null, 12.95);

