package models;

public class Ranged extends AttackType {
    private int range;

    public Ranged(int range) {
        this.range = range;
    }

    @Override
    public boolean canAttack(Cell myCell, Cell oppCell) {
        return Table.getDistance(myCell, oppCell) <= range && !Table.isAdjacent(myCell, oppCell);
    }
}
