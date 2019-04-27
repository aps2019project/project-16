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
                                .setPoison()//todo what should i add to set poison
                                .setDuration(3)
                                .create())
                        .setTargetSociety(new OneEnemyUnit())
                        .create())
                .setAttackType(new Ranged())
                .setAp(6)
                .setHp(5)
                .setManaCost(4)
                .setSellPrice(300)
                .setBuyPrice(300)
                .setName("mare sammi")
                .create()
        );
        //19
        shopCards.add(new Minion.MinionBuilder()
                .setAttackType(new Ranged())
                .setAp(5)
                .setHp(9)
                .setManaCost(5)
                .setSellPrice(250)
                .setBuyPrice(250)
                .setName("ezhdehaye atash andaz")
                .create()
        );
        //20
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setHoly() // todo this is wrong and must be checked with the DOC and new buff should be add
                                .create())
                        .setTargetSociety(null) //todo what if some one has no target society
                        .create())
                .setAttackType(new Melee())
                .setAp(8)
                .setHp(1)
                .setManaCost(2)
                .setSellPrice(600)
                .setBuyPrice(600)
                .setName("shire darande")
                .create()
        );
        //21
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_SPAWN)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDuration(Buff.INFINITY)
                                //todo new buff should be added  - revese of Holly buff
                                .create())
                        .setTargetSociety()//todo this kind of target society is not available
                        .create())
                .setAttackType(new Ranged())
                .setAp(7)
                .setHp(14)
                .setManaCost(8)
                .setBuyPrice(500)
                .setSellPrice(500)
                .setName("mare ghool peykar")
                .create()
        );
        //22
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-6)
                                .setDuration(1)
                                .create())
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-4)
                                .setDuration()//todo there is a problem when a buff should cast at turn = turn + X - checked with DOC
                                .create())
                        .setTargetSociety() // todo this target society for one minion enemy is not available
                        .create())
                .setAttackType(new Melee())
                .setAp(2)
                .setHp(8)
                .setManaCost(5)
                .setBuyPrice(400)
                .setSellPrice(400)
                .setName("gorge sefid")
                .create()
        );
        //23
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-8)
                                //todo has PROBLEM in cst time!!!
                                .create())
                        .setTargetSociety()//todo one minion enemy target society is not available
                        .create())
                .setAttackType(new Melee())
                .setAp(2)
                .setHp(6)
                .setManaCost(4)
                .setBuyPrice(400)
                .setSellPrice(400)
                .setName("palang")
                .create()
        );
        //24
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-6)
                                //todo duration has problem
                                .create())
                        .setTargetSociety()//todo minion target society is not available
                        .create())
                .setAttackType(new Melee())
                .setAp(1)
                .setHp(6)
                .setManaCost(3)
                .setSellPrice(400)
                .setBuyPrice(400)
                .setName("gorg")
                .create()
        );
        //25
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(PASSIVE)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaAP(2)
                                .setDeltaHP(-1)
                                .setDuration(1)
                                .create())
                        .setTargetSociety() // todo friendly minion target society is not available
                        .create())
                .setAttackType(new Ranged())
                .setAp(4)
                .setHp(5)
                .setManaCost(4)
                .setSellPrice(550)
                .setBuyPrice(550)
                .setName("jadoogar")
                .create()
        );
        //26
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(PASSIVE)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaAP(2)
                                .setHoly()
                                .setDuration(Buff.INFINITY)
                                .create())
                        .create())
                .setAttackType(new Ranged())
                .setAp(6)
                .setHp(6)
                .setManaCost(6)
                .setBuyPrice(550)
                .setSellPrice(550)
                .setName("jadoogare azam")
                .create()
        );
        //27
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime()// todo ON_Turn is not available
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaAP(1)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .setTargetSociety() //todo all enemy minion target society is not availalble
                        .create())
                .setAttackType(new Ranged())
                .setAp(4)
                .setHp(10)
                .setManaCost(5)
                .setSellPrice(500)
                .setBuyPrice(500)
                .setName("gen")
                .create()
        );
        //28
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_DEFEND)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                //todo .setNotDisarm must be added
                                //todo target society ?????!!!???
                                .setDuration(Buff.INFINITY)
                                .create())
                        .create())
                .setAttackType(new Melee())
                .setAp(14)
                .setHp(10)
                .setManaCost(6)
                .setBuyPrice(500)
                .setSellPrice(500)
                .setName("goraze vahshi")
                .create()
        );
        //29
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_DEFEND)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                //todo .setNotPoison must be added
                                //todo target society ????!!!???
                                .create())
                        .create())
                .setAttackType(new Melee())
                .setAp(12)
                .setHp(20)
                .setManaCost(8)
                .setBuyPrice(400)
                .setSellPrice(400)
                .setName("piran")
                .create()
        );
        //30
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_DEFEND)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                //todo doesnt get nagative buff from others
                                //todo target society
                                .setDuration(Buff.INFINITY)
                                .create())
                        .create())
                .setAttackType(new Ranged())
                .setAp(7)
                .setHp(5)
                .setManaCost(4)
                .setBuyPrice(450)
                .setSellPrice(450)
                .setName("giv")
                .create()
        );
        //31
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_SPAWN)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-16)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .setTargetSociety()//todo random enemy minion target society is not available
                        .create())
                .setAttackType(new Melee())
                .setAp(9)
                .setHp(16)
                .setManaCost(8)
                .setSellPrice(450)
                .setBuyPrice(450)
                .setName("bahman")
                .create()
        );
        //32
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_DEFEND)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                //todo implement at rthe end
                                .create())
                        .setTargetSociety(new AllEnemyUnits())
                        .create())
                .setAttackType(new Melee())
                .setAp(8)
                .setHp(14)
                .setManaCost(7)
                .setBuyPrice(400)
                .setSellPrice(400)
                .setName("ashkboos")
                .create()
        );
        //33
        shopCards.add(new Minion.MinionBuilder()
                .setAttackType(new Ranged())
                .setAp(20)
                .setHp(6)
                .setManaCost(4)
                .setSellPrice(500)
                .setBuyPrice(500)
                .setName("iraj")
                .create()
        );
        //34
        shopCards.add(new Minion.MinionBuilder()
                .setAttackType(new Hybrid())
                .setAp(8)
                .setHp(30)
                .setManaCost(9)
                .setSellPrice(600)
                .setBuyPrice(600)
                .setName("ghoole bozorg")
                .create()
        );
        //35
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .setTargetSociety(new OneEnemyUnit())
                        .setDispel()
                        .create())
                .setAttackType(new Melee())
                .setAp(4)
                .setHp(10)
                .setManaCost(4)
                .setBuyPrice(550)
                .setSellPrice(550)
                .setName("ghoole do sar")
                .create()
        );
        //36
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_SPAWN)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setStun()
                                .setDuration(1)
                                .create())
                        .setTargetSociety() //todo adjacent enemy cells target society not available
                        .create())
                .setAttackType(new Ranged())
                .setAp(4)
                .setHp(3)
                .setManaCost(3)
                .setSellPrice(500)
                .setBuyPrice(500)
                .setName("nanae sarma")
                .create()
        );
        //37
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(PASSIVE)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setHoly() //todo MOSTAFA ridi boro holy ro dorost kon
                                //todo continious must be implemented
                                .create())
                        .setTargetSociety()//todo must be checked
                        .create())
                .setAttackType(new Melee())
                .setAp(1)
                .setHp(1)
                .setManaCost(3)
                .setSellPrice(650)
                .setBuyPrice(650)
                .setName("foolad zereh")
                .create()
        );
        //38
        shopCards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_DEFEND)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-6)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .create())
                .setAttackType(new Melee())
                .setAp(5)
                .setHp(8)
                .setManaCost(4)
                .setBuyPrice(350)
                .setSellPrice(350)
                .setName("siavash")
                .create()
        );
        //39
        shopCards.add(new Minion.MinionBuilder()
                //todo combo must be implemented
                .setAttackType(new Melee())
                .setAp(4)
                .setHp(10)
                .setManaCost(5)
                .setSellPrice(600)
                .setBuyPrice(600)
                .setName("shahghool")
                .create()
        );
        //40
        shopCards.add(new Minion.MinionBuilder()
                //todo combo must be implemented
                .setAttackType(new Melee())
                .setAp(6)
                .setHp(6)
                .setManaCost(3)
                .setBuyPrice(600)
                .setBuyPrice(600)
                .setName("arzhang div")
                .create()
        );
        //spells
        shopCards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell(new OneEnemyUnit(), false, new Buff.BuffBuilder()
                        .setDuration(Buff.INFINITY).setDeltaHP(-4).create()))
                .setBuyPrice(400)
                .setManaCost(1)
                .setName("Fireball")
                .create()
        );
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
