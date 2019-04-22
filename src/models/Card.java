package models;

public abstract class Card {
    private int manaCost;
    private int sellPrice;
    private int buyPrice;
    private String name;
    private int cardID;
    private int price;

    public int getPrice() {
        return price;
    }

    protected Card(int manaCost, int buyPrice, int sellPrice) {
        this.manaCost = manaCost;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public String getName() {
        return this.name;
    }
    public int getCardID(){
        return this.cardID;
    }

}
