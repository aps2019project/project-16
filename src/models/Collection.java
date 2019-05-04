package models;
import models.card.*;
import models.card.Card;
import models.item.Item;

import java.util.ArrayList;

public class Collection {
    public static final int ITEM_CAPACITY = 3;
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
        this.cards.removeIf(card1 -> card1.equals(card));
        for (Deck deck : decks) {
            if (deck.hasCard(card.getCollectionID())) {
                if (card.getClass().equals(Hero.class)) {
                    deck.setHero(null);
                } else {
                    deck.removeCard(card);
                }
            }
        }
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.removeIf(b -> b.equals(item));
        for (Deck deck : decks) {
            if (deck.hasCard(item.getCollectionID())) {
                deck.setItem(null);
            }
        }
    }

    public int searchCard(String name) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(name))
                return card.getCollectionID();
        }
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name))
                return item.getCollectionID();
        }
        return -1;
    }

    public Item getItem(int itemID) {
        for (Item item : items) {
            if (item.getCollectionID() == itemID)
                return item;
        }
        return null;
    }

    public Card getCard(int cardID) {
        for (Card card : cards) {
            if (card.getCollectionID() == cardID )
                return card;
        }
        return null;
    }

    public Deck getDeck(String name) {
        for (Deck deck : decks) {
            if (deck.getName().equalsIgnoreCase(name))
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

    public void removeCardFromDeck(int cardID, String deckName) {
        Card card = this.getCard(cardID);
        Deck deck = this.getDeck(deckName);
        deck.removeCard(card);
    }

    public void removeItemFromDeck(int itemID, String deckName) {
        Item item = this.getItem(itemID);
        Deck deck = this.getDeck(deckName);
        deck.removeItem();
    }

    public String getType(int cardID) {
        Item item = getItem(cardID);
        Card card = getCard(cardID);
        if (item != null) {
            return "item";
        }
        if (card != null) {
            return "card";
        }
        return null;
    }
}
