IF EXISTS(SELECT 1 FROM sys.tables WHERE object_id = OBJECT_ID('Game'))
BEGIN;
	DROP TABLE [dbo].[Game];
END;
GO

IF EXISTS(SELECT 1 FROM sys.tables WHERE object_id = OBJECT_ID('Team'))
BEGIN;
	DROP TABLE [dbo].[Team];
END;
GO

IF EXISTS(SELECT 1 FROM sys.tables WHERE object_id = OBJECT_ID('Tip'))
BEGIN;
	DROP TABLE [dbo].[Tip];
END;
GO

IF EXISTS(SELECT 1 FROM sys.tables WHERE object_id = OBJECT_ID('User'))
BEGIN;
	DROP TABLE [dbo].[User];
END;
GO

CREATE TABLE [dbo].[Game] (
	[id]   		INT         NOT NULL,
	[team1Id]  	VARCHAR (3)	NOT NULL,
	[team2Id] 	VARCHAR (3)	NOT NULL,
	[place] 	VARCHAR (3)	NOT NULL,
	['date']  	DATETIME    NOT NULL,
	[goal1] 	INT			NOT NULL,
	[goal2]   	INT			NOT NULL,
	[wmstate]	INT			NOT NULL,
	PRIMARY KEY CLUSTERED ([id] ASC)
);
GO

CREATE TABLE [dbo].[Team] (
	[country]	VARCHAR (3)		NOT NULL,
	[name]		VARCHAR (30)	NOT NULL,
	PRIMARY KEY CLUSTERED ([id] ASC)
);
GO

CREATE TABLE [dbo].[Tip] (
	[id]					INT			NOT NULL,
	[userId]				VARCHAR (3)	NOT NULL,
	[gameId]				INT			NOT NULL,
	[tipGoalTeam1]			INT			NOT NULL,
	[tipGoalTeam2]			INT			NOT NULL,
	[tipWmWinnerTeamId]		VARCHAR (3)	NOT NULL,
	[tipGoalWinnerTeamId]	VARCHAR (3)	NOT NULL,
	[tipGoalLoserTeamId]	VARCHAR (3)	NOT NULL,
	[tipSecondPlaceTeamId]	VARCHAR (3)	NOT NULL,
	[tipWmStateLoserTeamId]	VARCHAR (3)	NOT NULL,
	[tipZeroGoalsTeamId]	VARCHAR (3)	NOT NULL,
	PRIMARY KEY CLUSTERED ([id] ASC)
);
GO

CREATE TABLE [dbo].[User] (
	[username]		VARCHAR (30)	NOT NULL,	
	[passwordHash]	VARCHAR (60)	NOT NULL,
	[firstName]		VARCHAR (30)	NOT NULL,
	[lastName]		VARCHAR (30)	NOT NULL,
	PRIMARY KEY CLUSTERED ([id] ASC)
);
GO
























