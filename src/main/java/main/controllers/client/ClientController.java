package main.controllers.client;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import main.bussinessLogic.AdminLogic;
import main.bussinessLogic.UserLogic;
import main.controllers.templates.LoggedWindow;
import main.database.SQLCommands;
import main.database.SQLiteConnector;
import main.models.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController extends LoggedWindow  {
    private ShoppingController shoppingController;


    private Button createAddMoneyButton() {
        Button addMoneyButton = createButton("Zasil konto",300,50);
        addMoneyButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Zasil konto");
            dialog.showAndWait();
            if(dialog.getResult() != null){
                float amount = Float.parseFloat(dialog.getResult());
                UserLogic logic=new UserLogic(new SQLCommands(new SQLiteConnector()));
                logic.updateMoney(loggedUser.getName(), amount);
            }
        });
        return addMoneyButton;
    }
    private Button createShopButton()
    {
        Button shopButton = createButton("Zrób zakupy",550,250);
        Scene scene=new Scene(shoppingController.createScenePane(),800,600);
        shopButton.setOnAction(event -> {
            AdminLogic logic=new AdminLogic(new SQLCommands(new SQLiteConnector()));
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
        shoppingController.setLoggedUser(user);
    }
}
