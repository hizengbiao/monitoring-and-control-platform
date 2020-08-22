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
-- Table structure for table `shed_corp`
--

DROP TABLE IF EXISTS `shed_corp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shed_corp` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `shedId` smallint(6) NOT NULL,
  `corpId` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiaxlm30bgocpsgi5dg2d57rm4` (`shedId`),
  KEY `FKnsxwbukuvkux6e5fbnnfaajsq` (`corpId`),
  CONSTRAINT `FKiaxlm30bgocpsgi5dg2d57rm4` FOREIGN KEY (`shedId`) REFERENCES `shed` (`id`),
  CONSTRAINT `FKnsxwbukuvkux6e5fbnnfaajsq` FOREIGN KEY (`corpId`) REFERENCES `corp` (`id`),
  CONSTRAINT `shed_corp_ibfk_1` FOREIGN KEY (`shedId`) REFERENCES `shed` (`id`),
  CONSTRAINT `shed_corp_ibfk_2` FOREIGN KEY (`corpId`) REFERENCES `corp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shed_corp`
--

LOCK TABLES `shed_corp` WRITE;
/*!40000 ALTER TABLE `shed_corp` DISABLE KEYS */;
/*!40000 ALTER TABLE `shed_corp` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-06 15:59:39
