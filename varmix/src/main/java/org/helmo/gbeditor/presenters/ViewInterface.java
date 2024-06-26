package org.helmo.gbeditor.presenters;

import javafx.scene.layout.GridPane;

/**
 * Interface possédant les méthodes globales 
 * pour les vues
 *
 */
public interface ViewInterface {


	void displayInfo(String msg);
	
	GridPane getPane();
	
	void setPresenter(PresenterInterface presenter);


	void goTo();
	


	

}
