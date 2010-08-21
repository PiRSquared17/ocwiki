-- phpMyAdmin SQL Dump
-- version 3.3.2deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 21, 2010 at 07:51 PM
-- Server version: 5.1.41
-- PHP Version: 5.3.2-1ubuntu4.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ocwiki`
--

-- --------------------------------------------------------

--
-- Table structure for table `ocwAnswer`
--

CREATE TABLE IF NOT EXISTS `ocwAnswer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` bigint(20) NOT NULL,
  `correct` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK81F532C1EA647FAC` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `ocwAnswer`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwArticleAttachment`
--

CREATE TABLE IF NOT EXISTS `ocwArticleAttachment` (
  `article_id` bigint(20) NOT NULL,
  `file_id` bigint(20) NOT NULL,
  PRIMARY KEY (`article_id`,`file_id`),
  KEY `FKDCFAE3D62B8E11D2` (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwArticleAttachment`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwArticleEmbed`
--

CREATE TABLE IF NOT EXISTS `ocwArticleEmbed` (
  `article_id` bigint(20) NOT NULL,
  `file_id` bigint(20) NOT NULL,
  PRIMARY KEY (`article_id`,`file_id`),
  KEY `FK1EA9A0462B8E11D2` (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwArticleEmbed`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwArticleTopic`
--

CREATE TABLE IF NOT EXISTS `ocwArticleTopic` (
  `article_id` bigint(20) NOT NULL,
  `topic_id` bigint(20) NOT NULL,
  PRIMARY KEY (`article_id`,`topic_id`),
  KEY `FK1F7E1E9C2575393F` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwArticleTopic`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwBaseQuestion`
--

CREATE TABLE IF NOT EXISTS `ocwBaseQuestion` (
  `id` bigint(20) NOT NULL,
  `namespace` bigint(20) NOT NULL,
  `content` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_vietnamese1_ci DEFAULT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK379164D67D807870ec12c190c0efe8c58e9eaa9a` (`namespace`),
  KEY `FK379164D6EA647FACec12c190c0efe8c58e9eaa9a` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwBaseQuestion`
--

INSERT INTO `ocwBaseQuestion` (`id`, `namespace`, `content`, `name`, `level`) VALUES
(1, 3, 1, NULL, 3);

-- --------------------------------------------------------

--
-- Table structure for table `ocwBaseQuestionAnswer`
--

CREATE TABLE IF NOT EXISTS `ocwBaseQuestionAnswer` (
  `question_id` bigint(20) NOT NULL,
  `answer_id` bigint(20) NOT NULL,
  `answer_index` int(11) NOT NULL,
  PRIMARY KEY (`question_id`,`answer_index`),
  KEY `FKCB25FAF840890D80` (`answer_id`),
  KEY `FKCB25FAF8EF28C9F1` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwBaseQuestionAnswer`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwComment`
--

CREATE TABLE IF NOT EXISTS `ocwComment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user` bigint(20) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `message` text COLLATE utf8_vietnamese1_ci NOT NULL,
  `resource` bigint(20) DEFAULT NULL,
  `revision` bigint(20) DEFAULT NULL,
  `status` varchar(10) COLLATE utf8_vietnamese1_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK27D95BBC53C202BC` (`revision`),
  KEY `FK27D95BBC4A301B22` (`resource`),
  KEY `FK27D95BBCB1E4DD9C` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `ocwComment`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwConstraint`
--

CREATE TABLE IF NOT EXISTS `ocwConstraint` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) COLLATE utf8_vietnamese1_ci NOT NULL,
  `count` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `ocwConstraint`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwFile`
--

