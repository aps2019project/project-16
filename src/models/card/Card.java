package models.card;

import com.gilecode.yagson.YaGson;
import models.UniqueIDGenerator;

public abstract class Card {
    private int manaCost;
    private int sellPrice;
    private int buyPrice;
    private String name;
    private String description;
    private int collectionID;
    private int gameCardID;

    public int getGameCardID() {
        return gameCardID;
    }

    public void setGameCardID(int gameCardID) {
        this.gameCardID = gameCardID;
    }

    protected Card(String name, int manaCost, int buyPrice, int sellPrice, String description) {
        this.manaCost = manaCost;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.name = name;
        this.description = description;
    }

    public static abstract class CardBuilder {
        private int manaCost;
        private int sellPrice;
        private int buyPrice;
        private String name;
        private String description;


        public CardBuilder setManaCost(int manaCost) {
            this.manaCost = manaCost;
            return this;
        }

        public CardBuilder setSellPrice(int sellPrice) {
            this.sellPrice = sellPrice;
            return this;
        }

        public CardBuilder setBuyPrice(int buyPrice) {
            setSellPrice(buyPrice);
            this.buyPrice = buyPrice;
            return this;
        }

        public CardBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CardBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        int getManaCost() {
            return manaCost;
        }

        int getSellPrice() {
            return sellPrice;
        }

        int getBuyPrice() {
            return buyPrice;
        }

        String getName() {
            return name;
        }

        String getDescription() {
            return description;
        }

        public abstract Card create();
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

    public String getDescription() {
        return description;
    }

    public int getCollectionID() {
        return this.collectionID;
    }

    public Card getCopy(boolean setCollectionID) {
        String json = new YaGson().toJson(this);
        Card newCard = new YaGson().fromJson(json, this.getClass());
        if (setCollectionID) {
            newCard.collectionID = UniqueIDGenerator.getCollectionUniqueID();
        }
        return newCard;
    }
}
