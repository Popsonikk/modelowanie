package main.bussinessLogic;

import main.database.SQLCommands;
import main.models.Item;

import java.util.List;

public class OrderLogic {

    public static void doOrder(List<Item> items)
    {
        for(Item item : items)
        {
            Item sQLItem=SQLCommands.getItem(item.getName());
            if(sQLItem!=null)
            {
                int number=item.getNumber()+ sQLItem.getNumber();
                SQLCommands.updateItemNumber(item.getName(), number);
            }
            else
            {
                SQLCommands.addItem(item.getName(), item.getNumber(), 0);
            }

        }
    }
    public static void saveOrder(List<Item> items, String name)
    {
        if(SQLCommands.checkOrder(name))
        {
            System.out.println("Order already exists");
        }
        else
        {
            for(Item item : items)
            {
                SQLCommands.addOrderRow(name, item.getName(), item.getNumber());
            }
        }
    }
    public static List<Item> getOrder(String name)
    {
         return SQLCommands.getOrder(name);
    }

}
