package models;

import models.card.Unit;
import models.item.Item;
import models.magic.Buff;
import models.magic.Buffable;
import newView.BattleView.ClientSender;
import newView.BattleView.gameActs.AddCollectibleAct;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Buffable {
    private int row;
    private int column;
    private ArrayList<Item> collectibles = new ArrayList<>();
    private Unit unit;
    private Table table;
    private ArrayList<Buff> cellEffect = new ArrayList<>();
    private ArrayList<Flag> flags = new ArrayList<>();

    public Cell(Table table, int row, int column) {
        this.table = table;
        this.row = row;
        this.column = column;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public ArrayList<Item> getCollectibles() {
        return collectibles;
    }

    public void addCollectible(Item collectible) {
        collectibles.add(collectible);

        ClientSender.sendToViewer(new AddCollectibleAct(row, column, collectible.getName()));
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
    }

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
            buff.castOnEndTurn(this);
    }

    public ArrayList<Buff> getCellEffect() {
        return cellEffect;
    }

    public ArrayList<Flag> getFlags() {
        return flags;
    }

    public void addFlag(Flag flag) {
        flags.add(flag);
        flag.setCurrentCell(this);
    }

    public void removeFlag() {
        while (flags.size() > 0)
            flags.remove(0);
    }

    public void removeCollectibles() {
        while (collectibles.size() > 0) {
            collectibles.remove(0);
        }
    }
}
