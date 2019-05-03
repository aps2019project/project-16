package contracts;

import models.card.Card;

import java.util.ArrayList;

public interface GraveyardContract {
    interface View {
        void setController(Controller controller);

        void showCard(Card card);
        void showCards(ArrayList<Card> cards);
    }

    interface Controller {
        void loadCard(String cardName, int gameID);
        void loadCards();
    }
}
