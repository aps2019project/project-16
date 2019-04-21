package models.targetsociety;

import models.Buff;
import models.Cell;
import models.Player;

import java.util.ArrayList;

public class OneEnemy extends TargetSociety {

    @Override
    public boolean canCast(Player player, Cell cell) {
        return cell.hasUnit() && cell.getUnit().getPlayer() != player;
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        buffs.forEach(cell.getUnit()::addBuff);
    }
}
