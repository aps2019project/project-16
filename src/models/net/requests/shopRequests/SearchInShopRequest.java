package models.net.requests.shopRequests;

import controllers.ShopController;
import models.net.RequestPacket;
import newView.ShopNetView;

public class SearchInShopRequest extends RequestPacket {
    private String cardName;

    public SearchInShopRequest(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public void run() {
        new ShopController(new ShopNetView()).searchInShop(cardName);
    }
}
