package models;

import models.card.Card;

import java.util.ArrayList;

public class Hand {
    private static final int CAPACITY = 5;
    private ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void removeCard(Card card) {
        cards.removeIf(card1 -> card1.equals(card));
    }

    public void addCard(Card card) {
        if (!isFull())
            cards.add(card);
    }

    public Card getCard(String cardName) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(cardName)) {
                return card;
            }
        }
        return null;
    }

    public boolean isFull() {
        return cards.size() >= CAPACITY;
    }
}
