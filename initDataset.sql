CREATE DATABASE  IF NOT EXISTS `brafar` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `brafar`;

--
-- Table structure for table `problem`
--

DROP TABLE IF EXISTS `problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problem` (
  `id` int NOT NULL,
  `className` varchar(45) NOT NULL,
  `methodName` varchar(45) NOT NULL,
  `testClassName` varchar(45) NOT NULL,
  `referenceFilesDirPath` varchar(100) NOT NULL,
  `testFileDirPath` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem`
--

LOCK TABLES `problem` WRITE;
/*!40000 ALTER TABLE `problem` DISABLE KEYS */;
INSERT INTO `problem` VALUES (1001,'MiniFloat','miniFloatFromString','MiniFloatTest','/data2/cy/brafar/data/referenceFile/1001','/data2/cy/brafar/data/testFile/1001'),(1002,'SpecialNumber','isSpecial','SpecialNumberTest','/data2/cy/brafar/data/referenceFile/1002','/data2/cy/brafar/data/testFile/1002'),(1003,'BalancedBrackets','isBalanced','BalancedBracketsTest','/data2/cy/brafar/data/referenceFile/1003','/data2/cy/brafar/data/testFile/1003'),(1004,'ScientificNotation','getValueFromAeB','ScientificNotationTest','/data2/cy/brafar/data/referenceFile/1004','/data2/cy/brafar/data/testFile/1004'),(1005,'LDLettersRemoval','removeLDLetters','LDLettersRemovalTest','/data2/cy/brafar/data/referenceFile/1005','/data2/cy/brafar/data/testFile/1005'),(1006,'Base7','convertToBase7','Base7Test','/data2/cy/brafar/data/referenceFile/1006','/data2/cy/brafar/data/testFile/1006');
/*!40000 ALTER TABLE `problem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repairrequest`
--

DROP TABLE IF EXISTS `repairrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repairrequest` (
  `id` int NOT NULL AUTO_INCREMENT,
  `problemId` int NOT NULL,
  `uploadFileId` int NOT NULL,
  `resultPath` varchar(100) NOT NULL,
  `timeStamp` varchar(45) NOT NULL,
  `repairTime` int DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  `result` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `repairrequesttoproblem_idx` (`problemId`),
  KEY `repairrequesttouploadfile_idx` (`uploadFileId`),
  CONSTRAINT `repairrequesttoproblem` FOREIGN KEY (`problemId`) REFERENCES `problem` (`id`),
  CONSTRAINT `repairrequesttouploadfile` FOREIGN KEY (`uploadFileId`) REFERENCES `uploadfile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testfile`
--

DROP TABLE IF EXISTS `testfile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testfile` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `path` varchar(100) NOT NULL,
  `problemId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1_idx` (`problemId`),
  CONSTRAINT `testfiletoproblem` FOREIGN KEY (`problemId`) REFERENCES `problem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testfile`
--

LOCK TABLES `testfile` WRITE;
/*!40000 ALTER TABLE `testfile` DISABLE KEYS */;
INSERT INTO `testfile` VALUES (1001,'MiniFloatTest.java','/data2/cy/brafar/data/testFile/1001/MiniFloatTest.java',1001),(1002,'SpecialNumberTest.java','/data2/cy/brafar/data/testFile/1002/SpecialNumberTest.java',1002),(1003,'BalancedBracketsTest.java','/data2/cy/brafar/data/testFile/1003/BalancedBracketsTest.java',1003),(1004,'ScientificNotationTest.java','/data2/cy/brafar/data/testFile/1004/ScientificNotationTest.java',1004),(1005,'LDLettersRemovalTest.java','/data2/cy/brafar/data/testFile/1005/LDLettersRemovalTest.java',1005),(1006,'Base7Test.java','/data2/cy/brafar/data/testFile/1006/Base7Test.java',1006);
/*!40000 ALTER TABLE `testfile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uploadfile`
--

DROP TABLE IF EXISTS `uploadfile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uploadfile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `path` varchar(100) NOT NULL,
  `problemId` int NOT NULL,
  `refactored` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1_idx` (`problemId`),
  CONSTRAINT `uploadfiletoproblem` FOREIGN KEY (`problemId`) REFERENCES `problem` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


