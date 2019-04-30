package models;

import models.card.Unit;

public class Buff {
    public static final int INFINITY = Integer.MAX_VALUE;
    private int durationToStart;
    private int remainingDuration;
    private final int duration;
    private int holy;
    private int deltaHP;
    private int deltaAP;
    private int poison;
    private boolean stun;
    private boolean disarm;
    private boolean casted = false;
    private Effect effect;

    public enum Effect {
        POSITIVE,
        NEGATIVE
    }

    private Buff(int duration, int durationToStart, int deltaHP, int deltaAP, int poison, int holy,
                 boolean stun, boolean disarm, Effect effect) {
        this.duration = duration;
        this.remainingDuration = duration;
        this.durationToStart = durationToStart;
        this.deltaHP = deltaHP;
        this.deltaAP = deltaAP;
        this.poison = poison;
        this.holy = holy;
        this.stun = stun;
        this.disarm = disarm;
        this.effect = effect;
    }

    private Buff(int duration, Buff buff) {
        this.duration = duration;
        this.remainingDuration = duration;
        this.durationToStart = buff.durationToStart;
        this.deltaHP = buff.deltaHP;
        this.deltaAP = buff.deltaAP;
        this.poison = buff.poison;
        this.holy = buff.holy;
        this.stun = buff.stun;
        this.disarm = buff.disarm;
    }

    public static class BuffBuilder {
        private static final int INFINITY = Integer.MAX_VALUE;
        private int duration;
        private int durationToStart;
        private int holy;
        private int deltaHP;
        private int deltaAP;
        private int poison;
        private boolean stun;
        private boolean disarm;
        private Effect effect;

        public BuffBuilder setDuration(int duration) {
            this.duration = 2 * duration;
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

        public BuffBuilder setEffect(Effect effect) {
            this.effect = effect;
            return this;
        }

        public Buff create() {
            return new Buff(duration, durationToStart, deltaHP, deltaAP, poison, holy, stun, disarm, effect);
        }
    }

    public void start(Unit unit, Player player) {
        if (durationToStart == 0) {
            unit.changeAP(deltaAP);
            unit.changeHP(deltaHP);
            casted = true;
        }
    }

    public void cast(Unit unit) {
        if (durationToStart > 0) {
            durationToStart--;
            return;
        }
        if (!casted) {
            unit.changeAP(deltaAP);
            unit.changeHP(deltaHP);
        }
        if (remainingDuration % 2 == 0)
            unit.changeHP(-poison);
        if (remainingDuration == 0) {
            unit.changeAP(-deltaAP);
            unit.changeHP(-deltaHP);
        }
        remainingDuration--;
    } //todo delete from buffs??

    public void cast(Cell cell) {
        if (cell.hasUnit()) {
            if (poison == 1)
                cell.getUnit().addBuff(new Buff(3, this));
            if (poison == 2)
                cell.getUnit().changeHP(-poison);
            if (holy != 0)
                cell.getUnit().addBuff(new Buff(1, this));
        }
    }// todo remember to cast on cells first.

    public Effect getEffect() {
        return effect;
    }

    public int getHoly() {
        if (remainingDuration > 0)
            return holy;
        return 0;
    }

    public boolean hasStun() {
        if (remainingDuration > 0)
            return stun;
        return false;
    }

    public boolean hasDisarm() {
        if (remainingDuration > 0)
            return disarm;
        return false;
    }

    private boolean isActive() {
        return remainingDuration > 0 && durationToStart <= 0;
    }
}