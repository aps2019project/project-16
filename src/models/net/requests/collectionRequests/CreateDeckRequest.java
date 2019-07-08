package models.net.requests.collectionRequests;

import controllers.CollectionController;
import models.net.RequestPacket;
import newView.CollectionNetView;

public class CreateDeckRequest extends RequestPacket {
    private String deckName;

    public CreateDeckRequest(String deckName) {
        this.deckName = deckName;
    }

    @Override
    public void run() {
        new CollectionController(new CollectionNetView()).createDeck(deckName);
    }
}
