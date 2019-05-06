package models;

import java.util.ArrayList;

public class Leaderboard {
    private ArrayList<AccountScore> scores;

    public ArrayList<AccountScore> getScores() {
        return scores;
    }

    private void save() {
        //phase 2: must be implemented (sepehr)
    }

    public  void addScore(AccountScore accountScore){
        scores.add(accountScore);
    }
    public  void removeScore(AccountScore accountScore){
        scores.removeIf(accountScore1 -> accountScore1.equals(accountScore));
    }
}
