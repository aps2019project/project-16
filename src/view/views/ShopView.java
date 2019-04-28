package view.views;

import contracts.ShopContract;
import models.Item;
import models.card.Card;
import models.card.Hero;
import models.card.Minion;
import models.card.SpellCard;

import java.util.ArrayList;

import static view.Notify.*;

public class ShopView implements ShopContract.View {
    private ShopContract.Controller controller;


    @Override
    public void setController(ShopContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showShopSearchResult(String cardName, String message) {

    }

    @Override
    public void showCollectionSearchResult(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {

    }

    @Override
    public void showShop(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {
        printHeroes(heroes, 's');
        printUsables(items, 's');
        printCards(cards, 's');
    }

    public static void printCards(ArrayList<Card> cards, char printingState) {
        int i = 0;
        logMessage("Cards:");
        for (Card card : cards) {
            i++;
            switch (getCardType(card)) {
                case "Minion":
                    Minion minion = (Minion) card;
                    System.out.printf("\t%d : Type: %s - Name: %s - Class: %s - AP: %d - HP: %d - MP: %d - Special power: %s - Buy cost: %d\n"
                            , (printingState == 's') ? (i) : (card.getCollectionID())
                            , getCardType(card)
                            , card.getName()
                            , minion.getAttackType().getClass().getName()
                            , minion.getAp()
                            , minion.getHp()
                            , card.getManaCost()
                            , minion.getSpecialPower().toString()
                            , card.getBuyPrice());
                    break;
                case "Spell":
                    SpellCard spellCard = (SpellCard) card;
                    System.out.printf("\t%d : Type: %s - Name: %s - MP: %d - Description: %s - Buy cost: %d\n"
                            , (printingState == 's') ? (i) : (card.getCollectionID())
                            , getCardType(card)
                            , card.getName()
                            , card.getManaCost()
                            , spellCard.toString()
                            , card.getBuyPrice());
                    break;
            }
        }
    }

    private static String getCardType(Card card) {
        if (card.getClass().equals(Minion.class)) {
            return "Minion";
        }
        if (card.getClass().equals(SpellCard.class)) {
            return "Spell";
        }
        return null;
    }

    public static void printUsables(ArrayList<Item> items, char printingState) {
        int i = 0;
        logMessage("Items:");
        for (Item item : items) {
            i++;
            System.out.printf("\t%d : Name: %s - Description: %s - Buy cost: %d\n"
                    , (printingState == 's') ? (i) : (item.getCollectionID())
                    , item.getName()
                    , item.toString()
                    , item.getBuyPrice());
        }
    }

    public static void printHeroes(ArrayList<Hero> heroes, char printingState) {
        int i = 0;
        logMessage("Heroes:");
        for (Hero hero : heroes) {
            i++;
            System.out.printf("\t%d :  Name: %s - AP: %d - HP: %d - Special Power : %s - Buy cost: %d\n"
                    , (printingState == 's') ? (i) : (hero.getCollectionID())
                    , hero.getName()
                    , hero.getAp()
                    , hero.getHp()
                    , hero.getSpell().toString()
                    , hero.getBuyPrice());
        }
    }

    @Override
    public void showBuyResult(String cardName, String message) {

    }

}
