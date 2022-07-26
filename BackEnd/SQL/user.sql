-- -----------------------------------------------------
-- Schema ssafy_web_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafy_web_db` DEFAULT CHARACTER SET utf8mb4 ;
USE `ssafy_web_db` ;

-- -----------------------------------------------------
-- Table `ssafy_web_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`user` (
  `user_id` VARCHAR(13) NOT NULL,
  `id` VARCHAR(20) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- -----------------------------------------------------
-- Table `ssafy_web_db`.`therapist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafy_web_db`.`therapist` (
  `ther_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(13) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `address` VARCHAR(80) NOT NULL,
  `profile_url` VARCHAR(200) NULL,
  `file_url` VARCHAR(200) NULL,
  PRIMARY KEY (`ther_no`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id_ther`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafy_web_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

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
  PRIMARY KEY (`parent_no`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafy_web_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


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
    REFERENCES `ssafy_web_db`.`parent` (`parent_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;