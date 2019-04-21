package models;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Cell implements Buffable {
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

    @Override
    public void addBuff(Buff buff) {
        cellEffect.add(buff);
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
