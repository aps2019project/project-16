package view.views;

import contracts.CollectionContract;
import models.Shop;
import models.card.Card;
import models.Deck;
import models.card.Hero;
import models.Item;
import view.Notify;

import java.util.ArrayList;

public class CollectionView implements CollectionContract.View {
    private CollectionContract.Controller controller;

    @Override
    public void setController(CollectionContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showAllDecks(ArrayList<Deck> decks) {
        for (Deck deck : decks) {
            Notify.logMessage("\nDeck with name: \"" + deck.getName() + "\"\n");
            showDeck(deck);
        }
    }

    @Override
    public void showDeck(Deck deck) {
        ArrayList<Hero> heroes = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Card> cards = deck.getCards();
        if (deck.getHero() != null) {
            heroes.add(deck.getHero());
        }
        if (deck.getItem() != null) {
            items.add(deck.getItem());
        }
        ShopView.printHeroes(heroes, 'c');
        ShopView.printUsables(items, 'c');
        ShopView.printCards(cards, 'c');
    }

    @Override
    public void showCollection(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {
        ShopView.printHeroes(heroes, 'c');
        ShopView.printUsables(items, 'c');
        ShopView.printCards(cards, 'c');
    }

    @Override
    public void showSearchResult(ArrayList<Item> items, ArrayList<Card> cards) {
        //un use now
    }

    @Override
    public void showDeckValidationStatus(String deckName, String message) {
        //un use now
    }
}
