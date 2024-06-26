package org.helmo.gbeditor.infrastructures.dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.helmo.gbeditor.models.Author;
import org.helmo.gbeditor.models.Book;
import org.helmo.gbeditor.models.Isbn;
import org.helmo.gbeditor.repositories.GameBookRepositoryDb;

public class SqlBookStorage implements AutoCloseable, GameBookRepositoryDb {
	
	private final Connection connection;
	private LibraryBook libraryBook;

	public SqlBookStorage(Connection con, LibraryBook libraryBook) {
        this.connection = con;
        this.libraryBook = libraryBook;
    }
	
	
	
	/* Author table */
	private static final String CREATE_TABLE_AUTHOR =
			"CREATE TABLE Author(" +
					"idAuthor INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY," +
					"prenom VARCHAR(255) NOT NULL," +
					"nom VARCHAR(255) NOT NULL," +
					"matricule VARCHAR(6) NOT NULL," +
					"UNIQUE(matricule))";
	
	private static final String INSERT_AUTHOR =
			"INSERT INTO Author(prenom, nom, matricule) VALUES (?, ?, ?)";
	
	private static final String DELETE_AUTHOR = "DROP TABLE Author";
	
	private static final String VERIFY_USER_EXISTS = "SELECT * FROM Author WHERE matricule = ?";
	
	private static final String LOAD_AUTHOR_INFORMATION =
			"SELECT idAuthor as idAuthor, prenom as prenom, nom as nom FROM Author WHERE matricule = ?";
	
	/* Book Table */
	private static final String CREATE_TABLE_BOOK =
			"CREATE TABLE Book("
			+ "   idBook INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
			+ "   titre VARCHAR(150) NOT NULL,"
			+ "   isbn VARCHAR(13) NOT NULL,"
			+ "   resume VARCHAR(500) NOT NULL,"
			+ "   estPublie BOOLEAN NOT NULL,"
			+ "   idAuthor INT NOT NULL,"
			+ "   UNIQUE(isbn),"
			+ "   FOREIGN KEY(idAuthor) REFERENCES Author(idAuthor)"
			+ ")";
	private static final String INSERT_BOOK =
			"INSERT INTO Book(titre, isbn, resume, estPublie, idAuthor)  VALUES (?, ?, ?, ?, ?)";
	
	private static final String DELETE_BOOK = "DROP TABLE Book";
	
	private static final String UPDATE_BOOK = "UPDATE Book SET titre = ?, isbn = ?, resume = ? WHERE idBook = ?";
	
	/* Page Table */
	private static final String CREATE_TABLE_PAGE = 
			"CREATE TABLE Page("
			+ "   idPage INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
			+ "   numPage INT NOT NULL,"
			+ "   textePage VARCHAR(550) NOT NULL,"
			+ "   idBook INT NOT NULL,"
			+ "   FOREIGN KEY(idBook) REFERENCES Book(idBook)"
			+ ")";
	
	private static final String DELETE_PAGE = "DROP TABLE Page";
	
	/* Choice Table */
	private static final String CREATE_TABLE_CHOICE =
			"CREATE TABLE Choice("
			+ "   idChoice INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
			+ "   numPageAction INT NOT NULL,"
			+ "   texteAction VARCHAR(255) NOT NULL,"
			+ "   idPage INT NOT NULL,"
			+ "   FOREIGN KEY(idPage) REFERENCES Page(idPage)"
			+ ")";
	
	private static final String DELETE_CHOICE = "DROP TABLE Choice";
	private static final String LOAD_BASIC_BOOK_INFO = 
			"SELECT b.idBook as idBook, b.titre as titre, b.isbn as isbn, b.resume as resume FROM Book b JOIN Author A on A.idAuthor = b.idAuthor WHERE A.idAuthor = ?";
	
	/**
	 * Crée toutes les tables en Derby
	 */
	public void setup() {
		try(Statement createStatement = connection.createStatement()) {
			createStatement.executeUpdate(CREATE_TABLE_AUTHOR);
			createStatement.executeUpdate(CREATE_TABLE_BOOK);
			createStatement.executeUpdate(CREATE_TABLE_PAGE);
			createStatement.executeUpdate(CREATE_TABLE_CHOICE);
		} catch (SQLException e) {
			throw new UnableToSetupException(e);
		}
	}
	
	/**
	 * Supprimer toutes les tables en Derby
	 */
	 public void tearDown() {
	        try (Statement dropStatement = connection.createStatement()) {
	        	dropStatement.executeUpdate(DELETE_CHOICE);
	        	dropStatement.executeUpdate(DELETE_PAGE);
	        	dropStatement.executeUpdate(DELETE_BOOK);
	            dropStatement.executeUpdate(DELETE_AUTHOR);
	        } catch (SQLException e) {
	            throw new UnableToTearDownException(e);
	        }
	    }
	
