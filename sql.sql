CREATE SCHEMA IF NOT EXISTS `Shop`;
CREATE SCHEMA IF NOT EXISTS `shop`;
USE `Shop` ;
CREATE TABLE IF NOT EXISTS `Shop`.`User` (
                                           `ID` INT NOT NULL AUTO_INCREMENT,
                                           `Email` VARCHAR(60) NOT NULL,
                                           `password` VARCHAR(20) NOT NULL,
                                           `Name` VARCHAR(50) NULL,
                                           `date_created` DATETIME NULL,
                                           `date_last_entered` DATETIME NULL,
                                           PRIMARY KEY (`ID`),
                                           UNIQUE INDEX `Email_UNIQUE` (`Email` ASC));

CREATE TABLE IF NOT EXISTS `Shop`.`catalog` (
                                              `ID` INT NOT NULL AUTO_INCREMENT,
                                              `Name` VARCHAR(60) NULL,
                                              `description` VARCHAR(200) NULL,
                                              PRIMARY KEY (`ID`));

CREATE TABLE IF NOT EXISTS `Shop`.`Good` (
                                           `idGood` INT NOT NULL AUTO_INCREMENT,
                                           `Count_of_goods` INT NOT NULL,
                                           `Good_name` VARCHAR(45) NOT NULL,
                                           `Picture_file_name` VARCHAR(45) NULL,
                                           `Price` DECIMAL(6,2) NULL,
                                           PRIMARY KEY (`idGood`));

