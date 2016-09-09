USE dbo;

INSERT INTO `dbo`.`Team` VALUES 
	('AUT', 'Österreich'), ('GER', 'Deutschland');

INSERT INTO `dbo`.`User` VALUES 
	('pony', '[B@64729b1e', 'Franz', 'Mustermann', 0);

INSERT INTO `dbo`.`Game` VALUES 
	(1000, 'AUT', 'GER', 'AUT', '2016-08-12T12:00:00Z', 2, 0, 1);

INSERT INTO `dbo`.`Tip` VALUES 
	(1000, 'pony', 1000, 2, 0, 'AUT', 'GER', 'AUT', 'GER', 'AUT', 'GER');