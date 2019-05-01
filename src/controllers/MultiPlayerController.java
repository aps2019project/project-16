package controllers;

import contracts.MultiPlayerContract;
import view.views.MultiPlayerView;

public class MultiPlayerController implements MultiPlayerContract.Controller {
    private MultiPlayerContract.View view;

    public MultiPlayerController() {
        view = new MultiPlayerView();
        view.setController(this);
    }

    // TODO: 5/1/19  implement functions

    @Override
    public void selectOppUser(String secondUserName) {

    }

    @Override
    public void loadSecondAccount() {

    }

    @Override
    public void startMultiGame(int mode, int numberOfFlags) {

    }
}
