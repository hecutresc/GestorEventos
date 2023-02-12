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
-- Table structure for table `catering`
--

DROP TABLE IF EXISTS `caterings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caterings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_empresa` int NOT NULL,
  `id_salon` int NOT NULL,
  `menu` text COLLATE latin1_spanish_ci NOT NULL,
  `precio` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_catering_salon_idx` (`id_salon`),
  CONSTRAINT `FK_catering_salon` FOREIGN KEY (`id_salon`) REFERENCES `salon` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catering`
--

LOCK TABLES `caterings` WRITE;
/*!40000 ALTER TABLE `caterings` DISABLE KEYS */;
/*!40000 ALTER TABLE `caterings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direcciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direcciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `calle` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `numero` varchar(5) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ciudad` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `cp` varchar(5) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direcciones`
--

LOCK TABLES `direcciones` WRITE;
/*!40000 ALTER TABLE `direcciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `direcciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cif` varchar(10) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `id_direccion` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cif_UNIQUE` (`cif`),
  KEY `FK_empresa_direccion_idx` (`id_direccion`),
  CONSTRAINT `FK_empresa_direccion` FOREIGN KEY (`id_direccion`) REFERENCES `direccion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresas` WRITE;
/*!40000 ALTER TABLE `empresas` DISABLE KEYS */;
/*!40000 ALTER TABLE `empresas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `id_catering` int,
  `id_salon` int NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_evento_salon_idx` (`id_salon`),
  KEY `FK_evento_catering_idx` (`id_catering`),
  KEY `FK_evento_usuario_idx` (`id_usuario`),
  CONSTRAINT `FK_evento_catering` FOREIGN KEY (`id_catering`) REFERENCES `catering` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_evento_salon` FOREIGN KEY (`id_salon`) REFERENCES `salon` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_evento_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `eventos` WRITE;
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitado`
--

DROP TABLE IF EXISTS `invitados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invitados` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `token` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `asistencia` tinyint(1) DEFAULT '0',
  `id_evento` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_invitado_evento_idx` (`id_evento`),
  CONSTRAINT `FK_invitado_evento` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitados`
--

LOCK TABLES `invitados` WRITE;
/*!40000 ALTER TABLE `invitados` DISABLE KEYS */;
/*!40000 ALTER TABLE `invitados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salon`
--

DROP TABLE IF EXISTS `salones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `aforo` varchar(7) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_direccion` int NOT NULL,
  `id_catering` int,
  PRIMARY KEY (`id`),
  KEY `FK_salon_direccion_idx` (`id_direccion`),
  KEY `FK_salon_catering_idx` (`id_catering`),
  CONSTRAINT `FK_salon_catering` FOREIGN KEY (`id_catering`) REFERENCES `catering` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_salon_direccion` FOREIGN KEY (`id_direccion`) REFERENCES `direccion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salon`
--

LOCK TABLES `salones` WRITE;
/*!40000 ALTER TABLE `salones` DISABLE KEYS */;
/*!40000 ALTER TABLE `salones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` int DEFAULT NULL,
  `nombre` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellidos` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `nif` varchar(9) COLLATE latin1_spanish_ci NOT NULL,
  `telefono` varchar(9) COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `nombre_usuario` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `contrasena` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `activo` tinyint NOT NULL DEFAULT '1',
  `id_direccion` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nif` (`nif`),
  KEY `FK_usuario_direccion_idx` (`id_direccion`),
  CONSTRAINT `FK_usuario_direccion` FOREIGN KEY (`id_direccion`) REFERENCES `direccion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

-- =======================================================
-- INSERCION DE TODOS LOS DATOS PARA LA BD ==============
-- =======================================================

SET FOREIGN_KEY_CHECKS=0;

INSERT INTO eventos (`fecha_inicio`,`fecha_fin`,`nombre`,`id_catering`,`id_salon`,`id_usuario`)
VALUES
  ("2022/02/16","2022/02/18","Ponencia nueva temporada de League of Legends",1,1,8),
  ("2022/02/20","2022/02/22","Congreso acerca del desarrollo urbanistico del Puig",2,2,5);


INSERT INTO direcciones (`calle`,`numero`,`ciudad`,`cp`) VALUES
  ("P.O. Box 573, 786 Nam Rd.",39,"Alajuela","41633"),
  ("3469 Felis Rd.",32,"Ulster","30145"),
  ("Ap #435-8999 Mauris, Av.",9,"Dongbei","5133"),
  ("950-9232 Aliquet Rd.",26,"Samara Oblast","21209"),
  ("P.O. Box 328, 3736 Egestas Street",12,"Lambayeque","35561"),
  ("845-8145 Ac Rd.",21,"North Gyeongsang","26965"),
  ("P.O. Box 633, 827 Curae Rd.",19,"Nottinghamshire","44755"),
  ("310-5515 Nunc Rd.",33,"Corse","14516"),
  ("430-9076 Pharetra. St.",32,"San Andres y Providencia","93527"),
  ("Ap #418-396 Felis, St.",33,"Flevoland","43658"),
  ("Ap #700-1546 Aliquet Road",29,"Pondicherry","36582"),
  ("1961 Erat Rd.",22,"Lower Austria","54348"),
  ("Ap #164-110 Sed Rd.",17,"North Region","37331"),
  ("6762 Libero Rd.",29,"Oost-Vlaanderen","63224"),
  ("3868 Vestibulum Street",26,"Gangwon","63222"),
  ("8100 Luctus Street",17,"South Island","25523"),
  ("9064 Mattis. Street",17,"Dongbei","49589"),
  ("6759 Curae Ave",34,"Nordland","84137"),
  ("487 Elit, Rd.",35,"Clackmannanshire","62662");
  
INSERT INTO usuarios (`nombre`,`apellidos`,`nif`,`telefono`,`email`,`nombre_usuario`,`contrasena`,`activo`,`id_direccion`)
VALUES
  ("Fredericka","Lopez","01959765K","856205866","arcu.ac@google.couk","Forrest S. Tillman","WKG93LDR4JH",1,1),
  ("Darius","Santos","52463682M","532340203","hendrerit@outlook.net","Carly W. Robinson","OUJ63DIU8XB",1,2),
  ("Stone","Nunez","43533439R","381469718","gravida.sit.amet@aol.net","Sarah W. Wood","XKD48ITX3WT",0,3),
  ("Hanae","Perez","49373488I","029347188","ut.eros@aol.com","Remedios V. Gibbs","FRR45JQW7TM",0,4),
  ("Chase","Molina","72240283M","412359654","vel.arcu@yahoo.edu","Joan B. Hines","VBI86VHH1YR",1,5),
  ("Benjamin","Perez","44384325D","224817740","mollis.non@hotmail.edu","Linus V. Burns","GOI62YGR3MK",1,6),
  ("Sheila","Arias","54783176J","565636863","aliquam.nisl@aol.couk","Selma O. Marks","QDD64KSR1CI",0,7),
  ("Hammett","Leon","76038033K","553353537","quis@yahoo.com","Shellie Q. Langley","DMG39YNJ4XT",0,8),
  ("Zia","Gil","22784382H","311661274","lacus.quisque.imperdiet@icloud.ca","Eagan E. Leblanc","PNJ53GGI9WK",1,9),
  ("Lillian","Reyes","44167866O","162009741","duis.cursus@hotmail.edu","Hilary R. Bernard","QLB37UHW3QH",1,10),
  ("Wyatt","Serrano","33717423H","330288805","sem.consequat.nec@icloud.ca","Herrod C. Oneil","JBS83TWH3YV",0,12),
  ("Alexandra","Gomez","62850703G","292163198","posuere.at.velit@hotmail.net","Avram E. Brooks","DUU58XRM9CK",0,13),
  ("Jael","Rubio","64149657Q","210298777","aliquet.nec@google.couk","Macaulay I. Blake","NXC46OJX0VO",1,14);
  
INSERT INTO empresas (`cif`,`nombre`,`id_direccion`)
VALUES
  ("N48227285","Eventos Paco S.L.",15),
  ("W19917333","Caterings El Puig",16);
  
INSERT INTO caterings (`id_empresa`,`id_salon`,`menu`,`precio`)
VALUES
(1,1,"arcu. Morbi sit amet massa. Quisque porttitor eros nec tellus. Nunc lectus pede, ultrices a, auctor non, feugiat nec, diam. Duis mi enim, condimentum eget, volutpat ornare, facilisis eget, ipsum. Donec sollicitudin adipiscing ligula. Aenean gravida nunc sed",26),
(2,2,"nisi. Mauris nulla. Integer urna. Vivamus molestie dapibus ligula. Aliquam erat volutpat. Nulla dignissim. Maecenas ornare egestas ligula. Nullam feugiat placerat velit. Quisque varius. Nam porttitor scelerisque neque.",90);

INSERT INTO salones (`nombre`,`aforo`,`id_direccion`,`id_catering`)
VALUES
  ("Lloret del Mar",30,17,1),
  ("Las Venturas",30,18,2);

INSERT INTO invitados (`email`,`token`,`asistencia`,`id_evento`)
VALUES
  ("lorem.donec.elementum@protonmail.edu","NSZ21QJW2QCRU447NV",1,1),
  ("sed.eu@icloud.org","OJU93BGT1PKPS114SM",1,1),
  ("sed@outlook.edu","SUB27YTX4TVWT051YX",0,2),
  ("risus.odio@aol.com","MCF65TLR5YVJJ032MJ",0,2),
  ("a.neque@protonmail.com","BLG40MYR9MLDX752UL",1,1),
  ("est@icloud.net","STS86RMK8UKFF247WQ",1,1),
  ("eu.lacus@yahoo.ca","AXM83CSO1NCNP051TL",0,2),
  ("justo.nec.ante@protonmail.ca","FNY23OQD7PMHW503YE",1,1),
  ("rutrum.eu@protonmail.ca","QTH52QPN7ZOXJ251TQ",1,1),
  ("accumsan.laoreet@aol.org","NIT17RSL7KPHO426MM",0,2),
  ("neque.morbi.quis@google.ca","QKO82FHL8OSWX677FM",0,2),
  ("eros@aol.com","ROI51PMP5TNIA608BJ",0,2),
  ("enim.commodo@yahoo.com","LFI56XCR6FJZY544IH",1,1);
  
SET FOREIGN_KEY_CHECKS=0;


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

-- Dump completed on 2023-01-11 10:30:11
