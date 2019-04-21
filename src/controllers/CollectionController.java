package controllers;

import contracts.CollectionContract;
import view.views.CollectionView;

public class CollectionController implements CollectionContract.Controller {
    private CollectionContract.View view;

    public CollectionController() {
        view = new CollectionView();
        view.setController(this);
    }

    // TODO: 4/21/19 implement all of functions :)))

    @Override
    public void loadCollection() {

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
