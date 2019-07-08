package models.net.requests.collectionRequests;

import controllers.CollectionController;
import models.net.RequestPacket;
import newView.CollectionNetView;

public class SelectDeckRequest extends RequestPacket {
    private String deckName;

    public SelectDeckRequest(String deckName) {
        this.deckName = deckName;
    }

    @Override
    public void run() {
        new CollectionController(new CollectionNetView()).selectDeck(deckName);
    }
}
