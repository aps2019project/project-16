package models;

public abstract class AttackType {
    public abstract boolean canAttack(Cell myCell, Cell oppCell);
}
