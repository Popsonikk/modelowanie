package main.controllers;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController extends LoggedWindow implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        Text text = new Text("Client");
        text.setLayoutX(25);
        text.setLayoutY(25);
        mainPane.getChildren().add(text);
    }
}
