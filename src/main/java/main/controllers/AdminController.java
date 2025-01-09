package main.controllers;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController extends LoggedWindow implements Initializable  {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        mainPane.getChildren().add(createAccountButton());

    }
}
