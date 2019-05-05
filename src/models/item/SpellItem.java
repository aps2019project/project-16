package models.item;

import models.Cell;
import models.Player;
import models.magic.Spell;

public class SpellItem extends Item {
    private Spell spell;

    private SpellItem(String name, int buyPrice, int sellPrice, Type type, Spell spell) {
        super(name, buyPrice, sellPrice, type);
        this.spell = spell;
    }

    public static class Builder extends Item.Builder {
        private Spell spell;

        public Builder setSpell(Spell spell) {
            this.spell = spell;
            return this;
        }

        public SpellItem create() {
            return new SpellItem(getName(), getBuyPrice(), getSellPrice(), getType(), spell);
        }
    }

    @Override
    public void use(Player player, Cell cell) {
        spell.cast(player, cell);
    }
}
