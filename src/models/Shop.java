package models;

import javax.naming.spi.StateFactory;
import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.util.ArrayList;

public class Shop {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Usable> items = new ArrayList<>();


    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Usable> getItems() {
        return items;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    public String searchCard(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name))
                return card.getCardID();
        }
        return null;
    }

    public Card getCard(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name))
                return card;
        }
        return  null;
    }
    public  Card getCardByCardID(String cardID){
        for (Card card  :cards){
            if (card.getCardID().equals(cardID))
                return card;
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
            account.getCollection().removecard(card);
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
            if (card.getCardID().equals(cardID));
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
            if (item.getItemID().equals(itemID));
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
