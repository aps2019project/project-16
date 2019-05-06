package models.targetsociety;

import models.magic.Buff;
import models.Cell;
import models.Player;

import java.util.ArrayList;

public class UnitsAdjacentToCell extends UnitTargetSociety {
    private boolean containsCenter;

    public UnitsAdjacentToCell(TargetType targetType, TargetTeam targetTeam, TargetAttackType targetAttackType, boolean containsCenter) {
        super(targetType, targetTeam, targetAttackType);
        this.containsCenter = containsCenter;
    }

    @Override
    public boolean canCast(Player player, Cell cell) {
        int[][] cells = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 0}};
        for (int[] coordinate : cells) {
            Cell adjacentCell = cell.getTable().getCell(cell.getRow() + coordinate[0], cell.getColumn() + coordinate[1]);
            if (adjacentCell != null)
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
            if (adjacentCell != null)
                if (adjacentCell.hasUnit() && doesEffect(adjacentCell.getUnit(), player))
                    if (containsCenter || adjacentCell != cell)
                        castOnUnit(player, adjacentCell.getUnit(), buffs);
        }
    }
}
