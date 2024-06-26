package org.helmo.gbeditor.presenters;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Interface héritant de PresenterInterface.
 * Elle profite des méthodes proprosées par PresenterInterface
 * et en implémente davantage.
 *
 */
public interface AddBookPresenterInterface extends PresenterInterface {
	
	void verify();
	
	void displayHelp();
	
	void addAbook(String title, String isbn, String text);

}
