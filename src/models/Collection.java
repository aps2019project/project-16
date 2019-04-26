package models;


import java.util.ArrayList;

public class Collection {
    private static final int ITEM_CAPACITY = 3;
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Deck> decks = new ArrayList<>();
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

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void removeCard(Card card) {
        this.cards.removeIf(card1 -> card1.equals(card)); // todo should be checked at end
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.removeIf(b -> b.equals(item));
    }

    public String searchCard(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name))
                return card.getCardID();
        }
        return null;
    }

    public String searchItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name))
                return item.getItemID();
        }
        return null;
    }

    public Item getItem(String itemID) {
        for (Item item : items) {
            if (item.getItemID().equals(itemID))
                return item;
        }
        return null;
    }

    public Card getCard(String cardID) {
        for (Card card : cards) {
            if (card.getCardID().equals(cardID))
                return card;
        }
        return null;
    }

    public Deck getDeck(String name) {
        for (Deck deck : decks) {
            if (deck.getName().equals(name))
                return deck;
        }
        return null;
    }

    public void createDeck(String name) {
        Deck deck = new Deck(name);
        this.decks.add(deck);
    }

    public void deleteDeck(Deck deck) {
        this.decks.removeIf(deck1 -> deck1.equals(deck));
    }

    public void addCard(Card card, Deck deck) {
        deck.addCard(card);
    }

    public void setHero(Hero hero, Deck deck) {
        deck.setHero(hero);
    }

    public void removeCardFromDeck(String cardID, String deckName) {
        Card card = this.getCard(cardID);
        Deck deck = this.getDeck(deckName);
        deck.removeCard(card);
    }

    public void removeItemFromDeck(String itemID, String deckName) {
        Item item = this.getItem(itemID);
        Deck deck = this.getDeck(deckName);
        deck.removeItem();
    }

    public boolean isExistingCardInCollectionError(Card card) {
        if (this.isExistingCard(card.getName()))
            return true;
        return false;
    } //todo maybe this method should go to CONTROL part

    public boolean isExistingCardInDeckError(Card card, Deck deck) {
        if (deck.isExistingCard(card.getName()))
            return true;
        return false;
    } //todo maybe this method should go to CONTROL part


    public boolean isExistingCard(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name))
                return true;
        }
        return false;
    } //todo maybe this method should go to CONTROL part

    public boolean isExistingItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name))
                return true;
        }
        return false;
    } //todo maybe this method should go to CONTROL part

    public boolean canAddItem() {
        if (this.items.size() < 3)
            return true;
        return false;
    } //todo maybe this method should go to CONTROL part
}
