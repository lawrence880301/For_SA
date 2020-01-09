-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: missa
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
  `Name` varchar(64) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Place` varchar(64) DEFAULT NULL,
  `Type` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk roomid_idx` (`Id_room`),
  KEY `fk memberid_idx` (`Id_member`),
  CONSTRAINT `fk memberid` FOREIGN KEY (`Id_member`) REFERENCES `members` (`member_id`),
  CONSTRAINT `fk roomid` FOREIGN KEY (`Id_room`) REFERENCES `rooms` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `joinedroomlist`
--

LOCK TABLES `joinedroomlist` WRITE;
/*!40000 ALTER TABLE `joinedroomlist` DISABLE KEYS */;
INSERT INTO `joinedroomlist` VALUES (1,1,1,'吃屎','2020-01-02','廁所','生理現象'),(2,3,2,'打球','2020-01-04','高雄發大財','籃球'),(3,3,5,'打球','2020-01-04','高雄發大財','籃球'),(4,3,5,'打球','2020-01-04','高雄發大財','籃球'),(5,12,12,'6666666666','2020-01-14','454','慢跑'),(6,15,12,'殼以','2020-01-09','宿舍','慢跑');
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'pupss940204@gmail.com','asd87128','戴','敬倫','boy','1234-05-06 00:00:00'),(2,'vicent871220@gmail.com','VICENT415263','楊','昱辰','male','2020-01-07 00:00:00'),(3,'vicent0123@yahoo.com.tw','VICENT666','v','ic','male','2019-12-31 00:00:00'),(4,'vicent01234@yahoo.com.tw','VICENT4567','s','inga','male','2020-01-08 00:00:00'),(5,'vicent012345@yahoo.com.tw','VICENT123','可以','的','male','2020-01-04 00:00:00'),(6,'123@gmail.com','VICENT415263','愈','楊啦','male','2020-01-11 00:00:00'),(7,'vicent123@yahoo.com.tw','VICENT415263','1','23','male','2020-01-08 00:00:00'),(8,'vicent871221@gmail.com','VICENT415263','1','33','male','2020-01-14 00:00:00'),(9,'vicent871222@gmail.com','VICENT415263','2','33','male','2020-01-14 00:00:00'),(10,'vicent871223@gmail.com','VICENT415263','999999','45354','male','2020-01-09 00:00:00'),(11,'vicent871224@gmail.com','VICENT415263','1','昱辰','male','2020-01-06 00:00:00'),(12,'vicent871225@gmail.com','VICENT415263','14','545466','male','2020-01-03 00:00:00'),(13,'vicent871226@gmail.com','VICENT415263','1','23','male','2020-01-16 00:00:00'),(14,'vicent871227@gmail.com','VICENT415263','測','試啦','male','2020-01-17 00:00:00'),(15,'vicent871228@gmail.com','VICENT415263','裕陽','23','male','2020-01-15 00:00:00');
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
  `Maxmember` varchar(64) DEFAULT NULL,
  `GenderRestriction` varchar(10) NOT NULL,
  `AgeUpperlimit` varchar(64) DEFAULT NULL,
  `AgeLowerlimit` varchar(64) DEFAULT NULL,
  `Description` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'您好','2020-01-01','資管系專題教室','coding','5','不限','0','999','元旦coding到死'),(2,'shit','2020-01-02','中央大學','大便','4','不限','10','20','好吃喔'),(3,'4555','2020-01-05','5645','慢跑','one','只限男性','one','one',''),(4,'55575676878','2020-01-02','686786','慢跑','one','只限男性','one','one',''),(5,'4535','2020-01-19','76855','慢跑','one','只限男性','one','one',''),(6,'5454','2020-01-14','455','慢跑','one','只限男性','one','one',''),(7,'45465','2020-01-07','4654654','慢跑','one','只限男性','one','one',''),(8,'5545','2020-01-05','4534535','慢跑','one','只限男性','one','one',''),(9,'454455','2020-01-13','2545242','慢跑','one','只限男性','one','one',''),(10,'45557','2020-01-16','57225','慢跑','one','只限男性','one','one',''),(11,'5345','2020-01-19','33535','慢跑','one','只限男性','one','nope',''),(12,'527537','2020-01-21','75275','慢跑','one','只限男性','one','one',''),(13,'4.45','2020-01-15','45454','慢跑','one','只限男性','one','one',''),(14,'45452544','2020-01-14','52452452','慢跑','one','性別不限','one','one',''),(15,'4554','2020-01-09','454545','慢跑','one','只限男性','one','one',''),(16,'4535435','2020-01-22','45354','慢跑','one','只限男性','one','one',''),(17,'54354','2020-01-23','4534534','慢跑','one','只限男性','one','one',''),(18,'4525','2020-01-08','452452','慢跑','one','只限男性','one','one',''),(19,'452452','2020-01-28','452452','慢跑','one','只限男性','one','one',''),(20,'43453','2020-01-07','453453','慢跑','one','只限男性','one','one',''),(21,'4534535','2020-01-14','45345353','慢跑','one','只限男性','one','one',''),(22,'4534534','2020-01-07','453534','慢跑','one','只限男性','one','one',''),(23,'543543543453','2020-01-21','453453453453','慢跑','one','只限男性','one','one',''),(24,'542542','2020-01-16','45245254452','慢跑','one','只限男性','one','one',''),(25,'4534','2020-01-22','4544','慢跑','one','只限男性','one','one',''),(26,'54245245','2020-01-26','452452542','慢跑','one','只限男性','one','one',''),(27,'43544554','2020-01-09','454554345353','慢跑','one','只限男性','one','one',''),(28,'4534534','2020-01-21','54345453','慢跑','one','只限男性','one','one',''),(29,'453453453','2020-01-15','213213213','慢跑','one','只限男性','one','one',''),(30,'4545345','2020-01-14','453434','慢跑','one','只限男性','one','one',''),(31,'524245','2020-01-21','4545','慢跑','one','只限男性','one','one',''),(32,'4545','2020-01-01','453435','慢跑','one','只限男性','one','one',''),(33,'453453','2020-01-22','453543','慢跑','one','只限男性','one','one',''),(34,'453554345','2020-01-14','4534535435','慢跑','one','性別不限','one','one',''),(35,'4524525','2020-01-24','45242452452','慢跑','one','只限女性','one','one',''),(36,'554456','2020-01-15','5433535535','慢跑','one','只限男性','one','one',''),(37,'4665456445','2020-01-21','546546455','慢跑','one','只限女性','one','one',''),(38,'4655456','2020-01-16','64546','慢跑','one','只限男性','one','one',''),(39,'有ㄅ','2020-01-15','4534535','慢跑','one','性別不限','one','one',''),(40,'453453','2020-01-13','351','慢跑','one','只限男性','one','one',''),(41,'有ㄇ','2020-01-20','4534535','慢跑','one','只限男性','one','one',''),(42,'贏了','2020-01-02','水','足球','one','只限女性','one','three',''),(43,'6666666666','2020-01-14','454','慢跑','one','只限男性','one','one',''),(44,'殼以','2020-01-09','宿舍','慢跑','one','只限女性','one','three','');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'missa'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-05 16:37:00
