-- MySQL dump 10.13  Distrib 5.6.10, for Win64 (x86_64)
--
-- Host: localhost    Database: nwserver
-- ------------------------------------------------------
-- Server version	5.6.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES cp1251 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `nwserver`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `nwserver` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `nwserver`;

--
-- Table structure for table `compiler`
--

DROP TABLE IF EXISTS `compiler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compiler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compiler`
--

LOCK TABLES `compiler` WRITE;
/*!40000 ALTER TABLE `compiler` DISABLE KEYS */;
INSERT INTO `compiler` VALUES (1,'Java'),(2,'C#'),(3,'C++'),(4,'Ruby'),(5,'Python');
/*!40000 ALTER TABLE `compiler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contest`
--

DROP TABLE IF EXISTS `contest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `duration` datetime DEFAULT NULL,
  `hidden` bit(1) DEFAULT NULL,
  `starts` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contest`
--

LOCK TABLES `contest` WRITE;
/*!40000 ALTER TABLE `contest` DISABLE KEYS */;
INSERT INTO `contest` VALUES (1,'This is new contest for publication.','1970-01-01 01:00:00','\0','2015-03-11 11:00:00','PREPARING','New Contest 1'),(2,'This is new contest for publication.','1970-01-01 01:30:00','','2015-03-11 12:00:00','PREPARING','New Contest 2'),(3,'This is new contest for publication.','1970-01-01 02:00:00','\0','2015-03-11 13:00:00','PREPARING','New Contest 3'),(4,'This is new contest for publication.','1970-01-01 02:30:00','','2015-03-11 14:00:00','PREPARING','New Contest 4'),(5,'This is new contest for publication.','1970-01-01 03:00:00','\0','2015-03-11 15:00:00','PREPARING','New Contest 5'),(6,'This competition has already passed.','1970-01-01 01:00:00','\0','2015-03-03 11:00:00','ARCHIVE','Old Contest 1'),(7,'This competition has already passed.','1970-01-01 01:30:00','','2015-03-03 12:00:00','ARCHIVE','Old Contest 2'),(8,'This competition has already passed.','1970-01-01 02:00:00','\0','2015-03-03 13:00:00','ARCHIVE','Old Contest 3'),(9,'<p>dpij[fja]pisd</p>\r\n','1970-01-01 00:10:00','\0','2015-04-28 13:56:00','GOING','Debug Contest 5634');
/*!40000 ALTER TABLE `contest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contest_users`
--

DROP TABLE IF EXISTS `contest_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contest_users` (
  `user_id` int(11) NOT NULL,
  `contest_id` int(11) NOT NULL,
  KEY `FK_87l014fh6rxdjjvi162vmpjp5` (`contest_id`),
  KEY `FK_ai4ifvl80ubnyoyjts9cbxw1s` (`user_id`),
  CONSTRAINT `FK_87l014fh6rxdjjvi162vmpjp5` FOREIGN KEY (`contest_id`) REFERENCES `contest` (`id`),
  CONSTRAINT `FK_ai4ifvl80ubnyoyjts9cbxw1s` FOREIGN KEY (`user_id`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contest_users`
--

LOCK TABLES `contest_users` WRITE;
/*!40000 ALTER TABLE `contest_users` DISABLE KEYS */;
INSERT INTO `contest_users` VALUES (2,1),(2,2),(3,2),(2,3),(3,3),(2,4),(3,4),(3,5),(2,6),(2,7),(3,7),(3,8),(12,9);
/*!40000 ALTER TABLE `contest_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contestpass`
--

DROP TABLE IF EXISTS `contestpass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contestpass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contestStatus` varchar(255) DEFAULT NULL,
  `passed` int(11) DEFAULT NULL,
  `passing` bit(1) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `timePenalty` int(11) DEFAULT NULL,
  `contest_id` int(11) DEFAULT NULL,
  `user_userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_efuuemma2744dnxypo9atkray` (`contest_id`),
  KEY `FK_msifr8gjjgti2kgkyekwpp8v5` (`user_userId`),
  CONSTRAINT `FK_efuuemma2744dnxypo9atkray` FOREIGN KEY (`contest_id`) REFERENCES `contest` (`id`),
  CONSTRAINT `FK_msifr8gjjgti2kgkyekwpp8v5` FOREIGN KEY (`user_userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contestpass`
--

LOCK TABLES `contestpass` WRITE;
/*!40000 ALTER TABLE `contestpass` DISABLE KEYS */;
INSERT INTO `contestpass` VALUES (1,NULL,4,'\0',3,30,6,7),(2,NULL,5,'\0',2,40,6,8),(3,NULL,3,'\0',4,10,6,9),(4,NULL,3,'\0',5,60,6,10),(5,NULL,5,'\0',1,20,6,11),(6,NULL,5,'\0',3,30,7,7),(7,NULL,6,'\0',1,40,7,8),(8,NULL,3,'\0',5,10,7,9),(9,NULL,4,'\0',4,60,7,10),(10,NULL,5,'\0',2,20,7,11),(11,NULL,7,'\0',2,30,8,7),(12,NULL,6,'\0',3,40,8,8),(13,NULL,7,'\0',1,10,8,9),(14,NULL,3,'\0',5,60,8,10),(15,NULL,5,'\0',4,20,8,11),(16,NULL,0,'\0',0,0,9,13);
/*!40000 ALTER TABLE `contestpass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('1','ReaktorDTR','classpath:/liquibase/changelog-0.0.1.xml','2015-04-28 13:45:28',1,'EXECUTED','7:b4aa50ed752ad658418c76c40a3b650b','sql','Insert Users to DB',NULL,'3.3.2'),('2','Kros05','classpath:/liquibase/changelog-0.0.2.xml','2015-04-28 13:45:28',2,'EXECUTED','7:ef793470d4cc7312d704952058a15f8e','sql','Insert Contests to DB',NULL,'3.3.2');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  KEY `FK_ln2j4xo19jnujn0wyhfnbc45t` (`userId`),
  CONSTRAINT `FK_ln2j4xo19jnujn0wyhfnbc45t` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN',1),(2,'ROLE_TEACHER',2),(3,'ROLE_TEACHER',3),(4,'ROLE_USER',4),(5,'ROLE_USER',5),(6,'ROLE_USER',6),(7,'ROLE_USER',7),(8,'ROLE_USER',8),(9,'ROLE_USER',9),(10,'ROLE_USER',10),(11,'ROLE_USER',11),(12,'ROLE_ADMIN',12),(13,'ROLE_USER',13),(14,'ROLE_TEACHER',12);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `complexity` int(11) DEFAULT NULL,
  `description` text NOT NULL,
  `forumLink` longtext,
  `inputFileName` varchar(60) NOT NULL,
  `memoryLimit` int(11) DEFAULT NULL,
  `outputFileName` varchar(60) NOT NULL,
  `rate` int(11) NOT NULL,
  `scriptForVerification` text,
  `timeLimit` int(11) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `contest_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pe4wan8hlvuf04qf6jx9cq6t5` (`contest_id`),
  CONSTRAINT `FK_pe4wan8hlvuf04qf6jx9cq6t5` FOREIGN KEY (`contest_id`) REFERENCES `contest` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,5,'This is task 1.',NULL,'inputFile1',5000000,'outputFile1',5,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 1',1),(2,3,'This is task 2.',NULL,'inputFile2',5000000,'outputFile2',5,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 2',1),(3,2,'This is task 3.',NULL,'inputFile3',5000000,'outputFile3',4,'%%%% SOME VERIFICATION SCRIPT %%%',20000,'Task 3',1),(4,1,'This is task 4.',NULL,'inputFile4',4000000,'outputFile4',4,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 4',1),(5,3,'This is task 5.',NULL,'inputFile5',4000000,'outputFile5',2,'%%%% SOME VERIFICATION SCRIPT %%%',20000,'Task 5',1),(6,8,'This is task 1.',NULL,'inputFile1',5000000,'outputFile1',8,'%%%% SOME VERIFICATION SCRIPT %%%',20000,'Task 1',2),(7,5,'This is task 2.',NULL,'inputFile2',6000000,'outputFile2',9,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 2',2),(8,3,'This is task 3.',NULL,'inputFile3',7000000,'outputFile3',3,'%%%% SOME VERIFICATION SCRIPT %%%',30000,'Task 3',2),(9,5,'This is task 4.',NULL,'inputFile4',8000000,'outputFile4',4,'%%%% SOME VERIFICATION SCRIPT %%%',40000,'Task 4',2),(10,7,'This is task 5.',NULL,'inputFile5',5000000,'outputFile5',5,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 5',2),(11,5,'This is task 1.',NULL,'inputFile1',5000000,'outputFile1',4,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 1',3),(12,4,'This is task 2.',NULL,'inputFile2',9000000,'outputFile2',4,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 2',3),(13,1,'This is task 3.',NULL,'inputFile3',50000,'outputFile3',1,'%%%% SOME VERIFICATION SCRIPT %%%',1000,'Task 3',3),(14,2,'This is task 4.',NULL,'inputFile4',3000000,'outputFile4',1,'%%%% SOME VERIFICATION SCRIPT %%%',1000,'Task 4',3),(15,2,'This is task 5.',NULL,'inputFile5',5000000,'outputFile5',3,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 5',3),(16,7,'This is task 1.',NULL,'inputFile1',7000000,'outputFile1',5,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 1',4),(17,4,'This is task 2.',NULL,'inputFile2',5000000,'outputFile2',6,'%%%% SOME VERIFICATION SCRIPT %%%',30000,'Task 2',4),(18,8,'This is task 3.',NULL,'inputFile3',10000000,'outputFile3',7,'%%%% SOME VERIFICATION SCRIPT %%%',40000,'Task 3',4),(19,4,'This is task 4.',NULL,'inputFile4',5000000,'outputFile4',9,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 4',4),(20,4,'This is task 5.',NULL,'inputFile5',9000000,'outputFile5',4,'%%%% SOME VERIFICATION SCRIPT %%%',30000,'Task 5',4),(21,3,'This is task 1.',NULL,'inputFile1',5000000,'outputFile1',3,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 1',5),(22,2,'This is task 2.',NULL,'inputFile2',5000000,'outputFile2',2,'%%%% SOME VERIFICATION SCRIPT %%%',60000,'Task 2',5),(23,5,'This is task 3.',NULL,'inputFile3',6000000,'outputFile3',1,'%%%% SOME VERIFICATION SCRIPT %%%',10500,'Task 3',5),(24,1,'This is task 4.',NULL,'inputFile4',5000000,'outputFile4',2,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 4',5),(25,6,'This is task 5.',NULL,'inputFile5',3000000,'outputFile5',5,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 5',5),(26,1,'This is task 1.',NULL,'inputFile1',5000000,'outputFile1',2,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 1',6),(27,3,'This is task 2.',NULL,'inputFile2',5000000,'outputFile2',5,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 2',6),(28,5,'This is task 3.',NULL,'inputFile3',5000000,'outputFile3',4,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 3',6),(29,5,'This is task 4.',NULL,'inputFile4',5000000,'outputFile4',6,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 4',6),(30,5,'This is task 5.',NULL,'inputFile5',5000000,'outputFile5',6,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 5',6),(31,7,'This is task 1.',NULL,'inputFile1',5000000,'outputFile1',6,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 1',7),(32,8,'This is task 2.',NULL,'inputFile2',5000000,'outputFile2',9,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 2',7),(33,3,'This is task 3.',NULL,'inputFile3',5000000,'outputFile3',1,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 3',7),(34,1,'This is task 4.',NULL,'inputFile4',5000000,'outputFile4',4,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 4',7),(35,7,'This is task 5.',NULL,'inputFile5',5000000,'outputFile5',6,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 5',7),(36,8,'This is task 6.',NULL,'inputFile6',5000000,'outputFile6',5,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 6',7),(37,5,'This is task 1.',NULL,'inputFile1',5000000,'outputFile1',5,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 1',8),(38,2,'This is task 2.',NULL,'inputFile2',5000000,'outputFile2',6,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 2',8),(39,4,'This is task 3.',NULL,'inputFile3',5000000,'outputFile3',3,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 3',8),(40,1,'This is task 4.',NULL,'inputFile4',5000000,'outputFile4',3,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 4',8),(41,1,'This is task 5.',NULL,'inputFile5',5000000,'outputFile5',2,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 5',8),(42,2,'This is task 6.',NULL,'inputFile6',5000000,'outputFile6',3,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 6',8),(43,3,'This is task 7.',NULL,'inputFile7',5000000,'outputFile7',5,'%%%% SOME VERIFICATION SCRIPT %%%',10000,'Task 7',8),(44,0,'<p>pasd f[asd[f]das f]ias</p>\r\n',NULL,'title.in',0,'out',0,'',0,'Debug task',9);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taskdata`
--

DROP TABLE IF EXISTS `taskdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taskdata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inputData` longblob,
  `outputData` longblob,
  `task_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1gtescuwfkv94ukmqnx10idox` (`task_id`),
  CONSTRAINT `FK_1gtescuwfkv94ukmqnx10idox` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taskdata`
--

LOCK TABLES `taskdata` WRITE;
/*!40000 ALTER TABLE `taskdata` DISABLE KEYS */;
/*!40000 ALTER TABLE `taskdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taskimages`
--

DROP TABLE IF EXISTS `taskimages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taskimages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` tinyblob,
  `id_task` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_itpnejh8mx35u6cucn3yq1bcg` (`id_task`),
  CONSTRAINT `FK_itpnejh8mx35u6cucn3yq1bcg` FOREIGN KEY (`id_task`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taskimages`
--

LOCK TABLES `taskimages` WRITE;
/*!40000 ALTER TABLE `taskimages` DISABLE KEYS */;
/*!40000 ALTER TABLE `taskimages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taskpass`
--

DROP TABLE IF EXISTS `taskpass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taskpass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `compilerMessage` varchar(255) DEFAULT NULL,
  `executionTime` int(11) DEFAULT NULL,
  `file` longblob,
  `memoryUsed` int(11) DEFAULT NULL,
  `passed` bit(1) DEFAULT NULL,
  `passedMinute` int(11) DEFAULT NULL,
  `contestPass` int(11) DEFAULT NULL,
  `task_id` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kyir2295mt8pc9yl7duh26fno` (`contestPass`),
  KEY `FK_ndrtj4ux10eejhmm344x00fj9` (`task_id`),
  KEY `FK_pek0fykipld0l3bj0l3c1d81l` (`user`),
  CONSTRAINT `FK_kyir2295mt8pc9yl7duh26fno` FOREIGN KEY (`contestPass`) REFERENCES `contestpass` (`id`),
  CONSTRAINT `FK_ndrtj4ux10eejhmm344x00fj9` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`),
  CONSTRAINT `FK_pek0fykipld0l3bj0l3c1d81l` FOREIGN KEY (`user`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taskpass`
--

LOCK TABLES `taskpass` WRITE;
/*!40000 ALTER TABLE `taskpass` DISABLE KEYS */;
INSERT INTO `taskpass` VALUES (1,'Compiler error',3281,'ˇÿˇ‡\0JFIF\0\0H\0H\0\0ˇ€\0C\0		\n\r	\n\n\r	\rˇ€\0C		ˇ¿\0\02\02\"\0ˇƒ\0\0\0\0\0\0\0\0\0\0\0\0\0\0	\nˇƒ\0/\0\0\0\0\0\0\0!1AQ\"BaÅ	23ëqˇƒ\0\0\0\0\0\0\0\0\0\0\0\0\0\0ˇƒ\0-\0\0\0\0\0\0\0\0\0\0!1AQaqÅ°\"$R±¡Òˇ⁄\0\0\0?\0È=µÖr∏–Yˆı}⁄ÈU≤äôÍjÍfnTÜ$RŒÏ~\0Î;P©ç¡KlÙç∫∂˘Q5€t–Og∂ƒœ °‰åÛ «ÑçzúI*<ÎÄd•˛nm∂?Q\0¯ü†Ãtä(l©\"Áo{DE∑ΩypqÒæ”≥ËÌ7˙ZKç¡(©ØïëC7;∞TvNrÍÑë‘ı\0‰Å◊≥§–aeö/2D¡◊˛åçPµÉ`\Zj+^ÿ§€¥€√yPSU^û’§$joˆ2â[G’ùÜI8◊±∞∏øVoØWgÇÂ∑Â•Uñ¢·eíXñï)è¢°fUÀ˝9 uŒ4Ê©Ù\\õÈS;íîåﬂÒ_8P»\"˛Ω≠J‘\\m\nqiÔ6∞»µ∆ác˝ã”«c◊∑ÁI™ù‡xô¥˝~€¨‹Oﬁó€dﬂRIGÀX”«Ip)ÂX±à∫·@\0W>V…•}féıÙ∂µÖ§((hv:A9+2&R´§§§ÿÉ®ﬂÓ!Ω?˜Fù£U∏ﬁÖÂ:‡T;∫€q„]Æ≈Ac`¢ñ\Z†≈UML‹íîLü©ñ$NoÇq‡‚¿±ûü=5B^ØwÇ^ΩhnÎ}≤\nö*z;ºè*ŒG;TH‰#nc ÁAÌ¶GES\rJ™Fù©&¸_…\rRûDÉ¬/ú∫∆Ìu§ØûŸ{ºÏÎú÷mÀYií’<ÎR¨¥Ã¿ÀO2¸:ÜÈ≠_á±ÒB√¬ mzyl÷\Z=Ÿ4uı?•I&ñò	˚f	’%eÍ√Í»ÂeVŒŸéπxg7lµ’Ë£5ç\'WwÂ˚èìåüç{ä™à\"ÅÄ™0\0ˇ\0Õ5ÖA˘d*]6PãËvÒé#\ZÈí≥ãKÓ	ø>~±7˙g€÷[Ø´ÖπURRV›6˛€özwôU¶ß3K)\"É‘í˝C∂Olı±~S™o·•=‚Íã¬\ZΩ•ü∏K1[ÒÖàSDUÉ˚ﬁ0bW¿= .:Å´ì\0‡|ÈA’å)©∂VWp¥È Åâπ\ZÈ≤Œw∏Í{m⁄´c|Ω∞aºßFüç\Z†DÑ?P7∏≥xè`íı˚%\r˜∑#‘€Ó€cyÂeF>ÃÄ`»Øÿyï∞T˜|«mà#∏ÌçnJÕ?&uï®q˚q÷Ñ∏,°x˘ÌN<*L—÷mi#u8pïΩAÚ0SÁ]ëÈ«`ˇ\0∂e√uIpìkÌ€}––O\nGÔU‘8ç$>€ê#E√ÅÃC~ﬁ«]k∏}#prÒBøw\\6`äÈ_+M[=K•<Ú?7<Ü#ïVbŸ<†ååÊgÿ€mÁÜ›°¥ÌÈn≤—1∆™ºŒŒ≈ô‹Ä9òì‘˜¿\Zk’˙ûåÙç©≠)\\úÅÕÆH<yà)Fj…{Û.Ö#:\0	„@-\r⁄õiÏãv˝µcß∑F£Îüì‹®ùèvífÀªí\0”[où?Fm(q«VV≤I;ú≈Ä\0ëa—ßÙ¸h÷8ˆª¯–?«FçAÁK£Fà!<:<Ë—¢]\Z4hÇ?ˇŸ',2001,'\0',0,16,44,13);
/*!40000 ALTER TABLE `taskpass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasktheorylink`
--

DROP TABLE IF EXISTS `tasktheorylink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasktheorylink` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(255) DEFAULT NULL,
  `task_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_aa9wynvp02u15a7508qwxgxxy` (`task_id`),
  CONSTRAINT `FK_aa9wynvp02u15a7508qwxgxxy` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasktheorylink`
--

LOCK TABLES `tasktheorylink` WRITE;
/*!40000 ALTER TABLE `tasktheorylink` DISABLE KEYS */;
/*!40000 ALTER TABLE `tasktheorylink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrequest`
--

DROP TABLE IF EXISTS `userrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userrequest` (
  `requestId` int(11) NOT NULL AUTO_INCREMENT,
  `request` varchar(255) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`requestId`),
  KEY `FK_1qdb29txclgp3bgk1ov02dstd` (`userId`),
  CONSTRAINT `FK_1qdb29txclgp3bgk1ov02dstd` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrequest`
--

LOCK TABLES `userrequest` WRITE;
/*!40000 ALTER TABLE `userrequest` DISABLE KEYS */;
INSERT INTO `userrequest` VALUES (1,'WANT_ROLE_TEACHER',4),(2,'WANT_ROLE_TEACHER',5),(3,'WANT_ROLE_TEACHER',6),(4,'WANT_ROLE_TEACHER',8),(5,'WANT_ROLE_TEACHER',10);
/*!40000 ALTER TABLE `userrequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `ban_time` bigint(20) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,0,'Administrators','Admin','admin@ua.ua','','Info about Admin','$2a$10$5uHAIiMg8TmCrsG8pLGNH.grGCujfeUK/YeMEqExkWHc//BTt7dwq','admin'),(2,0,'Mathematics','Teacher1','Teacher1@ua.ua','','Info about Teacher1','$2a$10$vEKi2nDhEZVthsCbZDFgN.gwYomwexzaD9hKbfjPjGthuemV2fyk6','teacher1'),(3,0,'Computer science','Teacher2','Teacher2@ua.ua','','Info about Teacher2','$2a$10$rlwAd/.KJcsdIT.IYhNj9OBC3p9gVmoFc61GJak8HGPKvs1vCjUf6','teacher2'),(4,0,'Computer science','Teacher3','Teacher3@ua.ua','','Info about Teacher3','$2a$10$xYzwYxth//WXSFxThk4p5.xNWdjLr9lv81H5dndNoErUOyZS/xi7i','teacher3'),(5,0,'Computer science','Teacher4','Teacher4@ua.ua','','Info about Teacher4','$2a$10$2Gjo9o1LfLzSIXsEYlD.Duau92FBarbw9WX5rAkJMPaTKpylw9wZK','teacher4'),(6,0,'Mathematics','Teacher5','Teacher5@ua.ua','','Info about Teacher5','$2a$10$jiB0zPkSBMOtgId/SmuUweCW6j8WdxrTQxjpm.XvtOELa2SuHroty','teacher5'),(7,0,'','User1','User1@ua.ua','','Info about User1','$2a$10$2B8iOyGDQFupeR6UYmfC3OCyL6//u8Ce4t6fCu0DPVqthwA9VGMui','user1'),(8,0,'','User2','User2@ua.ua','','Info about User2','$$2a$10$nn2DKdcpyvyFmbU1jYIz0.rurMhf9GLNRVT.5DplLvYzdgKtOi9bO','user2'),(9,0,'','User3','User3@ua.ua','','Info about User3','$2a$10$MgXJlCSLiRRbss.UJsPd/emuJd5wkux3Jf6bkA89IpUvAhz.rdDsy','user3'),(10,0,'','User4','User4@ua.ua','','Info about User4','$2a$10$7Wz.kkKH9c.rQJXNVFbr6OjzedzpmfmEYJrkJWEoOQPrgGKEoyLCu','user4'),(11,0,'','User5','User5@ua.ua','','Info about User5','$2a$10$/Rp3skF43HIw2mjEanwV4.98yjcpEcs5La3L7q/6lrgWHmSzyyTLe','user5'),(12,0,NULL,'root','root@root.com','',NULL,'$2a$10$gJwTaPHgUoaZeO9.tNZvRuk2V3sF5yTl9sCSWM8/sE8LzWf3NdAou','root'),(13,0,NULL,'notRoot','not@not.com','',NULL,'$2a$10$1l1xGBEL0M5KxL0TiG6WcuOzvhNGn3DmBgbVsxuleQ.bT73wKu2Ii','notRoot');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-28 14:04:31
