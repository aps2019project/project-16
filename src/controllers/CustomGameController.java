package controllers;

import contracts.CustomGameContract;
import models.*;
import models.artificialIntelligence.AIAccount;
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

        GameMode gameMode = MultiPlayerController.getGameMode(mode);

        Account currentAccount = GameContents.getCurrentAccount();
        Account AIAccount = new AIAccount("AI", "123", oppDeck);

        Game newGame = new Game(currentAccount, AIAccount, 1000, gameMode, flags);
        GameContents.setCurrentGame(newGame);
        view.goToInGameMenu();
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
