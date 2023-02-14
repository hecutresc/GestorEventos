CREATE DATABASE  IF NOT EXISTS `gestioneventos` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestioneventos`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: gestioneventos
-- ------------------------------------------------------
-- Server version	8.0.31

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
  `id_empresa` int NOT NULL,
  `menu` text CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `precio` float NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_catering_empresa` FOREIGN KEY (`id_empresa`) REFERENCES `empresas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caterings`
--

INSERT INTO caterings (`id_empresa`,`menu`,`precio`)
VALUES
(1,"arcu. Morbi sit amet massa. Quisque porttitor eros nec tellus. Nunc lectus pede, ultrices a, auctor non, feugiat nec, diam. Duis mi enim, condimentum eget, volutpat ornare, facilisis eget, ipsum. Donec sollicitudin adipiscing ligula. Aenean gravida nunc sed",26),
(2,"nisi. Mauris nulla. Integer urna. Vivamus molestie dapibus ligula. Aliquam erat volutpat. Nulla dignissim. Maecenas ornare egestas ligula. Nullam feugiat placerat velit. Quisque varius. Nam porttitor scelerisque neque.",90);

--
-- Table structure for table `cateringubicacionevento`
--

DROP TABLE IF EXISTS `cateringubicacionevento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cateringubicacionevento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fechahora` datetime NOT NULL,
  `id_catering` int NOT NULL,
  `id_evento` int NOT NULL,
  `id_ubicacion` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_cue_catering` FOREIGN KEY (`id_catering`) REFERENCES `caterings` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cue_evento` FOREIGN KEY (`id_evento`) REFERENCES `eventos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cue_ubicacion` FOREIGN KEY (`id_ubicacion`) REFERENCES `ubicaciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cateringubicacionevento`
--

INSERT INTO `cateringubicacionevento`
(`id`,`fechahora`,`id_catering`,`id_evento`,`id_ubicacion`)
VALUES
(1,now(),1,1,1),
(2,now(),1,2,2);

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direcciones`
--

LOCK TABLES `direcciones` WRITE;
/*!40000 ALTER TABLE `direcciones` DISABLE KEYS */;
INSERT INTO `direcciones` VALUES (1,'P.O. Box 573, 786 Nam Rd.','39','Alajuela','41633'),(2,'3469 Felis Rd.','32','Ulster','30145'),(3,'Ap #435-8999 Mauris, Av.','9','Dongbei','5133'),(4,'950-9232 Aliquet Rd.','26','Samara Oblast','21209'),(5,'P.O. Box 328, 3736 Egestas Street','12','Lambayeque','35561'),(6,'845-8145 Ac Rd.','21','North Gyeongsang','26965'),(7,'P.O. Box 633, 827 Curae Rd.','19','Nottinghamshire','44755'),(8,'310-5515 Nunc Rd.','33','Corse','14516'),(9,'430-9076 Pharetra. St.','32','San Andres y Providencia','93527'),(10,'Ap #418-396 Felis, St.','33','Flevoland','43658'),(11,'Ap #700-1546 Aliquet Road','29','Pondicherry','36582'),(12,'1961 Erat Rd.','22','Lower Austria','54348'),(13,'Ap #164-110 Sed Rd.','17','North Region','37331'),(14,'6762 Libero Rd.','29','Oost-Vlaanderen','63224'),(15,'3868 Vestibulum Street','26','Gangwon','63222'),(16,'8100 Luctus Street','17','South Island','25523'),(17,'9064 Mattis. Street','17','Dongbei','49589'),(18,'6759 Curae Ave','34','Nordland','84137'),(19,'487 Elit, Rd.','35','Clackmannanshire','62662');
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
  `id_direccion` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cif_UNIQUE` (`cif`),
  FOREIGN KEY (`id_direccion`) REFERENCES `direcciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresas`
--

