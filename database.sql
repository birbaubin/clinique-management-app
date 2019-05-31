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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Dumping data for table club-app.cotisations: ~7 rows (approximately)
/*!40000 ALTER TABLE `cotisations` DISABLE KEYS */;
INSERT INTO `cotisations` (`id`, `amount`, `description`, `timeLimit`) VALUES
	(2, 1000, 'Payer un gamous', '2019-06-06 00:00:00'),
	(3, 1000, 'Payer un gamous', '2019-06-06 00:00:00'),
	(4, 1478, 'qsdklgjgjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj', '2019-05-01 00:00:00'),
	(5, 1007, 'Payer un gamous', '2019-06-06 00:00:00'),
	(6, 1007, 'Payer un gamous', '2019-06-06 00:00:00'),
	(7, 14, '78', '2019-05-02 00:00:00'),
	(8, 17000, 'riine', '2019-04-29 00:00:00'),
	(9, 1000, 'none', '2019-05-03 00:00:00');
/*!40000 ALTER TABLE `cotisations` ENABLE KEYS */;

-- Dumping structure for table club-app.events
CREATE TABLE IF NOT EXISTS `events` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Dumping data for table club-app.events: ~3 rows (approximately)
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` (`id`, `name`, `description`, `date`) VALUES
	(8, 'party', 'b there', '2019-05-06 00:00:00'),
	(12, 'bip party', 'be tehre', '2019-04-28 00:00:00'),
	(13, 'kijhjhfgdg', 'nbbngxcghxfg', '2019-05-02 00:00:00');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;

-- Dumping structure for table club-app.notifications
CREATE TABLE IF NOT EXISTS `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
 `text` text NOT NULL DEFAULT '',
  `member_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table club-app.notifications: ~4 rows (approximately)
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` (`id`, `text`, `member_id`) VALUES
	(1, 'Hello bro', 18),
	(2, 'Hello bro', 18),
	(4, 'Hello girls', 18),
	(5, 'Hello bro', 18);
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;

-- Dumping structure for table club-app.payments
CREATE TABLE IF NOT EXISTS `payments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT 0,
  `cotisation_id` int(11) DEFAULT 0,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table club-app.payments: ~0 rows (approximately)
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;

-- Dumping structure for table club-app.users
CREATE TABLE IF NOT EXISTS `users` (
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `level` varchar(50) DEFAULT NULL,
  `cne` varchar(50) DEFAULT NULL,
  `userType` varchar(50) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=latin1;

-- Dumping data for table club-app.users: ~17 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`email`, `password`, `firstname`, `lastname`, `level`, `cne`, `userType`, `id`) VALUES
	('patrick@gmail.com', '2d0c8a8f26d90efbef15f042ab0030b3', 'patirck', 'jane', '2ite1', '1600002272', 'admin', 17),
	('aubin.birba@gmail.com', '3b024953642a0fde6a81266879102e7d', 'aubin', 'birba', '2ite', '1600000222', NULL, 21),
	('aubin.birba@gmaipl.com', '3b024953642a0fde6a81266879102e7d', 'aubin', 'birba', '2ite', '16000002226', NULL, 22),
	('aubin.birbaaaa', 'aab3238922bcc25a6f606eb525ffdc56', 'aubin', 'birba', '2ite', '1600002', 'user', 24),
	('patrick', '61a2e890e260431359b5931f243c33d6', 'Jane', 'Patrick', 'isic', '1489633', 'user', 37),
	('abdoul', '1ee0aca9f47645ea12a5d5699322f286', 'BIkienga', 'Abdoul', 'qlshet', '1985224441', 'user', 40),
	('luffy', 'e81502a921e78c4ddb017a555586664c', 'Luffy', 'Monkey D;', 'rookie', '12470000', 'admin', 53),
	('qsdg', '8c6b5e204437733234309ae510038561', 'birba', 'aubin', 'xcgng', '1547', 'user', 68),
	('emle', '69922873cb0cd47a84ef3e70b21eaf06', 'emile', 'birba', 'qsh', '1600000', 'user', 70),
	('amza@gmail.com', '69922873cb0cd47a84ef3e70b21eaf06', 'amza', 'bouchbi', 'qsrh', '17002514', 'user', 71),
	('zoro@shinsekai.com', 'eed83905a260b31bc5d254701999ee94', 'Zoro', 'Roronoa', 'haoshokuno haki', '1402514787', 'user', 72),
	('nebie.eddie@gmail.com', 'b67733c3e4ddc0633ddb2531e3a51ec9', 'Conrad', 'NEBIE', 'Philosophe', '12345678', 'user', 73);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
