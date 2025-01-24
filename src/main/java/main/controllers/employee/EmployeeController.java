package main.controllers.employee;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.bussinessLogic.SQLFacade;
import main.controllers.templates.InterfaceItems;
import main.controllers.templates.UserTemplateController;
import main.database.SQLCommands;
import main.database.SQLiteConnector;

public class EmployeeController extends UserTemplateController {
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
        Button b= InterfaceItems.createButton("Dodaj kartę",300,50,"interfaceButton");
        Scene scene=new Scene(cardController.createScenePane(),800,600);
        b.setOnAction(e->{
            SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
            cardController.setUserList(logic.getUsers());
            cardController.createView();
            mainStage.setScene(scene);
        });
        return b;

    }
}
