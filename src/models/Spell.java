package models;

public class Spell {
    private Buff buff;
    private TargetSociety targetSociety;

    public boolean canCast(Player player, Cell cell) {
        return targetSociety.canCast(player, cell);
    }

    public void cast(Player player, Cell cell) {
        targetSociety.cast(player, cell, buff);
    }
}
