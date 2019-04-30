package models.targetsociety;

import models.Buff;
import models.Cell;
import models.Player;
import models.Table;

import java.util.ArrayList;

public class UnitsInRange extends UnitTargetSociety {
    private int range;
    private boolean containsCenter;

    public UnitsInRange(TargetType targetType, TargetTeam targetTeam, int range, boolean containsCenter) {
        super(targetType, targetTeam);
        this.range = range;
        this.containsCenter = containsCenter;
    }

    @Override
    public boolean canCast(Player player, Cell cell) {
        Table table = cell.getTable();
        for (int row = 0; row < Table.HEIGHT; row++)
            for (int column = 0; column < Table.HEIGHT; column++)
                if (Table.getDistance(table.getCell(row, column), cell) <= range && table.getCell(row, column).hasUnit())
                    if (containsCenter || table.getCell(row, column) != cell)
                        if (doesEffect(table.getCell(row, column).getUnit(), player))
                            return true;
        return false;
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        Table table = cell.getTable();
        for (int row = 0; row < Table.HEIGHT; row++)
            for (int column = 0; column < Table.HEIGHT; column++)
                if (Table.getDistance(table.getCell(row, column), cell) <= range && table.getCell(row, column).hasUnit())
                    if (containsCenter || table.getCell(row, column) != cell)
                        if (doesEffect(table.getCell(row, column).getUnit(), player))
                            castOnUnit(player, table.getCell(row, column).getUnit(), buffs);
    }
}
