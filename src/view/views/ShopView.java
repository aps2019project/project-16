package view.views;

import contracts.ShopContract;
import models.Usable;
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
    public void showCollectionSearchResult(ArrayList<Hero> heroes, ArrayList<Usable> items, ArrayList<Card> cards) {

    }

    @Override
    public void showShop(ArrayList<Hero> heroes, ArrayList<Usable> items, ArrayList<Card> cards) {
        printHeroes(heroes);
        printUsables(items);
        printCards(cards);
    }

    private void printCards(ArrayList<Card> cards) {
        int i = 0;
        logMessage("Cards:");
        for (Card card : cards) {
            i++;
            switch (getCardType(card)) {
                case "Minion":
                    Minion minion = (Minion) card;
                    System.out.printf("\t%d : Type: %s - Name: %s - Class: %s - AP: %d - HP: %d - MP: %d - Special power: %s\n"
                            , i
                            , getCardType(card)
                            , card.getName()
                            , minion.getAttackType().getClass().getName()
                            , minion.getAp()
                            , minion.getHp()
                            , card.getManaCost()
                            , minion.getSpecialPower().toString());
                    break;
                case "Spell":
                    SpellCard spellCard = (SpellCard) card;
                    System.out.printf("\t%d : Type: %s - Name: %s - MP: %d - Description: %s - Buy cost: %d\n"
                            , i
                            , getCardType(card)
                            , card.getName()
                            , card.getManaCost()
                            , spellCard.toString()
                            , card.getBuyPrice());
                    break;
            }
        }
    }

    private String getCardType(Card card) {
        if (card.getClass().equals(Minion.class)) {
            return "Minion";
        }
        if (card.getClass().equals(SpellCard.class)) {
            return "Spell";
        }
        return null;
    }

    private void printUsables(ArrayList<Usable> items) {
        int i = 0;
        logMessage("Items:");
        for (Usable usable : items) {
            i++;
            System.out.printf("\t%d : Name: %s - Description: %s - Buy cost: %d\n"
                    , i
                    , usable.getName()
                    , usable.toString()
                    , usable.getBuyPrice());
        }
    }

    private void printHeroes(ArrayList<Hero> heroes) {
        int i = 0;
        logMessage("Heroes:");
        for (Hero hero : heroes) {
            i++;
            System.out.printf("\t%d :  Name: %s - AP: %d - HP: %d - Special Power : %s - Buy cost: %d\n"
                    , i
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
