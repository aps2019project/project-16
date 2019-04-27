package controllers;

import contracts.ShopContract;
import models.GameContents;
import models.Item;
import models.Shop;
import models.Usable;
import models.card.Card;
import models.card.Hero;
import view.Notify;
import view.views.ShopView;

import java.util.ArrayList;

import static models.ProgramConstants.MAX_NUMBER_OF_ITEMS;

public class ShopController implements ShopContract.Controller {
    private ShopContract.View view;

    public ShopController() {
        view = new ShopView();
        view.setController(this);
    }

    // TODO: 4/21/19 implement all of functions :)))

    @Override
    public void loadCollection() {
        new CollectionController().loadCollection();
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
    public void buyCard(String cardName) {
        Shop shop = GameContents.getShop();
        String cardType = shop.getType(cardName);
        if (cardType == null) {
            Notify.logError("This card(item) doesn't exist in shop.");
        } else {
            switch (cardType) {
                case "card":
                    buyThisCard(cardName);
                    break;
                case "item":
                    buyThisItem(cardName);
                    break;
            }
        }
    }

    private void buyThisItem(String cardName) {
        Shop shop = GameContents.getShop();
        Item item = shop.getItem(cardName);
        if (item.getBuyPrice() > GameContents.getCurrentAccount().getMoney()) {
            Notify.logError("You don't have enough money to buy this item!");
        } else if (GameContents.getCurrentAccount().getCollection().getItems().size() >= MAX_NUMBER_OF_ITEMS) {
            Notify.logError("You don't have capacity to buy item. maximum number of items:" + MAX_NUMBER_OF_ITEMS);
        } else {
            shop.buyItem(cardName);
            Notify.logMessage("You successfully bought item: \"" + cardName + "\"");
        }
    }

    private void buyThisCard(String cardName) {
        Shop shop = GameContents.getShop();
        Card card = shop.getCard(cardName);
        if (card.getBuyPrice() > GameContents.getCurrentAccount().getMoney()) {
            Notify.logError("You don't have enough money to buy this card!");
        } else {
            shop.buyCard(cardName);
            Notify.logMessage("You successfully bought card: \"" + cardName + "\"");
        }
    }

    @Override
    public void sellCard(int cardID) {

    }
}
