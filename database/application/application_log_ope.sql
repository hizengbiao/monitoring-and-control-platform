CREATE DATABASE  IF NOT EXISTS `application` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `application`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: application
-- ------------------------------------------------------
-- Server version	5.7.13-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `log_ope`
--

DROP TABLE IF EXISTS `log_ope`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_ope` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `operate` varchar(10) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `userId` smallint(6) NOT NULL,
  `shedId` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn3h06vmhu0fddh3jpixevbrfa` (`shedId`),
  KEY `FKmdk4ab9qu16ow5pju9rs95q8b` (`userId`),
  CONSTRAINT `FKmdk4ab9qu16ow5pju9rs95q8b` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `FKn3h06vmhu0fddh3jpixevbrfa` FOREIGN KEY (`shedId`) REFERENCES `shed` (`id`),
  CONSTRAINT `log_ope_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `log_ope_ibfk_2` FOREIGN KEY (`shedId`) REFERENCES `shed` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_ope`
--

LOCK TABLES `log_ope` WRITE;
/*!40000 ALTER TABLE `log_ope` DISABLE KEYS */;
INSERT INTO `log_ope` VALUES (3,'降温','2017-01-10 14:44:16',23,1),(4,'浇水','2017-01-10 14:44:16',23,1),(5,'提高CO2浓度','2017-01-10 14:44:36',23,4);
/*!40000 ALTER TABLE `log_ope` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-11 12:04:22
