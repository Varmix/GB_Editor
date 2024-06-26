package org.helmo.gbeditor.views;


import org.helmo.gbeditor.presenters.LoginPresenter;
import org.helmo.gbeditor.presenters.LoginPresenterInterface;
import org.helmo.gbeditor.presenters.PresenterInterface;
import org.helmo.gbeditor.presenters.ViewInterface;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Vue de connexion
 *
 */
public class LoginView implements ViewInterface {

	private LoginPresenterInterface loginPresenter;// = new LoginPresenter(this);
	private MainView mainView;
	
	

	private Label message = new Label("");{
		message.getStyleClass().add("alert-user");
	}

	private ImageView iv = new ImageView(new Image(getClass().getResource("/logoBook.jpg").toExternalForm()));{
		iv.setPreserveRatio(true);
		iv.setFitWidth(125);
	}
	private VBox header = new VBox();{
		header.setAlignment(Pos.CENTER);
		Label titleOfForm = new Label("Livre de jeux", iv);
		titleOfForm.getStyleClass().add("title-of-mainView");
		header.setPadding(new Insets(10, 5, 5, 5));
		header.getChildren().add(titleOfForm);

	}


	private TextField inputFirstName = new TextField();{
		//Permet l'ajour d'un placeholder
		inputFirstName.setPromptText("John");
		//Définir une préférence de la largeur
		inputFirstName.setMinWidth(250);
		inputFirstName.setPadding(new Insets(10, 0, 10, 0));
		inputFirstName.getStyleClass().add("input-of-form");
	}
	private TextField inputSurName = new TextField();{
		inputSurName.setPromptText("Doe");
		inputSurName.setPadding(new Insets(10, 0, 10, 0));
		inputSurName.getStyleClass().add("input-of-form");
	}
	
	private TextField inputMatricule = new TextField();{
		inputMatricule.setPromptText("Q210054 ou 210054");
		inputMatricule.setPadding(new Insets(10, 0, 10, 0));
		inputMatricule.getStyleClass().add("input-of-form");
	}

	private Button connexionButton = new Button("Se connecter"); {
		connexionButton.setPrefHeight(40);
		connexionButton.setPrefWidth(125);
		connexionButton.getStyleClass().add("login-button");
		connexionButton.setOnAction( action -> loginPresenter.verify(inputFirstName.getText(), inputSurName.getText(), inputMatricule.getText()));
	}

	private GridPane formPane = new GridPane();{
		//Définir le GridPane
		formPane.setAlignment(Pos.CENTER);
		formPane.setHgap(5);
		formPane.setVgap(5);
		formPane.setPadding(new Insets(15, 15, 15, 15));
		formPane.setMaxWidth(700);

		//Déclaration des éléments qui n'ont pas besoin de traitement.
		Label labelFirstName = new Label("Prénom*");
		Label labelSurName = new Label("Nom*");
		Label labelMatircule = new Label("Matricule*");
		Label nameOfTheForm = new Label("Connexion");
		nameOfTheForm.getStyleClass().add("title-of-form");
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(20);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(50);
		formPane.add(nameOfTheForm, 1, 0);
		formPane.add(labelFirstName, 0, 2);
		formPane.add(inputFirstName, 1, 2);
		formPane.add(labelSurName, 0, 3);
		formPane.add(inputSurName, 1, 3);
		formPane.add(labelMatircule, 0, 4);
		formPane.add(inputMatricule, 1, 4);
		formPane.add(message, 1, 5);
		formPane.add(connexionButton, 1, 6);
		formPane.getStyleClass().add("border-form-login");
		labelFirstName.getStyleClass().add("label-of-form");
		labelSurName.getStyleClass().add("label-of-form");
		labelMatircule.getStyleClass().add("label-of-form");
		//GridPane.setHalignment(connexionButton, HPos.CENTER);
		GridPane.setMargin(nameOfTheForm, new Insets(0, 0, 25, 10));
		//GridPane.setHalignment(connexionButton, HPos.CENTER);
		GridPane.setMargin(connexionButton, new Insets(15, 0, 0, 18));
		GridPane.setColumnSpan(message, GridPane.REMAINING);
		formPane.setPadding(new Insets(10, 5, 5, 5));
		formPane.getColumnConstraints().addAll(col1, col2);
//		GridPane.setHalignment(labelFirstName, HPos.CENTER);
//		GridPane.setHalignment(labelSurName, HPos.CENTER);
//		GridPane.setHalignment(labelMatircule, HPos.CENTER);
	}
	
//	private BorderPane mainPane = new BorderPane();{
//		mainPane.setTop(header);
//		mainPane.setCenter(formPane);
//		//mainPane.setPadding(new Insets(15));
//	}
	
	/**
	 * Constructeur
	 * @param mainView vue principale
	 */
	public LoginView(MainView mainView) {
		this.mainView = mainView;
	}

	/**
	 * Affiche les messages d'alerte concernant
	 * la vérfication des données de connexion
	 * provenant du LoginPresenter.
	 */
	@Override
	public void displayInfo(String msg) {
		message.setText(msg);
	}

	/**
	 * Retourne le GridPane de la LoginView
	 */
	@Override
	public GridPane getPane() {
		return formPane;
	}

	/**
	 * Permet de mettre à jour la valeur de l'attribut du présentateur
	 * du LoginPresenter.
	 */
	@Override
	public void setPresenter(PresenterInterface presenter) {
		this.loginPresenter = (LoginPresenterInterface) presenter;
	}

	/**
	 * 
	 * @return la VBox de la classe LoginView
	 */
	public VBox getVbox() {
		return header;
	}

	/**
	 * Permet de switcher vers la prochaine vue 
	 * lorsqu'on s'est connecté en respectant
	 * toutes les conditions.
	 */
	@Override
	public void goTo() {
		mainView.createEditorZoneView();
	}




}
