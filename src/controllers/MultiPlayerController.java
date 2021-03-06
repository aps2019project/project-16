package controllers;

import contracts.MultiPlayerContract;
import models.Account;
import models.Game;
import models.GameContents;
import models.GameMode;
import models.net.Server;
import models.net.updates.gameUpdates.GameStartedUpdate;
import view.Notify;

public class MultiPlayerController implements MultiPlayerContract.Controller {
    private MultiPlayerContract.View view;

    public MultiPlayerController(MultiPlayerContract.View view) {
        this.view = view;
        view.setController(this);
    }

    public MultiPlayerController() {

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
            currentAccount.setOpponentAccount(secondAccount);
            Notify.logMessage("You selected \"" + secondUserName + "\" as second account.");
        }
    }

    @Override
    public void loadSecondAccount() {
//        Account secondAccount = GameContents
//        .getSecondAccount();
//        if (secondAccount == null) {
//            Notify.logMessage("You didn't select any account as second player.");
//        } else {
//            view.showSecondAccount(secondAccount);
//        }
    }

    @Override
    public void startMultiGame(int mode, int numberOfFlags) {
        Account currentAccount = GameContents.getCurrentAccount();
        Account secondAccount = currentAccount.getOpponentAccount();

        if (secondAccount == null) {
            Notify.logError("Sorry! You didn't select any account as second player.");
            return;
        }

        GameMode gameMode = getGameMode(mode);

        new Game(currentAccount, secondAccount, 1000, gameMode, numberOfFlags);

        Server.getInstance().sendPacketTo(currentAccount.getName(), new GameStartedUpdate());
        Server.getInstance().sendPacketTo(secondAccount.getName(), new GameStartedUpdate());
    }

    public static GameMode getGameMode(int mode) {
        switch (mode) {
            case 1:
                return GameMode.KILLING_HERO;
            case 2:
                return GameMode.KEEP_FLAG;
            case 3:
                return GameMode.COLLECT_FLAG;
        }
        return GameMode.KILLING_HERO;
    }
}
