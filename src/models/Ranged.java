package models;

public class Ranged extends AttackType {
    private int range;

    @Override
    public boolean canAttack(Cell myCell, Cell oppCell) {
        return GameMap.getDistance(myCell,oppCell) <= range && ! GameMap.isAdjacent(myCell, oppCell);
    }
}
