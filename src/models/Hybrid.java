package models;

public class Hybrid extends AttackType {
    private int range;

    @Override
    public boolean canAttack(Cell myCell, Cell oppCell) {
        return GameMap.getDistance(myCell,oppCell) <= range;
    }
}
