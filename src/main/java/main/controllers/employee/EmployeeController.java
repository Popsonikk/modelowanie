package main.controllers.employee;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.bussinessLogic.PurchaseLogic;
import main.controllers.templates.LoggedWindow;
import main.database.SQLCommands;
import main.database.SQLiteConnector;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController extends LoggedWindow  {
    private CardController cardController;


    @Override
    public void setUpControllers(Stage stage, Scene scene) {
        cardController = new CardController();
        cardController.setMainStage(stage);
        cardController.setSelfScene(scene);

    }

    @Override
    public void startInit() {
        init();
        mainPane.getChildren().addAll(createAddCardButton());
    }
    private Button createAddCardButton()
    {
        Button b=createButton("Dodaj kartę",300,50);
        Scene scene=new Scene(cardController.createScenePane(),800,600);
        b.setOnAction(e->{
            PurchaseLogic logic=new PurchaseLogic(new SQLCommands(new SQLiteConnector()));
            cardController.setUserList(logic.getUsers());
            cardController.createView();
            mainStage.setScene(scene);
        });
        return b;

    }
}
