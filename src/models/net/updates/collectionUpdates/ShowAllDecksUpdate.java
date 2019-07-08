package models.net.updates.collectionUpdates;

import models.Deck;
import models.net.Client;
import models.net.UpdatePacket;

import java.util.ArrayList;

public class ShowAllDecksUpdate extends UpdatePacket {
    private Deck mainDeck;
    private ArrayList<Deck> decks;

    public ShowAllDecksUpdate(Deck mainDeck, ArrayList<Deck> decks) {
        this.mainDeck = mainDeck;
        this.decks = decks;
    }

    @Override
    public void update() {
        Client.getInstance().getCollectionSceneMaker().showAllDecks(mainDeck, decks);
    }
}
