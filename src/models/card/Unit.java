package models.card;

import models.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Unit extends Card implements Buffable {
    protected Spell specialPower;
    protected SpecialPowerCastTime specialPowerCastTime;
    private int hp;
    private int ap;
    private Cell currentCell;
    private ArrayList<Flag> flags;
    private Player player;
    private ArrayList<Buff> buffs = new ArrayList<>();
    private boolean moved; // todo should be checked
    private boolean attacked; // todo should be checked
    private AttackType attackType;
    private boolean combo; // todo implement combo attack func

    protected Unit(String name, int manaCost, int buyPrice, int sellPrice, String description, int hp, int ap, AttackType attackType, boolean combo, Spell specialPower, SpecialPowerCastTime specialPowerCastTime) {
        super(name, manaCost, buyPrice, sellPrice, description);
        this.hp = hp;
        this.ap = ap;
        this.attackType = attackType;
        this.combo = combo;
        this.specialPower = specialPower;
        this.specialPowerCastTime = specialPowerCastTime;
    }

    public Spell getSpecialPower() {
        return specialPower;
    }

    public void castSpecialPower(SpecialPowerCastTime time, Cell cell) {
        if (time == specialPowerCastTime)
            specialPower.cast(getPlayer(), cell);
    }

    public static abstract class UnitBuilder extends CardBuilder {
        private Spell specialPower;
        private SpecialPowerCastTime specialPowerCastTime;
        private int hp;
        private int ap;
        private AttackType attackType;


        public UnitBuilder setHp(int hp) {
            this.hp = hp;
            return this;
        }

        public UnitBuilder setAp(int ap) {
            this.ap = ap;
            return this;
        }

        public UnitBuilder setAttackType(AttackType attackType) {
            this.attackType = attackType;
            return this;
        }

        public UnitBuilder setSpecialPower(Spell specialPower) {
            this.specialPower = specialPower;
            return this;
        }

        public UnitBuilder setSpecialPowerCastTime(SpecialPowerCastTime specialPowerCastTime) {
            this.specialPowerCastTime = specialPowerCastTime;
            return this;
        }


        int getHp() {
            return hp;
        }

        int getAp() {
            return ap;
        }

        AttackType getAttackType() {
            return attackType;
        }


        Spell getSpecialPower() {
            return specialPower;
        }

        SpecialPowerCastTime getSpecialPowerCastTime() {
            return specialPowerCastTime;
        }
    }

    public int getHp() {
        return hp;
    }

    public int getAp() {
        return ap;
    }

    public AttackType getAttackType() {
        return attackType;
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

    public void nextTurn() {
        doBuffs();
        moved = false;
        attacked = false;
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

    private int getHoly() {
        int holy = 0;
        for (Buff buff : buffs)
            holy += buff.getHoly();
        return holy;
    }

    public Player getPlayer() {
        return player;
    }

    public void attack(Unit opponent) throws UnitAttackedThisTurnException, UnitStunnedException {
        if (isStunned())
            throw new UnitStunnedException();
        if (attacked)
            throw new UnitAttackedThisTurnException();
        int damage = -ap + opponent.getHoly();
        attacked = true;
        moved = true;
        if (damage < 0)
            opponent.changeHP(damage);
    }

    public void counterAttack(Unit opponent) {
        int damage = -ap + opponent.getHoly();
        if (!isDisarmed() && damage < 0)
            opponent.changeHP(damage);
    }

    public void comboAttack(Unit opponent, ArrayList<Unit> allies) { // todo add exceptions
        int damage = -ap;
        for (Unit unit : allies)
            damage -= unit.getAp();
        damage += opponent.getHoly();
        if (damage < 0)
            opponent.changeHP(damage);
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void move(Cell currentCell) throws UnitMovedThisTurnException, UnitStunnedException {
        if (isStunned())
            throw new UnitStunnedException();
        if (moved)
            throw new UnitMovedThisTurnException();
        this.currentCell = currentCell;
        moved = true;
    }

    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
    }
}
