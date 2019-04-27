package view.views;

import contracts.CollectionContract;
import models.card.Card;
import models.Deck;
import models.card.Hero;
import models.Item;

import java.util.ArrayList;

public class CollectionView implements CollectionContract.View {
    private CollectionContract.Controller controller;

    @Override
    public void setController(CollectionContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showAllDecks(ArrayList<Deck> decks) {

    }

    @Override
    public void showDeck(Deck deck) {

    }

    @Override
    public void showCollection(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {
        ShopView.printHeroes(heroes, 'c');
        ShopView.printUsables(items, 'c');
        ShopView.printCards(cards, 'c');
    }

    @Override
    public void showSearchResult(ArrayList<Item> items, ArrayList<Card> cards) {

    }

    @Override
    public void showDeckValidationStatus(String deckName, String message) {

    }
}
