package models.targetsociety;

import models.Buff;
import models.Cell;
import models.Player;
import models.Table;

import java.util.ArrayList;

public class EnemiesInColumn extends TargetSociety {
    @Override
    public boolean canCast(Player player, Cell cell) {
        for (int column = 0; column < Table.HEIGHT; column++) {
            Cell cellInComlumn = cell.getTable().getCell(cell.getRow(), column);
            if (cellInComlumn.hasUnit() && cellInComlumn.getUnit().getPlayer() != player)
                return true;
        }
        return false;
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        for (int column = 0; column < Table.HEIGHT; column++) {
            Cell cellInColumn = cell.getTable().getCell(cell.getRow(), column);
            if (cellInColumn.hasUnit() && cellInColumn.getUnit().getPlayer() != player)
                castOnUnit(player, cellInColumn.getUnit(), buffs);
        }
    }
}
