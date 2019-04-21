package models;

import java.util.ArrayList;

public class Cell {
    private int row;
    private int column;
    private ArrayList<Collectible> collectibles = new ArrayList<>();
    private Unit unit;
    private GameMap map;
    private ArrayList<Buff> cellEffect = new ArrayList<>();

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void removeUnit() {
        unit = null;
    }

    public boolean hasUnit() {
        return unit != null;
    }

    public void addBuff(Buff buff) {
        cellEffect.add(buff);
    }

    public void addBuffs(ArrayList<Buff> buffs) {
        cellEffect.addAll(buffs);
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

    public GameMap getMap() {
        return map;
    }
}
