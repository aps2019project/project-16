package models;

import sun.text.normalizer.UBiDiProps;

import java.util.ArrayList;

public abstract class Unit extends Card {
    private int hp;
    private int ap;
    private Cell currentCell;
    private ArrayList<Flag> flags;
    private Player player;
    private ArrayList<Buff> buffs = new ArrayList<>();
    private boolean moved; // ???
    private boolean attacked; // ???
    private AttackType attackType;

    protected Unit(int manaCost, int buyPrice, int sellPrice, int hp, int ap) { //todo complete
        super(manaCost, buyPrice, sellPrice);

    }

    public void addBuff(Buff buff) {
        buffs.add(buff);
    }

    public void changeHP(int amount) {
        hp += amount;
    }

    public void changeAP(int amount) {
        ap += amount;
    }

    private boolean isStunned() {
        for (Buff buff : buffs)
            if (buff.hasStun())
                return true;
        return false;
    }

    private boolean isDisarmed() {
        for (Buff buff : buffs)
            if (buff.hasDisarm())
                return true;
        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public void attack(Unit opponent) { // same as counter attack?

    }

    public boolean isDead() {
        return hp <= 0;
    }
}
