package models;

import com.gilecode.yagson.YaGson;

import java.io.*;
import java.util.ArrayList;
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

    public static GameLevel getGameLevel(int levelNumber) {
        for (GameLevel gameLevel : gameLevels) {
            if (gameLevel.getLevelNumber() == levelNumber) {
                return gameLevel;
            }
        }
        return null;
    }

    public static void saveAccount(Account account) throws IOException {
        YaGson yaGson = new YaGson();
        String json = yaGson.toJson(account);

        FileWriter writer = new FileWriter("accounts/" + account.getName() + ".json");

        writer.write(json);
        writer.close();
        //todo must be called from some where !! after battle
    }

    public static void loadAccounts() throws FileNotFoundException {
        File dir = new File("accounts");
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                Account account = new YaGson().fromJson(reader, Account.class);

                GameContents.addAccount(account);
            }
        }
    }
}
