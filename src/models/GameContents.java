package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameContents {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Account currentAccount;
    private static Shop shop = new Shop();
    private static ArrayList<GameLevel> gameLevels = Initializer2Movaghat.initGameLevels();

    public static void setCurrentAccount(Account currentAccount) {
        GameContents.currentAccount = currentAccount;
    }

    public static Shop getShop() {
        return shop;
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static void addAccount(Account account) {
        accounts.add(account);
    }

    public static Account findAccount(String name) {
        for (Account account : accounts) {
            if (account.getName().equals(name))
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
}
