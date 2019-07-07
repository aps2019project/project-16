package newView.battleView.gameActs;

import javafx.scene.image.ImageView;
import models.card.Card;
import newView.AnimationMaker;
import newView.GraphicalElements.battle.HandElement;
import newView.GraphicalElements.battle.Tile;
import newView.Type;
import newView.battleView.GameGraphicData;

public class SpellCastAct extends GameAct {
    private String cardName;
    private Type type = Type.SPELL;
    private int row;
    private int column;
    private boolean isForLeft;
    private boolean isCheatCast;

    public SpellCastAct(int row, int column, boolean isForLeft, Card card, boolean isCheatCast) {
        this.row = row;
        this.column = column;
        this.isForLeft = isForLeft;
        this.isCheatCast = isCheatCast;

        cardName = card.getName();
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

        if (!GameGraphicData.isSpectator() && isForLeft == GameGraphicData.isOnLeft() && !isCheatCast) {
            HandElement handElement = GameGraphicData.getHandBox().getHandElement(cardName, type.getName());
            Card card = handElement.getCard();
            handElement.setImageView(null, null, null, null);

            GameGraphicData.getGraveyardPane().addCard(cardName, type, card);
        }
    }
}
