USE dbo;

INSERT `dbo`.`Team` INTO VALUES 
	('AUT', 'Österreich'), ('GER', 'Deutschland');

INSERT `dbo`.`User` INTO VALUES 
	('pony', '[B@64729b1e', 'Franz', 'Mustermann', 0);

INSERT `dbo`.`Game` INTO VALUES 
	('AUT', 'GER', 'AUT', '2016-08-12T12:00:00Z', 2, 0, 1);

INSERT `dbo`.`Tip` INTO VALUES 
	('pony', 0, 2, 0, 'AUT', 'AUT', 'GER', 'GER', 1, 'GER', 'GER');