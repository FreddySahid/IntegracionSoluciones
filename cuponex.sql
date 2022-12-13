-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.31 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.2.0.6576
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para cuponex
CREATE DATABASE IF NOT EXISTS `cuponex` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cuponex`;

-- Volcando estructura para tabla cuponex.categoria
CREATE TABLE IF NOT EXISTS `categoria` (
  `idCategoria` int NOT NULL AUTO_INCREMENT,
  `categoria` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idCategoria`),
  UNIQUE KEY `categoria` (`categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla cuponex.categoria: ~2 rows (aproximadamente)
INSERT INTO `categoria` (`idCategoria`, `categoria`) VALUES
	(1, 'Farmacias'),
	(2, 'Restaurantes'),
	(3, 'Vinos y licores');

-- Volcando estructura para tabla cuponex.empresa
CREATE TABLE IF NOT EXISTS `empresa` (
  `idEmpresa` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL DEFAULT '',
  `nombreComercial` varchar(50) NOT NULL DEFAULT '',
  `nombreRepresentante` varchar(50) NOT NULL DEFAULT '',
  `correoEmpresa` varchar(50) NOT NULL DEFAULT '',
  `direccionCalle` varchar(50) NOT NULL DEFAULT '',
  `direccionNumero` int NOT NULL DEFAULT '0',
  `cp` int DEFAULT NULL,
  `ciudad` varchar(50) NOT NULL DEFAULT '0',
  `telefono` varchar(50) NOT NULL DEFAULT '0',
  `sitioWeb` varchar(50) DEFAULT '0',
  `rfc` varchar(13) DEFAULT '0',
  `idEstado` int DEFAULT NULL,
  `numSucursales` int DEFAULT NULL,
  PRIMARY KEY (`idEmpresa`),
  UNIQUE KEY `correoEmpresa` (`correoEmpresa`),
  UNIQUE KEY `rfc` (`rfc`),
  KEY `estadoEmpresa` (`idEstado`) USING BTREE,
  CONSTRAINT `estadoEmpresa` FOREIGN KEY (`idEstado`) REFERENCES `estado` (`idEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla cuponex.empresa: ~3 rows (aproximadamente)
INSERT INTO `empresa` (`idEmpresa`, `nombre`, `nombreComercial`, `nombreRepresentante`, `correoEmpresa`, `direccionCalle`, `direccionNumero`, `cp`, `ciudad`, `telefono`, `sitioWeb`, `rfc`, `idEstado`, `numSucursales`) VALUES
	(1, 'DaryQueen SA', 'DaryQueen', 'Warren Buffet', 'Dary@Queen', 'Miguel Avila Camacho', 21, 91150, 'Xalapa', '2284103051', 'DaryQueen.com', 'DC', 1, 2),
	(2, 'HomeDepotSA', 'The Home Depot', 'Señor Depot', 'Home@Depot', 'Animas', 22, 91155, 'Xalapa', '2299667253', 'theHomeDepot.com', 'THD000234FR2', 1, 0),
	(5, 'NIKESA', 'Nike', 'Señor nike', 'Just@DoIt', 'Animas', 22, 91155, 'Xalapa', '2299667253', 'nike.com', 'THD00023jdhs', 1, 4);

-- Volcando estructura para tabla cuponex.estado
CREATE TABLE IF NOT EXISTS `estado` (
  `idEstado` int NOT NULL,
  `estado` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla cuponex.estado: ~2 rows (aproximadamente)
INSERT INTO `estado` (`idEstado`, `estado`) VALUES
	(1, 'activo'),
	(2, 'inactivo');

-- Volcando estructura para tabla cuponex.promocion
CREATE TABLE IF NOT EXISTS `promocion` (
  `idPromocion` int NOT NULL AUTO_INCREMENT,
  `nombrePromocion` varchar(50) NOT NULL DEFAULT '',
  `foto` blob,
  `descripcion` varchar(50) NOT NULL DEFAULT '',
  `fechaInicioPromocion` date NOT NULL,
  `fechaFinPromocion` date NOT NULL,
  `idTipoPromocion` int NOT NULL DEFAULT '0',
  `porcentajeDescuento` double DEFAULT '0',
  `costo` double NOT NULL DEFAULT '0',
  `categoria` int NOT NULL DEFAULT '0',
  `idEstado` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`idPromocion`),
  KEY `categoriaPromocion` (`categoria`),
  KEY `estadoPromocion` (`idEstado`) USING BTREE,
  KEY `tipoPromocion` (`idTipoPromocion`) USING BTREE,
  CONSTRAINT `categoriaPromocion` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`idCategoria`),
  CONSTRAINT `estadoPromo` FOREIGN KEY (`idEstado`) REFERENCES `estado` (`idEstado`),
  CONSTRAINT `tipoPromo` FOREIGN KEY (`idPromocion`) REFERENCES `tipopromocion` (`idTipoPromocion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla cuponex.promocion: ~0 rows (aproximadamente)

-- Volcando estructura para tabla cuponex.sucursal
CREATE TABLE IF NOT EXISTS `sucursal` (
  `idSucursal` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL DEFAULT '',
  `direccionCalle` varchar(50) NOT NULL DEFAULT '',
  `direccionNumero` int NOT NULL DEFAULT '0',
  `cp` int NOT NULL DEFAULT '0',
  `colonia` varchar(50) NOT NULL DEFAULT '0',
  `ciudad` varchar(50) NOT NULL DEFAULT '0',
  `telefono` varchar(50) NOT NULL DEFAULT '0',
  `latitud` double NOT NULL DEFAULT '0',
  `longitud` double NOT NULL DEFAULT '0',
  `nombreEncargado` varchar(50) NOT NULL DEFAULT '0',
  `idEmpresa` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`idSucursal`),
  KEY `empresaSucursal` (`idEmpresa`),
  CONSTRAINT `empresaSucursal` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla cuponex.sucursal: ~6 rows (aproximadamente)
INSERT INTO `sucursal` (`idSucursal`, `nombre`, `direccionCalle`, `direccionNumero`, `cp`, `colonia`, `ciudad`, `telefono`, `latitud`, `longitud`, `nombreEncargado`, `idEmpresa`) VALUES
	(1, 'DaryQueen1', 'siempreviva', 21, 91022, 'Hidalgo', 'Xalapa', '2284103066', 23, 23, 'Carlos Alberto Gomez Mota', 1),
	(3, 'DaryQueen3', 'LAzaro Cardenas', 221, 98371, 'Casa Blanca', 'Xalapa', '2288992277', 34, 21, 'Carlos López Arguelles', 1),
	(4, 'NikeNuevo', 'Americas', 1, 12567, 'Manantiales', 'Xalapa', '228374653', 11.2, 234.21, 'Lebrom James', 5),
	(5, 'NikeXalapa', 'Ruiz Cortinez', 4, 99, 'Casa Blanca', 'Xalapa', '9116098472', 45.6, 847.3, 'Michael Jordan', 5),
	(6, 'NikeCDMX', 'Ruiz Cortinez', 4, 99, 'Casa Blanca', 'Xalapa', '9116098472', 45.6, 847.3, 'Kevin Duran', 5),
	(9, 'Nikenew', '12 de diciembre', 11, 11234, 'Rafael', 'Puebla', '22846281', 12, 13, 'CarlosNike jr.', 5);

-- Volcando estructura para tabla cuponex.sucursalpromocion
CREATE TABLE IF NOT EXISTS `sucursalpromocion` (
  `idPromocion` int NOT NULL,
  `idSucursal` int NOT NULL,
  KEY `sucursalpromo` (`idSucursal`),
  KEY `promosucursal` (`idPromocion`),
  CONSTRAINT `promosucursal` FOREIGN KEY (`idPromocion`) REFERENCES `promocion` (`idPromocion`),
  CONSTRAINT `sucursalpromo` FOREIGN KEY (`idSucursal`) REFERENCES `sucursal` (`idSucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla cuponex.sucursalpromocion: ~0 rows (aproximadamente)

-- Volcando estructura para tabla cuponex.tipopromocion
CREATE TABLE IF NOT EXISTS `tipopromocion` (
  `idTipoPromocion` int NOT NULL AUTO_INCREMENT,
  `tipoPromocion` varchar(50) NOT NULL,
  PRIMARY KEY (`idTipoPromocion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla cuponex.tipopromocion: ~2 rows (aproximadamente)
INSERT INTO `tipopromocion` (`idTipoPromocion`, `tipoPromocion`) VALUES
	(1, 'Descuento'),
	(2, 'Rebaja');

-- Volcando estructura para tabla cuponex.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL DEFAULT '',
  `apellidoPaterno` varchar(50) NOT NULL DEFAULT '',
  `apellidoMaterno` varchar(50) NOT NULL DEFAULT '',
  `correo` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla cuponex.usuario: ~4 rows (aproximadamente)
INSERT INTO `usuario` (`idUsuario`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `correo`, `password`) VALUES
	(1, 'Freddy', 'Sahid', 'Cuervo', 'freddy@cuervo', 'qwerty'),
	(2, 'Freddy', 'Sahid', 'Cuervo', 'freddy@sahid', 'qwerty'),
	(4, 'freddy', 'Cuervo', 'Sanchez', 'freddy@sanchez', 'qwerty'),
	(5, 'Carlos', 'Sanchez', 'Lopez', 'Carlos@Lopez', 'qwerty');

-- Volcando estructura para tabla cuponex.usuariomovil
CREATE TABLE IF NOT EXISTS `usuariomovil` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL DEFAULT '',
  `apellidoPaterno` varchar(50) NOT NULL DEFAULT '',
  `apellidoMaterno` varchar(50) NOT NULL DEFAULT '',
  `telefono` varchar(20) NOT NULL DEFAULT '',
  `correo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `direccionCalle` varchar(50) NOT NULL DEFAULT '',
  `direccionNumero` int NOT NULL DEFAULT '0',
  `fechaNacimiento` date NOT NULL,
  `password` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla cuponex.usuariomovil: ~0 rows (aproximadamente)

-- Volcando estructura para disparador cuponex.restarSucursal
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `restarSucursal` AFTER DELETE ON `sucursal` FOR EACH ROW BEGIN
    update empresa SET numSucursales = numSucursales - 1
    WHERE idEmpresa = OLD.idEmpresa;  
  END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador cuponex.sumarSucursales
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `sumarSucursales` AFTER INSERT ON `sucursal` FOR EACH ROW BEGIN
    update empresa SET numSucursales = numSucursales + 1
    WHERE idEmpresa = NEW.idEmpresa;  
  END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
