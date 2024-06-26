package org.helmo.gbeditor.presenters;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Interface héritant de ViewInterface afin
 * d'ajouter des méthodes aux vues qui devraient bénéficer
 * à la fois de méthodes provennant de ViewInterface et en implémenter davantage.
 *
 */
public interface TipsViewInterface extends ViewInterface {
	
	HBox getHBox();
	
	VBox getVBox();
	
	void displayHelp(String msg);
	
	void displayAlert(String msg, boolean resetInput);
	
	void getISBN(String msg);
	
	void addBookInTheList(BookViewModel bookModel);

}
