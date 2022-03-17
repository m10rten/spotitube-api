-- create && set.
CREATE DATABASE spotitube_api_db
USE spotitube_api_db

--CREATE DATABASE spotitube_db
--USE spotitube_db
DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
	UserId int IDENTITY(1,1),
	UserName varchar(64),
	UserFull varchar(128),
	UserPassword varchar(max),
	UserToken varchar(256),
	PRIMARY KEY (UserId),
);

DROP TABLE IF EXISTS Playlists;
CREATE TABLE Playlists (
	PlaylistId int IDENTITY(1, 1),
	PlaylistLength int,
	PlaylistName varchar(256),
	PlaylistOwnderId int FOREIGN KEY REFERENCES Users(UserId),
	PRIMARY KEY (PlaylistId),
);

DROP TABLE IF EXISTS Tracks;
CREATE TABLE Tracks (
	TrackId int IDENTITY(1, 1),
	TrackTitle varchar(256),
	TrackAlbum varchar(128),
	TrackDuration int, 
	TrackDescription varchar(512),
	TrackPerformer varchar(128),
	TrackPublicationDate varchar(64),
	TrackOfflineAvailable int,
	PRIMARY KEY (TrackId),
);

DROP TABLE IF EXISTS User_Playlists;
CREATE TABLE User_Playlists (
	UserId int FOREIGN KEY REFERENCES Users(UserId),
	PlaylistId int FOREIGN KEY REFERENCES Playlists(PlaylistId),	
);

DROP TABLE IF EXISTS Playlist_Tracks;
CREATE TABLE Playlist_Tracks (
	PlaylistId int FOREIGN KEY REFERENCES Playlists(PlaylistId) ON DELETE CASCADE,
	TrackId int FOREIGN KEY REFERENCES Tracks(TrackId) ON DELETE CASCADE,
	PRIMARY KEY (PlaylistId, TrackId)
);

-- mock data
INSERT INTO Users (UserName, UserFull, UserPassword, UserToken) 
	VALUES ('meron','Meron Brouwer','4a76c706eeb50a6ed472923fd39eb33332a37e8b676f68f891d60cee7e9800b3', 
			'1234-1523-6665-1298')

INSERT INTO Users (UserName, UserFull, UserPassword, UserToken)
	VALUES ('Morty','Morty','3762441e8688f8693e82ef7b7da8e329eb47ce70b14eaa9805c3daa4933e45ae', 
			'1234-1523-6665-1234')

INSERT INTO Tracks (TrackTitle, TrackAlbum, TrackDuration, TrackDescription, TrackPerformer, 
					TrackPublicationDate, TrackOfflineAvailable) 
	VALUES ('Look at Me!', 'Revenge', 127, 
			'Look at my Wrist, about ten Aye',
			'XXXTENTACION', '18-6-2018', 1);

INSERT INTO Tracks (TrackTitle, TrackAlbum, TrackDuration, TrackDescription, TrackPerformer, 
					TrackPublicationDate, TrackOfflineAvailable) 
	VALUES ('Hope', '?', 110, 
			'Hope by xxxtentacion',
			'XXXTENTACION', '18-6-2018', 0);

INSERT INTO Playlists (PlaylistLength, PlaylistName, PlaylistOwnderId)
	VALUES (237, 'Rap songs', 2);
-- INSERT INTO Users (UserName, UserFull, UserPassword, UserToken) VALUES ('meron','Meron Brouwer','4a76c706eeb50a6ed472923fd39eb33332a37e8b676f68f891d60cee7e9800b3', '1234-1523-6665-1298')

INSERT INTO Playlist_Tracks (PlaylistId, TrackId)
	VALUES (1, 1);
INSERT INTO Playlist_Tracks (PlaylistId, TrackId)
	VALUES (1, 2);

INSERT INTO User_Playlists (UserId, PlaylistId)
	VALUES (2, 1);

SELECT * FROM Users;
SELECT * FROM User_Playlists;
SELECT * FROM Tracks;
SELECT * FROM Playlists;
SELECT * FROM Tracks;
SELECT * FROM Playlist_Tracks;

SELECT * FROM Users 
	INNER JOIN User_Playlists ON Users.UserId = User_Playlists.UserId 
	INNER JOIN Playlists ON User_Playlists.PlaylistId = Playlists.PlaylistId
	INNER JOIN Playlist_Tracks ON Playlists.PlaylistId = Playlist_Tracks.PlaylistId
	INNER JOIN Tracks ON Playlist_Tracks.TrackId = Tracks.TrackId
	WHERE Users.UserId = 2;
-- mortypassword
-- MySuperSecretPassword12341