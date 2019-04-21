package contracts;

import models.Card;
import models.Hero;

import java.util.ArrayList;

public interface ShopContract {
    interface View {
        void setController(Controller controller);

        // TODO: 4/20/19  void showCollection(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards);
        void showShopSearchResult(String cardName, String message);
        // todo: void showCollectionSearchResult(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards);
        //  void showShop(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards);
        void showBuyResult(String cardName, String message);
        void showSellError(String cardID, String message);
        void showSellSuccessMSG(String cardName, String cardID);
    }

    interface Controller {
        void loadCollection();
        void loadShop();
        void searchInShop(String cardName);
        void searchInCollection(String cardName);
        void buyCard(String cardName);
        void sellCard(String cardID);
    }
}
