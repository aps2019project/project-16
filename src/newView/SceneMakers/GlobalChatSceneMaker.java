package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.globalChat.GlobalChatPane;

public class GlobalChatSceneMaker extends SceneMaker {
    private static GlobalChatPane globalChatPane = new GlobalChatPane();

    public GlobalChatSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }


    @Override
    public Scene makeScene() throws Exception {
        return new MyScene(globalChatPane);
    }

    public static GlobalChatPane getGlobalChatPane() {
        return globalChatPane;
    }
}
