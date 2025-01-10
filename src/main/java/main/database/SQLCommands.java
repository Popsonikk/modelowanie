package main.database;

import main.models.Item;
import main.models.User;

import java.sql.*;
import java.util.Collection;

public class SQLCommands {

    public static boolean accountExists(){
        String sql="SELECT * FROM USERS";
        try(Connection conn=SQLiteConnector.connect();
            Statement statement= conn.createStatement()){
            ResultSet rs=statement.executeQuery(sql);
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addAccount(String nick, String password) {
        String sql="INSERT INTO users (username,password,role,cash) VALUES (?,?,?,?)";
        try(Connection conn=SQLiteConnector.connect();
            PreparedStatement statement=conn.prepareStatement(sql)) {
            statement.setString(1, nick);
            statement.setString(2, password);
            if(SQLCommands.accountExists())
                statement.setInt(3,0);
            else
                statement.setInt(3,2);
            statement.setInt(4,0);
            statement.executeUpdate();
            System.out.println("Account added");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }public static void addAccount(String nick, String password,int role) {
        String sql="INSERT INTO users (username,password,role,cash) VALUES (?,?,?,?)";
        try(Connection conn=SQLiteConnector.connect();
            PreparedStatement statement=conn.prepareStatement(sql)) {
            statement.setString(1, nick);
            statement.setString(2, password);
            statement.setInt(3,role);
            statement.setInt(4,0);
            statement.executeUpdate();
            System.out.println("Account added");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static User getAccount(String nick,String password) {
        String sql="SELECT * FROM users WHERE username= ? AND password= ?";
        try(Connection conn=SQLiteConnector.connect();
            PreparedStatement statement=conn.prepareStatement(sql)) {
            statement.setString(1, nick);
            statement.setString(2, password);
            ResultSet rs=statement.executeQuery();
            if (rs.next()){
                System.out.println("Account found");
                return new User(rs.getString("username"),
                        rs.getInt("role"),rs.getInt("cash"));
            }
            else
            {
                System.out.println("Account not found");
                return null;
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addItem(String name, int number, int cash)
    {
        String sql="INSERT INTO items (name,number,price) VALUES (?,?,?)";
        try(Connection conn=SQLiteConnector.connect();
        PreparedStatement statement= conn.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, number);
            statement.setInt(3, cash);
            statement.executeUpdate();
            System.out.println("Item added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Item getItem(String name)
    {
        String sql="SELECT * FROM items WHERE name= ?";
        try(Connection conn=SQLiteConnector.connect();
            PreparedStatement statement= conn.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet rs=statement.executeQuery();
            if (rs.next()){
                System.out.println("Item found");
                return new Item(rs.getString("name"),rs.getInt("number"),rs.getInt("price"));
            }
            else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateItemNumber(String name, int number)
    {
        String sql="UPDATE items SET number= ? WHERE name= ?";
        try(Connection conn=SQLiteConnector.connect();
            PreparedStatement statement= conn.prepareStatement(sql)) {
            statement.setInt(1, number);
            statement.setString(2, name);
            statement.executeUpdate();
            System.out.println("Item updated");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateItemCash(String name, int price)
    {
        String sql="UPDATE items SET price= ? WHERE name= ?";
        try(Connection conn=SQLiteConnector.connect();
            PreparedStatement statement= conn.prepareStatement(sql)) {
            statement.setInt(1, price);
            statement.setString(2, name);
            statement.executeUpdate();
            System.out.println("Item updated");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
