package models.targetsociety;

import models.Buff;
import models.Cell;
import models.Player;

import java.util.ArrayList;

public class UnitsAdjacentToCell extends UnitTargetSociety {
    private boolean containsCenter;

    public UnitsAdjacentToCell(TargetType targetType, TargetTeam targetTeam, boolean containsCenter) {
        super(targetType, targetTeam);
        this.containsCenter = containsCenter;
    }

    @Override
    public boolean canCast(Player player, Cell cell) {
        int[][] cells = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 0}};
        for (int[] coordinate : cells) {
            Cell adjacentCell = cell.getTable().getCell(cell.getRow() + coordinate[0], cell.getColumn() + coordinate[1]);
            if (adjacentCell.hasUnit() && doesEffect(adjacentCell.getUnit(), player))
                if (containsCenter || adjacentCell != cell)
                    return true;
        }
        return false;
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        int[][] cells = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 0}};
        for (int[] coordinate : cells) {
            Cell adjacentCell = cell.getTable().getCell(cell.getRow() + coordinate[0], cell.getColumn() + coordinate[1]);
            if (adjacentCell.hasUnit() && doesEffect(adjacentCell.getUnit(), player))
                if (containsCenter || adjacentCell != cell)
                    adjacentCell.getUnit().addBuffs(buffs);
        }
    }
}
