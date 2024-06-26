package org.helmo.gbeditor.infrastructures.dataBase;

public class JdbcDriverNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JdbcDriverNotFoundException(String driverName) {
        super("Unable to load driver "+driverName+". Is it available from the class path?");
    }
}
