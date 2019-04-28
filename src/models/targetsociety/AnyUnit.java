package models.targetsociety;

import models.Buff;
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
        for (int i = 0; i < Table.HEIGHT; i++)
            for (int j = 0; j < Table.WIDTH; j++)
                if (table.getCell(i, j).hasUnit() && doesEffect(table.getCell(i,j).getUnit(), player))
                    table.getCell(i, j).getUnit().addBuffs(buffs);
    }
}
