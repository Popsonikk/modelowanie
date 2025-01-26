package main.controllers.users;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.bussinessLogic.SQLFacade;
import main.controllers.admin.AdminController;
import main.controllers.client.ClientController;
import main.controllers.employee.EmployeeController;
import main.controllers.templates.InterfaceItems;
import main.database.SQLCommands;
import main.database.SQLiteConnector;
import main.models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public Pane mainPane;
    private AdminController adminController;
    private EmployeeController employeeController;
    private ClientController clientController;

    private Scene adminScene;
    private Scene employeeScene;
    private Scene clientScene;

    protected Stage mainStage;
    protected Scene mainScene;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

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

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public void setAdminScene(Scene adminScene) {
        this.adminScene = adminScene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Text text= InterfaceItems.createText("Zaloguj się",600,50,100,75,32);
        text.setFill(Paint.valueOf("#006400"));

        HBox usernameBox=InterfaceItems.createHBox(200,"Podaj nickname:");
        HBox passBox=InterfaceItems.createHBox(350,"Podaj hasło:");

        TextField usernameField=new TextField();
        TextField passField=new PasswordField();

        usernameField.getStyleClass().add("registerField");
        passField.getStyleClass().add("registerField");

        usernameBox.getChildren().add(usernameField);
        passBox.getChildren().add(passField);

        Button loginButton=InterfaceItems.createButton("Zaloguj",300,500,"interfaceButton");
        Text message=InterfaceItems.createText("",300,30,250,450,16);

        loginButton.setOnAction(e->{
            SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
            User user=logic.getAccount(usernameField.getText(),passField.getText());
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
                        clientController.setUserForContorller(user);
                        mainStage.setScene(clientScene);
                        break;
                    }
                    case 1:{
                        employeeController.setLoggedUser(user);
                        employeeController.setUserForContorller(user);
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
}
