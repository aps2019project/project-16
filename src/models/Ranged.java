package models;

public class Ranged extends AttackType {
    private int range;

    @Override
    public boolean canAttack(Cell myCell, Cell oppCell) {
        return Table.getDistance(myCell,oppCell) <= range && ! Table.isAdjacent(myCell, oppCell);
    }
}
