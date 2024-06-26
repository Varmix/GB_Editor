package org.helmo.gbeditor.presenters;


import java.sql.SQLException;

import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.repositories.GameBookRepositoryDb;

public class LoginPresenter implements LoginPresenterInterface  {
	
	/**
	 * Déclaration des attributs
	 */
	private ViewInterface loginView;
	private EditorBooksPresenter editorBooksPresenter;
	private AddBookPresenter addBookPresenter;
	private Author author;
	private GameBookRepositoryDb bookStorage;
	
	/**
	 * Constructeur
	 * @param viewInterface LoginViewJoh
	 * @param addBookPresenter le presenter de notre prochaine vue
	 * @param auteur objet auteur
	 * @param bookStorage 
	 * @param addBookPresenter 
	 */
	public LoginPresenter(ViewInterface viewInterface, EditorBooksPresenter editorBooksPresenter, Author auteur, GameBookRepositoryDb bookStorage, AddBookPresenter addBookPresenter) {
		this.loginView = viewInterface;
		this.editorBooksPresenter = editorBooksPresenter;
		this.author = auteur;
		this.bookStorage = bookStorage;
		this.addBookPresenter = addBookPresenter;
		viewInterface.setPresenter(this);
		
	}

	/**
	 * Permet de vérifier les données du formulaire
	 * de connexion et envoyer les données nécessaires
	 * à la prochaine vue
	 * @param firstName le prénom encodée
	 * @param lastName le nom encodée
	 */
//	public void verify(String firstName, String lastName, String matricule) {
//		String msg = "";
//		if(!author.verifyLenghtOfAuthorInputs(firstName, lastName)) {
//			if(author.minimumLenghtOfAFirstName(firstName)) {
//				msg = "Merci de mettre un prénom valide";
//			} else if(author.minimumLenghtOfALastName(lastName)) {
//				msg = "Merci de mettre un nom valide";
//			} else {
//				this.author = new Author(firstName, lastName);
//				showBookPresenter.setAuthor(author);
//				loginView.goTo();
//			}
//		} else {
//			msg = "Veuillez compléter tous les champs !";
//		}
//		loginView.displayInfo(msg);
//	}
	
	public void verify(String firstName, String lastName, String matricule) {
		if(author.verifyLenghtOfAuthorInputsAreEmpty(firstName, lastName, matricule)) {
			loginView.displayInfo("Veuillez compléter tous les champs !");
		} else if(author.minimumLenghtOfAFirstName(firstName)) {
			loginView.displayInfo("Merci de mettre un prénom valide !");
		} else if(author.minimumLenghtOfALastName(lastName)) {
			loginView.displayInfo("Merci de mettre un nom valide !");
		} else if(!author.formatMatriculeCorrect(matricule)) {
			loginView.displayInfo("Votre matricule ne respecte pas le format attendu : une lettre étant facultative, suivie de 6 chiffres !");
		} else {
			treatmentCreationAuthor(firstName, lastName, matricule);
		}
	}

	private void treatmentCreationAuthor(String firstName, String lastName, String matricule) {
		this.author = new Author(firstName, lastName, matricule.toUpperCase());
		try {
			if(!bookStorage.verifiyIfUserIsInDb(matricule)) {
				bookStorage.insertAuthor(author);
			} else {
				this.author = bookStorage.getAuthorInformations(matricule);
			}
		} catch (SQLException e) {
			//TODO : afficher le message à la vue
			System.out.println(e.getMessage());
		}
		editorBooksPresenter.setAuthor(author);
		editorBooksPresenter.displayAllBookModel();
		addBookPresenter.setAuthor(author);
		loginView.goTo();
	}

}
