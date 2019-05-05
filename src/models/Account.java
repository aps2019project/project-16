package models;

import com.gilecode.yagson.YaGson;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.MatchResult;

public class Account {
    private String name;
    private String password;
    private Collection collection = new Collection();
    private ArrayList<MatchResult> matchHistory;//todo must be initialize
    private int money = 20000;
    private int wins;
    private int numberOfItems;

    public void setMoney(int money) {
        this.money = money;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }


    public int getNumberOfItems() {
        return numberOfItems;
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
    }
    //////////////////////////////////////////////////////////////////////////////////////////

    public static Account getAccount(String name) {

        return null;
        //todo must be checked with save and load method for accounts
        //todo must be called from some where !! maybe initializer or in game contents
    }

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

    public void increaseWin() {
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
