-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema acaipde
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `acaipde` ;

-- -----------------------------------------------------
-- Schema acaipde
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `acaipde` DEFAULT CHARACTER SET utf8 ;
USE `acaipde` ;

-- -----------------------------------------------------
-- Table `acaipde`.`tecnico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acaipde`.`tecnico` ;

CREATE TABLE IF NOT EXISTS `acaipde`.`tecnico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `matricula` VARCHAR(10) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acaipde`.`estabelecimento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acaipde`.`estabelecimento` ;

CREATE TABLE IF NOT EXISTS `acaipde`.`estabelecimento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `razaoSocial` VARCHAR(200) NULL,
  `nomeFantasia` VARCHAR(200) NOT NULL,
  `cnpj` VARCHAR(45) NULL,
  `nomeContato` VARCHAR(200) NOT NULL COMMENT 'Proprietario ou responsavel pelo estabelecimento',
  `rg` VARCHAR(11) NULL,
  `cpf` VARCHAR(15) NULL,
  `email` VARCHAR(45) NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `cep` VARCHAR(9) NOT NULL,
  `logradouro` VARCHAR(100) NOT NULL,
  `numero` VARCHAR(45) NULL,
  `complemento` VARCHAR(200) NULL,
  `bairro` VARCHAR(100) NULL,
  `cidade` VARCHAR(100) NULL,
  `uf` VARCHAR(3) NULL,
  `dataCadastro` DATE NOT NULL,
  `dataLicenca` DATE NULL COMMENT 'Data que foi expedida a licença para o estabelecimento',
  `dataVencimento` DATE NULL,
  `status` VARCHAR(20) NOT NULL COMMENT 'inicia apoós o cadastro \"aguardando vistoria\", solicitou licença.\npendente sofreu vitoria e esta irregular,\n\nregular sofreu vistoria e esta apto.\n\nvencida, não renovou.',
  `latitude` DECIMAL(9,6) NULL,
  `longitude` DECIMAL(9,6) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acaipde`.`equipamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acaipde`.`equipamento` ;

CREATE TABLE IF NOT EXISTS `acaipde`.`equipamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` TEXT(200) NULL,
  `status` VARCHAR(45) NOT NULL COMMENT 'Obrigatorio/Não Obrigatorio',
  `material` VARCHAR(45) NULL COMMENT 'Em condições\nSem condições\nInexistente.\n',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acaipde`.`vistoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acaipde`.`vistoria` ;

CREATE TABLE IF NOT EXISTS `acaipde`.`vistoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tecnico1` INT NULL,
  `tecnico2` INT NULL,
  `estabelecimento` INT NOT NULL,
  `dataSolicitacao` DATE NULL,
  `dataVistoria` DATE NULL,
  `prazo` INT NULL COMMENT 'Prazo em dias que o estabelecimento deve aplicar as correções indicadas pela vigilancia',
  `apto` TINYINT(1) NOT NULL DEFAULT 0,
  `observacao` TEXT(200) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_estabelecimento_has_inspecao_estabelecimento1_idx` (`estabelecimento` ASC),
  INDEX `fk_vistoria_tecnico1_idx` (`tecnico1` ASC),
  INDEX `fk_vistoria_tecnico2_idx` (`tecnico2` ASC),
  CONSTRAINT `fk_estabelecimento_has_inspecao_estabelecimento1`
    FOREIGN KEY (`estabelecimento`)
    REFERENCES `acaipde`.`estabelecimento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vistoria_tecnico1`
    FOREIGN KEY (`tecnico1`)
    REFERENCES `acaipde`.`tecnico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vistoria_tecnico2`
    FOREIGN KEY (`tecnico2`)
    REFERENCES `acaipde`.`tecnico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `acaipde`.`inspecao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `acaipde`.`inspecao` ;

CREATE TABLE IF NOT EXISTS `acaipde`.`inspecao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vistoria` INT NOT NULL,
  `equipamento` INT NOT NULL,
  `dataInsp` DATE NOT NULL,
  `apto` TINYINT(1) NOT NULL DEFAULT 0,
  `observacao` TEXT(300) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_inspecao_equipamento1_idx` (`equipamento` ASC),
  INDEX `fk_inspecao_vistoria1_idx` (`vistoria` ASC),
  CONSTRAINT `fk_inspecao_equipamento1`
    FOREIGN KEY (`equipamento`)
    REFERENCES `acaipde`.`equipamento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inspecao_vistoria1`
    FOREIGN KEY (`vistoria`)
    REFERENCES `acaipde`.`vistoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
