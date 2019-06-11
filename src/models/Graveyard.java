package models;

import models.card.Card;

import java.util.ArrayList;

public class Graveyard {
    private ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getCard(String cardName, int gameID) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(cardName) && card.getGameCardID() == gameID) {
                return card;
            }
        }
        return null;
    }
}
