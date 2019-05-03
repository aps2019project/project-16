package controllers;

import contracts.AccountContract;
import models.Account;
import models.Game;
import models.GameContents;
import view.MenuHandler;
import view.Notify;
import view.views.AccountView;

import static view.menuItems.MenuConstants.MAIN_MENU;

public class AccountController implements AccountContract.Controller {
    private AccountContract.View view;

    public AccountController() {
        view = new AccountView();
    }

    @Override
    public void loadLeaderboard() {
        GameContents.sortAccounts();
        view.showLeaderboard(GameContents.getAccounts());
    }

    @Override
    public void createAccount(String username, String password) {
        Account account = GameContents.findAccount(username);
        if (account != null) {
            Notify.logError("An account with this username is already exist! Try another username.");
        } else {
            GameContents.addAccount(new Account(username, password));
            Notify.logMessage("Good job! An account with name \"" + username + "\" created.");
        }
    }

    @Override
    public void loginAccount(String username, String password) {
        Account account = GameContents.findAccount(username);
        if (account == null) {
            Notify.logError("This username doesn't exists!");
        } else if (!account.getPassword().equals(password)) {
            Notify.logError("The password is incorrect!");
        } else {
            GameContents.setCurrentAccount(account);
            GameContents.setSecondAccount(null);//phase 3: must change
            Notify.logMessage("Dear " + username + "!!! You logged in successfully!");
            MenuHandler.goToSubMenu(MAIN_MENU);
        }
    }

    @Override
    public void saveGameData() {
        // phase 2: 4/30/19  save data of accounts in files
    }
}
