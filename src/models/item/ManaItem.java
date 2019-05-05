package models.item;

import models.Cell;
import models.Player;

public class ManaItem extends Item {
    private int mana;
    private int duration;

    public ManaItem(int mana, int duration) {
        this.mana = mana;
        this.duration = duration;
    }

    @Override
    public void use(Player player, Cell cell) { //remember to cast on own turn only.
        if (duration > 0)
            player.setMana(player.getMana() + mana);
        duration --;
    }
}
