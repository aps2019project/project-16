package models;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private static final int CARD_CAPACITY = 20;

    private ArrayList<Card> cards = new ArrayList<>();
    private Hero hero;
    private Item item;
    private String name;

    public Card getTop() {
        return cards.get(0);
    }

    public Card pop() {
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

    public boolean isValid() {
        return false;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(int index) {
        cards.remove(index);
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

    public Deck copy() {
        return null;
    }
}
