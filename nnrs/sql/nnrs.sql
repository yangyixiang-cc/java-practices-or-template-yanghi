-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: nnrs
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `a_id` varchar(16) NOT NULL,
  `a_name` varchar(16) NOT NULL,
  `a_password` varchar(100) NOT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES ('123456','yanghi','e10adc3949ba59abbe56e057f20f883e');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `u_id` varchar(16) NOT NULL,
  `n_id` varchar(16) NOT NULL,
  `read_chapters` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES ('123456','1227102837998395',1),('123456','1227102837998395',1),('123456','1227102837998395',1),('123456','1227102837998395',1),('123456','1227102837998395',1),('123456','1227102837998395',1),('123456','1227102837998395',10),('123456','1227102837968698',1),('123456','1227102837968698',2),('123456','1227102837968698',3),('123456','1227102837968698',4),('123456','1227102837968698',3),('123456','1227102837968698',2),('123456','1227102837968698',1),('123456','1227102837968698',19),('123456','1227102837968698',1);
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `novel_information`
--

DROP TABLE IF EXISTS `novel_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `novel_information` (
  `n_id` varchar(16) NOT NULL,
  `n_name` varchar(100) NOT NULL,
  `chapter_num` int NOT NULL,
  `visit_num` int NOT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `novel_information`
--

LOCK TABLES `novel_information` WRITE;
/*!40000 ALTER TABLE `novel_information` DISABLE KEYS */;
INSERT INTO `novel_information` VALUES ('1227102837968698','藏海花',58,4),('1227102837991677','寒夜',30,0),('1227102837993294','绝代双骄',128,0),('1227102837996116','平凡的世界',54,0),('1227102837998395','战争风云',64,0),('1227103037545896','球状闪电',32,0);
/*!40000 ALTER TABLE `novel_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `u_id` varchar(16) NOT NULL,
  `u_name` varchar(50) NOT NULL,
  `u_password` varchar(100) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `date` date NOT NULL,
  PRIMARY KEY (`u_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1111','1111','b59c67bf196a4758191e42f76670ceba',1,'2021-12-29'),('11111','11111','96e79218965eb72c92a549dd5a330112',1,'2021-12-29'),('111111','111111','96e79218965eb72c92a549dd5a330112',0,'2021-12-29'),('123456','123456','e10adc3949ba59abbe56e057f20f883e',1,'2021-12-27'),('2','22222','3d2172418ce305c7d16d4b05597c6a59',0,'2021-12-29');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-29 20:37:26
