package models;

import models.card.Unit;
import models.targetsociety.TargetSociety;

import java.util.ArrayList;
import java.util.Arrays;

public class Spell {
    private ArrayList<Buff> buffs;
    private TargetSociety targetSociety;

    public Spell(TargetSociety targetSociety, boolean dispel, Buff... buffs) { // todo remove dispel
        this.buffs = new ArrayList<>(Arrays.asList(buffs));
        this.targetSociety = targetSociety;
    }

    public Spell(TargetSociety targetSociety, boolean dispel, ArrayList<Buff> buffs) {
        this.buffs = buffs;
        this.targetSociety = targetSociety;
    }

    public static class SpellBuilder { // todo remove setDispel
        private ArrayList<Buff> buffs = new ArrayList<>();
        private TargetSociety targetSociety;
        private boolean dispel;

        public SpellBuilder setTargetSociety(TargetSociety targetSociety) {
            this.targetSociety = targetSociety;
            return this;
        }

        public SpellBuilder setDispel() {
            this.dispel = true;
            return this;
        }

        public SpellBuilder addBuff(Buff buff) {
            buffs.add(buff);
            return this;
        }

        public Spell create() {
            return new Spell(targetSociety, dispel, buffs);
        }
    }

    public boolean canCast(Player player, Cell cell) {
        return targetSociety.canCast(player, cell);
    }

    public void cast(Player player, Cell cell) {
        targetSociety.cast(player, cell, buffs);
    }
}
