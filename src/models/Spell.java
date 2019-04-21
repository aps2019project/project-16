package models;

import models.targetsociety.TargetSociety;

import java.util.ArrayList;
import java.util.Arrays;

public class Spell {
    private ArrayList<Buff> buffs;
    private TargetSociety targetSociety;

    public Spell(TargetSociety targetSociety, Buff... buffs) {
        this.buffs = new ArrayList<>(Arrays.asList(buffs));
        this.targetSociety = targetSociety;
    }

    public boolean canCast(Player player, Cell cell) {
        return targetSociety.canCast(player, cell);
    }

    public void cast(Player player, Cell cell) {
        targetSociety.cast(player, cell, buffs);
    }
}
