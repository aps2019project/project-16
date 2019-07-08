package models.net.requests.collectionRequests;

import controllers.CollectionController;
import models.net.RequestPacket;
import newView.CollectionNetView;

public class LoadAllDecksRequest extends RequestPacket {
    @Override
    public void run() {
        new CollectionController(new CollectionNetView()).loadAllDecks();
    }
}
