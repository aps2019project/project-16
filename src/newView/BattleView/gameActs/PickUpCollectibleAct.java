package newView.BattleView.gameActs;

import newView.BattleView.GameGraphicData;
import newView.GraphicalElements.battle.Tile;

public class PickUpCollectibleAct extends GameAct {
    private int row;
    private int column;

    public PickUpCollectibleAct(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public void showAction() {
        try {
            makeAnimation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeAnimation() throws Exception {
        Tile tile = GameGraphicData.getTilesPane().getTile(row, column);
        tile.removeCollectible();
    }
}
