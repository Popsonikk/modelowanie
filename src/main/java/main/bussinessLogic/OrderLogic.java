package main.bussinessLogic;

import main.database.SQLCommands;
import main.models.Item;

import java.util.List;

public class OrderLogic {

    public static void doOrder(List<String> items)
    {
        for(String item : items)
        {
            String[] it=item.split(",");
            Item sQLItem=SQLCommands.getItem(it[0].trim());
            if(sQLItem!=null)
            {
                int number=Integer.parseInt(it[1].trim())+ sQLItem.getNumber();
                SQLCommands.updateItemNumber(it[0].trim(),number);
            }
            else
            {
                SQLCommands.addItem(it[0].trim(), Integer.parseInt(it[1].trim()),0);
            }

        }
    }

}
