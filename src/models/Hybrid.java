package models;

public class Hybrid extends AttackType {
    private int range;

    public Hybrid(int range) {
        this.range = range;
    }

    @Override
    public boolean canAttack(Cell myCell, Cell oppCell) {
        return Table.getDistance(myCell,oppCell) <= range;
    }
}
