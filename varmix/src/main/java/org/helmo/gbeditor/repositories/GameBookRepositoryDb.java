package org.helmo.gbeditor.repositories;

import java.sql.SQLException;

import org.helmo.gbeditor.infrastructures.dataBase.InsertFailedException;
import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.models.Book;

public interface GameBookRepositoryDb {
	
	void insertBook(Book book) throws InsertFailedException, SQLException;
	
	boolean verifiyIfUserIsInDb(String authorMatricule) throws SQLException;
	
	void insertAuthor(Author auteur) throws SQLException;
	
	public Author getAuthorInformations(String authorMatricule) throws SQLException;
	
	void loadBasicBookInformation(Author auteur) throws SQLException;

	void updateBook(Book book) throws SQLException;

}
