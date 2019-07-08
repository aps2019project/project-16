package newView;

import contracts.ShopContract;
import models.card.Card;
import models.card.Hero;
import models.item.Item;
import models.net.Server;
import models.net.updates.ShopUpdates.ShowCardUpdate;
import models.net.updates.ShopUpdates.ShowCollectionUpdate;
import models.net.updates.ShopUpdates.ShowShopUpdate;

import java.util.ArrayList;

public class ShopNetView implements ShopContract.View {
    @Override
    public void setController(ShopContract.Controller controller) {

    }

    @Override
    public void showShop(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {
        Server.getInstance().sendPacketByThread(new ShowShopUpdate(heroes, cards, items));
    }

    @Override
    public void showCollection(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {
        Server.getInstance().sendPacketByThread(new ShowCollectionUpdate(heroes, items, cards));
    }

    @Override
    public void showCard(Object card) {
        Server.getInstance().sendPacketByThread(new ShowCardUpdate(card));
    }
}
