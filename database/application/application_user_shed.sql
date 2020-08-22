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
-- Table structure for table `user_shed`
--

DROP TABLE IF EXISTS `user_shed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_shed` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `userId` smallint(6) NOT NULL,
  `shedId` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoq093hma6b3e5yn9h66r16cv1` (`shedId`),
  KEY `user_shed_ibfk_1_idx` (`userId`),
  CONSTRAINT `FKgb4wcpau9gktfaxyakgysab11` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `FKoq093hma6b3e5yn9h66r16cv1` FOREIGN KEY (`shedId`) REFERENCES `shed` (`id`),
  CONSTRAINT `user_shed_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `user_shed_ibfk_2` FOREIGN KEY (`shedId`) REFERENCES `shed` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_shed`
--

LOCK TABLES `user_shed` WRITE;
/*!40000 ALTER TABLE `user_shed` DISABLE KEYS */;
INSERT INTO `user_shed` VALUES (1,1,1),(2,1,2),(3,1,4),(4,1,5),(92,22,1),(93,23,1),(97,23,2),(98,23,4);
/*!40000 ALTER TABLE `user_shed` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-11 12:04:21
