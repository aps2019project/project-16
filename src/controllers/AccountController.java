package controllers;

import contracts.AccountContract;
import exception.AccountExistsException;
import exception.InvalidCredentialsException;
import models.Account;
import models.GameContents;
import models.net.Server;
import models.net.updates.RequestResultUpdate;
import view.MenuHandler;
import view.Notify;

import java.io.FileNotFoundException;
import java.io.IOException;

import static view.menuItems.MenuConstants.MAIN_MENU;

public class AccountController implements AccountContract.Controller {
    private AccountContract.View view;

    public AccountController(AccountContract.View view) {
        this.view = view;
        view.setController(this);
    }

    public AccountController(){

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
            Server.getInstance().sendPacketByThread(
                    new RequestResultUpdate("An account with this username is already exist! Try another username."));
        } else {
            GameContents.addAccount(new Account(username, password));
            Server.getInstance().sendPacketByThread(
                    new RequestResultUpdate("Good job! An account with name \"" + username + "\" created."));
        }
    }

    @Override
    public void loginAccount(String username, String password) {
        Account account = GameContents.findAccount(username);
        if (account == null || !account.getPassword().equals(password)) {
            Notify.logError("Invalid Credentials!");
        } else {
            GameContents.setCurrentAccount(account);
            Notify.logMessage("Dear " + username + "!!! You logged in successfully!");
//            MenuHandler.goToSubMenu(MAIN_MENU);
        }
    }

    @Override
    public void saveGameData() {
        try {
            GameContents.saveAccount(GameContents.getCurrentAccount());
            Notify.logMessage("Your account saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadAccounts() {
        try {
            GameContents.loadAccounts();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
