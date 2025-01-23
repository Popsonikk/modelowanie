package main.database;

import main.models.Item;
import main.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SQLCommands {
    SQLiteConnector connector;

    public SQLCommands(SQLiteConnector connector) {
        this.connector = connector;
    }

    public boolean accountExists() {
        String sql = "SELECT * FROM USERS";
        try (Connection conn = connector.connect();
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAccount(String nick, String password) {
        String sql = "INSERT INTO users (username,password,role,cash) VALUES (?,?,?,?)";
        SQLCommands commands = new SQLCommands(connector);
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nick);
            statement.setString(2, password);
            if (commands.accountExists())
                statement.setInt(3, 0);
            else
                statement.setInt(3, 2);
            statement.setInt(4, 0);
            statement.executeUpdate();
            System.out.println("Account added");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAccount(String nick, String password, int role) {
        String sql = "INSERT INTO users (username,password,role,cash) VALUES (?,?,?,?)";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nick);
            statement.setString(2, password);
            statement.setInt(3, role);
            statement.setInt(4, 0);
            statement.executeUpdate();
            System.out.println("Account added");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getAccount(String nick, String password) {
        String sql = "SELECT * FROM users WHERE username= ? AND password= ?";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nick);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("Account found");
                return new User(rs.getString("username"),
                        rs.getInt("role"), rs.getInt("cash"));
            } else {
                System.out.println("Account not found");
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addItem(String name, int number, int cash) {
        String sql = "INSERT INTO items (name,number,price) VALUES (?,?,?)";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, number);
            statement.setInt(3, cash);
            statement.executeUpdate();
            System.out.println("Item added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Item getItem(String name) {
        String sql = "SELECT * FROM items WHERE name= ?";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("Item found");
                return new Item(rs.getString("name"), rs.getInt("number"), rs.getInt("price"));
            } else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateItemNumber(String name, int number) {
        String sql = "UPDATE items SET number= ? WHERE name= ?";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, number);
            statement.setString(2, name);
            statement.executeUpdate();
            System.out.println("Item updated");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateItemCash(String name, float price) {
        String sql = "UPDATE items SET price= ? WHERE name= ?";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setFloat(1, price);
            statement.setString(2, name);
            statement.executeUpdate();
            System.out.println("Item updated");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkOrder(String name) {
        String sql = "SELECT * FROM orders WHERE name= ?";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addOrderRow(String name, String item, int number) {
        String sql = "INSERT INTO orders (name,itemName,number) VALUES (?,?,?)";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, item);
            statement.setInt(3, number);
            statement.executeUpdate();
            System.out.println("Order row added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Item> getOrder(String name) {
        List<Item> list = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE name= ?";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next())
                list.add(new Item(rs.getString("ItemName"), rs.getInt("number"), 0));
            System.out.println("Order found");
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Item> getItems() {
        List<Item> list = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Connection conn = connector.connect();
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
                list.add(new Item(rs.getString("name"), rs.getInt("number"), rs.getFloat("price")));
            System.out.println("Order found");
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMoney(String name, float money) {
        String sql = "UPDATE users SET cash=cash+? WHERE username= ?";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setFloat(1, money);
            statement.setString(2, name);
            statement.executeUpdate();
            System.out.println("Money added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void buyItem(String name, int number) {
        String sql = "UPDATE items SET number=number- ? WHERE name= ?";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, number);
            statement.setString(2, name);
            statement.executeUpdate();
            System.out.println("Item updated");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users ";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("username"),
                        rs.getInt("role"), rs.getInt("cash"));
                user.setActive(rs.getDate("cardID") != null);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void createCard() {
        String sql = "INSERT INTO cards(points) VALUES (?);";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, 0);
             statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getCardID()
    {
        String sql = "SELECT * FROM cards;";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet rs= statement.executeQuery();
            int i=0;
            while (rs.next())
                i=rs.getInt("id");
            return i;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCard(String name,int id) {
        String sql = "UPDATE users SET cardID=? WHERE username= ?;";
        try (Connection conn = connector.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.executeUpdate();
            System.out.println("Card added");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
