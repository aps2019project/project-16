package models.item;

import models.Cell;
import models.Player;

public class ManaItem extends Item {
    private int mana;
    private int duration;

    private ManaItem(String name, int buyPrice, int sellPrice, int mana, int duration) {
        super(name, buyPrice, sellPrice);
        this.mana = mana;
        this.duration = duration;
    }

    public static class Builder extends Item.Builder {
        private int mana;
        private int duration;

        public Builder setMana(int mana) {
            this.mana = mana;
            return this;
        }

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        @Override
        public Item create() {
            return new ManaItem(getName(), getBuyPrice(), getSellPrice(), mana, duration);
        }
    }

    @Override
    public void use(Player player, Cell cell) { //remember to cast on own turn only.
        if (duration > 0)
            player.setMana(player.getMana() + mana);
        duration--;
    }
}
