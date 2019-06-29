package models.card;

import models.Pair;
import models.*;
import models.attackType.AttackType;
import exception.*;
import models.magic.Buff;
import models.magic.Buffable;
import models.magic.Spell;
import newView.BattleView.ClientSender;
import newView.BattleView.gameActs.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    private int addedApPerAttack;
    private boolean notDisarmable;
    private boolean notGetWeakerAttack;
    private boolean notGetNegativeEffect;
    private boolean notGetPoisoned;
    private HashMap<Unit, Integer> attackedTo = new HashMap<>();
    boolean hasFlag = false;


    protected Unit(String name, int manaCost, int buyPrice, int sellPrice, String description, int hp, int ap,
                   AttackType attackType, boolean combo, Spell specialPower, SpecialPowerCastTime specialPowerCastTime,
                   int addedApPerAttack, boolean piercingHoly, boolean notDisarmable, boolean notGetWeakerAttack,
                   boolean notGetNegativeEffect, boolean notGetPoisoned) {
        super(name, manaCost, buyPrice, sellPrice, description);
        this.hp = hp;
        this.ap = ap;
        this.attackType = attackType;
        this.combo = combo;
        this.piercingHoly = piercingHoly;
        if (specialPower != null)
            this.specialPowers.add(new Pair<>(specialPowerCastTime, specialPower));
        this.addedApPerAttack = addedApPerAttack;
        this.notDisarmable = notDisarmable;
        this.notGetWeakerAttack = notGetWeakerAttack;
        this.notGetNegativeEffect = notGetNegativeEffect;
        this.notGetPoisoned = notGetPoisoned;
    }

    public void castSpecialPower(SpecialPowerCastTime time, Cell cell) {
        for (Pair<SpecialPowerCastTime, Spell> specialPower : specialPowers)
            if (time == specialPower.getKey()) {
                specialPower.getValue().cast(getPlayer(), cell);

                ClientSender.sendToViewer(new MinionSpecialPowerAct(time, cell.getRow(), cell.getColumn()));
            }
    }

    public static abstract class UnitBuilder extends CardBuilder {
        private Spell specialPower;
        private SpecialPowerCastTime specialPowerCastTime;
        private int hp;
        private int ap;
        private AttackType attackType;
        private boolean piercingHoly = false;
        private int addedApPerAttack = 0;
        private boolean notDisarmable = false;
        private boolean notGetWeakerAttack = false;
        private boolean notGetNegativeEffect = false;
        private boolean notGetPoisoned = false;

        public UnitBuilder setPiercingHoly() {
            this.piercingHoly = true;
            return this;
        }

        public UnitBuilder setAddedApPerAttack(int addedApPerAttack) {
            this.addedApPerAttack = addedApPerAttack;
            return this;
        }

        public UnitBuilder setNotDisarmable() {
            this.notDisarmable = true;
            return this;
        }

        public UnitBuilder setNotGetWeakerAttack() {
            this.notGetWeakerAttack = true;
            return this;
        }

        public UnitBuilder setNotGetNegativeEffect() {
            this.notGetNegativeEffect = true;
            return this;
        }

        public UnitBuilder setNotGetPoisoned() {
            this.notGetPoisoned = true;
            return this;
        }

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

        boolean isPiercingHoly() {
            return piercingHoly;
        }

        Spell getSpecialPower() {
            return specialPower;
        }

        SpecialPowerCastTime getSpecialPowerCastTime() {
            return specialPowerCastTime;
        }

        int getAddedApPerAttack() {
            return addedApPerAttack;
        }

        boolean isNotDisarmable() {
            return notDisarmable;
        }

        boolean isNotGetWeakerAttack() {
            return notGetWeakerAttack;
        }

        boolean isNotGetNegativeEffect() {
            return notGetNegativeEffect;
        }

        boolean isNotGetPoisoned() {
            return notGetPoisoned;
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
        if (notGetNegativeEffect && !buff.isPositive())
            return;
        if (notGetPoisoned && buff.hasPoison())
            return;
        buffs.add(buff.copy());
    }

    public void addBuff(Buff buff, Player player) {
        if (notGetNegativeEffect && !buff.isPositive())
            return;
        if (notGetPoisoned && buff.hasPoison())
            return;
        Buff newBuff = buff.copy();
        buffs.add(newBuff);
        newBuff.start(this, player);
    }

    @Override
    public void addBuffs(List<Buff> buffs) {
        buffs.forEach(this::addBuff);
    }

    public void addBuffs(List<Buff> buffs, Player player) {
        buffs.forEach(buff -> addBuff(buff, player));
    }

    public void addSpecialPower(SpecialPowerCastTime specialPowerCastTime, Spell spell) {
        specialPowers.add(new Pair<>(specialPowerCastTime, spell));
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public boolean isNotGetNegativeEffect() {
        return notGetNegativeEffect;
    }

    @Override
    public void doBuffs() {
        for (Buff buff : buffs)
            buff.castOnEndTurn(this);
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

    public void getDamage(int amount) {
        if (notGetNegativeEffect)
            return;
        if (notGetWeakerAttack && amount >= ap)
            return;
        if (amount - getHoly() > 0) {
            hp -= amount - getHoly();

            if (getCurrentCell() != null) {
                ClientSender.sendToViewer(new ChangeApHpAct(getCurrentCell().getRow(), getCurrentCell().getColumn()
                        , true, -(amount - getHoly()), false));
            }
        }
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
        if (notDisarmable)
            return false;
        for (Buff buff : buffs)
            if (buff.hasDisarm())
                return true;
        return false;
    }

    private int getHoly() {
        int holy = 0;
        for (Buff buff : buffs)
            holy += buff.getHoly();
        for (Buff buff : currentCell.getCellEffect())
            holy += buff.getHoly();
        return holy;
    }

    public Player getPlayer() {
        return player;
    }

    public void attack(Unit opponent) throws AttackException {
        this.checkCanAttack(opponent);

        ClientSender.sendToViewer(
                new AttackAct(this.getCurrentCell(), opponent.getCurrentCell(), this, opponent));

        attackedTo.putIfAbsent(opponent, 0);
        int damage = ap;
        damage += addedApPerAttack * attackedTo.get(opponent);
        attacked = true;
        moved = true;
        if (piercingHoly)
            opponent.getDamage(damage + opponent.getHoly());
        else
            opponent.getDamage(damage);
        attackedTo.computeIfPresent(opponent, (unit, integer) -> integer + 1); //todo check if work correctly
        castSpecialPower(SpecialPowerCastTime.ON_ATTACK, opponent.getCurrentCell());
    }

    public void counterAttack(Unit opponent) {
        if (!this.getAttackType().canAttack(this.getCurrentCell(), opponent.getCurrentCell()))
            return;
        if (isDisarmed() || isStunned())
            return;

        ClientSender.sendToViewer(
                new AttackAct(this.getCurrentCell(), opponent.getCurrentCell(), this, opponent));

        opponent.getDamage(ap);
    }

    public void comboAttack(Unit opponent, ArrayList<Unit> allies) throws UnitHasNotComboException, AttackException {
        int damage = ap;
        for (Unit unit : allies) {
            if (!unit.hasCombo())
                throw new UnitHasNotComboException();
            unit.checkCanAttack(opponent);
            damage += unit.getAp();
        }
        opponent.getDamage(damage);
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

        ClientSender.sendToViewer(new MoveAct(this.getCurrentCell(), newCell, this));

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
            cell.addFlag(flag);

            ClientSender.sendToViewer(new AddFlagAct(cell.getRow(), cell.getColumn()));
        }
        unit.removeFlag();
    }
}
