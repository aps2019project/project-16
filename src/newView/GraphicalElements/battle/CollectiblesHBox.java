package newView.GraphicalElements.battle;

import javafx.scene.layout.HBox;
import newView.GraphicalElements.ScaleTool;

import java.util.ArrayList;

import static newView.GraphicalElements.battle.CollectibleElement.COLLECTIBLE_LENGTH;
import static newView.SceneMakers.SceneMaker.SCALE;

public class CollectiblesHBox extends HBox {
    private ArrayList<CollectibleElement> collectibleElements = new ArrayList<>();

    public CollectiblesHBox() {
        ScaleTool.relocate(this, 0, 0);
        this.setSpacing(COLLECTIBLE_LENGTH * SCALE * 1.1);
    }

    public void addElement(CollectibleElement element) {
        this.getChildren().add(element);
        collectibleElements.add(element);
    }

    public void deleteCollectible(String itemName) {
        CollectibleElement collectibleElement = null;
        for (CollectibleElement element : collectibleElements) {
            if (element.getItem().getName().equals(itemName)) {
                collectibleElement = element;
                break;
            }
        }
        if (collectibleElement != null) {
            collectibleElement.goUpAndDelete();
        }
    }
}
