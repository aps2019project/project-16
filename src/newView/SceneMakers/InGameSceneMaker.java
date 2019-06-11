package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.ForegroundMaker;
import newView.GraphicalElements.MyScene;
import newView.ScaleTool;

import java.io.FileNotFoundException;

public class InGameSceneMaker extends SceneMaker {
    @Override
    public Scene makeScene() throws FileNotFoundException {
        BorderPane borderPane = new BorderPane();
        BackgroundMaker.setBackgroundFor(borderPane, 10);

        ImageView mapBGView = ForegroundMaker.getForeground(10, 1021, 661);
        ScaleTool.relocateInScale(mapBGView, WIDTH / 2 - 1021 / 2, HEIGHT / 2 - 661 / 2);

        // TODO: 6/11/19

        borderPane.getChildren().addAll(mapBGView);
        return new MyScene(borderPane);
    }
}
