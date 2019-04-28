package models.card;

import models.AttackType;
import models.Cell;
import models.SpecialPowerCastTime;
import models.Spell;

public class Minion extends Unit {
    private Spell specialPower;
    private SpecialPowerCastTime specialPowerCastTime;

    public Minion(String name, int manaCost, int buyPrice, int sellPrice, String description, int hp, int ap, AttackType attackType,
                  boolean combo, Spell specialPower, SpecialPowerCastTime specialPowerCastTime) {
        super(name, manaCost, buyPrice, sellPrice, description, hp, ap, attackType, combo);
        this.specialPower = specialPower;
        this.specialPowerCastTime = specialPowerCastTime;
    }

    public static class MinionBuilder extends UnitBuilder {
        private Spell specialPower;
        private SpecialPowerCastTime specialPowerCastTime;
        private boolean combo;

        public MinionBuilder setSpecialPower(Spell specialPower) {
            this.specialPower = specialPower;
            return this;
        }

        public MinionBuilder setSpecialPowerCastTime(SpecialPowerCastTime specialPowerCastTime) {
            this.specialPowerCastTime = specialPowerCastTime;
            return this;
        }

        public MinionBuilder setCombo() {
            this.combo = true;
            return this;
        }

        public Minion create() {
            return new Minion(getName(), getManaCost(), getBuyPrice(), getSellPrice(), getDescription(), getHp(),
                    getAp(), getAttackType(), combo, specialPower, specialPowerCastTime);
        }
    }

    public Spell getSpecialPower() {
        return specialPower;
    }

    public void castSpecialPower(SpecialPowerCastTime time, Cell cell) {
        if (time == specialPowerCastTime)
            specialPower.cast(getPlayer(), cell);
    }
}
