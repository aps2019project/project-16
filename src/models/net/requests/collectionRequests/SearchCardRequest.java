package models.net.requests.collectionRequests;

import controllers.CollectionController;
import models.net.RequestPacket;
import newView.CollectionNetView;

public class SearchCardRequest extends RequestPacket {
    private String name;

    public SearchCardRequest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        new CollectionController(new CollectionNetView()).searchCard(name);
    }
}
