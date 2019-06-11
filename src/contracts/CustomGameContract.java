package contracts;

import models.Deck;

import java.util.ArrayList;

public interface CustomGameContract {
    interface View {
        void setController(Controller controller);

        void goToInGameMenu();
        void showDecks(ArrayList<Deck> decks);
    }

    interface Controller {
        void startGame(String oppDeckName, int mode, int flags);
        void loadDecks();
    }
}
