package ch.teko.prg3.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopupWindow {

	public static void display(String message) {
		Stage stage = new Stage();
		stage.setTitle("Info!");

		Label label = new Label(message);
		Button button = new Button("OK");

		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}
		});
		
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(label,button);
		vBox.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(vBox, 300, 150);
		
		stage.setScene(scene);
		stage.showAndWait();
	}

}
