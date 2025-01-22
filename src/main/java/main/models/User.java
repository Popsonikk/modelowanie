package main.models;

public class User {

    private String name;
    private int role;
    private float cash;

    public User( String name,int role,float cash )  {

        this.cash = cash;
        this.role = role;
        this.name = name;
    }



    public float getCash() {
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
