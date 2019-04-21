package models.targetsociety;

import models.Buff;
import models.Cell;
import models.Hero;
import models.Player;

import java.util.ArrayList;

public class EnemyHero extends TargetSociety {

    @Override
    public boolean canCast(Player player, Cell cell) {
        return cell.hasUnit()
                && cell.getUnit().getPlayer() != player
                && cell.getUnit() instanceof Hero;
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        cell.getUnit().addBuffs(buffs);
    }
}
