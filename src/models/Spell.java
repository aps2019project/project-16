package models;

import models.card.Unit;
import models.targetsociety.TargetSociety;

import java.util.ArrayList;
import java.util.Arrays;

public class Spell {
    private ArrayList<Buff> buffs;
    private TargetSociety targetSociety;
    private boolean dispel;

    public Spell(TargetSociety targetSociety, boolean dispel, Buff... buffs) {
        this.buffs = new ArrayList<>(Arrays.asList(buffs));
        this.targetSociety = targetSociety;
        this.dispel = dispel;
    }

    public Spell(TargetSociety targetSociety, boolean dispel, ArrayList<Buff> buffs) {
        this.buffs = buffs;
        this.targetSociety = targetSociety;
        this.dispel = dispel;
    }

    public static class SpellBuilder {
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
        if (dispel)
            dispel(player, cell);
        targetSociety.cast(player, cell, buffs);
    }

    private void dispel(Player player, Cell cell) { // todo make it correct for different target societies.
        Table table = cell.getTable();
        for (int i = 0; i < Table.HEIGHT; i++)
            for (int j = 0; j < Table.WIDTH; j++)
                if (canCast(player, cell.getTable().getCell(i, j)) && table.getCell(i, j).hasUnit()) {
                    Unit unit = table.getCell(i, j).getUnit();
                    if (unit.getPlayer() == player)
                        unit.getBuffs().removeIf(buff -> buff.getEffect() == Buff.Effect.NEGATIVE);
                    else
                        unit.getBuffs().removeIf(buff -> buff.getEffect() == Buff.Effect.POSITIVE);
                }
    }
}
