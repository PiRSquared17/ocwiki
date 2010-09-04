SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

CREATE TABLE IF NOT EXISTS `ocwAnswer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` bigint(20) NOT NULL,
  `correct` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK81F532C1EA647FAC` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `ocwArticle` (
  `id` bigint(20) NOT NULL,
  `discriminator` varchar(255) NOT NULL,
  `namespace` bigint(20) NOT NULL,
  `content` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC38C3A537D807870` (`namespace`),
  KEY `FKC38C3A5348AB9093` (`parent`),
  KEY `FKC38C3A53EA647FAC` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `ocwArticleAttachment` (
  `article_id` bigint(20) NOT NULL,
  `file_id` bigint(20) NOT NULL,
  PRIMARY KEY (`article_id`,`file_id`),
  KEY `FKDCFAE3D628588123` (`article_id`),
  KEY `FKDCFAE3D62B8E11D2` (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `ocwArticleEmbed` (
  `article_id` bigint(20) NOT NULL,
  `file_id` bigint(20) NOT NULL,
  PRIMARY KEY (`article_id`,`file_id`),
  KEY `FK1EA9A04628588123` (`article_id`),
  KEY `FK1EA9A0462B8E11D2` (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `ocwArticleTopic` (
  `article_id` bigint(20) NOT NULL,
  `topic_id` bigint(20) NOT NULL,
  PRIMARY KEY (`article_id`,`topic_id`),
  KEY `FK1F7E1E9CACC8C07A` (`article_id`),
  KEY `FK1F7E1E9C2575393F` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `ocwBaseQuestionAnswer` (
  `question_id` bigint(20) NOT NULL,
  `answer_id` bigint(20) NOT NULL,
  `answer_index` int(11) NOT NULL,
  PRIMARY KEY (`question_id`,`answer_index`),
  KEY `FKCB25FAF840890D80` (`answer_id`),
  KEY `FKCB25FAF8EF28C9F1` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `ocwComment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `user` bigint(20) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `message` text NOT NULL,
  `resource` bigint(20) DEFAULT NULL,
  `revision` bigint(20) DEFAULT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK27D95BBC53C202BC` (`revision`),
  KEY `FK27D95BBC4A301B22` (`resource`),
  KEY `FK27D95BBCB1E4DD9C` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `ocwConstraint` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL,
  `count` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `ocwLog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `ocwNamespace` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `ocwQuestion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `base_resource` bigint(20) DEFAULT NULL,
  `base_revision` bigint(20) DEFAULT NULL,
  `mark` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB1BC7A29CBC360D0` (`base_resource`),
  KEY `FKB1BC7A29D555486A` (`base_revision`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `ocwResource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` varchar(255) NOT NULL,
  `article` bigint(20) NOT NULL,
  `author` bigint(20) NOT NULL,
  `status` varchar(10) NOT NULL,
  `link` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE2E602515DDB135C` (`author`),
  KEY `FKE2E602515EB7070E` (`link`),
  KEY `FKE2E6025172978E26` (`article`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `ocwRevision` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource` bigint(20) NOT NULL,
  `article` bigint(20) NOT NULL,
  `author` bigint(20) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `summary` varchar(255) NOT NULL,
  `minor` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE7AEF61E5DDB135C` (`author`),
  KEY `FKE7AEF61E72978E26` (`article`),
  KEY `FKE7AEF61E4A301B22` (`resource`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `ocwSection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK64A2EC42EA647FAC` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `ocwSectionQuestion` (
  `section_id` bigint(20) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `question_index` int(11) NOT NULL,
  PRIMARY KEY (`section_id`,`question_index`),
  KEY `FKB0F82A485936BFD4` (`section_id`),
  KEY `FKB0F82A4866D4B300` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `ocwSectionStructure` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE0E50B51EA647FAC` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `ocwSectionStructureConstraint` (
  `section_id` bigint(20) NOT NULL,
  `constraint_id` bigint(20) NOT NULL,
  `constraint_index` int(11) NOT NULL,
  PRIMARY KEY (`section_id`,`constraint_index`),
  KEY `FK2228CC0E9391B220` (`constraint_id`),
  KEY `FK2228CC0E5CF139C9` (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `ocwTestSection` (
  `test_id` bigint(20) NOT NULL,
  `section_id` bigint(20) NOT NULL,
  `section_index` int(11) NOT NULL,
  PRIMARY KEY (`test_id`,`section_index`),
  KEY `FK80DC60D05CE45680` (`test_id`),
  KEY `FK80DC60D05936BFD4` (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `ocwTestSectionStructure` (
  `id` bigint(20) NOT NULL,
  `section_id` bigint(20) NOT NULL,
  `section_index` int(11) NOT NULL,
  PRIMARY KEY (`id`,`section_index`),
  KEY `FK3CCAD683A7507AD6` (`id`),
  KEY `FK3CCAD6835CF139C9` (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `ocwText` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `ocwTopicConstraintTopic` (
  `constraint_id` bigint(20) NOT NULL,
  `topic_id` bigint(20) NOT NULL,
  PRIMARY KEY (`constraint_id`,`topic_id`),
  KEY `FK7F38E1E68DCE1D63` (`constraint_id`),
  KEY `FK7F38E1E62575393F` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `ocwUser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `ugroup` varchar(255) NOT NULL,
  `blocked` bit(1) NOT NULL,
  `warning` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `block_expired_date` timestamp NULL DEFAULT NULL,
  `warning_expired_date` timestamp NULL DEFAULT NULL,
  `pref_template` varchar(50) NOT NULL DEFAULT 'default',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;





INSERT INTO `ocwResource` (`id`, `version`, `create_date`, `type`, `article`, `author`, `status`, `link`) VALUES
(62, 0, '2010-08-25 01:15:32', 'oop.data.Test', 62, 1, 'NORMAL', NULL),
(63, 0, '2010-08-25 01:15:32', 'oop.data.Test', 63, 1, 'NORMAL', NULL),
(64, 0, '2010-08-25 01:15:32', 'oop.data.Test', 64, 1, 'NORMAL', NULL),
(65, 0, '2010-08-25 01:15:32', 'oop.data.Test', 65, 1, 'NORMAL', NULL),
(66, 0, '2010-08-25 01:15:32', 'oop.data.Test', 66, 1, 'NORMAL', NULL),
(88, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 88, 1, 'NORMAL', NULL),
(89, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 89, 1, 'NORMAL', NULL),
(90, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 90, 1, 'NORMAL', NULL),
(91, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 91, 1, 'NORMAL', NULL),
(92, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 92, 1, 'NORMAL', NULL),
(93, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 93, 1, 'NORMAL', NULL),
(94, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 94, 1, 'NORMAL', NULL),
(95, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 95, 1, 'NORMAL', NULL),
(96, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 96, 1, 'NORMAL', NULL),
(97, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 97, 1, 'NORMAL', NULL),
(98, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 98, 1, 'NORMAL', NULL),
(99, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 99, 1, 'NORMAL', NULL),
(100, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 100, 1, 'NORMAL', NULL),
(101, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 101, 1, 'NORMAL', NULL),
(102, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 102, 1, 'NORMAL', NULL),
(103, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 103, 1, 'NORMAL', NULL),
(104, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 104, 1, 'NORMAL', NULL),
(105, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 105, 1, 'NORMAL', NULL),
(106, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 106, 1, 'NORMAL', NULL),
(107, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 107, 1, 'NORMAL', NULL),
(108, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 108, 1, 'NORMAL', NULL),
(109, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 109, 1, 'NORMAL', NULL),
(110, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 110, 1, 'NORMAL', NULL),
(111, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 111, 1, 'NORMAL', NULL),
(112, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 112, 1, 'NORMAL', NULL),
(113, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 113, 1, 'NORMAL', NULL),
(114, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 114, 1, 'NORMAL', NULL),
(115, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 115, 1, 'NORMAL', NULL),
(116, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 116, 1, 'NORMAL', NULL),
(117, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 117, 1, 'NORMAL', NULL),
(118, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 118, 1, 'NORMAL', NULL),
(119, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 119, 1, 'NORMAL', NULL),
(120, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 120, 1, 'NORMAL', NULL),
(121, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 121, 1, 'NORMAL', NULL),
(122, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 122, 1, 'NORMAL', NULL),
(123, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 123, 1, 'NORMAL', NULL),
(124, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 124, 1, 'NORMAL', NULL),
(125, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 125, 1, 'NORMAL', NULL),
(126, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 126, 1, 'NORMAL', NULL),
(127, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 127, 1, 'NORMAL', NULL),
(128, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 128, 1, 'NORMAL', NULL),
(129, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 129, 1, 'NORMAL', NULL),
(130, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 130, 1, 'NORMAL', NULL),
(131, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 131, 1, 'NORMAL', NULL),
(132, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 132, 1, 'NORMAL', NULL),
(133, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 133, 1, 'NORMAL', NULL),
(134, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 134, 1, 'NORMAL', NULL),
(135, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 135, 1, 'NORMAL', NULL),
(136, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 136, 1, 'NORMAL', NULL),
(137, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 137, 1, 'NORMAL', NULL),
(138, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 138, 1, 'NORMAL', NULL),
(139, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 139, 1, 'NORMAL', NULL),
(140, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 140, 1, 'NORMAL', NULL),
(141, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 141, 1, 'NORMAL', NULL),
(142, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 142, 1, 'NORMAL', NULL),
(143, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 143, 1, 'NORMAL', NULL),
(144, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 144, 1, 'NORMAL', NULL),
(145, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 145, 1, 'NORMAL', NULL),
(146, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 146, 1, 'NORMAL', NULL),
(147, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 147, 1, 'NORMAL', NULL),
(148, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 148, 1, 'NORMAL', NULL),
(149, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 149, 1, 'NORMAL', NULL),
(150, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 150, 1, 'NORMAL', NULL),
(151, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 151, 1, 'NORMAL', NULL),
(152, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 152, 1, 'NORMAL', NULL),
(153, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 153, 1, 'NORMAL', NULL),
(154, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 154, 1, 'NORMAL', NULL),
(155, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 155, 1, 'NORMAL', NULL),
(156, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 156, 1, 'NORMAL', NULL),
(157, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 157, 1, 'NORMAL', NULL),
(158, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 158, 1, 'NORMAL', NULL),
(159, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 159, 1, 'NORMAL', NULL),
(160, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 160, 1, 'NORMAL', NULL),
(161, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 161, 1, 'NORMAL', NULL),
(162, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 162, 1, 'NORMAL', NULL),
(163, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 163, 1, 'NORMAL', NULL),
(164, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 164, 1, 'NORMAL', NULL),
(165, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 165, 1, 'NORMAL', NULL),
(166, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 166, 1, 'NORMAL', NULL),
(167, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 167, 1, 'NORMAL', NULL),
(168, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 168, 1, 'NORMAL', NULL),
(169, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 169, 1, 'NORMAL', NULL),
(170, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 170, 1, 'NORMAL', NULL),
(171, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 171, 1, 'NORMAL', NULL),
(172, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 172, 1, 'NORMAL', NULL),
(173, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 173, 1, 'NORMAL', NULL),
(174, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 174, 1, 'NORMAL', NULL),
(175, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 175, 1, 'NORMAL', NULL),
(176, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 176, 1, 'NORMAL', NULL),
(177, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 177, 1, 'NORMAL', NULL),
(178, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 178, 1, 'NORMAL', NULL),
(179, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 179, 1, 'NORMAL', NULL),
(180, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 180, 1, 'NORMAL', NULL),
(181, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 181, 1, 'NORMAL', NULL),
(182, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 182, 1, 'NORMAL', NULL),
(183, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 183, 1, 'NORMAL', NULL),
(184, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 184, 1, 'NORMAL', NULL),
(185, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 185, 1, 'NORMAL', NULL),
(186, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 186, 1, 'NORMAL', NULL),
(187, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 187, 1, 'NORMAL', NULL),
(188, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 188, 1, 'NORMAL', NULL),
(189, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 189, 1, 'NORMAL', NULL),
(190, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 190, 1, 'NORMAL', NULL),
(191, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 191, 1, 'NORMAL', NULL),
(192, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 192, 1, 'NORMAL', NULL),
(193, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 193, 1, 'NORMAL', NULL),
(194, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 194, 1, 'NORMAL', NULL),
(195, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 195, 1, 'NORMAL', NULL),
(196, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 196, 1, 'NORMAL', NULL),
(197, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 197, 1, 'NORMAL', NULL),
(198, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 198, 1, 'NORMAL', NULL),
(199, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 199, 1, 'NORMAL', NULL),
(200, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 200, 1, 'NORMAL', NULL),
(201, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 201, 1, 'NORMAL', NULL),
(202, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 202, 1, 'NORMAL', NULL),
(203, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 203, 1, 'NORMAL', NULL),
(204, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 204, 1, 'NORMAL', NULL),
(205, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 205, 1, 'NORMAL', NULL),
(206, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 206, 1, 'NORMAL', NULL),
(207, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 207, 1, 'NORMAL', NULL),
(208, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 208, 1, 'NORMAL', NULL),
(209, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 209, 1, 'NORMAL', NULL),
(210, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 210, 1, 'NORMAL', NULL),
(211, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 211, 1, 'NORMAL', NULL),
(212, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 212, 1, 'NORMAL', NULL),
(213, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 213, 1, 'NORMAL', NULL),
(214, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 214, 1, 'NORMAL', NULL),
(215, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 215, 1, 'NORMAL', NULL),
(216, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 216, 1, 'NORMAL', NULL),
(217, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 217, 1, 'NORMAL', NULL),
(218, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 218, 1, 'NORMAL', NULL),
(219, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 219, 1, 'NORMAL', NULL),
(220, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 220, 1, 'NORMAL', NULL),
(221, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 221, 1, 'NORMAL', NULL),
(222, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 222, 1, 'NORMAL', NULL),
(223, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 223, 1, 'NORMAL', NULL),
(224, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 224, 1, 'NORMAL', NULL),
(225, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 225, 1, 'NORMAL', NULL),
(226, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 226, 1, 'NORMAL', NULL),
(227, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 227, 1, 'NORMAL', NULL),
(228, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 228, 1, 'NORMAL', NULL),
(229, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 229, 1, 'NORMAL', NULL),
(230, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 230, 1, 'NORMAL', NULL),
(231, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 231, 1, 'NORMAL', NULL),
(232, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 232, 1, 'NORMAL', NULL),
(233, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 233, 1, 'NORMAL', NULL),
(234, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 234, 1, 'NORMAL', NULL),
(235, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 235, 1, 'NORMAL', NULL),
(236, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 236, 1, 'NORMAL', NULL),
(237, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 237, 1, 'NORMAL', NULL),
(238, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 238, 1, 'NORMAL', NULL),
(239, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 239, 1, 'NORMAL', NULL),
(240, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 240, 1, 'NORMAL', NULL),
(241, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 241, 1, 'NORMAL', NULL),
(242, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 242, 1, 'NORMAL', NULL),
(243, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 243, 1, 'NORMAL', NULL),
(244, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 244, 1, 'NORMAL', NULL),
(245, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 245, 1, 'NORMAL', NULL),
(246, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 246, 1, 'NORMAL', NULL),
(247, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 247, 1, 'NORMAL', NULL),
(339, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 339, 1, 'NORMAL', NULL),
(340, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 340, 1, 'NORMAL', NULL),
(341, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 341, 1, 'NORMAL', NULL),
(342, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 342, 1, 'NORMAL', NULL),
(343, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 343, 1, 'NORMAL', NULL),
(344, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 344, 1, 'NORMAL', NULL),
(345, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 345, 1, 'NORMAL', NULL),
(346, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 346, 1, 'NORMAL', NULL),
(347, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 347, 1, 'NORMAL', NULL),
(348, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 348, 1, 'NORMAL', NULL),
(349, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 349, 1, 'NORMAL', NULL),
(350, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 350, 1, 'NORMAL', NULL),
(351, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 351, 1, 'NORMAL', NULL),
(352, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 352, 1, 'NORMAL', NULL),
(353, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 353, 1, 'NORMAL', NULL),
(354, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 354, 1, 'NORMAL', NULL),
(355, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 355, 1, 'NORMAL', NULL),
(356, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 356, 1, 'NORMAL', NULL),
(357, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 357, 1, 'NORMAL', NULL),
(358, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 358, 1, 'NORMAL', NULL),
(359, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 359, 1, 'NORMAL', NULL),
(360, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 360, 1, 'NORMAL', NULL),
(361, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 361, 1, 'NORMAL', NULL),
(362, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 362, 1, 'NORMAL', NULL),
(363, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 363, 1, 'NORMAL', NULL),
(364, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 364, 1, 'NORMAL', NULL),
(365, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 365, 1, 'NORMAL', NULL),
(366, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 366, 1, 'NORMAL', NULL),
(367, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 367, 1, 'NORMAL', NULL),
(368, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 368, 1, 'NORMAL', NULL),
(369, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 369, 1, 'NORMAL', NULL),
(370, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 370, 1, 'NORMAL', NULL),
(371, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 371, 1, 'NORMAL', NULL),
(372, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 372, 1, 'NORMAL', NULL),
(373, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 373, 1, 'NORMAL', NULL),
(374, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 374, 1, 'NORMAL', NULL),
(375, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 375, 1, 'NORMAL', NULL),
(376, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 376, 1, 'NORMAL', NULL),
(377, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 377, 1, 'NORMAL', NULL),
(378, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 378, 1, 'NORMAL', NULL),
(379, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 379, 1, 'NORMAL', NULL),
(380, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 380, 1, 'NORMAL', NULL),
(381, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 381, 1, 'NORMAL', NULL),
(382, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 382, 1, 'NORMAL', NULL),
(383, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 383, 1, 'NORMAL', NULL),
(384, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 384, 1, 'NORMAL', NULL),
(385, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 385, 1, 'NORMAL', NULL),
(386, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 386, 1, 'NORMAL', NULL),
(387, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 387, 1, 'NORMAL', NULL),
(388, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 388, 1, 'NORMAL', NULL),
(389, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 389, 1, 'NORMAL', NULL),
(390, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 390, 1, 'NORMAL', NULL),
(391, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 391, 1, 'NORMAL', NULL),
(392, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 392, 1, 'NORMAL', NULL),
(393, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 393, 1, 'NORMAL', NULL),
(394, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 394, 1, 'NORMAL', NULL),
(395, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 395, 1, 'NORMAL', NULL),
(396, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 396, 1, 'NORMAL', NULL),
(397, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 397, 1, 'NORMAL', NULL),
(398, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 398, 1, 'NORMAL', NULL),
(399, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 399, 1, 'NORMAL', NULL),
(400, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 400, 1, 'NORMAL', NULL),
(401, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 401, 1, 'NORMAL', NULL),
(402, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 402, 1, 'NORMAL', NULL),
(403, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 403, 1, 'NORMAL', NULL),
(404, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 404, 1, 'NORMAL', NULL),
(405, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 405, 1, 'NORMAL', NULL),
(406, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 406, 1, 'NORMAL', NULL),
(407, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 407, 1, 'NORMAL', NULL),
(408, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 408, 1, 'NORMAL', NULL),
(409, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 409, 1, 'NORMAL', NULL),
(410, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 410, 1, 'NORMAL', NULL),
(411, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 411, 1, 'NORMAL', NULL),
(412, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 412, 1, 'NORMAL', NULL),
(413, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 413, 1, 'NORMAL', NULL),
(414, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 414, 1, 'NORMAL', NULL),
(415, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 415, 1, 'NORMAL', NULL),
(416, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 416, 1, 'NORMAL', NULL),
(417, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 417, 1, 'NORMAL', NULL),
(418, 0, '2010-08-25 01:15:32', 'oop.data.BaseQuestion', 418, 1, 'NORMAL', NULL);


ALTER TABLE `ocwAnswer`
  ADD CONSTRAINT `FK81F532C1EA647FAC` FOREIGN KEY (`content`) REFERENCES `ocwText` (`id`);

ALTER TABLE `ocwArticle`
  ADD CONSTRAINT `FKC38C3A53EA647FAC` FOREIGN KEY (`content`) REFERENCES `ocwText` (`id`),
  ADD CONSTRAINT `FKC38C3A5348AB9093` FOREIGN KEY (`parent`) REFERENCES `ocwArticle` (`id`),
  ADD CONSTRAINT `FKC38C3A537D807870` FOREIGN KEY (`namespace`) REFERENCES `ocwNamespace` (`id`);

ALTER TABLE `ocwArticleAttachment`
  ADD CONSTRAINT `FKDCFAE3D62B8E11D2` FOREIGN KEY (`file_id`) REFERENCES `ocwResource` (`id`),
  ADD CONSTRAINT `FKDCFAE3D628588123` FOREIGN KEY (`article_id`) REFERENCES `ocwArticle` (`id`);

ALTER TABLE `ocwArticleEmbed`
  ADD CONSTRAINT `FK1EA9A0462B8E11D2` FOREIGN KEY (`file_id`) REFERENCES `ocwResource` (`id`),
  ADD CONSTRAINT `FK1EA9A04628588123` FOREIGN KEY (`article_id`) REFERENCES `ocwArticle` (`id`);

ALTER TABLE `ocwArticleTopic`
  ADD CONSTRAINT `FK1F7E1E9C2575393F` FOREIGN KEY (`topic_id`) REFERENCES `ocwResource` (`id`),
  ADD CONSTRAINT `FK1F7E1E9CACC8C07A` FOREIGN KEY (`article_id`) REFERENCES `ocwArticle` (`id`);

ALTER TABLE `ocwBaseQuestionAnswer`
  ADD CONSTRAINT `FKCB25FAF8EF28C9F1` FOREIGN KEY (`question_id`) REFERENCES `ocwArticle` (`id`),
  ADD CONSTRAINT `FKCB25FAF840890D80` FOREIGN KEY (`answer_id`) REFERENCES `ocwAnswer` (`id`);

ALTER TABLE `ocwComment`
  ADD CONSTRAINT `FK27D95BBCB1E4DD9C` FOREIGN KEY (`user`) REFERENCES `ocwUser` (`id`),
  ADD CONSTRAINT `FK27D95BBC4A301B22` FOREIGN KEY (`resource`) REFERENCES `ocwResource` (`id`),
  ADD CONSTRAINT `FK27D95BBC53C202BC` FOREIGN KEY (`revision`) REFERENCES `ocwRevision` (`id`);

ALTER TABLE `ocwHistory`
  ADD CONSTRAINT `FK267351F1B1E4DD9C` FOREIGN KEY (`user`) REFERENCES `ocwUser` (`id`),
  ADD CONSTRAINT `FK267351F1B1E38F2A` FOREIGN KEY (`test`) REFERENCES `ocwArticle` (`id`);

ALTER TABLE `ocwLog`
  ADD CONSTRAINT `FKC3144721B1E4DD9C` FOREIGN KEY (`user`) REFERENCES `ocwUser` (`id`),
  ADD CONSTRAINT `FKC31447213B31D0F8` FOREIGN KEY (`comment`) REFERENCES `ocwComment` (`id`),
  ADD CONSTRAINT `FKC31447214A301B22` FOREIGN KEY (`resource`) REFERENCES `ocwResource` (`id`),
  ADD CONSTRAINT `FKC3144721EC5981D4` FOREIGN KEY (`old_revision`) REFERENCES `ocwRevision` (`id`),
  ADD CONSTRAINT `FKC3144721F3DA5E7B` FOREIGN KEY (`new_revision`) REFERENCES `ocwRevision` (`id`);

ALTER TABLE `ocwQuestion`
  ADD CONSTRAINT `FKB1BC7A29D555486A` FOREIGN KEY (`base_revision`) REFERENCES `ocwRevision` (`id`),
  ADD CONSTRAINT `FKB1BC7A29CBC360D0` FOREIGN KEY (`base_resource`) REFERENCES `ocwResource` (`id`);

ALTER TABLE `ocwResource`
  ADD CONSTRAINT `FKE2E6025172978E26` FOREIGN KEY (`article`) REFERENCES `ocwArticle` (`id`),
  ADD CONSTRAINT `FKE2E602515DDB135C` FOREIGN KEY (`author`) REFERENCES `ocwUser` (`id`),
  ADD CONSTRAINT `FKE2E602515EB7070E` FOREIGN KEY (`link`) REFERENCES `ocwResource` (`id`);

ALTER TABLE `ocwRevision`
  ADD CONSTRAINT `FKE7AEF61E4A301B22` FOREIGN KEY (`resource`) REFERENCES `ocwResource` (`id`),
  ADD CONSTRAINT `FKE7AEF61E5DDB135C` FOREIGN KEY (`author`) REFERENCES `ocwUser` (`id`),
  ADD CONSTRAINT `FKE7AEF61E72978E26` FOREIGN KEY (`article`) REFERENCES `ocwArticle` (`id`);

ALTER TABLE `ocwSection`
  ADD CONSTRAINT `FK64A2EC42EA647FAC` FOREIGN KEY (`content`) REFERENCES `ocwText` (`id`);

ALTER TABLE `ocwSectionQuestion`
  ADD CONSTRAINT `FKB0F82A4866D4B300` FOREIGN KEY (`question_id`) REFERENCES `ocwQuestion` (`id`),
  ADD CONSTRAINT `FKB0F82A485936BFD4` FOREIGN KEY (`section_id`) REFERENCES `ocwSection` (`id`);

ALTER TABLE `ocwSectionStructure`
  ADD CONSTRAINT `FKE0E50B51EA647FAC` FOREIGN KEY (`content`) REFERENCES `ocwText` (`id`);

ALTER TABLE `ocwSectionStructureConstraint`
  ADD CONSTRAINT `FK2228CC0E5CF139C9` FOREIGN KEY (`section_id`) REFERENCES `ocwSectionStructure` (`id`),
  ADD CONSTRAINT `FK2228CC0E9391B220` FOREIGN KEY (`constraint_id`) REFERENCES `ocwConstraint` (`id`);

ALTER TABLE `ocwTestSection`
  ADD CONSTRAINT `FK80DC60D05936BFD4` FOREIGN KEY (`section_id`) REFERENCES `ocwSection` (`id`),
  ADD CONSTRAINT `FK80DC60D05CE45680` FOREIGN KEY (`test_id`) REFERENCES `ocwArticle` (`id`);

ALTER TABLE `ocwTestSectionStructure`
  ADD CONSTRAINT `FK3CCAD6835CF139C9` FOREIGN KEY (`section_id`) REFERENCES `ocwSectionStructure` (`id`),
  ADD CONSTRAINT `FK3CCAD683A7507AD6` FOREIGN KEY (`id`) REFERENCES `ocwArticle` (`id`);

ALTER TABLE `ocwTopicConstraintTopic`
  ADD CONSTRAINT `FK7F38E1E62575393F` FOREIGN KEY (`topic_id`) REFERENCES `ocwResource` (`id`),
  ADD CONSTRAINT `FK7F38E1E68DCE1D63` FOREIGN KEY (`constraint_id`) REFERENCES `ocwConstraint` (`id`);
