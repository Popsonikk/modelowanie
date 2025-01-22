package main.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.models.User;

public abstract  class LoggedWindow extends RegisterItems {
    @FXML
    public Pane mainPane;
    protected User loggedUser;
    protected Text loggedUserName;
    protected Stage mainStage;
    protected Scene mainScene;
    protected Scene selfScene;

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
    protected Button createButton(String text, double x, double y)
    {
        Button button=new Button(text);
        button.getStyleClass().add("interfaceButton");
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }

}
