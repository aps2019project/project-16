package controllers;

import contracts.CollectionContract;
import models.Collection;
import models.GameContents;
import models.Item;
import models.card.Card;
import models.card.Hero;
import view.views.CollectionView;

import java.util.ArrayList;

public class CollectionController implements CollectionContract.Controller {
    private CollectionContract.View view;

    public CollectionController() {
        view = new CollectionView();
        view.setController(this);
    }

    // TODO: 4/21/19 implement all of functions :)))

    @Override
    public void loadCollection() {
        Collection collection = GameContents.getCurrentAccount().getCollection();
        ArrayList<Hero> heroes = new ArrayList<>();
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Item> items = collection.getItems();
        for (Card card : collection.getCards()) {
            if (card.getClass().equals(Hero.class)) {
                heroes.add((Hero) card);
            } else {
                cards.add(card);
            }
        }
        view.showCollection(heroes, items, cards);
    }

    @Override
    public void loadAllDecks() {

    }

    @Override
    public void loadDeck(String deckName) {

    }

    @Override
    public void searchCard(String name) {

    }

    @Override
    public void saveCollection() {

    }

    @Override
    public void createDeck(String deckName) {

    }

    @Override
    public void deleteDeck(String deckName) {

    }

    @Override
    public void addCardToDeck(int cardID, String deckName) {

    }

    @Override
    public void removeCardFromDeck(int cardID, String deckName) {

    }

    @Override
    public void validateDeck(String deckName) {

    }

    @Override
    public void selectDeck(String deckName) {

    }
}
