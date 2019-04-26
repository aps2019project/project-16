package view.views;

import contracts.GraveyardContract;
import models.card.Card;

import java.util.ArrayList;

public class GraveyardView implements GraveyardContract.View {
    private GraveyardContract.Controller controller;

    @Override
    public void setController(GraveyardContract.Controller controller) {
        this.controller = controller;
    }

    // TODO: 4/26/19 implement all of functions :)))

    @Override
    public void showCard(Card card) {

    }

    @Override
    public void showCards(ArrayList<Card> cards) {

    }
}
