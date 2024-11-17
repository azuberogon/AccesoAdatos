-- Borro la tabla facturas si existe para que el script funcione bien siempre que se ejecute
DROP TABLE IF EXISTS facturas;

-- Crear la tabla facturas
create table facturas(
	codigo integer primary key,
    destinatario varchar(90) not null,
    cuenta integer not null,
    importe decimal(10,2) not null,
    fecha_hora datetime not null unique
);

-- Insertar los datos
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


