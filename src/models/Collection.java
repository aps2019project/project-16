package models;


import java.util.ArrayList;

public class Collection {
    private static final int ITEM_CAPACITY = 3;
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private Deck mainDeck;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Deck getMainDeck() {
        return this.mainDeck;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void removecard(Card card) {
        this.cards.removeIf(b -> b.equals(card)); // todo should be checked at  end
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.removeIf(b -> b.equals(item));
    }

    public boolean canAddItem() {
        if (this.items.size() < 3)
            return true;
        return false;
    }

    public int searchCard(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name))
                return card.getCardID();
        }
        return -1;
    }

    public int searchItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name))
                return item.getItemID();
        }
        return -1;
    }
}
