DROP DATABASE IF EXISTS admin_users;

CREATE DATABASE admin_users;

USE admin_users;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `pincodes`;
SET foreign_key_checks = 1;


/*
 * complaint entity
 *
 * */

-- pincode relate to user

CREATE TABLE `pincodes`(
`id` INTEGER AUTO_INCREMENT NOT NULL UNIQUE,
`pincode` VARCHAR(5) NOT NULL UNIQUE,
CONSTRAINT `pincode_primary_key_constraint`
PRIMARY KEY(`id`)
);

INSERT INTO`pincodes`(`pincode`) VALUES('100');
INSERT INTO`pincodes`(`pincode`) VALUES('101');
INSERT INTO`pincodes`(`pincode`) VALUES('102');
INSERT INTO`pincodes`(`pincode`) VALUES('103');
INSERT INTO`pincodes`(`pincode`) VALUES('104');



--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL UNIQUE,
  `password` char(80) NOT NULL,
  `enabled` tinyint NOT NULL,
  `phone_number` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `pincode_id` integer,
  CONSTRAINT `foreign_key_constraint` FOREIGN KEY (`pincode_id`) REFERENCES`pincodes`(`id`),
  PRIMARY KEY (`id`),
  CONSTRAINT`unique_constraint` UNIQUE KEY (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`,`password`,`enabled`, `phone_number`, `email`,`pincode_id`)
VALUES
('john','{bcrypt}$2a$10$iK8SiXPvC9aVSBGAKWl16ubppO3G8DxYYRwNV.aKsKoCyKxgvIz7e',1,'113_456_783', 'john@luv2code.com',2),
('mary','{bcrypt}$2a$10$iK8SiXPvC9aVSBGAKWl16ubppO3G8DxYYRwNV.aKsKoCyKxgvIz7e',1,'144_456_722','mary@luv2code.com',1),
('susan','{bcrypt}$2a$10$iK8SiXPvC9aVSBGAKWl16ubppO3G8DxYYRwNV.aKsKoCyKxgvIz7e',1,'123_456_567', 'susan@luv2code.com',4);


--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `role` (name)
VALUES
('ROLE_USER'),('ROLE_ADMIN');

SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,

  PRIMARY KEY (`user_id`,`role_id`),

--   KEY `FK_ROLE_idx` (`role_id`),

  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
  REFERENCES `role` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2);

SELECT * FROM user;

SELECT * FROM users_roles;

SELECT * FROM role;
SELECT * FROM pincodes;








DROP TABLE IF EXISTS `user_complaint`;

DROP TABLE IF EXISTS `complaint`;

-- complaint
CREATE TABLE `complaint`(
`id` INTEGER NOT NULL AUTO_INCREMENT,
`active` SMALLINT DEFAULT 0 NOT NULL,
`resolved` SMALLINT DEFAULT 0 NOT NULL,
`escalated` SMALLINT DEFAULT 0 NOT NULL,
`complaint` VARCHAR(300) NOT NULL,
`user_id` INTEGER NOT NULL,
`enginer_id` INTEGER NOT NULL,
`manager_id` INTEGER NOT NULL,
CONSTRAINT `complaint_primary_key_constraint` PRIMARY KEY(`id`)
)AUTO_INCREMENT = 1;



/*users*/
CREATE TABLE `user_complaint`(
`user_id` INTEGER NOT NULL,
`complaint_id`INTEGER NOT NULL,
CONSTRAINT `user_complaint_primary_key_constraint` PRIMARY KEY(`user_id`,`complaint_id`),
CONSTRAINT `user_complaint_FOREIGN_KEY_user_id_constraint` FOREIGN KEY(`user_id`) REFERENCES `user`(`id`),
CONSTRAINT `user_complaint_FOREIGN_KEY_complaint_id_constraint` FOREIGN KEY(`complaint_id`) REFERENCES `complaint`(`id`)
);





