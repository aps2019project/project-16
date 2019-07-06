package controllers;

import contracts.CustomGameContract;
import models.*;
import models.artificialIntelligence.AIAccount;
import models.net.Server;
import models.net.updates.gameUpdates.GameStartedUpdate;
import view.Notify;

public class CustomGameController implements CustomGameContract.Controller {
    private CustomGameContract.View view;

    public CustomGameController(CustomGameContract.View view) {
        this.view = view;
        view.setController(this);
    }

    public CustomGameController() {

    }

    @Override
    public void startGame(String oppDeckName, int mode, int flags) {
        Deck oppDeck = GameContents.getOpponentDeck(oppDeckName);

        if (oppDeck == null || !GameContents.hasOppDeck(oppDeckName)) {
            Notify.logError("Oh No! Opponent doesn't have this deck.");
            return;
        }

        GameMode gameMode = MultiPlayerController.getGameMode(mode);

        Account currentAccount = GameContents.getCurrentAccount();
        Account AIAccount = new AIAccount("AI", "123", oppDeck.getCopy());

        Server.getInstance().sendPacketByThread(new GameStartedUpdate());
        new Game(currentAccount, AIAccount, 1000, gameMode, flags);
    }

    @Override
    public void loadDecks() {
//        ArrayList<GameLevel> gameLevels = GameContents.getGameLevels();
//        ArrayList<Deck> decks = new ArrayList<>();
//        for (GameLevel gameLevel : gameLevels) {
//            decks.add(gameLevel.getDeck());
//        }
//        view.showDecks(decks);
    }
}
