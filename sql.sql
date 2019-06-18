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
                                           `Description` VARCHAR(200),
                                           PRIMARY KEY (`idGood`));

CREATE TABLE IF NOT EXISTS `Shop`.`order` (
											`User_ID` INT NOT NULL,
                                            `Good_idGood` INT NOT NULL,
                                            PRIMARY KEY(`User_ID`, `Good_idGood`),
                                            `date_order` DATE NULL,
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

CREATE TABLE ForSearchingGoods(
		idGood INT PRIMARY KEY,
        Count_of_goods INT,
        Good_name VARCHAR(45),
        Picture_file_name VARCHAR(45),
        Price DECIMAL(6, 2),
        `Description` VARCHAR(200),
        Count_of_repeats INT
	);

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FindByDescription`(UserDescription VARCHAR(45))
    DETERMINISTIC
BEGIN
	DECLARE a, b VARCHAR(45);
    DECLARE c INT;
	DECLARE done BOOLEAN DEFAULT FALSE;
	DECLARE Count_of_spaces INT DEFAULT 0;
    DECLARE Iterator INT DEFAULT 0;
    DECLARE SecondDescription VARCHAR(45) DEFAULT UserDescription;
    DECLARE cur1 CURSOR FOR SELECT idGood,  Good_name, `Description` FROM Good;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    INSERT INTO ForSearchingGoods
	SELECT idGood, Count_of_goods, Good_name, Picture_file_name, Price, Good.`Description`, 0 FROM Good;
    SET Count_of_spaces = LENGTH(UserDescription) - LENGTH(REPLACE(UserDescription, ' ', ''));
    OPEN cur1;
    FETCH cur1 INTO c, a, b;
    WHILE (done = FALSE) DO
    SET UserDescription = SecondDescription;
    SET Iterator = 0;
    WHILE (Iterator != Count_of_spaces + 1) DO
		SET Iterator = Iterator + 1;
        IF(a LIKE CONCAT(CONCAT('%', SUBSTRING_INDEX(UserDescription, ' ', 1)), '%')) THEN
			UPDATE ForSearchingGoods SET Count_of_repeats = Count_of_repeats + 1 WHERE idGood = c;
        END IF;
        IF(b LIKE CONCAT(CONCAT('%', SUBSTRING_INDEX(UserDescription, ' ', 1)), '%')) THEN
			UPDATE ForSearchingGoods SET Count_of_repeats = Count_of_repeats + 1 WHERE idGood = c;
        END IF;
        SET UserDescription = SUBSTRING_INDEX(UserDescription, ' ', -1 * Count_of_spaces + Iterator - 1);
    END WHILE;
    FETCH cur1 INTO c, a, b;
    END WHILE;
    CLOSE cur1;
    SELECT idGood, Count_of_goods, Good_name, Picture_file_name, Price, `Description` FROM ForSearchingGoods
    WHERE Count_of_repeats > 0
    GROUP BY idGood
    ORDER BY Count_of_repeats DESC;
    TRUNCATE ForSearchingGoods;
END$$

create user 'admin'@'localhost';
Grant All PRIVILEGES ON shop.* TO 'admin'@'localhost';

INSERT INTO `good` VALUES (1,0,'Coat Jungo','images/coat_jungo.jpg',3600.30,'This coat is beatiful'),
(2,5,'Dinosaur Trumbo','images/goods/dinosaur_trumbo.jpg',220.20,'This dinosaur is just for you'),
(3,5,'Shoes Joe Arvani','images/goods/shoes_joe_arvani.jpg',1200.40,'Beautiful shoes'),
(4,5,'Cap Cliver','images/goods/cap_cliver.jpg',1500.75,'Creepy and imaginative'),
(5,6,'Dinosaur Rex','images/goods/dinosaur_t-rex.jpg',126.30,'Looks very attractive'),
(6,5,'Jacket NorthIsland','images/goods/jacket_northisland.jpg',2999.99,'Very expensive jacket'),
(7,3,'McQueen racing car','images/goods/mcqueen.jpg',150.50,'Very fast and has a nitro');

INSERT INTO `catalog` VALUES (1,'Тип','Іграшка'),
(2,'Тип','Одяг'),
(3,'Матеріал','Пластмаса'),
(4,'Матеріал','Котон'),
(5,'Матеріал','Еластик'),
(6,'Тип','Взуття');

INSERT INTO `good_has_catalog` VALUES (2,1),(5,1),(7,1),(1,2),(4,2),(6,2),(2,3),(5,3),(7,3),(1,4),(4,4),(6,4),(3,5),(3,6);

INSERT INTO `user` VALUES (2,'admin@gmail.com','1111','Admin','2019-05-14 11:42:37','2019-05-14 11:42:37');

