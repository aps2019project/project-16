package models;

public class SpellCard extends Card {
    private Spell spell;

    public SpellCard(String name, int manaCost, int buyPrice, int sellPrice, Spell spell) {
        super(name, manaCost, buyPrice, sellPrice);
        this.spell = spell;
    }
    public boolean canCast(Player player, Cell cell) {
        return spell.canCast(player, cell);
    }

    public void cast(Player player, Cell cell) {
        spell.cast(player, cell);
    }
}
