package main.controllers.employee;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.bussinessLogic.SQLFacade;
import main.controllers.templates.InsideController;
import main.controllers.templates.InterfaceItems;
import main.database.SQLCommands;
import main.database.SQLiteConnector;
import main.models.User;

import java.util.List;

public class CardController extends InsideController {
    private List<User> userList;
    private VBox vBox;

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }


    @Override
    protected Pane createScenePane() {
        Pane pane=getPane();
        ScrollPane canvas=createScrollPane(790,10);
        vBox=getBox(canvas,790);
        Button back= InterfaceItems.createButton("Powrót",300,5,"orderButton");
        back.setOnAction(e->{
            mainStage.setScene(selfScene);
        });
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
            HBox b=getCanvasBox();
            Button button=InterfaceItems.createButton("Dodaj",50,30,"orderButton");
            button.setOnAction(e->{
                SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
                logic.addCard(user.getName());
                user.setActive(true);
                createView();
            });
            b.getChildren().addAll(createColumnCeil(600,user.getName()),button);
            vBox.getChildren().add(b);
        }


    }

}
