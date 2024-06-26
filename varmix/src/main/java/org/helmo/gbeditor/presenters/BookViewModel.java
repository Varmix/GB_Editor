package org.helmo.gbeditor.presenters;

import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.models.Book;
import org.helmo.gbeditor.models.Isbn;

public class BookViewModel {
	
	private Book book;
	
	public BookViewModel() {
	}
	
	public BookViewModel(Book book) {
		this.book = book;
	}
	
	
	public Author getAuthor() {
		return book.getAuthor();
	}

	/* Il me faut les 15 premiers caractères */
	public String getTitle() {
		return book.getTitle();
	}
	
	public Isbn getBookIsbn() {
		return book.getIsbnBook();
	}
	
	public String getIsbnNumber() {
		return book.getIsbnBook().getIsbn();
	}
	
	public String getFirstNameAuthor() {
		return book.getAuthor().getFirstName();
	}
	

	public String getLastNameAuthor() {
		return book.getAuthor().getLastName();
	}
	
	public String getSummary() {
		return book.getSummary();
	}
	
	@Override
	public String toString() {
		return "Titre : " + getTitle() + "\r\n"
				+ "ISBN : " + getIsbnNumber() + "\r\n"
				+ "Auteur :" + getFirstNameAuthor() + " " + getLastNameAuthor();
	}
	
	

}
