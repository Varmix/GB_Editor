package org.helmo.gbeditor.infrastructures.dataBase;

import java.sql.SQLException;

public class InsertFailedException extends SQLException {

	public InsertFailedException() {
        super("Erreur lors de l'insertion d'un livre");
    }
	
}
