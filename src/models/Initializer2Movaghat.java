package models;


import models.card.Card;
import models.card.Hero;

import java.util.ArrayList;

public class Initializer2Movaghat {
    public static ArrayList<GameLevel> initGameLevels() {
        ArrayList<GameLevel> gameLevels = new ArrayList<>();
        Shop shop = GameContents.getShop();

        //1

        ArrayList<Card> cards1 = new ArrayList<>();
        //minions
        cards1.add(shop.getCard("kamandare fars").getCopy(false));
        cards1.add(shop.getCard("neize dare toorani").getCopy(false));
        cards1.add(shop.getCard("gor dare toorani").getCopy(false));
        cards1.add(shop.getCard("gor dare toorani").getCopy(false));
        cards1.add(shop.getCard("dive siah").getCopy(false));
        cards1.add(shop.getCard("ghoole tak cheshm").getCopy(false));
        cards1.add(shop.getCard("mare sammi").getCopy(false));
        cards1.add(shop.getCard("mare ghool peykar").getCopy(false));
        cards1.add(shop.getCard("gorge sefid").getCopy(false));
        cards1.add(shop.getCard("jadoogare azam").getCopy(false));
        cards1.add(shop.getCard("nanae sarma").getCopy(false));
        cards1.add(shop.getCard("siavash").getCopy(false));
        cards1.add(shop.getCard("arzhang div").getCopy(false));

        //spellCards
        cards1.add(shop.getCard("total disarm").getCopy(false));
        cards1.add(shop.getCard("Lightening Bolt").getCopy(false));
        cards1.add(shop.getCard("All Disarm").getCopy(false));
        cards1.add(shop.getCard("All Poison").getCopy(false));
        cards1.add(shop.getCard("Dispel").getCopy(false));
        cards1.add(shop.getCard("Sacrifice ").getCopy(false));//todo change name in shop init and here
        cards1.add(shop.getCard("Shock").getCopy(false));

        //deck
        Deck deck1 = new Deck(cards1
                , (Hero) shop.getCard("dive sefid").getCopy(false)
                , shop.getItem("taje danayi").getCopy(false) // TODO: 4/30/19 change name in shop init
                , "level1Deck");

        gameLevels.add(new GameLevel(1, deck1, 1, 0, 500));


        //2

        ArrayList<Card> cards2 = new ArrayList<>();
        //minions
        cards2.add(shop.getCard("shamshir zane fars").getCopy(false));
        cards2.add(shop.getCard("neize dare fars").getCopy(false));
        cards2.add(shop.getCard("pahlevane fars").getCopy(false));
        cards2.add(shop.getCard("ghollab sang dare toorani").getCopy(false));
//      todo  cards2.add(shop.getCard("").getCopy(false));//shahzade toorani
        cards2.add(shop.getCard("oghab").getCopy(false));
        cards2.add(shop.getCard("oghab").getCopy(false));
        cards2.add(shop.getCard("ezhdehaye atash andaz").getCopy(false));
        cards2.add(shop.getCard("palang").getCopy(false));
        cards2.add(shop.getCard("gen").getCopy(false));
        cards2.add(shop.getCard("giv").getCopy(false));
        cards2.add(shop.getCard("iraj").getCopy(false));
        cards2.add(shop.getCard("shahghool").getCopy(false));

        //spellCards
        cards2.add(shop.getCard("Area Disepel").getCopy(false));
        cards2.add(shop.getCard("Empower").getCopy(false));
        cards2.add(shop.getCard("God Strength").getCopy(false));
        cards2.add(shop.getCard("Poison Lake").getCopy(false));
        cards2.add(shop.getCard("Madness").getCopy(false));
        cards2.add(shop.getCard("Health with profit").getCopy(false));
        cards2.add(shop.getCard("Kings Guard").getCopy(false));

        //deck
        Deck deck2 = new Deck(cards2
                , (Hero) shop.getCard("zahhak").getCopy(false)
                , shop.getItem("soul eater").getCopy(false) // TODO: 4/30/19 change name in shop init
                , "level2Deck");

        gameLevels.add(new GameLevel(2, deck2, 2, 1, 1000));


        //3

        ArrayList<Card> cards3 = new ArrayList<>();
        //minions
//        todo cards3.add(shop.getCard("").getCopy(false));// sepah salar e fars
        cards3.add(shop.getCard("kamandare toorani").getCopy(false));
        cards3.add(shop.getCard("jasoose toorani").getCopy(false));
        cards3.add(shop.getCard("ghool sang andaz").getCopy(false));
        cards3.add(shop.getCard("dive goraz savar").getCopy(false));
        cards3.add(shop.getCard("dive goraz savar").getCopy(false));
        cards3.add(shop.getCard("shire darande").getCopy(false));
        cards3.add(shop.getCard("gorg").getCopy(false));
        cards3.add(shop.getCard("jadoogar").getCopy(false));
        cards3.add(shop.getCard("goraze vahshi").getCopy(false));
        cards3.add(shop.getCard("piran").getCopy(false));
        cards3.add(shop.getCard("bahman").getCopy(false));
        cards3.add(shop.getCard("ghoole bozorg").getCopy(false));

        //spellCards
        cards3.add(shop.getCard("HellFire").getCopy(false));
        cards3.add(shop.getCard("All Disarm").getCopy(false));
        cards3.add(shop.getCard("Dispel").getCopy(false));
        cards3.add(shop.getCard("Power Up").getCopy(false));
        cards3.add(shop.getCard("All Power").getCopy(false));
        cards3.add(shop.getCard("All Attack").getCopy(false));
        cards3.add(shop.getCard("Weakening").getCopy(false));

        //deck
        Deck deck3 = new Deck(cards3
                , (Hero) shop.getCard("arash").getCopy(false)
                , shop.getItem("terror hood").getCopy(false) // TODO: 4/30/19 change name in shop init
                , "level3Deck");

        gameLevels.add(new GameLevel(3, deck3, 3, 8, 1500));


        return gameLevels;
    }
}
