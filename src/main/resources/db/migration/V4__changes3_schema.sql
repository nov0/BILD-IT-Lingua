/*! @author Mladen Todorovic */

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
UPDATE `lingua_db`.`tickets` SET `edited`=1 WHERE `id`='9';
UPDATE `lingua_db`.`tickets` SET `deactivated`='2016-05-25' WHERE `id`='7';
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;