	 /**
	  * Permet d'insérer un auteur
	  * @param auteur un auteur (objet)
	  * @throws SQLException
	  */
	public void insertAuthor(Author auteur) throws SQLException {
		try(PreparedStatement insertAuthor = connection.prepareStatement(INSERT_AUTHOR, Statement.RETURN_GENERATED_KEYS)) {
			insertAuthor.setString(1, auteur.getFirstName());
			insertAuthor.setString(2, auteur.getLastName());
			insertAuthor.setString(3, auteur.getMatricule());
			
			insertAuthor.execute();
			
			ResultSet generatedKeys = insertAuthor.getGeneratedKeys();
			while(generatedKeys.next()) {
				auteur.setIdAuthor(generatedKeys.getInt(1));
			}
			System.out.println("ID AUTEUR " + auteur + " " + auteur.getIdAuthor());
			
		}
	
	}
	
	/* INSERT INTO Book(titre, isbn, resume, estPublie, idAuthor)  VALUES (?, ?, ?, ?, ?); */
	public void insertBook(Book book) throws SQLException, InsertFailedException {
		try(PreparedStatement insertBook = connection.prepareStatement(INSERT_BOOK, Statement.RETURN_GENERATED_KEYS)) {
			insertBook.setString(1, book.getTitle());
			System.out.println("book sqlstorage: " + book);
			insertBook.setString(2, book.getIsbnBook().getIsbn());
			insertBook.setString(3, book.getSummary());
			insertBook.setBoolean(4, book.isPublished());
			insertBook.setInt(5, book.getAuthor().getIdAuthor());
			System.out.println("Id auteur" + book.getAuthor() + "relatif au livre : " + book.getAuthor().getIdAuthor());
			
			insertBook.execute();
			/* TODO : catch exception */
			try (ResultSet generatedKeys = insertBook.getGeneratedKeys();) {
				while(generatedKeys.next()) {
					libraryBook.addBookInTheLibrary(book, generatedKeys.getInt(1));
				}
			}
		}
//		} catch (SQLException ex) {
//			throw new InsertFailedException();
//		}
	
	}
	
	/**
	 * Permet de récupérer les informations basiques quant à un livre
	 * @param auteur l'auteur du livre
	 * @throws SQLException
	 */
	public void loadBasicBookInformation(Author auteur) throws SQLException {
		try(PreparedStatement loadBasicBookInfo = connection.prepareStatement(LOAD_BASIC_BOOK_INFO)) {
			loadBasicBookInfo.setInt(1, auteur.getIdAuthor());
			ResultSet listOfBaiscBookInfo = loadBasicBookInfo.executeQuery();
			//ResultSet generatedKeys = loadBasicBookInfo.getGeneratedKeys();
			
			while(listOfBaiscBookInfo.next()) {
				//System.out.println("ID : " + listOfBaiscBookInfo.getGeneratedKeys().getInt(1));
				libraryBook.addBookInTheLibrary(
						new Book
						(auteur,
						listOfBaiscBookInfo.getString("titre"),
						new Isbn(listOfBaiscBookInfo.getString("isbn")),
						listOfBaiscBookInfo.getString("resume")),
						listOfBaiscBookInfo.getInt("idBook"));
			}
		}
	}
	
	/**
	 * return true si l'auteur existe, sinon false
	 * @param authorMatricule le matricule de l'auteur
	 * @throws SQLExcpetion
	 */
	public boolean verifiyIfUserIsInDb(String authorMatricule) throws SQLException {
		try(PreparedStatement verifyUser = connection.prepareStatement(VERIFY_USER_EXISTS)) {
			verifyUser.setString(1, authorMatricule);
			ResultSet rs = verifyUser.executeQuery();
			//int rowCount = rs.last() ? rs.getRow() : 0;
			int rowCount = 0;
			/* Un utilisateur au MAXIMUM DONC IF */
			if(rs.next()) {
				rowCount++;
			}
			if(rowCount > 0) {
				return true;
			}
		}
		return false;
	}
	
	public Author getAuthorInformations(String authorMatricule) throws SQLException {
		try(PreparedStatement authorInfo = connection.prepareStatement(LOAD_AUTHOR_INFORMATION)) {
			authorInfo.setString(1, authorMatricule);
			ResultSet rs = authorInfo.executeQuery();
			/* Un resultat au maximum */
			if(rs.next()) {
				return new Author(rs.getString("prenom"), rs.getString("nom"), authorMatricule, rs.getInt("idAuthor"));
			}
		}
		return null;
	}

	@Override
	public void close() throws Exception {
		try {
            connection.close();
        } catch (SQLException ex) {
            throw new DeconnectionFailedException(ex);
        }
		
	}

	/* UPDATE Book SET titre = ?, isbn = ?, resume = ? WHERE idBook = ?; */
	@Override
	public void updateBook(Book book) throws SQLException {
		try(PreparedStatement updateBook = connection.prepareStatement(UPDATE_BOOK)) {
			updateBook.setString(1, book.getTitle());
			updateBook.setString(2, book.getIsbnBook().getIsbn());
			updateBook.setString(3, book.getSummary());
			updateBook.setInt(4, libraryBook.getASpecificId(book));
			
			updateBook.executeUpdate();
		}
	}



}
