package models;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private static final int CARD_CAPACITY = 20;

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

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean isValid() {
        if (this.cards.size() == 20 && this.hero != null && this.item != null)
            return true;
        return false;
    }//todo should be moved to controller (sepehr)

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.removeIf(card1 -> card1.getCardID().equals(card.getCardID()));
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

    public boolean isExistingCard(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name))
                return true;
        }
        return false;
    }

    public Deck copy() {
        Deck deck = new Deck(this.cards , this.hero ,this.item ,this.name);
        return deck;
        //todo copy constructor should be checked
    }

}
