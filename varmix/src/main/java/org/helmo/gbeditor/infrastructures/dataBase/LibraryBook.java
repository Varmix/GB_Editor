package org.helmo.gbeditor.infrastructures.dataBase;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.helmo.gbeditor.models.Book;

public class LibraryBook {
	
	private Map<Book, Integer> libraryBook = new HashMap<>();
	
	public LibraryBook() {
		
	}
	
	/**
	 * @param book un livre
	 * @return 
	 * @return l'id du livre
	 */
	public int getASpecificId(Book book) {
		Integer idBook = libraryBook.get(book);
		if(idBook == null) {
			idBook = 0;
		}
		return idBook.intValue();
	}

	/**
	 * Permet d'ajouter un livre à la librarie
	 * @param id l'id reçu depuis la BDD
	 * @param book le livre auquel on va associer l'id
	 */
	public void addBookInTheLibrary(Book book, int id) {
		/* On enlèves les anciens, lorsqu'on va update */
		libraryBook.remove(book);
		libraryBook.put(book, id);	
	}
	
	
	/**
	 * Permet de remplacer un Book en clé
	 * lorsqu'il faut le mettre à jour
	 * @param oldBook l'ancien livre
	 * @param newBook le nouveau
	 */
	public void replaceElement(Book oldBook, Book newBook) {
		int bookId = libraryBook.get(oldBook);
		libraryBook.remove(oldBook);
		libraryBook.put(newBook, bookId);
	}
	
	
//	public boolean getIdBookInTheLibrary(int id) {
//		return libraryBook.containsKey(id);
//	}

	public Collection<Book> getBooks() {
		return libraryBook.keySet();
	}
	
	
	
	
	
	
	
	
	

}
