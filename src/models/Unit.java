package models;

import java.util.ArrayList;
import java.util.List;

public abstract class Unit extends Card implements Buffable{
    private int hp;
    private int ap;
    private Cell currentCell;
    private ArrayList<Flag> flags;
    private Player player;
    private ArrayList<Buff> buffs = new ArrayList<>();
    private boolean moved; // todo should be checked
    private boolean attacked; // todo should be checked
    private AttackType attackType;

    protected Unit(String name, int manaCost, int buyPrice, int sellPrice, int hp, int ap, AttackType attackType) {
        super(name, manaCost, buyPrice, sellPrice);
        this.hp = hp;
        this.ap = ap;
        this.attackType = attackType;
    }

    @Override
    public void addBuff(Buff buff) {
        buffs.add(buff);
    }

    @Override
    public void addBuffs(List<Buff> buffs) {
        buffs.forEach(this::addBuff);
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    @Override
    public void doBuffs() {
        for (Buff buff : buffs)
            buff.cast(this);
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

    public void attack(Unit opponent) { // todo check if is same as counter attack?

    }

    public boolean isDead() {
        return hp <= 0;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }
}
