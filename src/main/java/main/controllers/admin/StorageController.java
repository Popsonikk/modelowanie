package main.controllers.admin;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.bussinessLogic.SQLFacade;
import main.controllers.templates.InsideController;
import main.controllers.templates.InterfaceItems;
import main.database.SQLCommands;
import main.database.SQLiteConnector;
import main.models.Item;
import main.models.User;

import java.util.ArrayList;
import java.util.List;

public class StorageController extends InsideController {
    private List<Item> items;
    private VBox vBox;
    private boolean isActive;
    private User user;
    Pane pane;

    public void setStorageItems(List<Item> storageItems) {
        this.items = storageItems;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pane createScenePane()
    {
        isActive = false;
        pane=getPane();
        items=new ArrayList<>();
        ScrollPane scrollPane = createScrollPane(780,10);
        vBox = getBox(scrollPane,780);

        Button back= InterfaceItems.createButton("Powrót",300,5,"orderButton");
        back.setOnAction(event -> {mainStage.setScene(selfScene);});

        pane.getChildren().addAll(scrollPane,back);
        createView();
        return pane;
    }
    public void createInwBut()
    {
        Button count=InterfaceItems.createButton("Inwentaryzacja",500,5,"orderButton");
        count.setOnAction(event -> {
            if(!isActive)
            {
                isActive=true;
                count.setText("Zakończ inwentaryzacje");

            }
            else
            {
                isActive=false;
                count.setText("Inwentaryzacja");
            }
            createView();
        });
        pane.getChildren().addAll(count);
    }
    public void createView()
    {
        vBox.getChildren().clear();
        HBox b=getCanvasBox();

        b.getChildren().addAll(createColumnCeil(300,"Przedmiot"),
                createColumnCeil(200,"Ilość"),
                createColumnCeil(200,"Cena"));
        vBox.getChildren().addAll(b);
        for (Item i : items)
        {
            HBox box=getCanvasBox();
            if(isActive)
            {
                Button editNumber=InterfaceItems.createButton("N",50,30,"editButton");
                editNumber.setOnAction(event -> {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Podaj liczbę");
                    dialog.showAndWait();
                    if(dialog.getResult() != null)
                    {
                        int number = Integer.parseInt(dialog.getResult());
                        SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
                        logic.updateItemNumber(i.getName(),number);
                        i.setNumber(number);
                        vBox.getChildren().clear();
                        createView();
                    }
                });
                box.getChildren().addAll(editNumber);
            }

            Button editPrice=InterfaceItems.createButton("C",50,30,"editButton");
            editPrice.setOnAction(event -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Podaj liczbę");
                dialog.showAndWait();
                if(dialog.getResult() != null)
                {
                    float price = Float.parseFloat(dialog.getResult());
                    SQLFacade logic=new SQLFacade(new SQLCommands(new SQLiteConnector()));
                    logic.updateItemPrice(i.getName(),price);
                    i.setCash(price);
                    vBox.getChildren().clear();
                    createView();
                }
            });
            box.getChildren().addAll(createColumnCeil(300,i.getName()),
                    createColumnCeil(175,String.valueOf(i.getNumber())),
                    createColumnCeil(175,String.valueOf(i.getCash())),editPrice);
            vBox.getChildren().addAll(box);
        }
    }
}
