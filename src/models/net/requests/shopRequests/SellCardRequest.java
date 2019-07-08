package models.net.requests.shopRequests;

import controllers.ShopController;
import models.net.RequestPacket;
import newView.ShopNetView;

public class SellCardRequest extends RequestPacket {
    private int cardId;

    public SellCardRequest(int cardId) {
        this.cardId = cardId;
    }

    @Override
    public void run() {
        new ShopController(new ShopNetView()).sellCard(cardId);
    }
}
