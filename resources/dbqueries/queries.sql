CREATE TABLE `delivery_platform`.`users` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `name` VARCHAR(45) NOT NULL,
 `phone_number` VARCHAR(45) NOT NULL,
 `email` VARCHAR(45) NOT NULL,
 `password` VARCHAR(45) NOT NULL,
 PRIMARY KEY (`id`),
 UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

