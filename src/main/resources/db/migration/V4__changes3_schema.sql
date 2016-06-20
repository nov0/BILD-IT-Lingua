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
INSERT INTO `votes` VALUES (5,0,0,10),(6,0,0,11),(7,0,0,12),(8,0,0,13),(9,0,0,14),(10,0,0,15),(11,0,0,16),(12,0,0,17),(13,0,0,18);
INSERT INTO `votes` VALUES (14,0,0,19),(15,0,0,20),(16,0,0,21),(17,0,0,22),(18,0,0,23),(19,0,0,24),(20,0,0,25),(21,0,0,26),(22,0,0,27);
INSERT INTO `votes` VALUES (23,0,0,28),(24,0,0,29),(25,0,0,30),(26,0,0,31);
/*!40000 ALTER TABLE `votes` ENABLE KEYS */;
UNLOCK TABLES;

UPDATE `votes` SET `likes`=1 WHERE `id`='1';
INSERT INTO `votes_users` VALUES (1,2);

UPDATE `votes` SET `dislikes`=1 WHERE `id`='4';
INSERT INTO `votes_users` VALUES (4,2);
