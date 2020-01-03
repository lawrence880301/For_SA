-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: missa
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `createdroomlist`
--

DROP TABLE IF EXISTS `createdroomlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `createdroomlist` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Id_member` int(10) NOT NULL,
  `Id_room` int(10) NOT NULL,
  `JoinDate` datetime NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`),
  KEY `fk_roomid_idx` (`Id_room`),
  KEY `fk_memberid_idx` (`Id_member`),
  CONSTRAINT `fk_memberid` FOREIGN KEY (`Id_member`) REFERENCES `members` (`member_id`),
  CONSTRAINT `fk_roomid` FOREIGN KEY (`Id_room`) REFERENCES `rooms` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `createdroomlist`
--

LOCK TABLES `createdroomlist` WRITE;
/*!40000 ALTER TABLE `createdroomlist` DISABLE KEYS */;
INSERT INTO `createdroomlist` VALUES (1,1,1,'2019-12-31 00:00:00');
/*!40000 ALTER TABLE `createdroomlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `joinedroomlist`
--

DROP TABLE IF EXISTS `joinedroomlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `joinedroomlist` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Id_member` int(11) NOT NULL,
  `Id_room` int(11) NOT NULL,
  `joinDate` date DEFAULT NULL,
  `Name` varchar(64) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Place` varchar(64) DEFAULT NULL,
  `Type` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk roomid_idx` (`Id_room`),
  KEY `fk memberid_idx` (`Id_member`),
  CONSTRAINT `fk memberid` FOREIGN KEY (`Id_member`) REFERENCES `members` (`member_id`),
  CONSTRAINT `fk roomid` FOREIGN KEY (`Id_room`) REFERENCES `rooms` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `joinedroomlist`
--

LOCK TABLES `joinedroomlist` WRITE;
/*!40000 ALTER TABLE `joinedroomlist` DISABLE KEYS */;
INSERT INTO `joinedroomlist` VALUES (1,1,1,'2019-12-31','吃屎','2020-01-02','廁所','生理現象');
/*!40000 ALTER TABLE `joinedroomlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `managers`
--

DROP TABLE IF EXISTS `managers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `managers` (
  `managers_id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `pwd` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`managers_id`),
  UNIQUE KEY `managers_id_UNIQUE` (`managers_id`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `managers`
--

LOCK TABLES `managers` WRITE;
/*!40000 ALTER TABLE `managers` DISABLE KEYS */;
INSERT INTO `managers` VALUES (1,'是在哈囉','pupss940204@gmail.com','asd87128'),(2,'在哈囉','pupss940203@gmail.com','asdf87128');
/*!40000 ALTER TABLE `managers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `pwd` varchar(64) DEFAULT NULL,
  `firstname` varchar(64) DEFAULT NULL,
  `lastname` varchar(64) DEFAULT NULL,
  `gender` varchar(5) NOT NULL,
  `Dob` datetime NOT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `member_id_UNIQUE` (`member_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'pupss940204@gmail.com','asd87128','戴','敬倫','boy','1234-05-06 00:00:00');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(64) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Place` varchar(50) NOT NULL,
  `Type` varchar(64) DEFAULT NULL,
  `Maxmember` int(10) NOT NULL,
  `GenderRestriction` varchar(10) NOT NULL,
  `AgeUpperlimit` int(10) DEFAULT NULL,
  `AgeLowerlimit` int(10) DEFAULT NULL,
  `Description` varchar(64) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'您好','2020-01-01','資管系專題教室','coding',5,'不限',0,999,'元旦coding到死','2019-12-31'),(2,'shit','2020-01-02','中央大學','大便',4,'不限',10,20,'好吃喔','2020-01-02');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-03  5:32:34
