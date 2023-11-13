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
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
);


use openmemo;

-- passwordはPassw0rdのハッシュ値
INSERT INTO 
  `customer` (`email`,`username`,`password`,`role`)
  VALUES ('example@example.com', 'example', '$2a$10$CtK1sZfJeggT1wRuiBgfbu6.r8MbA4v8bmNPhNqov.7VLnyTpBIYe', 'admin');