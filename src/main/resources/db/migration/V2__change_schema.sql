/*! @author Mladen Todorovic */

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_2d37nsdu6wfjfoo0q1l857edx` FOREIGN KEY (`id`) REFERENCES `base_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `base_users` WRITE;
/*!40000 ALTER TABLE `base_users` DISABLE KEYS */;
INSERT INTO `base_users` VALUES (1,'ADMIN','admin@admin.com','','Admin','Adminic','$2a$10$7apjkSZSaBsJGgio/zWVOOmombHmupiywX4NuX2/I.mL0sG7ruLXC','admin');
/*!40000 ALTER TABLE `base_users` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `base_users` WRITE;
/*!40000 ALTER TABLE `base_users` DISABLE KEYS */;
INSERT INTO `base_users` VALUES (2,'USER','yonbe83@gmail.com','','Bojan','Aleksic','$2a$10$YGj4GEVqkh8V0pXK9Y5D/uFcCNwGrKCz1pJU3x1PV916p9hQN1ep.','yonbe');
INSERT INTO `base_users` VALUES (3,'USER','novo@gmail.com','','Novislav','Sekulic','$2a$10$kA5IBQU/Gi2/yyZOrreEA.Sj1zDgGC0ctqyWMNIX.I2JZYhyKLINS','novos');
INSERT INTO `base_users` VALUES (4,'USER','gogi@gmail.com','','Goran','Arsenic','$2a$10$uzEwWbLm8v.KZVruhU11Ae6nDBqtGvFWh3NVLxmXwTvy3PjBfpgBy','goran');
INSERT INTO `base_users` VALUES (5,'USER','djomla79@gmail.com','','Mladen','Todorovic','$2a$10$0J9EJ/95st7qoBZpNw3t..tllIEMP5Fkr1MKGVp7MKhk.1EtP/WUi','djomla');
INSERT INTO `base_users` VALUES (6,'USER','senjin@gmail.com','','Senjin','Hajrulahovic','$2a$10$lKaSOqNMEO4d.nno73Ch0OOuWEUIAa3c8VGadGdb0Oo.GUY73AzLW','senjin');
INSERT INTO `base_users` VALUES (7,'USER','davor@gobac.com','','Davor','Gobac','$2a$10$6IUu.TWAOI0L8p./vwmSxezRyvC29.q7.gvK8/wNmQkgmx9XUdN72','gobac');
INSERT INTO `base_users` VALUES (8,'USER','john.smith@js.com','','John','Smith','$2a$10$I09MN.4NK6vAUGQ1265KOe2zfD5ZCd2Fo8veBM1wk3hJQAOgET4XO','smith');
INSERT INTO `base_users` VALUES (9,'USER','johann.schmidt@js.com','','Johann','Schmidt','$2a$10$HKkHRre57ScZAKUZQnnKduKj.JXbyTKqwDf72mXRA.QPkO7LXEuTu','johann');
/*!40000 ALTER TABLE `base_users` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (1,'Serbian'),(2,'Bosnian'),(3,'Croatian'),(4,'English'),(5,'German');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'\0','\0','2016-08-04 11:01:00',NULL,NULL,2,1,4);
INSERT INTO `user` VALUES (1,'\0','\0','2016-08-04 11:03:00',NULL,NULL,3,1,4);
INSERT INTO `user` VALUES ('\0','\0',1,NULL,NULL,'2016-08-04 11:05:00',4,1,5);
INSERT INTO `user` VALUES (1,'\0',1,'2016-08-04 11:07:00',NULL,'2016-08-04 11:09:00',5,1,4);
INSERT INTO `user` VALUES ('\0','\0',1,NULL,NULL,'2016-08-04 11:10:00',6,2,5);
INSERT INTO `user` VALUES (1,'\0',1,'2016-08-04 11:11:00',NULL,'2016-08-04 11:13:00',7,3,5);
INSERT INTO `user` VALUES ('\0','\0',1,NULL,NULL,'2016-08-04 11:15:00',8,4,1);
INSERT INTO `user` VALUES ('\0','\0','\0',NULL,NULL,NULL,9,5,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;