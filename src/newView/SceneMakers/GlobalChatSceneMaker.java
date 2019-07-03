package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;

public class GlobalChatSceneMaker extends SceneMaker {

    public GlobalChatSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public Scene makeScene() throws Exception {
        Pane root = new Pane();

        BackgroundMaker.setBackgroundFor(root, 1, "globalChat");

        return new MyScene(root);
    }
}
