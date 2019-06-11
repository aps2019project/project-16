package view.views;

import contracts.GraveyardContract;
import models.card.Card;
import view.Notify;

import java.util.ArrayList;

public class GraveyardView implements GraveyardContract.View {
    private GraveyardContract.Controller controller;

    @Override
    public void setController(GraveyardContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showCard(Card card) {
        new InGameView().showCardInfo(card);
    }

    @Override
    public void showCards(ArrayList<Card> cards) {
        Notify.logMessage("*Graveyard cards:");
        InGameView inGameView = new InGameView();
        for (Card card : cards) {
            inGameView.showCardInfo(card);
        }
    }
}
