package newView.SceneMakers;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import newView.GraphicalElements.BackgroundMaker;
import newView.GraphicalElements.MyScene;
import newView.GraphicalElements.globalChat.GlobalChatPane;

import static ir.pas.ClientApp.getPrimaryStage;

public class GlobalChatSceneMaker extends SceneMaker {
    private static GlobalChatPane globalChatPane = new GlobalChatPane();

    public GlobalChatSceneMaker(Stage primaryStage) {
        super(primaryStage);
    }


    @Override
    public Scene makeScene() throws Exception {
        Pane root = new Pane();
        root.getChildren().add(globalChatPane);
        globalChatPane.getBack().setOnMouseClicked(event -> new MainMenuSceneMaker(getPrimaryStage()).set());
        return new MyScene(root);
    }

    public static GlobalChatPane getGlobalChatPane() {
        return globalChatPane;
    }
}
