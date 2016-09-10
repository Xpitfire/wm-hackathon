USE dbo;

/* counrtyid, country */
INSERT INTO `dbo`.`Team` VALUES 
	('AUT', 'Österreich'), ('GER', 'Deutschland'),
	('ALB', 'Albanien'), ('BHS', 'Bahamas'), 
	('CHN', 'China'), ('FIN', 'Finnland'),
	('FRA', 'Frankreich'), ('GRC', 'Griechenland'),
	('HKG', 'Hongkong'), ('IND', 'Indien'), 
	('JPN', 'Japan'), ('CAN', 'Canada'),
	('NOR', 'Norwegen'), ('NER', 'Niger'),
	('PAK', 'Pakistan'), ('POL', 'Polen'), 
	('SWE', 'Schweden'), ('SGP', 'Singapur'),
	('ESP', 'Spanien'), ('THA', 'Thailand'),
	('CZE', 'Tschechien'), ('TUN', 'Tunesien'),
	('UKR', 'Urkaine'), ('VNM', 'Vietnam'),
	('AND', 'Andora'), ('BEL', 'Belgien'), 
	('BOL', 'Bolivien'), ('DMA', 'Dominica'),
	('DNK', 'Dänemark'), ('ECU', 'Equador'),
	('EST', 'Estland'), ('ISR', 'Israel');

/* username, passwordhashvalue, firstname, lastname, isactive */
INSERT INTO `dbo`.`User` VALUES 
	('pony', '[B@64729b1e', 'Franz', 'Mustermann', 0),
	('matrix', 'asdf', 'Susi', 'Musterfrau', 0),
	('lili', 'hfhfhfhf', 'Lisa', 'Berger', 0),
	('tobi', 'erewr', 'Tobias', 'Mayer', 0);

/* id, team1, team2, place, date, goal1, goal2, wmstate */
INSERT INTO `dbo`.`Game` VALUES 
	(1000, 'ISR', 'POL', 'AUT', '2016-08-13T12:00:00Z', 5, 4, 1),
	(1001, 'GRC', 'POL', 'AUT', '2016-08-14T12:00:00Z', 2, 2, 2),
	(1002, 'VNM', 'ISR', 'AUT', '2016-08-15T12:00:00Z', 3, 1, 2),
	(1003, 'BHS', 'GRC', 'AUT', '2016-08-16T12:00:00Z', 1, 1, 3),
	(1004, 'CAN', 'POL', 'AUT', '2016-08-17T12:00:00Z', 1, 2, 3),
	(1005, 'THA', 'VNM', 'AUT', '2016-08-18T12:00:00Z', 1, 3, 3),
	(1006, 'DMA', 'ISR', 'AUT', '2016-08-19T12:00:00Z', 2, 2, 3),
	(1007, 'BHS', 'GER', 'AUT', '2016-08-19T12:00:00Z', 1, 3, 4),
	(1008, 'FIN', 'GRC', 'AUT', '2016-08-20T12:00:00Z', 2, 2, 4),
	(1009, 'IND', 'CAN', 'AUT', '2016-08-21T12:00:00Z', 1, 1, 4),
	(1010, 'NER', 'POL', 'AUT', '2016-08-22T12:00:00Z', 2, 3, 4),
	(1011, 'SGP', 'THA', 'AUT', '2016-08-23T12:00:00Z', 4, 5, 4),
	(1012, 'TUN', 'VNM', 'AUT', '2016-08-24T12:00:00Z', 2, 2, 4),
	(1013, 'BEL', 'DMA', 'AUT', '2016-08-25T12:00:00Z', 1, 3, 4),
	(1014, 'ECU', 'ISR', 'AUT', '2016-08-26T12:00:00Z', 1, 3, 4);

/* id, username, idfromgame, goal1, goal2, wmwinner, secondplace, goalwinner, goalloserteam, mostgameswonteam, mostgameslostteam */
/*INSERT INTO `dbo`.`Tip` VALUES 
	(1000, 'pony', 1000, 2, 0, 'AUT', 'GER', 'AUT', 'GER', 'AUT', 'GER'),
	(1001, 'matrix', 1001, 2, 2, 'IST', 'AUT', 'POL', 'GER', 'GRC', 'BHS'),
	(1002, 'matrix', 1002, 3, 3, 'VNM', 'ISR', 'AUT', 'GER', 'DMA', 'THA'),
	(1003, 'lili', 1006, 2, 2, 'DMA', 'ISR', 'THA', 'GRC', 'DMA', 'DNK');

