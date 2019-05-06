package models.magic;

import models.Cell;
import models.Player;
import models.card.Unit;

public class Buff {
    public static final int INFINITY = 1000000;
    private int durationToStart;
    private int remainingDuration;
    private final int duration;
    private int damage;
    private int holy;
    private int deltaHP;
    private int deltaAP;
    private int poison;
    private boolean stun;
    private boolean disarm;
    private boolean dispel;
    private boolean casted = false;
    private boolean dispelable;

    private Buff(int duration, int durationToStart, int damage, int deltaHP, int deltaAP, int poison, int holy,
                 boolean stun, boolean disarm, boolean dispel, boolean dispelable) {
        this.duration = duration;
        this.remainingDuration = duration;
        this.durationToStart = durationToStart;
        this.damage = damage;
        this.deltaHP = deltaHP;
        this.deltaAP = deltaAP;
        this.poison = poison;
        this.holy = holy;
        this.stun = stun;
        this.disarm = disarm;
        this.dispel = dispel;
        this.dispelable = dispelable;
    }

    private Buff(int duration, Buff buff) {
        this.duration = duration;
        this.remainingDuration = duration;
        this.durationToStart = buff.durationToStart;
        this.damage = buff.damage;
        this.deltaHP = buff.deltaHP;
        this.deltaAP = buff.deltaAP;
        this.poison = buff.poison;
        this.holy = buff.holy;
        this.stun = buff.stun;
        this.disarm = buff.disarm;
        this.dispel = buff.dispel;
        this.dispelable = buff.dispelable;
    }

    public static class BuffBuilder {
        private int duration;
        private int durationToStart;
        private int holy;
        private int damage;
        private int deltaHP;
        private int deltaAP;
        private int poison;
        private boolean stun;
        private boolean disarm;
        private boolean dispel;
        private boolean dispelable = true;

        public BuffBuilder setDuration(int duration) {
            this.duration = 2 * duration;
            return this;
        }

        public BuffBuilder setHalfTurnDuration() {
            this.duration = 0;
            return this;
        }

        public BuffBuilder setDurationToStart(int durationToStart) {
            this.durationToStart = 2 * durationToStart;
            return this;
        }

        public BuffBuilder setHoly(int holy) {
            this.holy = holy;
            return this;
        }

        public BuffBuilder setDamage(int damage) {
            this.damage = damage;
            return this;
        }

        public BuffBuilder setDeltaHP(int deltaHP) {
            this.deltaHP = deltaHP;
            return this;
        }

        public BuffBuilder setDeltaAP(int deltaAP) {
            this.deltaAP = deltaAP;
            return this;
        }

        public BuffBuilder setPoison(int poison) {
            this.poison = poison;
            return this;
        }

        public BuffBuilder setStun() {
            this.stun = true;
            return this;
        }

        public BuffBuilder setDisarm() {
            this.disarm = true;
            return this;
        }

        public BuffBuilder setDispel() {
            this.dispel = true;
            return this;
        }

        public BuffBuilder setNotDispelable() {
            this.dispelable = false;
            return this;
        }

        public Buff create() {
            return new Buff(duration, durationToStart, damage, deltaHP, deltaAP, poison, holy, stun, disarm, dispel, dispelable);
        }
    }

    /**
     * disarm casted on first turn even if duration to start is not zero.
     *
     * @param unit
     * @param player
     */
    public void start(Unit unit, Player player) {
        if (unit.isNotGetNegativeEffect() && !isPositive())
            return;
        if (durationToStart == 0) {
            cast(unit);
        }
        if (dispel) {
            dispel(unit, player);
        }
    }

    private void dispel(Unit unit, Player player) {
        if (unit.getPlayer() != player) {
            unit.getBuffs().forEach(buff -> {
                if (buff.isPositive() && buff.isDispelable())
                    buff.finish(unit);
            });
            unit.getBuffs().removeIf(buff -> buff.isDispelable() && buff.isPositive());
        } else {
            unit.getBuffs().forEach(buff -> {
                if (!buff.isPositive() && buff.isDispelable())
                    buff.finish(unit);
            });
            unit.getBuffs().removeIf(buff -> !buff.isPositive() && buff.isDispelable());
        }
    }

    private void cast(Unit unit) {
        if (!casted) {
            unit.getDamage(damage);
            unit.changeAP(deltaAP);
            unit.changeHP(deltaHP);
        }
        casted = true;
    }

    public boolean isDispelable() {
        return dispelable;
    }

    public boolean isPositive() {
        return deltaAP > 0 || deltaHP > 0 || holy > 0;
    }

    public void castOnEndTurn(Unit unit) {
        if (durationToStart > 0) {
            durationToStart--;
            return;
        }
        if (!casted) {
            cast(unit);
        }
        if (remainingDuration % 2 == 0 && remainingDuration > 0)
            unit.changeHP(-poison);
        if (remainingDuration == 0) {
            finish(unit);
        }
        remainingDuration--;
    } //todo delete buff from buffs??

    private void finish(Unit unit) {
        unit.changeAP(-deltaAP);
        unit.changeHP(-deltaHP);
    }

    /**
     * it should be casted before unit buffs casting.
     *
     * @param cell
     */
    public void castOnEndTurn(Cell cell) {
        if (cell.hasUnit()) {
            if (poison == 1)
                cell.getUnit().addBuff(new Buff(3, this));
            if (poison == 2)
                cell.getUnit().changeHP(-poison);
            if (holy != 0)
                cell.getUnit().addBuff(new Buff(1, this));
        }
    }

    public int getHoly() {
        if (isActive())
            return holy;
        return 0;
    }

    public boolean hasStun() {
        if (isActive())
            return stun;
        return false;
    }

    public boolean hasDisarm() {
        if (isActive())
            return disarm;
        return false;
    }

    public boolean hasPoison() {
        return poison > 0;
    }

    public Buff copy() {
        return new Buff(duration, this);
    }

    private boolean isActive() {
        return remainingDuration > 0 && durationToStart <= 0;
    }
}