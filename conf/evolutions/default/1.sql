# --- !Ups

USE `blog`;

DROP TABLE IF EXISTS articles;

CREATE  TABLE `articles` (
  `article_id` INT NOT NULL AUTO_INCREMENT ,
  `caption` VARCHAR(45) NOT NULL ,
  `content` TEXT NOT NULL ,
  PRIMARY KEY (`article_id`) )
ENGINE = InnoDB;

# --- !Downs
