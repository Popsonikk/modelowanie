package main.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableGenerator {

    public static void generateUserTable() {
        try (Connection conn = SQLiteConnector.connect();
             Statement statement = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "username TEXT PRIMARY KEY NOT NULL, " +
                    "password TEXT NOT NULL, " +
                    "role INTEGER NOT NULL, " +
                    "cash INTEGER NOT NULL)";
            statement.executeUpdate(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createCardTable() {
        try (Connection conn = SQLiteConnector.connect();
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

    public static void createUsersCardsTable() {
        try (Connection conn = SQLiteConnector.connect();
             Statement statement = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS usersCards (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "userID TEXT NOT NULL, " +
                    "cardID INTEGER NOT NULL, " +
                    "FOREIGN KEY (userID) REFERENCES users(id), " +
                    "FOREIGN KEY (cardID) REFERENCES cards(id))";
            statement.executeUpdate(sql);
            System.out.println("Table created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
