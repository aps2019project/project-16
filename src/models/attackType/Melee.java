package models.attackType;

import models.Cell;
import models.Table;

public class Melee extends AttackType {
    @Override
    public boolean canAttack(Cell myCell, Cell oppCell) {
        return Table.isAdjacent(myCell, oppCell);
    }
}
