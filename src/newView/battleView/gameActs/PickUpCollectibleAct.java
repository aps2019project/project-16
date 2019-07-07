package newView.battleView.gameActs;

import models.item.Item;
import newView.GraphicalElements.battle.Tile;
import newView.battleView.GameGraphicData;

import java.util.ArrayList;

public class PickUpCollectibleAct extends GameAct {
    private int row;
    private int column;
    private boolean forLeft;
    private ArrayList<Item> items;


    public PickUpCollectibleAct(int row, int column, boolean forLeft, ArrayList<Item> items) {
        this.row = row;
        this.column = column;
        this.forLeft = forLeft;
        this.items = items;
    }

    @Override
    public void showAction() {
        makeAnimation();
    }

    private void makeAnimation() {
        Tile tile = GameGraphicData.getTilesPane().getTile(row, column);
        tile.removeCollectible();
        if (!GameGraphicData.isSpectator() && forLeft == GameGraphicData.isOnLeft()) {
            GameGraphicData.addCollectibles(items);
        }
    }
}
