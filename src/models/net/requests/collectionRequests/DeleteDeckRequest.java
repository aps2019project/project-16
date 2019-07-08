package models.net.requests.collectionRequests;

import controllers.CollectionController;
import models.net.RequestPacket;
import newView.CollectionNetView;

public class DeleteDeckRequest extends RequestPacket {
    private String deckName;

    public DeleteDeckRequest(String deckName) {
        this.deckName = deckName;
    }

    @Override
    public void run() {
        new CollectionController(new CollectionNetView()).deleteDeck(deckName);
    }
}
