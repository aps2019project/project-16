package contracts;

import models.*;
import models.card.Card;
import models.card.Hero;
import models.card.Minion;

import java.util.ArrayList;

public interface InGameContract {
    interface View {
        void setController(Controller controller);

        void showGameInfo(Game game);
        void showMinions(String playerName, Hero hero, ArrayList<Minion> minions);
        void showCardInfo(Card card);
        void showHand(Hand hand, Card nextCard);
        void showCollectables(ArrayList<Collectible> collectibles);
        void showCollectableInfo(Collectible collectible);
        void showNextCard(Card card);
    }

    interface Controller {
        void loadGameInfo();
        void loadMinions(boolean myMinions);
        void loadCardInfo(String cardID);
        void selectCard(String cardID);
        void moveToCell(int x, int y);
        void attack(String oppCardID);
        void attackCombo(String oppCardID, ArrayList<String> myCardIDs);
        void useSpecialPower(int x, int y);
        void loadHand();
        void insertCard(String cardName, int x, int y);
        void endTurn();
        void loadCollectables();
        void selectCollectable(int collectableID);
        void loadSelectedCollectableInfo();
        void useSelectedCollectable(int x, int y);
        void loadNextCard();
        void loadGameHint();
        void finishTheGame();
    }
}
