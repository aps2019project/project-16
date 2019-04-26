package controllers;

import contracts.ShopContract;
import models.GameContents;
import models.Item;
import models.Shop;
import models.Usable;
import models.card.Card;
import models.card.Hero;
import view.views.ShopView;

import java.util.ArrayList;

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
        Shop shop = GameContents.getShop();
        ArrayList<Usable> items = shop.getItems();
        ArrayList<Hero> heroes = new ArrayList<>();
        ArrayList<Card> cards = new ArrayList<>();
        for (Card card : shop.getCards()) {
            if (card.getClass().equals(Hero.class)) {
                heroes.add((Hero) card);
            } else {
                cards.add(card);
            }
        }
        view.showShop(heroes, items, cards);
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
