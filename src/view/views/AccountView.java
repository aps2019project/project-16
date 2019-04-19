package view.views;

import contracts.AccountContract;
import models.Leaderboard;
import view.MenuHandler;
import static view.menuItems.MenuConstants.*;

public class AccountView implements AccountContract.View {
    private AccountContract.Controller controller;

    @Override
    public void setController(AccountContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showUsernameProblemError(String message) {
        System.err.println("There is a problem with username: " + message);
    }

    @Override
    public void showPasswordProblemError(String message) {
        System.err.println("There is a problem with password: " + message);
    }

    @Override
    public void loginSuccessMSG(String username) {
        System.out.println("dear " + username + "!! you've logged in successfully.");
        MenuHandler.goToSubMenu(MAIN_MENU);
    }

    @Override
    public void showLeaderboard(Leaderboard leaderboard) {
        // TODO: 4/19/19 show leaderborad
    }
}
