package models.net.updates.collectionUpdates;

import models.net.Client;
import models.net.UpdatePacket;

import java.util.List;

public class ShowCardsUpdate extends UpdatePacket {
    private List<Object> cards;

    public ShowCardsUpdate(List<Object> cards) {
        this.cards = cards;
    }

    @Override
    public void update() {
        Client.getInstance().getCollectionSceneMaker().showCards(cards);
    }
}
