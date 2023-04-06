
-- DROP DATABASE IF EXISTS biblio;

CREATE DATABASE biblio DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE biblio.auteur (
 id        INT( 11 )     NOT NULL AUTO_INCREMENT ,
 nom       VARCHAR( 20 ) NOT NULL ,
 prenom    VARCHAR( 20 ) ,
 telephone VARCHAR( 10 ) NOT NULL,
 email     VARCHAR( 60 ) ,
 PRIMARY KEY ( id )
) ENGINE = INNODB;

CREATE TABLE biblio.users (
 id        INT( 11 )     NOT NULL AUTO_INCREMENT ,
 username  VARCHAR( 20 ) NOT NULL ,
 password  VARCHAR( 20 )
) ENGINE = INNODB;

CREATE TABLE biblio.livre (
 id         INT( 11 )     NOT NULL AUTO_INCREMENT ,
 id_auteur  INT( 11 ) ,
 titre      VARCHAR( 50 ) NOT NULL ,
 nb_pages   INT( 11 )     NOT NULL ,
 categorie  VARCHAR( 20 ),
 PRIMARY KEY ( id ) ,
 CONSTRAINT fk_id_auteur    
    FOREIGN KEY (id_auteur)  
    REFERENCES biblio.auteur(id)  
    ON DELETE SET NULL    
) ENGINE = INNODB;


INSERT INTO biblio.auteur (id,prenom,nom,telephone,email) VALUES
(1, 'Bruce', 'Eckel','0605040302', 'thinking@me.net'),
(2, 'Antonio', 'Goncalves', '0102030405', null),
(3, 'Petter', 'Haggar', '0655443322', 'petharg@hotmail.com'),
(4, 'Claude', 'Delannoy', '0677889900', 'claude@delanooy.com');

INSERT INTO biblio.livre (id, id_auteur, titre, nb_pages, categorie) VALUES
(1, 1, 'Thinking in Java', 320, 'java'),
(2, 1, 'Thinking in C++', 640, 'cpp'),
(3, 2, 'Les cahiers du programmeur Java EE', '240', 'java'),
(4, 2, 'Beginning Java EE 7', 120, 'javaee'),
(5, 3, 'Mieux programmer en Java', 540, 'java'),
(6, 4, 'Exercices en Java', 184, 'java'),
(7, 4, 'Initiation à la programmation', 350, 'algo'),
(8, 4, 'C++ Guide complet', 842, 'cpp');

-- -----------------------------------------------------------------------------------------
USE biblio;
SHOW TABLES;
SELECT A.prenom, A.nom, L.titre, L.categorie
  FROM livre L
  LEFT JOIN auteur A ON A.id=L.id_auteur
  ORDER BY A.nom;

