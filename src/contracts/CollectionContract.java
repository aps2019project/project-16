package contracts;

import models.card.Card;
import models.Deck;
import models.card.Hero;
import models.Item;

import java.util.ArrayList;

public interface CollectionContract {
    interface View {
        void setController(Controller controller);

        void showAllDecks(ArrayList<Deck> decks);
        void showDeck(Deck deck);
        void showDeckProblemError(String message);
        void showCollection(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards);
        void showSearchResult(ArrayList<Item> items, ArrayList<Card> cards);
        void deckCreationError(String message);
        void deckCreationSuccessMSG(String deckName);
        void deckDeletionError(String message);
        void deckDeletionSuccessMSG(String deckName);
        void collectionSaveSuccessMSG();
        void addCardProblemError(String message);
        void addCardSuccessMSG(String cardName, String deckName);
        void removeCardProblemError(String message);
        void removeCardSuccessMSG(String cardName, String deckName);
        void mainDeckSelectionSuccessMSG(String deckName);
        void showDeckValidationStatus(String deckName, String message);
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
    }
}
