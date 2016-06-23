SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `transportesys` ;
CREATE SCHEMA IF NOT EXISTS `transportesys` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `transportesys` ;

-- -----------------------------------------------------
-- Table `transportesys`.`provincia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transportesys`.`provincia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) NOT NULL,
  `nombre` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transportesys`.`camionero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transportesys`.`camionero` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dni` VARCHAR(255) NOT NULL,
  `nombre` VARCHAR(255) NOT NULL,
  `direccion` VARCHAR(50) NOT NULL,
  `salario` INT NOT NULL,
  `poblacion` INT NOT NULL,
  `telefono` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transportesys`.`camion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transportesys`.`camion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `matricula` VARCHAR(255) NOT NULL,
  `potencia` DOUBLE NULL,
  `modelo` VARCHAR(255) NOT NULL,
  `tipo` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transportesys`.`camion_camionero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transportesys`.`camion_camionero` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `camionero_id` INT NOT NULL,
  `camion_id` INT NOT NULL,
  `fecha` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_camion_camionero_camionero1_idx` (`camionero_id` ASC),
  INDEX `fk_camion_camionero_camion1_idx` (`camion_id` ASC),
  CONSTRAINT `fk_camion_camionero_camionero1`
    FOREIGN KEY (`camionero_id`)
    REFERENCES `transportesys`.`camionero` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_camion_camionero_camion1`
    FOREIGN KEY (`camion_id`)
    REFERENCES `transportesys`.`camion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transportesys`.`paquete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transportesys`.`paquete` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `provincia_id` INT NOT NULL,
  `camion_camionero_id` INT NOT NULL,
  `codigo` VARCHAR(255) NOT NULL,
  `descripcion` VARCHAR(255) NOT NULL,
  `destinatario` VARCHAR(255) NOT NULL,
  `direccion_destinatario` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_paquete_provincia1_idx` (`provincia_id` ASC),
  INDEX `fk_paquete_camion_camionero1_idx` (`camion_camionero_id` ASC),
  CONSTRAINT `fk_paquete_provincia1`
    FOREIGN KEY (`provincia_id`)
    REFERENCES `transportesys`.`provincia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_paquete_camion_camionero1`
    FOREIGN KEY (`camion_camionero_id`)
    REFERENCES `transportesys`.`camion_camionero` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
