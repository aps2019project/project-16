package models.item;

import models.Cell;
import models.Player;

public class Usable extends Item {
    public Usable(String name, int buyPrice, int sellPrice, Type type) {
        super(name, buyPrice, sellPrice, type);
    }

    @Override
    public void use(Player player, Cell cell) {

    }
}
