package main.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableGenerator {
    SQLiteConnector connector;
    public TableGenerator(SQLiteConnector connector) {
        this.connector = connector;
    }

    public  void generateUserTable() {
        try (Connection conn = connector.connect();
             Statement statement = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "username TEXT PRIMARY KEY NOT NULL, " +
                    "password TEXT NOT NULL, " +
                    "role INTEGER NOT NULL, " +
                    "cash FLOAT(2) NOT NULL, " +
                    "cardID INTEGER, " +
                    "FOREIGN KEY (cardID) REFERENCES cards(id))";
            statement.executeUpdate(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  void createCardTable() {
        try (Connection conn = connector.connect();
             Statement statement = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS cards (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "points INTEGER NOT NULL)";
            statement.executeUpdate(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  void createProductTable() {
        try (Connection conn = connector.connect();
             Statement statement = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS items (" +
                    "name TEXT PRIMARY KEY NOT NULL,"+
                    "number INTEGER NOT NULL, " +
                    "price FLOAT(2) NOT NULL) ";
            statement.executeUpdate(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  void createOrderTable() {
        try (Connection conn = connector.connect();
             Statement statement = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS orders (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "itemName TEXT NOT NULL, " +
                    "number INTEGER NOT NULL,"+
                    "FOREIGN KEY (itemName) REFERENCES items(name)) ";
            statement.executeUpdate(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
