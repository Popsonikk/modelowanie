package main.controllers;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public abstract class RegisterItems {
    protected Stage mainStage;
    protected Scene mainScene;
    protected HBox createHBox(double y, String Text) {
        HBox hBox=new HBox();
        hBox.setLayoutX(100);
        hBox.setLayoutY(y);
        hBox.getStyleClass().add("registerBox");
        javafx.scene.text.Text text=new Text(Text);
        text.setWrappingWidth(200);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(new Font(24));
        hBox.getChildren().addAll(text);
        return hBox;

    }
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }
    protected Text createText(String text,double width,double height,double x,double y,double font) {
        Text message=new Text(text);
        message.setTextAlignment(TextAlignment.CENTER);
        message.setWrappingWidth(width);
        message.setLayoutX(x);
        message.setLayoutY(y);
        message.prefHeight(height);
        message.setFont(new Font(font));
        return message;
    }
}
