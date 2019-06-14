package models;

import com.gilecode.yagson.YaGson;
import models.card.Card;
import models.card.Hero;
import exception.ArrayIsEmptyException;
import models.item.Item;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    public static final int CARD_CAPACITY = 20;

    private ArrayList<Card> cards = new ArrayList<>();
    private Hero hero;
    private Item item;
    private String name;

    public Deck(String name) {
        this.name = name;
    }

    public Deck(ArrayList<Card> cards, Hero hero, Item item, String name) {
        this.cards = cards;
        this.hero = hero;
        this.item = item;
        this.name = name;
    }

    public Card getTop() {
        if (cards.size() < 1) {
            return null;
        }
        return cards.get(0);
    }

    public Card pop() throws ArrayIsEmptyException {
        if (cards.size() < 1) {
            throw new ArrayIsEmptyException();
        }
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }

    public Hero getHero() {
        return hero;
    }

    public Item getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.removeIf(card1 -> card1.equals(card));
    }

    public void removeItem() {
        this.item = null;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public boolean hasCard(int cardID) {
        for (Card card : cards) {
            if (card.getCollectionID() == cardID)
                return true;
        }
        if (hero != null && hero.getCollectionID() == cardID) {
            return true;
        }
        if (item != null && item.getCollectionID() == cardID) {
            return true;
        }
        return false;
    }

    public boolean isValid() {
        return this.cards.size() == CARD_CAPACITY && this.getHero() != null;
    }

    public Deck getCopy() {
        String json = new YaGson().toJson(this);
        Deck newDeck = new YaGson().fromJson(json, this.getClass());
        return newDeck;
    }
}
