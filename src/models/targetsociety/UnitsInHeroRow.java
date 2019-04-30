package models.targetsociety;

import models.Buff;
import models.Cell;
import models.Player;
import models.Table;

import java.util.ArrayList;

public class UnitsInHeroRow extends TargetSociety {
    @Override
    public boolean canCast(Player player, Cell cell) {
        Table table = cell.getTable();
        for (int column = 0; column < Table.WIDTH; column++)
            if (column != cell.getColumn())
                if (table.getCell(cell.getRow(), column).hasUnit())
                    return true;
        return false;
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        Table table = cell.getTable();
        for (int column = 0; column < Table.WIDTH; column++)
            if (column != cell.getColumn())
                if (table.getCell(cell.getRow(), column).hasUnit())
                    castOnUnit(player, table.getCell(cell.getRow(), column).getUnit(), buffs);
    }
}
