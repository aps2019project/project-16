package models;

import models.card.Unit;
import models.card.exception.GameIsEndException;

import java.util.ArrayList;

public class Game {
    private static final int NUMBER_OF_PLAYERS = 2;
    private static final int KEEPING_TURNS = 6;
    private Account[] accounts = new Account[NUMBER_OF_PLAYERS];
    private Player[] players = new Player[NUMBER_OF_PLAYERS];
    private Table table = new Table();
    private Player currentPlayer;
    private Player opponentPlayer;
    private int turn = 0;
    private int reward;
    private GameMode gameMode;
    private Player winner;
    private boolean endFlag = false;
    private int numberOfFlags;

    public Game(Account firstAccount, Account secondAccount, int reward, GameMode gameMode, int numberOfFlags) {
        this.reward = reward;
        this.gameMode = gameMode;
        this.currentPlayer = this.players[0] = firstAccount.getPlayer();
        this.opponentPlayer = this.players[1] = secondAccount.getPlayer();
        this.players[0].setTable(table);
        this.players[1].setTable(table);
        this.accounts[0] = firstAccount;
        this.accounts[1] = secondAccount;
        this.numberOfFlags = numberOfFlags;
    }

    public int getNumberOfFlags() {
        return numberOfFlags;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Table getTable() {
        return table;
    }

    public Player getPlayer(String playerName) {
        for (Player player : players) {
            if (player.getAccount().getName().equalsIgnoreCase(playerName)) {
                return player;
            }
        }
        return null;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void startTurn() throws GameIsEndException {
        setCurrentPlayer();
        setMana();
        doUnitsBuffs();
        checkIfAnyoneIsDead();
        gameIsEnd();
        //todo merge start and end turn
        //todo call AI to act then end turn for AI
    }

    public void doUnitsBuffs() {
        for (int row = 0; row < Table.HEIGHT; row++) {
            for (int column = 0; column < Table.WIDTH; column++) {
                if (table.getCell(row, column).hasUnit()) {
                    table.getCell(row, column).getUnit().nextTurn();
                }
            }
        }
    }

    public void doCellBuffs() {
        for (int row = 0; row < Table.HEIGHT; row++) {
            for (int column = 0; column < Table.WIDTH; column++) {
                table.getCell(row, column).doBuffs();
            }
        }
    }

    public void endTurn() throws GameIsEndException {
        doCellBuffs();
        checkIfAnyoneIsDead();
        gameIsEnd();
        this.turn++;
        //todo cast especial power unit
        incrementTurnFlagKeeped();
        gameIsEnd();

    }

    private void gameIsEnd() throws GameIsEndException {
        if (checkWinningCondition()) {
            this.endFlag = true;
            this.getRewardToWinner();
            throw new GameIsEndException();
        }
    }

    private void setMana() {
        players[0].setMana((turn + 1) / 2 + 2);
        players[1].setMana((turn + 1) / 2 + 2);
    }

    private void getRewardToWinner() {
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
        // TODO: 5/4/19 if getHero == null
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
        if (player1.getTurnsFlagKeeped() == KEEPING_TURNS) {
            setWinner(player1);
            return true;
        }
        if (player2.getTurnsFlagKeeped() == KEEPING_TURNS) {
            setWinner(player2);
            return true;
        }
        return false;
    }

    private boolean checkCollectFlags() {
        Player player1 = this.players[0];
        Player player2 = this.players[1];
        int number = this.getNumberOfFlags() / 2;
        if (player1.getNumberOfCollectedFlags() >= number) {
            setWinner(player1);
            return true;
        }
        if (player2.getNumberOfCollectedFlags() >= number) {
            setWinner(player2);
            return true;
        }
        return false;
    }

    private void setCurrentPlayer() {
        if (turn % 2 == 0) {
            this.currentPlayer = players[0];
            this.opponentPlayer = players[1];
        } else {
            this.currentPlayer = players[1];
            this.opponentPlayer = players[0];
        }
    }

    public void checkIfAnyoneIsDead() {
        for (Player player : players) {
            for (Unit unit : player.getUnits()) {
                if (unit.isDead()) {
                    unit.getCurrentCell().setUnit(null);
                    unit.dropFlags(unit.getCurrentCell(), unit);
                    // TODO: 5/4/19 check and do ON_DEATH
                    player.getUnits().removeIf(x -> x.equals(unit));
                    player.getGraveYard().addCard(unit);
                }
            }
        }
    }

    //for gameMode: "keep flag" and collect flags
    public ArrayList<Flag> getFlags() {
        // TODO: 5/3/19 must implement by Sepehr
        return null;
    }

    public void incrementTurnFlagKeeped() {
        if (this.getGameMode() == GameMode.KEEP_FLAG) {
            if (this.players[0].hasFlag())
                this.players[0].incrementTurnsFlagKeeped();
            if (this.players[1].hasFlag())
                this.players[1].incrementTurnsFlagKeeped();
        }
    }
}
