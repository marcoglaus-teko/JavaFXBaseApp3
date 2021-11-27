package ch.teko.prg3;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private Button b3;

    @FXML
    void openBrowser(ActionEvent event) throws IOException {
        Runtime rt = Runtime.getRuntime();
        String url = "https://youtube.com";
        rt.exec("open " + url);
    }
}
