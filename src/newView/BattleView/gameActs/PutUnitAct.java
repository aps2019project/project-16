package newView.BattleView.gameActs;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import models.card.Unit;
import newView.AnimationMaker;
import newView.BattleView.GameGraphicData;
import newView.GraphicalElements.battle.HandElement;
import newView.GraphicalElements.battle.Tile;
import newView.Type;

public class PutUnitAct extends GameAct {
    private String cardName = "piran";//todo must be from CARD
    private Type type = Type.MINION;//todo must be from CARD
    private int row;
    private int column;
    private boolean isForLeft;
    private Unit unit;//todo must be in constructor

    public PutUnitAct(int row, int column, boolean isForLeft) {
        this.row = row;
        this.column = column;
        this.isForLeft = isForLeft;
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
        ImageView idleView = AnimationMaker.getBreathingAnimation(cardName, type.getName());

        if (isForLeft == GameGraphicData.isOnLeft()) {
            HandElement handElement = GameGraphicData.getHandBox().getHandElement(cardName, type.getName());
            handElement.setImageView(null, null, null, null);
        }

        Tile putTile = GameGraphicData.getTilesPane().getTile(row, column);
        putTile.enableColorAnimation(Color.NAVY);
        putTile.setImageView(idleView, unit);
    }
}