LOCK TABLES `empresas` WRITE;
/*!40000 ALTER TABLE `empresas` DISABLE KEYS */;
INSERT INTO `empresas` VALUES (1,'N48227285','Eventos Paco S.L.',15),(2,'W19917333','Caterings El Puig',16);
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
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `id_ubicacion` int NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_evento_ubicacion` FOREIGN KEY (`id_ubicacion`) REFERENCES `ubicaciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_evento_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos`
--

INSERT INTO eventos (`creacion`,`nombre`,`fecha_inicio`,`fecha_fin`,`id_ubicacion`,`id_usuario`)
VALUES
  (now(),"Ponencia nueva temporada de League of Legends","2022/02/16","2022/02/18",1,8),
  (now(),"Congreso acerca del desarrollo urbanistico del Puig","2022/02/20","2022/02/22",2,5);

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
  CONSTRAINT `FK_invitado_evento` FOREIGN KEY (`id_evento`) REFERENCES `eventos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitados`
--

LOCK TABLES `invitados` WRITE;
/*!40000 ALTER TABLE `invitados` DISABLE KEYS */;
INSERT INTO `invitados` VALUES (1,'lorem.donec.elementum@protonmail.edu','NSZ21QJW2QCRU447NV',1,1),(2,'sed.eu@icloud.org','OJU93BGT1PKPS114SM',1,1),(3,'sed@outlook.edu','SUB27YTX4TVWT051YX',0,2),(4,'risus.odio@aol.com','MCF65TLR5YVJJ032MJ',0,2),(5,'a.neque@protonmail.com','BLG40MYR9MLDX752UL',1,1),(6,'est@icloud.net','STS86RMK8UKFF247WQ',1,1),(7,'eu.lacus@yahoo.ca','AXM83CSO1NCNP051TL',0,2),(8,'justo.nec.ante@protonmail.ca','FNY23OQD7PMHW503YE',1,1),(9,'rutrum.eu@protonmail.ca','QTH52QPN7ZOXJ251TQ',1,1),(10,'accumsan.laoreet@aol.org','NIT17RSL7KPHO426MM',0,2),(11,'neque.morbi.quis@google.ca','QKO82FHL8OSWX677FM',0,2),(12,'eros@aol.com','ROI51PMP5TNIA608BJ',0,2),(13,'enim.commodo@yahoo.com','LFI56XCR6FJZY544IH',1,1);
/*!40000 ALTER TABLE `invitados` ENABLE KEYS */;
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
  `id_catering` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ubicacion_catering` FOREIGN KEY (`id_catering`) REFERENCES `caterings` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ubicacion_direccion` FOREIGN KEY (`id_direccion`) REFERENCES `direcciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ubicaciones`
--

LOCK TABLES `ubicaciones` WRITE;
/*!40000 ALTER TABLE `ubicaciones` DISABLE KEYS */;
INSERT INTO `ubicaciones` VALUES (1,'Lloret del Mar','30',17,1),(2,'Las Venturas','30',18,2);
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
  `tipo` int DEFAULT 0,
  `nombre` varchar(20) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellidos` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `nif` varchar(9) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `telefono` varchar(9) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `nombre_usuario` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `clave_acceso` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `activo` tinyint NOT NULL DEFAULT '1',
  `id_direccion` int NOT NULL,
 
  PRIMARY KEY (`id`),
  UNIQUE KEY `nif` (`nif`),
  KEY `FK_usuario_direccion_idx` (`id_direccion`),
  CONSTRAINT `FK_usuario_direccion` FOREIGN KEY (`id_direccion`) REFERENCES `direcciones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

INSERT INTO usuarios (`tipo`,`nombre`,`apellidos`,`nif`,`telefono`,`email`,`nombre_usuario`,`clave_acceso`,`activo`,`id_direccion`)
VALUES
  (1,"Fredericka","Lopez","01959765K","856205866","arcu.ac@google.couk","Forrest S. Tillman","WKG93LDR4JH",1,1),
  (0,"Darius","Santos","52463682M","532340203","hendrerit@outlook.net","Carly W. Robinson","OUJ63DIU8XB",1,2),
  (0,"Stone","Nunez","43533439R","381469718","gravida.sit.amet@aol.net","Sarah W. Wood","XKD48ITX3WT",0,3),
  (0,"Hanae","Perez","49373488I","029347188","ut.eros@aol.com","Remedios V. Gibbs","FRR45JQW7TM",0,4),
  (0,"Chase","Molina","72240283M","412359654","vel.arcu@yahoo.edu","Joan B. Hines","VBI86VHH1YR",1,5),
  (0,"Benjamin","Perez","44384325D","224817740","mollis.non@hotmail.edu","Linus V. Burns","GOI62YGR3MK",1,6),
  (0,"Sheila","Arias","54783176J","565636863","aliquam.nisl@aol.couk","Selma O. Marks","QDD64KSR1CI",0,7),
  (0,"Hammett","Leon","76038033K","553353537","quis@yahoo.com","Shellie Q. Langley","DMG39YNJ4XT",0,8),
  (0,"Zia","Gil","22784382H","311661274","lacus.quisque.imperdiet@icloud.ca","Eagan E. Leblanc","PNJ53GGI9WK",1,9),
  (0,"Lillian","Reyes","44167866O","162009741","duis.cursus@hotmail.edu","Hilary R. Bernard","QLB37UHW3QH",1,10),
  (0,"Wyatt","Serrano","33717423H","330288805","sem.consequat.nec@icloud.ca","Herrod C. Oneil","JBS83TWH3YV",0,12),
  (0,"Alexandra","Gomez","62850703G","292163198","posuere.at.velit@hotmail.net","Avram E. Brooks","DUU58XRM9CK",0,13),
  (0,"Jael","Rubio","64149657Q","210298777","aliquet.nec@google.couk","Macaulay I. Blake","NXC46OJX0VO",1,14);

--
-- Dumping events for database 'gestioneventos'
--

--
-- Dumping routines for database 'gestioneventos'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-12 19:21:23
