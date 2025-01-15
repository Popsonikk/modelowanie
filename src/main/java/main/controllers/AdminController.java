package main.controllers;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.bussinessLogic.OrderLogic;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController extends LoggedWindow   {

    private AdminStorageController adminStorageController;
    private AdminRegisterController adminRegisterController;
    private AdminOrderController adminOrderController;
    public void startInit()
    {
        init();
        mainPane.getChildren().addAll(createAccountButton(),createOrderButton(),createStorageButton());
    }

    public void setUpControllers(Stage stage, Scene adminScene) {
        adminStorageController = new AdminStorageController();
        adminStorageController.setMainStage(stage); // Setting mainStage here
        adminStorageController.setSelfScene(adminScene);
        adminOrderController=new AdminOrderController();
        adminOrderController.setMainStage(stage);
        adminOrderController.setSelfScene(adminScene);
        adminRegisterController=new AdminRegisterController();
        adminRegisterController.setMainStage(stage);
        adminRegisterController.setSelfScene(adminScene);


    }

    private Button createOrderButton()
    {

        Button orderButton = new Button("Złóż zamówienie");
        orderButton.getStyleClass().add("interfaceButton");
        orderButton.setLayoutX(300);
        orderButton.setLayoutY(50);
        Scene scene=new Scene(adminOrderController.createScenePane(),800,600);
        orderButton.setOnAction(event -> {mainStage.setScene(scene);});
        return orderButton;

    }

    private Button createStorageButton()
    {


        Button orderButton = new Button("Zobacz magazyn");
        orderButton.getStyleClass().add("interfaceButton");
        orderButton.setLayoutX(300);
        orderButton.setLayoutY(250);
        Scene scene=new Scene(adminStorageController.createScenePane(),800,600);
        orderButton.setOnAction(event -> {
            adminStorageController.setStorageItems(OrderLogic.getItems());
            adminStorageController.createView();
            mainStage.setScene(scene);

        });
        return orderButton;
    }

    protected Button createAccountButton()
    {

        Button button=new Button("Stwórz konto");
        button.getStyleClass().add("interfaceButton");
        button.setLayoutX(575);
        button.setLayoutY(50);
        Scene scene=new Scene(adminRegisterController.createScenePane(),800,600);
        button.setOnAction(e -> {mainStage.setScene(scene); });

        return button;
    }






}
