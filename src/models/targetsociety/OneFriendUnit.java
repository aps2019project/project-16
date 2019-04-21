package models.targetsociety;

import models.Cell;
import models.Player;

public class OneFriendUnit extends OneUnit{
    @Override
    public boolean canCast(Player player, Cell cell) {
        return cell.hasUnit() && cell.getUnit().getPlayer() == player;
    }
}
