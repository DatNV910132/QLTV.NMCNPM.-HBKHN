-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: qltv
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `thuthu`
--

DROP TABLE IF EXISTS `thuthu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
<<<<<<< HEAD
CREATE TABLE `thuthu` (
  `MaNV` char(4) NOT NULL,
  `TenNV` varchar(30) DEFAULT NULL,
  `Ngaysinh` date DEFAULT NULL,
  `Gioitinh` varchar(5) DEFAULT NULL,
  `Diachi` varchar(30) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `Email` varchar(30) DEFAULT NULL,
  `Luong` int(11) DEFAULT NULL,
  `TenTaiKhoan` varchar(30) DEFAULT NULL,
  `Matkhau` varchar(30) DEFAULT NULL,
  `VaiTro` int DEFAULT NULL,
  PRIMARY KEY (`MaNV`)
=======
CREATE TABLE thuthu (
  MaNV char(4) NOT NULL,
  TenNV varchar(30) DEFAULT NULL,
  Ngaysinh date DEFAULT NULL,
  Gioitinh varchar(5) DEFAULT NULL,
  Diachi varchar(30) DEFAULT NULL,
  Phone varchar(15) DEFAULT NULL,
  Email varchar(30) DEFAULT NULL,
  Thoigianlamviec int(11) DEFAULT NULL,
  Luong int(11) DEFAULT NULL,
  Matkhau varchar(30) DEFAULT NULL,
  PRIMARY KEY (MaNV)
>>>>>>> 2784d2e6fe8bc967fa947b42dc6b00f356399932
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thuthu`
--

LOCK TABLES `thuthu` WRITE;
/*!40000 ALTER TABLE `thuthu` DISABLE KEYS */;
/*!40000 ALTER TABLE `thuthu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-19 10:42:10
