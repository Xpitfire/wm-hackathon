CREATE DATABASE IF NOT EXISTS dbo;
USE dbo;

DROP TABLE IF EXISTS `dbo`.`Tip`;
DROP TABLE IF EXISTS `dbo`.`Game`;
DROP TABLE IF EXISTS `dbo`.`User`;
DROP TABLE IF EXISTS `dbo`.`Team`;

CREATE TABLE `dbo`.`Team` (
	`country`	VARCHAR (3)		NOT NULL,
	`name`		VARCHAR (30)	NOT NULL,
	PRIMARY KEY (`country`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `dbo`.`User` (
	`username`		VARCHAR (30)	NOT NULL,	
	`passwordHash`	VARCHAR (60)	NOT NULL,
	`firstName`		VARCHAR (30)	NOT NULL,
	`lastName`		VARCHAR (30)	NOT NULL,
	`isActive`      TINYINT         NOT NULL,
	PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `dbo`.`Game` (
	`id`   		INT         NOT NULL AUTO_INCREMENT,
	`team1Id`  	VARCHAR (3)	NOT NULL,
	`team2Id` 	VARCHAR (3)	NOT NULL,
	`place` 	VARCHAR (3)	NOT NULL,
	`date`  	DATETIME    NOT NULL,
	`goal1` 	INT			NOT NULL,
	`goal2`   	INT			NOT NULL,
	`wmstate`	INT			NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `conteam1Id` 
		FOREIGN KEY (`team1Id`) 
		REFERENCES `dbo`.`Team` (`country`),
	CONSTRAINT `conteam2Id` 
		FOREIGN KEY (`team2Id`) 
		REFERENCES `dbo`.`Team` (`country`) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `dbo`.`Tip` (
	`id`					INT			NOT NULL AUTO_INCREMENT,
	`userId`				VARCHAR (30)	NOT NULL,
	`gameId`				INT			NOT NULL,
	`tipGoalTeam1`			INT			NOT NULL,
	`tipGoalTeam2`			INT			NOT NULL,
	`tipWmWinnerTeamId`		VARCHAR (3)	NOT NULL,
	`tipSecondPlaceTeamId` 	VARCHAR (3) NOT NULL,
	`tipGoalWinnerTeamId`	VARCHAR (3)	NOT NULL,
	`tipGoalLoserTeamId`	VARCHAR (3)	NOT NULL,
	`tipMostGamesWonTeamId`	VARCHAR (3)	NOT NULL,
	`tipMostGamesLostTeamId`VARCHAR (3)	NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `conuserId`
		FOREIGN KEY (`userId`) 
		REFERENCES `dbo`.`User` (`username`),
	CONSTRAINT `congameId`
		FOREIGN KEY (`gameId`) 
		REFERENCES `dbo`.`Game` (`id`),
	CONSTRAINT `contipWmWinnerTeamId` 
		FOREIGN KEY (`tipWmWinnerTeamId`) 
		REFERENCES `dbo`.`Team` (`country`),
	CONSTRAINT `contipSecondPlaceTeamId` 
		FOREIGN KEY (`tipSecondPlaceTeamId`) 
		REFERENCES `dbo`.`Team` (`country`), 
	CONSTRAINT `contipGoalWinnerTeamId` 
		FOREIGN KEY (`tipGoalWinnerTeamId`) 
		REFERENCES `dbo`.`Team` (`country`),
	CONSTRAINT `contipGoalLoserTeamId` 
		FOREIGN KEY (`tipGoalLoserTeamId`) 
		REFERENCES `dbo`.`Team` (`country`),
	CONSTRAINT `contipMostGamesWonTeamId` 
		FOREIGN KEY (`tipMostGamesWonTeamId`) 
		REFERENCES `dbo`.`Team` (`country`), 
	CONSTRAINT `contipMostGamesLostTeamId` 
		FOREIGN KEY (`tipMostGamesLostTeamId`) 
		REFERENCES `dbo`.`Team` (`country`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
























