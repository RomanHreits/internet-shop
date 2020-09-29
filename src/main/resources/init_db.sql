CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;

   CREATE TABLE `internet_shop`.`products` (
  `product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DOUBLE NOT NULL,
  `is_deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`product_id`));

   CREATE TABLE `internet_shop`.`users` (
  `user_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `login` VARCHAR(225) NOT NULL UNIQUE,
  `password` VARCHAR(225) NOT NULL,
  `salt` VARBINARY(16) NOT NULL,
  `is_deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`));

  CREATE TABLE `internet_shop`.`shopping_carts` (
  `cart_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(11) NOT NULL,
  `is_deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`cart_id`),
  INDEX `users_cart_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `users_cart_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

   CREATE TABLE `internet_shop`.`roles` (
  `role_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(256) NOT NULL UNIQUE,
   PRIMARY KEY (`role_id`));

   CREATE TABLE `internet_shop`.`user_roles` (
  `user_id` BIGINT(11) NOT NULL,
  `role_id` BIGINT(11) NOT NULL,
  INDEX `user_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `role_fk_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `role_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `internet_shop`.`roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `internet_shop`.`orders` (
  `order_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(11) NOT NULL,
  `is_deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`order_id`),
  INDEX `user_order_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_order_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `internet_shop`.`shopping_cart_products` (
  `cart_id` BIGINT(11) NOT NULL,
  `product_id` BIGINT(11) NOT NULL,
  INDEX `cart_fk_idx` (`cart_id` ASC) VISIBLE,
  INDEX `product_fk_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `cart_fk`
    FOREIGN KEY (`cart_id`)
    REFERENCES `internet_shop`.`shopping_carts` (`cart_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `internet_shop`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `internet_shop`.`orders_products` (
  `order_id` BIGINT(11) NOT NULL,
  `product_id` BIGINT(11) NOT NULL,
  INDEX `order_fk_idx` (`order_id` ASC) VISIBLE,
  INDEX `product_fk_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `order_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `internet_shop`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_order_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `internet_shop`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    INSERT INTO `internet_shop`.`roles`(`role_name`) VALUES ('ADMIN');
    INSERT INTO `internet_shop`.`roles`(`role_name`) VALUES ('USER');
