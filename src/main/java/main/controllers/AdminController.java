package main.controllers;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController extends LoggedWindow implements Initializable  {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        mainPane.getChildren().addAll(createAccountButton(),createOrderButton());

    }
    private Button createOrderButton()
    {
        Button orderButton = new Button("Złóż zamówienie");
        orderButton.getStyleClass().add("interfaceButton");
        orderButton.setLayoutX(300);
        orderButton.setLayoutY(50);
        Scene scene=new Scene(createOrderPane(),800,600);
        orderButton.setOnAction(event -> {mainStage.setScene(scene);});
        return orderButton;

    }
    private Pane createOrderPane()
    {
        List<String> orderedItems = new ArrayList<>();
        ScrollPane scrollPane = createScrollPane();
        VBox vBox = new VBox();
        vBox.setPrefHeight(500);
        vBox.setPrefWidth(780);
        scrollPane.setContent(vBox);
        Pane pane=new Pane();
        URL cssResource = getClass().getResource("/main/style.css");
        pane.getStylesheets().add(cssResource.toExternalForm());
        HBox hbox=new HBox();
        hbox.setLayoutX(10);
        hbox.setLayoutY(10);
        hbox.setPrefHeight(50);
        TextField item=new TextField();
        item.getStyleClass().add("orderField");
        Button addToOrder=new Button("Dodaj ");
        addToOrder.getStyleClass().add("orderButton");
        addToOrder.setOnAction(event -> {
            orderedItems.add(item.getText());
            vBox.getChildren().add(createShowBox(item.getText(),orderedItems,vBox));
            item.clear();

        });
        Button save=new Button("Zapisz ");
        save.getStyleClass().add("orderButton");

        Button load=new Button("Wczytaj ");
        load.getStyleClass().add("orderButton");

        Button order=new Button("Wykonaj ");
        order.getStyleClass().add("orderButton");
        HBox options=new HBox();
        options.setSpacing(10);
        options.getChildren().addAll(save,load,order);
        options.setLayoutX(395);
        options.setLayoutY(10);
        hbox.getChildren().addAll(item,addToOrder);
        pane.getChildren().addAll(hbox,scrollPane,options);

        return pane;
    }
    private ScrollPane createScrollPane()
    {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.prefHeight(500);
        scrollPane.prefWidth(780);
        scrollPane.setLayoutX(10);
        scrollPane.setLayoutY(60);
        return scrollPane;
    }
    private HBox createShowBox(String text, List<String> orderedItems, VBox vBox)
    {
        HBox box=new HBox();
        box.getStyleClass().add("basketBorder");
        String [] items=text.split(",");
        Button b=new Button("X");
        b.getStyleClass().add("deleteButton");
        b.setOnAction(event -> {
            vBox.getChildren().remove(box);
            orderedItems.remove(text);
        });

        box.getChildren().addAll(createColumnCeil(350,items[0]),createColumnCeil(350,items[1]),b);
        return box;
    }
    private HBox createColumnCeil(double width, String text)
    {
        HBox box=new HBox();
        box.setPrefWidth(width);
        Text t=new Text(text);
        t.setFont(new Font(20));
        t.setFill(Paint.valueOf("#006400"));
        box.getChildren().add(t);
        return box;
    }


}
