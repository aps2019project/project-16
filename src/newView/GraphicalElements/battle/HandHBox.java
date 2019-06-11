package newView.GraphicalElements.battle;

import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static newView.GraphicalElements.battle.HandElement.HAND_LENGTH;
import static newView.SceneMakers.SceneMaker.SCALE;

public class HandHBox extends HBox {
    private ArrayList<HandElement> handElements = new ArrayList<>();

    public HandHBox() throws FileNotFoundException {
        this.setSpacing(HAND_LENGTH * SCALE * 1.1);
        for (int i = 0; i < 5; i++) {
            HandElement element = new HandElement();
            handElements.add(element);
            this.getChildren().add(element);
        }
    }
}
