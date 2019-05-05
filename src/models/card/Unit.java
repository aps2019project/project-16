package models.card;

import models.Pair;
import models.*;
import models.attackType.AttackType;
import models.card.exception.*;
import models.magic.Buff;
import models.magic.Buffable;
import models.magic.Spell;

import java.util.ArrayList;
import java.util.List;

public abstract class Unit extends Card implements Buffable {
    private ArrayList<Pair<SpecialPowerCastTime, Spell>> specialPowers = new ArrayList<>();
    private int hp;
    private int ap;
    private Cell currentCell;
    private ArrayList<Flag> flags = new ArrayList<>();
    private Player player;
    private ArrayList<Buff> buffs = new ArrayList<>();
    private boolean moved;
    private boolean attacked;
    private AttackType attackType;
    private boolean combo;
    private boolean piercingHoly;
    boolean hasFlag = false;


    protected Unit(String name, int manaCost, int buyPrice, int sellPrice, String description, int hp, int ap, AttackType attackType, boolean combo, boolean piercingHoly, Spell specialPower, SpecialPowerCastTime specialPowerCastTime) {
        super(name, manaCost, buyPrice, sellPrice, description);
        this.hp = hp;
        this.ap = ap;
        this.attackType = attackType;
        this.combo = combo;
        this.piercingHoly = piercingHoly;
        if (specialPower != null)
            this.specialPowers.add(new Pair<>(specialPowerCastTime, specialPower));
    }

    public void castSpecialPower(SpecialPowerCastTime time, Cell cell) {
        for (Pair<SpecialPowerCastTime, Spell> specialPower : specialPowers)
            if (time == specialPower.getKey())
                specialPower.getValue().cast(getPlayer(), cell);
    }

    public static abstract class UnitBuilder extends CardBuilder {
        private Spell specialPower;
        private SpecialPowerCastTime specialPowerCastTime;
        private int hp;
        private int ap;
        private AttackType attackType;
        private boolean piercingHoly = false;

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

        public UnitBuilder setPiercingHoly() {
            this.piercingHoly = true;
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

        boolean isPiercingHoly() {
            return piercingHoly;
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    @Override
    public void addBuff(Buff buff) {
        buffs.add(buff.copy());
    }

    @Override
    public void addBuffs(List<Buff> buffs) {
        buffs.forEach(this::addBuff);
    }

    public void addSpecialPower(SpecialPowerCastTime specialPowerCastTime, Spell spell) {
        specialPowers.add(new Pair<>(specialPowerCastTime, spell));
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
        castSpecialPower(SpecialPowerCastTime.PASSIVE, getCurrentCell());
    }

    public void changeHP(int amount) {
        hp += amount;
    }

    public void dealDamage(int amount) {
        if (amount - getHoly() > 0)
            hp -= amount - getHoly();
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

    public void attack(Unit opponent) throws AttackException {
        this.checkCanAttack(opponent);
        attacked = true;
        moved = true;
        if (piercingHoly)
            opponent.dealDamage(ap + opponent.getHoly());
        else
            opponent.dealDamage(ap);
        castSpecialPower(SpecialPowerCastTime.ON_ATTACK, opponent.getCurrentCell());
    }

    public void counterAttack(Unit opponent) {
        if (!this.getAttackType().canAttack(this.getCurrentCell(), opponent.getCurrentCell()))
            return;
        if (isDisarmed() || isStunned())
            return;
        opponent.dealDamage(ap);
    }

    public void comboAttack(Unit opponent, ArrayList<Unit> allies) throws UnitHasNotComboException, AttackException {
        int damage = ap;
        for (Unit unit : allies) {
            if (!unit.hasCombo())
                throw new UnitHasNotComboException();
            unit.checkCanAttack(opponent);
            damage += unit.getAp();
        }
        opponent.dealDamage(damage);
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void move(Cell newCell) throws UnitMovedThisTurnException, UnitStunnedException, CellIsNotFreeException {
        if (isStunned())
            throw new UnitStunnedException();
        if (moved)
            throw new UnitMovedThisTurnException();
        if (newCell.hasUnit())
            throw new CellIsNotFreeException();
        this.currentCell = newCell;
        moved = true;
    }

    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
    }

    public boolean hasCombo() {
        return combo;
    }

    public void checkCanAttack(Unit opponent) throws AttackException {
        if (!this.getAttackType().canAttack(this.getCurrentCell(), opponent.getCurrentCell()))
            throw new OpponentNotInRangeException();
        if (isStunned())
            throw new UnitStunnedException();
        if (attacked)
            throw new UnitAttackedThisTurnException();
    }

    public ArrayList<Flag> getFlags() {
        return flags;
    }

    public void addFlag(Flag flag) {
        flags.add(flag);
        flag.setOwnerUnit(this);
    }

    public void removeFlag() {
        while (flags.size() > 0)
            flags.remove(0);
    }

    public void dropFlags(Cell cell, Unit unit) {
        for (Flag flag : unit.getFlags()) {
            flag.setOwnerUnit(null);
            flag.setCurrentCell(cell);
        }
        unit.removeFlag();
    }
}
