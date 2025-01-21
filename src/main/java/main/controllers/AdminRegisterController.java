package main.controllers;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.database.SQLCommands;

import java.net.URL;

public class AdminRegisterController extends AdminInsideControllers{
    protected Stage mainStage;
    protected Scene selfScene;


    public void setSelfScene(Scene selfScene) {
        this.selfScene = selfScene;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
    @Override
    protected Pane createScenePane() {
        Pane pane=getPane();
        Text text=createText("Zarejestruj pracownika",600,50,100,75,32);
        text.setFill(Paint.valueOf("#006400"));

        HBox usernameBox=createHBox(175,"Podaj nickname:");
        HBox passBox=createHBox(275,"Podaj hasło:");

        TextField usernameField=new TextField();
        TextField passField=new PasswordField();

        usernameField.getStyleClass().add("registerField");
        passField.getStyleClass().add("registerField");

        usernameBox.getChildren().add(usernameField);
        passBox.getChildren().add(passField);

        Button loginButton=new Button("Stwórz konto");
        loginButton.getStyleClass().add("interfaceButton");
        loginButton.setLayoutY(500);
        loginButton.setLayoutX(300);

        ChoiceBox<String> choiceBox=new ChoiceBox<>();
        choiceBox.getItems().addAll("------","Pracownik","Administrator");
        choiceBox.getStyleClass().add("interfaceButton");
        choiceBox.getSelectionModel().selectFirst();
        choiceBox.setLayoutX(300);
        choiceBox.setLayoutY(375);
        pane.getChildren().addAll(text,usernameBox,passBox,choiceBox,loginButton);
        loginButton.setOnAction(e -> {
            int i;
            if(choiceBox.getSelectionModel().getSelectedIndex()==1)
                i=1;
            else if(choiceBox.getSelectionModel().getSelectedIndex()==2)
                i=2;
            else
            {
                System.out.println("Wrong choice");
                return;
            }
            if(usernameField.getText().isEmpty() || passField.getText().isEmpty())
            {
                System.out.println("blank fields");
                return;
            }
            SQLCommands.addAccount(usernameField.getText(),passField.getText(),i);
            usernameField.clear();
            passField.clear();
            choiceBox.getSelectionModel().selectFirst();
            mainStage.setScene(selfScene);
        });
        return pane;
    }


}
