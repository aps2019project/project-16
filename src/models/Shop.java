package models;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Item> items = new ArrayList<Item>();

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    public int searchCard(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name))
                return card.getCardID;
        }
    }///todo should be checked with next method

    private Card getCard(int cardID) {
        for (Card card : cards) {
            if (card.getCardID == cardID)
                return card;
        }
    }

    private Card getCard(String cardName) {
        for (Card card : cards) {
            if (card.getName.equals(cardName))
                return card;
        }
    }

    public void buyCard(Account account, String name) {
        if (isExistingCard(name)) {
            Card card = getCard(name);
            if (account.getMoney() >= card.getPrice) {
                if (account.getNumberOfItems() < 3) {
                    account.decreaseMoney(card.getPrice);
                    account.getCollection().add(card);//todo must be implemneted
                } else {
                    error;//todo somethng should be added
                }
            } else {
                error;//todo something should be added
            }
        } else {
            Error;//todo something should be add
        }
    }

    public void sellCard(Account account, int cardID) {
        if (this.accountHasCard(account , cardID)) {
            Card card = getCard(cardID);
            account.increaseMoney(card.getPrice);
            account.getCollection().getCards.removeIf();//todo must be fixed
        } else {
            error;//todo something should be added
        }
    }

    private boolean isExistingCard(String name) {
        for (Card card : cards) {
            if (card.getName.equals(name))
                return true;
        }
        return false;
    }

    private boolean isExistingCard(int cardID) {
        for (Card card : cards) {
            if (card.getCardID == cardID)
                return true;
        }
        return false;
    }

    private boolean isExistingItem(String name) {
        for (Item item : items) {
            if (item.getName.equals(name))
                return true;
        }
        return false;
    }

    private boolean isExistingItem(int itemID) {
        for (Item item : items) {
            if (item.getItemID == itemID)
                return true;
        }
        return false;
    }

    public boolean accountHasCard(Account account, int cardID) {
        for (Card card : account.getCollection().getCards)
            if (card.getCardID == cardID)
                return true;
        return false;
    }
}
