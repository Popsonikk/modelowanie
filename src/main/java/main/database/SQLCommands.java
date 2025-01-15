package main.database;

import main.models.Item;
import main.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public static void updateItemCash(String name, float price)
    {
        String sql="UPDATE items SET price= ? WHERE name= ?";
        try(Connection conn=SQLiteConnector.connect();
            PreparedStatement statement= conn.prepareStatement(sql)) {
            statement.setFloat(1, price);
            statement.setString(2, name);
            statement.executeUpdate();
            System.out.println("Item updated");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean checkOrder(String name)
    {
        String sql="SELECT * FROM orders WHERE name= ?";
        try(Connection conn=SQLiteConnector.connect();
            PreparedStatement statement= conn.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet rs=statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addOrderRow(String name,String item, int number)
    {
        String sql="INSERT INTO orders (name,itemName,number) VALUES (?,?,?)";
        try(Connection conn=SQLiteConnector.connect();
            PreparedStatement statement= conn.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, item);
            statement.setInt(3, number);
            statement.executeUpdate();
            System.out.println("Order row added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static List<Item> getOrder(String name)
    {
        List<Item> list= new ArrayList<>();
        String sql="SELECT * FROM orders WHERE name= ?";
        try(Connection conn=SQLiteConnector.connect();
            PreparedStatement statement= conn.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet rs=statement.executeQuery();
            while (rs.next())
                list.add(new Item(rs.getString("ItemName"),rs.getInt("number"),0));
            System.out.println("Order found");
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Item> getItems()
    {
        List<Item> list= new ArrayList<>();
        String sql="SELECT * FROM items";
        try(Connection conn=SQLiteConnector.connect();
            Statement statement= conn.createStatement()) {
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next())
                list.add(new Item(rs.getString("name"),rs.getInt("number"),rs.getFloat("price")));
            System.out.println("Order found");
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
