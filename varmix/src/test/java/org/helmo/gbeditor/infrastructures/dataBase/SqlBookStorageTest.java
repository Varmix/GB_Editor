package org.helmo.gbeditor.infrastructures.dataBase;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.models.Book;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
//@TestInstance(Lifecycle.PER_CLASS)
class SqlBookStorageTest {
	
	private static SqlBookStorageFactory factory = new SqlBookStorageFactory(
			"org.apache.derby.jdbc.EmbeddedDriver",
            "jdbc:derby:../GB_Book_DERBY",
            "",
            ""
    ); 
	private  Author auteur = new Author("Emile", "Zola", "210054");
	private  Author auteur2 = new Author("Michel", "Serres", "070039");
	private  Book book = new Book(auteur, "L'Assommoir", "2-210054-01-X", "Livre de Zola" );
	private  Book book2 = new Book(auteur2, "Petite Poucette", "2-070039-01-3", "Petite Poucette est un essai publié par le philosophe français Michel Serres en 2012" );
	private  LibraryBook libraryBook = new LibraryBook();
	
	@BeforeEach
    public void setup() throws Exception {
        try(SqlBookStorage storage = factory.newStorageSession(libraryBook)) {
            try {
                storage.setup();
            } catch(UnableToTearDownException ex) {
                //Cette exception peut être lancée si le schéma ne contient pas les tables.
                //La méthode essaie alors de créer les tables.
            }
        }
    }

    @AfterEach
    public void teardown() throws Exception {
        try(SqlBookStorage storage = factory.newStorageSession(libraryBook)) {
            storage.tearDown();
        }
    }
	
	


	@Test
	void insertAuthor() throws Exception {
		try(SqlBookStorage storage = factory.newStorageSession(libraryBook)) {
			
			storage.insertAuthor(auteur);
			/* On vérifie que la valeur reçue est bien différente de la valeur par défaut */
			assertNotEquals(auteur.getIdAuthor(), -1);
			/* Commence à 1 en Bd */
			assertTrue(auteur.getIdAuthor() > 0);
			
			storage.insertAuthor(auteur2);
			/* On vérifie que la valeur reçue est bien différente de la valeur par défaut */
			assertNotEquals(auteur2.getIdAuthor(), -1);
			//Le 1 a déjà été inséré
			assertTrue(auteur2.getIdAuthor() > 1);
		}
	}
	
	@Test 
	void insertBook() throws Exception {
		try(SqlBookStorage storage = factory.newStorageSession(libraryBook)) {
			storage.insertAuthor(auteur);
			storage.insertAuthor(auteur2);
			
			storage.insertBook(book);
			/* On vérifie que la valeur reçue est bien différente de la valeur par défaut */
			assertFalse(libraryBook.isPublished(book));
			/* Commence à 1 en Bd */
			assertTrue(libraryBook.getIdBookInTheLibrary(1));
			
			storage.insertBook(book2);
			
			assertFalse(libraryBook.isPublished(book2));

			assertTrue(libraryBook.getIdBookInTheLibrary(2));
			
		}
	}
	
	@Test
	void loadBasicBookInformation() throws Exception {
		try(SqlBookStorage storage = factory.newStorageSession(libraryBook)) {
			storage.insertAuthor(auteur);
			storage.insertAuthor(auteur2);
			
			storage.insertBook(book);
			storage.insertBook(book2);
			
			storage.loadBasicBookInformation(auteur);
		}
	}
	
//	@Test
//	void insert

}
