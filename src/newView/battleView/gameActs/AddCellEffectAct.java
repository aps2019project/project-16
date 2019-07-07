package newView.battleView.gameActs;

import javafx.scene.paint.Color;
import models.CellEffectType;
import newView.GraphicalElements.battle.CellEffectPane;
import newView.GraphicalElements.battle.Tile;
import newView.battleView.GameGraphicData;

public class AddCellEffectAct extends GameAct {
    private CellEffectType effectType;
    private int row;
    private int column;

    public AddCellEffectAct(CellEffectType effectType, int row, int column) {
        this.effectType = effectType;
        this.row = row;
        this.column = column;
    }

    @Override
    public void showAction() {
        Tile tile = GameGraphicData.getTilesPane().getTile(row, column);
        CellEffectPane effectPane = tile.getCellEffectPane();
        tile.enableColorAnimation(Color.BLUE);
        switch (effectType) {
            case FIRE:
                effectPane.addFire();
                break;
            case HOLY:
                effectPane.addHoly();
                break;
            case POISON:
                effectPane.addPoison();
                break;
        }
    }
}
