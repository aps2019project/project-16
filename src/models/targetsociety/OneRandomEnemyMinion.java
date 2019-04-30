package models.targetsociety;

import models.Buff;
import models.Cell;
import models.Player;
import models.Table;

import java.util.ArrayList;
import java.util.Random;

public class OneRandomEnemyMinion extends TargetSociety {
    @Override
    public boolean canCast(Player player, Cell cell) {
        Table table = cell.getTable();
        for (int i = 0; i < Table.HEIGHT; i++)
            for (int j = 0; j < Table.WIDTH; j++)
                if (table.getCell(i, j).hasUnit() && table.getCell(i, j).getUnit().getPlayer() != player)
                    return true;
        return false;
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        if (!canCast(player, cell))
            return;
        Table table = cell.getTable();
        Random random = new Random();
        int randomRow = random.nextInt(Table.HEIGHT);
        int randomColumn = random.nextInt(Table.WIDTH);
        int row = randomRow;
        do {
            int column = randomColumn;
            do {
                if (table.getCell(row, column).hasUnit() && table.getCell(row, column).getUnit().getPlayer() != player) {
                    castOnUnit(player, table.getCell(row, column).getUnit(), buffs);
                    return;
                }
                column = (column + 1) % Table.WIDTH;
            } while (column != randomColumn);
            row = (row + 1) % Table.HEIGHT;
        } while(row != randomRow);
    }
}
