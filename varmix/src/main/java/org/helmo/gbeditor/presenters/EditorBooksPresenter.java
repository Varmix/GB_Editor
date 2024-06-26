package org.helmo.gbeditor.presenters;

import java.sql.SQLException;
import java.util.List;

import org.helmo.gbeditor.infrastructures.Mapper.BookMapper;
import org.helmo.gbeditor.infrastructures.dataBase.LibraryBook;
import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.models.Book;
import org.helmo.gbeditor.models.Isbn;
import org.helmo.gbeditor.repositories.GameBookRepositoryDb;

public class EditorBooksPresenter implements EditorBooksPresenterInterface {
	
	private TipsViewInterface editorBooksView;
	private Book book;
	private LibraryBook libraryBook;
	private GameBookRepositoryDb bookStorage;
	private Author auteur;

	public EditorBooksPresenter(TipsViewInterface editorBooksView, Book gameBook, GameBookRepositoryDb bookStorage,
			LibraryBook libraryBook) {
		this.editorBooksView = editorBooksView;
		this.book = gameBook;
		this.bookStorage = bookStorage;
		this.libraryBook = libraryBook;
		editorBooksView.setPresenter(this);
	}

	/**
	 * Permet de mettre à jour notre attribut auteur
	 * dans notre classe EditorBookPresenter
	 * @param author un objet auteur provenant du LoginPresenter
	 */
	public void setAuthor(Author author) {
		this.auteur = author;
		editorBooksView.displayInfo(auteur.getFirstName() + " " + auteur.getLastName()); 
		editorBooksView.getISBN(auteur.getMatricule());
	}

		/**
	 * Permettre d'afficher tous les livres liés à l'auteur
	 */
	public void displayAllBookModel() {
		System.out.println("Je suis ici");
		try {
			bookStorage.loadBasicBookInformation(auteur);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		List<BookViewModel> allBooksViewModel = BookMapper.convertBooksBookViewModel(libraryBook.getBooks());
		for (BookViewModel bookViewModel : allBooksViewModel) {
			System.out.println(bookViewModel.getTitle());
			editorBooksView.addBookInTheList(bookViewModel);
		}
		
	}
	@Override
	public void updateInformationAboutABook(String title, String isbn, String summary, BookViewModel bookModel) {
		Isbn isbnForBook = new Isbn();
		if(!book.verifyLengthOfBookInputsAreNotEmpty(title, isbn, summary)) {
			editorBooksView.displayAlert("Veuillez compléter tous les champs", false);
		} else if(!isbnForBook.formatIsbnCorrect(isbn)) {
			editorBooksView.displayAlert("Veuillez respecter le format attendu par l'isbn à savoir uniquement des nombres entre les tirets. Exemple : 2-210054-01-X", false);
		//} //else if(!book.checkLengthIsbn(isbnUser)) {
			//showBookView.displayAlert("Votre ISBN ne comporte pas 10 chiffres", false);
		} else if(!isbnForBook.checkISBN(isbn)) {
			editorBooksView.displayAlert("Le code de vérification de votre ISBN ne semble par correcte", false);
		} else {
			Book oldBook = BookMapper.convertBookViewModelToBook(bookModel);
			book = new Book(auteur, title, isbnForBook, summary);
			//libraryBook.replaceElement(oldBook, book);
			/* Faire la requête pour update */
			//Update le libre en bd
			try {
				this.bookStorage.updateBook(book);
				libraryBook.replaceElement(oldBook, book);
			} catch (/*InsertFailedException|*/SQLException e) {
				//TODO : Afficher à la vue
				System.out.println(e.getMessage());
			}
			editorBooksView.displayAlert("Votre livre a été modifié", true);
			displayAllBookModel();

		}
	}

	@Override
	public void verify() {
		editorBooksView.goTo();
		
	}

	@Override
	public void displayHelp() {
		// TODO Auto-generated method stub
		
	}

}
