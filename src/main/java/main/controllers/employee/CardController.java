package main.controllers.employee;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.bussinessLogic.PurchaseLogic;
import main.controllers.templates.InsideController;
import main.database.SQLCommands;
import main.database.SQLiteConnector;
import main.models.User;

import java.util.List;

public class CardController extends InsideController {
    private List<User> userList;
    private Stage mainStage;
    private Scene selfScene;

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setSelfScene(Scene selfScene) {
        this.selfScene = selfScene;
    }

    @Override
    protected Pane createScenePane() {
        Pane pane=getPane();
        ScrollPane canvas=createScrollPane();
        vBox=new VBox();
        vBox.setPrefHeight(500);
        vBox.setPrefWidth(790);
        canvas.setContent(vBox);
        Button back=new Button("Powrót");
        back.getStyleClass().add("orderButton");
        back.setOnAction(e->{
            mainStage.setScene(selfScene);
        });
        back.setLayoutX(300);
        back.setLayoutY(5);
        pane.getChildren().addAll(canvas,back);
        return pane;
    }
    public  void createView()
    {
        vBox.getChildren().clear();
        for(User user:userList)
        {
            if(user.isActive()||user.getRole()==2)
                continue;
            HBox b=new HBox();
            b.getStyleClass().add("basketBorder");
            Button button=new Button("Dodaj");
            button.getStyleClass().add("orderButton");
            button.setOnAction(e->{
                PurchaseLogic logic=new PurchaseLogic(new SQLCommands(new SQLiteConnector()));
                logic.addCard(user.getName());
                user.setActive(true);
                createView();
            });
            b.getChildren().addAll(createColumnCeil(500,user.getName()),button);
            vBox.getChildren().add(b);
        }


    }

}
