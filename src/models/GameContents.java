package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameContents {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Game currentGame;
    private static Account currentAccount;
    private static Account secondAccount = null;
    private static Shop shop = new Shop();
    private static ArrayList<GameLevel> gameLevels = Initializer2Movaghat.initGameLevels();

    public static void setCurrentAccount(Account currentAccount) {
        GameContents.currentAccount = currentAccount;
    }

    public static Shop getShop() {
        return shop;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        GameContents.currentGame = currentGame;
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static Account getSecondAccount() {
        return secondAccount;
    }

    public static void setSecondAccount(Account secondAccount) {
        GameContents.secondAccount = secondAccount;
    }

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static void addAccount(Account account) {
        accounts.add(account);
    }

    public static Account findAccount(String name) {
        for (Account account : accounts) {
            if (account.getName().equalsIgnoreCase(name))
                return account;
        }
        return null;
    }

    public static void logoutCurrentAccount() {
        currentAccount = null;
    }

    public static void sortAccounts() {
        accounts.sort(Comparator.comparing(Account::getWins, Comparator.reverseOrder())
                .thenComparing(Account::getName));
    }

    public static ArrayList<GameLevel> getGameLevels() {
        return gameLevels;
    }

    public static boolean hasOppDeck(String oppDeckName) {
        for (GameLevel gameLevel : gameLevels) {
            if (gameLevel.getDeck().getName().equalsIgnoreCase(oppDeckName)) {
                return true;
            }
        }
        return false;
    }

    public static Deck getOpponentDeck(String oppDeckName) {
        for (GameLevel gameLevel : gameLevels) {
            if (gameLevel.getDeck().getName().equalsIgnoreCase(oppDeckName)) {
                return gameLevel.getDeck();
            }
        }
        return null;
    }
}
