package view.views;

import contracts.AccountContract;
import models.Account;
import view.Notify;

import java.util.ArrayList;


public class AccountView implements AccountContract.View {

    @Override
    public void setController(AccountContract.Controller controller) {

    }

    @Override
    public void showLeaderboard(ArrayList<Account> accounts) {
        int i = 0;
        Notify.logMessage("Leaderboard: ");
        for (Account account : accounts) {
            i++;
            System.out.printf("\t%d : UserName: %s - Wins: %d\n", i, account.getName(), account.getWins());
        }
    }

}
