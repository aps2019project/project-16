package newView.BattleView.gameActs;

import javafx.scene.image.ImageView;
import newView.AnimationMaker;
import newView.BattleView.GameGraphicData;
import newView.GraphicalElements.battle.HandElement;
import newView.GraphicalElements.battle.Tile;
import newView.Type;

public class SpellCastAct extends GameAct {
    private String cardName = "Shock";//todo must be from CARD
    private Type type = Type.SPELL;
    private int row;
    private int column;
    private boolean isForLeft;

    public SpellCastAct(int row, int column, boolean isForLeft) {
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
        ImageView spellView = AnimationMaker.getActiveAnimation(cardName, type.getName());

        Tile putTile = GameGraphicData.getTilesPane().getTile(row, column);
        putTile.showSpellCast(spellView);

        if (isForLeft == GameGraphicData.isOnLeft()) {
            HandElement handElement = GameGraphicData.getHandBox().getHandElement(cardName, type.getName());
            handElement.setImageView(null, null, null, null);
        }
    }
}
