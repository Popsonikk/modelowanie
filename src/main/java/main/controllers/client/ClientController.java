package main.controllers.client;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import main.bussinessLogic.SQLFacade;
import main.controllers.templates.InterfaceItems;
import main.controllers.templates.UserTemplateController;
import main.database.SQLCommands;
import main.database.SQLiteConnector;
import main.models.User;

public class ClientController extends UserTemplateController {
    private ShoppingController shoppingController;
    private User user;

    private Button createAddMoneyButton() {
        Button addMoneyButton = InterfaceItems.createButton("Zasil konto",300,50,"interfaceButton");
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

    @Override
    public void setUpControllers(Stage stage, Scene scene) {
        shoppingController=new ShoppingController();
        shoppingController.setMainStage(stage);
        shoppingController.setSelfScene(scene);
    }

    @Override
    public void startInit() {
        init();
        mainPane.getChildren().addAll(createAddMoneyButton(),createShopButton());
    }

    public void setUserForContorller(User user) {
        this.user=user;
        shoppingController.setLoggedUser(user);
    }

}
