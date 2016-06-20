/*! @author Mladen Todorovic */

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (6,'Nouns','2016 jun 08 14:24',NULL,'\0','soba','room',5,2),(7,'Nouns','2016 jun 08 14:25',NULL,'\0','prozor','window',5,2),(8,'Nouns','2016 jun 08 14:26',NULL,'\0','knjiga','book',5,2),(9,'Verbs','2016 jun 08 14:27',NULL,'\0','pisati','write',5,2);
INSERT INTO `tickets` VALUES (10,'Nouns','2016 jun 08 14:30',NULL,'\0','prst','finger',5,3),(11,'Verbs','2016 jun 08 14:31',NULL,'\0','gledati','watch',5,3),(12,'Nouns','2016 jun 08 14:32',NULL,'\0','mlijeko','milk',5,3),(13,'Nouns','2016 jun 08 14:33',NULL,'\0','ekran','screen',5,3),(14,'Nouns','2016 jun 08 14:34',NULL,'\0','dijete','child',5,3),(15,'Nouns','2016 jun 08 14:35',NULL,'\0','dugme','button',5,3);
INSERT INTO `tickets` VALUES (16,'Nouns','2016 jun 08 14:36',NULL,'\0','ruka','hand',5,3),(17,'Verbs','2016 jun 08 14:35',NULL,'\0','disati','breathe',5,3),(18,'Nouns','2016 jun 08 14:32',NULL,'\0','baza','base',5,3),(19,'Nouns','2016 jun 08 14:36',NULL,'\0','kompjuter','computer',5,3),(20,'Nouns','2016 jun 08 14:37',NULL,'\0','oči','eyes',5,3),(21,'Nouns','2016 jun 08 14:37',NULL,'\0','naočari','glasses',5,3),(22,'Nouns','2016 jun 08 14:37',NULL,'\0','majica','shirt',5,3);
INSERT INTO `tickets` VALUES (23,'Nouns','2016 jun 08 14:38',NULL,'\0','glava','head',5,2),(24,'Verbs','2016 jun 08 14:39',NULL,'\0','trolling','trolati',5,2),(25,'Nouns','2016 jun 08 14:39',NULL,'\0','zid','wall',5,2),(26,'Nouns','2016 jun 08 14:39',NULL,'\0','broj','number',5,2),(27,'Nouns','2016 jun 08 14:40',NULL,'\0','nula','zero',5,2),(28,'Nouns','2016 jun 08 14:41',NULL,'\0','vazduh','air',5,2),(29,'Nouns','2016 jun 08 14:41',NULL,'\0','zub','teeth',5,2),(30,'Nouns','2016 jun 08 14:43',NULL,'\0','plavo','blue',5,2),(31,'Nouns','2016 jun 08 14:43',NULL,'\0','meta','target',5,2);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user_tickets` WRITE;
/*!40000 ALTER TABLE `user_tickets` DISABLE KEYS */;
INSERT INTO `user_tickets` VALUES (2,6),(2,7),(2,8),(2,9);
INSERT INTO `user_tickets` VALUES (3,10),(3,11),(3,12),(3,13),(3,14),(3,15),(3,16),(3,17),(3,18),(3,19),(3,20),(3,21),(3,22);
INSERT INTO `user_tickets` VALUES (2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31);
/*!40000 ALTER TABLE `user_tickets` ENABLE KEYS */;
UNLOCK TABLES;