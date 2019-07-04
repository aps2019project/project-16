package models;

import java.net.Socket;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.regex.MatchResult;

public class Account {

    private String name;
    private String password;
    private Collection collection = new Collection();
    private ArrayList<MatchResult> matchHistory;//phase 2
    private int money = 2000000;
    private int wins;
    private int numberOfItems;
    //todo for server

    private Game currentGame;
    private Account opponentAccount;

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setOpponentAccount(Account opponentAccount) {
        this.opponentAccount = opponentAccount;
    }

    public Account getOpponentAccount() {
        return opponentAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection getCollection() {
        return collection;
    }

    public ArrayList<MatchResult> getMatchHistory() {
        return matchHistory;
    }

    public ArrayList<Deck> getDecks() {
        return this.collection.getDecks();
    }

    public int getWins() {
        return this.wins;
    }

    public int getMoney() {
        return this.money;
    }


    public Account(String name, String password) {
        this.name = name;
        this.password = password;
        //TODO: JUST FOR DEBUG
        GameLevel gameLevel = GameContents.getGameLevel(1);
        if (gameLevel != null) {
            Deck defaultDeck = gameLevel.getDeck().getCopy();
            defaultDeck.setName("default1");
            collection.addDeck(defaultDeck);
        }
        //TODO END OF
    }
    //////////////////////////////////////////////////////////////////////////////////////////

    public Player getNewPlayerFromAccount() {
        Collection collection = this.getCollection();
        return new Player(collection.getMainDeck().getCopy(), this);
    }

    public void addHisytory(MatchResult history) {
        this.matchHistory.add(history);
    }

    public Deck getDeck(int deckNumber) {
        return this.getDecks().get(deckNumber);
    }

    public void increaseMoney(int number) {
        this.money += number;
    }

    public void decreaseMoney(int number) {
        this.money -= number;
    }

    public void increaseWins() {
        this.wins++;
    }

    public boolean hasValidMainDeck() {
        Deck mainDeck = collection.getMainDeck();
        if (mainDeck == null) {
            return false;
        } else {
            return mainDeck.isValid();
        }
    }

    @Override
    public String toString() {
        return "UserName : " + this.getName() + " - Wins : " + this.getWins();
    }

}
