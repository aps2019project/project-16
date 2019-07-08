package models.net.updates.ShopUpdates;

import models.card.Card;
import models.card.Hero;
import models.item.Item;
import models.net.Client;
import models.net.UpdatePacket;

import java.util.ArrayList;

public class ShowCollectionUpdate extends UpdatePacket {
    private ArrayList<Hero> heroes;
    private ArrayList<Card> cards;
    private ArrayList<Item> items;

    public ShowCollectionUpdate(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {
        this.heroes = heroes;
        this.cards = cards;
        this.items = items;
    }

    @Override
    public void update() {
        Client.getInstance().getShopSceneMaker().showCollection(heroes, items, cards);
    }
}
