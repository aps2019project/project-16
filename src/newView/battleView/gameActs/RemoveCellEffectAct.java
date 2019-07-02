package newView.battleView.gameActs;

import javafx.scene.paint.Color;
import models.CellEffectType;
import newView.battleView.GameGraphicData;
import newView.GraphicalElements.battle.CellEffectPane;
import newView.GraphicalElements.battle.Tile;

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
        Tile tile = GameGraphicData.getTilesPane().getTile(row, column);
        CellEffectPane effectPane = tile.getCellEffectPane();
        tile.enableColorAnimation(Color.LAVENDER);
        switch (effectType) {
            case FIRE:
                effectPane.decreaseFire();
                break;
            case HOLY:
                effectPane.decreaseHoly();
                break;
            case POISON:
                effectPane.decreasePoison();
                break;
        }
    }
}
