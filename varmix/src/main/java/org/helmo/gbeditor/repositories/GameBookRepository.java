package org.helmo.gbeditor.repositories;

import org.helmo.gbeditor.models.Book;
/**
 * Interface permettant d'aller lire/sauvegarder
 * des livres
 *
 */
public interface GameBookRepository {
	
	 void save(Book book);

	 void load();
}
