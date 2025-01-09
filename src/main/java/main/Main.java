package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controllers.MainController;
import main.controllers.RegisterController;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainWindow.fxml"));
        FXMLLoader registerLoader = new FXMLLoader(Main.class.getResource("registerWindow.fxml"));

        Scene mainScene = new Scene(fxmlLoader.load(), 800, 600);
        Scene registerScene= new Scene(registerLoader.load(), 800, 600);

        MainController mainController = fxmlLoader.getController();
        RegisterController registerController = registerLoader.getController();

        mainController.setMainStage(stage);
        mainController.setRegisterScene(registerScene);

        registerController.setMainScene(mainScene);
        registerController.setMainStage(stage);


        stage.setTitle("Modelowanie biznesowe");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}