package main.bussinessLogic;

import main.database.SQLCommands;
import main.models.Item;

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
}
