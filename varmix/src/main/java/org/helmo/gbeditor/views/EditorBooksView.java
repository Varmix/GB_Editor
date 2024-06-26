package org.helmo.gbeditor.views;

import org.helmo.gbeditor.presenters.BookViewModel;
import org.helmo.gbeditor.presenters.EditorBooksPresenterInterface;
import org.helmo.gbeditor.presenters.PresenterInterface;
import org.helmo.gbeditor.presenters.TipsViewInterface;
import org.helmo.gbeditor.presenters.ViewInterface;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class EditorBooksView implements TipsViewInterface {
	
	private Label nomAuteur;
	private EditorBooksPresenterInterface listOfBooksPresenter;
	private MainView mainView;
	
	public EditorBooksView(MainView mainView) {
		this.mainView = mainView;
	}

	@Override
	public void setPresenter(PresenterInterface presenter) {
		listOfBooksPresenter = (EditorBooksPresenterInterface) presenter;
		
	}
	
	private ImageView iv = new ImageView(new Image(getClass().getResource("/userIcon.png").toExternalForm()));{
		iv.setPreserveRatio(true);
		iv.setFitWidth(25);
		iv.setFitHeight(25);
	}
	
	private Button addBookButton = new Button("Ajouter un livre"); {
		addBookButton.setPrefHeight(15);
		addBookButton.setPrefWidth(150);
		addBookButton.getStyleClass().add("menu-button");
		addBookButton.setOnAction( action -> listOfBooksPresenter.verify());
	}
	
	private HBox header = new HBox();{
		nomAuteur = new Label("", iv);
		nomAuteur.setPadding(new Insets(10, 0, 10, 0));
		header.setSpacing(50);
		nomAuteur.getStyleClass().add("menu-name-author");
		header.getChildren().add(nomAuteur);
//		header.getChildren().add(addBookButtonHeader);
//		HBox.setMargin(addBookButtonHeader, new Insets(3, 0, 2, 50));
	}
	
	private TextField titleViewModelinput = new TextField(); {
		final int CHARACTER_LIMIT = 150;
		titleViewModelinput.setPromptText("L'Assommoir");
		titleViewModelinput.lengthProperty().addListener(new ChangeListener<Number>() {
			//Limite la zone de saisie à 150 caractères.
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(newValue.intValue() > oldValue.intValue()) {
					if(titleViewModelinput.getText().length() >= CHARACTER_LIMIT) {
						titleViewModelinput.setText(titleViewModelinput.getText().substring(0, CHARACTER_LIMIT));
					}
				}
				
			}
		});
	}
	
	private TextArea summaryViewModelArea = new TextArea(); {
		final int CHARACTER_LIMIT = 500;
		summaryViewModelArea.setPromptText("L'Assommoir raconte la grandeur puis la "
				+ "décadence de Gervaise Macquart, blanchisseuse dans le quartier "
				+ "de la Goutte-d'Or à Paris. Gervaise et son amant Auguste Lantier "
				+ "viennent à Paris avec Claude et Etienne, leurs deux fils. Chapelier de métier, "
				+ "Lantier est paresseux et infidèle.");
		summaryViewModelArea.lengthProperty().addListener(new ChangeListener<Number>() {
			//Limite la zone de saisie à 500 caractères.
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(newValue.intValue() > oldValue.intValue()) {
					if(summaryViewModelArea.getText().length() >= CHARACTER_LIMIT) {
						summaryViewModelArea.setText(summaryViewModelArea.getText().substring(0, CHARACTER_LIMIT));
					}
				}
				
			}
		});
	}
	
	private Label alertMessage = new Label("");{
		alertMessage.getStyleClass().add("alert-user");
	}
	
	private Button updateBookButton = new Button("Mettre à jour"); {
		updateBookButton.setPrefHeight(15);
		updateBookButton.setPrefWidth(150);
		updateBookButton.getStyleClass().add("menu-button");
	}
	
	private Button editionBookButton = new Button("Editer");{
		editionBookButton.setPrefHeight(15);
		editionBookButton.setPrefWidth(150);
		editionBookButton.getStyleClass().add("menu-button");
	}
	
	private TextField inputISBNViewModel = new TextField();{
		inputISBNViewModel.setPromptText("2-070039-01-3");
	}
	
	private Label titleEditionZone = new Label("Zone d'édition du livre");{
		titleEditionZone.getStyleClass().add("title-of-form");
	}
	
	private GridPane modifyBookPane = new GridPane();{
		modifyBookPane.setAlignment(Pos.CENTER);
		modifyBookPane.setHgap(5);
		modifyBookPane.setVgap(5);
		modifyBookPane.setVisible(false);
		modifyBookPane.getStyleClass().add("border-form-login");
		//modifyBookPane.setPrefWidth(425);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(10);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(85);
		
		Label labelTitle = new Label("Titre");
		Label isbnLabel = new Label("ISBN");
		Label labelSummary = new Label("Résumé");
		modifyBookPane.add(titleEditionZone, 0, 0);
		modifyBookPane.add(labelTitle, 0, 2);
		modifyBookPane.add(titleViewModelinput, 1, 2);
		modifyBookPane.add(isbnLabel, 0, 3);
		modifyBookPane.add(inputISBNViewModel, 1, 3);
		modifyBookPane.add(labelSummary, 0, 4);
		modifyBookPane.add(summaryViewModelArea, 1, 4);
		modifyBookPane.add(alertMessage, 1, 7);
		modifyBookPane.add(editionBookButton, 0, 9);
		modifyBookPane.add(updateBookButton, 1, 9);
		GridPane.setColumnSpan(alertMessage, GridPane.REMAINING);
		GridPane.setHalignment(updateBookButton, HPos.RIGHT);
		/* Prendre les deux colonnes et centrés */
		GridPane.setColumnSpan(titleEditionZone, GridPane.REMAINING);
		GridPane.setHalignment(titleEditionZone, HPos.CENTER);
		modifyBookPane.getColumnConstraints().addAll(col1, col2);
		modifyBookPane.setVisible(false);
	}
	
	
	
	
	
	private FlowPane listOfBooks = new FlowPane();{
		listOfBooks.setPrefWidth(300);
		listOfBooks.setHgap(5);
		listOfBooks.setVgap(5);
		listOfBooks.minHeight(200);
	}
	
	private ScrollPane scrollPaneForListBooks = new ScrollPane();{
		scrollPaneForListBooks.setContent(listOfBooks);
		scrollPaneForListBooks.setPrefWidth(300);
		scrollPaneForListBooks.minHeight(200);
		scrollPaneForListBooks.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPaneForListBooks.setHbarPolicy(ScrollBarPolicy.NEVER);
	}
	
	private VBox ListOfBooksOnLeftSide = new VBox();{
		Label labelListeLivre = new Label("Liste des livres");
		ListOfBooksOnLeftSide.getChildren().add(labelListeLivre);
		ListOfBooksOnLeftSide.getChildren().add(scrollPaneForListBooks);
		ListOfBooksOnLeftSide.setPadding(new Insets(2));
		ListOfBooksOnLeftSide.getChildren().add(addBookButton);
		/* Centrer le bouton */
		VBox.setMargin(addBookButton, new Insets(2, 0, 0, 60));
	}
	

	private GridPane createBookModelItem(BookViewModel bookModel, Button buttonBookModel) {
		Label titleBookViewModel = new Label(bookModel.getTitle());
		Label isbnBookViewModel = new Label(bookModel.getIsbnNumber());
		Label authorNameViewModel = new Label(bookModel.getFirstNameAuthor() + " " + bookModel.getLastNameAuthor());
		GridPane BookViewModelItem = new GridPane();{
			BookViewModelItem.getStyleClass().add("border-form-login");
			Label labelTitle = new Label("Titre :");
			Label isbnLabel = new Label("ISBN :");
			Label auteurLabel = new Label("Auteur : ");
			BookViewModelItem.add(labelTitle, 0, 0);
			/* Mettre titre livre */
			BookViewModelItem.add(titleBookViewModel, 1, 0);
			BookViewModelItem.add(isbnLabel, 0, 1);
			/* Mettre isbn livre */
			BookViewModelItem.add(isbnBookViewModel, 1, 1);
			BookViewModelItem.add(auteurLabel, 0, 2);
			BookViewModelItem.add(authorNameViewModel, 1, 2);
			BookViewModelItem.add(buttonBookModel, 1, 3);
			GridPane.setColumnSpan(buttonBookModel, GridPane.REMAINING);
		}
		return BookViewModelItem;
	}
	
	@Override
	public void addBookInTheList(BookViewModel bookModel) {
		//Créer un élément gridPane
		Button buttonModelItem = createButtonModelItem(bookModel);
		GridPane BookModelItem = createBookModelItem(bookModel, buttonModelItem);
		updateBookButton.setOnAction(action -> listOfBooksPresenter.updateInformationAboutABook(titleViewModelinput.getText(), inputISBNViewModel.getText(), summaryViewModelArea.getText() ,bookModel));
		//Ajouter un flowPane
		listOfBooks.getChildren().add(BookModelItem);
		
	}
	
	private Button createButtonModelItem(BookViewModel bookModel) {
		Button editBookViewModelItem = new Button("Voir"); {
			editBookViewModelItem.setPrefHeight(5);
			editBookViewModelItem.setPrefWidth(75);
			editBookViewModelItem.getStyleClass().add("menu-button");
			//Envoyer l'id pour faire une méthode allant chercher davantage d'informations sur le livre
			//qui se fera sur base de cette id envoyé.
			/*editBookViewModelItem.setOnAction( action -> showBookPresenter.informationAboutABookView(idBook)); */
			editBookViewModelItem.setOnAction(action -> displayBasicDetailsAboutAnBook(bookModel));
			/* à la place d'envoyer l'id book, je vais envoyer le bookViewModel, le reconvertir en book */
			/* Ensuite, dans ma méthode updateInformationAboutBook, je vais convertir ma bookViewModel
			 * en ModelBook afin de récupérer la clé dans mon Presenter
			 */
			return editBookViewModelItem;
		}
	}
	
	private void displayBasicDetailsAboutAnBook(BookViewModel bookModel) {
		mainView.setCenter(modifyBookPane);
		modifyBookPane.setVisible(true);
		titleViewModelinput.setText(bookModel.getTitle());
		inputISBNViewModel.setText(bookModel.getIsbnNumber());
		summaryViewModelArea.setText(bookModel.getSummary());
	}
	
	/**
	 * Permet d'afficher une partie de l'isbn,
	 * c'est-à-dire, 2-matricule- . L'auteur
	 * aura besoin uniquement d'indiquer l'identfiant
	 * du livre et le code de vérification.
	 */
	@Override
	public void getISBN(String msg) {
		inputISBNViewModel.setText("2-" + msg + "-");
	}

	@Override
	public VBox getVBox() {
		return ListOfBooksOnLeftSide;
	}

	@Override
	public void displayInfo(String msg) {
		nomAuteur.setText(msg);
	}

	@Override
	public GridPane getPane() {
		return modifyBookPane;
	}

	@Override
	public void goTo() {
		mainView.createAddBookView();		
	}

	@Override
	public HBox getHBox() {
		return header;
	}

	@Override
	public void displayHelp(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayAlert(String msg, boolean resetInput) {
		if(!resetInput) {
			//alertMessage.getStyleClass().add("alert-user");
			alertMessage.setTextFill(Color.RED);
		} else {
			alertMessage.setTextFill(Color.GREEN);
			/* Si c'est bon, j'enlève les anciens livres */
			/* Dans mon Presenter, j'afficherai les nouveaux */
			listOfBooks.getChildren().clear();
		}
		alertMessage.setText(msg);
		
	}
	
	



}
