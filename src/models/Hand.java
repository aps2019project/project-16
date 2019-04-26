package models;

import models.card.Card;

import java.util.ArrayList;

public class Hand {
    private static final int CAPACITY = 5;
    private static ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void removeCard(Card card) {
        cards.removeIf(card1 -> card1.equals(card));
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isFull() {
        return cards.size() >= CAPACITY;
    }///todo should be moved to controller
}
