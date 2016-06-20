/*! @author Mladen Todorovic */

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
UPDATE `tickets` SET `edited`=1 WHERE `id`='9';
UPDATE `tickets` SET `edited`=1 WHERE `id`='12';
UPDATE `tickets` SET `deactivated`='2016-06-08' WHERE `id`='8';
UPDATE `tickets` SET `deactivated`='2016-06-08' WHERE `id`='10';
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `votes` WRITE;
/*!40000 ALTER TABLE `votes` DISABLE KEYS */;
INSERT INTO `votes` VALUES (1,0,0,6),(2,0,0,7),(3,0,0,8),(4,0,0,9);
INSERT INTO `votes` VALUES (5,0,0,10),(6,0,0,11),(7,0,0,12);
/*!40000 ALTER TABLE `votes` ENABLE KEYS */;
UNLOCK TABLES;

UPDATE `votes` SET `likes`=1 WHERE `id`='1';
INSERT INTO `votes_users` VALUES (1,2);

UPDATE `votes` SET `dislikes`=1 WHERE `id`='4';
INSERT INTO `votes_users` VALUES (4,2);
