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
        cards.removeIf(card1 -> card1.getName().equals(card.getName()));
    }

    public void addCard(Card card) {
        if (!isFull())
            cards.add(card); //todo i think it must be copy
    }

    public boolean isFull() {
        return cards.size() >= CAPACITY;
    }///todo should be moved to controller
}
