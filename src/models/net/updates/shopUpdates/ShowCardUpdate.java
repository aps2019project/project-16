package models.net.updates.shopUpdates;

import models.net.Client;
import models.net.UpdatePacket;

public class ShowCardUpdate extends UpdatePacket {
    private Object card;

    public ShowCardUpdate(Object card) {
        this.card = card;
    }

    @Override
    public void update() {
        Client.getInstance().getShopSceneMaker().showCard(card);
    }
}
