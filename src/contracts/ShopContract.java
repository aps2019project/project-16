package contracts;

import models.card.Card;
import models.card.Hero;
import models.item.Item;

import java.util.ArrayList;

public interface ShopContract {
    interface View {
        void setController(Controller controller);

        void showShop(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards);

        void showCollection(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards);

        void showCard(Object card);
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
