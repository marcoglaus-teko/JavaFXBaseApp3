package ch.teko.prg3.kontakt;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import ch.teko.prg3.dbUtil.Database;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class KontaktController implements Initializable {

	@FXML
	private TextField firstNameTextField;

	@FXML
	private TextField secondNameTextField;

	@FXML
	private TextField phoneNumberTextField;

	@FXML
	private TextField emailTextField;

	@FXML
	private Button createButton;

	@FXML
	private Button cancelButton;

	@FXML
	private Circle dbStatusLight;

	@FXML
	private TableView<Kontakt> contactsTableView;

	@FXML
	private TableColumn<Kontakt, String> firstnameColumn;

	@FXML
	private TableColumn<Kontakt, String>  secondnameColumn;

	@FXML
	private TableColumn<Kontakt, String>  phonenumberColumn;

	@FXML
	private TableColumn<Kontakt, String>  emailColumn;

	// Variablen
	private Database database = new Database();
	private KontaktModel kontaktModel = new KontaktModel();

	private ObservableList<Kontakt> kontakte;

	@FXML
	void cancelButtonTapped(ActionEvent event) {
		clearAllTextField();
	}
	
	public void clearAllTextField() {
		firstNameTextField.clear();
		secondNameTextField.clear();
		phoneNumberTextField.clear();
		emailTextField.clear();

		cancelButton.setDisable(true);
		createButton.setDisable(true);
	}

	@FXML
	void createButtonTapped(ActionEvent event) {
		createContact();
		loadContacts();
		
		clearAllTextField();
	}

	@FXML
	void keyReleasedProperty(KeyEvent event) {
		String fString = firstNameTextField.getText();
		String sString = secondNameTextField.getText();
		String pString = phoneNumberTextField.getText();
		String eString = emailTextField.getText();

		// trim() schneidet alle Leerzeichen raus z.B. aus "  Hallo   " wird "Hallo"		

		boolean createButtonDisable = (fString.isEmpty() || fString.trim().isEmpty())
				|| (sString.isEmpty() || sString.trim().isEmpty()) || (pString.isEmpty() || pString.trim().isEmpty())
				|| (eString.isEmpty() || eString.trim().isEmpty());

		boolean cancelButtonDisable = (fString.isEmpty() || fString.trim().isEmpty())
				&& (sString.isEmpty() || sString.trim().isEmpty()) && (pString.isEmpty() || pString.trim().isEmpty())
				&& (eString.isEmpty() || eString.trim().isEmpty());

		// 1. Fall Abrechen erscheint sofort sobald Text geschrieben wird
		if (!cancelButtonDisable) {
			cancelButton.setDisable(false);
		} else {
			cancelButton.setDisable(true);
		}

		// 2. Fall Erstellen erscheint nur wenn alle Daten eingegeben sind
		if (!createButtonDisable) {
			createButton.setDisable(false);
		} else {
			createButton.setDisable(true);
		}

	}

	@FXML
	void loadContactFromDatabase(ActionEvent event) {
		loadContacts();
		
		clearAllTextField();
	}

	// Laden der Konakte 
	public void loadContacts() {
		try {
			kontakte = kontaktModel.loadContacts(database.getStatement(), kontakte);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		firstnameColumn.setCellValueFactory(new PropertyValueFactory<Kontakt, String>("firstName"));
		secondnameColumn.setCellValueFactory(new PropertyValueFactory<Kontakt, String>("secondName"));
		phonenumberColumn.setCellValueFactory(new PropertyValueFactory<Kontakt, String>("phoneNumber"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Kontakt, String>("email"));
		
		contactsTableView.setItems(kontakte);
	}
	
	// Erstellen von Kontakten
	public void createContact() {
		String firstname = firstNameTextField.getText();
		String secondname = secondNameTextField.getText();
		String phoneNumber = phoneNumberTextField.getText();
		String email = emailTextField.getText();
		
		try {
			kontaktModel.createContact(database.getStatement(), firstname, secondname, phoneNumber, email);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createButton.setDisable(true);
		cancelButton.setDisable(true);

		boolean dbConnection = database.open();

		if (dbConnection) {
			dbStatusLight.setFill(Color.GREEN);
			
			// Lade Kontakte aus der Tabelle (Datenbank) in die TableView
			loadContacts();
		} else {
			dbStatusLight.setFill(Color.RED);
		}

	}

}
