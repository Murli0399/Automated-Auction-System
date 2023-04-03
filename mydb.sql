-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: SB101_Project
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auction`
--

DROP TABLE IF EXISTS `auction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auction` (
  `auction_id` int NOT NULL AUTO_INCREMENT,
  `seller_id` int NOT NULL,
  `product_id` int NOT NULL,
  `price` double(10,2) NOT NULL,
  `date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `buyer_id` int DEFAULT '0',
  `is_sold` int DEFAULT '0',
  `is_deleted` int DEFAULT '0',
  PRIMARY KEY (`auction_id`),
  KEY `seller_id` (`seller_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `auction_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`sellerId`),
  CONSTRAINT `auction_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction`
--

LOCK TABLES `auction` WRITE;
/*!40000 ALTER TABLE `auction` DISABLE KEYS */;
INSERT INTO `auction` VALUES (1,8,4,250.50,'2023-03-31','11:00:00','13:00:00',0,0,0),(2,8,2,35000.00,'2023-04-01','11:00:00','12:00:00',0,0,0),(3,3,1,14500.00,'2023-03-31','13:00:00','14:00:00',0,0,0),(4,8,4,350.00,'2023-03-31','15:00:00','23:00:00',1,0,0),(5,8,2,35000.00,'2023-04-03','12:00:00','20:00:00',0,0,0),(6,8,2,55000.00,'2023-04-01','12:00:00','14:00:00',8,0,0),(7,8,3,150.00,'2023-04-03','14:00:00','20:00:00',0,0,0);
/*!40000 ALTER TABLE `auction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyer`
--

DROP TABLE IF EXISTS `buyer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyer` (
  `buyerId` int NOT NULL AUTO_INCREMENT,
  `buyerName` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `mobileNo` varchar(10) DEFAULT NULL,
  `username` varchar(25) DEFAULT NULL,
  `password` varchar(8) DEFAULT NULL,
  `is_deleted` int DEFAULT '0',
  PRIMARY KEY (`buyerId`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyer`
--

LOCK TABLES `buyer` WRITE;
/*!40000 ALTER TABLE `buyer` DISABLE KEYS */;
INSERT INTO `buyer` VALUES (1,'Murli Khaire','Khandwa, Madhya Pradesh','8462858556','buyer123','151515',0),(2,'Murli','khandwa','8462858556','murli123','123456',0),(6,'Murli','indore','8462858556','123456','55555',0),(8,'Rishab','Dhanbad','9898989898','rishab123','123456',1);
/*!40000 ALTER TABLE `buyer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (101,'Clothing'),(102,'Electronics'),(103,'Grocery'),(104,'Miscellaneous');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `seller_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL,
  `sold_status` int NOT NULL DEFAULT '0',
  `category_id` int NOT NULL,
  `is_deleted` int DEFAULT '0',
  PRIMARY KEY (`product_id`),
  KEY `seller_id` (`seller_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`sellerId`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,3,'Monitor',14500.00,1,1,102,0),(2,8,'Laptop',33500.00,23,1,102,0),(3,8,'Mouse',150.00,13,0,102,0),(4,8,'keyboard',250.50,0,0,102,0),(5,8,'T-Shirt',300.00,1,1,101,0),(6,1,'Mobile',13500.00,27,1,102,0),(7,9,'Oppo Mobile ',12500.00,50,0,102,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller`
--

DROP TABLE IF EXISTS `seller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seller` (
  `sellerId` int NOT NULL AUTO_INCREMENT,
  `sellerName` varchar(50) NOT NULL,
  `address` varchar(255) NOT NULL,
  `mobileNo` varchar(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(8) NOT NULL,
  `is_deleted` int DEFAULT '0',
  PRIMARY KEY (`sellerId`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller`
--

LOCK TABLES `seller` WRITE;
/*!40000 ALTER TABLE `seller` DISABLE KEYS */;
INSERT INTO `seller` VALUES (1,'murli','khandwa','8462858556','murli123','123456',0),(2,'murli khaire','ahamdpur khaigaon','8462858556','murlikhaire','123456',0),(3,'rahul','pandhana','9174169595','rahulpatel','123456',0),(4,'mohit','indore','9617794003','mohitviswakarma','123456',0),(5,'Shalu','khandwa','7878787878','shalugour','123456',1),(6,'Yagyavi Markandey','Burhanpur','6598569856','yagyvi','123456',0),(7,'murli khaire','khandwa','8462858556','mkhaire','123456',0),(8,'Shyam Khaire','Bhopal','1231231231','shyam123','shyam123',1),(9,'murli','khandwa','8462858556','murli111','123456',1),(12,'Murli Khaire','Indore','8462858556','123456','123456',0);
/*!40000 ALTER TABLE `seller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `seller_id` int NOT NULL,
  `buyer_id` int NOT NULL,
  `item_id` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL,
  `transaction_date` date DEFAULT NULL,
  `is_returned` int DEFAULT '0',
  `is_deleted` int DEFAULT '0',
  PRIMARY KEY (`transaction_id`),
  KEY `seller_id` (`seller_id`),
  KEY `buyer_id` (`buyer_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`sellerId`),
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`buyer_id`) REFERENCES `buyer` (`buyerId`),
  CONSTRAINT `transaction_ibfk_3` FOREIGN KEY (`item_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,8,1,4,275.55,1,'2023-03-30',1,0),(2,8,1,2,38500.00,1,'2023-03-30',0,0),(3,8,1,4,275.55,1,'2023-03-30',0,0),(4,3,1,1,11000.00,1,'2023-03-30',1,0),(5,8,1,4,275.55,1,'2023-03-31',1,0),(6,8,1,4,275.55,1,'2023-03-31',0,0),(7,8,1,4,275.55,1,'2023-03-31',1,0),(8,3,1,1,15950.00,1,'2023-03-31',1,0),(9,8,1,2,38500.00,1,'2023-03-31',0,0),(10,3,6,1,15950.00,1,'2023-03-31',0,0),(11,8,6,2,38500.00,1,'2023-04-01',0,0),(12,8,1,2,38500.00,1,'2023-04-01',1,0),(13,3,1,1,15950.00,6,'2023-04-01',1,0),(14,8,6,5,990.00,3,'2023-04-01',0,0),(15,8,1,5,600.00,2,'2023-04-01',0,0),(16,8,1,5,600.00,2,'2023-04-01',1,0),(17,1,1,6,27000.00,2,'2023-04-01',0,0),(18,8,1,5,300.00,1,'2023-04-02',0,0),(19,1,1,6,54000.00,4,'2023-04-02',0,0),(20,8,1,5,300.00,1,'2023-04-02',0,0),(21,1,1,6,13500.00,1,'2023-04-02',0,0),(22,1,1,6,13500.00,1,'2023-04-02',0,0),(23,1,1,6,13500.00,1,'2023-04-03',0,0);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-03 15:58:40
