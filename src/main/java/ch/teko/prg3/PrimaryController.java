package ch.teko.prg3;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.format.DateTimeFormatter;


public class PrimaryController {

    private ObservableList<Todo> todos = FXCollections.observableArrayList();
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @FXML
    public void initialize() {
        System.out.println("init");
        Todo todo = new Todo("init", "init");
        todos.add(todo);
        this.columnDescription.setCellValueFactory(new PropertyValueFactory<Todo, String>("description"));
        this.columnUntil.setCellValueFactory(new PropertyValueFactory<Todo, String>("until"));
        table.setItems(getTodos());
    }

    @FXML
    private Button button1;

    @FXML
    private Button clearButton;

    @FXML
    private TextField inputField1;

    @FXML
    private DatePicker datepickerField;

    @FXML
    private TableView<Todo> table;

    @FXML
    private TableColumn<Todo, String> columnDescription;

    @FXML
    private TableColumn<Todo, String> columnUntil;

    @FXML
    void onClick(ActionEvent event) {
        try {
            System.out.println(inputField1.getText());
            Todo todo = new Todo();
            todo.setDescription(inputField1.getText());
            todo.setUntil(datepickerField.getValue().format(this.formatter));
            todos.add(todo);
        } catch (NullPointerException e) {
            String message = "Unknown error";
            if (inputField1.getText().equals("")) {
                message = "No Input message";
            } else if (datepickerField.getValue() == null) {
                message = "no date selected";
            }
            Alert alert = new Alert(Alert.AlertType.WARNING, message);
            alert.show();
        } finally {
            System.out.println("LOL");
        }
    }

    @FXML
    void clearAction(ActionEvent event) {
        this.todos.clear();
    }

    public ObservableList<Todo> getTodos() {
        return this.todos;
    }

}

