/*! @author Mladen Todorovic */

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `lingua_db`.`tickets` (`id`, `date_created`, `text_domestic`, `text_foreign`, `edited`, `user`) VALUES ('10', '2016-05-24', 'Textic domestic lorem ipsum...', 'Textic foreign lorem ipsum...', 0, '2');
INSERT INTO `lingua_db`.`tickets` (`id`, `date_created`, `text_domestic`, `text_foreign`, `edited`, `user`) VALUES ('11', '2016-05-24', 'Textic domestic lorem ipsum...', 'Textic foreign lorem ipsum...', 1, '2');
UPDATE `lingua_db`.`tickets` SET `deactivated`='2016-05-25' WHERE `id`='8';
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user_tickets` WRITE;
/*!40000 ALTER TABLE `user_tickets` DISABLE KEYS */;
INSERT INTO `user_tickets` VALUES (2,10),(2,11);
/*!40000 ALTER TABLE `user_tickets` ENABLE KEYS */;
UNLOCK TABLES;