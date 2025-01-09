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
import main.models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController  implements Initializable {
    @FXML
    public Pane mainPane;
    private AdminController adminController;
    private EmployeeController employeeController;
    private ClientController clientController;

    private Stage mainStage;
    private Scene adminScene;
    private Scene employeeScene;
    private Scene clientScene;

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    public void setEmployeeScene(Scene employeeScene) {
        this.employeeScene = employeeScene;
    }

    public void setClientScene(Scene clientScene) {
        this.clientScene = clientScene;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public void setAdminScene(Scene adminScene) {
        this.adminScene = adminScene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Text text=new Text("Zaloguj się");
        text.setWrappingWidth(600);
        text.prefHeight(50);
        text.setLayoutX(100);
        text.setLayoutY(75);
        text.setFill(Paint.valueOf("#006400"));
        text.setFont(new Font(32.0));
        text.setTextAlignment(TextAlignment.CENTER);
        HBox usernameBox=createHBox(200,"Podaj nickname:");
        HBox passBox=createHBox(350,"Podaj hasło:");

        TextField usernameField=new TextField();
        TextField passField=new PasswordField();


        usernameField.getStyleClass().add("registerField");
        passField.getStyleClass().add("registerField");

        usernameBox.getChildren().add(usernameField);
        passBox.getChildren().add(passField);


        Button loginButton=new Button("Zarejestruj");
        loginButton.getStyleClass().add("interfaceButton");
        loginButton.setLayoutY(500);
        loginButton.setLayoutX(300);
        Text message=new Text();
        message.setTextAlignment(TextAlignment.CENTER);
        message.setWrappingWidth(300);
        message.setLayoutX(250);
        message.setLayoutY(450);
        message.prefHeight(30);
        message.setFont(new Font(16));

        loginButton.setOnAction(e->{
            User user=SQLCommands.getAccount(usernameField.getText(),passField.getText());
            if(user==null){
                message.setText("Błędne dane");
                usernameField.clear();
                passField.clear();
            }
            else{
                usernameField.clear();
                passField.clear();
                message.setText("");
                switch (user.getRole())
                {
                    case 0:{
                        clientController.setLoggedUser(user);
                        mainStage.setScene(clientScene);
                        break;
                    }
                    case 1:{
                        employeeController.setLoggedUser(user);
                        mainStage.setScene(employeeScene);
                        break;
                    }
                    case 2:{
                        adminController.setLoggedUser(user);
                        mainStage.setScene(adminScene);
                        break;
                    }
                }

            }
        });

        mainPane.getChildren().addAll(text,usernameBox,passBox,loginButton,message);




    }

    private HBox createHBox(double y, String Text) {
        HBox hBox=new HBox();
        hBox.setLayoutX(100);
        hBox.setLayoutY(y);
        hBox.getStyleClass().add("registerBox");
        javafx.scene.text.Text text=new Text(Text);
        text.setWrappingWidth(200);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(new Font(24));
        hBox.getChildren().addAll(text);
        return hBox;

    }


}
