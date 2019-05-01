package models.card;

import models.AttackType;
import models.Cell;
import models.SpecialPowerCastTime;
import models.Spell;

public class Hero extends Unit {
    private Spell spell;
    private int spellManaCost;
    private int spellRemainingCoolDown = 0;
    private int spellCoolDown;

    public Hero(String name, int manaCost, int buyPrice, int sellPrice, String description, int hp, int ap, AttackType attackType,
                Spell specialPower, SpecialPowerCastTime specialPowerCastTime, Spell spell, int spellManaCost, int spellCoolDown) {
        super(name, manaCost, buyPrice, sellPrice, description, hp, ap, attackType, false, specialPower, specialPowerCastTime);
        this.spell = spell;
        this.spellManaCost = spellManaCost;
        this.spellCoolDown = spellCoolDown;
    }

    public static class HeroBuilder extends UnitBuilder {
        private Spell spell;
        private int spellManaCost;
        private int spellRemainingCoolDown = 0;
        private int spellCoolDown;

        public HeroBuilder setSpell(Spell spell) {
            this.spell = spell;
            return this;
        }

        public HeroBuilder setSpellManaCost(int spellManaCost) {
            this.spellManaCost = spellManaCost;
            return this;
        }

        public HeroBuilder setSpellRemainingCoolDown(int spellRemainingCoolDown) {
            this.spellRemainingCoolDown = spellRemainingCoolDown;
            return this;
        }

        public HeroBuilder setSpellCoolDown(int spellCoolDown) {
            this.spellCoolDown = spellCoolDown;
            return this;
        }

        @Override
        public Hero create() {
            return new Hero(getName(), getManaCost(), getBuyPrice(), getSellPrice(), getDescription(), getHp(), getAp(), getAttackType(),
                    getSpecialPower(), getSpecialPowerCastTime(), spell, spellManaCost, spellCoolDown);
        }
    }

    public Spell getSpell() {
        return spell;
    }

    public boolean canCastSpell(Cell cell) {
        return spell.canCast(getPlayer(), cell);
    }

    public void castSpell(Cell cell) {
        spell.canCast(getPlayer(), cell);
    }
}
