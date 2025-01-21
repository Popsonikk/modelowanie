package main.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.bussinessLogic.UserLogic;
import main.database.SQLCommands;
import main.database.SQLiteConnector;


import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController extends RegisterItems implements Initializable {

    private boolean isNickValid = false;
    private boolean isPasswordValid = false;
    private boolean isApplyPassValid = false;

    @FXML
    public Pane mainPane;

    protected Stage mainStage;
    protected Scene mainScene;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Text text=createText("Zarejestruj się",600,50,100,75,32);
        text.setFill(Paint.valueOf("#006400"));
        HBox usernameBox=createHBox(150,"Podaj nickname:");
        HBox passBox=createHBox(250,"Podaj hasło:");
        HBox appBox=createHBox(350,"Potwierdź hasło:");

        Text nickMess=createText("",300,30,250,242,16);
        Text passMess=createText("",300,30,250,342,16);
        Text applyMess=createText("",300,30,250,442,16);

        TextField usernameField=new TextField();
        TextField passField=new PasswordField();
        TextField appField=new PasswordField();

        usernameField.getStyleClass().add("registerField");
        appField.getStyleClass().add("registerField");
        passField.getStyleClass().add("registerField");

        usernameBox.getChildren().add(usernameField);
        passBox.getChildren().add(passField);
        appBox.getChildren().add(appField);

        Button registerButton=new Button("Zarejestruj");
        registerButton.getStyleClass().add("interfaceButton");
        registerButton.setLayoutY(475);
        registerButton.setLayoutX(300);

        mainPane.getChildren().addAll(text,usernameBox,passBox,appBox,registerButton,applyMess,passMess,nickMess);

        registerButton.setDisable(true);

        usernameField.textProperty().addListener((observable,oldValue,newValue )-> {
            if(newValue.isEmpty())
            {
                nickMess.setText("Pole tekstowe jest puste");
                usernameField.setStyle("-fx-text-box-border: red;");
                isNickValid=false;
                registerButton.setDisable(true);
            } else if (newValue.length()<4) {
                nickMess.setText("Pole tekstowe jest za krótkie");
                usernameField.setStyle("-fx-text-box-border: red;");
                isNickValid=false;
                registerButton.setDisable(true);
            }
            else
            {
                nickMess.setText("Nickname poprawny");
                usernameField.setStyle("-fx-text-box-border: green;");
                isNickValid=true;
                registerButton.setDisable(!isApplyPassValid || !isPasswordValid);
            }

        });
        passField.textProperty().addListener((observable,oldValue,newValue )-> {
            if(newValue.isEmpty())
            {
                passMess.setText("Pole tekstowe jest puste");
                passField.setStyle("-fx-text-box-border: red;");
                isPasswordValid=false;
                registerButton.setDisable(true);
            } else if (newValue.length()<8) {
                passMess.setText("Pole tekstowe jest za krótkie");
                passField.setStyle("-fx-text-box-border: red;");
                isPasswordValid=false;
                registerButton.setDisable(true);
            }
            else if(!newValue.matches(".*[A-Z].*"))
            {
                passMess.setText("Brak dużej litery");
                passField.setStyle("-fx-text-box-border: green;");
                isPasswordValid=false;
                registerButton.setDisable(true);
            }
            else if(!newValue.matches(".*\\d.*"))
            {
                passMess.setText("Brak cyfry");
                passField.setStyle("-fx-text-box-border: green;");
                isPasswordValid=false;
                registerButton.setDisable(true);
            }
            else
            {
                passMess.setText("Hasło poprawny");
                passField.setStyle("-fx-text-box-border: green;");
                isPasswordValid=true;
                registerButton.setDisable(!isNickValid || !isApplyPassValid);
            }
        });
        appField.textProperty().addListener((observable,oldValue,newValue )-> {
            if(newValue.isEmpty())
            {
                applyMess.setText("Pole tekstowe jest puste");
                appField.setStyle("-fx-text-box-border: red;");
                isApplyPassValid=false;
                registerButton.setDisable(true);
            } else if (!newValue.equals(passField.getText())) {
                applyMess.setText("Podane hasła się różnią");
                appField.setStyle("-fx-text-box-border: red;");
                isApplyPassValid=false;
                registerButton.setDisable(true);
            }
            else
            {
                applyMess.setText("Hasła są zgodne");
                appField.setStyle("-fx-text-box-border: green;");
                isApplyPassValid=true;
                registerButton.setDisable(!isNickValid || !isPasswordValid);
            }
        });
        registerButton.setOnAction(event -> {
            UserLogic logic=new UserLogic(new SQLCommands(new SQLiteConnector()));
            logic.addAccount(usernameField.getText(),passField.getText());
            usernameField.clear();
            passField.clear();
            appField.clear();
            applyMess.setText("");
            passMess.setText("");
            nickMess.setText("");
            mainStage.setScene(mainScene);
        });

    }







}
