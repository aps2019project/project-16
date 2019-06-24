package newView.BattleView.gameActs;

import javafx.scene.image.ImageView;
import models.card.Card;
import newView.AnimationMaker;
import newView.BattleView.GameGraphicData;
import newView.GraphicalElements.battle.HandElement;
import newView.GraphicalElements.battle.HandHBox;
import newView.Type;

public class AddToHandAct extends GameAct {
    private String cardName = "Shock";//todo must be from CARD
    private Type cardType = Type.SPELL;//todo must be from CARD
    private Card card;//todo must be in constructor
    private boolean isForLeft;

    public AddToHandAct(boolean isForLeft) {
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
        if (isForLeft == GameGraphicData.isOnLeft()) {
            HandHBox handHBox = GameGraphicData.getHandBox();
            HandElement handElement = handHBox.getAnEmptyElement();
            if (handElement != null) {
                ImageView imageView;
                if (cardType.equals(Type.SPELL)) {
                    imageView = AnimationMaker.getNothingAnimation(cardName, cardType.getName());
                } else {
                    imageView = AnimationMaker.getIdleAnimation(cardName, cardType.getName());
                }
                handElement.setImageView(imageView, null, cardName, cardType);
            }
        }
    }
}
