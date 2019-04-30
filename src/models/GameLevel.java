package models;

public class GameLevel {
    private int levelNumber;
    private Deck deck;
    private int mode;
    private int numberOfFlags;
    private int prize;

    public GameLevel(int levelNumber, Deck deck, int mode, int numberOfFlags, int prize) {
        this.levelNumber = levelNumber;
        this.deck = deck;
        this.mode = mode;
        this.numberOfFlags = numberOfFlags;
        this.prize = prize;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public Deck getDeck() {
        return deck;
    }

    public int getMode() {
        return mode;
    }

    public int getNumberOfFlags() {
        return numberOfFlags;
    }

    public int getPrize() {
        return prize;
    }
}
