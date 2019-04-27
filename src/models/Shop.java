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
    public Card getCard(String cardName) {
        for (Card card : cards) {
            if (card.getName().equals(cardName))
                return card;
        }
        return null;
    }

    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public Card getCardByCardID(int cardID) {
        for (Card card : cards) {
            if (card.getShopCardID() == cardID)
                return card;
        }
        return null;
    }

    public Item getItemByItemID(int itemID) {
        for (Item item : items) {
            if (item.getShopItemID() == itemID)
                return item;
        }
        return null;
    }

    public void buyCard(String cardName) {
        Account currentAccount = GameContents.getCurrentAccount();
        Collection collection = currentAccount.getCollection();

        Card card = getCard(cardName);
        currentAccount.decreaseMoney(card.getBuyPrice());
        collection.addCard(card);
    }

    public void sellCard(int cardID) {
        Account currentAccount = GameContents.getCurrentAccount();
        Collection collection = currentAccount.getCollection();

        Card card = collection.getCard(cardID);
        currentAccount.increaseMoney(card.getBuyPrice());
        collection.removeCard(card);
    }

    public void buyItem(String itemName) {
        Account currentAccount = GameContents.getCurrentAccount();
        Collection collection = currentAccount.getCollection();

        Item item = getItem(itemName);
        currentAccount.decreaseMoney(item.getBuyPrice());
        collection.addItem(item);
    }

    public void sellItem(int itemID) {
        Account currentAccount = GameContents.getCurrentAccount();
        Collection collection = currentAccount.getCollection();

        Item item = collection.getItem(itemID);
        currentAccount.increaseMoney(item.getBuyPrice());
        collection.removeItem(item);
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
}
