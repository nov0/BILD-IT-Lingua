INSERT INTO `admin` VALUES (1);

INSERT INTO `base_users` VALUES (1,'ADMIN','admin@admin.com',1,'Admin','Adminic','admin','admin'),(2,'USER','yonbe83@gmail.com',1,'Bojan','Aleksic','yonbe','yonbe');

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `adding_ban` tinyint(1) DEFAULT NULL,
  `login_ban` tinyint(1) DEFAULT NULL,
  `voting_ban` tinyint(1) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_8qtpnv06elxuryeuv1ac4ximm` FOREIGN KEY (`id`) REFERENCES `base_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `user` VALUES (0,0,0,2);
