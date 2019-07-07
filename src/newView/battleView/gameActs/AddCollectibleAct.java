package newView.battleView.gameActs;

import javafx.scene.image.ImageView;
import newView.AnimationMaker;
import newView.GraphicalElements.battle.Tile;
import newView.Type;
import newView.battleView.GameGraphicData;

public class AddCollectibleAct extends GameAct {
    private int row;
    private int column;
    private String itemName;

    public AddCollectibleAct(int row, int column, String itemName) {
        this.row = row;
        this.column = column;
        this.itemName = itemName;
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
        ImageView itemView = AnimationMaker.getNothingAnimation(itemName, Type.ITEM.getName());

        Tile tile = GameGraphicData.getTilesPane().getTile(row, column);
        tile.addCollectible(itemView);
    }
}
