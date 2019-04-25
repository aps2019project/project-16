package models;

import java.util.ArrayList;

public class Leaderboard {
    private ArrayList<AccountScore> scores;

    public ArrayList<AccountScore> getScores() {
        return scores;
    }

    private void save() {
        //todo must be implemented (sepehr)
    }

    public void incrementWins(String accountName) {
        Account.getAccount(accountName).increaseWin();
    }
    public  void addScore(AccountScore accountScore){
        scores.add(accountScore);
    }
    public  void removeScore(AccountScore accountScore){
        scores.removeIf(accountScore1 -> accountScore1.equals(accountScore));
    }
}
