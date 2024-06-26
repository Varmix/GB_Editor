package org.helmo.gbeditor.infrastructures.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.helmo.gbeditor.models.Book;
import org.helmo.gbeditor.presenters.BookViewModel;

public class BookMapper {
	
	public static List<BookViewModel> convertBooksBookViewModel(Collection<Book> books) {
		List<BookViewModel> allBooks = new ArrayList<BookViewModel>();
		
		for (Book book : books) {
			allBooks.add(convertBookToBookViewModel(book));
		}
		return allBooks;
	}
	
	public static Book convertBookViewModelToBook(BookViewModel bookVM) {
		return new Book(bookVM.getAuthor(), bookVM.getTitle(), bookVM.getBookIsbn(), bookVM.getSummary());
	}
	
	public static BookViewModel convertBookToBookViewModel(Book book) {
		return new BookViewModel(book);
	}

}
