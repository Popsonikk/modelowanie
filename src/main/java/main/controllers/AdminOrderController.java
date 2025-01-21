package main.controllers;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.bussinessLogic.OrderLogic;
import main.models.Item;
import java.util.ArrayList;
import java.util.List;


public class AdminOrderController extends AdminInsideControllers{

    protected Stage mainStage;
    protected Scene selfScene;


    public void setSelfScene(Scene selfScene) {
        this.selfScene = selfScene;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    protected Pane createScenePane() {
        ScrollPane scrollPane = createScrollPane();
        items=new ArrayList<>();
        vBox = new VBox();
        vBox.setPrefHeight(500);
        vBox.setPrefWidth(780);
        scrollPane.setContent(vBox);
        Pane pane=getPane();
        HBox hbox=new HBox();
        hbox.setLayoutX(10);
        hbox.setLayoutY(10);
        hbox.setPrefHeight(50);
        TextField item=new TextField();
        item.getStyleClass().add("orderField");
        Button addToOrder=new Button("Dodaj ");
        addToOrder.getStyleClass().add("orderButton");
        addToOrder.setOnAction(event -> {
            Item i=new Item(item.getText().split(",")[0].trim(),Integer.parseInt(item.getText().split(",")[1].trim()));
            items.add(i);
            vBox.getChildren().add(createShowBox(i));
            item.clear();

        });
        Button save=new Button("Zapisz ");
        save.getStyleClass().add("orderButton");
        save.setOnAction(event -> {
            OrderLogic.saveOrder(items,item.getText());
            item.clear();
        });

        Button load=new Button("Wczytaj ");
        load.getStyleClass().add("orderButton");
        load.setOnAction(e -> {
            items.clear();
            vBox.getChildren().clear();
            items=new ArrayList<>(OrderLogic.getOrder(item.getText()));
            for(Item i:items)
            {
                vBox.getChildren().add(createShowBox(i));
            }
            item.clear();
        });

        Button order=new Button("Wykonaj ");
        order.getStyleClass().add("orderButton");
        order.setOnAction(event -> {
            OrderLogic.doOrder(items);
            vBox.getChildren().clear();
            items.clear();
            mainStage.setScene(selfScene);
        });

        HBox options=new HBox();
        options.setSpacing(10);
        options.getChildren().addAll(save,load,order);
        options.setLayoutX(395);
        options.setLayoutY(10);
        hbox.getChildren().addAll(item,addToOrder);
        pane.getChildren().addAll(hbox,scrollPane,options);

        return pane;
    }


}
