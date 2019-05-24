-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               10.3.10-MariaDB-log - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5332
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for club-app
CREATE DATABASE IF NOT EXISTS `club-app` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `club-app`;

-- Dumping structure for table club-app.cotisations
CREATE TABLE IF NOT EXISTS `cotisations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `timeLimit` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table club-app.events
CREATE TABLE IF NOT EXISTS `events` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table club-app.notifications
CREATE TABLE IF NOT EXISTS `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL DEFAULT '0',
  `member_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table club-app.payments
CREATE TABLE IF NOT EXISTS `payments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT 0,
  `cotisation_id` int(11) DEFAULT 0,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table club-app.users
CREATE TABLE IF NOT EXISTS `users` (
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `level` varchar(50) DEFAULT NULL,
  `cne` varchar(50) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
