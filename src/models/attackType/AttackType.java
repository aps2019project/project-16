package models.attackType;

import models.Cell;

public abstract class AttackType {
    public abstract boolean canAttack(Cell myCell, Cell oppCell);
}
