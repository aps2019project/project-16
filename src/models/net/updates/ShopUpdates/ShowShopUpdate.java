package models.net.updates.ShopUpdates;

import models.card.Card;
import models.card.Hero;
import models.item.Item;
import models.net.Client;
import models.net.UpdatePacket;

import java.util.ArrayList;

public class ShowShopUpdate extends UpdatePacket {
    private ArrayList<Hero> heroes;
    private ArrayList<Card> cards;
    private ArrayList<Item> items;

    public ShowShopUpdate(ArrayList<Hero> heroes, ArrayList<Card> cards, ArrayList<Item> items) {
        this.heroes = heroes;
        this.cards = cards;
        this.items = items;
    }

    @Override
    public void update() {
        Client.getInstance().getShopSceneMaker().showShop(heroes, items, cards);
    }
}
