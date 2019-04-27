package models;

import models.card.Card;
import models.card.Hero;
import models.card.Minion;
import models.card.SpellCard;
import models.targetsociety.*;

import java.util.ArrayList;

import static models.SpecialPowerCastTime.*;

public class Initializer {
    public static void initShopCards(ArrayList<Card> shopCards) {
        //minions
        //1
        shopCards.add(new Minion.MinionBuilder()
                .setAttackType(new Ranged())
                .setHp(6)
                .setAp(4)
                .setManaCost(2)
                .setBuyPrice(300)
                .setSellPrice(300)
                .setName("kamandare fars")
                .create()
        );
        //2
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setStun()
                                .setDuration(1)
                                .create())
                        .setTargetSociety(new OneEnemyUnit())
                        .create())
                .setAttackType(new Melee())
                .setAp(4)
                .setHp(6)
                .setManaCost(2)
                .setSellPrice(400)
                .setBuyPrice(300)
                .setName("shamshir zane fars")
                .create()
        );
        //3
        shopCards.add(new Minion.MinionBuilder()
                .setAttackType(new Hybrid())
                .setAp(3)
                .setHp(5)
                .setManaCost(1)
                .setBuyPrice(500)
                .setSellPrice(500)
                .setName("neize dare fars")
                .create()
        );
        //4
        shopCards.add(new Minion.MinionBuilder()
                .setAttackType(new Melee())
                .setAp(6)
                .setHp(10)
                .setManaCost(4)
                .setSellPrice(200)
                .setBuyPrice(200)
                .setName("asb savare fars")
                .create()
        );
        //5
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDuration(Buff.INFINITY)
                                .setDeltaHP(0)// todo this must change (sepehr)
                                .create())
                        .setTargetSociety(new AllEnemyUnits())
                        .create())
                .setAttackType(new Melee())
                .setAp(6)
                .setHp(24)
                .setManaCost(9)
                .setBuyPrice(600)
                .setSellPrice(600)
                .setName("pahlevane fars")
                .create()
        );
        //6
        shopCards.add(new Minion.MinionBuilder()
                .create() // todo combe type must be iplemented and this minion must be completed
        );
        //7
        shopCards.add(new Minion.MinionBuilder()
                .setAttackType(new Ranged())
                .setAp(4)
                .setHp(3)
                .setManaCost(1)
                .setBuyPrice(500)
                .setSellPrice(500)
                .setName("kamandare toorani")
                .create()
        );
        //8
        shopCards.add(new Minion.MinionBuilder()
                .setAttackType(new Ranged())
                .setAp(2)
                .setHp(4)
                .setManaCost(1)
                .setSellPrice(600)
                .setBuyPrice(600)
                .setName("ghollab sang dare toorani")
                .create()
        );
        //9
        shopCards.add(new Minion.MinionBuilder()
                .setAttackType(new Hybrid())
                .setAp(4)
                .setHp(4)
                .setManaCost(1)
                .setBuyPrice(600)
                .setSellPrice(600)
                .setName("neize dare toorani")
                .create()
        );
        //10
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDisarm()
                                .setDuration(1)
                                .create())
                        .addBuff(new Buff.BuffBuilder()
                                .setPoison(0) //todo what is the argument in poison????!!!??
                                .setDuration(4)
                                .create())
                        .create())
                .create()
        );
        //11
        shopCards.add(new Minion.MinionBuilder()
                .setAttackType(new Melee())
                .setAp(10)
                .setHp(3)
                .setManaCost(2)
                .setSellPrice(450)
                .setBuyPrice(450)
                .setName("gor dare toorani")
                .create()
        );
        //12
        shopCards.add(new Minion.MinionBuilder()// todo COMBO must be added ny Mostafa
                .create()
        );
        //13
        shopCards.add(new Minion.MinionBuilder()
                .setAttackType(new Hybrid())
                .setAp(10)
                .setHp(14)
                .setManaCost(9)
                .setBuyPrice(300)
                .setSellPrice(300)
                .setName("dive siah")
                .create()
        );
        //14
        shopCards.add(new Minion.MinionBuilder()
                .setAttackType(new Ranged())
                .setAp(12)
                .setHp(12)
                .setManaCost(9)
                .setBuyPrice(300)
                .setSellPrice(300)
                .setName("ghool sang andaz")
                .create()
        );
        //15
        shopCards.add(new Minion.MinionBuilder()
                //todo PASIVE must be added by Mostafa
                .create()
        );
        //16
        shopCards.add(new Minion.MinionBuilder()
                .setAttackType(new Melee())
                .setAp(8)
                .setHp(16)
                .setManaCost(6)
                .setSellPrice(300)
                .setBuyPrice(300)
                .setName("dive goraz savar")
                .create()
        );
        //17
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_DEATH)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-2)
                                .create())
                        .setTargetSociety()//todo 8Adjacent cells must be added to target society
                        .create())
                .setAttackType(new Hybrid)
                .setAp(11)
                .setHp(12)
                .setManaCost(7)
                .setBuyPrice(500)
                .setSellPrice(500)
                .setName("ghoole tak cheshm")
                .create()
        );
        //18
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                        .create())
                        .create())
                .create()
        );


        //spells
        {
            shopCards.add(new SpellCard.SpellCardBuilder()
                    .setSpell(new Spell(new OneEnemyUnit(), false, new Buff.BuffBuilder()
                            .setDuration(Buff.INFINITY).setDeltaHP(-4).create()))
                    .setBuyPrice(400)
                    .setManaCost(1)
                    .setName("Fireball")
                    .create()
            );
        }
        //heroes
        shopCards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-8)
                                .setDuration(Buff.INFINITY)
                                .create())
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
