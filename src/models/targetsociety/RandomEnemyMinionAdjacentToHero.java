package models.targetsociety;

import models.Buff;
import models.Cell;
import models.Player;
import models.Table;

import java.util.ArrayList;
import java.util.Random;

public class RandomEnemyMinionAdjacentToHero extends TargetSociety { // todo must be corrected <Mostafa>
    @Override
    public boolean canCast(Player player, Cell cell) {
        Table table = cell.getTable();
        for (int i = 0; i < Table.HEIGHT; i++)
            for (int j = 0; j < Table.WIDTH; j++)
                if (Table.isAdjacent(cell, table.getCell(i, j)) && table.getCell(i, j).hasUnit()
                        && table.getCell(i, j).getUnit().getPlayer() != player)
                    return true;
        return false;
    }

    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        int[][] cells = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        Random random = new Random();
        for (int i = random.nextInt(cells.length); true; i = (i + 1) % cells.length) {
            Cell adjacentCell = cell.getTable().getCell(cell.getRow() + cells[i][0], cell.getColumn() + cells[i][1]);
            if (adjacentCell.hasUnit() && adjacentCell.getUnit().getPlayer() != player) {
                castOnUnit(player, adjacentCell.getUnit(), buffs);
                return;
            }
        }
    }
}
