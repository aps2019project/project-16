package models.card;

import models.CollectionUniqueIDGenerator;

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

    public int getPrice() {
        return buyPrice;
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
            this.buyPrice = buyPrice;
            return this;
        }

        public CardBuilder setName(String name) {
            this.name = name;
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
        // TODO: 4/29/19

//        GameElement cardType;
//        if (this.getClass().equals(Hero.class)) {
//            cardType = HERO;
//        } else if (this.getClass().equals(Minion.class)) {
//            cardType = MINION;
//        } else if (this.getClass().equals(SpellCard.class)) {
//            cardType = SPELL_CARD;
//        } else {
//            cardType = UNDEFINED;
//        }

        Card newCard = this;//this is chert and movaghat

//        switch (cardType) {
//            case HERO:
//
//                break;
//            case MINION:
//
//                break;
//            case SPELL_CARD:
//
//                break;
//        }
        if (setCollectionID) {
            newCard.collectionID = CollectionUniqueIDGenerator.getUniqueID();
        }
        return newCard;
    }
}
