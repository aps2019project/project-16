package models;

import java.util.ArrayList;

public class Hand {
    private static final int CAPACITY = 5;

    private ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void removeCard(Card card) {  //cardID???

    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isFull() {
        return cards.size() >= CAPACITY;
    }
}
