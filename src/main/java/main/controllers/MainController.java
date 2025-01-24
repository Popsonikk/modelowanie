package main.controllers;

import main.bussinessLogic.SQLFacade;
import main.controllers.templates.InterfaceItems;
import main.database.SQLCommands;
import main.database.SQLiteConnector;
import main.database.TableGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Stage mainStage;
    private Scene registerScene;
    private Scene loginScene;


    @FXML
    public Pane mainPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Text text= InterfaceItems.createText("Witaj w szablonie aplikacji sklepowej",600,50,100,125,32);
        Button register=InterfaceItems.createButton("Zarejestruj",100,250,"interfaceButton");
        Button log=InterfaceItems.createButton("Zaloguj",500,250,"interfaceButton");
        Button init=InterfaceItems.createButton("Zainicjuj bazę",300,400,"interfaceButton");
        init.setOnAction(event -> {
            SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
            logic.generateTables();
        });
        register.setOnAction(event -> {
            mainStage.setScene(registerScene);
        });
        log.setOnAction(event -> {mainStage.setScene(loginScene);});
        mainPane.getChildren().addAll(text,register,log,init);
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
    public void setRegisterScene(Scene registerScene) {
        this.registerScene = registerScene;
    }
    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }
}