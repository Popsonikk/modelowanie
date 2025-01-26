package main.controllers.employee;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import main.bussinessLogic.SQLFacade;
import main.controllers.client.ShoppingController;
import main.controllers.templates.InterfaceItems;
import main.controllers.templates.UserTemplateController;
import main.database.SQLCommands;
import main.database.SQLiteConnector;
import main.models.User;

public class EmployeeController extends UserTemplateController {
    private CardController cardController;
    private ShoppingController shoppingController;
    private User user;


    @Override
    public void setUpControllers(Stage stage, Scene scene) {
        cardController = new CardController();
        cardController.setMainStage(stage);
        cardController.setSelfScene(scene);
        shoppingController = new ShoppingController();
        shoppingController.setMainStage(stage);
        shoppingController.setSelfScene(scene);
    }

    @Override
    public void startInit() {
        init();
        mainPane.getChildren().addAll(createAddCardButton(),createShopButton(),createAddMoneyButton());
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
        this.user=user;
        shoppingController.setLoggedUser(user);
    }
    private Button createAddMoneyButton() {
        Button addMoneyButton = InterfaceItems.createButton("Zasil konto",550,250,"interfaceButton");
        addMoneyButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Zasil konto");
            dialog.showAndWait();
            if(dialog.getResult() != null){
                float amount = Float.parseFloat(dialog.getResult());
                user.updateCash(amount);
                shoppingController.setLoggedUser(user);
                SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
                logic.updateMoney(user.getName(), amount);
            }
        });
        return addMoneyButton;
    }
}
