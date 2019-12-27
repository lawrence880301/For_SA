-- MySQL Script generated by MySQL Workbench
-- Sun Dec 22 14:33:01 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `missa` DEFAULT CHARACTER SET utf8 ;
USE `missa` ;

-- -----------------------------------------------------
-- Table `mydb`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `missa`.`member` (
  `member_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(1) NOT NULL,
  `lastName` VARCHAR(2) NOT NULL,
  `gender` VARCHAR(5) NOT NULL,
  `Dob` DATETIME NOT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE INDEX `member_id_UNIQUE` (`member_id` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`managers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `missa`.`managers` (
  `managers_id` INT(10) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(30) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Password` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`managers_id`),
  UNIQUE INDEX `managers_id_UNIQUE` (`managers_id` ASC) ,
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`JoinedRoomList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `missa`.`JoinedRoomList` (
  `Id` INT(10) NOT NULL AUTO_INCREMENT,
  `Id_member` INT(10) NOT NULL,
  `Id_room` INT(10) NOT NULL,
  `JoinDate` DATETIME NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CreatedRoomList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `missa`.`CreatedRoomList` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Id_member` INT NOT NULL,
  `Id_room` INT NOT NULL,
  `createDate` DATETIME NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `missa`.`rooms` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(30) NOT NULL,
  `Date` DATETIME NOT NULL,
  `Place` VARCHAR(50) NOT NULL,
  `Type` VARCHAR(30) NOT NULL,
  `Deadline` DATETIME NOT NULL,
  `Maxmember` INT(10) NOT NULL,
  `GenderRestriction` VARCHAR(10) NOT NULL,
  `AgeUpperLimit` INT(10) NOT NULL,
  `AgeDownwardLimit` INT(10) NOT NULL,
  `Creator` VARCHAR(30) NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  `room_deadline` DATETIME NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;