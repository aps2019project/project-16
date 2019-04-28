package models.targetsociety;

import models.Buff;
import models.Cell;
import models.Player;
import models.Table;

import java.util.ArrayList;

public class UnitsInRange extends UnitTargetSociety {
    private int range;

    public UnitsInRange(TargetType targetType, TargetTeam targetTeam, int range) {
        super(targetType, targetTeam);
        this.range = range;
    }

    @Override
    public boolean canCast(Player player, Cell cell) {
        return false;
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        Table table = cell.getTable();
        for (int row = 0; row < Table.HEIGHT; row++)
            for (int column = 0; column < Table.HEIGHT; column++)
                if (Table.getDistance(table.getCell(row, column), cell) <= range && cell.hasUnit())
                    cell.getUnit().addBuffs(buffs);
    }
}
