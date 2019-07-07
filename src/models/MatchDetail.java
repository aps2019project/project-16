package models;

public class MatchDetail {
    private String firstPlayer;
    private String secondPlayer;
    private boolean firstIsWinner;
    private int matchID;

    public MatchDetail(String firstPlayer, String secondPlayer, int matchID) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.matchID = matchID;
    }

    public MatchDetail(String firstPlayer, String secondPlayer, boolean firstIsWinner, int matchID) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.firstIsWinner = firstIsWinner;
        this.matchID = matchID;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public boolean isFirstIsWinner() {
        return firstIsWinner;
    }

    public int getMatchID() {
        return matchID;
    }
}
