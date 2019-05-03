package models;

import models.card.Unit;

public class Flag extends Item{
    private Cell currentCell;
    private Unit ownerUnit;

    public Cell getCurrentCell() {
        return currentCell;
    }

    public Unit getOwnerUnit() {
        return ownerUnit;
    }

    @Override
    public void use() {

    }
}
