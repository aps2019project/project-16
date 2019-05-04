package models;

import models.card.Unit;

public class Flag {
    private Cell currentCell;
    private Unit ownerUnit;

    Flag(Cell cell) {
        this.currentCell = cell;
        this.ownerUnit = null;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public Unit getOwnerUnit() {
        return ownerUnit;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public void setOwnerUnit(Unit ownerUnit) {
        this.ownerUnit = ownerUnit;
    }
}
