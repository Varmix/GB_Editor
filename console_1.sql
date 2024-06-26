CREATE TABLE Author(
   matricule VARCHAR(6),
   prenom VARCHAR(255) NOT NULL,
   nom VARCHAR(255) NOT NULL,
   PRIMARY KEY(matricule)
);

CREATE TABLE Book(
   idBook INT AUTO_INCREMENT,
   titre VARCHAR(150) NOT NULL,
   isbn VARCHAR(14) NOT NULL,
   resume VARCHAR(500) NOT NULL,
   estPublie BOOLEAN NOT NULL,
   matricule VARCHAR(6) NOT NULL,
   PRIMARY KEY(idBook),
   UNIQUE(isbn),
   FOREIGN KEY(matricule) REFERENCES Author(matricule)
);

CREATE TABLE Page(
   idPage INT AUTO_INCREMENT,
   numPage INT NOT NULL,
   textPage VARCHAR(550) NOT NULL,
   idBook INT NOT NULL,
   PRIMARY KEY(idPage),
   FOREIGN KEY(idBook) REFERENCES Book(idBook)
);

CREATE TABLE Choice(
   idChoice INT AUTO_INCREMENT,
   texteAction VARCHAR(255) NOT NULL,
   numPageFrom INT NOT NULL,
   numPageTo INT NOT NULL,
   PRIMARY KEY(idChoice),
   FOREIGN KEY(numPageFrom) REFERENCES Page(idPage)
   on delete cascade,
   FOREIGN KEY(numPageTo) REFERENCES Page(idPage)
   on delete cascade
);

DROP TABLE Choice;
DROP TABLE Page;
DROP TABLE  Book;
DROP TABLE Author;

/* getAuthor() */
SELECT matricule as matricule, prenom as prenom, nom as nom
FROM Author
WHERE matricule = 210054;
/* INSERT BOOK */
INSERT INTO Book(titre, isbn, resume, estPublie, matricule)  VALUES ('123456789X1234567', '2-070038-01-4', 'aaaaa', false, 210054);
/* Basic INFO */
SELECT b.idBook as idBook, b.titre as titre, b.isbn as isbn, b.resume as resume, b.estPublie as estPublie
FROM Book b
JOIN Author A on A.matricule = b.matricule
WHERE A.matricule = 210054;

/* Complete INFO */
SELECT b.titre as titre, b.isbn as isbn, b.resume as resume, b.estPublie as estPublie
FROM Book b
WHERE b.idBook = 2;
/* INSERT PAGE*/
INSERT INTO Page(numPage, textPage, idBook) VALUES (1, 'Vous êtes plongés dans la lecture du journal', 2);
INSERT INTO Page(numPage, textPage, idBook) VALUES (2, 'Salut', 2);
/* Pages d'un livres */
SELECT p.idPage as idPage, p.numPage as numPage, p.textPage as textPage
FROM Page p
JOIN Book B on B.idBook = p.idBook
WHERE p.idBook = 2;

/* INSERT CHOICES */
INSERT INTO Choice(texteAction, numPageFrom, numPageTo) VALUES ('Je suis un choix en Page 1 : affiche moi page2 stp', 55, 56);
INSERT INTO Choice(texteAction, numPageFrom, numPageTo) VALUES ('Je suis un choix en Page 1 : affiche moi page3 stp', 55, 56);

select * from Page join Choice C on Page.idPage = C.numPageFrom;
select * from Choice;
/* Je connais ma page courante, je veux juste savoir ma page vers laquelle je vais */
SELECT c.texteAction as texteAction, c.numPageTo as numPageTo
FROM Choice c
WHERE c.numPageFrom = 46;
/* Aller chercher le NumPage sur base de l'id d'une page */
SELECT p.numPage as numPage
FROM Page p
WHERE p.idPage = 48;

DELETE FROM Page WHERE idBook = 2;



SELECT * from Book;
DELETE FROM Book Where idBook = 4;

SELECT * from Book
JOIN Page P on Book.idBook = P.idBook;

INSERT INTO `Page` (`idPage`, `numPage`, `textPage`, `idBook`) VALUES
(55, 1, 'Bonjour', 2),
(56, 2, 'salut les potos', 2),
(57, 3, 'Salut les terriens', 2),
(58, 4, 'coucou', 2);

INSERT INTO `Choice` (`idChoice`, `texteAction`, `numPageFrom`, `numPageTo`) VALUES
(3, 'Je suis un choix en Page 1 : affiche moi page2 stp', 55, 56),
(4, 'Je suis un choix en Page 1 : affiche moi page3 stp', 55, 56);

INSERT INTO Choice (texteAction, numPageFrom, numPageTo) VALUES (?, ?, ?);

/* Aller chercher l'id Page sur base du numPage + idBook */
SELECT idPage as idPage
FROM Page
WHERE idBook = 2 AND numPage = 1;

DELETE FROM Choice WHERE numPageFrom =58;

SELECT * FROM Page;
/******************Ajout page*****************/
/* Aller chercher les id des pages afin de renuméro celle qui va être insérée */
/* Je garde supérieur ou égale pour mettre à jour tous les anciennes */
/*Si par exemple, on ajoute la page 2, et on a 2,3,4 en BD alors j'incrémente à 3,4,5 et j'ajoute la 2 */
SELECT idPage as idPage
FROM Page
WHERE idBook = 2 AND numPage >= 2;

UPDATE Page SET numPage = numPage + 1 WHERE idPage = ?;

/****************SUPPRESSION PAGE*******************/
/* Je vais supprimer instantanément la page concernée */
DELETE FROM Page WHERE idBook = 3 AND numPage = 4;
INSERT INTO Page VALUES (115, 4, 'ddd', 3);
/* Je vais sélectionner l'id des autres */
/* Ensuite, je vais aller les mettre à jour */
/* pas de >= comme celle de base est supprimée */
SELECT idPage as idPage
FROM Page
WHERE idBook = 2 AND numPage > ?;

SELECT * from Page;

UPDATE Page SET numPage = numPage - 1 WHERE idPage = ?;



UPDATE Page SET numPage = numPage + 1 WHERE idPage = ?;

SELECT idChoice FROM Choice WHERE numPageFrom = 106 AND numPageTo = 104;
DELETE FROM Choice WHERE numPageFrom = 106 AND numPageTo = 104 AND texteAction = 'p3 vers 91';

UPDATE Book SET estPublie = true WHERE idBook = 2;
UPDATE Book SET estPublie = false WHERE idBook = 2;
select * from Book;

SELECT * FROM Book WHERE isbn = '2-210054-01-X';


INSERT INTO Choice (texteAction, numPageFrom, numPageTo) VALUES (?, ?, ?);

SELECT B.titre as titreLivre, B.isbn as isbnLivre, B.resume as resumeLivre, B.matricule as matriculeAutheur,
A.prenom as prenomAuteur, A.nom as nomAuteur
FROM Book B
Join Author A on B.matricule = A.matricule
WHERE estPublie = 1;

SELECT P.idPage as idPage, P.numPage as numeroPage, P.textPage as textePage
FROM Page P
WHERE idBook = 2;

SELECT c.texteAction as texteAction, c.numPageTo as numPageTo FROM Choice c WHERE c.numPageFrom = 23;

SELECT p.numPage as numPageTo FROM Page p WHERE p.idPage = 24;

select * from Page;

SELECT idBook as idBook
FROM Book
WHERE isbn = '2-070039-01-3';

SELECT prenom as prenomAuteur, nom as nomAuteur
FROM Author
WHERE matricule = 070039




