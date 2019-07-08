package models.net.requests.shopRequests;

import controllers.ShopController;
import models.net.RequestPacket;
import newView.ShopNetView;

public class LoadShopRequest extends RequestPacket {
    @Override
    public void run() {
        new ShopController(new ShopNetView()).loadShop();
    }
}
