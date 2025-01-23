package main.bussinessLogic;

import main.database.SQLCommands;
import main.models.Item;
import main.models.User;

import java.util.List;

public class PurchaseLogic {
    private final SQLCommands commands;
    public PurchaseLogic(SQLCommands sqlCommands) {
        this.commands = sqlCommands;
    }
    public void doPurchase(List<Item> purchase,float price,String name) {
        for (Item item : purchase) {
            commands.buyItem(item.getName(), item.getNumber());
        }
        commands.updateMoney(name,-price);
    }
    public List<User> getUsers()
    {
        return commands.getUsers();
    }
    public void addCard(String name)
    {
        commands.createCard();
        commands.addCard(name,commands.getCardID());
    }
}
