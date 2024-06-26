/*
 * This Java source file was generated by the Gradle 'init' task.
 */
/*
 * Aucune dispense
 */
package org.helmo.gbeditor;

import java.util.ArrayList;
import java.util.List;

import org.helmo.gbeditor.infrastructures.dataBase.LibraryBook;
import org.helmo.gbeditor.infrastructures.dataBase.SqlBookStorage;
import org.helmo.gbeditor.infrastructures.dataBase.SqlBookStorageFactory;
import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.models.Book;
import org.helmo.gbeditor.models.Isbn;
import org.helmo.gbeditor.presenters.AddBookPresenter;
import org.helmo.gbeditor.presenters.EditorBooksPresenter;
import org.helmo.gbeditor.presenters.LoginPresenter;
import org.helmo.gbeditor.presenters.MainPresenter;
import org.helmo.gbeditor.presenters.PresenterInterface;
import org.helmo.gbeditor.presenters.ViewInterface;
import org.helmo.gbeditor.repositories.GameBookRepositoryDb;
import org.helmo.gbeditor.views.LoginView;
import org.helmo.gbeditor.views.MainView;
import org.helmo.gbeditor.views.AddBookView;
import org.helmo.gbeditor.views.EditorBooksView;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Déclaration des vues
		MainView mainView = new MainView();
		AddBookView addBookView = new AddBookView(mainView);
		EditorBooksView editorsBooksView = new EditorBooksView(mainView);
		mainView.add(new LoginView(mainView));
		mainView.add(addBookView);
		mainView.add(editorsBooksView);
		
		//Délcaration des objets métiers
		Author auteur = new Author();
		Book gameBook = new Book();
		LibraryBook libraryBook = new LibraryBook();
		
		//J'établis la connexion avec la BDD
		SqlBookStorageFactory factory = new SqlBookStorageFactory(
				"com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://192.168.128.13:3306/in21b10054?useSSL=false&Timezone=UTC",
                "in21b10054",
                "0054"
        );
		
		GameBookRepositoryDb bookStorage = factory.newStorageSession(libraryBook);
		
		
		
		//Déclarations des présentateurs
		/* Je déclare ceux-ci avant car j'en ai besoin dans mon LoginPresenter */
		EditorBooksPresenter editorBookPresenter = new EditorBooksPresenter(editorsBooksView, gameBook,bookStorage, libraryBook);
		AddBookPresenter addBookPresenter = new AddBookPresenter(addBookView, gameBook, bookStorage, editorBookPresenter);
		List<PresenterInterface> allPresenters = new ArrayList<PresenterInterface>();
		allPresenters.add(new LoginPresenter(mainView.getView(0), editorBookPresenter, auteur, bookStorage, addBookPresenter));
		allPresenters.add(addBookPresenter);
		allPresenters.add(editorBookPresenter);
		MainPresenter mainPresenter = new MainPresenter(allPresenters);
		//Déclaration des vues
		Parent root = mainView.getRoot();
		Scene scene = new Scene(root, 1200, 700);
		//Récupérer le css
		scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
		primaryStage.setTitle("AI 2022-2023 : De Vlegelaer Edwin");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
