package org.helmo.gbeditor.models;

import java.util.Objects;
import java.util.regex.Pattern;

public class Isbn {
	
	private String isbn;

	/**
	 * Constructeur vide
	 */
	public Isbn() {
		
	}
	/*
	 * Surcharge de constructeur
	 */
	public Isbn(String isbn) {
		this.isbn = isbn;
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
	 * @param isbnUser isbn fourni par l'utilisateur
	 * @return true si l'isbn fourni par l'utilisateur est équivalent au isbn calculé
	 */
	public boolean checkISBN(String isbnUser) {
		String isbn = isbnUser;
		// 0-1 2-8 9
		//ISBN 2-070039-013 -> 2070039013
//		if(!formatIsbnCorrect(isbn)) {
//			return false;
//		}
		if(!checkLengthIsbn(isbn) || !formatIsbnCorrect(isbn)) {
			return false;
		}
		isbn = isbnUser.substring(0, 1) + isbnUser.substring(2, 8) + isbnUser.substring(9, 11) + isbnUser.substring(12);
		int sum = sumIsbn(isbn);
		//Calcul de la reste de la somme précèdente par 11
		int restSum = sum % 11;
		//Calcul du code controle
		int controlCode = 11 - restSum;
		//Vérification si le code de vérification entrée par l'utilsiateur est égale au code fourni par la méthode
		String copyIsbn = particularVerificationCode(isbn, controlCode);
		if(!checkVerificationCode(isbn, copyIsbn)) {
			return false;
		}
		//2-210054-05-2
//		  0123456789
		//2210054052
		this.isbn = copyIsbn.substring(0, 1) + "-" + copyIsbn.substring(1, 7) + "-" + copyIsbn.substring(7, 9) + "-" + copyIsbn.substring(9);
		return true;
	}
	/**
	 * 
	 * @param isbn fourni par l'utilisateur tronqué pour conserver uniquement les chiffres
	 * @param copyIsbn isbn calculé
	 * @return true si le code de vérification calculé est équivalent à celui fourni
	 * par l'utilisateur
	 */
	private boolean checkVerificationCode(String isbn, String copyIsbn) {
		return copyIsbn.charAt(9) == isbn.charAt(9);
	}
	
	/**
	 * Cette méthode permet d'assigner le code de contrôle calculé à
     * une copie de l'isbn de l'utilisateur. Bien évidemment,
     * ce dernier a été tronqué pour ne conserver que les 9 premiers chiffres
	 * (sans le code de vérification)
	 * @param isbn tronqué pour conserver uniquement les chiffres et sans le code de vérification
	 * @param controlCode code de contrôle calculé
	 * @return retourne l'isbn avec le code de vérification calculé.
     * Si ce dernier est 10, on le remplace par un "X". S'il vaut 11, on le rempalce par un "0".
     * Dans les autres cas, on assignera le code de vérification calculé.
	 */
	private String particularVerificationCode(String isbn, int controlCode) {
		String copyIsbn = "";
		//Cas particulier
		if(controlCode == 10) {
			copyIsbn = isbn.substring(0, 9) + "X";
		} else if(controlCode == 11) {
			copyIsbn = isbn.substring(0, 9) + "0";                
		} else {
			copyIsbn = isbn.substring(0, 9) + String.valueOf(controlCode);
		}
		return copyIsbn;
	}

	
	private int sumIsbn(String isbn) {
		int isbnNumber, multiplicatorNumber, resultat;
		int sum = 0;
		//Je ne prends pas le dernier caractère pour faire la vérification
		for (int i = 0; i < isbn.length() - 1; i++) {
			isbnNumber = Integer.parseInt(isbn.substring(i, i+1));
			multiplicatorNumber = 10 - i;
			resultat = multiplicatorNumber * isbnNumber;
			sum += resultat;
		}
		return sum;
	}
	/**
	 * 
	 * @param isbn fourni par l'utilisateur
	 * @return true si l'isbn a une longueur de 13, sinon false
	 */
	public boolean checkLengthIsbn(String isbn) {
		return isbn.length() == 13;
	}
	
	/**
	 * Vérifie si le format d'un isbn est correct
	 * @param isbn un isbn fourni par l'utilsiateur
	 * @return true si le format est correct, sinon false
	 */
	public boolean formatIsbnCorrect(String isbn) {
		return Pattern.matches("\\d-\\d{6}-\\d{2}-[\\d|X]{1}", isbn);
	}
	/*
	 * Met à jour la valeur de l'isbn
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
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
		Isbn other = (Isbn) obj;
		return Objects.equals(isbn, other.isbn);
	}

	
	

}
