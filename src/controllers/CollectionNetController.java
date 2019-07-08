package controllers;

import contracts.CollectionContract;
import models.net.Client;
import models.net.requests.collectionRequests.*;
import models.net.requests.shopRequests.LoadCollectionRequest;

public class CollectionNetController implements CollectionContract.Controller {
    @Override
    public void loadCollection() {
        Client.getInstance().sendPacket(new LoadCollectionRequest());
    }

    @Override
    public void loadAllDecks() {
        Client.getInstance().sendPacket(new LoadAllDecksRequest());
    }

    @Override
    public void loadDeck(String deckName) {
        Client.getInstance().sendPacket(new LoadDeckRequest(deckName));
    }

    @Override
    public void searchCard(String name) {
        Client.getInstance().sendPacket(new SearchCardRequest(name));
    }

    @Override
    public void saveCollection() {
    }

    @Override
    public void createDeck(String deckName) {
        Client.getInstance().sendPacket(new CreateDeckRequest(deckName));
    }

    @Override
    public void deleteDeck(String deckName) {
        Client.getInstance().sendPacket(new DeleteDeckRequest(deckName));
    }

    @Override
    public void addCardToDeck(int cardID, String deckName) {
        Client.getInstance().sendPacket(new AddCardToDeckRequest(cardID, deckName));
    }

    @Override
    public void removeCardFromDeck(int cardID, String deckName) {
        Client.getInstance().sendPacket(new RemoveCardFromDeckRequest(cardID, deckName));
    }

    @Override
    public void validateDeck(String deckName) {
    }

    @Override
    public void selectDeck(String deckName) {
        Client.getInstance().sendPacket(new SelectDeckRequest(deckName));
    }

    @Override
    public void validateMainDeckForEnterBattle() {
    }
}
