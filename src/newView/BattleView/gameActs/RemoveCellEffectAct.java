package newView.BattleView.gameActs;

import models.CellEffectType;

public class RemoveCellEffectAct extends GameAct {
    private CellEffectType effectType;
    private int row;
    private int column;

    public RemoveCellEffectAct(CellEffectType effectType, int row, int column) {
        this.effectType = effectType;
        this.row = row;
        this.column = column;
    }

    @Override
    public void showAction() {
        // TODO Sadegh: 6/29/19
    }
}
