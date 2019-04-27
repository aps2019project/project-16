package models.card;

import models.Cell;
import models.Player;
import models.Spell;

public class SpellCard extends Card {
    private Spell spell;

    public SpellCard(String name, int manaCost, int buyPrice, int sellPrice, String description, Spell spell) {
        super(name, manaCost, buyPrice, sellPrice, description);
        this.spell = spell;
    }

    public static class SpellCardBuilder extends CardBuilder {
        private Spell spell;

        public SpellCardBuilder setSpell(Spell spell) {
            this.spell = spell;
            return this;
        }

        public SpellCard create() {
            return new SpellCard(getName(), getManaCost(),getBuyPrice(), getSellPrice(), getDescription(), spell);
        }
    }

    public boolean canCast(Player player, Cell cell) {
        return spell.canCast(player, cell);
    }

    public void cast(Player player, Cell cell) {
        spell.cast(player, cell);
    }
}
