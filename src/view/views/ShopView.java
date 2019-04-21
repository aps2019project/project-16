package view.views;

import contracts.ShopContract;
import models.Card;
import models.Hero;
import models.Item;

import java.util.ArrayList;

public class ShopView implements ShopContract.View {
    private ShopContract.Controller controller;


    @Override
    public void setController(ShopContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showCollection(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {

    }

    @Override
    public void showShopSearchResult(String cardName, String message) {

    }

    @Override
    public void showCollectionSearchResult(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {

    }

    @Override
    public void showShop(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {

    }

    @Override
    public void showBuyResult(String cardName, String message) {

    }

    @Override
    public void showSellError(String cardID, String message) {

    }

    @Override
    public void showSellSuccessMSG(String cardName, String cardID) {

    }
}
