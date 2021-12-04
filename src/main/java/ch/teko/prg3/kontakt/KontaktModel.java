package ch.teko.prg3.kontakt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KontaktModel {
	
	private final String TABLE_CONTACT = "kontakte";
	
	private final String QUERY_DATA_FROM_TABLE_CONTACT = "SELECT * FROM " + TABLE_CONTACT;

	// Erstelle Kontakt
	public void createContact(Statement statement, String fString, String sString, String pString, String eString) throws SQLException {
		statement.execute("INSERT INTO " + TABLE_CONTACT + " VALUES " + "('" + fString + "','" + sString + "', '" + pString + "', '" + eString + "')");
		statement.close();
	}
	
	// Lade Kontakte
	public ObservableList<Kontakt> loadContacts(Statement statement, ObservableList<Kontakt> list) throws SQLException {
		
		list = FXCollections.observableArrayList();
		
		ResultSet resultSet = statement.executeQuery(QUERY_DATA_FROM_TABLE_CONTACT);
		
		while(resultSet.next()) {
			String firstName = resultSet.getString(1);
			String secondName = resultSet.getString(2);
			String phoneNumber = resultSet.getString(3);
			String email = resultSet.getString(4);
			
			list.add(new Kontakt(firstName, secondName, phoneNumber, email));
		}
		
		statement.close();
		
		return list;
	}





}
