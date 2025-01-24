package main.controllers.templates;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class InterfaceItems {

    public static HBox createHBox(double y, String Text) {
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
    public static Text createText(String text,double width,double height,double x,double y,double font) {
        Text message=new Text(text);
        message.setFill(Paint.valueOf("#006400"));
        message.setTextAlignment(TextAlignment.CENTER);
        message.setWrappingWidth(width);
        message.setLayoutX(x);
        message.setLayoutY(y);
        message.prefHeight(height);
        message.setFont(new Font(font));
        return message;
    }
    public static Button createButton(String text, double x, double y,String style)
    {
        Button button=new Button(text);
        button.getStyleClass().add(style);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }
}
