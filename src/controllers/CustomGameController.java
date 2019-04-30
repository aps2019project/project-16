package controllers;

import contracts.CustomGameContract;
import view.views.CustomGameView;

public class CustomGameController implements CustomGameContract.Controller {
    private CustomGameContract.View view;

    public CustomGameController() {
        view = new CustomGameView();
        view.setController(this);
    }

    @Override
    public void startGame(int mode, int flags) {
        // TODO: 4/30/19
        switch (mode) {
            case 1:
                //kill hero
                break;
            case 2:
                //hold flag
                break;
            case 3:
                //collect flags
                break;
        }
    }
}
