package org.helmo.gbeditor.infrastructures.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookDTOTest {
	
	private BookDTO bookDTO;
	
	@BeforeEach
	void setup() {
		bookDTO = new BookDTO("Emile", "Zola", "L'Assommoir", "2-070039-01-3", "Livre de Zola");
	}

	@Test
	void getFirstName() {
		String firstNameExpected = "Emile";
		assertEquals(firstNameExpected, bookDTO.getFirstName());
	}
	
	@Test
	void getLastName() {
		String lastNameExpected = "Zola";
		assertEquals(lastNameExpected, bookDTO.getLastName());
	}
	
	@Test
	void getTitleOfBook() {
		String titleOfBookExpected = "L'Assommoir";
		assertEquals(titleOfBookExpected, bookDTO.getTitle());
	}
	
	@Test
	void getIsbnOfBook() {
		String isbnOfBookExpected = "2-070039-01-3";
		assertEquals(isbnOfBookExpected, bookDTO.getIsbn());
	}
	
	@Test
	void getSummaryOfBook() {
		String summaryOfBookExpected = "Livre de Zola";
		assertEquals(summaryOfBookExpected, bookDTO.getSummary());
	}

}
