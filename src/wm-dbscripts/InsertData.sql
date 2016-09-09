USE dbo;

/* counrtyid, country */
INSERT INTO `dbo`.`Team` VALUES 
	('AUT', 'Österreich'), ('GER', 'Deutschland'),
	('ALB', 'Albanien'), ('BHS', 'Bahamas'), 
	('CHN' 'China'), ('FIN', 'Finnland'),
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
	('EST', 'Estland'), ('ECU', 'Ecuador');

/* username, passwordhashvalue, firstname, lastname, isactive */
INSERT INTO `dbo`.`User` VALUES 
	('pony', '[B@64729b1e', 'Franz', 'Mustermann', 0),
	('matrix', 'asdf', 'Susi', 'Musterfrau', 0),
	('lili', 'hfhfhfhf', 'Lisa', 'Berger', 0),
	('tobi', 'erewr', 'Tobias', 'Mayer', 0);

/* id, team1, team2, place, date, goal1, goal2, wmstate */
INSERT INTO `dbo`.`Game` VALUES 
	(1000, 'AUT', 'GER', 'AUT', '2016-08-12T12:00:00Z', 2, 0, 1);

/* id, username, idfromgame, goal1, goal2, wmwinner, secondplace, goalwinner, goalloserteam, mostgameswonteam, mostgameslostteam */
INSERT INTO `dbo`.`Tip` VALUES 
	(1000, 'pony', 1000, 2, 0, 'AUT', 'GER', 'AUT', 'GER', 'AUT', 'GER');
