package models.targetsociety;

import models.Buff;
import models.Cell;
import models.Table;
import models.Player;

import java.util.ArrayList;

public class AllEnemyUnits extends TargetSociety {
    @Override
    public boolean canCast(Player player, Cell cell) {
        return true;
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        for (int i = 0; i < Table.HEIGHT; i++)
            for (int j = 0; j < Table.WIDTH; j++)
                if (hasEnemy(player, cell.getTable().getCell(i, j)))
                    cell.getTable().getCell(i, j).addBuffs(buffs);
    }

    private boolean hasEnemy(Player player, Cell cell) {
        return cell.hasUnit() && cell.getUnit().getPlayer() != player;
    }
}
