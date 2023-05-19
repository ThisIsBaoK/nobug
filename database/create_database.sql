CREATE DATABASE nobug;

USE nobug;

CREATE TABLE Users (
	email VARCHAR(45) NOT NULL,
	password VARCHAR(45) NOT NULL,
	lastName VARCHAR(45) NOT NULL,
	firstNames VARCHAR(45) NOT NULL,
	PRIMARY KEY (email)
);

CREATE TABLE Tasks (
	id INT NOT NULL AUTO_INCREMENT,
	author VARCHAR(45) NOT NULL,
	assigned VARCHAR(45),
	title VARCHAR(255) NOT NULL,
	description VARCHAR(2000) NOT NULL,
	project INT NOT NULL,
	status ENUM('TODO', 'INPROGRESS', 'DONE') NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (author) REFERENCES Users(email),
	FOREIGN KEY (assigned) REFERENCES Users(email),
	FOREIGN KEY (project) REFERENCES Projects(id)
);

CREATE TABLE Projects (
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(255) NOT NULL,
	description VARCHAR(2000) NOT NULL,
	status ENUM('ARCHIVED', 'ACTIVE', 'MAINTENANCE') NOT NULL,
	PRIMARY KEY (id)
);