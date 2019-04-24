CREATE DATABASE  IF NOT EXISTS `cinema` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `cinema`;
-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `days_translate`
--

DROP TABLE IF EXISTS `days_translate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `days_translate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day_id` int(11) NOT NULL,
  `lang_id` int(11) NOT NULL,
  `day_name` varchar(16) NOT NULL,
  `day_name_short` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `days_translate_days_id_fk` (`day_id`),
  KEY `days_translate_languages_id_fk` (`lang_id`),
  CONSTRAINT `days_translate_days_id_fk` FOREIGN KEY (`day_id`) REFERENCES `days` (`id`),
  CONSTRAINT `days_translate_languages_id_fk` FOREIGN KEY (`lang_id`) REFERENCES `languages` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `days_translate`
--

LOCK TABLES `days_translate` WRITE;
/*!40000 ALTER TABLE `days_translate` DISABLE KEYS */;
INSERT INTO `days_translate` VALUES (1,1,1,'Monday','mon'),(2,1,2,'Понеділок','пн'),(3,2,1,'Tuesday','tue'),(4,2,2,'Вівторок','вт'),(5,3,1,'Wednesday','wed'),(6,3,2,'Середа','ср'),(7,4,1,'Thursday','thu'),(8,4,2,'Четвер','чт'),(9,5,1,'Friday','fri'),(10,5,2,'П\'ятниця','пт'),(11,6,1,'Saturday','sat'),(12,6,2,'Субота','сб'),(13,7,1,'Sunday','sun'),(14,7,2,'Неділя','нд');
/*!40000 ALTER TABLE `days_translate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-21 21:50:38
