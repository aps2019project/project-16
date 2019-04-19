package models;

import java.util.ArrayList;

public class Cell {
    private int row;
    private int column;
    private ArrayList<Collectable> collectables = new ArrayList<>();
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
}
