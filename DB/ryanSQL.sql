-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.15 - MySQL Community Server - GPL
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- ryan 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `ryan` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `ryan`;

-- 테이블 ryan.chatmember 구조 내보내기
CREATE TABLE IF NOT EXISTS `chatmember` (
  `chatRoomId` int(11) NOT NULL,
  `userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `joinTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `hideName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `banCount` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`chatRoomId`),
  KEY `fk_chatmember_userId` (`userId`),
  KEY `fk_chatmember_chatRoomId` (`chatRoomId`),
  CONSTRAINT `fk_chatmember_chatRoomId` FOREIGN KEY (`chatRoomId`) REFERENCES `chatroom` (`id`),
  CONSTRAINT `fk_chatmember_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ryan.chatmember:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `chatmember` DISABLE KEYS */;
/*!40000 ALTER TABLE `chatmember` ENABLE KEYS */;

-- 테이블 ryan.chatmessage 구조 내보내기
CREATE TABLE IF NOT EXISTS `chatmessage` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `chatRoomId` int(20) NOT NULL,
  `userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chatmessage_userId` (`userId`),
  KEY `fk_chatmessage_chatRoomId` (`chatRoomId`),
  CONSTRAINT `fk_chatmessage_chatRoomId` FOREIGN KEY (`chatRoomId`) REFERENCES `chatroom` (`id`),
  CONSTRAINT `fk_chatmessage_userId` FOREIGN KEY (`userId`) REFERENCES `chatmember` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ryan.chatmessage:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `chatmessage` DISABLE KEYS */;
/*!40000 ALTER TABLE `chatmessage` ENABLE KEYS */;

-- 테이블 ryan.chatroom 구조 내보내기
CREATE TABLE IF NOT EXISTS `chatroom` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `master` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `roomNumber` bigint(10) NOT NULL,
  `roomName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chatroom_master` (`master`),
  CONSTRAINT `fk_chatroom_master` FOREIGN KEY (`master`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ryan.chatroom:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `chatroom` DISABLE KEYS */;
/*!40000 ALTER TABLE `chatroom` ENABLE KEYS */;

-- 테이블 ryan.dm 구조 내보내기
CREATE TABLE IF NOT EXISTS `dm` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `sender` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `receiver` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `message` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `isRead` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dm_sender` (`sender`),
  KEY `fk_dm_receiver` (`receiver`),
  CONSTRAINT `fk_dm_receiver` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_dm_sender` FOREIGN KEY (`sender`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 테이블 데이터 ryan.dm:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `dm` DISABLE KEYS */;
INSERT INTO `dm` (`id`, `sender`, `receiver`, `message`, `time`, `isRead`) VALUES
	(1, '111', 'w', 'dfasdfsdf', '2019-05-27 17:24:35', 0);
/*!40000 ALTER TABLE `dm` ENABLE KEYS */;

-- 테이블 ryan.dmfile 구조 내보내기
CREATE TABLE IF NOT EXISTS `dmfile` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `dmId` int(20) NOT NULL,
  `path` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dmfile_dmId` (`dmId`),
  CONSTRAINT `fk_dmfile_dmId` FOREIGN KEY (`dmId`) REFERENCES `dm` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ryan.dmfile:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `dmfile` DISABLE KEYS */;
/*!40000 ALTER TABLE `dmfile` ENABLE KEYS */;

-- 테이블 ryan.feed 구조 내보내기
CREATE TABLE IF NOT EXISTS `feed` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `writer` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `comment` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_feed_writer` (`writer`),
  CONSTRAINT `fk_feed_writer` FOREIGN KEY (`writer`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ryan.feed:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `feed` DISABLE KEYS */;
/*!40000 ALTER TABLE `feed` ENABLE KEYS */;

-- 테이블 ryan.friends 구조 내보내기
CREATE TABLE IF NOT EXISTS `friends` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `receiver` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `requester` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `state` int(1) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_friends_receiver` (`receiver`),
  KEY `kf_friends_requester` (`requester`),
  CONSTRAINT `fk_friends_receiver` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`),
  CONSTRAINT `kf_friends_requester` FOREIGN KEY (`requester`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 테이블 데이터 ryan.friends:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;

-- 테이블 ryan.item 구조 내보내기
CREATE TABLE IF NOT EXISTS `item` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `count` bigint(20) NOT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_item_userId` (`userId`),
  CONSTRAINT `fk_item_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ryan.item:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
/*!40000 ALTER TABLE `item` ENABLE KEYS */;

-- 테이블 ryan.like 구조 내보내기
CREATE TABLE IF NOT EXISTS `like` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `sender` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `feedId` int(20) NOT NULL,
  `receiver` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_like_sender` (`sender`),
  KEY `fk_like_receiver` (`receiver`),
  KEY `fk_like_feedId` (`feedId`),
  CONSTRAINT `fk_like_feedId` FOREIGN KEY (`feedId`) REFERENCES `feed` (`id`),
  CONSTRAINT `fk_like_receiver` FOREIGN KEY (`receiver`) REFERENCES `feed` (`writer`),
  CONSTRAINT `fk_like_sender` FOREIGN KEY (`sender`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ryan.like:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `like` DISABLE KEYS */;
/*!40000 ALTER TABLE `like` ENABLE KEYS */;

-- 테이블 ryan.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `birth` date NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `appToken` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `phoneNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `intro` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `scope` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `point` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phoneNumber` (`phoneNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ryan.user:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `password`, `birth`, `gender`, `appToken`, `phoneNumber`, `picture`, `intro`, `scope`, `point`) VALUES
	('111', '류재정', '111', '2002-07-05', 'man', NULL, '010-9121-0769', 'df', NULL, NULL, 0),
	('123', '류재정', '123123', '2002-07-05', 'man', NULL, '010-1234-1234', NULL, NULL, NULL, 0),
	('w', '류재정', '123123', '2002-07-05', 'man', NULL, '123', 'df', NULL, NULL, 0),
	('wowjddl133', '류재정', '123123', '2002-07-05', 'man', NULL, '010-9121-0544', 'df', NULL, NULL, 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
