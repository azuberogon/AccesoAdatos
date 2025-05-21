/********************************************************************
*********************************************************************
							BD Series
*********************************************************************
********************************************************************/
DROP SCHEMA IF EXISTS bdseries;
create database bdseries;
use bdseries;

CREATE TABLE serie (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(100) NOT NULL,
  anio INT NOT NULL,
  num_temporadas INT,
  valoracion DECIMAL(2,1) NOT NULL DEFAULT 0
  );
  
create table capitulo(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  num INT NOT NULL,
  titulo VARCHAR(128) NOT NULL,
  fecha_emision DATE,
  temporada INT,
  id_serie INT NOT NULL,
  CONSTRAINT FOREIGN KEY (id_serie) REFERENCES serie(id)
);

INSERT INTO serie(titulo, anio, num_temporadas, valoracion) VALUES ('One Piece', 1999, 21, '4.8');
INSERT INTO serie(titulo, anio, num_temporadas, valoracion) VALUES ('Solo Leveling', 2024, 2, '4.9');
INSERT INTO serie(titulo, anio, num_temporadas, valoracion) VALUES ('Los diarios de la boticaria', 2023, 2, '4.9');

INSERT INTO capitulo(num, titulo, fecha_emision, temporada, id_serie) VALUES (1, '¡Soy Luffy! ¡El hombre que se convertirá en el Rey de los Piratas', '1999-10-20', 1, 1);
INSERT INTO capitulo(num, titulo, fecha_emision, temporada, id_serie) VALUES (2, '¡Aparece el gran espadachín! El Cazador de Piratas, Roronoa Zoro', '1999-11-17', 1, 1);
INSERT INTO capitulo(num, titulo, fecha_emision, temporada, id_serie) VALUES (4, '¿Eres feliz? ¡La médica a la que llaman "bruja"!', '2001-09-16', 3, 1);
INSERT INTO capitulo(num, titulo, fecha_emision, temporada, id_serie) VALUES (1, 'El regreso a casa de Sanji. ¡Al territorio de Big Mom!', '2017-04-09', 1, 1);

INSERT INTO capitulo(num, titulo, fecha_emision, temporada, id_serie) VALUES (1, 'Ya estoy acostumbrado', '2024-01-07', 1, 2);
INSERT INTO capitulo(num, titulo, fecha_emision, temporada, id_serie) VALUES (2, 'Si tuviera otra oportunidad', '2024-01-14', 1, 2);

INSERT INTO capitulo(num, titulo, fecha_emision, temporada, id_serie) VALUES (1, 'Mao Mao', '2023-10-22', 1, 3);
INSERT INTO capitulo(num, titulo, fecha_emision, temporada, id_serie) VALUES (2, 'La boticaria distante', '2023-10-22', 1, 3);
INSERT INTO capitulo(num, titulo, fecha_emision, temporada, id_serie) VALUES (3, 'Mao y Mao', '2025-01-10', 2, 3);
