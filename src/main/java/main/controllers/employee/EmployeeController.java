package main.controllers.employee;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.bussinessLogic.SQLFacade;
import main.controllers.admin.StorageController;
import main.controllers.client.ShoppingController;
import main.controllers.templates.InterfaceItems;
import main.controllers.templates.UserTemplateController;
import main.database.SQLCommands;
import main.database.SQLiteConnector;
import main.models.User;

public class EmployeeController extends UserTemplateController {
    private CardController cardController;
    private ShoppingController shoppingController;
    private StorageController adminStorageController;



    @Override
    public void setUpControllers(Stage stage, Scene scene) {
        cardController = new CardController();
        cardController.setMainStage(stage);
        cardController.setSelfScene(scene);
        shoppingController = new ShoppingController();
        shoppingController.setMainStage(stage);
        shoppingController.setSelfScene(scene);
        adminStorageController = new StorageController();
        adminStorageController.setMainStage(stage);
        adminStorageController.setSelfScene(scene);
    }

    @Override
    public void startInit() {
        init();
        mainPane.getChildren().addAll(createAddCardButton(),createShopButton(),createStorageButton());
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
    private Button createShopButton()
    {
        Button shopButton = InterfaceItems.createButton("Zrób zakupy",550,50,"interfaceButton");
        Scene scene=new Scene(shoppingController.createScenePane(),800,600);
        shopButton.setOnAction(event -> {
            SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
            shoppingController.setStorageItems(logic.getItems());
            shoppingController.createView();
            mainStage.setScene(scene);
        });
        return shopButton;
    }
    public void setUserForContorller(User user) {
        shoppingController.setLoggedUser(user);
        adminStorageController.setUser(user);
    }
    private Button createStorageButton()
    {
        Button orderButton = InterfaceItems.createButton("Zobacz magazyn",300,250,"interfaceButton");
        Scene scene=new Scene(adminStorageController.createScenePane(),800,600);
        orderButton.setOnAction(event -> {
            SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
            adminStorageController.setStorageItems(logic.getItems());
            adminStorageController.createView();
            mainStage.setScene(scene);
        });
        return orderButton;
    }

}
