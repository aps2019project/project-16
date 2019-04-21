package models;

public class Melee extends AttackType {
    @Override
    public boolean canAttack(Cell myCell, Cell oppCell) {
        return Table.isAdjacent(myCell, oppCell);
    }
}
