CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internet_shop`.`products` (
  `product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `productName` VARCHAR(255) NOT NULL,
  `price` DOUBLE NOT NULL,
  `is_deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`product_id`),
  UNIQUE INDEX `productName_UNIQUE` (`productName` ASC) VISIBLE);
