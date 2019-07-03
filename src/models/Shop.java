package models;

import com.gilecode.yagson.YaGson;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import models.card.Card;
import models.card.Hero;
import models.card.Minion;
import models.item.Item;
import newView.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.SplittableRandom;

public class Shop {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    private File customCardsPath = new File("customCards");
    //file custom card

    public Shop() throws FileNotFoundException {
        initCustomCards();
        Initializer.initShopCards(cards);
        Initializer.initShopUsableItems(items);
    }

    private void initCustomCards() throws FileNotFoundException {
        File dir = customCardsPath;
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                Card card = new YaGson().fromJson(reader, Card.class);
                cards.add(card);
            }
        }
    }

    private boolean CustomCardIsRepeated(Card customCard) throws FileNotFoundException {
        File dir = customCardsPath;
        File[] files = dir.listFiles();
        for (File file : files) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Card card = new YaGson().fromJson(reader, Card.class);
            if (card.getName().equals(customCard.getName()) && getCardType(card).equals(getCardType(customCard))) {
                return true;
            }
        }
        return false;
    }

    public void saveCustomCard(Card customCard) throws Exception {
        if (CustomCardIsRepeated(customCard)) {
            throw new Exception();
            ///todo must check and customize by MOSTAFA
        }
        YaGson yaGson = new YaGson();
        String json = yaGson.toJson(customCard);

        FileWriter writer = new FileWriter("customCards/" + getCardType(customCard).getName() + "_" + customCard.getName() + ".josn");

        writer.write(json);
        writer.close();
    }

    private Type getCardType(Card card) {
        if (card instanceof Hero)
            return Type.HERO;
        else if (card instanceof Minion)
            return Type.MINION;
        else
            return Type.SPELL;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getCard(String cardName) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(cardName))
                return card;
        }
        return null;
    }

    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName))
                return item;
        }
        return null;
    }

    public Card getCardByCardID(int cardID) {
        for (Card card : cards) {
            if (card.getCollectionID() == cardID)
                return card;
        }
        return null;
    }

    public Item getItemByItemID(int itemID) {
        for (Item item : items) {
            if (item.getCollectionID() == itemID)
                return item;
        }
        return null;
    }

    public void buyCard(String cardName) {
        Account currentAccount = GameContents.getCurrentAccount();
        Collection collection = currentAccount.getCollection();

        Card card = getCard(cardName);
        card.decrementCapacity();

        Card newCard = card.getCopy(true);
        currentAccount.decreaseMoney(newCard.getBuyPrice());
        collection.addCard(newCard);

    }

    public void sellCard(int cardID) {
        Account currentAccount = GameContents.getCurrentAccount();
        Collection collection = currentAccount.getCollection();

        Card card = collection.getCard(cardID);
        currentAccount.increaseMoney(card.getBuyPrice());
        collection.removeCard(card);

        incrementCardCapacity(card.getName());
    }

    public void buyItem(String itemName) {
        Account currentAccount = GameContents.getCurrentAccount();
        Collection collection = currentAccount.getCollection();

        Item item = getItem(itemName);
        item.decrementCapacity();

        Item newItem = item.getCopy(true);
        currentAccount.decreaseMoney(newItem.getBuyPrice());
        collection.addItem(newItem);
    }

    public void sellItem(int itemID) {
        Account currentAccount = GameContents.getCurrentAccount();
        Collection collection = currentAccount.getCollection();

        Item item = collection.getItem(itemID);
        currentAccount.increaseMoney(item.getBuyPrice());
        collection.removeItem(item);

        incrementItemCapacity(item.getName());
    }

    public String getType(String name) {
        Card card = getCard(name);
        Item item = getItem(name);
        if (card != null)
            return "card";
        if (item != null)
            return "item";
        return null;
    }

    public void incrementCardCapacity(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name)) {
                card.incrementCapacity();
                break;
            }
        }
    }

    public void incrementItemCapacity(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                item.incrementCapacity();
                break;
            }
        }
    }
}
