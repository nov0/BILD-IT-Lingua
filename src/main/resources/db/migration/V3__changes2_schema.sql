/*! @author Mladen Todorovic */

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (6,'Nouns','2016 jun 08 14:24',NULL,'\0','soba','room',5,2),(7,'Nouns','2016 jun 08 14:25',NULL,'\0','prozor','window',5,2),(8,'Nouns','2016 jun 08 14:26',NULL,'\0','knjiga','book',5,2),(9,'Verbs','2016 jun 08 14:27',NULL,'\0','pisati','write',5,2);
INSERT INTO `tickets` VALUES (10,'Nouns','2016 jun 08 14:30',NULL,'\0','prst','finger',5,3),(11,'Verbs','2016 jun 08 14:31',NULL,'\0','gledati','watch',5,3),(12,'Nouns','2016 jun 08 14:32',NULL,'\0','mlijeko','milk',5,3);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user_tickets` WRITE;
/*!40000 ALTER TABLE `user_tickets` DISABLE KEYS */;
INSERT INTO `user_tickets` VALUES (2,6),(2,7),(2,8),(2,9);
INSERT INTO `user_tickets` VALUES (3,10),(3,11),(3,12);
/*!40000 ALTER TABLE `user_tickets` ENABLE KEYS */;
UNLOCK TABLES;