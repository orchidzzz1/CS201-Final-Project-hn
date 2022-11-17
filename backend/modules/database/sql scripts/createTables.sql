CREATE TABLE `201projectdb`.`users` (
  `userId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `userId_UNIQUE` (`userId` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

CREATE TABLE `201projectdb`.`preferencetypes` (
  `preferenceId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `preferenceName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`preferenceId`),
  UNIQUE INDEX `preferenceId_UNIQUE` (`preferenceId` ASC) VISIBLE);

CREATE TABLE `201projectdb`.`events` (
  `eventId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `eventTitle` VARCHAR(45) NULL,
  `eventDescription` VARCHAR(768) NULL,
  `userId` VARCHAR(45) NOT NULL,
  `postedDateTime` DATETIME NULL DEFAULT getdate(),
  `eventDateTime` DATETIME NOT NULL,
  `expired` TINYINT NOT NULL,
  `preferenceId` INT NULL,
  `eventLocation` VARCHAR(45) NULL,
  PRIMARY KEY (`eventId`),
  UNIQUE INDEX `preferenceId_UNIQUE` (`eventId` ASC) VISIBLE);

CREATE TABLE `201projectdb`.`rsvp` (
  `userId` INT NOT NULL,
  `eventId` INT NOT NULL,
  `reminded` TINYINT NOT NULL,
  PRIMARY KEY (`userId`, `eventId`));

CREATE TABLE `201projectdb`.`preferences` (
  `userId` INT NOT NULL,
  `preferenceId` INT NOT NULL,
  `alert` TINYINT NOT NULL,
  PRIMARY KEY (`userId`, 'preferenceId'));
