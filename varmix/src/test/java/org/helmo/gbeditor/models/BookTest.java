package org.helmo.gbeditor.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTest {
	
	private Book book;
	
	@BeforeEach
	void setup() {
		book = new Book(new Author("Emile", "Zola", "210054"), "L'Assommoir", "2-070039-01-3", "Livre de Zola");
	}

	@Test
	void getFirstName() {
		String firstNameExpected = "Emile";
		assertEquals(firstNameExpected, book.getAuthor().getFirstName());
	}
	
	@Test
	void getLastName() {
		String lastNameExpected = "Zola";
		assertEquals(lastNameExpected, book.getAuthor().getLastName());
	}
	
	@Test
	void getTitleOfBook() {
		String titleOfBookExpected = "L'Assommoir";
		assertEquals(titleOfBookExpected, book.getTitle());
	}
	
	@Test
	void getIsbnOfBook() {
		String isbnOfBookExpected = "2-070039-01-3";
		assertEquals(isbnOfBookExpected, book.getIsbn());
	}
	
	@Test
	void getSummaryOfBook() {
		String summaryOfBookExpected = "Livre de Zola";
		assertEquals(summaryOfBookExpected, book.getSummary());
	}
	
	@Test
	void validIsbn() {
		String isbnValidExpected = "2-070039-01-3";
		assertTrue(book.checkISBN(isbnValidExpected));
	}
	
	@Test
	void validIsbnWithAnOtherIdVerificationCodeX() {
		String isbnValidExpected = "2-210054-01-X";
		assertTrue(book.checkISBN(isbnValidExpected));
	}
	
	
	@Test
	void NotValidIsbn() {
		String isbnValidExpected = "2-070039-01-2";
		assertFalse(book.checkISBN(isbnValidExpected));
	}
	
	@Test
	void NotValideIsbnWithAnOtherIdVerificationCodeX() {
		String isbnValidExpected = "2-210054-01-3";
		assertFalse(book.checkISBN(isbnValidExpected));
	}
	
	@Test
	void CorrectLengthIsbn() {
		String isbnCorrectLength = "2-070039-01-3";
		assertTrue(book.checkISBN(isbnCorrectLength));
	}
	
	@Test
	void NotCorrectLengthIsbn() {
		String isbnNotCorrectLength = "2-070039-01-";
		assertFalse(book.checkISBN(isbnNotCorrectLength));
	}
	
	@Test
	void LengthOfBookInputsAreNotEmpty() {
		assertTrue(book.verifyLengthOfBookInputsAreNotEmpty(book.getTitle(), book.getIsbn(), book.getSummary()));
	}
	
	@Test
	void OneOfTheInputsInTheBookIsEmpty() {
		assertFalse(book.verifyLengthOfBookInputsAreNotEmpty(book.getTitle(), "", book.getSummary()));
	}
	

}
