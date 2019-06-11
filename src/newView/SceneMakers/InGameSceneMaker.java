package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.ForegroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.ScaleTool;
import newView.GraphicalElements.battle.HandHBox;

import java.io.FileNotFoundException;

import static newView.GraphicalElements.battle.HandElement.HAND_LENGTH;

public class InGameSceneMaker extends SceneMaker {
    @Override
    public Scene makeScene() throws FileNotFoundException {
        BorderPane borderPane = new BorderPane();
        BackgroundMaker.setBackgroundFor(borderPane, 10);

        ImageView mapBGView = ForegroundMaker.getForeground(10, 1300, 661);
        ScaleTool.relocate(mapBGView, WIDTH / 2 - 1300 / 2, HEIGHT / 2 - 661 / 2);

        HBox handHBox = new HandHBox();
        ScaleTool.relocate(handHBox, WIDTH / 2 - HAND_LENGTH * 3.5, HEIGHT - HAND_LENGTH);

        // TODO: 6/11/19

        borderPane.getChildren().addAll(mapBGView, handHBox);
        return new MyScene(borderPane);
    }
}
