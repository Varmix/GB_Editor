package org.helmo.gbeditor.views;

import java.util.ArrayList;
import java.util.List;

import org.helmo.gbeditor.presenters.AddBookPresenter;
import org.helmo.gbeditor.presenters.TipsViewInterface;
import org.helmo.gbeditor.presenters.LoginPresenter;
import org.helmo.gbeditor.presenters.ViewInterface;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainView {
	
	private List<ViewInterface> allViews = new ArrayList<ViewInterface>();
	private TabPane tabPane = new TabPane();
	

	/**
	 * Constructeur vide
	 */
	public MainView() {
	}

	/**
	 * Déclaration de la mainPane
	 */
	private BorderPane mainPane = new BorderPane();
	{
		mainPane.setPadding(new Insets(15));
	}
	
	
	/**
	 * 
	 * @return le chemin du mainPane
	 */
	public Parent getRoot() {
		LoginView loginView = (LoginView) allViews.get(0);
		setTop(loginView.getVbox());;
		setCenter(loginView.getPane());
		//setCenter(new AddBookView(this));
		return mainPane;
	}
	/**
	 * Met à jour l'élément top de notre MainView
	 * @param header une Vbox
	 */
	public void setTop(VBox header) {
		mainPane.setTop(header);
		
	}
	/**
	 * Permet d'ajouter un élément dans la liste des vues
	 * contenue dans MainView
	 * @param view une vue
	 */
	public void add(ViewInterface view) {
		allViews.add(view);
	}
	
	/**
	 * 
	 * @param i un entier 
	 * @return la vue sur base de l'entier entré par l'utilisateur
	 */
	public ViewInterface getView(int i) {
		return allViews.get(i);
	}
	
	/**
	 * 
	 * @return une liste contenant l'ensemble des vues
	 */
	public List<ViewInterface> getAllViews() {
		return List.copyOf(allViews);
	}

	/**
	 * Permet de mettre à jour la mainView après que les données
	 * (provenant du loginView) soient correctes.
	 */
	public void createAddBookView() {
		TipsViewInterface showbookview = (TipsViewInterface) allViews.get(1);
		//mainPane.setTop(showbookview.getHBox());
		System.out.println("yoyoyo");
		mainPane.setCenter(showbookview.getPane());
	}
	
	public void createEditorZoneView() {
		TipsViewInterface editorBooksView = (TipsViewInterface) allViews.get(2);
		mainPane.setTop(editorBooksView.getHBox());
		/* Liste  de livre sur la gauche */
		mainPane.setLeft(editorBooksView.getVBox());
		mainPane.setCenter(editorBooksView.getPane());
	}
	
	/**
	 * Permet de mettre à jour l'élément au centre
	 * de notre BorderPane dans la MainView
	 * @param pane un élément GridPane
	 */
	public void setCenter(GridPane pane) {
		mainPane.setCenter(pane);
	}
	/**
	 * Permet de mettre à jour l'élément à droite
	 * de notre BorderPane dans le MainView
	 * @param gridPane est un élément GridPane
	 */
	public void setRight(GridPane gridPane) {
		mainPane.setRight(gridPane);
		
	}
	
	/**
	 * Permet de mettre à jour l'élément à gauche
	 * de notre BorderPane dans le MainView
	 * @param vbox est un élément vbox javafx
	 */
	public void setRight(VBox vbox) {
		mainPane.setLeft(vbox);
		
	}
	

}
