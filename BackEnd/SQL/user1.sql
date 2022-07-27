-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ssafy_web_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafy_web_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafy_web_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ssafy_web_db` ;

-- -----------------------------------------------------
-- Table `ssafy_web_db`.`parent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`parent` (
  `parent_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(13) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `address` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`parent_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_web_db`.`child`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`child` (
  `child_no` INT NOT NULL AUTO_INCREMENT,
  `parent_no` INT NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `birth` DATE NOT NULL,
  `gender` INT NOT NULL,
  PRIMARY KEY (`child_no`),
  INDEX `parent_no_idx` (`parent_no` ASC) VISIBLE,
  CONSTRAINT `parent_no`
    FOREIGN KEY (`parent_no`)
    REFERENCES `ssafy_web_db`.`parent` (`parent_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_web_db`.`therapist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`therapist` (
  `ther_no` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `user_id` VARCHAR(13) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `address` VARCHAR(80) NOT NULL,
  `profile_url` VARCHAR(200) NULL DEFAULT NULL,
  `file_url` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`ther_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafy_web_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`user` (
  `user_id` VARCHAR(13) NOT NULL,
  `id` VARCHAR(20) NOT NULL,
  `password` VARCHAR(10000) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS
;
select * from user;
select * from parent;
select * from therapist;

select * from parent join user on parent.user_id = user.user_id;
