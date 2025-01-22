package main.controllers.client;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.controllers.templates.InsideController;
import main.models.Item;
import main.models.User;

import java.util.*;

public class ShoppingController extends InsideController{

    private Stage mainStage;
    private Scene selfScene;
    private List<Item> purchaseList;
    private User loggedUser;
    private Text purchaseText;
    private Text accountValue;

    private VBox purchaseBox;
    public void setSelfScene(Scene selfScene) {
        this.selfScene = selfScene;
    }
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
        accountValue.setText(String.format("%.2f",loggedUser.getCash()));

    }

    @Override
    protected Pane createScenePane() {
        accountValue=createText("0",550,25);
        purchaseText=createText("0",700,25);
        purchaseList = new ArrayList<>();
        vBox = new VBox();
        items=new ArrayList<>();
        Pane pane=getPane();
        ScrollPane shopPane=createScrollPane();
        vBox.setPrefHeight(500);
        vBox.setPrefWidth(390);
        shopPane.setPrefWidth(390);
        shopPane.setContent(vBox);

        ScrollPane purchasePane=createScrollPane();
        purchasePane.setPrefWidth(390);
        purchasePane.setLayoutX(400);
        purchaseBox=new VBox();
        purchaseBox.setPrefHeight(500);
        purchaseBox.setPrefWidth(390);
        purchasePane.setContent(purchaseBox);

        Button back=new Button("Zakup");
        back.getStyleClass().add("orderButton");
        back.setOnAction(e->{

            purchaseList.clear();
            mainStage.setScene(selfScene);

        });
        back.setLayoutX(300);
        back.setLayoutY(5);


        pane.getChildren().addAll(shopPane,purchasePane,back,accountValue,purchaseText);

        createView();
        return pane;
    }
    public void createView()
    {
        vBox.getChildren().clear();
        HBox b=new HBox();
        b.getStyleClass().add("basketBorder");
        b.getChildren().addAll(createColumnCeil(150,"Przedmiot"),
                createColumnCeil(50,"Ilość"),
                createColumnCeil(50,"Cena"));
        vBox.getChildren().addAll(b);
        for (Item i : items)
        {
            if(i.getCash()==0)
                continue;
            HBox box=new HBox();
            box.getStyleClass().add("basketBorder");
            Button addToBasket=new Button("+");
            addToBasket.getStyleClass().add("editButton");
            addToBasket.setOnAction(event -> {
                for(Item purchase:purchaseList)
                {
                    if(Objects.equals(purchase.getName(), i.getName()))
                    {
                        if(purchase.getNumber()==i.getNumber())
                            return;
                        else
                        {
                            purchase.addNumber();
                            updateSum(i.getCash());
                            createPurchaseView();
                            return;
                        }
                    }
                }
                purchaseList.add(new Item(i.getName(), 1,i.getCash()));
                updateSum(i.getCash());
                createPurchaseView();


            });

            box.getChildren().addAll(createColumnCeil(150,i.getName()),
                    createColumnCeil(50,String.valueOf(i.getNumber())),
                    createColumnCeil(50,String.valueOf(i.getCash())),addToBasket);
            vBox.getChildren().addAll(box);
        }
    }
    private void createPurchaseView()
    {
        purchaseBox.getChildren().clear();
        for(Item i:purchaseList)
        {
            if(i.getNumber()==0)
                continue;
            HBox box=new HBox();
            box.getStyleClass().add("basketBorder");
            Button deleteFromBasket=new Button("-");
            deleteFromBasket.getStyleClass().add("editButton");
            deleteFromBasket.setOnAction(event -> {
                i.deleteNumber();
                updateSum(-i.getCash());

            });
            box.getChildren().addAll(createColumnCeil(200,i.getName()),
                    createColumnCeil(75,String.valueOf(i.getNumber())),
                    deleteFromBasket);
            purchaseBox.getChildren().addAll(box);
        }
    }
    private Text createText(String s,double x,double y)
    {
        Text text=new Text(s);
        text.setWrappingWidth(100);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(new Font(16));
        text.prefHeight(50);
        text.setLayoutX(x);
        text.setLayoutY(y);
        return text;
    }
    private void updateSum(float f)
    {
        float sum=Float.parseFloat(purchaseText.getText());
        sum+=f;
        purchaseText.setText(String.format("%.2f",sum));
        createPurchaseView();
    }
}
