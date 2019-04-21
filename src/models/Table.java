package models;

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
    }

    public static int getDistance(Cell firstCell, Cell secondCell) {
        return Math.abs(firstCell.getRow() - secondCell.getRow()) + Math.abs(firstCell.getColumn() - secondCell.getColumn());
    }

    public static boolean isAdjacent(Cell firstCell, Cell secondCell) { //todo write it
        return false;
    }
}
