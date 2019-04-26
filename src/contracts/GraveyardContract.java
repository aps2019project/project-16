package contracts;

import models.Card;

import java.util.ArrayList;

public interface GraveyardContract {
    interface View {
        void setController(Controller controller);

        void showCard(Card card);
        void showCards(ArrayList<Card> cards);
    }

    interface Controller {
        void loadCard(String cardID);
        void loadCards();
    }
}