CREATE TABLE IF NOT EXISTS `Shop`.`order` (
                                            `ID` INT NOT NULL,
                                            `date_order` DATE NULL,
                                            `User_ID` INT NOT NULL,
                                            `Good_idGood` INT NOT NULL,
                                            PRIMARY KEY (`ID`),
                                            INDEX `fk_order_User1_idx` (`User_ID` ASC),
                                            INDEX `fk_order_Good1_idx` (`Good_idGood` ASC),
                                            CONSTRAINT `fk_order_User1`
                                              FOREIGN KEY (`User_ID`)
                                                REFERENCES `Shop`.`User` (`ID`)
                                                ON DELETE NO ACTION
                                                ON UPDATE NO ACTION,
                                            CONSTRAINT `fk_order_Good1`
                                              FOREIGN KEY (`Good_idGood`)
                                                REFERENCES `Shop`.`Good` (`idGood`)
                                                ON DELETE NO ACTION
                                                ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `Shop`.`Good_has_catalog` (
                                                       `Good_idGood` INT NOT NULL,
                                                       `catalog_ID` INT NOT NULL,
                                                       PRIMARY KEY (`Good_idGood`, `catalog_ID`),
                                                       INDEX `fk_Good_has_catalog_catalog1_idx` (`catalog_ID` ASC),
                                                       INDEX `fk_Good_has_catalog_Good1_idx` (`Good_idGood` ASC),
                                                       CONSTRAINT `fk_Good_has_catalog_Good1`
                                                         FOREIGN KEY (`Good_idGood`)
                                                           REFERENCES `Shop`.`Good` (`idGood`)
                                                           ON DELETE NO ACTION
                                                           ON UPDATE NO ACTION,
                                                       CONSTRAINT `fk_Good_has_catalog_catalog1`
                                                         FOREIGN KEY (`catalog_ID`)
                                                           REFERENCES `Shop`.`catalog` (`ID`)
                                                           ON DELETE NO ACTION
                                                           ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `shop`.`catalog` (
                                              `ID` INT(11) NOT NULL AUTO_INCREMENT,
                                              `Name` VARCHAR(60) NULL DEFAULT NULL,
                                              `description` VARCHAR(200) NULL DEFAULT NULL,
                                              PRIMARY KEY (`ID`));

CREATE TABLE IF NOT EXISTS `shop`.`good` (
                                           `idGood` INT(11) NOT NULL AUTO_INCREMENT,
                                           `Count_of_goods` INT(11) NOT NULL,
                                           `Good_name` VARCHAR(45) NOT NULL,
                                           `Picture_file_name` VARCHAR(45) NULL DEFAULT NULL,
                                           `Price` DECIMAL(6,2) NULL DEFAULT NULL,
                                           `Description` VARCHAR(200) NULL DEFAULT NULL,
                                           PRIMARY KEY (`idGood`));

CREATE TABLE IF NOT EXISTS `shop`.`good_has_catalog` (
                                                       `Good_idGood` INT(11) NOT NULL,
                                                       `catalog_ID` INT(11) NOT NULL,
                                                       PRIMARY KEY (`Good_idGood`, `catalog_ID`),
                                                       INDEX `fk_Good_has_catalog_catalog1_idx` (`catalog_ID` ASC),
                                                       INDEX `fk_Good_has_catalog_Good1_idx` (`Good_idGood` ASC),
                                                       CONSTRAINT `fk_Good_has_catalog_Good1`
                                                         FOREIGN KEY (`Good_idGood`)
                                                           REFERENCES `shop`.`good` (`idGood`),
                                                       CONSTRAINT `fk_Good_has_catalog_catalog1`
                                                         FOREIGN KEY (`catalog_ID`)
                                                           REFERENCES `shop`.`catalog` (`ID`));

CREATE TABLE IF NOT EXISTS `shop`.`user` (
                                           `ID` INT(11) NOT NULL AUTO_INCREMENT,
                                           `Email` VARCHAR(60) NOT NULL,
                                           `password` VARCHAR(20) NOT NULL,
                                           `Name` VARCHAR(50) NULL DEFAULT NULL,
                                           `date_created` DATETIME NULL DEFAULT NULL,
                                           `date_last_entered` DATETIME NULL DEFAULT NULL,
                                           PRIMARY KEY (`ID`),
                                           UNIQUE INDEX `Email_UNIQUE` (`Email` ASC));
CREATE TABLE IF NOT EXISTS `shop`.`order` (
                                            `ID` INT(11) NOT NULL,
                                            `date_order` DATE NULL DEFAULT NULL,
                                            `User_ID` INT(11) NOT NULL,
                                            `Good_idGood` INT(11) NOT NULL,
                                            PRIMARY KEY (`ID`),
                                            INDEX `fk_order_User1_idx` (`User_ID` ASC),
                                            INDEX `fk_order_Good1_idx` (`Good_idGood` ASC),
                                            CONSTRAINT `fk_order_Good1`
                                              FOREIGN KEY (`Good_idGood`)
                                                REFERENCES `shop`.`good` (`idGood`),
                                            CONSTRAINT `fk_order_User1`
                                              FOREIGN KEY (`User_ID`)
                                                REFERENCES `shop`.`user` (`ID`));

INSERT INTO shop.user (ID, Email, password, Name, date_created, date_last_entered) VALUES (1, 'f@gmail.com', '1111', 'Nazar', '2019-05-14 11:42:37', '2019-05-14 11:42:37');
INSERT INTO shop.good (idGood, Count_of_goods, Good_name, Picture_file_name, Price, Description) VALUES (1, 4, 'Куртка Джанго', 'images/good1.jpg', 3600.30, 'This coat is beatiful');
INSERT INTO shop.good (idGood, Count_of_goods, Good_name, Picture_file_name, Price, Description) VALUES (2, 8, 'Динозавр Трумбо', 'images/good2.jpg', 2800.20, 'This dinosaur is just for you');
INSERT INTO shop.good (idGood, Count_of_goods, Good_name, Picture_file_name, Price, Description) VALUES (3, 5, 'Туфлі Джо Арвані', 'images/good3.jpg', 1200.40, 'Beatiful shoes');
INSERT INTO shop.good (idGood, Count_of_goods, Good_name, Picture_file_name, Price, Description) VALUES (4, 6, 'Шапка Клівер', 'images/good4.jpg', 1500.75, 'Creep');
INSERT INTO shop.good_has_catalog (Good_idGood, catalog_ID) VALUES (1, 1);
INSERT INTO shop.good_has_catalog (Good_idGood, catalog_ID) VALUES (2, 2);
INSERT INTO shop.user (ID, Email, password, Name, date_created, date_last_entered) VALUES (1, 'f@gmail.com', '1111', 'Nazar', '2019-05-14 11:42:37', '2019-05-14 11:42:37');


create user 'admin'@'localhost';
Grant All PRIVILEGES ON shop.* TO 'admin'@'localhost';