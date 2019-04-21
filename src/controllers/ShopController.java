package controllers;

import contracts.ShopContract;
import view.views.ShopView;

public class ShopController implements ShopContract.Controller {
    private ShopContract.View view;

    public ShopController() {
        view = new ShopView();
        view.setController(this);
    }

    // TODO: 4/21/19 implement all of functions :)))

    @Override
    public void loadCollection() {

    }

    @Override
    public void loadShop() {

    }

    @Override
    public void searchInShop(String cardName) {

    }

    @Override
    public void searchInCollection(String cardName) {

    }

    @Override
    public void buyCard(int cardID) {

    }

    @Override
    public void sellCard(int cardID) {

    }
}
