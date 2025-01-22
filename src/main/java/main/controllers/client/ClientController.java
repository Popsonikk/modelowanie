package main.controllers.client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import main.bussinessLogic.UserLogic;
import main.controllers.LoggedWindow;
import main.database.SQLCommands;
import main.database.SQLiteConnector;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController extends LoggedWindow implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        mainPane.getChildren().addAll(createAddMoneyButton());
    }
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

}
