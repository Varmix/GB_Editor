package org.helmo.gbeditor.models;

import java.util.regex.Pattern;

public class Author {
	
	private int idAuthor;
	private String firstName;
	private String lastName;
	private String matricule;
	
	/**
	 * Constructeur vide pour la première instancation
	 */
	public Author() {
		
	}
	
	/**
	 * Surcharge du constructeur
	 * @param firstName le prénom de l'auteur
	 * @param lastName le nom de l'auteur
	 */
	public Author(String firstName, String lastName, String matricule) {
		this.firstName = firstName;
		this.lastName = lastName;
		if(matricule.length() == 7) {
			matricule.substring(1);
		}
		this.matricule = matricule;
		/* ID par défaut tant qu'il n'a pas été en DB */
		idAuthor = -1;
	}
	
	public Author(String firstName, String lastName, String matricule, int authorId) {
		this.firstName = firstName;
		this.lastName = lastName;
		if(matricule.length() == 7) {
			matricule.substring(1);
		}
		this.matricule = matricule;
		this.idAuthor = authorId;
	}
	

	/**
	 * 
	 * @return le prénom de l'auteur
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * 
	 * @return le nom de famille de l'auteur
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * 
	 * @return le matricule de l'auteur
	 */
	public String getMatricule() {
		return matricule;
	}
	
	
	
	/**
	 * 
	 * @return l'id de l'auteur
	 */
	public int getIdAuthor() {
		return idAuthor;
	}

	/**
	 * Permet de pattre à jour l'id de l'auteur
	 * @param idAuthor id de l'auteur
	 */
	public void setIdAuthor(int idAuthor) {
		this.idAuthor = idAuthor;
	}

	/**
	 * 
	 * @param firstName prénom fourni par l'utilisateur
	 * @param lastName nom fourni par l'utilisateur
	 * @param matricule matricule fourni par l'utilisateur
	 * @return true si un des champs est vide, sinon false
	 */
	public boolean verifyLenghtOfAuthorInputsAreEmpty(String firstName, String lastName, String matricule) {
		return firstName.isBlank() || lastName.isBlank() || matricule.isBlank();
	}
	/**
	 * 
	 * @param firstName prénom fourni par l'utilisateur
	 * @return true si le prénom fait moins de deux caractères sinon false
	 */
	public boolean minimumLenghtOfAFirstName(String firstName) {
		return firstName.length() < 2;
	}
	/**
	 * 
	 * @param lastName nom fourni par l'utilisateur
	 * @return true si le nom fait moins de deux caractères sinon false
	 */
	public boolean minimumLenghtOfALastName(String lastName) {
		return lastName.length() < 2;
	}

	/**
	 * 
	 * @param matricule matircule saisi par l'utilisateur
	 * @return true si le matricule est correct, sinon false
	 */
	public boolean formatMatriculeCorrect(String matricule) {
		return Pattern.matches("[a-zA-z]?\\d{6}", matricule);
	}

	
	

}
