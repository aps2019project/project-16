package controllers;

import contracts.CustomGameContract;
import models.Deck;
import models.GameContents;
import models.GameLevel;
import view.Notify;
import view.views.CustomGameView;

import java.util.ArrayList;

public class CustomGameController implements CustomGameContract.Controller {
    private CustomGameContract.View view;

    public CustomGameController() {
        view = new CustomGameView();
        view.setController(this);
    }

    @Override
    public void startGame(String oppDeckName, int mode, int flags) {
        if (!GameContents.hasOppDeck(oppDeckName)) {
            Notify.logError("Oh No! Opponent doesn't have this deck.");
            return;
        }
        Deck oppDeck = GameContents.getOpponentDeck(oppDeckName);
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

    @Override
    public void loadDecks() {
        ArrayList<GameLevel> gameLevels = GameContents.getGameLevels();
        ArrayList<Deck> decks = new ArrayList<>();
        for (GameLevel gameLevel : gameLevels) {
            decks.add(gameLevel.getDeck());
        }
        view.showDecks(decks);
    }
}
