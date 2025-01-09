package main;

import database.TableGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    public Pane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Text text=new Text("Witaj w szablonie aplikacji sklepowej");

        text.setWrappingWidth(600);
        text.prefHeight(50);
        text.setLayoutX(100);
        text.setLayoutY(125);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(new Font("Arial", 32));
        Button register=createButton(100,250,"Zarejestruj");
        Button log=createButton(500,250,"Zaloguj");
        Button init=createButton(300,450,"Zainicjuj bazę");
        init.setOnAction(event -> {
            TableGenerator.generateUserTable();
            TableGenerator.createCardTable();
            TableGenerator.createUsersCardsTable();
        });
        mainPane.getChildren().addAll(text,register,log,init);

    }
    private Button createButton(double width, double height, String text) {
        Button button = new Button(text);
        button.setLayoutX(width);
        button.setLayoutY(height);
        button.getStyleClass().add("interfaceButton");
        return button;

    }
}