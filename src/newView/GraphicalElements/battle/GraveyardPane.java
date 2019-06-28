package newView.GraphicalElements.battle;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    public void addCard(String name, Type type) throws Exception {
        Pane cardView;
        if (type == Type.SPELL) {
            cardView = new CardMaker(name, type).getSpellCardView();
        } else if (type == Type.MINION) {
            cardView = new CardMaker(name, type).getUnitCardView();
        } else {
            cardView = new CardMaker(name, type).getUnitCardView();
        }
        ScaleTool.homothety(cardView, 0.5);
        this.getChildren().add(cardView);
    }
}
