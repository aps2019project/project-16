package models.targetsociety;

import models.magic.Buff;
import models.Cell;
import models.Player;
import models.Table;

import java.util.ArrayList;

public class AnyUnit extends  UnitTargetSociety {
    public AnyUnit(TargetType targetType, TargetTeam targetTeam) {
        super(targetType, targetTeam);
    }

    @Override
    public boolean canCast(Player player, Cell cell) {
        Table table = cell.getTable();
        for (int i = 0; i < Table.HEIGHT; i++)
            for (int j = 0; j < Table.WIDTH; j++)
                if (table.getCell(i, j).hasUnit() && doesEffect(table.getCell(i,j).getUnit(), player))
                    return true;
        return false;
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        Table table = cell.getTable();
        for (int row = 0; row < Table.HEIGHT; row++)
            for (int column = 0; column < Table.WIDTH; column++)
                if (table.getCell(row, column).hasUnit() && doesEffect(table.getCell(row,column).getUnit(), player))
                    castOnUnit(player, table.getCell(row, column).getUnit(), buffs);
    }
}
