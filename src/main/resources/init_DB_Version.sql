/*
SQLyog Community Edition- MySQL GUI v8.17 
MySQL - 5.1.37-community : Database - qualitysystemmetrics
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`BusSystem` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `BusSystem`;

/*Table structure for table `cpd_error` */

DROP TABLE IF EXISTS `chain`;

CREATE TABLE `chain` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT, 
  `name` varchar(255) DEFAULT NULL,
  `startAt` Time,
  `finishAt` TIME,
  `city` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `station`;

CREATE TABLE `station` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `x` float(6,2) DEFAULT NULL,
  `y` float(6,2) DEFAULT NULL,
  `nearStation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `chain_station`;

CREATE TABLE `chain_station` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `chain` int(10) NOT NULL,
  `station` int(10) NOT NULL,
  `position` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
