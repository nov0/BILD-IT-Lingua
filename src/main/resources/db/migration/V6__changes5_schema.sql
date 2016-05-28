/* @author Bojan Aleksic */

INSERT INTO `lingua_db`.`votes` (`id`, `vote_value`, `ticket`) VALUES ('23', '50', '6');
INSERT INTO `lingua_db`.`votes` (`id`, `vote_value`, `ticket`) VALUES ('24', '14', '7');
INSERT INTO `lingua_db`.`votes` (`id`, `vote_value`, `ticket`) VALUES ('25', '3', '8');
INSERT INTO `lingua_db`.`votes` (`id`, `vote_value`, `ticket`) VALUES ('26', '10', '9');
INSERT INTO `lingua_db`.`votes` (`id`, `vote_value`, `ticket`) VALUES ('27', '15', '10');
INSERT INTO `lingua_db`.`votes` (`id`, `vote_value`, `ticket`) VALUES ('28', '17', '11');
INSERT INTO `lingua_db`.`votes` (`id`, `vote_value`, `ticket`) VALUES ('29', '1', '6');
INSERT INTO `lingua_db`.`votes` (`id`, `vote_value`, `ticket`) VALUES ('30', '2', '7');
INSERT INTO `lingua_db`.`votes` (`id`, `vote_value`, `ticket`) VALUES ('31', '3', '8');
INSERT INTO `lingua_db`.`votes` (`id`, `vote_value`, `ticket`) VALUES ('32', '4', '9');
INSERT INTO `lingua_db`.`votes` (`id`, `vote_value`, `ticket`) VALUES ('33', '5', '10');
INSERT INTO `lingua_db`.`votes` (`id`, `vote_value`, `ticket`) VALUES ('34', '6', '11');

INSERT INTO `lingua_db`.`ticket_votes_down` (`ticket_id`, `vote_id`) VALUES ('6', '29');
INSERT INTO `lingua_db`.`ticket_votes_down` (`ticket_id`, `vote_id`) VALUES ('7', '30');
INSERT INTO `lingua_db`.`ticket_votes_down` (`ticket_id`, `vote_id`) VALUES ('8', '31');
INSERT INTO `lingua_db`.`ticket_votes_down` (`ticket_id`, `vote_id`) VALUES ('9', '32');
INSERT INTO `lingua_db`.`ticket_votes_down` (`ticket_id`, `vote_id`) VALUES ('10', '33');
INSERT INTO `lingua_db`.`ticket_votes_down` (`ticket_id`, `vote_id`) VALUES ('11', '34');

INSERT INTO `lingua_db`.`ticket_votes_up` (`ticket_id`, `vote_id`) VALUES ('6', '23');
INSERT INTO `lingua_db`.`ticket_votes_up` (`ticket_id`, `vote_id`) VALUES ('7', '24');
INSERT INTO `lingua_db`.`ticket_votes_up` (`ticket_id`, `vote_id`) VALUES ('8', '25');
INSERT INTO `lingua_db`.`ticket_votes_up` (`ticket_id`, `vote_id`) VALUES ('9', '26');
INSERT INTO `lingua_db`.`ticket_votes_up` (`ticket_id`, `vote_id`) VALUES ('10', '27');
INSERT INTO `lingua_db`.`ticket_votes_up` (`ticket_id`, `vote_id`) VALUES ('11', '28');