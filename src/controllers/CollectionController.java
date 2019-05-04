package controllers;

import contracts.CollectionContract;
import models.*;
import models.card.Card;
import models.card.Hero;
import models.item.Item;
import view.Notify;
import view.views.CollectionView;

import java.util.ArrayList;

import static models.Deck.CARD_CAPACITY;

public class CollectionController implements CollectionContract.Controller {
    private CollectionContract.View view;

    public CollectionController() {
        view = new CollectionView();
    }

    @Override
    public void loadCollection() {
        Collection collection = GameContents.getCurrentAccount().getCollection();
        ArrayList<Hero> heroes = new ArrayList<>();
        ArrayList<Card> cards = new ArrayList<>();
        ArrayList<Item> items = collection.getItems();
        for (Card card : collection.getCards()) {
            if (card.getClass().equals(Hero.class)) {
                heroes.add((Hero) card);
            } else {
                cards.add(card);
            }
        }
        view.showCollection(heroes, items, cards);
    }

    @Override
    public void loadAllDecks() {
        Collection collection = GameContents.getCurrentAccount().getCollection();
        view.showAllDecks(collection.getMainDeck(), collection.getDecks());
    }

    @Override
    public void loadDeck(String deckName) {
        Collection collection = GameContents.getCurrentAccount().getCollection();
        Deck deck = collection.getDeck(deckName);
        if (deck == null) {
            Notify.logError("Sorry! This deck doesn't exist!");
        } else {
            view.showDeck(deck);
        }
    }

    @Override
    public void searchCard(String name) {
        Collection collection = GameContents.getCurrentAccount().getCollection();
        int collectionID = collection.searchCard(name);
        if (collectionID == -1) {
            Notify.logError("No! This card doesn't exist in collection!");
        } else {
            Notify.logMessage("Yes! We have this card in collection. Its cardID: " + collectionID);
        }
    }

    @Override
    public void saveCollection() {
        // phase 2: 4/30/19  save in file next phase
        Notify.logMessage("All things about your collection is saved.");
    }

    @Override
    public void createDeck(String deckName) {
        Collection collection = GameContents.getCurrentAccount().getCollection();
        if (collection.getDeck(deckName) != null) {
            Notify.logError("Sorry!! Deck with this name is already exist!");
        } else {
            collection.createDeck(deckName);
            Notify.logMessage("Good job! You created deck \"" + deckName + "\"");
        }
    }

    @Override
    public void deleteDeck(String deckName) {
        Collection collection = GameContents.getCurrentAccount().getCollection();
        Deck deck = collection.getDeck(deckName);
        if (deck == null) {
            Notify.logError("Oh!! This deck doesn't exist!");
        } else {
            collection.deleteDeck(deck);
            Notify.logMessage("Deck \"" + deckName + "\" deleted successfully.");
        }
    }

    @Override
    public void addCardToDeck(int cardID, String deckName) {
        Collection collection = GameContents.getCurrentAccount().getCollection();
        String type = collection.getType(cardID);
        Deck deck = collection.getDeck(deckName);
        if (type == null) {
            Notify.logError("This card doesn't exist in collection!");
        } else if (deck == null) {
            Notify.logError("This deck doesn't exist in collection!");
        } else if (deck.hasCard(cardID)) {
            Notify.logError("Sorry! This card is already exist in deck!");
        } else {
            switch (type) {
                case "item":
                    if (deck.getItem() != null) {
                        Notify.logError("Sorry! You have ONE item in this deck.");
                    } else {
                        deck.setItem(collection.getItem(cardID));
                        Notify.logMessage("Good! you added this card to deck \"" + deckName + "\"");
                    }
                    break;
                case "card":
                    Card card = collection.getCard(cardID);
                    if (card.getClass() == Hero.class) {
                        if (deck.getHero() != null) {
                            Notify.logError("Oh No! You have a hero in deck \"" + deckName + "\"");
                        } else {
                            deck.setHero((Hero) card);
                            Notify.logMessage("Hero \"" + card.getName() + "\" added to deck \"" + deckName + "\"");
                        }
                    } else if (deck.getCards().size() >= CARD_CAPACITY) {
                        Notify.logError("No free capacity in deck \"" + deckName + "\"! capacity of cards in deck is " + CARD_CAPACITY + ".");
                    } else {
                        deck.addCard(card);
                        Notify.logMessage("Card \"" + card.getName() + "\" added to deck \"" + deckName + "\"");
                    }
                    break;
            }
        }
    }

    @Override
    public void removeCardFromDeck(int cardID, String deckName) {
        Collection collection = GameContents.getCurrentAccount().getCollection();
        Deck deck = collection.getDeck(deckName);
        String type = collection.getType(cardID);
        if (deck == null) {
            Notify.logError("Sorry! This deck doesn't exist!");
        } else if (!deck.hasCard(cardID)) {
            Notify.logError("Sorry! This card doesn't exist exist in deck \"" + deckName + "\"");
        } else {
            switch (type) {
                case "item":
                    deck.setItem(null);
                    break;
                case "card":
                    Card card = collection.getCard(cardID);
                    if (card.getClass() == Hero.class) {
                        deck.setHero(null);
                    } else {
                        deck.removeCard(card);
                    }
                    break;
            }
            Notify.logMessage("You removed card with cardID \"" + cardID + "\" from deck \"" + deckName + "\"");
        }
    }

    @Override
    public void validateDeck(String deckName) {
        Collection collection = GameContents.getCurrentAccount().getCollection();
        Deck deck = collection.getDeck(deckName);
        if (deck == null) {
            Notify.logError("Sorry! This deck doesn't exist!");
        } else if (!deck.isValid()) {
            Notify.logError("Deck with name \"" + deckName + "\" isn't valid. You must have " + CARD_CAPACITY + " cards and 1 hero in your deck.");
        } else {
            Notify.logMessage("OK! Deck with name \"" + deckName + "\" is valid.");
        }
    }

    @Override
    public void selectDeck(String deckName) {
        Collection collection = GameContents.getCurrentAccount().getCollection();
        Deck deck = collection.getDeck(deckName);
        if (deck == null) {
            Notify.logError("Sorry! This deck doesn't exist!");
        } else {
            collection.setMainDeck(deck);
            Notify.logMessage("Good job! You set the main deck to \"" + deckName + "\".");
        }
    }

    @Override
    public void validateMainDeckForEnterBattle() {
        Account currentAccount = GameContents.getCurrentAccount();
        if (currentAccount.hasValidMainDeck()) {
            view.goToBattleMenu();
        } else {
            Notify.logError("For entering battle, First complete and set your main deck.");
        }
    }
}
