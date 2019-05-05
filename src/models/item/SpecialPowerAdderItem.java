package models.item;

import models.Cell;
import models.Player;
import models.SpecialPowerCastTime;
import models.Table;
import models.attackType.Hybrid;
import models.attackType.Melee;
import models.attackType.Ranged;
import models.card.Card;
import models.card.Hero;
import models.card.Minion;
import models.card.Unit;
import models.magic.Spell;

import java.util.ArrayList;
import java.util.Random;

public class SpecialPowerAdderItem extends Item {
    private ArrayList<TargetAttackType> targetAttackTypes;
    private TargetType targetType;
    private TargetSelection targetSelection;
    private SpecialPowerCastTime specialPowerCastTime;
    private Spell specialPower;

    private SpecialPowerAdderItem(String name, int buyPrice, int sellPrice, ArrayList<TargetAttackType> targetAttackTypes,
                                  TargetType targetType, TargetSelection targetSelection,
                                  SpecialPowerCastTime specialPowerCastTime, Spell specialPower) {
        super(name, buyPrice, sellPrice);
        this.targetAttackTypes = targetAttackTypes;
        this.targetType = targetType;
        this.targetSelection = targetSelection;
        this.specialPowerCastTime = specialPowerCastTime;
        this.specialPower = specialPower;
    }

    public static class Builder extends Item.Builder {
        private ArrayList<TargetAttackType> targetAttackTypes = new ArrayList<>();
        private TargetType targetType;
        private TargetSelection targetSelection;
        private SpecialPowerCastTime specialPowerCastTime;
        private Spell specialPower;

        public Builder addTargetAttackType(TargetAttackType targetAttackType) {
            targetAttackTypes.add(targetAttackType);
            return this;
        }

        public Builder setTargetAttackTypeAll() {
            targetAttackTypes.add(TargetAttackType.MELEE);
            targetAttackTypes.add(TargetAttackType.RANGED);
            targetAttackTypes.add(TargetAttackType.HYBRID);
            return this;
        }

        public Builder setTargetType(TargetType targetType) {
            this.targetType = targetType;
            return this;
        }

        public Builder setTargetSelection(TargetSelection targetSelection) {
            this.targetSelection = targetSelection;
            return this;
        }

        public Builder setSpecialPowerCastTime(SpecialPowerCastTime specialPowerCastTime) {
            this.specialPowerCastTime = specialPowerCastTime;
            return this;
        }

        public Builder setSpecialPower(Spell specialPower) {
            this.specialPower = specialPower;
            return this;
        }

        @Override
        public SpecialPowerAdderItem create() {
            return new SpecialPowerAdderItem(getName(), getBuyPrice(), getSellPrice(), targetAttackTypes, targetType,
                    targetSelection, specialPowerCastTime, specialPower);
        }
    }

    public enum TargetAttackType {
        MELEE,
        RANGED,
        HYBRID
    }

    public enum TargetType {
        UNIT,
        MINION,
        HERO
    }

    public enum TargetSelection {
        RANDOM,
        ALL
    }

    @Override
    public void use(Player player, Cell cell) {
        if (targetSelection == TargetSelection.ALL) {
            for (Card card : player.getDeck().getCards())
                if (card instanceof Unit)
                    if (checkSpecialPowerMatches((Unit) card))
                        ((Unit) card).addSpecialPower(specialPowerCastTime, specialPower);
        }
        if (targetSelection == TargetSelection.RANDOM) {
            Table table = cell.getTable();
            Random random = new Random();
            int randomRow = random.nextInt(Table.HEIGHT);
            int randomColumn = random.nextInt(Table.WIDTH);
            int row = randomRow;
            do {
                int column = randomColumn;
                do {
                    if (table.getCell(row, column).hasUnit() && table.getCell(row, column).getUnit().getPlayer() == player) {
                        if (checkSpecialPowerMatches(table.getCell(row, column).getUnit())) {
                            table.getCell(row, column).getUnit().addSpecialPower(specialPowerCastTime, specialPower);
                            return;
                        }
                    }
                    column = (column + 1) % Table.WIDTH;
                } while (column != randomColumn);
                row = (row + 1) % Table.HEIGHT;
            } while (row != randomRow);
        }
    }

    private boolean checkSpecialPowerMatches(Unit unit) {
        if (targetType == TargetType.MINION && !(unit instanceof Minion))
            return false;
        if (targetType == TargetType.HERO && !(unit instanceof Hero))
            return false;
        if (unit.getAttackType() instanceof Melee)
            return targetAttackTypes.contains(TargetAttackType.MELEE);
        if (unit.getAttackType() instanceof Ranged)
            return targetAttackTypes.contains(TargetAttackType.RANGED);
        if (unit.getAttackType() instanceof Hybrid)
            return targetAttackTypes.contains(TargetAttackType.HYBRID);
        return false;
    }
}

