package newView.GraphicalElements.battle;

import javafx.scene.control.ScrollPane;
import newView.GraphicalElements.ScaleTool;

import static newView.SceneMakers.SceneMaker.HEIGHT;

public class GraveyardPane extends ScrollPane {
    public GraveyardPane() {
        ScaleTool.relocate(this, 0, HEIGHT * 0.6);
        //size
    }

    public void addCard(String name, String type) {
        //animation + name
        //this.getChildren().add()

    }
}
