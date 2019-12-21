delete from seances;
delete from formateurs;
delete from formations;

INSERT INTO formateurs (id, nom, prenom,  adresse_email) VALUES
(1001, 'ZAROUAL', 'MOHAMED',  'my-email@gmail.com'),
(1002, 'Lindouv', 'Bernard', 'my-email@gmail.com'),
(1003, 'Standers', 'Clement',  'my-email@gmail.com'),
(1004, 'Stevens', 'Ferdinand',  'my-email@gmail.com'),
(1005, 'Dean', 'Matthew',  'my-email@gmail.com'),
(1006, 'Holland', 'Belinda',  'my-email@gmail.com'),
(1007, 'Branch', 'Brooke',  'my-email@gmail.com'),
(1008, 'Patterson', 'Santiago',  'my-email@gmail.com'),
(1009, 'Harrington', 'Chaim',  'my-email@gmail.com'),
(1010, 'Leonard', 'Sidney', 'my-email@gmail.com'),
(1011, 'Shepard', 'Darian',  'my-email@gmail.com'),
(1012, 'Logan', 'Griffin',  'my-email@gmail.com'),
(1013, 'Barnett', 'Devan',  'my-email@gmail.com'),
(1014, 'Fry', 'Elaina',  'my-email@gmail.com'),
(1015, 'Swanson', 'Gia',  'my-email@gmail.com'),
(1016, 'Gardner', 'Elliot',  'my-email@gmail.com');

INSERT INTO formations (code, libelle, descriptif) VALUES
('HIBERNAT01', 'Hibernate : les secrets sont dévoilés', 'Apprendre comment fonctionne Hibernate et maitriser le développement de la couche de persistance '),
('SPRINGBASE01', 'Spring framework : Etape par étape pour devenir professionel', 'Apprendre comment fonctionne Spring Framework (ioc, jdbc, test, jpa, aop, tx) pour passer au niveau professionnel '),
('SPRINGBATCH01', 'Spring Batch par la pratique', 'Apprendre comment fonctionne Spring batch et développer vos batches avec le minimum d''effort'),
('SPRINGBOOT01', 'Spring Boot par la pratique', 'Apprendre comment fonctionne Spring boot et développer rapidement vos applications microservices ');

INSERT INTO seances (code_formation, id_formateur, date_debut, date_fin) VALUES
('SPRINGBASE01', 1001, '2019-01-09', '2019-01-11'),
('SPRINGBASE01', 1002, '2019-02-09', '2019-02-11'),
('SPRINGBASE01', 1003, '2019-11-09', '2019-11-11'),
('SPRINGBASE01', 1004, '2019-12-09', '2019-12-11');