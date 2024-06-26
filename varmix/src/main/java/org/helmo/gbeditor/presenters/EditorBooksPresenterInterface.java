package org.helmo.gbeditor.presenters;

public interface EditorBooksPresenterInterface extends PresenterInterface {

	void verify();

	void displayHelp();
	
	void updateInformationAboutABook(String title, String isbn, String summary, BookViewModel bookModel);
	
	

}
