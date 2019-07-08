package models.net.requests.collectionRequests;

import controllers.CollectionController;
import models.net.RequestPacket;
import newView.CollectionNetView;

public class AddCardToDeckRequest extends RequestPacket {
    private int cardId;
    private String deckName;

    public AddCardToDeckRequest(int cardId, String deckName) {
        this.cardId = cardId;
        this.deckName = deckName;
    }

    @Override
    public void run() {
        new CollectionController(new CollectionNetView()).addCardToDeck(cardId, deckName);
    }
}
