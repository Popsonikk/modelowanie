package main.models;

public class User {

    private String name;
    private int role;
    private int cash;

    public User( String name,int role,int cash )  {

        this.cash = cash;
        this.role = role;
        this.name = name;
    }



    public int getCash() {
        return cash;
    }

    public void updateCash(int c) {
        cash += c;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
