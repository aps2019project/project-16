package models;

public class AccountScore {
    private int accountName;
    private int wins;

    public int getAccountName() {
        return accountName;
    }

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        wins++;
    }
}
