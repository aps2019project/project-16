package models;

public abstract class TargetSociety {
    public abstract boolean canCast(Player player, Cell cell);
    public abstract void cast(Player player, Cell cell, Buff buff);
}
