package models;

import models.card.Unit;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Buffable {
    private int row;
    private int column;
    private ArrayList<Collectible> collectibles = new ArrayList<>();
    private Unit unit;
    private Table table;
    private ArrayList<Buff> cellEffect = new ArrayList<>();

    public Cell(Table table, int row, int column) {
        this.table = table;
        this.row = row;
        this.column = column;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Unit getUnit() {
        return unit;
    }

    public Table getTable() {
        return this.table;
    }

    public void removeUnit() {
        unit = null;
    }

    public boolean hasUnit() {
        return unit != null;
    } //todo maybe it should moved to controller (sepehr)

    @Override
    public void addBuff(Buff buff) {
        cellEffect.add(buff.copy());
    }

    @Override
    public void addBuffs(List<Buff> buffs) {
        buffs.forEach(this::addBuff);
    }

    @Override
    public void doBuffs() {
        for (Buff buff : cellEffect)
            buff.cast(this);
    }
}
