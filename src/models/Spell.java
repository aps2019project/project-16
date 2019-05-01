package models;

import models.targetsociety.TargetSociety;

import java.util.ArrayList;
import java.util.Arrays;

public class Spell {
    private ArrayList<Buff> buffs;
    private TargetSociety targetSociety;

    public Spell(TargetSociety targetSociety, Buff... buffs) { // todo remove dispel
        this.buffs = new ArrayList<>(Arrays.asList(buffs));
        this.targetSociety = targetSociety;
    }

    public Spell(TargetSociety targetSociety, ArrayList<Buff> buffs) {
        this.buffs = buffs;
        this.targetSociety = targetSociety;
    }

    public static class SpellBuilder {
        private ArrayList<Buff> buffs = new ArrayList<>();
        private TargetSociety targetSociety;

        public SpellBuilder setTargetSociety(TargetSociety targetSociety) {
            this.targetSociety = targetSociety;
            return this;
        }

        public SpellBuilder addBuff(Buff buff) {
            buffs.add(buff);
            return this;
        }

        public Spell create() {
            return new Spell(targetSociety, buffs);
        }
    }

    public boolean canCast(Player player, Cell cell) {
        return targetSociety.canCast(player, cell);
    }

    public void cast(Player player, Cell cell) {
        targetSociety.cast(player, cell, buffs);
    }
}
