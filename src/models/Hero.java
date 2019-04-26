package models;

public class Hero extends Unit {
    private Spell spell;
    private int spellManaCost;
    private int spellRemainingCoolDown = 0;
    private int spellCoolDown;
    public Hero(String name, int manaCost, int buyPrice, int sellPrice, int hp, int ap, AttackType attackType,
                Spell spell, int spellManaCost, int spellCoolDown) {
        super(name, manaCost, buyPrice, sellPrice, hp, ap, attackType);
        this.spell = spell;
        this.spellManaCost = spellManaCost;
        this.spellCoolDown = spellCoolDown;
    }

    public boolean canCastSpell(Cell cell) {
        return spell.canCast(getPlayer(), cell);
    }
    public void castSpell(Cell cell) {
        spell.canCast(getPlayer(), cell);
    }
}
