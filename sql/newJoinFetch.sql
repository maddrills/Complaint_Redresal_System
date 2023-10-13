
USE admin_users;



INSERT INTO `complaint`(`active`,`resolved`,`escalated`,`complaint`,`user_id`,`enginer_id`,`manager_id`)
VALUES (1,1,1,'not working',1,1,1);

INSERT INTO `user_complaint`
VALUES (1,1);


SELECT * FROM `user_complaint` AS `uc`
JOIN `user` AS `u`
ON `u`.`id` = `uc`.`user_id`;


SELECT * FROM `user_complaint` AS `uc`
JOIN `user` AS `u`
ON `u`.`id` = `uc`.`user_id`
JOIN `complaint` AS c
ON `c`.`id` = `uc`.`complaint_id`;