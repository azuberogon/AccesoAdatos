DROP DATABASE IF EXISTS `bdgestionventas`;
CREATE DATABASE `bdgestionventas`;
USE `bdgestionventas`;
CREATE TABLE `cliente` (
  `id` bigint NOT NULL PRIMARY KEY,
  `nombre` varchar(100) NOT NULL,
  `apellido1` varchar(100) NOT NULL,
  `apellido2` varchar(100),
  `ciudad` varchar(100),
  `categoria` bigint
) ;

