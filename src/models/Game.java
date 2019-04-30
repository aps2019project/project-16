package models;

import models.GameMode;

import java.lang.management.PlatformLoggingMXBean;

public abstract class Game {
    private static final int NUMBER_OF_PLAYERS = 2;
    private Account[] accounts = new Account[NUMBER_OF_PLAYERS];
    private Player[] players = new Player[NUMBER_OF_PLAYERS];
    private Table table = new Table();
    private Player currentPlayer;
    private int turn;
    private int maxMana;
    private int reward;
    private GameMode gameMode;
    private Player winner;
    private boolean endFlag;

    private int numberOfFlags ;

    public Game(Account firstAccount, Account secondAccount, int reward, GameMode gameMode) {
        this.maxMana = 4;
        this.turn = 0;
        this.reward = reward;
        this.gameMode = gameMode;
    }

    public int getNumberOfFlags() {
        return numberOfFlags;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void startTurn() {
        setPlayerTurn();
        setMana();

    }

    public void endTurn() {
        if (checkWinningCondition()){
            this.endFlag = true;
            this.getRewardToWinner();
        }
        ///decrement duration of spells
    }

    private void setMana() {
        this.maxMana++;
        players[0].setMana(this.maxMana);
        players[1].setMana(this.maxMana);
    }

    private void getRewardToWinner(){
        if (this.winner.equals(players[0]))
            accounts[0].increaseMoney(this.reward);
        if (this.winner.equals(players[1]))
            accounts[1].increaseMoney(this.reward);
    }

    public boolean checkWinningCondition() {
        GameMode gameMode = this.gameMode;
        if (gameMode == GameMode.KILLING_HERO)
            return checkKillingHero();
        if (gameMode == GameMode.KEEP_FLAG)
            return checkKeepFlag();
        if (gameMode == GameMode.COLLECT_FLAG)
            return checkCollectFlags();
        return false;
    }

    private boolean checkKillingHero() {
        Player player1 = this.players[0];
        Player player2 = this.players[1];
        if (player1.getHero().getHp() <= 0) {
            setWinner(player1);
            return true;
        }
        if (player2.getHero().getHp() <= 0) {
            setWinner(player2);
            return true;
        }
        return false;
    }

    private boolean checkKeepFlag() {
        Player player1 = this.players[0];
        Player player2 = this.players[1];
        if (player1.getTurnsFlagKeeped() == 6) {
            setWinner(player1);
            return true;
        }
        if (player2.getTurnsFlagKeeped() == 6) {
            setWinner(player2);
            return true;
        }
        return false;
    }

    private boolean checkCollectFlags() {
        Player player1 = this.players[0];
        Player player2 = this.players[1];
        int number = this.getNumberOfFlags() / 2;
        if (player1.getNumberOfColectedFlags() >= number) {
            setWinner(player1);
            return true;
        }
        if (player2.getNumberOfColectedFlags() >= number) {
            setWinner(player2);
            return true;
        }
        return false;
    }

    private void setPlayerTurn() {
        if (turn % 2 == 0)
            this.currentPlayer = players[0];
        else
            this.currentPlayer = players[1];
    }
}
