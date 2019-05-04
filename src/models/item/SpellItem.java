package models.item;

import models.Cell;
import models.Player;
import models.magic.Spell;

public class SpellItem extends Item {
    private Spell spell;

    public SpellItem(Spell spell) {
        this.spell = spell;
    }

    @Override
    public void use(Player player, Cell cell) {
        spell.cast(player, cell);
    }
}
