package newView.BattleView.gameActs;

import javafx.scene.image.ImageView;
import models.card.Card;
import models.card.Hero;
import models.card.Minion;
import newView.AnimationMaker;
import newView.BattleView.GameGraphicData;
import newView.GraphicalElements.battle.HandElement;
import newView.GraphicalElements.battle.HandHBox;
import newView.Type;

public class AddToHandAct extends GameAct {
    private String cardName;
    private Type cardType;
    private boolean isForLeft;
    private Card card;

    public AddToHandAct(boolean isForLeft, Card card) {
        this.isForLeft = isForLeft;
        this.card = card;

        cardName = card.getName();

        if (card instanceof Hero) {
            cardType = Type.HERO;
        } else if (card instanceof Minion) {
            cardType = Type.MINION;
        } else {
            cardType = Type.SPELL;
        }
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
