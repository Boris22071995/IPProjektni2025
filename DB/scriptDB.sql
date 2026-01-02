-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema StudentInternship
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema StudentInternship
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `StudentInternship` DEFAULT CHARACTER SET utf8 ;
USE `StudentInternship` ;

-- -----------------------------------------------------
-- Table `StudentInternship`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`Role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `roleId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_User_Role_idx` (`roleId` ASC) VISIBLE,
  CONSTRAINT `fk_User_Role`
    FOREIGN KEY (`roleId`)
    REFERENCES `StudentInternship`.`Role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`Company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`Company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` MEDIUMTEXT NOT NULL,
  `approved` TINYINT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Company_User1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_Company_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `StudentInternship`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`Student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`Student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `indexNumber` VARCHAR(20) NOT NULL,
  `yearOfStudy` INT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Student_User1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_Student_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `StudentInternship`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`Cv`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`Cv` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `profileImagePath` VARCHAR(255) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `postCode` INT NOT NULL,
  `dateOfBirth` DATE NOT NULL,
  `studentId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Cv_Student1_idx` (`studentId` ASC) VISIBLE,
  CONSTRAINT `fk_Cv_Student1`
    FOREIGN KEY (`studentId`)
    REFERENCES `StudentInternship`.`Student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`CvExperience`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`CvExperience` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cvId` INT NOT NULL,
  `company` VARCHAR(100) NOT NULL,
  `position` VARCHAR(100) NOT NULL,
  `description` MEDIUMTEXT NOT NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CvExperience_Cv1_idx` (`cvId` ASC) VISIBLE,
  CONSTRAINT `fk_CvExperience_Cv1`
    FOREIGN KEY (`cvId`)
    REFERENCES `StudentInternship`.`Cv` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`CvEducation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`CvEducation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nameOfInstitution` VARCHAR(100) NOT NULL,
  `titleName` VARCHAR(100) NOT NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NOT NULL,
  `cvId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CvEducation_Cv1_idx` (`cvId` ASC) VISIBLE,
  CONSTRAINT `fk_CvEducation_Cv1`
    FOREIGN KEY (`cvId`)
    REFERENCES `StudentInternship`.`Cv` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`CvInterest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`CvInterest` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `interestName` VARCHAR(50) NOT NULL,
  `cvId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CvInterest_Cv1_idx` (`cvId` ASC) VISIBLE,
  CONSTRAINT `fk_CvInterest_Cv1`
    FOREIGN KEY (`cvId`)
    REFERENCES `StudentInternship`.`Cv` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`CvSkill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`CvSkill` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `skillName` VARCHAR(50) NOT NULL,
  `level` VARCHAR(30) NOT NULL,
  `cvId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CvSkill_Cv1_idx` (`cvId` ASC) VISIBLE,
  CONSTRAINT `fk_CvSkill_Cv1`
    FOREIGN KEY (`cvId`)
    REFERENCES `StudentInternship`.`Cv` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`Internship`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`Internship` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `description` MEDIUMTEXT NOT NULL,
  `requirements` MEDIUMTEXT NOT NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NOT NULL,
  `restrictions` MEDIUMTEXT NOT NULL,
  `companyId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Intership_Company1_idx` (`companyId` ASC) VISIBLE,
  CONSTRAINT `fk_Intership_Company1`
    FOREIGN KEY (`companyId`)
    REFERENCES `StudentInternship`.`Company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`InternshipTechnology`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`InternshipTechnology` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `texhnologyName` VARCHAR(50) NOT NULL,
  `internshipId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_InternshipTechnology_Internship1_idx` (`internshipId` ASC) VISIBLE,
  CONSTRAINT `fk_InternshipTechnology_Internship1`
    FOREIGN KEY (`internshipId`)
    REFERENCES `StudentInternship`.`Internship` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`AiRecommendation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`AiRecommendation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `studentId` INT NOT NULL,
  `internshipId` INT NOT NULL,
  `score` DECIMAL NOT NULL,
  `explanation` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`id`, `studentId`, `internshipId`),
  INDEX `fk_Student_has_Internship_Internship1_idx` (`internshipId` ASC) VISIBLE,
  INDEX `fk_Student_has_Internship_Student1_idx` (`studentId` ASC) VISIBLE,
  CONSTRAINT `fk_Student_has_Internship_Student1`
    FOREIGN KEY (`studentId`)
    REFERENCES `StudentInternship`.`Student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Student_has_Internship_Internship1`
    FOREIGN KEY (`internshipId`)
    REFERENCES `StudentInternship`.`Internship` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`InternshipApplication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`InternshipApplication` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `studentId` INT NOT NULL,
  `internshipId` INT NOT NULL,
  `status` VARCHAR(20) NOT NULL,
  `appliedAt` DATETIME NOT NULL,
  PRIMARY KEY (`id`, `studentId`, `internshipId`),
  INDEX `fk_Student_has_Internship_Internship2_idx` (`internshipId` ASC) VISIBLE,
  INDEX `fk_Student_has_Internship_Student2_idx` (`studentId` ASC) VISIBLE,
  CONSTRAINT `fk_Student_has_Internship_Student2`
    FOREIGN KEY (`studentId`)
    REFERENCES `StudentInternship`.`Student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Student_has_Internship_Internship2`
    FOREIGN KEY (`internshipId`)
    REFERENCES `StudentInternship`.`Internship` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`InternshipAssignment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`InternshipAssignment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `studentId` INT NOT NULL,
  `internshipId` INT NOT NULL,
  `startDate` DATE NOT NULL,
  `endDate` DATE NOT NULL,
  PRIMARY KEY (`id`, `studentId`, `internshipId`),
  INDEX `fk_Student_has_Internship_Internship3_idx` (`internshipId` ASC) VISIBLE,
  INDEX `fk_Student_has_Internship_Student3_idx` (`studentId` ASC) VISIBLE,
  CONSTRAINT `fk_Student_has_Internship_Student3`
    FOREIGN KEY (`studentId`)
    REFERENCES `StudentInternship`.`Student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Student_has_Internship_Internship3`
    FOREIGN KEY (`internshipId`)
    REFERENCES `StudentInternship`.`Internship` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`WorkLog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`WorkLog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `weekNumber` INT NOT NULL,
  `activities` MEDIUMTEXT NOT NULL,
  `internshipAssignmentId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_WorkLog_InternshipAssignment1_idx` (`internshipAssignmentId` ASC) VISIBLE,
  CONSTRAINT `fk_WorkLog_InternshipAssignment1`
    FOREIGN KEY (`internshipAssignmentId`)
    REFERENCES `StudentInternship`.`InternshipAssignment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`CompanyFeedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`CompanyFeedback` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` MEDIUMTEXT NOT NULL,
  `createdAt` DATETIME NOT NULL,
  `internshipAssignmentId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CompanyFeedback_InternshipAssignment1_idx` (`internshipAssignmentId` ASC) VISIBLE,
  CONSTRAINT `fk_CompanyFeedback_InternshipAssignment1`
    FOREIGN KEY (`internshipAssignmentId`)
    REFERENCES `StudentInternship`.`InternshipAssignment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudentInternship`.`FacultyGrade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentInternship`.`FacultyGrade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `grade` INT NOT NULL,
  `comment` MEDIUMTEXT NOT NULL,
  `internshipAssignmentId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_FacultyGrade_InternshipAssignment1_idx` (`internshipAssignmentId` ASC) VISIBLE,
  CONSTRAINT `fk_FacultyGrade_InternshipAssignment1`
    FOREIGN KEY (`internshipAssignmentId`)
    REFERENCES `StudentInternship`.`InternshipAssignment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
