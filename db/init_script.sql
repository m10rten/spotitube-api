-- create && set.
CREATE DATABASE spotitube_api_db
USE spotitube_api_db
CREATE TABLE Users (
	UserName varchar(64),
	UserPassword varchar(max),
	UserToken varchar(256)
);
INSERT INTO Users (UserName, UserPassword) VALUES ('meron','MySuperSecretPassword12341')
SELECT * FROM Users;