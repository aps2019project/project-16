package models.net.requests.collectionRequests;

import controllers.CollectionController;
import models.net.RequestPacket;
import newView.CollectionNetView;

public class LoadDeckRequest extends RequestPacket {
    private String deckName;

    public LoadDeckRequest(String deckName) {
        this.deckName = deckName;
    }

    @Override
    public void run() {
        new CollectionController(new CollectionNetView()).loadDeck(deckName);
    }
}
