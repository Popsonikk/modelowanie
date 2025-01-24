package main.bussinessLogic;

import main.database.SQLCommands;
import main.database.SQLiteConnector;
import main.database.TableGenerator;
import main.models.Item;
import main.models.User;

import java.util.List;

public class SQLFacade {
    private final SQLCommands commands;
    public SQLFacade(SQLCommands sqlCommands) {
        this.commands = sqlCommands;
    }
    public void doOrder(List<Item> items)
    {
        for(Item item : items) {
            Item sQLItem=commands.getItem(item.getName());
            if(sQLItem!=null) {
                int number=item.getNumber()+ sQLItem.getNumber();
                commands.updateItemNumber(item.getName(), number);
            }
            else
                commands.addItem(item.getName(), item.getNumber(), 0);
        }
    }
    public  void saveOrder(List<Item> items, String name)
    {
        if(commands.checkOrder(name))
            System.out.println("Order already exists");
        else
        {
            for(Item item : items)
                commands.addOrderRow(name, item.getName(), item.getNumber());
        }
    }
    public  List<Item> getOrder(String name) {
         return commands.getOrder(name);
    }
    public  List<Item> getItems() {
        return commands.getItems();
    }
    public  void updateItemNumber(String i,int number) {
        commands.updateItemNumber(i,number);
    }
    public  void updateItemPrice(String i,float number) {
        commands.updateItemCash(i,number);
    }
    public void generateTables()
    {
        TableGenerator generator=new TableGenerator(new SQLiteConnector());
        generator.createOrderTable();
        generator.generateUserTable();
        generator.createProductTable();
        generator.createCardTable();
    }
    public void addAccount(String username, String password,int role) {
        commands.addAccount(username,password,role);
    }
    public void addAccount(String username, String password) {
        commands.addAccount(username,password);
    }
    public User getAccount(String username, String password) {
        return commands.getAccount(username,password);
    }
    public void updateMoney(String username, float money) {
        commands.updateMoney(username,money);
    }
    public void doPurchase(List<Item> purchase,float price,String name) {
        for (Item item : purchase) {
            commands.buyItem(item.getName(), item.getNumber());
        }
        commands.updateMoney(name,-price);
    }
    public List<User> getUsers() {
        return commands.getUsers();
    }
    public void addCard(String name) {
        commands.createCard();
        commands.addCard(name,commands.getCardID());
    }
    public void updateCard(String name,int points) {
        int cardID=commands.findCard(name);
        commands.updatePoints(cardID,points);
    }
    public int getPoints(String name) {
        int cardID=commands.findCard(name);
        return commands.getPoints(cardID);
    }

}
