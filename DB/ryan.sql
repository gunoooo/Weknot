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
CREATE TABLE IF NOT EXISTS `chatmember`user (
  `chatRoomId` int(11) NOT NULL,
  `userId` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `joinTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `banCount` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`chatRoomId`),
  KEY `fk_chatmember_userId` (`userId`),
  KEY `fk_chatmember_chatRoomId` (`chatRoomId`),
  CONSTRAINT `fk_chatmember_chatRoomId` FOREIGN KEY (`chatRoomId`) REFERENCES `chatroom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_chatmember_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
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
  CONSTRAINT `fk_chatmessage_chatRoomId` FOREIGN KEY (`chatRoomId`) REFERENCES `chatroom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_chatmessage_userId` FOREIGN KEY (`userId`) REFERENCES `chatmember` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 ryan.chatroom 구조 내보내기
CREATE TABLE IF NOT EXISTS `chatroom` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `master` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `roomName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chatroom_master` (`master`),
  CONSTRAINT `fk_chatroom_master` FOREIGN KEY (`master`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
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
  CONSTRAINT `fk_dm_receiver` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_dm_sender` FOREIGN KEY (`sender`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 ryan.dmfile 구조 내보내기
CREATE TABLE IF NOT EXISTS `dmfile` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `dmId` int(20) NOT NULL,
  `path` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dmfile_dmId` (`dmId`),
  CONSTRAINT `fk_dmfile_dmId` FOREIGN KEY (`dmId`) REFERENCES `dm` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 ryan.feed 구조 내보내기
CREATE TABLE IF NOT EXISTS `feed` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `writer` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `comment` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_feed_writer` (`writer`),
  CONSTRAINT `fk_feed_writer` FOREIGN KEY (`writer`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 ryan.friends 구조 내보내기
CREATE TABLE IF NOT EXISTS `friends` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `receiver` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `requester` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `state` int(1) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `receiver_requester` (`receiver`,`requester`),
  KEY `fk_friends_receiver` (`receiver`),
  KEY `kf_friends_requester` (`requester`),
  CONSTRAINT `fk_friends_receiver` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kf_friends_requester` FOREIGN KEY (`requester`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
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
  CONSTRAINT `fk_item_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
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
  CONSTRAINT `fk_like_feedId` FOREIGN KEY (`feedId`) REFERENCES `feed` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_like_receiver` FOREIGN KEY (`receiver`) REFERENCES `feed` (`writer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_like_sender` FOREIGN KEY (`sender`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 ryan.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `birth_old` date DEFAULT NULL,
  `birth` bigint(30) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `appToken` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `phoneNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `intro` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `scope` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `point` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phoneNumber` (`phoneNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
