package contracts;

import models.card.Card;
import models.card.Hero;
import models.item.Item;

import java.util.ArrayList;

public interface ShopContract {
    interface View {
        void showShop(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards);
    }

    interface Controller {
        void loadCollection();
        void loadShop();
        void searchInShop(String cardName);
        void searchInCollection(String cardName);
        void buyCard(String cardName);
        void sellCard(int cardID);
    }
}
