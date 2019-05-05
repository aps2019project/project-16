package models;

import java.util.ArrayList;

public class UniqueIDGenerator {
    private static int lastUsedID = 0;
    private static ArrayList<Wrapper> wrappers = new ArrayList<>();

    public static int getCollectionUniqueID() {
        lastUsedID++;
        return lastUsedID;
    }

    public static void setLastUsedID(int lastUsedID) {
        UniqueIDGenerator.lastUsedID = lastUsedID;
    }

    public static int getGameUniqueID(String playerName, String cardName) {
        for (Wrapper wrapper : wrappers) {
            if (wrapper.equals(playerName, cardName)) {
                return wrapper.getNewNumberOfCard();
            }
        }
        Wrapper newWrapper = new Wrapper(playerName, cardName);
        wrappers.add(newWrapper);
        return newWrapper.getNewNumberOfCard();
    }

    private static class Wrapper {
        private String playerName;
        private String cardName;
        private int lastNumberOfCard = 0;

        Wrapper(String playerName, String cardName) {
            this.playerName = playerName;
            this.cardName = cardName;
        }

        int getNewNumberOfCard() {
            lastNumberOfCard++;
            return lastNumberOfCard;
        }

        public boolean equals(String playerName, String cardName) {
            return playerName.equalsIgnoreCase(this.playerName) && cardName.equalsIgnoreCase(this.cardName);
        }
    }

}
