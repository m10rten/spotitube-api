-- create && set.
--CREATE DATABASE spotitube_api_db
--USE spotitube_api_db

--CREATE DATABASE spotitube_db
--USE spotitube_db
DROP TABLE IF EXISTS User_Playlists;
DROP TABLE IF EXISTS Playlist_Tracks;
DROP TABLE IF EXISTS Playlists;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Tracks;

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
	PlaylistOwnerId int FOREIGN KEY REFERENCES Users(UserId),
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
	TrackPlayCount int,
	PRIMARY KEY (TrackId),
);

DROP TABLE IF EXISTS User_Playlists;
CREATE TABLE User_Playlists (
	UserId int FOREIGN KEY REFERENCES Users(UserId) ON DELETE CASCADE,
	PlaylistId int FOREIGN KEY REFERENCES Playlists(PlaylistId) ON DELETE CASCADE,	
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
					TrackPublicationDate, TrackOfflineAvailable, TrackPlayCount) 
	VALUES ('Look at Me!', 'Revenge', 127, 
			'Look at my Wrist, about ten Aye',
			'XXXTENTACION', '18-6-2018', 1, 38);

INSERT INTO Tracks (TrackTitle, TrackAlbum, TrackDuration, TrackDescription, TrackPerformer, 
					TrackPublicationDate, TrackOfflineAvailable, TrackPlayCount) 
	VALUES ('Hope', '?', 110, 
			'Hope by xxxtentacion',
			'XXXTENTACION', '18-6-2018', 0, 45);

INSERT INTO Tracks (TrackTitle, TrackAlbum, TrackDuration, TrackDescription, TrackPerformer, 
					TrackPublicationDate, TrackOfflineAvailable, TrackPlayCount) 
	VALUES ('noted', '<;>', 134, 
			'notation required before',
			'running jewels', '18-6-2018', 1, 1);

INSERT INTO Tracks (TrackTitle, TrackAlbum, TrackDuration, TrackDescription, TrackPerformer, 
					TrackPublicationDate, TrackOfflineAvailable, TrackPlayCount)
VALUES ('beethoven 5e', 'sonata', 610, 
		'classical piano playlist',
		'Ludwig Von Beethoven', '18-6-2018', 0, 4);
			   		
INSERT INTO Tracks (TrackTitle, TrackAlbum, TrackDuration, TrackDescription, TrackPerformer, 
					TrackPublicationDate, TrackOfflineAvailable, TrackPlayCount)
VALUES ('beethoven 2e', 'sonata', 140, 
		'classical piano playlist',
		'Ludwig Von Beethoven', '18-6-2018', 0, 3);

INSERT INTO Playlists (PlaylistLength, PlaylistName, PlaylistOwnerId)
	VALUES (237, 'Rap songs', 2);

INSERT INTO Playlists (PlaylistLength, PlaylistName, PlaylistOwnerId)
	VALUES (1430, 'classical piano', 2);

	--DELETE FROM Playlists WHERE PlaylistId = 2;

-- INSERT INTO Users (UserName, UserFull, UserPassword, UserToken) VALUES ('meron','Meron Brouwer','4a76c706eeb50a6ed472923fd39eb33332a37e8b676f68f891d60cee7e9800b3', '1234-1523-6665-1298')

INSERT INTO Playlist_Tracks (PlaylistId, TrackId)
	VALUES (1, 1);
INSERT INTO Playlist_Tracks (PlaylistId, TrackId)
	VALUES (1, 2);

INSERT INTO Playlist_Tracks (PlaylistId, TrackId)
	VALUES (2, 4);
INSERT INTO Playlist_Tracks (PlaylistId, TrackId)
	VALUES (2, 5);

INSERT INTO User_Playlists (UserId, PlaylistId)
	VALUES (2, 1);

INSERT INTO User_Playlists (UserId, PlaylistId)
	VALUES (1, 1);
INSERT INTO User_Playlists (UserId, PlaylistId)
	VALUES (2, 2);


	--UPDATE Users SET UserToken = '517d5317-be5f-4f30-a2e4-2cf76968035f' OUTPUT INSERTED.UserToken WHERE UserName = 'morty'
SELECT * FROM Users WHERE UserToken = 'dfd90692-4c68-440b-b867-888d08177363';
SELECT * FROM User_Playlists;
SELECT * FROM Tracks;
SELECT * FROM Playlists;
SELECT * FROM Playlist_Tracks;

SELECT SUM(TrackDuration) AS Duration FROM Users 
	INNER JOIN User_Playlists ON Users.UserId = User_Playlists.UserId 
	INNER JOIN Playlists ON User_Playlists.PlaylistId = Playlists.PlaylistId
	INNER JOIN Playlist_Tracks ON Playlists.PlaylistId = Playlist_Tracks.PlaylistId
	INNER JOIN Tracks ON Playlist_Tracks.TrackId = Tracks.TrackId
	WHERE Users.UserId = 2;


	SELECT * FROM User_Playlists
		INNER JOIN Playlists ON User_Playlists.PlaylistId = Playlists.PlaylistId
		WHERE User_Playlists.UserId = 2;
SELECT * FROM Playlist_Tracks 
	INNER JOIN Tracks ON Playlist_Tracks.TrackId = Tracks.TrackId
	WHERE Playlist_Tracks.PlaylistId = 1
-- mortypassword
-- MySuperSecretPassword12341