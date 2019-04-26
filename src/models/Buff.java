package models;

import models.card.Unit;

public class Buff {
    public static final int INFINITY = Integer.MAX_VALUE;
    private int remainingDuration;
    private final int duration;
    private boolean holy;
    private int deltaHP;
    private int deltaAP;
    private int poison;
    private boolean stun;
    private boolean disarm;
    private Effect effect;

    public enum Effect {
        POSITIVE,
        NEGATIVE
    }

    private Buff(int duration, int deltaHP, int deltaAP, int poison, boolean holy,
                 boolean stun, boolean disarm, Effect effect) {
        this.duration = duration;
        this.remainingDuration = duration;
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
        private boolean holy;
        private int deltaHP;
        private int deltaAP;
        private int poison;
        private boolean stun;
        private boolean disarm;
        private Effect effect;

        public BuffBuilder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public BuffBuilder setHoly() {
            this.holy = true;
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

        public Buff build() {
            return new Buff(duration, deltaHP, deltaAP, poison, holy, stun, disarm, effect);
        }
    }

    public void cast(Unit unit) {
        if (remainingDuration == duration) {
            unit.changeAP(deltaAP);
            unit.changeHP(deltaHP);
        }
        unit.changeHP(-poison);
        remainingDuration--;
    }

    public void cast(Cell cell) { //todo change it (maybe cell should have spell)
        if (cell.hasUnit())
            cell.getUnit().changeHP(-poison);
    }

    public Effect getEffect() {
        return effect;
    }

    public boolean hasHoly() {
        return holy;
    }

    public boolean hasStun() {
        return stun;
    }

    public boolean hasDisarm() {
        return disarm;
    }
}