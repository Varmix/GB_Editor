package org.helmo.gbeditor.infrastructures.Mapper;

import org.helmo.gbeditor.infrastructures.dto.BookDTO;
import org.helmo.gbeditor.models.Book;
/**
 * Cette classe est un mapper qui permet de convertir
 * un Book en BookDTO et inversément. 
 *
 */
public class Mapping {

	/**
	 * Cette méthode permet de convertir un Book
	 * en BookDTO
	 * @param book un objet book
	 * @return un objet BookDTO.
	 */
	public BookDTO toBookDto(Book book) {
		return new BookDTO(book.getAuthor().getFirstName(), book.getAuthor().getLastName(), book.getTitle(), book.getIsbnBook().getIsbn(), book.getSummary());
	}
	
	/**
	 * Cette méthode permet de convertir un BookDtO
	 * en Book
	 * @param bookDTO un objet bookDTO
	 * @return un objet Book.
	 */
//	public Book toBook(BookDTO bookDTO) {
//		return new Book(bookDTO.getFirstName(), bookDTO.getLastName(), bookDTO.getTitle(), bookDTO.getIsbn(), bookDTO.getSummary());
//	}
}
