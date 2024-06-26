package org.helmo.gbeditor.infrastructures.dataBase;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlBookStorageFactory {
	
	private final String db ;
    private final String username;
    private final String password;

    /*
     * Derby Constructeur
     */
    public SqlBookStorageFactory(String driverName, String db, String username, String pass) {
        try {
        	Class.forName(driverName);
            this.db = db;
            this.username = username;
            this.password = pass;
        } catch(ClassNotFoundException ex) {
        	throw  new JdbcDriverNotFoundException(driverName);
        }
    }
    
    /*
     * MySQL constructeur
     */
//    public SqlBookStorageFactory(String db, String username, String pass) {
//        try {
//        	Class.forName("com.mysql.cj.jdbc.Driver");
//            this.db = db;
//            this.username = username;
//            this.password = pass;
//        } catch(ClassNotFoundException ex) {
//        	throw new JdbcDriverNotFoundException("com.mysql.cj.jdbc.Driver");
//        }
//    }
    
    public SqlBookStorage newStorageSession(LibraryBook libraryBook) {
        try {
            return new SqlBookStorage(DriverManager.getConnection(db, username, password), libraryBook);
        } catch (SQLException e) {
            throw new ConnectionFailedException("Unable to access db" + db, e);
        }
    }

}
