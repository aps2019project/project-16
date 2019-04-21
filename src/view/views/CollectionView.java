package view.views;

import contracts.CollectionContract;
import models.Deck;

import java.util.ArrayList;

public class CollectionView implements CollectionContract.View {
    private CollectionContract.Controller controller;

    @Override
    public void setController(CollectionContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showAllDecks(ArrayList<Deck> decks) {

    }

    @Override
    public void showDeck(Deck deck) {

    }

    @Override
    public void showDeckProblemError(String message) {

    }

    @Override
    public void deckCreationError(String message) {

    }

    @Override
    public void deckCreationSuccessMSG(String deckName) {

    }

    @Override
    public void deckDeletionError(String message) {

    }

    @Override
    public void deckDeletionSuccessMSG(String deckName) {

    }

    @Override
    public void collectionSaveSuccessMSG() {

    }

    @Override
    public void addCardProblemError(String message) {

    }

    @Override
    public void addCardSuccessMSG(String cardName, String deckName) {

    }

    @Override
    public void removeCardProblemError(String message) {

    }

    @Override
    public void removeCardSuccessMSG(String cardName, String deckName) {

    }

    @Override
    public void mainDeckSelectionSuccessMSG(String deckName) {

    }

    @Override
    public void showDeckValidationStatus(String deckName, String message) {

    }
}
