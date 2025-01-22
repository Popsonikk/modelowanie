package main.bussinessLogic;

import main.database.SQLCommands;
import main.models.User;

public class UserLogic {
    private final SQLCommands commands;
    public UserLogic(SQLCommands sqlCommands) {
        this.commands = sqlCommands;
    }
    public void addAccount(String username, String password,int role)
    {
        commands.addAccount(username,password,role);
    }
    public void addAccount(String username, String password)
    {
        commands.addAccount(username,password);
    }
    public User getAccount(String username, String password)
    {
        return commands.getAccount(username,password);
    }
    public void updateMoney(String username, float money)
    {
        commands.updateMoney(username,money);
    }
}
