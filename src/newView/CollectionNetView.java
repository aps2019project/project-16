package newView;

import contracts.CollectionContract;
import models.Deck;
import models.card.Card;
import models.card.Hero;
import models.item.Item;
import models.net.Server;
import models.net.updates.collectionUpdates.ShowAllDecksUpdate;
import models.net.updates.collectionUpdates.ShowCardsUpdate;
import models.net.updates.collectionUpdates.ShowCollectionUpdate;
import models.net.updates.collectionUpdates.ShowDeckUpdate;

import java.util.ArrayList;
import java.util.List;

public class CollectionNetView implements CollectionContract.View {
    @Override
    public void setController(CollectionContract.Controller controller) {
    }

    @Override
    public void showAllDecks(Deck mainDeck, ArrayList<Deck> decks) {
        Server.getInstance().sendPacketByThread(new ShowAllDecksUpdate(mainDeck, decks));
    }

    @Override
    public void showDeck(Deck deck) {
        Server.getInstance().sendPacketByThread(new ShowDeckUpdate(deck));
    }

    @Override
    public void showCollection(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {
        Server.getInstance().sendPacketByThread(new ShowCollectionUpdate(heroes, items, cards));
    }

    @Override
    public void showCards(List<Object> cards) {
        Server.getInstance().sendPacketByThread(new ShowCardsUpdate(cards));
    }

    @Override
    public void goToBattleMenu() {
    }
}
