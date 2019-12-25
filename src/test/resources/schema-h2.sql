DROP TABLE  SEANCES IF EXISTS;
DROP TABLE  FORMATEURS IF EXISTS;
DROP TABLE  FORMATIONS IF EXISTS;

CREATE TABLE FORMATEURS (
  id number(11) NOT NULL PRIMARY KEY,
  nom varchar(12) NOT NULL,
  prenom varchar(12) NOT NULL,
  adresse_email varchar(50) NOT NULL
) ;

CREATE TABLE FORMATIONS (
  code varchar(20) NOT NULL PRIMARY KEY,
  libelle varchar(200) NOT NULL,
  descriptif varchar(500) NOT NULL
) ;

CREATE TABLE SEANCES (
  code_formation varchar(20) NOT NULL,
  id_formateur int(11) NOT NULL,
  date_debut date NOT NULL,
  date_fin date NOT NULL,
  CONSTRAINT FK_FORMATEURS FOREIGN KEY (id_formateur) REFERENCES FORMATEURS (id),
  CONSTRAINT FK_FORMATIONS FOREIGN KEY (code_formation) REFERENCES FORMATIONS (code)
  
) ;
