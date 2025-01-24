package main.controllers.templates;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

public abstract class InsideController {

    protected Stage mainStage;
    protected Scene selfScene;
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setSelfScene(Scene selfScene) {
        this.selfScene = selfScene;
    }

    protected ScrollPane createScrollPane(double width,double x)
    {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.prefHeight(500);
        scrollPane.prefWidth(width);
        scrollPane.setLayoutX(x);
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
        assert cssResource != null;
        pane.getStylesheets().add(cssResource.toExternalForm());
        return pane;
    }
    protected VBox getBox(ScrollPane pane,double width)
    {
        VBox box=new VBox();
        box.setPrefWidth(width);
        box.setPrefHeight(500);
        pane.setContent(box);
        return box;
    }
    protected HBox getCanvasBox()
    {
        HBox box=new HBox();
        box.getStyleClass().add("basketBorder");
        return box;
    }

    protected abstract Pane createScenePane();
    public  abstract  void createView();


}
