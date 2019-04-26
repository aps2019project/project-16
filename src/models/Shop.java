package models;

import models.card.Card;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Usable> items = new ArrayList<>();

    public Shop() {
        Initializer.initShopCards(cards);
        Initializer.initShopUsableItems(items);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Usable> getItems() {
        return items;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    public Card getCard(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name))
                return card;
        }
        return null;
    }

    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name))
                return item;
        }
        return null;
    }

    public Card getCardByCardID(String cardID) {
        for (Card card : cards) {
            if (card.getCardID().equals(cardID))
                return card;
        }
        return null;
    }

    public Item getItemByItemID(String itemID) {
        for (Item item : items) {
            if (item.getItemID().equals(itemID))
                return item;
        }
        return null;
    }

    public void buyCard(Account account, String name) {
        Card card = getCard(name);
        account.decreaseMoney(card.getPrice());
        account.getCollection().addCard(card);
    }

    public void sellCard(Account account, String cardID) {
        Card card = getCard(cardID);
        account.increaseMoney(card.getPrice());
        account.getCollection().removeCard(card);
    }

    public void buyItem(Account account, String name) {
        Item item = getItem(name);
        account.decreaseMoney(item.getBuyPrice());
        account.getCollection().addItem(item);
    }

    public void sellItem(Account account, String name) {
        Item item = getItem(name);
        account.increaseMoney(item.getSellPrice());
        account.getCollection().removeItem(item);
    }

    public String getType(String name) {
        Card card = getCard(name);
        Item item = getItem(name);
        if (card != null)
            return "card";
        if (item != null)
            return "item";
        return null;
    }


    private boolean isExistingCard(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name))
                return true;
        }
        return false;
    }//todo should go to controller part

    private boolean isExistingCardID(String cardID) {
        for (Card card : cards) {
            if (card.getCardID().equals(cardID)) ;
            return true;
        }
        return false;
    }//todo should go to controller part

    private boolean isExistingItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name))
                return true;
        }
        return false;
    }//todo should go to controller part

    private boolean isExistingItemID(String itemID) {
        for (Item item : items) {
            if (item.getItemID().equals(itemID)) ;
            return true;
        }
        return false;
    }//todo should go to controller part

    public boolean accountHasCard(Account account, String cardID) {
        for (Card card : account.getCollection().getCards())
            if (card.getCardID().equals(cardID))
                return true;
        return false;
    }//todo should go to controller part

}
