package org.helmo.gbeditor.views;

import org.helmo.gbeditor.presenters.AddBookPresenter;
import org.helmo.gbeditor.presenters.AddBookPresenterInterface;
import org.helmo.gbeditor.presenters.TipsViewInterface;
import org.helmo.gbeditor.presenters.BookViewModel;
import org.helmo.gbeditor.presenters.PresenterInterface;
import org.helmo.gbeditor.presenters.ViewInterface;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AddBookView  implements TipsViewInterface  {

	private Label message;
	private AddBookPresenterInterface addBookPresenter;
	private MainView mainView;
	
	@Override
	public void setPresenter(PresenterInterface presenter) {
		addBookPresenter = (AddBookPresenterInterface) presenter;
		
	}
	
	public AddBookView(MainView mainView) {
		this.mainView = mainView;
	}
	
	
	
	private Button helpISBNButton = new Button("Aide ?"); {
		helpISBNButton.setPrefHeight(15);
		helpISBNButton.setPrefWidth(150);
		helpISBNButton.getStyleClass().add("menu-button");
		helpISBNButton.setOnAction( action -> addBookPresenter.displayHelp());
	}
	
	private ImageView iv = new ImageView(new Image(getClass().getResource("/userIcon.png").toExternalForm()));{
		iv.setPreserveRatio(true);
		iv.setFitWidth(25);
		iv.setFitHeight(25);
	}
	
	private HBox header = new HBox();{
		message = new Label("", iv);
		message.setPadding(new Insets(10, 0, 10, 0));
		header.setSpacing(50);
		message.getStyleClass().add("menu-name-author");
		header.getChildren().add(message);
//		header.getChildren().add(addBookButtonHeader);
//		HBox.setMargin(addBookButtonHeader, new Insets(3, 0, 2, 50));
	}
	
	private TextField inputTitle = new TextField(); {
		final int CHARACTER_LIMIT = 150;
		inputTitle.setPromptText("L'Assommoir");
		inputTitle.lengthProperty().addListener(new ChangeListener<Number>() {
			//Limite la zone de saisie à 150 caractères.
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(newValue.intValue() > oldValue.intValue()) {
					if(inputTitle.getText().length() >= CHARACTER_LIMIT) {
						inputTitle.setText(inputTitle.getText().substring(0, CHARACTER_LIMIT));
					}
				}
				
			}
		});
	}
	
	private TextArea summaryArea = new TextArea(); {
		final int CHARACTER_LIMIT = 500;
		summaryArea.setPromptText("L'Assommoir raconte la grandeur puis la "
				+ "décadence de Gervaise Macquart, blanchisseuse dans le quartier "
				+ "de la Goutte-d'Or à Paris. Gervaise et son amant Auguste Lantier "
				+ "viennent à Paris avec Claude et Etienne, leurs deux fils. Chapelier de métier, "
				+ "Lantier est paresseux et infidèle.");
		summaryArea.lengthProperty().addListener(new ChangeListener<Number>() {
			//Limite la zone de saisie à 500 caractères.
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(newValue.intValue() > oldValue.intValue()) {
					if(summaryArea.getText().length() >= CHARACTER_LIMIT) {
						summaryArea.setText(summaryArea.getText().substring(0, CHARACTER_LIMIT));
					}
				}
				
			}
		});
	}
	
	private Label helpISBN = new Label("");{
		helpISBN.setVisible(false);
		helpISBN.getStyleClass().add("wrap-text");
	}
	
	private TextField inputISBN = new TextField();{
		inputISBN.setPromptText("2-070039-01-3");
	}
	
	private Button addBookButton = new Button("Ajouter"); {
		addBookButton.setPrefHeight(15);
		addBookButton.setPrefWidth(150);
		addBookButton.getStyleClass().add("menu-button");
		addBookButton.setOnAction( action -> addBookPresenter.addAbook(inputTitle.getText(), inputISBN.getText(), summaryArea.getText()));
	}
	
	private Label alertMessage = new Label("");{
		alertMessage.getStyleClass().add("alert-user");
	}
	
