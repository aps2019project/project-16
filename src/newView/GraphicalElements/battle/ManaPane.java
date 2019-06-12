package newView.GraphicalElements.battle;

import javafx.scene.layout.HBox;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static newView.GraphicalElements.battle.ManaElement.MANA_LENGTH;
import static newView.SceneMakers.SceneMaker.SCALE;

public class ManaPane extends HBox {
    private int numberOfManas;
    private int capacity;
    private ArrayList<ManaElement> elements = new ArrayList<>();

    public ManaPane(int capacity, int numberOfManas) throws FileNotFoundException {
        this.numberOfManas = numberOfManas;
        this.capacity = capacity;
        this.setSpacing(MANA_LENGTH * SCALE * 1.1);
        initialize();
    }

    private void initialize() throws FileNotFoundException {
        for (int i = 0; i < capacity; i++) {
            elements.add(new ManaElement());
        }
        for (int i = 0; i < numberOfManas; i++) {
            elements.get(i).setFull();
        }
        this.getChildren().addAll(elements);
    }

    public void addCapacity(int number) {
        capacity += number;
        try {
            for (int i = 0; i < number; i++) {
                ManaElement element = new ManaElement();
                elements.add(element);
                this.getChildren().add(element);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
