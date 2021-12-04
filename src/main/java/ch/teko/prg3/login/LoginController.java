package ch.teko.prg3.login;

import ch.teko.prg3.dbUtil.Database;
import ch.teko.prg3.model.PopupWindow;
import ch.teko.prg3.model.ToolTipWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label dbStatusLabel;

    @FXML
    private Circle dbStatusLight;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordTextField;

    // Datenbank Objekt
    Database database = new Database();

    // Login Model
    LoginModel loginModel = new LoginModel();

    // Login Button klickt
    @FXML
    void loginButtonAction(ActionEvent event) {
        String uString = usernameTextField.getText();
        String pString = passwordTextField.getText();
        Connection connection = database.getConnection();

        try {

            if (loginModel.isLogin(uString, pString, connection)) {
                System.out.println("Einloggen erfolgreich");
                PopupWindow.display("Login erfolgreich");

                // Das Login Fenster schließen
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();

                // Öffne das neue Fenster
                succusLogin();

            } else {
                System.out.println("Login fehlgeschlagen");
                PopupWindow.display("Login fehlgeschlagen \n Username oder Passwort falsch");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Überprüft ob sich im username und passwort-TextField Daten befinden
    @FXML
    void keyReleasedProperty(KeyEvent event) {
        String uString = usernameTextField.getText();
        String pString = passwordTextField.getText();

        boolean isDisabled = (uString.isEmpty() || uString.trim().isEmpty() || uString.length() < 4)
                || (pString.isEmpty() || pString.trim().isEmpty() || pString.length() < 4);
        loginButton.setDisable(isDisabled);
    }

    // Username und Password-TextField - Tooltip erstellen
    @FXML
    void enteredMouseAction(MouseEvent event) {

        TextField textField = (TextField) event.getSource();
        String promtText = textField.getPromptText();

        if (promtText.equals("username")) {
            usernameTextField.setTooltip(ToolTipWindow.createToolTip("Username mind. 4 Zeichen"));
        } else if (promtText.equals("passwort")) {
            passwordTextField.setTooltip(ToolTipWindow.createToolTip("Passwort mind. 4 Zeichen"));
        }

    }


    public void succusLogin() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();

            Pane root = (Pane) fxmlLoader.load(getClass().getResource("kontakt.fxml").openStream());
            // Pane ist die Basisklasse von alle Layoutklassen wie z.B. AnchorPane, Gridpane etc.
            // Die Punkte im Paketnamen sind Verzeichnisse

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Dashboard");
            stage.setResizable(false);
            stage.show();

            // Datenbank Verbindung schließen
            database.getConnection().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Initializes the controller class = Wird als erstes aufgerufen
     *
     * Dort können z.B. Button versteckt werden oder nicht aktiv geschaltet werden
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginButton.setDisable(true);

        boolean dbConnection = database.open();
        System.out.println(dbConnection);

        if (dbConnection) {
            dbStatusLabel.setText("OK");
            dbStatusLight.setFill(Color.GREEN);
            PopupWindow.display("Verbindung zur Datenbank aufgebaut");
        } else {
            dbStatusLabel.setText("ERROR");
            dbStatusLight.setFill(Color.RED);
            PopupWindow.display("Keine Verbindung zur Datenbank aufgebaut");
        }
    }


}

