package main.controllers.admin;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.bussinessLogic.SQLFacade;
import main.controllers.templates.InterfaceItems;
import main.controllers.templates.UserTemplateController;
import main.database.SQLCommands;
import main.database.SQLiteConnector;

public class AdminController extends UserTemplateController {

    private StorageController adminStorageController;
    private CreateEmployeeController adminRegisterController;
    private OrderController adminOrderController;
    public void startInit()
    {
        init();
        mainPane.getChildren().addAll(createAccountButton(),createOrderButton(),createStorageButton());
    }
    public void setUpControllers(Stage stage, Scene adminScene) {
        adminStorageController = new StorageController();
        adminStorageController.setMainStage(stage); // Setting mainStage here
        adminStorageController.setSelfScene(adminScene);
        adminOrderController=new OrderController();
        adminOrderController.setMainStage(stage);
        adminOrderController.setSelfScene(adminScene);
        adminRegisterController=new CreateEmployeeController();
        adminRegisterController.setMainStage(stage);
        adminRegisterController.setSelfScene(adminScene);
    }
    private Button createOrderButton()
    {
        Button orderButton = InterfaceItems.createButton("Złóż zamówienie",300,50,"interfaceButton");
        Scene scene=new Scene(adminOrderController.createScenePane(),800,600);
        orderButton.setOnAction(event -> {mainStage.setScene(scene);});
        return orderButton;
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
    protected Button createAccountButton()
    {
        Button button=InterfaceItems.createButton("Stwórz konto",575,50,"interfaceButton");
        Scene scene=new Scene(adminRegisterController.createScenePane(),800,600);
        button.setOnAction(e -> {mainStage.setScene(scene); });
        return button;
    }
}
