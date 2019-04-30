package models.targetsociety;

import models.Buff;
import models.Cell;
import models.Player;
import models.card.Unit;

import java.util.ArrayList;

public abstract class TargetSociety {
    public abstract boolean canCast(Player player, Cell cell);

    public abstract void cast(Player player, Cell cell, ArrayList<Buff> buffs);

    void castOnUnit(Player player, Unit unit, ArrayList<Buff> buffs) {
        unit.addBuffs(buffs);
        buffs.forEach(buff -> buff.start(unit, player));
    }
}
