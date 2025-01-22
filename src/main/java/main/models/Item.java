package main.models;

public class Item {
    private String name;
    private int number;
    private float cash;

    public Item(String name, int number, float cash) {
        this.name = name;
        this.number = number;
        this.cash = cash;
    }
    public Item(String name, int number) {
        this.name = name;
        this.number = number;
        this.cash = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void addNumber() {
        this.number++;
    }
    public void deleteNumber() {
        this.number--;
    }
    public void addCash(float money) {
        cash += money;
    }
}
