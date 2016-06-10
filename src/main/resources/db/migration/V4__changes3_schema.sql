/*! @author Mladen Todorovic */

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
UPDATE `tickets` SET `edited`=1 WHERE `id`='9';
UPDATE `tickets` SET `edited`=1 WHERE `id`='24';
UPDATE `tickets` SET `deactivated`='2016-06-08' WHERE `id`='12';
UPDATE `tickets` SET `deactivated`='2016-06-08' WHERE `id`='18';
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `votes` WRITE;
/*!40000 ALTER TABLE `votes` DISABLE KEYS */;
INSERT INTO `votes` VALUES (7,0,6),(8,0,6),(10,0,9),(11,0,9),(13,0,12),(14,0,12),(16,0,15),(17,0,15);
INSERT INTO `votes` VALUES (19,0,18),(20,0,18),(22,0,21),(23,0,21),(25,0,24),(26,0,24);
/*!40000 ALTER TABLE `votes` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
UPDATE `tickets` SET `dislikes`=7 WHERE `id`='6';
UPDATE `tickets` SET `likes`=8 WHERE `id`='6';
UPDATE `tickets` SET `dislikes`=10 WHERE `id`='9';
UPDATE `tickets` SET `likes`=11 WHERE `id`='9';
UPDATE `tickets` SET `dislikes`=13 WHERE `id`='12';
UPDATE `tickets` SET `likes`=14 WHERE `id`='12';
UPDATE `tickets` SET `dislikes`=16 WHERE `id`='15';
UPDATE `tickets` SET `likes`=17 WHERE `id`='15';
UPDATE `tickets` SET `dislikes`=19 WHERE `id`='18';
UPDATE `tickets` SET `likes`=20 WHERE `id`='18';
UPDATE `tickets` SET `dislikes`=22 WHERE `id`='21';
UPDATE `tickets` SET `likes`=23 WHERE `id`='21';
UPDATE `tickets` SET `dislikes`=25 WHERE `id`='24';
UPDATE `tickets` SET `likes`=26 WHERE `id`='24';
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

UPDATE `votes` SET `vote_value`=1 WHERE `id`='8';
INSERT INTO `votes_users` VALUES (8,3);

UPDATE `votes` SET `vote_value`=1 WHERE `id`='26';
INSERT INTO `votes_users` VALUES (26,2);