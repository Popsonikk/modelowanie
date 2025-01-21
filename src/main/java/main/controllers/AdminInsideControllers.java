package main.controllers;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.models.Item;

import java.net.URL;
import java.util.List;

public abstract class AdminInsideControllers extends RegisterItems {


    protected List<Item> items;
    protected VBox vBox;
    protected ScrollPane createScrollPane()
    {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.prefHeight(500);
        scrollPane.prefWidth(780);
        scrollPane.setLayoutX(10);
        scrollPane.setLayoutY(60);
        return scrollPane;
    }
    protected HBox createColumnCeil(double width, String text)
    {
        HBox box=new HBox();
        box.setPrefWidth(width);
        Text t=new Text(text);
        t.setFont(new Font(20));
        t.setFill(Paint.valueOf("#006400"));
        box.getChildren().add(t);
        return box;
    }
    protected Pane getPane()
    {
        Pane pane=new Pane();
        URL cssResource = getClass().getResource("/main/style.css");
        pane.getStylesheets().add(cssResource.toExternalForm());
        return pane;
    }
    protected HBox createShowBox(Item text)
    {
        HBox box=new HBox();
        box.getStyleClass().add("basketBorder");
        Button b=new Button("X");
        b.getStyleClass().add("deleteButton");
        b.setOnAction(event -> {
            vBox.getChildren().remove(box);
            items.remove(text);

        });

        box.getChildren().addAll(createColumnCeil(350,text.getName()),createColumnCeil(350,String.valueOf(text.getNumber())),b);
        return box;
    }
    public void setStorageItems(List<Item> storageItems) {
        this.items = storageItems;
    }
    protected abstract Pane createScenePane();

}
