package org.helmo.gbeditor.views;

import org.helmo.gbeditor.presenters.MenuPresenter;
import org.helmo.gbeditor.presenters.PresenterInterface;
import org.helmo.gbeditor.presenters.TipsViewInterface;
import org.helmo.gbeditor.presenters.ViewInterface;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MenuView implements ViewInterface{
	
	private MenuPresenter menuPresenter;
	private MainView mainView;
	
//	
//	
//	/* Sera placé au centre de la mainView */
//	
//	private Button addBookButton = new Button("Ajouter un livre"); {
//		addBookButton.setPrefHeight(15);
//		addBookButton.setPrefWidth(150);
//		addBookButton.getStyleClass().add("menu-button");
//		addBookButton.setOnAction( action -> menuPresenter.verify("Ajouter"));
//	}
//	
//	private Button editionZoneButton = new Button("Espace de travail"); {
//		addBookButton.setPrefHeight(15);
//		addBookButton.setPrefWidth(150);
//		addBookButton.getStyleClass().add("menu-button");
//		addBookButton.setOnAction( action -> menuPresenter.verify("Edition"));
//	}
//	
//	private Button quitApplicationButton = new Button("Quitter l'application"); {
//		addBookButton.setPrefHeight(15);
//		addBookButton.setPrefWidth(150);
//		addBookButton.getStyleClass().add("menu-button");
//		addBookButton.setOnAction( action -> menuPresenter.verify("Quitter"));
//	}
//	
//	private VBox menuVbox = new VBox();{
//		menuVbox.getChildren().add(addBookButton);
//		menuVbox.getChildren().add(editionZoneButton);
//		menuVbox.getChildren().add(quitApplicationButton);
//	}

	/**
	 * Constructeur
	 * @param mainView vue principale
	 */
	public MenuView(MainView mainView) {
		this.mainView = mainView;
	}

	@Override
	public void displayInfo(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GridPane getPane() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPresenter(PresenterInterface presenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goTo() {
		// TODO Auto-generated method stub
		
	}
}
