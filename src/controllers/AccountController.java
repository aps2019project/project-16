package controllers;

import contracts.AccountContract;
import view.views.AccountView;

public class AccountController implements AccountContract.Controller {
    private AccountContract.View view;

    public AccountController() {
        view = new AccountView();
        view.setController(this);
    }

    @Override
    public void loadLeaderboard() {
        // TODO: 4/19/19 loadLeaderboard
    }

    @Override
    public void createAccount(String username, String password) {
        // TODO: 4/19/19 check that username not exist then create and success message
    }

    @Override
    public void loginAccount(String username, String password) {
        // TODO: 4/19/19 check that account exist then login
    }

}
