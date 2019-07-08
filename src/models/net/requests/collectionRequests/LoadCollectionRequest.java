package models.net.requests.collectionRequests;

import controllers.CollectionController;
import models.net.RequestPacket;
import newView.CollectionNetView;

public class LoadCollectionRequest extends RequestPacket {
    @Override
    public void run() {
        new CollectionController(new CollectionNetView()).loadCollection();
    }
}
