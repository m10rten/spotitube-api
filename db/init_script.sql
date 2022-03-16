-- create && set.
CREATE DATABASE spotitube_api_db
USE spotitube_api_db

CREATE DATABASE spotitube_db
USE spotitube_db
DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
	UserId int IDENTITY(1,1),
	UserName varchar(64),
	UserFull varchar(128),
	UserPassword varchar(max),
	UserToken varchar(256),
	PRIMARY KEY (UserId),
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
	PRIMARY KEY (PlaylistId)
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

INSERT INTO Users (UserName, UserFull, UserPassword, UserToken) VALUES ('meron','Meron Brouwer','4a76c706eeb50a6ed472923fd39eb33332a37e8b676f68f891d60cee7e9800b3', '1234-1523-6665-1298')
-- INSERT INTO Users (UserName, UserFull, UserPassword, UserToken) VALUES ('meron','Meron Brouwer','4a76c706eeb50a6ed472923fd39eb33332a37e8b676f68f891d60cee7e9800b3', '1234-1523-6665-1298')

SELECT * FROM Users;
-- MySuperSecretPassword12341