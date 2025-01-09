package main.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.database.SQLCommands;
import main.models.User;

import java.net.URL;
import java.util.Objects;

public abstract  class LoggedWindow extends RegisterItems {
    @FXML
    public Pane mainPane;
    private User loggedUser;
    private Text loggedUserName;
    private Stage mainStage;
    private Scene mainScene;
    private Scene selfScene;

    public void setSelfScene(Scene selfScene) {
        this.selfScene = selfScene;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void init()
    {
        loggedUser=null;
        loggedUserName=new Text();
        loggedUserName.setWrappingWidth(400);
        loggedUserName.prefHeight(50);
        loggedUserName.setLayoutX(200);
        loggedUserName.setLayoutY(525);
        loggedUserName.setTextAlignment(TextAlignment.CENTER);
        loggedUserName.setFont(new Font("Arial", 24));
        Button buttonLogOut=new Button("Wyloguj");
        buttonLogOut.getStyleClass().add("interfaceButton");
        buttonLogOut.setLayoutX(25);
        buttonLogOut.setLayoutY(50);
        buttonLogOut.setOnAction(e -> {
            loggedUser=null;
            loggedUserName.setText("");
            mainStage.setScene(mainScene);
        });
        mainPane.getChildren().addAll(loggedUserName,buttonLogOut);

    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
        loggedUserName.setText("Zalogowany jako: "+loggedUser.getName());
    }
    protected Button createAccountButton()
    {
        Button button=new Button("Stwórz konto");
        button.getStyleClass().add("interfaceButton");
        button.setLayoutX(575);
        button.setLayoutY(50);
        Pane pane=createEmployeeRegisterPane();
        Scene scene=new Scene(pane,800,600);
        button.setOnAction(e -> {mainStage.setScene(scene); });

        return button;
    }
    private Pane createEmployeeRegisterPane()
    {
        Pane pane=new Pane();
        URL cssResource = getClass().getResource("/main/style.css");
        pane.getStylesheets().add(cssResource.toExternalForm());
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
