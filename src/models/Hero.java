package models;

public class Hero extends Unit {
    private Spell spell;
    private int spellManaCost;
    private int spellRemainingCooldown;
    private int spellCooldown;
    public Hero(int manaCost, int buyPrice, int sellPrice, int hp, int ap) { //todo correct
        super(manaCost, buyPrice, sellPrice, hp, ap);
    }

    public void castSpell(Cell cell) {

    }
}
