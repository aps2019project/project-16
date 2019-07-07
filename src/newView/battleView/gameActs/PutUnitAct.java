package newView.battleView.gameActs;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import models.card.Hero;
import models.card.Unit;
import newView.AnimationMaker;
import newView.GraphicalElements.battle.HandElement;
import newView.GraphicalElements.battle.Tile;
import newView.Type;
import newView.battleView.GameGraphicData;

public class PutUnitAct extends GameAct {
    private String cardName;
    private Type type;
    private int row;
    private int column;
    private boolean isForLeft;
    private boolean isCheatPut;
    private Unit unit;

    public PutUnitAct(int row, int column, boolean isForLeft, Unit unit, boolean isCheatPut) {
        this.row = row;
        this.column = column;
        this.isForLeft = isForLeft;
        this.isCheatPut = isCheatPut;
        this.unit = unit;

        if (unit instanceof Hero) {
            type = Type.HERO;
        } else {
            type = Type.MINION;
        }
        cardName = unit.getName();
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

        if (!GameGraphicData.isSpectator()) {
            if (type != Type.HERO && isForLeft == GameGraphicData.isOnLeft() && !isCheatPut) {
                HandElement handElement = GameGraphicData.getHandBox().getHandElement(cardName, type.getName());
                handElement.setImageView(null, null, null, null);
            }
        }

        Tile putTile = GameGraphicData.getTilesPane().getTile(row, column);
        putTile.enableColorAnimation(Color.NAVY);
        putTile.setImageView(idleView, unit);
    }
}
