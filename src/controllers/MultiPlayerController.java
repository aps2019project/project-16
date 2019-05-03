package controllers;

import contracts.MultiPlayerContract;
import models.Account;
import models.Game;
import models.GameContents;
import models.GameMode;
import view.Notify;
import view.views.MultiPlayerView;

public class MultiPlayerController implements MultiPlayerContract.Controller {
    private MultiPlayerContract.View view;

    public MultiPlayerController() {
        view = new MultiPlayerView();
        view.setController(this);
    }

    @Override
    public void selectOppUser(String secondUserName) {
        Account currentAccount = GameContents.getCurrentAccount();
        Account secondAccount = GameContents.findAccount(secondUserName);
        if (currentAccount.getName().equalsIgnoreCase(secondUserName)) {
            Notify.logError("You can't play with yourself :|");
        } else if (secondAccount == null) {
            Notify.logError("Account with this name doesn't exist!");
        } else if (!secondAccount.hasValidMainDeck()) {
            Notify.logError("OOPS! This account doesn't have valid main deck. Try another one!");
        } else {
            GameContents.setSecondAccount(secondAccount);
            Notify.logMessage("You selected \"" + secondUserName + "\" as second account.");
        }
    }

    @Override
    public void loadSecondAccount() {
        Account secondAccount = GameContents.getSecondAccount();
        if (secondAccount == null) {
            Notify.logMessage("You didn't select any account as second player.");
        } else {
            view.showSecondAccount(secondAccount);
        }
    }

    @Override
    public void startMultiGame(int mode, int numberOfFlags) {
        Account currentAccount = GameContents.getCurrentAccount();
        Account secondAccount = GameContents.getSecondAccount();
        GameMode gameMode = null;

        if (secondAccount == null) {
            Notify.logError("Sorry! You didn't select any account as second player.");
            return;
        }

        switch (mode) {
            case 1:
                gameMode = GameMode.KILLING_HERO;
                break;
            case 2:
                gameMode = GameMode.KEEP_FLAG;
                break;
            case 3:
                gameMode = GameMode.COLLECT_FLAG;
                break;
        }
        Game newGame = new Game(currentAccount, secondAccount, 1000, gameMode, numberOfFlags);
        GameContents.setCurrentGame(newGame);
        view.goToInGameMenu();
    }
}
