package controllers;

import contracts.AccountContract;
import view.views.AccountView;

public class AccountController implements AccountContract.Controller {
    private AccountContract.View view;

    public AccountController() {
        view = new AccountView();
        view.setController(this);
    }

    // TODO: 4/21/19  implement all of functions :)))

    @Override
    public void loadLeaderboard() {

    }

    @Override
    public void createAccount(String username, String password) {

    }

    @Override
    public void loginAccount(String username, String password) {

    }

}
