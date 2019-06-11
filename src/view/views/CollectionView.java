package view.views;

import contracts.CollectionContract;
import models.card.Card;
import models.Deck;
import models.card.Hero;
import models.item.Item;
import view.MenuHandler;
import view.Notify;

import java.util.ArrayList;

import static view.menuItems.MenuConstants.BATTLE_MENU;

public class CollectionView implements CollectionContract.View {

    @Override
    public void showAllDecks(Deck mainDeck, ArrayList<Deck> decks) {
        if (mainDeck != null) {
            showDeck(mainDeck);
        }
        for (Deck deck : decks) {
            if (deck != mainDeck) {
                showDeck(deck);
            }
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
        Notify.logMessage("\nDeck with name: \"" + deck.getName() + "\":\n");
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
    public void goToBattleMenu() {
        MenuHandler.goToSubMenu(BATTLE_MENU);
    }
}
