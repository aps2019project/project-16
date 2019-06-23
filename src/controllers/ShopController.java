package controllers;

import contracts.ShopContract;
import models.*;
import models.card.Card;
import models.card.Hero;
import models.item.Item;
import view.Notify;
import view.views.CollectionView;

import java.util.ArrayList;

import static models.Collection.ITEM_CAPACITY;

public class ShopController implements ShopContract.Controller {
    private ShopContract.View view;

    public ShopController(ShopContract.View view) {
        this.view = view;
        view.setController(this);
    }

    @Override
    public void loadCollection() {
        Collection collection = GameContents.getCurrentAccount().getCollection();
        ArrayList<Hero> heroes = new ArrayList<>();
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Item> items = collection.getItems();
        for (Card card : collection.getCards()) {
            if (card.getClass().equals(Hero.class)) {
                heroes.add((Hero) card);
            } else {
                cards.add(card);
            }
        }
        view.showCollection(heroes, items, cards);
    }

    @Override
    public void loadShop() {
        Shop shop = GameContents.getShop();
        ArrayList<Item> items = shop.getItems();
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
        Shop shop = GameContents.getShop();
        if (shop.getType(cardName) == null) {
            Notify.logError("This card doesn't exist in shop!!!");
        } else {
            Notify.logMessage("Yes! We have this card in shop. You can buy it by its name.");
            if (shop.getCard(cardName) != null)
                view.showCard(shop.getCard(cardName));
            else
                view.showCard(shop.getItem(cardName));
        }
    }

    @Override
    public void searchInCollection(String cardName) {
        new CollectionController(new CollectionView()).searchCard(cardName);
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
            Notify.logMessage("Your current money: " + GameContents.getCurrentAccount().getMoney());
        }
    }

    private void buyThisItem(String cardName) {
        Shop shop = GameContents.getShop();
        Item item = shop.getItem(cardName);
        if (item.getBuyPrice() > GameContents.getCurrentAccount().getMoney()) {
            Notify.logError("You don't have enough money to buy this item!");
        } else if (GameContents.getCurrentAccount().getCollection().getItems().size() >= ITEM_CAPACITY) {
            Notify.logError("You don't have capacity to buy item. maximum number of items:" + ITEM_CAPACITY);
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
        Collection collection = GameContents.getCurrentAccount().getCollection();
        Shop shop = GameContents.getShop();
        String cardType = collection.getType(cardID);
        if (cardType == null) {
            Notify.logError("OOPS!! You don't have this card(item).");
        } else {
            switch (cardType) {
                case "item":
                    shop.sellItem(cardID);
                    Notify.logMessage("You successfully sold item with ID: " + cardID);
                    break;
                case "card":
                    shop.sellCard(cardID);
                    Notify.logMessage("You successfully sold card with ID: " + cardID);
                    break;
            }
            Notify.logMessage("Your current money: " + GameContents.getCurrentAccount().getMoney());
        }
    }
}
