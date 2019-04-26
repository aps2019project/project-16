package controllers;

import contracts.GraveyardContract;
import view.views.GraveyardView;

public class GraveyardController implements GraveyardContract.Controller{
    private GraveyardContract.View view;

    public GraveyardController() {
        view = new GraveyardView();
        view.setController(this);
    }

    // TODO: 4/26/19 implement all of functions :)))

    @Override
    public void loadCard(String cardID) {

    }

    @Override
    public void loadCards() {

    }
}
