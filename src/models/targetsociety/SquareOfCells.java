package models.targetsociety;

import models.Buff;
import models.Cell;
import models.Player;

import java.util.ArrayList;

public class SquareOfCells extends TargetSociety{
    private int size;

    public SquareOfCells(int size) {
        this.size = size;
    }

    @Override
    public boolean canCast(Player player, Cell cell) {
        return true;
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        buffs.forEach(buff -> {
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    cell.getMap().getCell(cell.getRow() + i, cell.getColumn() + j).addBuff(buff);
        });

    }
}