CREATE TABLE IF NOT EXISTS `ocwFile` (
  `id` bigint(20) NOT NULL,
  `namespace` bigint(20) NOT NULL,
  `content` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_vietnamese1_ci DEFAULT NULL,
  `filename` varchar(255) COLLATE utf8_vietnamese1_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK379164D67D807870ec12c1909f71cd3f` (`namespace`),
  KEY `FK379164D6EA647FACec12c1909f71cd3f` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwFile`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwHistory`
--

CREATE TABLE IF NOT EXISTS `ocwHistory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user` bigint(20) DEFAULT NULL,
  `test` bigint(20) DEFAULT NULL,
  `taken_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `mark` double DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK267351F1B1E38F2A` (`test`),
  KEY `FK267351F1B1E4DD9C` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `ocwHistory`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwLog`
--

CREATE TABLE IF NOT EXISTS `ocwLog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) COLLATE utf8_vietnamese1_ci NOT NULL,
  `user` bigint(20) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `resource` bigint(20) DEFAULT NULL,
  `comment` bigint(20) DEFAULT NULL,
  `new_revision` bigint(20) DEFAULT NULL,
  `old_revision` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC3144721F3DA5E7B` (`new_revision`),
  KEY `FKC3144721EC5981D4` (`old_revision`),
  KEY `FKC31447214A301B22` (`resource`),
  KEY `FKC31447213B31D0F8` (`comment`),
  KEY `FKC3144721B1E4DD9C` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `ocwLog`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwNamespace`
--

CREATE TABLE IF NOT EXISTS `ocwNamespace` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_vietnamese1_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwNamespace`
--

INSERT INTO `ocwNamespace` (`id`, `name`) VALUES
(0, 'Chính'),
(1, 'OCWIKI'),
(2, 'Chủ đề'),
(3, 'Câu hỏi'),
(4, 'Đề thi'),
(5, 'Cấu trúc đề'),
(6, 'Tập tin');

-- --------------------------------------------------------

--
-- Table structure for table `ocwQuestion`
--

CREATE TABLE IF NOT EXISTS `ocwQuestion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `base_resource` bigint(20) DEFAULT NULL,
  `base_revision` bigint(20) DEFAULT NULL,
  `mark` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB1BC7A29CBC360D0` (`base_resource`),
  KEY `FKB1BC7A29D555486A` (`base_revision`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `ocwQuestion`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwResource`
--

CREATE TABLE IF NOT EXISTS `ocwResource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` varchar(255) COLLATE utf8_vietnamese1_ci NOT NULL,
  `article` bigint(20) NOT NULL,
  `author` bigint(20) NOT NULL,
  `status` varchar(10) COLLATE utf8_vietnamese1_ci NOT NULL,
  `link` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE2E602515DDB135C` (`author`),
  KEY `FKE2E602515EB7070E` (`link`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `ocwResource`
--

INSERT INTO `ocwResource` (`id`, `version`, `create_date`, `type`, `article`, `author`, `status`, `link`) VALUES
(1, 0, '2010-08-21 13:40:56', 'oop.data.BaseQuestion', 1, 2, 'NORMAL', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ocwRevision`
--

CREATE TABLE IF NOT EXISTS `ocwRevision` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource` bigint(20) NOT NULL,
  `article` bigint(20) NOT NULL,
  `author` bigint(20) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `summary` varchar(255) COLLATE utf8_vietnamese1_ci NOT NULL,
  `minor` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE7AEF61E5DDB135C` (`author`),
  KEY `FKE7AEF61E4A301B22` (`resource`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `ocwRevision`
--

INSERT INTO `ocwRevision` (`id`, `resource`, `article`, `author`, `timestamp`, `summary`, `minor`) VALUES
(1, 1, 1, 2, '2010-08-21 13:40:56', 'Khởi tạo đối tượng', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `ocwSection`
--

CREATE TABLE IF NOT EXISTS `ocwSection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK64A2EC42EA647FAC` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `ocwSection`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwSectionQuestion`
--

CREATE TABLE IF NOT EXISTS `ocwSectionQuestion` (
  `section_id` bigint(20) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  PRIMARY KEY (`section_id`,`question_id`),
  KEY `FKB0F82A485936BFD4` (`section_id`),
  KEY `FKB0F82A4866D4B300` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwSectionQuestion`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwSectionStructure`
--

CREATE TABLE IF NOT EXISTS `ocwSectionStructure` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE0E50B51EA647FAC` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `ocwSectionStructure`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwSectionStructureConstraint`
--

CREATE TABLE IF NOT EXISTS `ocwSectionStructureConstraint` (
  `section_id` bigint(20) NOT NULL,
  `constraint_id` bigint(20) NOT NULL,
  `constraint_index` int(11) NOT NULL,
  PRIMARY KEY (`section_id`,`constraint_index`),
  KEY `FK2228CC0E9391B220` (`constraint_id`),
  KEY `FK2228CC0E5CF139C9` (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwSectionStructureConstraint`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwTest`
--

CREATE TABLE IF NOT EXISTS `ocwTest` (
  `id` bigint(20) NOT NULL,
  `namespace` bigint(20) NOT NULL,
  `content` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_vietnamese1_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_vietnamese1_ci NOT NULL,
  `time` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK379164D67D807870ec12c190c0efe8c59f781c55` (`namespace`),
  KEY `FK379164D6EA647FACec12c190c0efe8c59f781c55` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwTest`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwTestSection`
--

CREATE TABLE IF NOT EXISTS `ocwTestSection` (
  `id` bigint(20) NOT NULL,
  `section_id` bigint(20) NOT NULL,
  `section_index` int(11) NOT NULL,
  PRIMARY KEY (`id`,`section_index`),
  KEY `FK80DC60D0B1AD57B3` (`id`),
  KEY `FK80DC60D05936BFD4` (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwTestSection`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwTestSectionStructure`
--

CREATE TABLE IF NOT EXISTS `ocwTestSectionStructure` (
  `id` bigint(20) NOT NULL,
  `section_id` bigint(20) NOT NULL,
  `section_index` int(11) NOT NULL,
  PRIMARY KEY (`id`,`section_index`),
  KEY `FK3CCAD683A7507AD6` (`id`),
  KEY `FK3CCAD6835CF139C9` (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwTestSectionStructure`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwTestStructure`
--

CREATE TABLE IF NOT EXISTS `ocwTestStructure` (
  `id` bigint(20) NOT NULL,
  `namespace` bigint(20) NOT NULL,
  `content` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_vietnamese1_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_vietnamese1_ci NOT NULL,
  `time` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK379164D67D807870ec12c190c0efe8c5ec59409e` (`namespace`),
  KEY `FK379164D6EA647FACec12c190c0efe8c5ec59409e` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwTestStructure`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwText`
--

CREATE TABLE IF NOT EXISTS `ocwText` (
  `txt_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `txt_text` mediumtext COLLATE utf8_vietnamese1_ci,
  PRIMARY KEY (`txt_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `ocwText`
--

INSERT INTO `ocwText` (`txt_id`, `txt_text`) VALUES
(1, 'klhjlkj\r\n\r\n\r\nlkj');

-- --------------------------------------------------------

--
-- Table structure for table `ocwTextArticle`
--

CREATE TABLE IF NOT EXISTS `ocwTextArticle` (
  `id` bigint(20) NOT NULL,
  `namespace` bigint(20) NOT NULL,
  `content` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_vietnamese1_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK379164D67D807870ec12c190c0efe8c5c51bd9e6` (`namespace`),
  KEY `FK379164D6EA647FACec12c190c0efe8c5c51bd9e6` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwTextArticle`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwTopic`
--

CREATE TABLE IF NOT EXISTS `ocwTopic` (
  `id` bigint(20) NOT NULL,
  `namespace` bigint(20) NOT NULL,
  `content` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_vietnamese1_ci DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK379164D67D8078704f8fedcc` (`namespace`),
  KEY `FK379164D6EA647FAC4f8fedcc` (`content`),
  KEY `FK4F8FEDCC48AB9093` (`parent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwTopic`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwTopicConstraintTopic`
--

CREATE TABLE IF NOT EXISTS `ocwTopicConstraintTopic` (
  `constraint_id` bigint(20) NOT NULL,
  `topic_id` bigint(20) NOT NULL,
  PRIMARY KEY (`constraint_id`,`topic_id`),
  KEY `FK7F38E1E68DCE1D63` (`constraint_id`),
  KEY `FK7F38E1E62575393F` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci;

--
-- Dumping data for table `ocwTopicConstraintTopic`
--


-- --------------------------------------------------------

--
-- Table structure for table `ocwUser`
--

CREATE TABLE IF NOT EXISTS `ocwUser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_vietnamese1_ci NOT NULL,
  `fullname` varchar(255) COLLATE utf8_vietnamese1_ci NOT NULL,
  `pass` varchar(255) COLLATE utf8_vietnamese1_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_vietnamese1_ci NOT NULL,
  `group` varchar(255) COLLATE utf8_vietnamese1_ci NOT NULL,
  `blocked` bit(1) NOT NULL,
  `warning` varchar(255) COLLATE utf8_vietnamese1_ci DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8_vietnamese1_ci DEFAULT NULL,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pref_template` varchar(50) COLLATE utf8_vietnamese1_ci NOT NULL DEFAULT 'default',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese1_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `ocwUser`
--

INSERT INTO `ocwUser` (`id`, `name`, `fullname`, `pass`, `email`, `group`, `blocked`, `warning`, `avatar`, `register_date`, `pref_template`) VALUES
(1, 'admin', 'admin', '1234', 'admin@ocwiki.org', 'admin', b'0', NULL, NULL, '2010-08-21 13:40:40', 'default'),
(2, 'teacher', 'teacher', '1234', 'teacher@ocwiki.org', 'teacher', b'0', NULL, NULL, '2010-08-21 13:40:40', 'default');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ocwAnswer`
--
ALTER TABLE `ocwAnswer`
  ADD CONSTRAINT `FK81F532C1EA647FAC` FOREIGN KEY (`content`) REFERENCES `ocwText` (`txt_id`);

--
-- Constraints for table `ocwArticleAttachment`
--
ALTER TABLE `ocwArticleAttachment`
  ADD CONSTRAINT `FKDCFAE3D62B8E11D2` FOREIGN KEY (`file_id`) REFERENCES `ocwResource` (`id`);

--
-- Constraints for table `ocwArticleEmbed`
--
ALTER TABLE `ocwArticleEmbed`
  ADD CONSTRAINT `FK1EA9A0462B8E11D2` FOREIGN KEY (`file_id`) REFERENCES `ocwResource` (`id`);

--
-- Constraints for table `ocwArticleTopic`
--
ALTER TABLE `ocwArticleTopic`
  ADD CONSTRAINT `FK1F7E1E9C2575393F` FOREIGN KEY (`topic_id`) REFERENCES `ocwResource` (`id`);

--
-- Constraints for table `ocwBaseQuestion`
--
ALTER TABLE `ocwBaseQuestion`
  ADD CONSTRAINT `FK379164D67D807870ec12c190c0efe8c58e9eaa9a` FOREIGN KEY (`namespace`) REFERENCES `ocwNamespace` (`id`),
  ADD CONSTRAINT `FK379164D6EA647FACec12c190c0efe8c58e9eaa9a` FOREIGN KEY (`content`) REFERENCES `ocwText` (`txt_id`);

--
-- Constraints for table `ocwBaseQuestionAnswer`
--
ALTER TABLE `ocwBaseQuestionAnswer`
  ADD CONSTRAINT `FKCB25FAF840890D80` FOREIGN KEY (`answer_id`) REFERENCES `ocwAnswer` (`id`),
  ADD CONSTRAINT `FKCB25FAF8EF28C9F1` FOREIGN KEY (`question_id`) REFERENCES `ocwBaseQuestion` (`id`);

--
-- Constraints for table `ocwComment`
--
ALTER TABLE `ocwComment`
  ADD CONSTRAINT `FK27D95BBC4A301B22` FOREIGN KEY (`resource`) REFERENCES `ocwResource` (`id`),
  ADD CONSTRAINT `FK27D95BBC53C202BC` FOREIGN KEY (`revision`) REFERENCES `ocwRevision` (`id`),
  ADD CONSTRAINT `FK27D95BBCB1E4DD9C` FOREIGN KEY (`user`) REFERENCES `ocwUser` (`id`);

--
-- Constraints for table `ocwFile`
--
ALTER TABLE `ocwFile`
  ADD CONSTRAINT `FK379164D67D807870ec12c1909f71cd3f` FOREIGN KEY (`namespace`) REFERENCES `ocwNamespace` (`id`),
  ADD CONSTRAINT `FK379164D6EA647FACec12c1909f71cd3f` FOREIGN KEY (`content`) REFERENCES `ocwText` (`txt_id`);

--
-- Constraints for table `ocwHistory`
--
ALTER TABLE `ocwHistory`
  ADD CONSTRAINT `FK267351F1B1E38F2A` FOREIGN KEY (`test`) REFERENCES `ocwTest` (`id`),
  ADD CONSTRAINT `FK267351F1B1E4DD9C` FOREIGN KEY (`user`) REFERENCES `ocwUser` (`id`);

--
-- Constraints for table `ocwLog`
--
ALTER TABLE `ocwLog`
  ADD CONSTRAINT `FKC31447213B31D0F8` FOREIGN KEY (`comment`) REFERENCES `ocwComment` (`id`),
  ADD CONSTRAINT `FKC31447214A301B22` FOREIGN KEY (`resource`) REFERENCES `ocwResource` (`id`),
  ADD CONSTRAINT `FKC3144721B1E4DD9C` FOREIGN KEY (`user`) REFERENCES `ocwUser` (`id`),
  ADD CONSTRAINT `FKC3144721EC5981D4` FOREIGN KEY (`old_revision`) REFERENCES `ocwRevision` (`id`),
  ADD CONSTRAINT `FKC3144721F3DA5E7B` FOREIGN KEY (`new_revision`) REFERENCES `ocwRevision` (`id`);

--
-- Constraints for table `ocwQuestion`
--
ALTER TABLE `ocwQuestion`
  ADD CONSTRAINT `FKB1BC7A29CBC360D0` FOREIGN KEY (`base_resource`) REFERENCES `ocwResource` (`id`),
  ADD CONSTRAINT `FKB1BC7A29D555486A` FOREIGN KEY (`base_revision`) REFERENCES `ocwRevision` (`id`);

--
-- Constraints for table `ocwResource`
--
ALTER TABLE `ocwResource`
  ADD CONSTRAINT `FKE2E602515DDB135C` FOREIGN KEY (`author`) REFERENCES `ocwUser` (`id`),
  ADD CONSTRAINT `FKE2E602515EB7070E` FOREIGN KEY (`link`) REFERENCES `ocwResource` (`id`);

--
-- Constraints for table `ocwRevision`
--
ALTER TABLE `ocwRevision`
  ADD CONSTRAINT `FKE7AEF61E4A301B22` FOREIGN KEY (`resource`) REFERENCES `ocwResource` (`id`),
  ADD CONSTRAINT `FKE7AEF61E5DDB135C` FOREIGN KEY (`author`) REFERENCES `ocwUser` (`id`);

--
-- Constraints for table `ocwSection`
--
ALTER TABLE `ocwSection`
  ADD CONSTRAINT `FK64A2EC42EA647FAC` FOREIGN KEY (`content`) REFERENCES `ocwText` (`txt_id`);

--
-- Constraints for table `ocwSectionQuestion`
--
ALTER TABLE `ocwSectionQuestion`
  ADD CONSTRAINT `FKB0F82A485936BFD4` FOREIGN KEY (`section_id`) REFERENCES `ocwSection` (`id`),
  ADD CONSTRAINT `FKB0F82A4866D4B300` FOREIGN KEY (`question_id`) REFERENCES `ocwQuestion` (`id`);

--
-- Constraints for table `ocwSectionStructure`
--
ALTER TABLE `ocwSectionStructure`
  ADD CONSTRAINT `FKE0E50B51EA647FAC` FOREIGN KEY (`content`) REFERENCES `ocwText` (`txt_id`);

--
-- Constraints for table `ocwSectionStructureConstraint`
--
ALTER TABLE `ocwSectionStructureConstraint`
  ADD CONSTRAINT `FK2228CC0E5CF139C9` FOREIGN KEY (`section_id`) REFERENCES `ocwSectionStructure` (`id`),
  ADD CONSTRAINT `FK2228CC0E9391B220` FOREIGN KEY (`constraint_id`) REFERENCES `ocwConstraint` (`id`);

--
-- Constraints for table `ocwTest`
--
ALTER TABLE `ocwTest`
  ADD CONSTRAINT `FK379164D67D807870ec12c190c0efe8c59f781c55` FOREIGN KEY (`namespace`) REFERENCES `ocwNamespace` (`id`),
  ADD CONSTRAINT `FK379164D6EA647FACec12c190c0efe8c59f781c55` FOREIGN KEY (`content`) REFERENCES `ocwText` (`txt_id`);

--
-- Constraints for table `ocwTestSection`
--
ALTER TABLE `ocwTestSection`
  ADD CONSTRAINT `FK80DC60D05936BFD4` FOREIGN KEY (`section_id`) REFERENCES `ocwSection` (`id`),
  ADD CONSTRAINT `FK80DC60D0B1AD57B3` FOREIGN KEY (`id`) REFERENCES `ocwTest` (`id`);

--
-- Constraints for table `ocwTestSectionStructure`
--
ALTER TABLE `ocwTestSectionStructure`
  ADD CONSTRAINT `FK3CCAD6835CF139C9` FOREIGN KEY (`section_id`) REFERENCES `ocwSectionStructure` (`id`),
  ADD CONSTRAINT `FK3CCAD683A7507AD6` FOREIGN KEY (`id`) REFERENCES `ocwTestStructure` (`id`);

--
-- Constraints for table `ocwTestStructure`
--
ALTER TABLE `ocwTestStructure`
  ADD CONSTRAINT `FK379164D67D807870ec12c190c0efe8c5ec59409e` FOREIGN KEY (`namespace`) REFERENCES `ocwNamespace` (`id`),
  ADD CONSTRAINT `FK379164D6EA647FACec12c190c0efe8c5ec59409e` FOREIGN KEY (`content`) REFERENCES `ocwText` (`txt_id`);

--
-- Constraints for table `ocwTextArticle`
--
ALTER TABLE `ocwTextArticle`
  ADD CONSTRAINT `FK379164D67D807870ec12c190c0efe8c5c51bd9e6` FOREIGN KEY (`namespace`) REFERENCES `ocwNamespace` (`id`),
  ADD CONSTRAINT `FK379164D6EA647FACec12c190c0efe8c5c51bd9e6` FOREIGN KEY (`content`) REFERENCES `ocwText` (`txt_id`);

--
-- Constraints for table `ocwTopic`
--
ALTER TABLE `ocwTopic`
  ADD CONSTRAINT `FK379164D67D8078704f8fedcc` FOREIGN KEY (`namespace`) REFERENCES `ocwNamespace` (`id`),
  ADD CONSTRAINT `FK379164D6EA647FAC4f8fedcc` FOREIGN KEY (`content`) REFERENCES `ocwText` (`txt_id`),
  ADD CONSTRAINT `FK4F8FEDCC48AB9093` FOREIGN KEY (`parent`) REFERENCES `ocwTopic` (`id`);

--
-- Constraints for table `ocwTopicConstraintTopic`
--
ALTER TABLE `ocwTopicConstraintTopic`
  ADD CONSTRAINT `FK7F38E1E62575393F` FOREIGN KEY (`topic_id`) REFERENCES `ocwResource` (`id`),
  ADD CONSTRAINT `FK7F38E1E68DCE1D63` FOREIGN KEY (`constraint_id`) REFERENCES `ocwConstraint` (`id`);
