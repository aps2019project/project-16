package models;

import models.card.Unit;

public class Table {
    public static final int WIDTH = 9;
    public static final int HEIGHT = 5;
    private Cell[][] cells = new Cell[HEIGHT][WIDTH];

    public void putUnit(Unit unit, Cell cell) {

    }

    public void moveUnit(Unit unit, Cell cell) {

    }

    public Cell[][] getCells() {
        return cells;
    }


    public Cell getCell(int row, int column) {
        return cells[row][column];
        //todo shouldn't be row-1 and column -1
    }

    public static int getDistance(Cell firstCell, Cell secondCell) {//todo why should be static ?
        return Math.abs(firstCell.getRow() - secondCell.getRow()) + Math.abs(firstCell.getColumn() - secondCell.getColumn());
    }

    public static boolean checkDistance(int distance, Cell firstCell, Cell secondCell) {//todo why should be static ?
        if (getDistance(firstCell, secondCell) > distance)
            return false;
        return true;
    }

    public boolean checkPathIsBlocked(Cell firstCell, Cell secondCell) {
        //we know that in this case distance is 2
        if (this.getCell(firstCell.getRow(), secondCell.getColumn()).hasUnit() &&
                this.getCell(secondCell.getRow(), firstCell.getColumn()).hasUnit())
            return false;
        return true;
    }

    public static boolean isAdjacent(Cell firstCell, Cell secondCell) {
        return (firstCell.getColumn() == secondCell.getColumn()
                && Math.abs(firstCell.getRow() - secondCell.getRow()) == 1)
                || (firstCell.getRow() == secondCell.getRow()
                && Math.abs(firstCell.getColumn() - secondCell.getColumn()) == 1);
    }
}
