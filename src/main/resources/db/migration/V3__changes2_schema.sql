LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (3,'Serbian'),(4,'English'),(5,'German');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
UPDATE `lingua_db`.`user` SET `domestic_language`='3' WHERE `id`='2';
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (6,'2016-05-20','Textic domestic lorem ipsum...','Textic foreign lorem ipsum...',NULL,'\0',2),(7,'2016-05-21','Textic domestic lorem ipsum...','Textic foreign lorem ipsum...',NULL,'\0',2),(8,'2016-05-22','Textic domestic lorem ipsum...','Textic foreign lorem ipsum...',NULL,'\0',2),(9,'2016-05-23','Textic domestic lorem ipsum...','Textic foreign lorem ipsum...',NULL,'\0',2);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user_tickets` WRITE;
/*!40000 ALTER TABLE `user_tickets` DISABLE KEYS */;
INSERT INTO `user_tickets` VALUES (2,6),(2,7),(2,8),(2,9);
/*!40000 ALTER TABLE `user_tickets` ENABLE KEYS */;
UNLOCK TABLES;