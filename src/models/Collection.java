package models;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;

public class Collection {
    private static final int ITEM_CAPACITY = 3;

    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    public void addCard(Card card) {

    }

    public void removeCard() { //cardID

    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem() { //itemID

    }

    public boolean canAddItem() {
        return items.size() < ITEM_CAPACITY;
    }

    public int search(String name) {
        return 0;
    }
}
