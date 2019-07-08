package models.net.updates.collectionUpdates;

import models.card.Card;
import models.card.Hero;
import models.item.Item;
import models.net.Client;
import models.net.UpdatePacket;

import java.util.ArrayList;

public class ShowCollectionUpdate extends UpdatePacket {
    private ArrayList<Hero> heroes;
    private ArrayList<Item> items;
    private ArrayList<Card> cards;

    public ShowCollectionUpdate(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {
        this.heroes = heroes;
        this.items = items;
        this.cards = cards;
    }

    @Override
    public void update() {
        Client.getInstance().getCollectionSceneMaker().showCollection(heroes, items, cards);
    }
}
