/*! @author Mladen Todorovic */

DROP TABLE IF EXISTS `base_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `base_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  `email` varchar(80) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `password` varchar(40) NOT NULL,
  `username` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `base_users` WRITE;
/*!40000 ALTER TABLE `base_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_users` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `languages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `language_title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `login_ban` bit(1) DEFAULT NULL,
  `adding_ban` bit(1) DEFAULT NULL,
  `voting_ban` bit(1) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `domestic_language` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hi3yehoowqynwt5f5rjntcb91` (`domestic_language`),
  CONSTRAINT `FK_8qtpnv06elxuryeuv1ac4ximm` FOREIGN KEY (`id`) REFERENCES `base_users` (`id`),
  CONSTRAINT `FK_hi3yehoowqynwt5f5rjntcb91` FOREIGN KEY (`domestic_language`) REFERENCES `languages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tickets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `text_domestic` varchar(255) DEFAULT NULL,
  `text_foreign` varchar(255) DEFAULT NULL,
  `deactivated` date DEFAULT NULL,
  `edited` bit(1) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1o1qks9jxgqgnoqnyduycqlb8` (`user`),
  CONSTRAINT `FK_1o1qks9jxgqgnoqnyduycqlb8` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `votes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `votes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vote_value` int(11) NOT NULL DEFAULT 0,
  `ticket` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_f78dg7c8e7y7ne8cfnevgd869` (`ticket`),
  CONSTRAINT `FK_f78dg7c8e7y7ne8cfnevgd869` FOREIGN KEY (`ticket`) REFERENCES `tickets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `votes` WRITE;
/*!40000 ALTER TABLE `votes` DISABLE KEYS */;
/*!40000 ALTER TABLE `votes` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `ticket_votes_down`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_votes_down` (
  `ticket_id` bigint(20) NOT NULL,
  `vote_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_c714dikqy4c41457g77gheowo` (`vote_id`),
  KEY `FK_4wuy7uwygma0bb3si88q72tvq` (`ticket_id`),
  CONSTRAINT `FK_4wuy7uwygma0bb3si88q72tvq` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`),
  CONSTRAINT `FK_c714dikqy4c41457g77gheowo` FOREIGN KEY (`vote_id`) REFERENCES `votes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `ticket_votes_down` WRITE;
/*!40000 ALTER TABLE `ticket_votes_down` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_votes_down` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `ticket_votes_up`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_votes_up` (
  `ticket_id` bigint(20) NOT NULL,
  `vote_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_9rk9j0youi2yqv0w92dtwn9rb` (`vote_id`),
  KEY `FK_ekie7ggfqy2pay0gnr5uekh8b` (`ticket_id`),
  CONSTRAINT `FK_9rk9j0youi2yqv0w92dtwn9rb` FOREIGN KEY (`vote_id`) REFERENCES `votes` (`id`),
  CONSTRAINT `FK_ekie7ggfqy2pay0gnr5uekh8b` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `ticket_votes_up` WRITE;
/*!40000 ALTER TABLE `ticket_votes_up` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_votes_up` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `user_tickets` WRITE;
/*!40000 ALTER TABLE `user_tickets` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_tickets` ENABLE KEYS */;
UNLOCK TABLES;
