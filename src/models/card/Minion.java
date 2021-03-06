package models.card;

import models.attackType.AttackType;
import models.SpecialPowerCastTime;
import models.magic.Spell;

public class Minion extends Unit {

    private Minion(String name, int manaCost, int buyPrice, int sellPrice, String description, int hp, int ap,
                   AttackType attackType, boolean combo, Spell specialPower, SpecialPowerCastTime specialPowerCastTime,
                   int addedApPerAttack, boolean piercingHoly, boolean notDisarmable, boolean notGetWeakerAttack,
                   boolean notGetNegativeEffect, boolean notGetPoisoned) {
        super(name, manaCost, buyPrice, sellPrice, description, hp, ap, attackType, combo, specialPower,
                specialPowerCastTime, addedApPerAttack, piercingHoly, notDisarmable, notGetWeakerAttack,
                notGetNegativeEffect, notGetPoisoned);
    }

    public static class MinionBuilder extends UnitBuilder {
        private boolean combo;

        public MinionBuilder setCombo() {
            this.combo = true;
            return this;
        }

        @Override
        public Minion create() {
            return new Minion(getName(), getManaCost(), getBuyPrice(), getSellPrice(), getDescription(), getHp(),
                    getAp(), getAttackType(), combo, getSpecialPower(), getSpecialPowerCastTime(), getAddedApPerAttack(),
                    isPiercingHoly(), isNotDisarmable(), isNotGetWeakerAttack(), isNotGetNegativeEffect(), isNotGetPoisoned());
        }
    }

    public String getComboDescription() {
        // TODO: 5/3/19
        return null;
    }

}
