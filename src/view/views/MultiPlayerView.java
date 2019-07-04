package view.views;

import contracts.MultiPlayerContract;
import models.Account;
import view.MenuHandler;
import view.Notify;

import static view.menuItems.MenuConstants.IN_GAME_MENU;

public class MultiPlayerView implements MultiPlayerContract.View {
    private MultiPlayerContract.Controller controller;

    @Override
    public void setController(MultiPlayerContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showSecondAccount(Account secondAccount) {
        Notify.logMessage("Selected account as second player: \"" + secondAccount.getName() + "\"");
    }

    @Override
    public void goToInGameMenu() {
//        MenuHandler.goToSubMenu(IN_GAME_MENU);
    }
}
