package newView.GraphicalElements.scoreBoard;

public class AccountProperty {
    private int money;
    private String accountName;
    private int score;
    private boolean isOnline;

    public AccountProperty(int money, String accountName, int score, boolean isOnline) {
        this.money = money;
        this.accountName = accountName;
        this.score = score;
        this.isOnline = isOnline;
    }

    public int getMoney() {
        return money;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getScore() {
        return score;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public String getIsOnline() {
        if (isOnline)
            return "ONLINE";
        else
            return "OFFLINE";
    }
}
