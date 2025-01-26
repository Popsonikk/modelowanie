package main.controllers.admin;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.bussinessLogic.SQLFacade;
import main.controllers.templates.InsideController;
import main.controllers.templates.InterfaceItems;
import main.database.SQLCommands;
import main.database.SQLiteConnector;

public class CreateEmployeeController extends InsideController {

    @Override
    protected Pane createScenePane() {
        Pane pane=getPane();
        Text text= InterfaceItems.createText("Zarejestruj pracownika",600,50,100,75,32);


        HBox usernameBox=InterfaceItems.createHBox(175,"Podaj nickname:");
        TextField usernameField=new TextField();
        usernameField.getStyleClass().add("registerField");
        usernameBox.getChildren().add(usernameField);

        HBox passBox=InterfaceItems.createHBox(275,"Podaj hasło:");
        TextField passField=new PasswordField();
        passField.getStyleClass().add("registerField");
        passBox.getChildren().add(passField);

        Button loginButton=InterfaceItems.createButton("Stwórz konto",300,500,"interfaceButton");

        ChoiceBox<String> choiceBox=new ChoiceBox<>();
        choiceBox.getItems().addAll("------","Pracownik","Administrator");
        choiceBox.getStyleClass().add("interfaceButton");
        choiceBox.getSelectionModel().selectFirst();
        choiceBox.setLayoutX(300);
        choiceBox.setLayoutY(375);


        loginButton.setOnAction(e -> {
            int i;

            if(usernameField.getText().isEmpty() || passField.getText().isEmpty()) {
                System.out.println("blank fields");
                return;
            }
            SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
            if(choiceBox.getSelectionModel().getSelectedIndex()==1) {
                i=1;
                logic.addAccount(usernameField.getText(),passField.getText(),i);
                logic.addCard(usernameField.getText());
                logic.updateCard(usernameField.getText(),5000);
            }

            else if(choiceBox.getSelectionModel().getSelectedIndex()==2) {
                i = 2;
                logic.addAccount(usernameField.getText(),passField.getText(),i);
            }
            else
            {
                System.out.println("Wrong choice");
                return;
            }



            usernameField.clear();
            passField.clear();
            choiceBox.getSelectionModel().selectFirst();
            mainStage.setScene(selfScene);
        });

        pane.getChildren().addAll(text,usernameBox,passBox,choiceBox,loginButton);
        return pane;
    }

    @Override
    public void createView() {

    }
}
