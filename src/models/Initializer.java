package models;

import models.card.Card;
import models.card.Hero;
import models.card.Minion;
import models.card.SpellCard;
import models.targetsociety.EnemyHero;
import models.targetsociety.OneEnemyUnit;
import models.targetsociety.OneFriendUnit;

import java.util.ArrayList;

import static models.SpecialPowerCastTime.*;

public class Initializer {
    public static void initShopCards(ArrayList<Card> shopCards) {
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(PASSIVE)
                .setSpecialPower(new Spell(new OneEnemyUnit(), false, new Buff.BuffBuilder()
                        .setStun()
                        .setDuration(1)
                        .build()))
                .setAp(4)
                .setHp(6)
                .setAttackType(new Melee())
                .setBuyPrice(400)
                .setName("shamshir zane fars")
                .setManaCost(2)
                .create()
        );

        shopCards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell(new OneEnemyUnit(), false, new Buff.BuffBuilder()
                        .setDuration(Buff.INFINITY).setDeltaHP(-4).build()))
                .setBuyPrice(400)
                .setManaCost(1)
                .setName("Fireball")
                .create()
        );

        shopCards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-8)
                                .setDuration(Buff.INFINITY)
                                .build())
                        .setTargetSociety(new EnemyHero())
                        .create())
                .setBuyPrice(1250)
                .setManaCost(2)
                .setName("Lightening Bolt")
                .create()
        );

        shopCards.add(new Hero.HeroBuilder()
                .setSpellManaCost(1)
                .setSpellCoolDown(2)
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety(new FriendHero()) // todo must be corrected by Mostafa
                        .create()
                )
                .setAp(4)
                .setHp(50)
                .setAttackType(new Melee())
                .setName("dive sefid")
                .setManaCost(1)
                .setBuyPrice(8000)
                .create()
        );
        //todo must be implemented
    }

    public static void initShopUsableItems(ArrayList<Usable> shopUsables) {
        //todo must be implemented
    }

    public static void initCollectibleItems() {
        //todo must be implemented
    }

}
