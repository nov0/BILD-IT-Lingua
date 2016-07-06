/*! @author Mladen Todorovic */

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (6,'nouns','2016 jun 08 14:24',NULL,'\0','soba','room',5,4,2),(7,'nouns','2016 jun 08 14:25',NULL,'\0','prozor','window',5,4,2),(8,'nouns','2016 jun 08 14:26',NULL,'\0','knjiga','book',5,4,2),(9,'verbs','2016 jun 08 14:27',NULL,'\0','pisati','write',5,4,2);
INSERT INTO `tickets` VALUES (10,'nouns','2016 jun 08 14:30',NULL,'\0','prst','finger',5,4,3),(11,'verbs','2016 jun 08 14:31',NULL,'\0','gledati','watch',5,4,3),(12,'nouns','2016 jun 08 14:32',NULL,'\0','mlijeko','milk',5,4,3),(13,'nouns','2016 jun 08 14:33',NULL,'\0','ekran','screen',5,4,3),(14,'nouns','2016 jun 08 14:34',NULL,'\0','dijete','child',5,4,3),(15,'nouns','2016 jun 08 14:35',NULL,'\0','dugme','button',5,4,3);
INSERT INTO `tickets` VALUES (16,'nouns','2016 jun 08 14:36',NULL,'\0','ruka','hand',5,4,3),(17,'verbs','2016 jun 08 14:35',NULL,'\0','disati','breathe',5,4,3),(18,'nouns','2016 jun 08 14:32',NULL,'\0','baza','base',5,4,3),(19,'nouns','2016 jun 08 14:36',NULL,'\0','kompjuter','computer',5,4,3),(20,'nouns','2016 jun 08 14:37',NULL,'\0','nebo','sky',5,4,3),(21,'nouns','2016 jun 08 14:37',NULL,'\0','torba','bag',5,4,3),(22,'nouns','2016 jun 08 14:37',NULL,'\0','majica','shirt',5,4,3);
INSERT INTO `tickets` VALUES (23,'nouns','2016 jun 08 14:38',NULL,'\0','glava','head',5,4,2),(24,'verbs','2016 jun 08 14:39',NULL,'\0','trolling','trolati',5,4,2),(25,'nouns','2016 jun 08 14:39',NULL,'\0','zid','wall',5,4,2),(26,'nouns','2016 jun 08 14:39',NULL,'\0','broj','number',5,4,2),(27,'nouns','2016 jun 08 14:40',NULL,'\0','nula','zero',5,4,2),(28,'nouns','2016 jun 08 14:41',NULL,'\0','vazduh','air',5,4,2),(29,'nouns','2016 jun 08 14:41',NULL,'\0','zub','teeth',5,4,2),(30,'nouns','2016 jun 08 14:43',NULL,'\0','plavo','blue',5,4,2),(31,'nouns','2016 jun 08 14:43',NULL,'\0','meta','target',5,4,2);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user_tickets` WRITE;
/*!40000 ALTER TABLE `user_tickets` DISABLE KEYS */;
INSERT INTO `user_tickets` VALUES (2,6),(2,7),(2,8),(2,9);
INSERT INTO `user_tickets` VALUES (3,10),(3,11),(3,12),(3,13),(3,14),(3,15),(3,16),(3,17),(3,18),(3,19),(3,20),(3,21),(3,22);
INSERT INTO `user_tickets` VALUES (2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31);
/*!40000 ALTER TABLE `user_tickets` ENABLE KEYS */;
UNLOCK TABLES;