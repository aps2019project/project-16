package controllers;

import contracts.AccountContract;
import models.Account;
import models.GameContents;
import models.net.AuthTokenGenerator;
import models.net.RequestHandlerThread;
import models.net.Server;
import models.net.updates.LoginSuccessUpdate;
import view.Notify;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AccountController implements AccountContract.Controller {
    private AccountContract.View view;

    public AccountController(AccountContract.View view) {
        this.view = view;
        view.setController(this);
    }

    public AccountController() {

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
        if (account == null || !account.getPassword().equals(password)) {
            Notify.logError("Invalid Credentials!");
        } else {
            ((RequestHandlerThread) Thread.currentThread()).setAccountName(username);
            String authToken = AuthTokenGenerator.generateNewToken();
            ((RequestHandlerThread) Thread.currentThread()).getServerSideClient().setAuthToken(authToken);
            Server.getInstance().sendPacketByThread(new LoginSuccessUpdate(authToken));
            Notify.logMessage("Dear " + username + "!!! You logged in successfully!");
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
