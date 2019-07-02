package newView.GraphicalElements.battle;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.card.Card;
import newView.CardMaker;
import newView.GraphicalElements.ScaleTool;
import newView.Type;

import static newView.SceneMakers.SceneMaker.HEIGHT;
import static newView.SceneMakers.SceneMaker.SCALE;

public class GraveyardPane extends VBox {
    public GraveyardPane() {
        ScaleTool.relocate(this, 0, HEIGHT * 0.4);

        this.setSpacing(100 * SCALE);
    }

    public void addCard(String name, Type type, Card card) throws Exception {
        if (card == null) {
            return;
        }
        Pane cardView;
        if (type == Type.SPELL) {
            cardView = new CardMaker(name, type, card).getSpellCardView();
        } else if (type == Type.MINION) {
            cardView = new CardMaker(name, type, card).getUnitCardView();
        } else {
            cardView = new CardMaker(name, type, card).getUnitCardView();
        }
        ScaleTool.homothety(cardView, 0.5);
        this.getChildren().add(cardView);
    }
}
