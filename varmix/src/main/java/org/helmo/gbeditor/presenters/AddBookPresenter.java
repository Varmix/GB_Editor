package org.helmo.gbeditor.presenters;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.helmo.gbeditor.infrastructures.JSONrepository;
import org.helmo.gbeditor.infrastructures.Mapper.BookMapper;
import org.helmo.gbeditor.infrastructures.dataBase.InsertFailedException;
import org.helmo.gbeditor.infrastructures.dataBase.LibraryBook;
import org.helmo.gbeditor.infrastructures.dataBase.SqlBookStorage;
import org.helmo.gbeditor.infrastructures.dataBase.SqlBookStorageFactory;
import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.models.Book;
import org.helmo.gbeditor.models.Isbn;
import org.helmo.gbeditor.repositories.GameBookRepository;
import org.helmo.gbeditor.repositories.GameBookRepositoryDb;

public class AddBookPresenter implements AddBookPresenterInterface {
	
	private TipsViewInterface addBookView;
	private Author auteur;
	//private Isbn isbnBook;
	private Book book;
	//private GameBookRepository jsonRepo = new JSONrepository();
	private EditorBooksPresenter editorBookPresenter;
	private GameBookRepositoryDb bookStorage;

	/**
	 * Constructeur
	 * @param viewInterface
	 * @param showBookView
	 * @param gameBook
	 * @param bookStorage 
	 * @param editorBookPresenter 
	 */
	public AddBookPresenter(TipsViewInterface addBookView, Book gameBook, GameBookRepositoryDb bookStorage, EditorBooksPresenter editorBookPresenter) {
		this.addBookView = addBookView;
		this.book = gameBook;
		this.bookStorage = bookStorage;
		this.editorBookPresenter = editorBookPresenter;
		addBookView.setPresenter(this);
	}
	/**
	 * Permet de mettre à jour notre attribut auteur
	 * dans notre classe ShowBookPresenter
	 * @param author un objet auteur provenant du LoginPresenter
	 */
	public void setAuthor(Author author) {
		this.auteur = author;
		addBookView.displayInfo(auteur.getFirstName() + " " + auteur.getLastName()); 
		addBookView.getISBN(auteur.getMatricule());
	}

	/**
	 * Permet d'afficher ou non le GridPane
	 * lorsqu'on appuie sur le bouton "Ajouter un livre"
	 * dans l'espace de travail
	 */
	@Override
	public void verify() {
		addBookView.goTo();
	}
	
	/**
	 * Cette méthode permet de fournir une aide à l'utilisateur
	 * pour l'encodage de l'isbn lorsqu'il appuie sur le bouton
	 * "Aide ?"
	 */
	@Override
	public void displayHelp() {
		addBookView.displayHelp("Un numéro ISBN-10 est composé par 10 chiffres. Il commence par le numéro du groupe linguistique visé (2 dans notre cas pour le français) suivi par l'identifiant de l'auteur qui correspondra aux 6 chiffres de votre matricule, suivi par 2 chiffres identifiant le livre. Voici un exemple d'ISBN valide : 2-070039-01-3");
	}
	
	
	@Override
	public void addAbook(String title, String isbnUser, String summary) {
		Isbn isbnForBook = new Isbn();
		if(!book.verifyLengthOfBookInputsAreNotEmpty(title, isbnUser, summary)) {
			addBookView.displayAlert("Veuillez compléter tous les champs", false);
		} else if(!isbnForBook.formatIsbnCorrect(isbnUser)) {
			addBookView.displayAlert("Veuillez respecter le format attendu par l'isbn à savoir uniquement des nombres entre les tirets. Exemple : 2-210054-01-X", false);
		//} //else if(!book.checkLengthIsbn(isbnUser)) {
			//showBookView.displayAlert("Votre ISBN ne comporte pas 10 chiffres", false);
		} else if(!isbnForBook.checkISBN(isbnUser)) {
			addBookView.displayAlert("Le code de vérification de votre ISBN ne semble par correcte", false);
		} else {
			book = new Book(auteur, title, new Isbn(isbnUser),  summary);
			System.out.println("ISBN : " + book.getIsbnBook().getIsbn());
			System.out.println(book);
			//Insert le livre en BD
			try {
				System.out.println(bookStorage +  " VALUE RBOOKSTORAGE");
				this.bookStorage.insertBook(book);
			} catch (/*InsertFailedException|*/SQLException e) {
				//TODO : Afficher à la vue
				System.out.println(e.getMessage());
			}
			addBookView.displayAlert("Votre livre a été enregistré", true);
			editorBookPresenter.displayAllBookModel();
		}
		
	}
		
}


