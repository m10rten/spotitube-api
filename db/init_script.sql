-- create && set.
CREATE DATABASE spotitube_api_db
USE spotitube_api_db
DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
	UserName varchar(64),
	UserFull varchar(128),
	UserPassword varchar(max),
	UserToken varchar(256)
);
INSERT INTO Users (UserName, UserFull, UserPassword, UserToken) VALUES ('meron','Meron Brouwer','4a76c706eeb50a6ed472923fd39eb33332a37e8b676f68f891d60cee7e9800b3', '1234-1523-6665-1298')
-- INSERT INTO Users (UserName, UserFull, UserPassword, UserToken) VALUES ('meron','Meron Brouwer','4a76c706eeb50a6ed472923fd39eb33332a37e8b676f68f891d60cee7e9800b3', '1234-1523-6665-1298')

SELECT * FROM Users;
-- MySuperSecretPassword12341