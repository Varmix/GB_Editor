package org.helmo.gbeditor.infrastructures.dataBase;

public class UnableToSetupException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnableToSetupException(Exception ex) {
        super(ex);
    }
}
