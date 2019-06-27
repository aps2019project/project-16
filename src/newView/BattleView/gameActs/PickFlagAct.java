package newView.BattleView.gameActs;

import newView.BattleView.GameGraphicData;
import newView.GraphicalElements.battle.Tile;

public class PickFlagAct extends GameAct {
    private int row;
    private int column;
    private boolean isForLeft;// TODO: 6/24/19 must be used

    public PickFlagAct(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public void showAction() {
        Tile tile = GameGraphicData.getTilesPane().getTile(row, column);
        tile.removeFlag();
    }
}
