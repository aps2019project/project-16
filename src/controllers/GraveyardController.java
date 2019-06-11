package controllers;

import contracts.GraveyardContract;
import models.Game;
import models.GameContents;
import models.Graveyard;
import models.card.Card;
import view.Notify;
import view.views.GraveyardView;

public class GraveyardController implements GraveyardContract.Controller {
    private GraveyardContract.View view;

    public GraveyardController() {
        view = new GraveyardView();
        view.setController(this);
    }

    @Override
    public void loadCard(String cardName, int gameID) {
        Graveyard graveyard = GameContents.getCurrentGame().getCurrentPlayer().getGraveYard();
        Card card = graveyard.getCard(cardName, gameID);
        if (card == null) {
            Notify.logError("This card doesn't exist in graveyard.");
        } else {
            view.showCard(card);
        }
    }

    @Override
    public void loadCards() {
        Graveyard graveyard = GameContents.getCurrentGame().getCurrentPlayer().getGraveYard();
        view.showCards(graveyard.getCards());
    }
}
