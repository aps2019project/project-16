package controllers;

import contracts.ShopContract;
import models.net.Client;
import models.net.requests.shopRequests.*;

public class ShopNetController implements ShopContract.Controller {

    @Override
    public void loadCollection() {
        Client.getInstance().sendPacket(new LoadCollectionRequest());
    }

    @Override
    public void loadShop() {
        Client.getInstance().sendPacket(new LoadShopRequest());
    }

    @Override
    public void searchInShop(String cardName) {
        Client.getInstance().sendPacket(new SearchInShopRequest(cardName));
    }

    @Override
    public void searchInCollection(String cardName) {

    }

    @Override
    public void buyCard(String cardName) {
        Client.getInstance().sendPacket(new BuyCardRequest(cardName));
    }

    @Override
    public void sellCard(int cardID) {
        Client.getInstance().sendPacket(new SellCardRequest(cardID));
    }
}
