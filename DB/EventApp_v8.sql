-- MySQL dump 10.13  Distrib 8.0.33, for Linux (x86_64)
--
-- Host: localhost    Database: eventapp
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `caterings`
--

DROP TABLE IF EXISTS `caterings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caterings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_empresa` int DEFAULT NULL,
  `menu` text CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `precio` float NOT NULL,
  `foto` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_catering_empresa` (`id_empresa`),
  CONSTRAINT `fk_catering_empresa` FOREIGN KEY (`id_empresa`) REFERENCES `empresas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caterings`
--

LOCK TABLES `caterings` WRITE;
/*!40000 ALTER TABLE `caterings` DISABLE KEYS */;
INSERT INTO `caterings` VALUES (3,5,'Menu Hamburguesa',14,'/imagesCaterings/hamburguesas.jpg'),(4,5,'Menu para Boda',50,'/imagesCaterings/menu_boda.jpg'),(5,7,'Menu Boda',57,'/imagesCaterings/menu_boda2.jpg'),(6,7,'Menu del día',16,'/imagesCaterings/menu_dia.jpg');
/*!40000 ALTER TABLE `caterings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cateringubicacionevento`
--

DROP TABLE IF EXISTS `cateringubicacionevento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cateringubicacionevento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fechahora` datetime NOT NULL,
  `id_catering` int DEFAULT NULL,
  `id_evento` int NOT NULL,
  `id_ubicacion` int NOT NULL,
  `id_decorado` int DEFAULT NULL,
  `id_ocio` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cue_evento` (`id_evento`),
  KEY `FK_cue_ubicacion` (`id_ubicacion`),
  KEY `FK_cue_decorado_idx` (`id_decorado`),
  KEY `FK_cue_catering` (`id_catering`),
  KEY `FK_cue_ocio_idx` (`id_ocio`),
  CONSTRAINT `FK_cue_catering` FOREIGN KEY (`id_catering`) REFERENCES `caterings` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cue_decorado` FOREIGN KEY (`id_decorado`) REFERENCES `decorados` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cue_evento` FOREIGN KEY (`id_evento`) REFERENCES `eventos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cue_ocio` FOREIGN KEY (`id_ocio`) REFERENCES `ocio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cue_ubicacion` FOREIGN KEY (`id_ubicacion`) REFERENCES `ubicaciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cateringubicacionevento`
--

