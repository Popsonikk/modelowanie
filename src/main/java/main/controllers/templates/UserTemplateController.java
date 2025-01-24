package main.controllers.templates;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.models.User;

public abstract  class UserTemplateController {
    @FXML
    public Pane mainPane;
    protected Text loggedUserName;
    protected Stage mainStage;
    protected Scene mainScene;


    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void init()
    {
        loggedUserName=InterfaceItems.createText("",400,50,200,525,24);
        Button buttonLogOut=InterfaceItems.createButton("Wyloguj",25,50,"interfaceButton");
        buttonLogOut.setOnAction(e -> {
            loggedUserName.setText("");
            mainStage.setScene(mainScene);
        });
        mainPane.getChildren().addAll(loggedUserName,buttonLogOut);
    }
    public void setLoggedUser(User loggedUser) {
        loggedUserName.setText("Zalogowany jako: "+loggedUser.getName());
    }
    public abstract void setUpControllers(Stage stage, Scene adminScene);
    public abstract void startInit();

}
