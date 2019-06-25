package newView.BattleView.gameActs;

import javafx.scene.image.ImageView;
import models.item.Item;
import newView.AnimationMaker;
import newView.BattleView.GameGraphicData;
import newView.GraphicalElements.battle.Tile;
import newView.Type;

public class UseCollectibleAct extends GameAct {
    private String itemName;
    private Type type = Type.ITEM;
    private int row;
    private int column;

    public UseCollectibleAct(int row, int column, Item item) {
        this.row = row;
        this.column = column;

        itemName = item.getName();
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
        ImageView itemCastView = AnimationMaker.getActiveAnimation(itemName, type.getName());

        Tile putTile = GameGraphicData.getTilesPane().getTile(row, column);
        putTile.showSpellCast(itemCastView);
    }
}
