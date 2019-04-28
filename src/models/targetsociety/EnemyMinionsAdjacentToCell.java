package models.targetsociety;

import models.Buff;
import models.Cell;
import models.Player;
import models.Table;

import java.util.ArrayList;
import java.util.Random;

public class EnemyMinionsAdjacentToCell extends TargetSociety {
    @Override
    public boolean canCast(Player player, Cell cell) {
        int[][] cells = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        for (int[] coordinate : cells) {
            Cell adjacentCell = cell.getTable().getCell(cell.getRow() + coordinate[0], cell.getColumn() + coordinate[1]);
            if (adjacentCell.hasUnit() && adjacentCell.getUnit().getPlayer() != player)
                return true;
        }
        return false;
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        int[][] cells = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        for (int[] coordinate : cells) {
            Cell adjacentCell = cell.getTable().getCell(cell.getRow() + coordinate[0], cell.getColumn() + coordinate[1]);
            if (adjacentCell.hasUnit() && adjacentCell.getUnit().getPlayer() != player)
                adjacentCell.getUnit().addBuffs(buffs);
        }
    }
}
