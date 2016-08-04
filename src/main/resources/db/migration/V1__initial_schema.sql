/*! @author Mladen Todorovic */

DROP TABLE IF EXISTS `base_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` bit(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `first_name` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `languages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `language_title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `adding_ban` bit(1) DEFAULT NULL,
  `login_ban` bit(1) DEFAULT NULL,
  `voting_ban` bit(1) DEFAULT NULL,
  `date_of_adding_ban` DATETIME COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_of_login_ban` DATETIME COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_of_voting_ban` DATETIME COLLATE utf8_unicode_ci DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `domestic_language` bigint(20) DEFAULT NULL,
  `foreign_language` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hi3yehoowqynwt5f5rjntcb91` (`domestic_language`),
  KEY `FK_b06c1ydioahlapib3ttlgb0jk` (`foreign_language`),
  CONSTRAINT `FK_8qtpnv06elxuryeuv1ac4ximm` FOREIGN KEY (`id`) REFERENCES `base_users` (`id`),
  CONSTRAINT `FK_b06c1ydioahlapib3ttlgb0jk` FOREIGN KEY (`foreign_language`) REFERENCES `languages` (`id`),
  CONSTRAINT `FK_hi3yehoowqynwt5f5rjntcb91` FOREIGN KEY (`domestic_language`) REFERENCES `languages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tickets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `local_date_time` DATETIME COLLATE utf8_unicode_ci DEFAULT NULL,
  `deactivated` date DEFAULT NULL,
  `edited` bit(1) DEFAULT NULL,
  `text_domestic` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `text_foreign` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `learning_language` bigint(20) DEFAULT NULL,
  `domestic_language` bigint(20) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7l33mxamukg6om3h7yhtn5ptn` (`learning_language`),
  KEY `FK_3233mlamukg1om3h7yhtn5pta` (`domestic_language`),
  KEY `FK_1o1qks9jxgqgnoqnyduycqlb8` (`user`),
  CONSTRAINT `FK_1o1qks9jxgqgnoqnyduycqlb8` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_7l33mxamukg6om3h7yhtn5ptn` FOREIGN KEY (`learning_language`) REFERENCES `languages` (`id`),
  CONSTRAINT `FK_3233mlamukg1om3h7yhtn5pta` FOREIGN KEY (`domestic_language`) REFERENCES `languages` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `votes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `votes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dislikes` int(11) NOT NULL,
  `likes` int(11) NOT NULL,
  `ticket` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_f78dg7c8e7y7ne8cfnevgd869` (`ticket`),
  CONSTRAINT `FK_f78dg7c8e7y7ne8cfnevgd869` FOREIGN KEY (`ticket`) REFERENCES `tickets` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `votes_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `votes_users` (
  `vote_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`vote_id`,`user_id`),
  KEY `UK_luhu2ggyumag1y7t6jhpg8ion` (`user_id`),
  CONSTRAINT `FK_cxa7y2u2v1sak5idgjm325hb` FOREIGN KEY (`vote_id`) REFERENCES `votes` (`id`),
  CONSTRAINT `FK_luhu2ggyumag1y7t6jhpg8ion` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `user_tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_tickets` (
  `user_id` bigint(20) NOT NULL,
  `ticket_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_9bh6c1lny71j16qsav1gt0eao` (`ticket_id`),
  KEY `FK_c1ltcs2jv8b75ts4ukncb3frh` (`user_id`),
  CONSTRAINT `FK_9bh6c1lny71j16qsav1gt0eao` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`),
  CONSTRAINT `FK_c1ltcs2jv8b75ts4ukncb3frh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
