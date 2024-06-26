package org.helmo.gbeditor.infrastructures.Mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.helmo.gbeditor.infrastructures.dto.BookDTO;
import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.models.Book;
import org.junit.jupiter.api.Test;

class MappingTest {

	@Test
	void BookToBookDto() {
		Book book = new Book(new Author("Emile", "Zola", "210054"), "L'Assommoir", "2-070039-01-3", "Livre de Zola");
		Mapping mapping = new Mapping();
		BookDTO bookDTO = mapping.toBookDto(book);
		assertEquals(book.getAuthor().getFirstName(), bookDTO.getFirstName());
		assertEquals(book.getAuthor().getLastName(), bookDTO.getLastName());
		assertEquals(book.getTitle(), bookDTO.getTitle());
		assertEquals(book.getIsbn(), bookDTO.getIsbn());
		assertEquals(book.getSummary(), bookDTO.getSummary());
	}

}
