package org.helmo.gbeditor.presenters;

/**
 * Interface héritant de PresenterInterface.
 * Elle profite des méthodes proprosées par PresenterInterface
 * et en implémente davantage.
 *
 */
public interface LoginPresenterInterface extends PresenterInterface {

	
	void verify(String firstName, String lastName, String matricule);
}
