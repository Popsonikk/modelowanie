package main.models;

public class Item {
    private String name;
    private int number;
    private int cash;

    public Item(String name, int number, int cash) {
        this.name = name;
        this.number = number;
        this.cash = cash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
