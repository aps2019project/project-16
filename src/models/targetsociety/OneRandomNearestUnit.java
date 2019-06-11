package models.targetsociety;

import models.Cell;
import models.Player;
import models.Table;
import models.magic.Buff;

import java.util.ArrayList;
import java.util.Random;

public class OneRandomNearestUnit extends UnitTargetSociety {

    public OneRandomNearestUnit(TargetType targetType, TargetTeam targetTeam, TargetAttackType targetAttackType) {
        super(targetType, targetTeam, targetAttackType);
    }

    @Override
    public boolean canCast(Player player, Cell cell) {
        Table table = cell.getTable();
        for (int i = 0; i < Table.HEIGHT; i++)
            for (int j = 0; j < Table.WIDTH; j++)
                if (table.getCell(i, j).hasUnit() && doesEffect(table.getCell(i, j).getUnit(), player))
                    return true;
        return false;
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) { // make better random
        Table table = cell.getTable();
        Random random = new Random();
        int randomRow = random.nextInt(Table.HEIGHT);
        int randomColumn = random.nextInt(Table.WIDTH);
        int row = randomRow;
        for (int distance = 1; distance < 50; distance++)
            do {
                int column = randomColumn;
                do {
                    if (Table.getDistance(table.getCell(row, column), cell) == distance &&
                            table.getCell(row, column).hasUnit() &&
                            doesEffect(table.getCell(row, column).getUnit(), player)) {
                        castOnUnit(player, table.getCell(row, column).getUnit(), buffs);
                        return;
                    }
                    column = (column + 1) % Table.WIDTH;
                } while (column != randomColumn);
                row = (row + 1) % Table.HEIGHT;
            } while (row != randomRow);
    }
}
