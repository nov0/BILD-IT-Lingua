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
INSERT INTO `votes` VALUES (1,0,10,6),(2,1,20,7),(3,11,0,8),(4,0,44,9);
INSERT INTO `votes` VALUES (5,30,2,10),(6,0,13,11),(7,0,1,12),(8,0,19,13),(9,1,9,14),(10,0,7,15),(11,0,14,16),(12,1,18,17),(13,2,0,18);
INSERT INTO `votes` VALUES (14,16,0,19),(15,0,29,20),(16,0,4,21),(17,0,26,22),(18,0,1,23),(19,1,0,24),(20,0,10,25),(21,3,0,26),(22,0,11,27);
INSERT INTO `votes` VALUES (23,1,22,28),(24,0,9,29),(25,1,24,30),(26,15,1,31),(27,2,32,32),(28,30,0,33),(29,0,11,34),(30,0,1,35);
INSERT INTO `votes` VALUES (31,0,17,36),(32,3,33,37),(33,40,1,38),(34,0,8,39),(35,7,0,40),(36,5,0,41),(37,0,3,42);
INSERT INTO `votes` VALUES (38,0,5,43),(39,3,0,44),(40,0,12,45),(41,0,9,46),(42,2,0,47),(43,0,1,48);
/*!40000 ALTER TABLE `votes` ENABLE KEYS */;
UNLOCK TABLES;

UPDATE `votes` SET `likes`=1 WHERE `id`='1';
INSERT INTO `votes_users` VALUES (1,2);

UPDATE `votes` SET `dislikes`=1 WHERE `id`='4';
INSERT INTO `votes_users` VALUES (4,2);
