package models.net.requests.shopRequests;

import controllers.ShopController;
import models.net.Client;
import models.net.RequestPacket;
import newView.ShopNetView;

public class BuyCardRequest extends RequestPacket {
    private String cardName;

    public BuyCardRequest(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public void run() {
        new ShopController(new ShopNetView()).buyCard(cardName);
    }
}
