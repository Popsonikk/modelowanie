package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.controllers.*;
import main.controllers.admin.AdminController;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainWindow.fxml"));
        FXMLLoader registerLoader = new FXMLLoader(Main.class.getResource("registerWindow.fxml"));
        FXMLLoader loginLoader = new FXMLLoader(Main.class.getResource("loginWindow.fxml"));
        FXMLLoader adminLoader = new FXMLLoader(Main.class.getResource("adminWindow.fxml"));
        FXMLLoader employeeLoader = new FXMLLoader(Main.class.getResource("employeeWindow.fxml"));
        FXMLLoader clientLoader = new FXMLLoader(Main.class.getResource("clientWindow.fxml"));

        Scene mainScene = new Scene(fxmlLoader.load(), 800, 600);
        Scene registerScene= new Scene(registerLoader.load(), 800, 600);
        Scene loginScene= new Scene(loginLoader.load(), 800, 600);
        Scene adminScene= new Scene(adminLoader.load(), 800, 600);
        Scene employeeScene= new Scene(employeeLoader.load(), 800, 600);
        Scene clientScene= new Scene(clientLoader.load(), 800, 600);

        MainController mainController = fxmlLoader.getController();
        RegisterController registerController = registerLoader.getController();
        LoginController loginController = loginLoader.getController();
        AdminController adminController = adminLoader.getController();
        EmployeeController employeeController = employeeLoader.getController();
        ClientController clientController = clientLoader.getController();

        mainController.setMainStage(stage);
        mainController.setRegisterScene(registerScene);
        mainController.setLoginScene(loginScene);

        registerController.setMainScene(mainScene);
        registerController.setMainStage(stage);

        loginController.setAdminController(adminController);
        loginController.setMainStage(stage);
        loginController.setAdminScene(adminScene);
        loginController.setEmployeeController(employeeController);
        loginController.setClientController(clientController);
        loginController.setEmployeeScene(employeeScene);
        loginController.setClientScene(clientScene);

        adminController.setMainScene(mainScene);
        adminController.setMainStage(stage);
        adminController.setSelfScene(adminScene);
        adminController.setUpControllers(stage,adminScene);
        adminController.startInit();

        employeeController.setMainScene(mainScene);
        employeeController.setMainStage(stage);
        employeeController.setSelfScene(employeeScene);

        clientController.setMainScene(mainScene);
        clientController.setMainStage(stage);
        clientController.setSelfScene(clientScene);


        stage.setTitle("Modelowanie biznesowe");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}