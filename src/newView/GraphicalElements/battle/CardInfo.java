package newView.GraphicalElements.battle;

import javafx.scene.layout.Pane;
import newView.CardMaker;
import newView.GraphicalElements.ScaleTool;
import newView.Type;

import java.util.ArrayList;

import static newView.SceneMakers.SceneMaker.HEIGHT;
import static newView.SceneMakers.SceneMaker.WIDTH;

public class CardInfo extends Pane {
    private Pane cardView;
    private ArrayList<Pane> panes = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();

    public CardInfo() {
        ScaleTool.relocate(this, WIDTH * 0.8, HEIGHT * 0.4);
    }

    public void setCardView(String name, Type type) {
        Pane cardView = getPane(name, type);

        if (this.cardView != null) {
            this.getChildren().remove(this.cardView);
        }

        this.cardView = cardView;
        this.getChildren().add(cardView);
    }

    public void setNull() {
        if (cardView != null) {
            this.getChildren().remove(cardView);
            cardView = null;
        }
    }

    private Pane getPane(String name, Type type) {
        Pane cardView = findPane(name);
        if (cardView != null) {
            return cardView;
        }
        try {
            if (type == Type.SPELL) {
                cardView = new CardMaker(name, type).getSpellCardView();
            } else if (type == Type.ITEM) {
                cardView = new CardMaker(name, type).getItemCardView();
            } else {
                cardView = new CardMaker(name, type).getUnitCardView();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        panes.add(cardView);
        names.add(name);
        return cardView;
    }

    private Pane findPane(String name) {
        for (int i = 0; i < names.size(); i++) {
            if (name.equals(names.get(i))) {
                return panes.get(i);
            }
        }
        return null;
    }
}
