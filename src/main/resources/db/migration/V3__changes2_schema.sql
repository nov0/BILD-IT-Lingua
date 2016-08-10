/*! @author Mladen Todorovic */

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (6,'nouns','2016-07-08 19:54:37',NULL,'\0','soba','room',4,1,2),(7,'nouns','2016-07-02 14:01:00',NULL,'\0','prozor','window',4,1,2),(8,'nouns','2016-07-05 14:21:44',NULL,'\0','knjiga','book',4,1,2),(9,'verbs','2016-07-08 00:53:33',NULL,'\0','pisati','write',4,1,2);
INSERT INTO `tickets` VALUES (10,'nouns','2016-07-07 02:14:34',NULL,'\0','prst','finger',4,1,3),(11,'verbs','2016-07-08 11:11:56',NULL,'\0','gledati','watch',4,1,3),(12,'nouns','2016-07-07 17:05:13',NULL,'\0','mlijeko','milk',4,1,3),(13,'nouns','2016-07-08 03:47:37',NULL,'\0','ekran','screen',4,1,3),(14,'nouns','2016-07-07 19:23:23',NULL,'\0','dijete','child',4,1,3),(15,'nouns','2016-07-08 19:12:37',NULL,'\0','dugme','button',4,1,3);
INSERT INTO `tickets` VALUES (16,'nouns','2016-07-06 15:22:12',NULL,'\0','ruka','hand',4,1,3),(17,'verbs','2016-07-09 11:23:37',NULL,'\0','disati','breathe',4,1,3),(18,'nouns','2016-07-09 09:54:37',NULL,'\0','baza','base',4,1,3),(19,'nouns','2016-07-08 02:02:37',NULL,'\0','kompjuter','computer',4,1,3),(20,'nouns','2016-07-08 15:44:37',NULL,'\0','nebo','sky',4,1,3),(21,'nouns','2016-07-08 19:05:37',NULL,'\0','torba','bag',4,1,3),(22,'nouns','2016-07-08 19:43:37',NULL,'\0','majica','shirt',4,1,3);
INSERT INTO `tickets` VALUES (23,'nouns','2016-07-09 10:05:30',NULL,'\0','glava','head',4,1,2),(24,'verbs','2016-07-08 09:04:12',NULL,'\0','trolling','trolati',4,1,2),(25,'nouns','2016-07-09 12:45:04',NULL,'\0','zid','wall',4,1,2),(26,'nouns','2016-07-09 13:50:12',NULL,'\0','broj','number',4,1,2),(27,'nouns','2016-07-08 12:13:05',NULL,'\0','nula','zero',4,1,2),(28,'nouns','2016-07-08 18:17:37',NULL,'\0','vazduh','air',4,1,2),(29,'nouns','2016-07-09 08:00:15',NULL,'\0','zub','teeth',4,1,2),(30,'nouns','2016-07-09 08:55:37',NULL,'\0','plavo','blue',4,1,2),(31,'nouns','2016-07-09 08:12:37',NULL,'\0','meta','target',4,1,2);
INSERT INTO `tickets` VALUES (32,'nouns','2016-07-30 17:05:30',NULL,'\0','bicikl','Fahrrad',5,2,6),(33,'nouns','2016-07-30 17:07:30',NULL,'\0','miš','Maus',5,2,6),(34,'nouns','2016-07-30 17:09:30',NULL,'\0','novine','Zeitung',5,2,6),(35,'nouns','2016-07-30 17:10:30',NULL,'\0','pantalone','Hose',5,2,6),(36,'nouns','2016-07-30 17:11:30',NULL,'\0','list','Blatt',5,2,6),(37,'nouns','2016-07-30 17:12:30',NULL,'\0','kralj','König',5,2,6);
INSERT INTO `tickets` VALUES (38,'nouns','2016-07-30 17:13:30',NULL,'\0','skupina','Gruppe',5,3,7),(39,'nouns','2016-07-30 17:15:30',NULL,'\0','momčad','Team',5,3,7),(40,'nouns','2016-07-30 17:20:30',NULL,'\0','gitara','Gitarre',5,3,7),(41,'nouns','2016-07-30 17:21:30',NULL,'\0','bubnjevi','Schlagzeug',5,3,7),(42,'nouns','2016-07-30 17:22:30',NULL,'\0','prostor','Raum',5,3,7);
INSERT INTO `tickets` VALUES (43,'nouns','2016-08-01 19:11:30',NULL,'\0','mouse','miš',1,4,8),(44,'nouns','2016-08-01 19:17:30',NULL,'\0','pencil','olovka',1,4,8),(45,'nouns','2016-08-01 19:19:30',NULL,'\0','sun','sunce',1,4,8);
INSERT INTO `tickets` VALUES (46,'nouns','2016-08-01 19:30:30',NULL,'\0','Zeit','vrijeme',2,5,9),(47,'nouns','2016-08-01 19:33:30',NULL,'\0','Auto','kola',2,5,9),(48,'nouns','2016-08-01 19:35:30',NULL,'\0','Nacht','noć',2,5,9);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user_tickets` WRITE;
/*!40000 ALTER TABLE `user_tickets` DISABLE KEYS */;
INSERT INTO `user_tickets` VALUES (2,6),(2,7),(2,8),(2,9);
INSERT INTO `user_tickets` VALUES (3,10),(3,11),(3,12),(3,13),(3,14),(3,15),(3,16),(3,17),(3,18),(3,19),(3,20),(3,21),(3,22);
INSERT INTO `user_tickets` VALUES (2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(2,31);
INSERT INTO `user_tickets` VALUES (6,32),(6,33),(6,34),(6,35),(6,36),(6,37),(7,38),(7,39),(7,40),(7,41),(7,42);
INSERT INTO `user_tickets` VALUES (8,43),(8,44),(8,45),(9,46),(9,47),(9,48);
/*!40000 ALTER TABLE `user_tickets` ENABLE KEYS */;
UNLOCK TABLES;