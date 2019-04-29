package contracts;

import models.Usable;
import models.card.Card;
import models.card.Hero;
import models.Item;

import java.util.ArrayList;

public interface ShopContract {
    interface View {
        void setController(Controller controller);

        void showShopSearchResult(String cardName, String message);
        void showCollectionSearchResult(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards);
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
