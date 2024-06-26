package org.helmo.gbeditor.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class Book {
	
	private Author author;
	private String title;
	private Isbn isbn;
	private String summary;
	private boolean isPublished;
	private List<Page> listOfPages = new ArrayList<Page>();
	

	/**
	 * Constructeur vide
	 */
	public Book() {
	}

	/**
	 * Surchage du constructeur
	 * @param firstNameAuthor prénom de l'auteur
	 * @param lastNameAuhtor nom de l'auteur
	 * @param title titre du livre
	 * @param isbnForBook isbn du livre
	 * @param summary résumé du livre
	 */
	public Book(Author author, String title, Isbn isbnForBook, String summary) {
		this.author = author;
		this.title = title;
		this.isbn = isbnForBook;
		this.summary = summary;
		this.isPublished = false;
		System.out.println("new book "+title);
	}

	/**
	 * 
	 * @return l'auteur associé à un livre
	 */
	public Author getAuthor() {
		return author;
	}
	
	
	
	/**
	 * 
	 * @return le titre du livre
	 */
	public String getTitle() {
		return title;
	}
	

	/**
	 * 
	 * @return l'isbn (objet) du livre
	 */
	public Isbn getIsbnBook() {
		return isbn;
	}
	
	/**
	 * 
	 * @return le résumé du livre
	 */
	public String getSummary() {
		return summary;
	}
	
	/**
	 * 
	 * @return le statut du livre
	 */
	public boolean isPublished() {
		return isPublished;
	}

	
	/**
	 *
	 * @param title le titre fourni par l'utilisateur
	 * @param isbnUser l'isbn fourni par l'utilisateur
	 * @param summary le résumé fourni par l'utilisateur
	 * @return true si les tous les champs ne sont pas vides, sinon false
	 */
	public boolean verifyLengthOfBookInputsAreNotEmpty(String title, String isbnUser, String summary) {
		return !title.isEmpty() && !isbnUser.isEmpty() && !summary.isEmpty();
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(isbn, other.isbn);
	}
	
	
	
	
	
	
	
	
	

}
