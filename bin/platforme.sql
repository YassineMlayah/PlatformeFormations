-- Creation de base de donnees

-- DROP DATABASE platforme;
CREATE DATABASE IF NOT EXISTS platforme;
USE platforme;

-- Creation du tableau d'utilisateurs avec role (Etudiant ou Formateur)
CREATE TABLE utilisateur (
	-- L'id_utilisateur est le cle primaire de la table utilisateur (C'est un entier unique non null generee automatiquement)
	id_utilisateur INT NOT NULL AUTO_INCREMENT,
	nom VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    motDePasse VARCHAR(50) NOT NULL,
    role ENUM("Etudiant", "Formateur") NOT NULL,
    PRIMARY KEY (id_utilisateur)
);

-- Creation du tableau de formations
CREATE TABLE formation (
	-- id_formation est le cle primaire de la table formation (C'est un entier unique non null generee automatiquement)
	id_formation INT NOT NULL AUTO_INCREMENT,
	titre VARCHAR(50) NOT NULL,
    description VARCHAR(250),
    -- formateur est un cle etrangere du tableau utlisateur (id_utilisateur)
    formateur INT NOT NULL,  
    prix DOUBLE NOT NULL,
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

-- Remplir la table utilisateur
INSERT INTO utilisateur (nom, email, motDePasse, role) VALUES
('yassine', 'yassine@gmail.com', 'yassine', 'Formateur'),
('ahmed', 'ahmed@gmail.com', 'ahmed', 'Formateur'),
('ali', 'ali@gmail.com', 'ali', 'Formateur'),
('salah', 'salah@gmail.com', 'salah', 'Etudiant'),
('imen', 'imen@gmail.com', 'imen', 'Etudiant'),
('noura', 'noura@gmail.com', 'noura', 'Etudiant');


-- Remplir la table formation
-- id_formation >= 100
ALTER TABLE formation AUTO_INCREMENT = 100;
INSERT INTO formation (titre, description, formateur, prix) VALUES
('Introduction to Programming', 'Learn the basics of programming with Python.', 3, 100),
('Advanced Java Programming', 'Deep dive into Java with advanced concepts and frameworks.', 3, 150),
('Data Science Essentials', 'An introduction to data science and machine learning.', 1, 200),
('Web Development Bootcamp', 'Learn to build modern web applications with HTML, CSS, and JavaScript.', 2, 120),
('Cybersecurity Fundamentals', 'Learn how to protect systems from cyber threats and vulnerabilities.', 2, 180);


-- Remplir la table inscription
INSERT INTO inscription (id_utilisateur, id_formation) VALUES
(4, 100),
(4, 103),
(5, 103),
(5, 104),
(6, 102);
