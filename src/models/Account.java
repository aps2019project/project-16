package models;

import java.util.ArrayList;
import java.util.regex.MatchResult;

public class Account {
    private String name;
    private String password;
    private Collection collection;
    private ArrayList<MatchResult> matchHistory;//todo must be initialize
    private int money;
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
    }

    public Player getPlayer() {
        return null;
        //todo should be implemented at the end when the world and game has completed
    }

    public void addHisytory(MatchResult history) {
        this.matchHistory.add(history);
    }

    public void save() {
        //todo must be implemented
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

    @Override
    public String toString() {
        return "UserName : " + this.getName() + " - Wins : " + this.getWins();
    }

}
