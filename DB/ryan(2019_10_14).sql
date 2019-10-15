-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.3.14-MariaDB - mariadb.org binary distribution
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

-- 테이블 ryan.feed 구조 내보내기
CREATE TABLE IF NOT EXISTS `feed` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `writer` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `comment` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_feed_writer` (`writer`),
  CONSTRAINT `fk_feed_writer` FOREIGN KEY (`writer`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 뷰 ryan.feeddetail 구조 내보내기
-- VIEW 종속성 오류를 극복하기 위해 임시 테이블을 생성합니다.
CREATE TABLE `feeddetail` (
	`id` INT(20) NOT NULL,
	`writer` VARCHAR(20) NOT NULL COLLATE 'utf8_unicode_ci',
	`time` TIMESTAMP NOT NULL,
	`picture` VARCHAR(255) NOT NULL COLLATE 'utf8_unicode_ci',
	`comment` VARCHAR(30) NOT NULL COLLATE 'utf8_unicode_ci',
	`receiver` VARCHAR(20) NOT NULL COLLATE 'utf8_unicode_ci',
	`sender` VARCHAR(20) NOT NULL COLLATE 'utf8_unicode_ci'
) ENGINE=MyISAM;

-- 테이블 ryan.friends 구조 내보내기
CREATE TABLE IF NOT EXISTS `friends` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `receiver` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `requester` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `state` int(1) NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `receiver_requester` (`receiver`,`requester`),
  KEY `fk_friends_receiver` (`receiver`),
  KEY `kf_friends_requester` (`requester`),
  CONSTRAINT `fk_friends_receiver` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kf_friends_requester` FOREIGN KEY (`requester`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 ryan.like 구조 내보내기
CREATE TABLE IF NOT EXISTS `like` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `sender` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `feedId` int(20) NOT NULL,
  `receiver` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `fk_like_sender` (`sender`),
  KEY `fk_like_receiver` (`receiver`),
  KEY `fk_like_feedId` (`feedId`),
  CONSTRAINT `fk_like_feedId` FOREIGN KEY (`feedId`) REFERENCES `feed` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_like_receiver` FOREIGN KEY (`receiver`) REFERENCES `feed` (`writer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_like_sender` FOREIGN KEY (`sender`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 ryan.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `birth` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `appToken` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `phoneNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `intro` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `scope` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `point` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phoneNumber` (`phoneNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 뷰 ryan.feeddetail 구조 내보내기
-- 임시 테이블을 제거하고 최종 VIEW 구조를 생성
DROP TABLE IF EXISTS `feeddetail`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `feeddetail` AS select feed.* , `like`.receiver, `like`.sender from feed, `like` where feed.writer=like.receiver ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
