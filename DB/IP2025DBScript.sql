-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema IP2025
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema IP2025
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `IP2025` DEFAULT CHARACTER SET utf8 ;
USE `IP2025` ;

-- -----------------------------------------------------
-- Table `IP2025`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `active` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`Role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`UserRole`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`UserRole` (
  `userId` INT NOT NULL,
  `roleId` INT NOT NULL,
  PRIMARY KEY (`userId`, `roleId`),
  INDEX `fk_User_has_Role_Role1_idx` (`roleId` ASC) VISIBLE,
  INDEX `fk_User_has_Role_User_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_Role_User`
    FOREIGN KEY (`userId`)
    REFERENCES `IP2025`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Role_Role1`
    FOREIGN KEY (`roleId`)
    REFERENCES `IP2025`.`Role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`Student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`Student` (
  `userId` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `indexNumber` VARCHAR(20) NOT NULL,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `yearOfStudy` INT NOT NULL,
  INDEX `fk_Student_User1_idx` (`userId` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Student_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `IP2025`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`Cv`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`Cv` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `studentId` INT NOT NULL,
  `summary` LONGTEXT NOT NULL,
  `profileImagePath` VARCHAR(255) NOT NULL,
  `createdAt` DATETIME NOT NULL,
  `updatedAt` DATETIME NOT NULL,
  INDEX `fk_Cv_Student1_idx` (`studentId` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Cv_Student1`
    FOREIGN KEY (`studentId`)
    REFERENCES `IP2025`.`Student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`CvExperience`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`CvExperience` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cvId` INT NOT NULL,
  `company` VARCHAR(100) NOT NULL,
  `position` VARCHAR(100) NOT NULL,
  `description` MEDIUMTEXT NOT NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CvExperience_Cv1_idx` (`cvId` ASC) VISIBLE,
  CONSTRAINT `fk_CvExperience_Cv1`
    FOREIGN KEY (`cvId`)
    REFERENCES `IP2025`.`Cv` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`CvSkill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`CvSkill` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cvId` INT NOT NULL,
  `skillName` VARCHAR(50) NOT NULL,
  `level` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CvSkill_Cv1_idx` (`cvId` ASC) VISIBLE,
  CONSTRAINT `fk_CvSkill_Cv1`
    FOREIGN KEY (`cvId`)
    REFERENCES `IP2025`.`Cv` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`CvInterest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`CvInterest` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cvId` INT NOT NULL,
  `interestName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CvInterest_Cv1_idx` (`cvId` ASC) VISIBLE,
  CONSTRAINT `fk_CvInterest_Cv1`
    FOREIGN KEY (`cvId`)
    REFERENCES `IP2025`.`Cv` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`Company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`Company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `description` MEDIUMTEXT NOT NULL,
  `approved` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Company_User1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_Company_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `IP2025`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`Intership`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`Intership` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `companyId` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `description` MEDIUMTEXT NOT NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NOT NULL,
  `requirements` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Intership_Company1_idx` (`companyId` ASC) VISIBLE,
  CONSTRAINT `fk_Intership_Company1`
    FOREIGN KEY (`companyId`)
    REFERENCES `IP2025`.`Company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`IntershipTechnology`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`IntershipTechnology` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `intershipId` INT NOT NULL,
  `technologyName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_IntershipTechnology_Intership1_idx` (`intershipId` ASC) VISIBLE,
  CONSTRAINT `fk_IntershipTechnology_Intership1`
    FOREIGN KEY (`intershipId`)
    REFERENCES `IP2025`.`Intership` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`IntershipApplication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`IntershipApplication` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `studentId` INT NOT NULL,
  `intershipId` INT NOT NULL,
  `status` VARCHAR(20) NOT NULL,
  `appliedAt` DATETIME NOT NULL,
  PRIMARY KEY (`id`, `studentId`, `intershipId`),
  INDEX `fk_Student_has_Intership_Intership1_idx` (`intershipId` ASC) VISIBLE,
  INDEX `fk_Student_has_Intership_Student1_idx` (`studentId` ASC) VISIBLE,
  CONSTRAINT `fk_Student_has_Intership_Student1`
    FOREIGN KEY (`studentId`)
    REFERENCES `IP2025`.`Student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Student_has_Intership_Intership1`
    FOREIGN KEY (`intershipId`)
    REFERENCES `IP2025`.`Intership` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`IntershipAssignment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`IntershipAssignment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `studentId` INT NOT NULL,
  `intershipId` INT NOT NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NOT NULL,
  PRIMARY KEY (`id`, `studentId`, `intershipId`),
  INDEX `fk_Student_has_Intership_Intership2_idx` (`intershipId` ASC) VISIBLE,
  INDEX `fk_Student_has_Intership_Student2_idx` (`studentId` ASC) VISIBLE,
  CONSTRAINT `fk_Student_has_Intership_Student2`
    FOREIGN KEY (`studentId`)
    REFERENCES `IP2025`.`Student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Student_has_Intership_Intership2`
    FOREIGN KEY (`intershipId`)
    REFERENCES `IP2025`.`Intership` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`WorkLog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`WorkLog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `intershipAssignmentId` INT NOT NULL,
  `weekNumber` INT NOT NULL,
  `activities` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_WorkLog_IntershipAssignment1_idx` (`intershipAssignmentId` ASC) VISIBLE,
  CONSTRAINT `fk_WorkLog_IntershipAssignment1`
    FOREIGN KEY (`intershipAssignmentId`)
    REFERENCES `IP2025`.`IntershipAssignment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`CompanyFeedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`CompanyFeedback` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `intershipAssignmetId` INT NOT NULL,
  `comment` MEDIUMTEXT NOT NULL,
  `createdAt` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CompanyFeedback_IntershipAssignment1_idx` (`intershipAssignmetId` ASC) VISIBLE,
  CONSTRAINT `fk_CompanyFeedback_IntershipAssignment1`
    FOREIGN KEY (`intershipAssignmetId`)
    REFERENCES `IP2025`.`IntershipAssignment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`FacultyGrade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`FacultyGrade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `intershipAssignmentId` INT NOT NULL,
  `grade` INT NOT NULL,
  `comment` MEDIUMTEXT NOT NULL,
  INDEX `fk_table1_IntershipAssignment1_idx` (`intershipAssignmentId` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_table1_IntershipAssignment1`
    FOREIGN KEY (`intershipAssignmentId`)
    REFERENCES `IP2025`.`IntershipAssignment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IP2025`.`AiRecommendation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `IP2025`.`AiRecommendation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `studentId` INT NOT NULL,
  `intershipId` INT NOT NULL,
  `score` DECIMAL NOT NULL,
  `explanation` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`id`, `studentId`, `intershipId`),
  INDEX `fk_Student_has_Intership_Intership3_idx` (`intershipId` ASC) VISIBLE,
  INDEX `fk_Student_has_Intership_Student3_idx` (`studentId` ASC) VISIBLE,
  CONSTRAINT `fk_Student_has_Intership_Student3`
    FOREIGN KEY (`studentId`)
    REFERENCES `IP2025`.`Student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Student_has_Intership_Intership3`
    FOREIGN KEY (`intershipId`)
    REFERENCES `IP2025`.`Intership` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
