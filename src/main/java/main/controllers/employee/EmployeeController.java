package main.controllers.employee;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controllers.templates.LoggedWindow;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController extends LoggedWindow implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    @Override
    protected void setUpControllers(Stage stage, Scene adminScene) {

    }

    @Override
    protected void startInit() {

    }
}
