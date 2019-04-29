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
        addMinions(shopCards);
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
                        .setTargetSociety() // todo must be corrected by Mostafa
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

    private static void addMinions(ArrayList<Card> cards) {
        //minions
        //1
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
                .create() // todo combe type must be iplemented and this minion must be completed
        );
        //7
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDisarm()
                                .setDuration(1)
                                .create())
                        .addBuff(new Buff.BuffBuilder()
                                .setPoison(1)
                                .setDuration(4)
                                .create())
                        .setTargetSociety(new AllEnemyUnits())
                        .create())
                .setAttackType(new Melee())
                .setAp(6)
                .setHp(6)
                .setManaCost(4)
                .setBuyPrice(700)
                .setSellPrice(700)
                .setName("jasoose toorani")
                .create()
        );
        //11
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()// todo COMBO must be added ny Mostafa
                .create()
        );
        //13
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(PASSIVE)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(10)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .setTargetSociety() // todo target society ke roo khodam cast she mikham
                        .create())
                .setAttackType(new Ranged())
                .setAp(2)
                .setHp(0)
                .setManaCost(2)
                .setSellPrice(200)
                .setBuyPrice(200)
                .setName("oghab")
                .create()
        );
        //16
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_DEATH)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-2)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .setTargetSociety(new EnemyMinionsAdjacentToCell())
                        .create())
                .setAttackType(new Hybrid())
                .setAp(11)
                .setHp(12)
                .setManaCost(7)
                .setBuyPrice(500)
                .setSellPrice(500)
                .setName("ghoole tak cheshm")
                .create()
        );
        //18
        cards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setPoison(1)
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setHoly(1) // todo this is wrong and must be checked with the DOC and new buff should be add
                                .create())
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-2)
                                .setDuration(1)
                                .create())
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-4)
                                .setDuration(2)//todo there is a problem when a buff should cast at turn = turn + X - checked with DOC
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
        cards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-8)
                                .setDuration(2)
                                .create())
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(8)
                                .setDuration(1)
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
        cards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_ATTACK)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-6)
                                .setDuration(2)
                                .create())
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(6)
                                .setDuration(1)
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(PASSIVE)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaAP(2)
                                .setHoly(1)
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
        cards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime()// todo ON_Turn is not available
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaAP(1)
                                .setDuration()//todo continiuous buff NOT  implemented
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_DEFEND)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                //todo implement at the end
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(ON_SPAWN)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setStun()
                                .setDuration(1)
                                .create())
                        .setTargetSociety(new EnemyMinionsAdjacentToCell())
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
        cards.add(new Minion.MinionBuilder()
                .setSpecialPowerCastTime(PASSIVE)
                .setSpecialPower(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setHoly(1) //todo MOSTAFA ridi boro holy ro dorost kon
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
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
        cards.add(new Minion.MinionBuilder()
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
    }

    private static void addSpells(ArrayList<Card> cards) {
        //1
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDisarm()
                                .setDuration(Buff.INFINITY)
                                .create())
                        .setTargetSociety()//todo must be implemented
                        .create())
                .setManaCost(0)
                .setBuyPrice(1000)
                .setSellPrice(1000)
                .setName("total disarm")
                .create()
        );
        //2
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDuration(Buff.INFINITY)//todo must be checked
                                //todo must be implemented
                                .create())
                        .setTargetSociety(new SquareOfCells(2))
                        .create())
                .setManaCost(2)
                .setSellPrice(1500)
                .setBuyPrice(1500)
                .setName("Area Disepel")
                .create()
        );
        //3
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaAP(2)
                                .setDuration(Buff.INFINITY)//todo must be checked
                                .create())
                        .setTargetSociety()//todo must be implemented
                        .create())
                .setManaCost(1)
                .setBuyPrice(250)
                .setSellPrice(250)
                .setName("Empower")
                .create()
        );
        //4
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-4)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .setTargetSociety(///todo must be implemented)
                        .create())
                .setManaCost(1)
                .setSellPrice(250)
                .setBuyPrice(250)
                .setName("Fireball")
                .create()
        );
        //5
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaAP(4)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .create())
                .setManaCost(2)
                .setBuyPrice(400)
                .setSellPrice(400)
                .setName("God Strength")
                .create()
        );
        //6
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setPoison(2)
                                .setDuration(2)
                                .create())
                        .setTargetSociety(new SquareOfCells(2))
                        .create())
                .setManaCost(3)
                .setSellPrice(600)
                .setBuyPrice(600)
                .setName("HellFire")
                .create()
        );
        //7
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-8)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .create())
                .setManaCost(2)
                .setBuyPrice(1250)
                .setSellPrice(1250)
                .setName("Lightening Bolt")
                .create()
        );
        //8
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setPoison(1)
                                .setDuration(1)
                                .create())
                        .setTargetSociety(new SquareOfCells(3))
                        .create())
                .setManaCost(5)
                .setSellPrice(900)
                .setBuyPrice(900)
                .setName("Poison Lake")
                .create()
        );
        //9
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaAP(4)
                                .setDisarm()
                                .setDuration(3)
                                .create())
                        .create())
                .setManaCost(0)
                .setSellPrice(650)
                .setBuyPrice(650)
                .setName("Madness")
                .create());
        //10
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setDisarm()
                                .setDuration(1)
                                .create())
                        .setTargetSociety()//todo must be implemented
                        .create())
                .setManaCost(9)
                .setBuyPrice(2000)
                .setSellPrice(2000)
                .setName("All Disarm")
                .create()
        );
        //11
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setDuration(4)
                                .setPoison(1)
                                .create())
                        .create())
                .setManaCost(8)
                .setSellPrice(1500)
                .setBuyPrice(1500)
                .setName("All Poison")
                .create());
        //12
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                //todo must be implemented
                                .create())
                        .create())
                .setManaCost(0)
                .setSellPrice(2100)
                .setBuyPrice(2100)
                .setName("Dispel")
                .create()
        );
        //13
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(-6)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .addBuff(new Buff.BuffBuilder()
                                .setDuration(3)
                                .setHoly(2)
                                .create())
                        .create())
                .setManaCost(0)
                .setSellPrice(2250)
                .setBuyPrice(2250)
                .setName("Health with profit")
                .create()
        );
        //14
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaAP(6)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .create())
                .setManaCost(2)
                .setBuyPrice(2500)
                .setSellPrice(2500)
                .setName("Power Up")
                .create()
        );
        //15
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaAP(2)
                                //todo what is "daem" in duration
                                .create())
                        .create())
                .setManaCost(4)
                .setBuyPrice(2000)
                .setSellPrice(2000)
                .setName("All Power")
                .create());
        //16
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaHP(6)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .create())
                .setManaCost(4)
                .setSellPrice(1500)
                .setBuyPrice(1500)
                .setName("All Attack")
                .create());
        //17
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaAP(-4)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .create())
                .setManaCost(1)
                .setBuyPrice(1000)
                .setBuyPrice(1000)
                .setName("Weakening")
                .create()
        );
        //18
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaAP(8)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .addBuff(new Buff.BuffBuilder()
                                .setDuration(Buff.INFINITY)
                                .setDeltaHP(-6)
                                .create())
                        .create())
                .setManaCost(2)
                .setBuyPrice(1600)
                .setSellPrice(1600)
                .setName("Sacrifice ")
                .create());
        //19
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                //todo what should i do ??? for buff
                                .create()))
                .setManaCost(9)
                .setSellPrice(1750)
                .setBuyPrice(1750)
                .setName("Kings Guard")
                .create());
        //20
        cards.add(new SpellCard.SpellCardBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setStun()
                                .setDuration(2)
                                .create())
                        .create())
                .setManaCost(1)
                .setSellPrice(1200)
                .setBuyPrice(1200)
                .setName("Shock")
                .create());
    }

    private static void addHeroes(ArrayList<Card> cards) {
        //1
        cards.add(new Hero.HeroBuilder()
                .setSpellCoolDown(2)
                .setSpellManaCost(1)
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaAP(4)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .create())
                .setAttackType(new Melee())
                .setAp(4)
                .setManaCost(0)
                .setHp(50)
                .setSellPrice(8000)
                .setBuyPrice(8000)
                .setName("dive sefid")
                .create());
        //2
        cards.add(new Hero.HeroBuilder()
                .setSpellCoolDown(8)
                .setSpellManaCost(5)
                .setSpell(new Spell.SpellBuilder()
                        .addBuff(new Buff.BuffBuilder()
                                .setStun()
                                .setDuration(1)
                                .create())
                        .setTargetSociety()//todo must be implemented
                        .create())
                .setAttackType(new Melee())
                .setAp(4)
                .setHp(50)
                .setManaCost(0)
                .setBuyPrice(9000)
                .setSellPrice(9000)
                .setName("simorgh")
                .create()
        );
        //3
        cards.add(new Hero.HeroBuilder()
                .setSpellCoolDown(1)
                .setSpellManaCost(0)
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setDisarm()
                                .setDuration()//todo there is EHBHAM !!!
                                .create())
                        .create())
                .setAttackType(new Melee())
                .setAp(4)
                .setHp(50)
                .setManaCost(0)
                .setSellPrice(8000)
                .setBuyPrice(8000)
                .setName("ezhdehaye haft sar")
                .create());
        //4
        cards.add(new Hero.HeroBuilder()
                .setSpellCoolDown(2)
                .setSpellManaCost(1)
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setDisarm()
                                .setDuration(1)
                                .create())
                        .create())
                .setAttackType(new Melee())
                .setAp(4)
                .setHp(50)
                .setManaCost(0)
                .setBuyPrice(8000)
                .setSellPrice(8000)
                .setName("rakhsh")
                .create());
        //5
        cards.add(new Hero.HeroBuilder()
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setPoison(1)
                                .setDuration(3)
                                .create())
                        .create())
                .setAttackType(new Melee())
                .setAp(2)
                .setHp(50)
                .setManaCost(0)
                .setSellPrice(10000)
                .setBuyPrice(10000)
                .setName("zahhak")
                .create());
        //6
        cards.add(new Hero.HeroBuilder()
                .setSpellCoolDown(3)
                .setSpellManaCost(1)
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setHoly(1)
                                .setDuration(3)
                                .create())
                        .create())
                .setAttackType(new Melee())
                .setAp(4)
                .setHp(50)
                .setManaCost(0)
                .setBuyPrice(8000)
                .setSellPrice(8000)
                .setName("kave")
                .create());
        //7
        cards.add(new Hero.HeroBuilder()
                .setSpellCoolDown(2)
                .setSpellManaCost(2)
                .setSpell(new Spell.SpellBuilder()
                        .setTargetSociety()//todo must be implemented
                        .addBuff(new Buff.BuffBuilder()
                                .setDeltaAP(-4)
                                .setDuration(Buff.INFINITY)
                                .create())
                        .create())
                .setAttackType(new Ranged(6))
                .setAp(2)
                .setHp(30)
                .setManaCost(0)
                .setSellPrice(10000)
                .setBuyPrice(10000)
                .setName("arash")
                .create());
        //8
        cards.add(new Hero.HeroBuilder()
                .setSpellCoolDown(2)
                .setSpellManaCost(1)
                .setSpell(new Spell.SpellBuilder()
                        .setDispel()
                        .setTargetSociety()//todo must be implemented
                        .create())
                .setAttackType(new Ranged(3))
                .setAp(3)
                .setHp(40)
                .setManaCost(0)
                .setBuyPrice(11000)
                .setSellPrice(11000)
                .setName("afsane")
                .create());
        //9
        cards.add(new Hero.HeroBuilder()
                //todo must be hard coded
                .setAttackType(new Hybrid(3))
                .setAp(3)
                .setHp(35)
                .setManaCost(0)
                .setSellPrice(12000)
                .setBuyPrice(12000)
                .setName("esfandiar")
                .create());
        //10
        cards.add(new Hero.HeroBuilder()
                .setAttackType(new Hybrid(4))
                .setAp(7)
                .setHp(55)
                .setManaCost(0)
                .setBuyPrice(8000)
                .setSellPrice(8000)
                .setName("rostam")
                .create());
    }

    public static void initShopUsableItems(ArrayList<Item> shopItems) {
        //todo must be implemented
    }

    public static void initCollectibleItems() {
        //todo must be implemented
    }

}