LOCK TABLES `cateringubicacionevento` WRITE;
/*!40000 ALTER TABLE `cateringubicacionevento` DISABLE KEYS */;
INSERT INTO `cateringubicacionevento` VALUES (27,'2023-06-06 21:50:39',4,27,3,2,2),(28,'2023-06-06 22:37:31',6,28,6,5,3);
/*!40000 ALTER TABLE `cateringubicacionevento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `decorados`
--

DROP TABLE IF EXISTS `decorados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `decorados` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `descripcion` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `foto` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `precio` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id_empresa` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_decorados_empresa_idx` (`id_empresa`),
  CONSTRAINT `FK_decorados_empresa` FOREIGN KEY (`id_empresa`) REFERENCES `empresas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `decorados`
--

LOCK TABLES `decorados` WRITE;
/*!40000 ALTER TABLE `decorados` DISABLE KEYS */;
INSERT INTO `decorados` VALUES (2,'Decorado Boda','Decorado Floral para bodas.','/imagesDecorados/boda.jpeg','1500.0',5),(3,'Globos','Globos de plástico con helio.','/imagesDecorados/globos.jpg','50.0',5),(4,'Decorado De Bodas','Decorado Floral para bodas.','/imagesDecorados/decorado_boda5.jpg','1320.0',7),(5,'Focos de Luz Rotativos','Luces neon','/imagesDecorados/haces_luz.jpg','1200.0',7);
/*!40000 ALTER TABLE `decorados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direcciones`
--

DROP TABLE IF EXISTS `direcciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direcciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `calle` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `numero` varchar(5) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `ciudad` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `cp` varchar(5) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `provincia` varchar(45) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direcciones`
--

LOCK TABLES `direcciones` WRITE;
/*!40000 ALTER TABLE `direcciones` DISABLE KEYS */;
INSERT INTO `direcciones` VALUES (28,'De adminstrador','11','Puerto de Sagunto','46500','Valencia'),(29,'Moragas','23','Barcelona','08010','Barcelona'),(30,'Callao','13','Madrid','28001','Madrid'),(31,'Carrer raiola','35','Valencia','46001','Valencia'),(32,'Calle almudena','16','Sevilla','41001','Sevilla'),(33,'Calle el torrezno','23','Barcelona','08010','Barcelona'),(34,'la de atras','23','Gilet','46149','Valencia'),(35,'Carrer d\'en bot','34','Barcelona','08010','Barcelona'),(36,'Av. del Professor López Piñero','7','Valencia','45966','Valencia'),(37,' Carrer del Pintor Martí Girbés','17','El Palmar','46012','Valencia');
/*!40000 ALTER TABLE `direcciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresas`
--

DROP TABLE IF EXISTS `empresas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cif` varchar(10) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `email_contacto` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `telefono_contacto` varchar(9) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `web` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `id_direccion` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cif_UNIQUE` (`cif`),
  KEY `id_direccion` (`id_direccion`),
  CONSTRAINT `empresas_ibfk_1` FOREIGN KEY (`id_direccion`) REFERENCES `direcciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresas`
--

LOCK TABLES `empresas` WRITE;
/*!40000 ALTER TABLE `empresas` DISABLE KEYS */;
INSERT INTO `empresas` VALUES (5,'A12345678','Delightful Events','contact@delightfulevents.com','910123456','https://www.delightfulevents.com',29),(6,'B98765432','Dreamscapes','contact@dreamscapes.com','931987654','https://www.dreamscapes.com',30),(7,'C24681357','DreamWish','contact@dreamwish.com','912345678','https://www.dreamwish.com',31),(8,'D57483920','Event Chaser','contact@eventchaser.com','915678901','https://www.eventchaser.com',32);
/*!40000 ALTER TABLE `empresas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventos`
--

DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `creacion` datetime DEFAULT NULL,
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `fecha_inicio` datetime NOT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  `id_ubicacion` int NOT NULL,
  `id_usuario` int NOT NULL,
  `tipo` varchar(45) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `precio` float DEFAULT NULL,
  `hora_inicio` time DEFAULT NULL,
  `num_horas` int DEFAULT NULL,
  `n_asistentes` int NOT NULL,
  `pagado` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_evento_ubicacion` (`id_ubicacion`),
  KEY `FK_evento_usuario` (`id_usuario`),
  CONSTRAINT `FK_evento_ubicacion` FOREIGN KEY (`id_ubicacion`) REFERENCES `ubicaciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_evento_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos`
--

LOCK TABLES `eventos` WRITE;
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
INSERT INTO `eventos` VALUES (27,'2023-06-06 21:50:39','Boda Prueba','2023-06-15 00:00:00',NULL,3,20,'Boda',4920,'10:50:00',12,12,0),(28,'2023-06-06 22:37:30','jhdlasjdf','2023-06-08 00:00:00',NULL,6,20,'Boda',2192,'11:36:00',8,30,0);
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitados`
--

DROP TABLE IF EXISTS `invitados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invitados` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `token` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `asistencia` tinyint(1) DEFAULT '0',
  `id_evento` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_invitado_evento` (`id_evento`),
  CONSTRAINT `FK_invitado_evento` FOREIGN KEY (`id_evento`) REFERENCES `eventos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitados`
--

LOCK TABLES `invitados` WRITE;
/*!40000 ALTER TABLE `invitados` DISABLE KEYS */;
/*!40000 ALTER TABLE `invitados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocio`
--

DROP TABLE IF EXISTS `ocio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ocio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(600) NOT NULL,
  `foto` varchar(500) DEFAULT NULL,
  `id_empresa` int NOT NULL,
  `precio_hora` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ocio_empresas_idx` (`id_empresa`),
  CONSTRAINT `FK_ocio_empresas` FOREIGN KEY (`id_empresa`) REFERENCES `empresas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocio`
--

LOCK TABLES `ocio` WRITE;
/*!40000 ALTER TABLE `ocio` DISABLE KEYS */;
INSERT INTO `ocio` VALUES (2,'DJ','Dj para ambientar tu evento con todo tipo de música.','/images/dj3.jpg',5,35),(3,'Dj','Musica de Ambiente.','/images/dj7.jpg',7,24);
/*!40000 ALTER TABLE `ocio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_rol_idx` (`id_usuario`),
  CONSTRAINT `FK_user_rol` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (9,'ROLE_ADMIN',19),(10,'ROLE_USER',19),(11,'ROLE_USER',20);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ubicaciones`
--

DROP TABLE IF EXISTS `ubicaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ubicaciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `aforo` varchar(7) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_direccion` int NOT NULL,
  `foto` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `precio_hora` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ubicacion_direccion` (`id_direccion`),
  CONSTRAINT `FK_ubicacion_direccion` FOREIGN KEY (`id_direccion`) REFERENCES `direcciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ubicaciones`
--

LOCK TABLES `ubicaciones` WRITE;
/*!40000 ALTER TABLE `ubicaciones` DISABLE KEYS */;
INSERT INTO `ubicaciones` VALUES (3,'Masia Tradicional','300',33,'/imagesUbicaciones/masia_boda.webp',200),(4,'Sala Conferencias/Congresos','250',35,'/imagesUbicaciones/sala_eventos_bcn.jpg',100),(5,'Ciudad de las Artes y las Ciencias','1700',36,'/imagesUbicaciones/ciudad_artes_vlc.jpg',1000),(6,'La Barraca el Palmar','40',37,'/imagesUbicaciones/elPalmar.jpg',40);
/*!40000 ALTER TABLE `ubicaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellidos` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `nif` varchar(9) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `telefono` varchar(9) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `nombre_usuario` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `clave_acceso` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_direccion` int NOT NULL,
  `cookie` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nif` (`nif`),
  KEY `FK_usuario_direccion_idx` (`id_direccion`),
  CONSTRAINT `FK_usuario_direccion` FOREIGN KEY (`id_direccion`) REFERENCES `direcciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (19,'admin','admin','01959765K','856205866','admin@eventapp.com','admin','$2a$10$n7UFZpeva9lAR2TWN2/hjO/BIF5Yd72M8r.MOLND3304oOJyZJE7S',28,'CBDB0EA32CD87F2654C866FC2439A24E'),(20,'Hector','Utrillas Escapa','45913889R','123456789','hector@gmail.com','hector','$2a$10$nZy9OmWDy.XjD4dbIB3a4egu5iGaut/dAn5Mi6.6khFO8gGycgjAq',34,'9E3574F557A0E359B4989EE5464CAEC1');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-07 15:59:27
