package main.bussinessLogic;

import main.database.SQLCommands;
import main.models.Item;

import java.util.List;

public class AdminLogic {
    private final SQLCommands commands;
    public AdminLogic(SQLCommands sqlCommands) {
        this.commands = sqlCommands;
    }

    public void doOrder(List<Item> items)
    {
        for(Item item : items)
        {
            Item sQLItem=commands.getItem(item.getName());
            if(sQLItem!=null)
            {
                int number=item.getNumber()+ sQLItem.getNumber();
                commands.updateItemNumber(item.getName(), number);
            }
            else
            {
                commands.addItem(item.getName(), item.getNumber(), 0);
            }

        }
    }
    public  void saveOrder(List<Item> items, String name)
    {
        if(commands.checkOrder(name))
        {
            System.out.println("Order already exists");
        }
        else
        {
            for(Item item : items)
            {
                commands.addOrderRow(name, item.getName(), item.getNumber());
            }
        }
    }
    public  List<Item> getOrder(String name)
    {
         return commands.getOrder(name);
    }
    public  List<Item> getItems()
    {
        return commands.getItems();
    }
    public  void updateItemNumber(String i,int number)
    {
        commands.updateItemNumber(i,number);
    }
    public  void updateItemPrice(String i,float number)
    {
        commands.updateItemCash(i,number);
    }



}
