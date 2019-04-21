package models;

public abstract class Game {
    private static final int NUMBER_OF_PLAYERS = 2;
    private Account[] accounts = new Account[NUMBER_OF_PLAYERS];
    private Player[] players = new Player[NUMBER_OF_PLAYERS];
    private Table map = new Table();
    private int turn = 0;

    protected Game(Account firstAccount, Account secondAccount, int reward) {

    }

    public void startTurn() {

    }

    public void endTurn() {

    }

    private void setMana() {

    }

    protected abstract boolean checkWinningCondition();
}
