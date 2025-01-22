package main.controllers.templates;

import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class RegisterItems {

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
