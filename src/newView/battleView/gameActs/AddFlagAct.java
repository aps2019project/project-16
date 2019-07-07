package newView.battleView.gameActs;

import newView.GraphicalElements.battle.Tile;
import newView.battleView.GameGraphicData;

public class AddFlagAct extends GameAct {
    private int row;
    private int column;

    public AddFlagAct(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public void showAction() {
        Tile tile = GameGraphicData.getTilesPane().getTile(row, column);
        tile.addFlag();
    }
}