//	private Label successMessage = new Label("");{
//		alertMessage.getStyleClass().add("success-user");
//	}
	
	
	private Label titleCreationBook = new Label("Ajout d'un livre");{
		titleCreationBook.getStyleClass().add("title-of-form");
	}
	
	private GridPane addBookPane = new GridPane();{
		addBookPane.setAlignment(Pos.CENTER);
		addBookPane.setHgap(5);
		addBookPane.setVgap(5);
		//addBookPane.setVisible(false);
		addBookPane.getStyleClass().add("border-form-login");
		addBookPane.setPrefWidth(425);
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(10);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(85);
		
		Label labelTitle = new Label("Titre");
		Label isbnLabel = new Label("ISBN");
		Label labelSummary = new Label("Résumé");
		addBookPane.add(titleCreationBook, 0, 0);
		addBookPane.add(labelTitle, 0, 2);
		addBookPane.add(inputTitle, 1, 2);
		addBookPane.add(isbnLabel, 0, 3);
		addBookPane.add(inputISBN, 1, 3);
		addBookPane.add(labelSummary, 0, 4);
		addBookPane.add(summaryArea, 1, 4);
		addBookPane.add(helpISBNButton, 1, 5);
		addBookPane.add(helpISBN, 1, 6);
		addBookPane.add(alertMessage, 1, 7);
		addBookPane.add(addBookButton, 1, 9);
		GridPane.setColumnSpan(helpISBN, GridPane.REMAINING);
		GridPane.setColumnSpan(alertMessage, GridPane.REMAINING);
		GridPane.setHalignment(helpISBNButton, HPos.RIGHT);
		GridPane.setHalignment(addBookButton, HPos.RIGHT);
		GridPane.setColumnSpan(titleCreationBook, GridPane.REMAINING);
		GridPane.setHalignment(titleCreationBook, HPos.CENTER);
		addBookPane.getColumnConstraints().addAll(col1, col2);
	}
	//private GridPane modifyBookPane = new GridPane();
	
	
	
	
	
//	private BorderPane mainPane = new BorderPane();{
//		mainPane.setTop(header);
//		mainPane.setCenter(gridPane);
//		mainPane.setPadding(new Insets(15));
//	}

	/**
	 * Permet d'afficher le nom et prénom
	 * de l'auteur en permanence au dessus
	 * à gauche de la fenêtre
	 */
	@Override
	public void displayInfo(String msg) {
		message.setText(msg);
	}

	/**
	 * Retourne le GridPane de l'espace
	 * de travail
	 */
	@Override
	public GridPane getPane() {
		return addBookPane;
	}

	/**
	 * Permet d'afficher ou non
	 * le GridPane permettant d'ajouter un livre
	 * lorsqu'on appuie sur le bouton "Ajouter un livre"
	 */
	@Override
	public void goTo() {
//		if(!addBookPane.isVisible()) {
//			addBookPane.setVisible(true);
//		} else {
//			addBookPane.setVisible(false);
//		}
//		//mainView.setRight(addBookPane);
	}
	
	/**
	 * Permet de récupérer le Hbox
	 * qui correspond au header de notre
	 * ShowBookView.
	 */
	@Override
	public HBox getHBox() {
		return header;
	}
	
	/**
	 * Permet d'afficher ou non le message
	 * d'aide à l'encodage de l'isbn en appuyant
	 * sur le bouton "Aide ?"
	 */
	@Override
	public void displayHelp(String msg) {
		helpISBN.setText(msg);
		if(!helpISBN.isVisible()) {
			helpISBN.setVisible(true);
		} else {
			helpISBN.setVisible(false);;
		}
	}
	
	/**
	 * Permet d'afficher les messages d'alerte
	 * à l'utilisateur lorsqu'il ne remplit pas 
	 * le conditions pour ajouter un livre.
	 */
	@Override
	public void displayAlert(String msg, boolean resetInput) {
		if(!resetInput) {
			//alertMessage.getStyleClass().add("alert-user");
			alertMessage.setTextFill(Color.RED);
		}
		alertMessage.setText(msg);
		if(resetInput) {
			inputISBN.setText("");
			inputTitle.setText("");
			summaryArea.setText("");	
			//alertMessage.getStyleClass().add("success-user");
			alertMessage.setTextFill(Color.GREEN);
		}
	}

	@Override
	public VBox getVBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getISBN(String msg) {
		inputISBN.setText("2-" + msg + "-");
		
	}

	@Override
	public void addBookInTheList(BookViewModel bookModel) {
		// TODO Auto-generated method stub
		
	}




}
