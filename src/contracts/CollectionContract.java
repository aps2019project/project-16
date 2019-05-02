package contracts;

import models.card.Card;
import models.Deck;
import models.card.Hero;
import models.Item;

import java.util.ArrayList;

public interface CollectionContract {
    interface View {
        void showAllDecks(Deck mainDeck, ArrayList<Deck> decks);
        void showDeck(Deck deck);
        void showCollection(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards);

        void goToBattleMenu();
    }

    interface Controller {
        void loadCollection();
        void loadAllDecks();
        void loadDeck(String deckName);
        void searchCard(String name);
        void saveCollection();
        void createDeck(String deckName);
        void deleteDeck(String deckName);
        void addCardToDeck(int cardID, String deckName);
        void removeCardFromDeck(int cardID, String deckName);
        void validateDeck(String deckName);
        void selectDeck(String deckName);

        void validateMainDeckForEnterBattle();
    }
}
