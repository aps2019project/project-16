package view.views;

import contracts.AccountContract;
import models.Leaderboard;
import view.MenuHandler;

import static view.Notify.*;
import static view.menuItems.MenuConstants.*;

public class AccountView implements AccountContract.View {
    private AccountContract.Controller controller;

    @Override
    public void setController(AccountContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void usernameProblemError(String message) {
        logError("There is a problem with username: " + message);
    }

    @Override
    public void passwordProblemError(String message) {
        logError("There is a problem with password: " + message);
    }

    @Override
    public void loginSuccessMSG(String username) {
        logMessage("dear " + username + "!! you've logged in successfully.");
        MenuHandler.goToSubMenu(MAIN_MENU);
    }

    @Override
    public void showLeaderboard(Leaderboard leaderboard) {
        // TODO: 4/19/19 show leaderborad
    }
}
