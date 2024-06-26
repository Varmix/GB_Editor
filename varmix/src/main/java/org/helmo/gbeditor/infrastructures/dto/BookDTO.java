package org.helmo.gbeditor.infrastructures.dto;

public class BookDTO {
	

	private String firstNameAuthor;
	private String lastNameAuthor;
	private String title;
	private String isbn;
	private String summary;
	
	/**
	 * Constructeur
	 * @param firstNameAuthor prénom de l'auteur
	 * @param lastNameAuthor nom de l'auteur
	 * @param title titre du livre
	 * @param isbn isbn du livre
	 * @param summary résumé du livre
	 */
	public BookDTO(String firstNameAuthor, String lastNameAuthor, String title, String isbn, String summary) {
		this.firstNameAuthor = firstNameAuthor;
		this.lastNameAuthor = lastNameAuthor;
		this.title = title;
		this.isbn = isbn;
		this.summary = summary;
	}
	
	/**
	 * 
	 * @return le prénom de l'auteur
	 */
	public String getFirstName() {
		return firstNameAuthor;
	}
	
	/**
	 * 
	 * @return le nom de l'auteur
	 */
	public String getLastName() {
		return lastNameAuthor;
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
	 * @return l'isbn du livre
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * 
	 * @return le résumé du livre
	 */
	public String getSummary() {
		return summary;
	}
	
	

}
