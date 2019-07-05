package view.views;

import contracts.ShopContract;
import models.card.Card;
import models.card.Hero;
import models.card.Minion;
import models.card.SpellCard;
import models.item.Item;

import java.util.ArrayList;

import static view.Notify.logMessage;

public class ShopView implements ShopContract.View {

    @Override
    public void setController(ShopContract.Controller controller) {

    }

    @Override
    public void showShop(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {
        printHeroes(heroes, 's');
        printUsables(items, 's');
        printCards(cards, 's');
    }

    @Override
    public void showCollection(ArrayList<Hero> heroes, ArrayList<Item> items, ArrayList<Card> cards) {

    }

    @Override
    public void showCard(Object card) {

    }

    public static void printCards(ArrayList<Card> cards, char printingState) {
        int i = 0;
        logMessage("Cards:");
        for (Card card : cards) {
            i++;
            switch (getCardType(card)) {
                case "Minion":
                    Minion minion = (Minion) card;
                    System.out.printf("\t%d : Type: %s - Name: %s - Class: %s - AP: %d - HP: %d - MP: %d " + /* "- Special power: %s" +*/ " - Buy cost: %d\n"
                            , (printingState == 's') ? (i) : (minion.getCollectionID())
                            , getCardType(minion)
                            , minion.getName()
                            , minion.getAttackType().toString()
                            , minion.getAp()
                            , minion.getHp()
                            , minion.getManaCost()
                            /*, minion.toString()*/
                            , minion.getBuyPrice());
                    break;
                case "Spell":
                    SpellCard spellCard = (SpellCard) card;
                    System.out.printf("\t%d : Type: %s - Name: %s - MP: %d " + /*"- Description: %s" +*/ " - Buy cost: %d\n"
                            , (printingState == 's') ? (i) : (spellCard.getCollectionID())
                            , getCardType(spellCard)
                            , spellCard.getName()
                            , spellCard.getManaCost()
                            /* , spellCard.toString() */
                            , spellCard.getBuyPrice());
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
            System.out.printf("\t%d : Name: %s " + /*"- Description: %s" +*/ " - Buy cost: %d\n"
                    , (printingState == 's') ? (i) : (item.getCollectionID())
                    , item.getName()
                    /*, item.toString()*/
                    , item.getBuyPrice());
        }
    }

    public static void printHeroes(ArrayList<Hero> heroes, char printingState) {
        int i = 0;
        logMessage("Heroes:");
        for (Hero hero : heroes) {
            i++;
            System.out.printf("\t%d :  Name: %s - AP: %d - HP: %d " + /*"- Special Power : %s " +*/ "- Buy cost: %d\n"
                    , (printingState == 's') ? (i) : (hero.getCollectionID())
                    , hero.getName()
                    , hero.getAp()
                    , hero.getHp()
                    /*, hero.toString() */
                    , hero.getBuyPrice());
        }
    }

}
