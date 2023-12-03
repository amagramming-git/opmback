CREATE TABLE `openmemo`.`post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customerId` INT NOT NULL,
  `content` VARCHAR(8192) NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));

CREATE TABLE `openmemo`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `openmemo`.`authority` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `rolename` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
);



-- passwordはPassw0rdのハッシュ値
use openmemo;

INSERT INTO 
  `authority` (`customer_id`,`rolename`)
  VALUES (1, 'ROLE_ADMIN');

INSERT INTO 
  `authority` (`customer_id`,`rolename`)
  VALUES (1, 'ROLE_USER');

INSERT INTO 
  `authority` (`customer_id`,`rolename`)
  VALUES (2, 'ROLE_USER');

INSERT INTO 
  `customer` (`email`,`username`,`password`)
  VALUES ('admin@example.com', 'example', '$2a$10$CtK1sZfJeggT1wRuiBgfbu6.r8MbA4v8bmNPhNqov.7VLnyTpBIYe');

INSERT INTO 
  `customer` (`email`,`username`,`password`)
  VALUES ('customer@example.com', 'example', '$2a$10$CtK1sZfJeggT1wRuiBgfbu6.r8MbA4v8bmNPhNqov.7VLnyTpBIYe');


INSERT INTO 
  `post` (`customerId`,`content`)
  VALUES (1, 'i am a admin');

INSERT INTO 
  `post` (`customerId`,`content`)
  VALUES (2, 'i am a user');
