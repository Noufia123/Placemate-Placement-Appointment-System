CREATE DATABASE  IF NOT EXISTS `placement` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `placement`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: placement
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `appointment_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `company_id` int NOT NULL,
  `appointment_day` varchar(20) NOT NULL,
  `slot_time` varchar(20) NOT NULL,
  `token_number` varchar(20) NOT NULL,
  `status` enum('BOOKED','RESCHEDULED','CANCELLED') DEFAULT 'BOOKED',
  `rescheduled_day` varchar(20) DEFAULT NULL,
  `rescheduled_slot` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`appointment_id`),
  UNIQUE KEY `token_number` (`token_number`),
  KEY `student_id` (`student_id`),
  KEY `company_id` (`company_id`),
  CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`),
  CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`company_id`) REFERENCES `companies` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (1,1,5,'Monday','10:00-11:00','APM1758608357265402','BOOKED',NULL,NULL),(2,2,3,'Tuesday','10:00-11:00','APM1758608417301398','BOOKED',NULL,NULL),(3,3,5,'Tuesday','2:00-3:00','PLT8023','RESCHEDULED',NULL,NULL),(4,4,3,'Monday','11:00-12:00','PLT9774','BOOKED',NULL,NULL),(5,5,5,'Monday','10:00-11:00','PLT771221','BOOKED',NULL,NULL),(6,6,2,'Wednesday','11:00-12:00','PLT212266','RESCHEDULED',NULL,NULL),(7,7,5,'Monday','10:00-11:00','PLT782707','BOOKED',NULL,NULL),(8,8,1,'Wednesday','10:00-11:00','PLT824937','BOOKED',NULL,NULL),(9,9,4,'Tuesday','2:00-3:00','PLT392168','RESCHEDULED',NULL,NULL),(10,10,5,'Monday','10:00-11:00','PLT949431','BOOKED',NULL,NULL),(11,11,3,'Monday','1:00-2:00','PLT898094','BOOKED',NULL,NULL),(12,12,2,'Wednesday','11:00-12:00','PLT320450','BOOKED',NULL,NULL),(13,13,5,'Monday','2:00-3:00','PLT502057','BOOKED',NULL,NULL),(14,14,5,'Monday','10:00-11:00','PLT729126','BOOKED',NULL,NULL),(15,15,2,'Wednesday','1:00-2:00','PLT554777','BOOKED',NULL,NULL),(16,16,4,'Friday','10:00-11:00','PLT969027','BOOKED',NULL,NULL);
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companies` (
  `company_id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(100) NOT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (1,'TCS'),(2,'Infosys'),(3,'IBM'),(4,'Wipro'),(5,'Capgemini');
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'Noufia','8590302241'),(2,'Nasiya','9282781728'),(3,'Noufia','8590302241'),(4,'Nasiya','9282781728'),(5,'Noufia','8590302241'),(6,'Bincy','9278936283'),(7,'Noufia','8590302241'),(8,'Arush','8590213456'),(9,'Shilpa','8590456792'),(10,'Noufia','8590456792'),(11,'Sreeram','9800215678'),(12,'sreeraj','8590783356'),(13,'priya','9845678022'),(14,'priya','9845678022'),(15,'amii','7893226789'),(16,'Harsha','7903458791');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-23 15:59:58
