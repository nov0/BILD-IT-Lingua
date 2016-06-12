/*! @author Mladen Todorovic */

ALTER TABLE `tickets` ADD COLUMN `dislikes` bigint(20) DEFAULT NULL, ADD FOREIGN KEY (`dislikes`) REFERENCES `votes` (`id`);
ALTER TABLE `tickets` ADD COLUMN `likes` bigint(20) DEFAULT NULL, ADD FOREIGN KEY (`likes`) REFERENCES `votes` (`id`);

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (6,'Nouns','2016 jun 08 14:24',NULL,'\0','soba','room',5,2,NULL,NULL),(9,'Nouns','2016 jun 08 14:25',NULL,'\0','prozor','window',5,2,NULL,NULL),(12,'Nouns','2016 jun 08 14:26',NULL,'\0','knjiga','book',5,2,NULL,NULL),(15,'Verbs','2016 jun 08 14:27',NULL,'\0','pisati','write',5,2,NULL,NULL);
INSERT INTO `tickets` VALUES (18,'Nouns','2016 jun 08 14:30',NULL,'\0','prst','finger',5,3,NULL,NULL),(21,'Verbs','2016 jun 08 14:31',NULL,'\0','gledati','watch',5,3,NULL,NULL),(24,'Nouns','2016 jun 08 14:32',NULL,'\0','mlijeko','milk',5,3,NULL,NULL);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user_tickets` WRITE;
/*!40000 ALTER TABLE `user_tickets` DISABLE KEYS */;
INSERT INTO `user_tickets` VALUES (2,6),(2,9),(2,12),(2,15);
INSERT INTO `user_tickets` VALUES (3,18),(3,21),(3,24);
/*!40000 ALTER TABLE `user_tickets` ENABLE KEYS */;
UNLOCK TABLES;