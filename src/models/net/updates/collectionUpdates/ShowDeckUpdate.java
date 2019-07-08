package models.net.updates.collectionUpdates;

import models.Deck;
import models.net.Client;
import models.net.UpdatePacket;

public class ShowDeckUpdate extends UpdatePacket {
    private Deck deck;

    public ShowDeckUpdate(Deck deck) {
        this.deck = deck;
    }

    @Override
    public void update() {
        Client.getInstance().getCollectionSceneMaker().showDeck(deck);
    }
}
