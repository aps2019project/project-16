package models.targetsociety;

import models.Player;
import models.attackType.Hybrid;
import models.attackType.Melee;
import models.attackType.Ranged;
import models.card.Hero;
import models.card.Minion;
import models.card.Unit;

public abstract class UnitTargetSociety extends TargetSociety {
    private TargetType targetType;
    private TargetTeam targetTeam;
    private TargetAttackType targetAttackType;

    public UnitTargetSociety(TargetType targetType, TargetTeam targetTeam, TargetAttackType targetAttackType) {
        this.targetType = targetType;
        this.targetTeam = targetTeam;
        this.targetAttackType = targetAttackType;
    }

    public enum TargetType {
        UNIT,
        MINION,
        HERO
    }

    public enum TargetTeam {
        FRIEND,
        ENEMY,
        ANY
    }

    public enum TargetAttackType {
        MELEE,
        RANGED_HYBRID,
        ANY
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
        if (targetAttackType == TargetAttackType.MELEE && !(unit.getAttackType() instanceof Melee))
            return false;
        if (targetAttackType == TargetAttackType.RANGED_HYBRID && !(unit.getAttackType() instanceof Ranged) &&
                !(unit.getAttackType() instanceof Hybrid))
            return false;
        return true;
    }
}
