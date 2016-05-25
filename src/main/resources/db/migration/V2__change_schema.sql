/*! @author Mladen Todorovic */

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_2d37nsdu6wfjfoo0q1l857edx` FOREIGN KEY (`id`) REFERENCES `base_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `base_users` WRITE;
/*!40000 ALTER TABLE `base_users` DISABLE KEYS */;
INSERT INTO `base_users` VALUES (1,'ADMIN','admin@admin.com','','Admin','Adminic','admin','admin');
/*!40000 ALTER TABLE `base_users` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `base_users` WRITE;
/*!40000 ALTER TABLE `base_users` DISABLE KEYS */;
INSERT INTO `base_users` VALUES (2,'USER','yonbe83@gmail.com','','Bojan','Aleksic','yonbe','yonbe');
/*!40000 ALTER TABLE `base_users` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('\0','\0','\0',2,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;