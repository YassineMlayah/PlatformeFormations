-- Creation de base de donnees
CREATE DATABASE IF NOT EXISTS platforme;
USE platforme;

-- Creation du tableau d'utilisateurs avec role (Etudiant ou Formateur)
CREATE TABLE utilisateur (
	-- L'id_utilisateur est le cle primaire de la table utilisateur (C'est un entier unique non null generee automatiquement)
	id_utilisateur INT NOT NULL AUTO_INCREMENT,
	nom VARCHAR(50),
    email VARCHAR(50),
    motDePasse VARCHAR(50),
    role ENUM("Etudiant", "Formateur"),
    PRIMARY KEY (id_utilisateur)
);

-- Creation du tableau de formations
CREATE TABLE formation (
	-- id_formation est le cle primaire de la table formation (C'est un entier unique non null generee automatiquement)
	id_formation INT NOT NULL AUTO_INCREMENT,
	titre VARCHAR(50),
    description VARCHAR(255),
    -- formateur est un cle etrangere du tableau utlisateur (id_utilisateur)
    formateur INT,  
    prix DOUBLE,
    PRIMARY KEY (id_formation),
	FOREIGN KEY (formateur) REFERENCES utilisateur(id_utilisateur)
);

-- Creation du tableau inscription
CREATE TABLE inscription (
	-- id_utilisateur est un cle etrangere du tableau utlisateur (id_utilisateur)
	id_utilisateur INT,
    -- id_formation est un cle etrangere du tableau formation (id_formation)
    id_formation INT,
    FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id_utilisateur),
    FOREIGN KEY (id_formation) REFERENCES formation(id_formation)
);