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
import main.bussinessLogic.OrderLogic;
import main.models.Item;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class AdminController extends LoggedWindow implements Initializable  {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        mainPane.getChildren().addAll(createAccountButton(),createOrderButton(),createStorageButton());

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
        AtomicReference<List<Item>> orderedItems = new AtomicReference<>(new ArrayList<>());
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
            Item i=new Item(item.getText().split(",")[0].trim(),Integer.parseInt(item.getText().split(",")[1].trim()));
            orderedItems.get().add(i);
            vBox.getChildren().add(createShowBox(i, orderedItems.get(),vBox));
            item.clear();

        });
        Button save=new Button("Zapisz ");
        save.getStyleClass().add("orderButton");
        save.setOnAction(event -> {
            OrderLogic.saveOrder(orderedItems.get(),item.getText());
            item.clear();
        });

        Button load=new Button("Wczytaj ");
        load.getStyleClass().add("orderButton");
        load.setOnAction(e -> {
            orderedItems.get().clear();
            vBox.getChildren().clear();
            orderedItems.set(OrderLogic.getOrder(item.getText()));
            for(Item i:orderedItems.get())
            {
                vBox.getChildren().add(createShowBox(i, orderedItems.get(),vBox));
            }
            item.clear();
        });

        Button order=new Button("Wykonaj ");
        order.getStyleClass().add("orderButton");
        order.setOnAction(event -> {
            OrderLogic.doOrder(orderedItems.get());
            vBox.getChildren().clear();
            orderedItems.get().clear();
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
    private ScrollPane createScrollPane()
    {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.prefHeight(500);
        scrollPane.prefWidth(780);
        scrollPane.setLayoutX(10);
        scrollPane.setLayoutY(60);
        return scrollPane;
    }
    private HBox createShowBox(Item text, List<Item> orderedItems, VBox vBox)
    {
        HBox box=new HBox();
        box.getStyleClass().add("basketBorder");
        Button b=new Button("X");
        b.getStyleClass().add("deleteButton");
        b.setOnAction(event -> {
            vBox.getChildren().remove(box);
            orderedItems.remove(text);

        });

        box.getChildren().addAll(createColumnCeil(350,text.getName()),createColumnCeil(350,String.valueOf(text.getNumber())),b);
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
    private Button createStorageButton()
    {
        Button orderButton = new Button("Zobacz magazyn");
        orderButton.getStyleClass().add("interfaceButton");
        orderButton.setLayoutX(300);
        orderButton.setLayoutY(250);
        Scene scene=new Scene(createStoragePane(),800,600);
        orderButton.setOnAction(event -> {mainStage.setScene(scene);});
        return orderButton;
    }
    private Pane createStoragePane()
    {
        List<Item> orderedItems = OrderLogic.getItems();
        ScrollPane scrollPane = createScrollPane();
        VBox vBox = new VBox();
        vBox.setPrefHeight(500);
        vBox.setPrefWidth(780);
        scrollPane.setContent(vBox);
        Pane pane=new Pane();
        URL cssResource = getClass().getResource("/main/style.css");
        pane.getStylesheets().add(cssResource.toExternalForm());
        Button back=new Button("Powrót");
        back.getStyleClass().add("orderButton");
        back.setLayoutX(300);
        back.setLayoutY(5);
        back.setOnAction(event -> {mainStage.setScene(selfScene);});

        pane.getChildren().addAll(scrollPane,back);
        createView(orderedItems,vBox);
        return pane;
    }
    private void createView(List<Item> orderedItems,VBox vBox)
    {
        HBox b=new HBox();
        b.getStyleClass().add("basketBorder");
        b.getChildren().addAll(createColumnCeil(300,"Przedmiot"),
                createColumnCeil(200,"Ilość"),
                createColumnCeil(200,"Cena"));
        vBox.getChildren().addAll(b);

        for (Item i : orderedItems)
        {
            HBox box=new HBox();
            box.getStyleClass().add("basketBorder");
            box.getChildren().addAll(createColumnCeil(300,i.getName()),
                    createColumnCeil(200,String.valueOf(i.getNumber())),
                    createColumnCeil(200,String.valueOf(i.getCash())));
            vBox.getChildren().addAll(box);

        }
    }




}
