package main.controllers.client;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.bussinessLogic.SQLFacade;
import main.controllers.templates.InsideController;
import main.controllers.templates.InterfaceItems;
import main.database.SQLCommands;
import main.database.SQLiteConnector;
import main.models.Item;
import main.models.User;

import java.util.*;

public class ShoppingController extends InsideController{

    private List<Item> purchaseList;
    private User loggedUser;
    private Text purchaseText;
    private Text accountValue;
    private List<Item> items;
    private VBox vBox;
    private VBox purchaseBox;
    private Button cardPurchase;

    public void setStorageItems(List<Item> storageItems) {
        this.items = storageItems;
    }
    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
        cardPurchase.setDisable(!loggedUser.isActive());
        accountValue.setText(String.format("%.2f",loggedUser.getCash()));
    }
    @Override
    public Pane createScenePane() {
        accountValue= InterfaceItems.createText("0",100,50,550,25,16);
        purchaseText=InterfaceItems.createText("0",100,50,700,25,16);

        purchaseList = new ArrayList<>();
        items=new ArrayList<>();

        Pane pane=getPane();
        ScrollPane shopPane=createScrollPane(390,10);
        vBox = getBox(shopPane,390);

        ScrollPane purchasePane=createScrollPane(390,400);
        purchaseBox=getBox(purchasePane,390);


        Button back=InterfaceItems.createButton("Zakup",10,10,"orderButton");
        back.setOnAction(e->{
            float price=Float.parseFloat(purchaseText.getText());
            float cash=Float.parseFloat(accountValue.getText());
            if(price>cash)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Brak środków!");
                alert.show();
                return;
            }
            SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
            if(loggedUser.getRole()==1)
                logic.updateCard(loggedUser.getName(), (int)price*20);
            else if(loggedUser.isActive())
               logic.updateCard(loggedUser.getName(), (int)price*10);
            logic.doPurchase(purchaseList,price,loggedUser.getName());
            vBox.getChildren().clear();
            purchaseBox.getChildren().clear();
            purchaseList.clear();
            purchaseText.setText("0");
            loggedUser.updateCash(-price);
            accountValue.setText(String.format("%.2f",loggedUser.getCash()));
            mainStage.setScene(selfScene);

        });
        cardPurchase=InterfaceItems.createButton("Zakup z kartą",200,10,"orderButton");

        cardPurchase.setOnAction(e->{
            SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
            int cardPoints=logic.getPoints(loggedUser.getName());
            int usedPoints=0;
            float price=Float.parseFloat(purchaseText.getText());
            if(cardPoints>0)
            {
                if(loggedUser.getRole()==1)
                {
                    if(((float) cardPoints /50)>price)
                    {
                        usedPoints=(int)price*50;
                        price=0;
                    }
                    else
                    {
                        price-=(float)(cardPoints/50);
                        usedPoints=cardPoints;
                    }
                }
                else
                {
                    if(((float) cardPoints /100)>price)
                    {
                        usedPoints=(int)price*100;
                        price=0;
                    }
                    else
                    {
                        price-=(float)(cardPoints/100);
                        usedPoints=cardPoints;
                    }
                }

            }

            float cash=Float.parseFloat(accountValue.getText());
            if(price>cash)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Brak środków!");
                alert.show();
                return;
            }
            if(loggedUser.getRole()==1)
                usedPoints-=(int)(price*20);
            else
                usedPoints-=(int)(price*10);
            logic.doPurchase(purchaseList,price,loggedUser.getName());
            logic.updateCard(loggedUser.getName(), -usedPoints);
            vBox.getChildren().clear();
            purchaseBox.getChildren().clear();
            purchaseList.clear();
            purchaseText.setText("0");
            loggedUser.updateCash(-price);
            accountValue.setText(String.format("%.2f",loggedUser.getCash()));
            mainStage.setScene(selfScene);
        });
        pane.getChildren().addAll(shopPane,purchasePane,back,cardPurchase,accountValue,purchaseText);
        createView();
        return pane;
    }
    public void createView()
    {
        vBox.getChildren().clear();
        HBox b=getCanvasBox();
        b.getChildren().addAll(createColumnCeil(150,"Przedmiot"),
                createColumnCeil(50,"Ilość"),
                createColumnCeil(50,"Cena"));
        vBox.getChildren().addAll(b);
        for (Item i : items)
        {
            if(i.getCash()==0||i.getNumber()<1)
                continue;
            HBox box=getCanvasBox();
            Button addToBasket=InterfaceItems.createButton("+",50,30,"editButton");
            addToBasket.setOnAction(event -> {
                for(Item purchase:purchaseList)
                {
                    if(Objects.equals(purchase.getName(), i.getName()))
                    {
                        if(purchase.getNumber()<i.getNumber())
                        {
                            purchase.addNumber();
                            updateSum(i.getCash());
                            createPurchaseView();
                            return;
                        }
                        if(purchase.getNumber()==i.getNumber())
                            return;
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
            HBox box=getCanvasBox();
            Button deleteFromBasket=InterfaceItems.createButton("-",50,30,"editButton");
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
    private void updateSum(float f)
    {
        float sum=Float.parseFloat(purchaseText.getText());
        sum+=f;
        purchaseText.setText(String.format("%.2f",sum));
        createPurchaseView();
    }
}
