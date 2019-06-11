package models.item;

import models.Cell;
import models.Player;

public class ManaItem extends Item {
    private int mana;
    private int duration;

    private ManaItem(String name, int buyPrice, int sellPrice, Type type, int mana, int duration) {
        super(name, buyPrice, sellPrice, type);
        this.mana = mana;
        this.duration = duration;
    }

    public static class ManaItemBuilder extends ItemBuilder {
        private int mana;
        private int duration;

        public ManaItemBuilder setMana(int mana) {
            this.mana = mana;
            return this;
        }

        public ManaItemBuilder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        @Override
        public ManaItem create() {
            return new ManaItem(getName(), getBuyPrice(), getSellPrice(), getType(), mana, duration);
        }
    }

    @Override
    public void use(Player player, Cell cell) { //remember to castOnEndTurn on own turn only.
        if (duration > 0)
            player.setMana(player.getMana() + mana);
        duration--;
    }
}
