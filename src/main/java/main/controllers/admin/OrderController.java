package main.controllers.admin;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.bussinessLogic.SQLFacade;
import main.controllers.templates.InsideController;
import main.controllers.templates.InterfaceItems;
import main.database.SQLCommands;
import main.database.SQLiteConnector;
import main.models.Item;
import java.util.ArrayList;
import java.util.List;


public class OrderController extends InsideController {
    private List<Item> items;
    private VBox vBox;
    @Override
    protected Pane createScenePane() {
        Pane pane=getPane();
        ScrollPane scrollPane = createScrollPane(780,10);
        items=new ArrayList<>();
        vBox = getBox(scrollPane,780);
        HBox hbox=createBox(10,10,0,50);
        TextField item=new TextField();
        item.getStyleClass().add("orderField");
        Button addToOrder= InterfaceItems.createButton("Dodaj",0,0,"orderButton");
        addToOrder.setOnAction(event -> {
            Item i=new Item(item.getText().split(",")[0].trim(),Integer.parseInt(item.getText().split(",")[1].trim()));
            items.add(i);
            createView();
            item.clear();

        });
        Button save=InterfaceItems.createButton("Zapisz",0,0,"orderButton");
        save.setOnAction(event -> {
            SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
            logic.saveOrder(items,item.getText());
            item.clear();
        });
        Button load=InterfaceItems.createButton("Wczytaj",0,0,"orderButton");
        load.setOnAction(e -> {
            items.clear();
            vBox.getChildren().clear();
            SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
            items=new ArrayList<>(logic.getOrder(item.getText()));
            for(Item i:items)
                vBox.getChildren().add(createShowBox(i));
            item.clear();
        });
        Button order=InterfaceItems.createButton("Wykonaj",0,0,"orderButton");;
        order.setOnAction(event -> {
            SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
            logic.doOrder(items);
            vBox.getChildren().clear();
            items.clear();
            mainStage.setScene(selfScene);
        });
        HBox options=createBox(395,10,10,50);
        options.getChildren().addAll(save,load,order);
        hbox.getChildren().addAll(item,addToOrder);
        pane.getChildren().addAll(hbox,scrollPane,options);
        return pane;
    }
    private HBox createBox(int x, int y,int spacing,int height) {
        HBox box=new HBox();
        box.setSpacing(spacing);
        box.setLayoutX(x);
        box.setLayoutY(y);
        box.setPrefHeight(height);
        return box;
    }

    @Override
    public void createView() {
        vBox.getChildren().clear();
        for(Item i:items) {
            HBox hbox=createShowBox(i);
            vBox.getChildren().add(hbox);
        }
    }
    protected HBox createShowBox(Item text) {
        HBox box=getCanvasBox();
        Button b=InterfaceItems.createButton("X",50,30,"deleteButton");
        b.setOnAction(event -> {
            vBox.getChildren().remove(box);
            items.remove(text);
        });
        box.getChildren().addAll(createColumnCeil(350,text.getName()),createColumnCeil(350,String.valueOf(text.getNumber())),b);
        return box;
    }
}
