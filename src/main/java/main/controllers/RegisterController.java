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
import main.database.SQLCommands;


import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private boolean isNickValid = false;
    private boolean isPasswordValid = false;
    private boolean isApplyPassValid = false;

    @FXML
    public Pane mainPane;

    private Stage mainStage;
    private Scene mainScene;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Text text=new Text("Zarejestruj się");
        text.setWrappingWidth(600);
        text.prefHeight(50);
        text.setLayoutX(100);
        text.setLayoutY(75);
        text.setFill(Paint.valueOf("#006400"));
        text.setFont(new Font(32.0));
        text.setTextAlignment(TextAlignment.CENTER);
        HBox usernameBox=createHBox(150,"Podaj nickname:");
        HBox passBox=createHBox(250,"Podaj hasło:");
        HBox appBox=createHBox(350,"Potwierdź hasło:");

        Text nickMess=createText(230);
        Text passMess=createText(330);
        Text applyMess=createText(430);

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
            SQLCommands.addAccount(usernameField.getText(),passField.getText());
            usernameField.clear();
            passField.clear();
            appField.clear();
            applyMess.setText("");
            passMess.setText("");
            nickMess.setText("");
            mainStage.setScene(mainScene);
        });

    }
    private HBox createHBox(double y,String Text) {
        HBox hBox=new HBox();
        hBox.setLayoutX(100);
        hBox.setLayoutY(y);
        hBox.getStyleClass().add("registerBox");
        Text text=new Text(Text);
        text.setWrappingWidth(200);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(new Font(24));
        hBox.getChildren().addAll(text);
        return hBox;

    }
    private Text createText(double y)
    {
        Text text=new Text();
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(300);
        text.setLayoutX(250);
        text.setLayoutY(y);
        text.prefHeight(30);
        text.setFont(new Font(16));
        return text;
    }


    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }


}
