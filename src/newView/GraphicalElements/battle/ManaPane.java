package newView.GraphicalElements.battle;

import javafx.scene.layout.HBox;
import javafx.scene.transform.Rotate;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ManaPane extends HBox {
    private int numberOfManas;
    private static final int CAPACITY = 12;
    private ArrayList<ManaElement> elements = new ArrayList<>();

    public ManaPane(int numberOfManas) throws FileNotFoundException {
        this.getTransforms().add(new Rotate(-8));
        this.numberOfManas = numberOfManas;
        this.setSpacing(0);
        initialize();
    }

    private void initialize() throws FileNotFoundException {
        for (int i = 0; i < CAPACITY; i++) {
            elements.add(new ManaElement());
        }
        for (int i = 0; i < numberOfManas; i++) {
            elements.get(i).setFull();
        }
        this.getChildren().addAll(elements);
    }

    public void setNumberOfManas(int numberOfManas) {
        if (numberOfManas > this.numberOfManas) {
            for (int i = this.numberOfManas; i < numberOfManas; i++) {
                elements.get(i).setFull();
            }
        } else {
            for (int i = numberOfManas; i < this.numberOfManas ; i++) {
                elements.get(i).setEmpty();
            }
        }
        this.numberOfManas = numberOfManas;
    }
}
