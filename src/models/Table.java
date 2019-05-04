package models;

import models.card.Hero;
import models.card.Unit;

public class Table {
    public static final int WIDTH = 9;
    public static final int HEIGHT = 5;
    private Cell[][] cells = new Cell[HEIGHT][WIDTH];

    public Table() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                cells[i][j] = new Cell(this, i, j);
            }
        }
    }

    public void putUnit(Unit unit, Cell cell) {

    }

    public void moveUnit(Unit unit, Cell cell) {

    }

    public Cell[][] getCells() {
        return cells;
    }


    public Cell getCell(int row, int column) {
        if (row >= HEIGHT || row < 0 || column >= WIDTH || column < 0) {
            return null;
        }
        return cells[row][column];
    }

    public static int getDistance(Cell firstCell, Cell secondCell) {
        return Math.abs(firstCell.getRow() - secondCell.getRow()) + Math.abs(firstCell.getColumn() - secondCell.getColumn());
    }

    public boolean checkPathIsBlocked(Cell firstCell, Cell secondCell) {
        if (Table.getDistance(firstCell, secondCell) == 2) {
            if (firstCell.getRow() == secondCell.getRow())
                if (this.getCell(firstCell.getRow(), (firstCell.getColumn() + secondCell.getColumn()) / 2).hasUnit())
                    return true;
            if (firstCell.getColumn() == secondCell.getColumn())
                if (this.getCell((firstCell.getRow() + secondCell.getRow()) / 2, firstCell.getColumn()).hasUnit())
                    return true;
            if (this.getCell(firstCell.getRow(), secondCell.getColumn()).hasUnit() &&
                    this.getCell(secondCell.getRow(), firstCell.getColumn()).hasUnit())
                return true;
        }
        return false;
    }

    public static boolean isAdjacent(Cell firstCell, Cell secondCell) {
        return (firstCell.getColumn() == secondCell.getColumn()
                && Math.abs(firstCell.getRow() - secondCell.getRow()) == 1)
                || (firstCell.getRow() == secondCell.getRow()
                && Math.abs(firstCell.getColumn() - secondCell.getColumn()) == 1);
    }
}
