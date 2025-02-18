DROP DATABASE IF EXISTS `bdgestionventas`;
CREATE DATABASE `bdgestionventas`;
USE `bdgestionventas`;
CREATE TABLE `cliente` (
  `id` bigint NOT NULL PRIMARY KEY,
  `nombre` varchar(100) NOT NULL,
  `apellido1` varchar(100) NOT NULL,
  `apellido2` varchar(100),
  `ciudad` varchar(100)
) ;
CREATE TABLE `pedido` (
  `pedidoID` bigint NOT NULL PRIMARY KEY,
  `fecha` date DEFAULT NULL,
  `total` decimal(19,2) NOT NULL,
  `id_cliente` bigint NOT NULL,
  FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
) 
