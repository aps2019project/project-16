package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;

public class ScoreBoardSceneMaker extends SceneMaker {
    public ScoreBoardSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws Exception {
        Pane pane = new Pane();
        BackgroundMaker.setBackgroundFor(pane , 1 , "scoreBoard");


        return new MyScene(pane);
    }
}
