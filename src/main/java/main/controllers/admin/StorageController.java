package main.controllers.admin;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.bussinessLogic.AdminLogic;
import main.controllers.templates.InsideController;
import main.database.SQLCommands;
import main.database.SQLiteConnector;
import main.models.Item;

import java.util.ArrayList;

public class StorageController extends InsideController {
    protected Stage mainStage;
    protected Scene selfScene;
    public void setSelfScene(Scene selfScene) {
        this.selfScene = selfScene;
    }
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
    public Pane createScenePane()
    {
        items=new ArrayList<>();
        ScrollPane scrollPane = createScrollPane();
        vBox = new VBox();
        vBox.setPrefHeight(500);
        vBox.setPrefWidth(780);
        scrollPane.setContent(vBox);
        Pane pane=getPane();
        Button back=new Button("Powrót");
        back.getStyleClass().add("orderButton");
        back.setLayoutX(300);
        back.setLayoutY(5);
        back.setOnAction(event -> {mainStage.setScene(selfScene);});
        pane.getChildren().addAll(scrollPane,back);
        createView();
        return pane;
    }
    public void createView()
    {
        vBox.getChildren().clear();
        HBox b=new HBox();
        b.getStyleClass().add("basketBorder");
        b.getChildren().addAll(createColumnCeil(300,"Przedmiot"),
                createColumnCeil(200,"Ilość"),
                createColumnCeil(200,"Cena"));
        vBox.getChildren().addAll(b);
        for (Item i : items)
        {
            HBox box=new HBox();
            box.getStyleClass().add("basketBorder");
            Button editNumber=new Button("N");
            editNumber.getStyleClass().add("editButton");
            editNumber.setOnAction(event -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Podaj liczbę");
                dialog.showAndWait();
                if(dialog.getResult() != null)
                {
                    int number = Integer.parseInt(dialog.getResult());
                    AdminLogic logic=new AdminLogic(new SQLCommands(new SQLiteConnector()));
                    logic.updateItemNumber(i.getName(),number);
                    i.setNumber(number);
                    vBox.getChildren().clear();
                    createView();
                }
            });
            Button editPrice=new Button("C");
            editPrice.getStyleClass().add("editButton");
            editPrice.setOnAction(event -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Podaj liczbę");
                dialog.showAndWait();
                if(dialog.getResult() != null)
                {
                    float price = Float.parseFloat(dialog.getResult());
                    AdminLogic logic=new AdminLogic(new SQLCommands(new SQLiteConnector()));
                    logic.updateItemPrice(i.getName(),price);
                    i.setCash(price);
                    vBox.getChildren().clear();
                    createView();
                }
            });
            box.getChildren().addAll(createColumnCeil(300,i.getName()),
                    createColumnCeil(175,String.valueOf(i.getNumber())),
                    createColumnCeil(175,String.valueOf(i.getCash())),editNumber,editPrice);
            vBox.getChildren().addAll(box);
        }
    }
}
