package models;

public class MatchDetail {
    private String firstPlayerName;
    private String secondPlayerName;
    private boolean firstIsWinner;
    private int matchID;

    public MatchDetail(String firstPlayerName, String secondPlayerName, int matchID) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
        this.matchID = matchID;
    }

    public MatchDetail(String firstPlayerName, String secondPlayerName, boolean firstIsWinner, int matchID) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
        this.firstIsWinner = firstIsWinner;
        this.matchID = matchID;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public boolean isFirstIsWinner() {
        return firstIsWinner;
    }

    public int getMatchID() {
        return matchID;
    }
}
