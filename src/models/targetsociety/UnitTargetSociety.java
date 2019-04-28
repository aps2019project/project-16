package models.targetsociety;

import models.Player;
import models.card.Hero;
import models.card.Minion;
import models.card.Unit;

public abstract class UnitTargetSociety extends TargetSociety {
    private TargetType targetType;
    private TargetTeam targetTeam;

    public UnitTargetSociety(TargetType targetType, TargetTeam targetTeam) {
        this.targetType = targetType;
        this.targetTeam = targetTeam;
    }

    public enum TargetType {
        UNIT,
        MINION,
        HERO
    }

    public enum TargetTeam {
        FRIEND,
        ENEMY
    }

    boolean doesEffect(Unit unit, Player player) {
        if (targetTeam == TargetTeam.FRIEND && unit.getPlayer() != player)
            return false;
        if (targetTeam == TargetTeam.ENEMY && unit.getPlayer() == player)
            return false;
        if (targetType == TargetType.HERO && !(unit instanceof Hero))
            return false;
        if (targetType == TargetType.MINION && !(unit instanceof Minion))
            return false;
        return true;
    }
}
