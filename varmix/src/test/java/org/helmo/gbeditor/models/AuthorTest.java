package org.helmo.gbeditor.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthorTest {

private Author author;
	
	@BeforeEach
	void setup() {
		author = new Author("Emile", "Zola", "Q190078");
	}
	
	@Test
	void getFirstName() {
		String firstNameExpected = "Emile";
		assertEquals(firstNameExpected, author.getFirstName());
	}
	
	@Test
	void getLastName() {
		String firstNameExpected = "Zola";
		assertEquals(firstNameExpected, author.getLastName());
	}
	
	@Test
	void firstNameIsEmpty() {
		String firstName = "";
		assertTrue(author.verifyLenghtOfAuthorInputsAreEmpty(firstName, author.getLastName(), author.getMatricule()));
	}
	
	@Test
	void lastNameIsEmpty() {
		String lastName = "";
		assertTrue(author.verifyLenghtOfAuthorInputsAreEmpty(author.getFirstName(), lastName, author.getMatricule()));
	}
	
	@Test
	void firstNameAndLastNameAreEmpty() {
		String firstName = "";
		String lastName = "";
		assertTrue(author.verifyLenghtOfAuthorInputsAreEmpty(firstName, lastName, author.getMatricule()));
	}
	
	@Test
	void firstNameAndLastNameAreNotEmpty() {
		assertFalse(author.verifyLenghtOfAuthorInputsAreEmpty(author.getFirstName(), author.getLastName(), author.getMatricule()));
	}
	
	@Test
	void firstAndLastNameAreNotEmpty() {
		assertFalse(author.verifyLenghtOfAuthorInputsAreEmpty(author.getFirstName(), author.getLastName(), author.getMatricule()));
	}
	
	@Test
	void lengthOfTheFirstNameDoesNotRespectTheMinLength() {
		String firstName = "J";
		assertTrue(author.minimumLenghtOfAFirstName(firstName));
	}
	
	@Test
	void lengthOfTheFirstNameRespectsTheMinLength() {
		String firstName = "Jo";
		assertFalse(author.minimumLenghtOfAFirstName(firstName));
	}
	
	@Test
	void lengthOfTheLastNameDoesNotRespectTheMinLength() {
		String lastName = "H";
		assertTrue(author.minimumLenghtOfALastName(lastName));
	}
	
	@Test
	void lengthOfTheLastNameRespectsTheMinLength() {
		String lastName = "Ha";
		assertFalse(author.minimumLenghtOfALastName(lastName));
	}
	
}
