package main.database;

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

}
