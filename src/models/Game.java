package models;

import models.artificialIntelligence.AIPlayer;
import models.card.Card;
import models.card.Hero;
import models.card.Unit;
import exception.ArrayIsEmptyException;
import exception.GameIsEndException;
import models.item.Item;
import models.item.ManaItem;
import newView.BattleView.ClientSender;
import newView.BattleView.gameActs.*;
import newView.Type;

import java.util.ArrayList;
import java.util.Random;

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
        UniqueIDGenerator.renewWrappersList();
        this.reward = reward;
        this.gameMode = gameMode;
        this.currentPlayer = this.players[0] = firstAccount.getNewPlayerFromAccount();
        this.opponentPlayer = this.players[1] = secondAccount.getNewPlayerFromAccount();

        ClientSender.sendToViewer(new SetPlayerInfosAct(firstAccount.getName(), secondAccount.getName()));

        initHands();
        initIsOnLeft();
        this.players[0].setTable(table);
        this.players[1].setTable(table);
        this.accounts[0] = firstAccount;
        this.accounts[1] = secondAccount;
        this.numberOfFlags = numberOfFlags;
        if (gameMode != GameMode.KILLING_HERO)
            generateFlags(numberOfFlags);
        generateCollectibles();
        initHeroPlaces();
        castUsableItems();
    }

    private void initIsOnLeft() {
        for (int i = 0; i < 2; i++) {
            players[i].setOnLeft(getIsForLeft(i));
        }
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

    public Player getWinner() {
        return winner;
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

        ClientSender.sendToViewer(new GameEndAct(winner.getName()));
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    private void initHands() {
        for (int i = 0; i < 2; i++) {
            ArrayList<Card> addedCards = players[i].setHand();
            for (Card card : addedCards) {
                ClientSender.sendToViewer(new AddToHandAct(getIsForLeft(i), card));
            }
        }
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

    public void changeTurn() throws GameIsEndException {

        ClientSender.sendToViewer(new TurnChangeAct());

        endTurn();
        startTurn();
        if (currentPlayer instanceof AIPlayer) {

            ((AIPlayer) currentPlayer).doActsInAITurn(this);

            ClientSender.sendToViewer(new TurnChangeAct());

            endTurn();
            startTurn();
        }
    }

    private void startTurn() throws GameIsEndException {
        swapPlayers();
        setMana();
        doUnitsBuffs();
        checkIfAnyoneIsDead();
        gameIsEnd();
    }

    private void endTurn() throws GameIsEndException {
        addNextCardToHands();
        doCellBuffs();
        checkIfAnyoneIsDead();
        gameIsEnd();
        this.turn++;
        incrementTurnFlagKeeped();
        gameIsEnd();
    }

    private void addNextCardToHands() {
        for (Player player : players) {
            try {
                if (!player.getHand().isFull()) {
                    Card nextCard = player.getDeck().pop();
                    player.getHand().addCard(nextCard);

                    ClientSender.sendToViewer(new AddToHandAct(player == players[0], nextCard));
                }
            } catch (ArrayIsEmptyException e) {

            }
        }
    }

    private void gameIsEnd() throws GameIsEndException {
        if (checkWinningCondition()) {
            this.endFlag = true;
            this.getRewardToWinner();
            GameContents.saveCurrentAndSecondAccounts();
            throw new GameIsEndException();
        }
    }

    private void setMana() {
        players[0].setMana((turn + 1) / 2 + 2);
        players[1].setMana((turn + 1) / 2 + 2);
        if (players[0].getMana() > 9)
            players[0].setMana(9);
        if (players[1].getMana() > 9)
            players[1].setMana(9);
        Item item;
        if (turn % 2 == 0) {
            item = players[0].getDeck().getItem();
            if (item instanceof ManaItem)
                item.use(players[0], this.table.getCell(0, 0));
        } else {
            item = players[1].getDeck().getItem();
            if (item instanceof ManaItem)
                item.use(players[1], this.table.getCell(0, 0));
        }
    }

    public void getRewardToWinner() {
        if (this.winner.equals(players[0])) {
            accounts[0].increaseMoney(this.reward);
            accounts[0].increaseWins();
        }
        if (this.winner.equals(players[1])) {
            accounts[1].increaseMoney(this.reward);
            accounts[1].increaseWins();
        }
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
        Hero hero1 = player1.getHero();
        Hero hero2 = player2.getHero();
        if (hero1 == null || hero1.getHp() <= 0) {
            setWinner(player2);
            return true;
        }
        if (hero2 == null || hero2.getHp() <= 0) {
            setWinner(player1);
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

    private void swapPlayers() {
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
            ArrayList<Unit> unitsToRemove = new ArrayList<>();
            for (Unit unit : player.getUnits()) {
                if (unit.isDead()) {
                    Cell currentCell = unit.getCurrentCell();

                    currentCell.setUnit(null);
                    unit.dropFlags(unit.getCurrentCell(), unit);
                    unit.castSpecialPower(SpecialPowerCastTime.ON_DEATH, currentCell);//todo: send to client
                    unitsToRemove.add(unit);

                    ClientSender.sendToViewer(new DieUnitAct(currentCell.getRow(), currentCell.getColumn()
                            , unit.getName(), unit instanceof Hero ? Type.HERO : Type.MINION
                            , getIsForLeft(player)));
                }
            }
            for (Unit unit : unitsToRemove) {
                player.getUnits().removeIf(x -> x.equals(unit));
                player.getGraveYard().addCard(unit);
            }
        }
    }

    //for gameMode: "keep flag" and collect flags
    public ArrayList<Flag> getFlags() {
        ArrayList<Flag> flags = new ArrayList<>();
        for (Player player : players) {
            for (Unit unit : player.getUnits()) {
                for (Flag flag : unit.getFlags()) {
                    flags.add(flag);
                }
            }
        }
        for (int i = 0; i < Table.HEIGHT; i++) {
            for (int j = 0; j < Table.WIDTH; j++) {
                Cell cell = table.getCell(i, j);
                for (Flag flag : cell.getFlags())
                    flags.add(flag);
            }
        }
        return flags;
    }

    public void incrementTurnFlagKeeped() {
        if (this.getGameMode() == GameMode.KEEP_FLAG) {
            if (this.players[0].hasFlag())
                this.players[0].incrementTurnsFlagKeeped();
            if (this.players[1].hasFlag())
                this.players[1].incrementTurnsFlagKeeped();
        }
    }

    public void generateFlags(int numberOfFlags) {
        if (numberOfFlags % 2 == 1) {
            new Flag(table.getCell(2, 4));
            numberOfFlags--;
        }
        numberOfFlags = numberOfFlags / 2;
        for (int i = 0; i < numberOfFlags; i++) {
            int a = Math.abs(new Random().nextInt()) % 5;
            int b = Math.abs(new Random().nextInt()) % 4;
            new Flag(table.getCell(a, b));
            new Flag(table.getCell(a, 8 - b));
        }
    }

    private void initHeroPlaces() {
        for (int i = 0; i < 2; i++) {
            Hero hero = players[i].getDeck().getHero();

            Cell cell = table.getCell(2, 8 * i);

            hero.setCurrentCell(cell);
            hero.setGameCardID(UniqueIDGenerator.getGameUniqueID(players[i].getAccount().getName(), hero.getName()));
            cell.setUnit(hero);

            ClientSender.sendToViewer(new PutUnitAct(2, 8 * i, getIsForLeft(i), hero));

            players[i].getUnits().add(hero);
            players[i].pickUpFlags(cell, hero);
            players[i].pickUpCollectibles(cell);
        }
    }

    private void castUsableItems() {
        for (Player player : players) {
            Item item = player.getDeck().getItem();
            if (item != null && !(item instanceof ManaItem)) {
                item.use(player, table.getCell(0, 0));

                ClientSender.sendToViewer(new UsableItemAct(player.getName(), item.getName()));
            }
        }
    }

    private void generateCollectibles() {
        for (int i = 0; i < 3; i++) {
            Item collectible = Initializer.getRandomCollectible();
            collectible.setCollectibleID(i);
            Cell cell = table.getCell(2 * i, 4);
            cell.addCollectible(collectible);
        }
    }

    private boolean getIsForLeft(int i) {
        return i == 0;
    }

    private boolean getIsForLeft(Player player) {
        return player == players[0];
    }
}
