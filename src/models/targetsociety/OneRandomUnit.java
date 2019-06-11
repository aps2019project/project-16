package models.targetsociety;

import models.magic.Buff;
import models.Cell;
import models.Player;
import models.Table;

import java.util.ArrayList;
import java.util.Random;

public class OneRandomUnit extends UnitTargetSociety {
    public OneRandomUnit(TargetType targetType, TargetTeam targetTeam, TargetAttackType targetAttackType) {
        super(targetType, targetTeam, targetAttackType);
    }

    @Override
    public boolean canCast(Player player, Cell cell) {
        Table table = cell.getTable();
        for (int row = 0; row < Table.HEIGHT; row++)
            for (int column = 0; column < Table.WIDTH; column++)
                if (table.getCell(row, column).hasUnit() && doesEffect(table.getCell(row, column).getUnit(), player))
                    return true;
        return false;
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        Table table = cell.getTable();
        Random random = new Random();
        int randomRow = random.nextInt(Table.HEIGHT);
        int randomColumn = random.nextInt(Table.WIDTH);
        int row = randomRow;
        do {
            int column = randomColumn;
            do {
                if (table.getCell(row, column).hasUnit() && doesEffect(table.getCell(row, column).getUnit(), player)) {
                    castOnUnit(player, table.getCell(row, column).getUnit(), buffs);
                    return;
                }
                column = (column + 1) % Table.WIDTH;
            } while (column != randomColumn);
            row = (row + 1) % Table.HEIGHT;
        } while (row != randomRow);
    }
}
